# Kotlin Code Style & Conventions

---

## 1) Goals

- [ ] Write clean, efficient, and well-documented Kotlin code  
- [ ] Apply **Clean Code, SOLID, KISS, YAGNI** principles  
- [ ] Maximize readability, maintainability, and testability  
- [ ] Prefer explicitness over cleverness; prefer composition over inheritance  
- [ ] Fail fast, validate early, and surface actionable errors  
- [ ] Ensure consistent APIs with OpenAPI and strong observability  

---

## 2) Source Layout

**Project structure (Gradle + Kotlin DSL)**

```
settings.gradle.kts
build.gradle.kts
gradle.properties
/gradle
compose.yaml
dependency-check-suppression.xml
/.editorconfig
/config/spotless/ (ktlint rules)
/config/detekt/detekt.yml

```

- [ ] Use `application.yml` 
- [ ] Secrets externalized via env vars or vaults  

---

## 3) Naming Conventions

- **Packages** → lowercase → `io.codehunters.artifact`  
- **Classes/Interfaces/Enums** → PascalCase → `InvoiceService`, `UserStatus`  
- **Functions & Properties** → camelCase → `calculateTotal()`, `unitPrice`  
- **Constants** → UPPER_SNAKE_CASE → `DEFAULT_PAGE_SIZE`  
- **Tests** → `ClassNameMethodName_ShouldExpectedBehavior` or Kotest BDD  

---

## 4) Formatting & Style

- [ ] Line length ≤ 120 columns  
- [ ] Indentation: 2 spaces  
- [ ] Always use braces `{}` for control flow  
- [ ] No wildcard imports  
- [ ] Default to `val`; `var` only when required  
- [ ] Use `T?` for nullability; avoid `!!`  
- [ ] Restrict visibility (`private`/`internal`) by default  
- [ ] Use `data class`/`value class` for DTOs and primitives  
- [ ] Comments explain *why*, not *what*  

**Checkers**
- Spotless + ktlint  
- Detekt  
- Kover (coverage)  

---

## 5) Logging, Tracing & Observability

- [ ] Use SLF4J (no `println`)  
- [ ] Avoid logging sensitive data  
- [ ] Propagate trace/context IDs  
- [ ] Use Micrometer + Prometheus/Grafana  

---

## 6) Performance & Resilience

- [ ] Apply connection pooling, timeouts, retries  
- [ ] Use Resilience4j for circuit breakers/bulkheads  
- [ ] Pagination/streaming instead of bulk loading  
- [ ] Cache with TTL/invalidations  
- [ ] Coroutines: structured concurrency only  

---

## 7) Testing Strategy

- **Unit tests** → fast, pure Kotlin, MockK for doubles  
- **Integration tests** → Testcontainers for DB/messaging  
- **Contract tests** → HTTP & event contracts  

```kotlin
@Test
fun calculateTotal_ShouldApplyVat() {
  val calc = InvoiceCalculator(vat = BigDecimal("0.19"))
  assertThat(calc.total(BigDecimal("100"))).isEqualByComparingTo("119.00")
}
```

---

## 8) API Documentation

- [ ] Use `springdoc-openapi`  
- [ ] Swagger UI only for non-prod  
- [ ] Include examples, error schemas, Problem+JSON  

```kotlin
dependencies {
  implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:latest-version")
}
```

---

## 9) Kotlin Best Practices Recap

- [ ] Immutability and pure functions preferred  
- [ ] Use `value class` for domain primitives  
- [ ] No JPA entities in APIs → map to DTOs  
- [ ] Use sealed results or `Result<T>` instead of exceptions for flow  
- [ ] Ubiquitous language in domain layer  

---

## 10) Build & Tooling

- [ ] Kotlin DSL + Version Catalogs (`libs.versions.toml`)  
- [ ] Dependency locking enabled  
- [ ] CI enforces Spotless, Detekt, Kover, OWASP  

---

## 11) Java Interop

- [ ] Keep domain in one language if possible  
- [ ] Annotate nullability (`@Nullable`, `@NotNull`)  
- [ ] Wrap Java APIs; avoid platform types in domain  

---

## 12) Checklists

### PR Checklist
- [ ] Code formatted (Spotless/ktlint) & Detekt clean  
- [ ] Tests (unit/integration/contract) updated and green  
- [ ] OpenAPI updated with examples/errors  
- [ ] Logs/metrics/traces present  
- [ ] Security review completed  

### Controller Checklist
- [ ] Input validated (`@Valid`)  
- [ ] Proper status codes + Problem+JSON errors  
- [ ] Uses DTOs (no entities)  
- [ ] Pagination/sorting consistent  
- [ ] Idempotency considered  

### Coroutine Checklist
- [ ] `suspend` at boundaries  
- [ ] Proper dispatcher (`Dispatchers.IO` for blocking)  
- [ ] Structured concurrency & cancellation propagation  

---
