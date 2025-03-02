package model;

import java.util.Random;

public class Job implements Comparable<Job> {
    public Task task;
    public int releaseTime;
    public int executionTime;
    public int priority;

    public Job(Task task, int releaseTime) {
        this.task = task;
        this.releaseTime = releaseTime;
        this.executionTime = new Random().nextInt(task.wcet - task.bcet +1) + task.bcet;
        this.priority = task.period; // Task's priority is assigned based on its period.
    }

    @Override
    public int compareTo(Job other){
        return Integer.compare(this.priority, other.priority);
    }

    @Override
    public String toString() {
        return "model.Job [model.Task " + task.name + ", Release=" + releaseTime + ", Exec=" + executionTime + "]";
    }
}