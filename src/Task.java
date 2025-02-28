public class Task {
    String name; // Task name
    int period; // Task period
    int executionTime; // Task execution time
    int priority;  // Task priority
    int worstCaseResponseTime = 0; // Task worst case response time

    public Task(String name, int period, int executionTime) {
        this.name = name;
        this.period = period;
        this.executionTime = executionTime;
        this.priority = period; // Lower period = higher priority (Rate Monotonic)
    }
}
