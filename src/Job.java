public class Job {
    Task task;
    int releaseTime; // Time when the job was released
    int remainingTime; // Remaining time to complete the job
    int responseTime; // Time when the job was first executed
    public Job(Task task, int releaseTime) {
        this.task = task;
        this.releaseTime = releaseTime;
        this.remainingTime = task.executionTime;
        this.responseTime = releaseTime;
    }

}
