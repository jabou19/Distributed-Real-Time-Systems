package scheduler;

import model.Job;
import model.Task;

import java.util.*;

public class Simulator {
    private List<Task> tasks;
    private PriorityQueue<Job> readyQueue;
    private int simulationTime;
    private Map<Task, Integer> worstCaseResponseTime;

    public Simulator(List<Task> tasks, int simulationTime) {
        this.tasks = tasks;
        this.simulationTime = simulationTime;
        this.readyQueue = new PriorityQueue<>();
        this.worstCaseResponseTime = new HashMap<>();
    }

    public void run() {
        int currentTime = 0;
        while (currentTime <= simulationTime) {
            // Release a new task
            for (Task task : tasks) {
                if (currentTime % task.period == 0) {
                    Job newJob = new Job(task, currentTime);
                    readyQueue.add(newJob);
                }
            }

            // Choose the highest-priority task from all tasks
            if (!readyQueue.isEmpty()) {
                Job currentJob = readyQueue.poll();
                int execTime = Math.min(currentJob.executionTime, 1); // Executing one time unit.
                currentTime += execTime;
                currentJob.executionTime -= execTime;

                // Computing WCRT
                if (currentJob.executionTime <= 0) {
                    int responseTime = currentTime - currentJob.releaseTime;
                    worstCaseResponseTime.put(currentJob.task,
                            Math.max(worstCaseResponseTime.getOrDefault(currentJob.task, 0), responseTime));
                } else {
                    readyQueue.add(currentJob); // If tasks are unfinished, then continue executing
                }
            } else {
                currentTime++; // Spare time
            }
        }

        // 输出结果
        for (Task task : tasks) {
            System.out.println("Task " + task.name + " WCRT: " + worstCaseResponseTime.getOrDefault(task, 0));
        }
    }
}
