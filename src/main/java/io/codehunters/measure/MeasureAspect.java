package io.codehunters.measure;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.LongTaskTimer;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Timer;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

/**
 * Aspect for measuring methods
 *
 * @since 1.0.0
 * @author anresmontoyat@gmail.com
 */
@Aspect
@Component
public class MeasureAspect {

    private final Logger log = LoggerFactory.getLogger(MeasureAspect.class);

    private final MeterRegistry registry;
    private final ExpressionParser expressionParser;
    private final ParameterNameDiscoverer parameterNameDiscoverer;

    public MeasureAspect(MeterRegistry registry) {
        this.registry = registry;
        this.expressionParser = new SpelExpressionParser();
        this.parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
    }

    @Around("@annotation(measured)")
    public Object aroundAnnotated(ProceedingJoinPoint pjp, Measured measured) throws Throwable {
        log.debug("Measuring method: {}", pjp.getSignature().toShortString());
        return time(pjp, measured);
    }

    private Object time(ProceedingJoinPoint pjp, Measured measured) throws Throwable {
        // derive method and a safe metric name early
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        String metricName = measured.name() == null || measured.name().isBlank()
                ? method.getDeclaringClass().getSimpleName() + "." + method.getName()
                : measured.name();

        List<String> tags = new ArrayList<>(Arrays.asList(measured.tags()));
        if ((measured.expressions() != null) && measured.expressions().length > 0) {
            Object[] args = pjp.getArgs();
            String[] paramNames = parameterNameDiscoverer.getParameterNames(method);

            if (paramNames == null || paramNames.length == 0) {
                paramNames = new String[args.length];
                for (int i = 0; i < args.length; i++) paramNames[i] = "arg" + i;
            }

            EvaluationContext evaluationContext = new StandardEvaluationContext();

            for (int i = 0; i < args.length; i++) {
                evaluationContext.setVariable("p" + i, args[i]);
                evaluationContext.setVariable("a" + i, args[i]);
                evaluationContext.setVariable("arg" + i, args[i]);
            }

            if (paramNames != null) {
                for (int i = 0; i < paramNames.length; i++) {
                    evaluationContext.setVariable(paramNames[i], args[i]);
                }
            }

            for (String expression : measured.expressions()) {
                String[] keyValue = expression.split("=", 2);
                if (keyValue.length == 2) {
                    String key = keyValue[0].trim();
                    String value = expressionParser
                            .parseExpression(keyValue[1].trim())
                            .getValue(evaluationContext, String.class);
                    if (value != null && !value.isBlank()) {
                        tags.add(key);
                        tags.add(value);
                    }
                }
            }
        }

        Iterable<Tag> micrometerTags = toTags(tags);

        boolean useLongTask = measured.longTask();

        Timer.Sample timerSample = null;
        Timer timer = null;
        LongTaskTimer.Sample longSample = null;

        if (useLongTask) {
            LongTaskTimer ltt = LongTaskTimer.builder(String.format("%s.long-task", metricName))
                    .description("Long task execution time")
                    .tags(micrometerTags)
                    .register(registry);
            longSample = ltt.start();
        } else {
            timerSample = Timer.start(registry);
            timer = Timer.builder(metricName)
                    .description("Execution time")
                    .tags(micrometerTags)
                    .register(registry);
        }

        try {
            return pjp.proceed();
        } catch (Throwable ex) {
            if (measured.recordExceptions()) {
                Counter.builder(String.format("%s.errors", metricName))
                        .tags(micrometerTags)
                        .tag("exception", ex.getClass().getSimpleName())
                        .register(registry)
                        .increment();
            }
            throw ex;

        } finally {
            if (useLongTask && longSample != null) {
                longSample.stop();
            } else if (timerSample != null && timer != null) {
                timerSample.stop(timer);
            }
        }
    }

    private Iterable<Tag> toTags(List<String> kv) {
        List<Tag> out = new ArrayList<>();
        for (int i = 0; i + 1 < kv.size(); i += 2) {
            String k = kv.get(i), v = kv.get(i + 1);
            if (k != null && v != null && !k.isBlank() && !v.isBlank()) out.add(Tag.of(k, v));
        }
        return out;
    }
}
