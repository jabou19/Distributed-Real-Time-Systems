package scheduler;

import model.Job;
import model.Task;

import java.util.*;
public class Simulator {
    private List<Task> tasks; // List of tasks
    private List<Job> readyQueue; // Ready queue
    private int simulationTime;
    private Map<Task, Integer> worstCaseResponseTime;

    public Simulator(List<Task> tasks, int simulationTime) {
        this.tasks = tasks;
        this.simulationTime = simulationTime;
        this.readyQueue = new ArrayList<>();
        this.worstCaseResponseTime = new HashMap<>();
    }

    public void run() {
        int currentTime = 0;

        while (currentTime <= simulationTime || !readyQueue.isEmpty()) {
            // Release new jobs
            for (Task task : tasks) {
                if (currentTime % task.period == 0) {
                    Job newJob = new Job(task, currentTime);
                    readyQueue.add(newJob);
              //      System.out.println("Time " + currentTime + ": Released " + newJob);
                }
            }

            // Select highest-priority job (shortest period)
            Job currentJob = null;
            if (!readyQueue.isEmpty()) {
                currentJob = readyQueue.stream()
                        .min(Comparator.comparingInt(j -> j.task.period))
                        .orElse(null);
                readyQueue.remove(currentJob);

                // Execute 1 time unit
                currentJob.executionTime -= 1;
                //System.out.println("Time " + currentTime + ": Executing " + currentJob);
                currentTime++;

                if (currentJob.executionTime <= 0) {
                    int responseTime = currentTime - currentJob.releaseTime;
                    int prevWCRT = worstCaseResponseTime.getOrDefault(currentJob.task, 0);
                    worstCaseResponseTime.put(currentJob.task, Math.max(prevWCRT, responseTime));
                   // System.out.println("Time " + currentTime + ": Finished " + currentJob + ", Response Time = " + responseTime);
                } else {
                    readyQueue.add(currentJob); // Not finished yet
                }
            } else {
                // CPU idle
               // System.out.println("Time " + currentTime + ": Idle");
                currentTime++;
            }
        }

        // Print WCRT
        System.out.println("\n=== (VSS) Response Times ===");
        List<Map.Entry<Task, Integer>> sortedWcrtList = new ArrayList<>(worstCaseResponseTime.entrySet());
        sortedWcrtList.sort(Comparator.comparingInt(Map.Entry::getValue));

        for (Map.Entry<Task, Integer> entry : sortedWcrtList) {
            Task task = entry.getKey();
            int wcrt = entry.getValue();
            System.out.println("Task " + task.name + " RT: " + wcrt);
        }
    }
}

