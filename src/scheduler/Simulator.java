package scheduler;

import model.Job;
import model.Task;

import java.util.*;

public class Simulator {
    private List<Task> tasks;
    private PriorityQueue<Job> readyQueue;
    private int simulationTime; // Simulation time for all tasks
    private Map<Task, Integer> worstCaseResponseTime;
    private Map<Task, Integer> nextReleaseTime;

    public Simulator(List<Task> tasks, int simulationTime) {
        this.tasks = tasks;
        this.simulationTime = simulationTime;
        this.readyQueue = new PriorityQueue<>(Comparator.comparingInt(j -> j.task.period));
        this.worstCaseResponseTime = new HashMap<>();
        this.nextReleaseTime = new HashMap<>();
        for (Task task : tasks) {
            nextReleaseTime.put(task, task.period);
        }
    }

    public void run() {
        int currentTime = 0;
        tasks.sort(Comparator.comparingInt(t -> t.period));
        while (currentTime <= simulationTime) {
            // Release new tasks at their respective periods
            for (Task task : tasks) {
                if (currentTime == nextReleaseTime.get(task)) {
                    Job newJob = new Job(task, currentTime);
                    readyQueue.add(newJob);
                    nextReleaseTime.put(task, currentTime + task.period);
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

        // Output results
        System.out.println("Simulation results for task ");
        for (Task task : tasks) {
            System.out.println("Task name " + task.name + " RT: " + worstCaseResponseTime.getOrDefault(task, 0) + " deadline: " + task.deadline);
        }
    }
}