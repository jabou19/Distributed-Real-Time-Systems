package scheduler;

import model.Job;
import model.Task;

import java.util.*;

//public class Simulator {
//    private List<Task> tasks;
//    private PriorityQueue<Job> readyQueue;
//    private int simulationTime;
//    private Map<Task, Integer> worstCaseResponseTime;
//
//    public Simulator(List<Task> tasks, int simulationTime) {
//        this.tasks = tasks;
//        this.simulationTime = simulationTime;
//        this.readyQueue = new PriorityQueue<>(Comparator.comparingInt(j -> j.task.period));
//        this.worstCaseResponseTime = new HashMap<>();
//    }
//
//    public void run() {
//        int currentTime = 0;
//        tasks.sort((t1, t2) -> Integer.compare(t1.period, t2.period));
//
//        while (currentTime <= simulationTime) {
//            // Release a new task
//            for (Task task : tasks) {
//                if (currentTime % task.period == 0) {
//                    Job newJob = new Job(task, currentTime);
//                    readyQueue.add(newJob);
//                }
//            }
//
//            // Choose the highest-priority task from all tasks
//            if (!readyQueue.isEmpty()) {
//                Job currentJob = readyQueue.poll();
//                int execTime = Math.min(currentJob.executionTime, 1); // Executing one time unit.
//                currentTime += execTime;
//                currentJob.executionTime -= execTime;
//
//                // Computing WCRT
//                if (currentJob.executionTime <= 0) {
//                    int responseTime = currentTime - currentJob.releaseTime;
//                    worstCaseResponseTime.put(currentJob.task,
//                            Math.max(worstCaseResponseTime.getOrDefault(currentJob.task, 0), responseTime));
//                } else {
//                    readyQueue.add(currentJob); // If tasks are unfinished, then continue executing
//                }
//            } else {
//                currentTime++; // Spare time
//            }
//        }
//
//        // output results
//        for (Task task : tasks) {
//            System.out.println("Task " + task.name + " WCRT: " + worstCaseResponseTime.getOrDefault(task, 0));
//        }
//    }
//}

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

