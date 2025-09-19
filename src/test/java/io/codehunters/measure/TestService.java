package io.codehunters.measure;

public interface TestService {
    String doWork();

    void processUser(String userId);

    void longOp();

    void willFail();
}
