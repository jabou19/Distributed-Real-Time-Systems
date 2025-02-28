import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class VerySimpleSimulator {
    public static List<Task> tasks = new ArrayList<Task>();
    public static List<Job> jobQueue  = new ArrayList<Job>();
    public static int simulationTime = 100; // Number of cycles
    public static void main(String[] args) {
        readTasksFromFile("./files/exercise-TC1.csv");
        File file = new File("../files/exercise-TC1.csv");
        System.out.println(file.getAbsolutePath());
        simulate();
        printResults();
    }

    private static void readTasksFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 3) continue;

                String name = data[0].trim();
                int period = Integer.parseInt(data[1].trim());
                int executionTime = Integer.parseInt(data[2].trim());

                tasks.add(new Task(name, period, executionTime));
            }

            // Sort tasks by priority (Rate Monotonic Scheduling: lower period = higher priority)
            tasks.sort(Comparator.comparingInt(t -> t.period));

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static void simulate() {
        int currentTime = 0;

        while (currentTime <= simulationTime) {
            // Step 6: Get ready jobs
            for (Task task : tasks) {
                if (currentTime % task.period == 0) {
                    jobQueue.add(new Job(task, currentTime));
                }
            }

            // Step 7: Get the highest-priority job
            jobQueue.sort(Comparator.comparingInt(j -> j.task.priority));
            Job currentJob = jobQueue.isEmpty() ? null : jobQueue.get(0);

            if (currentJob != null) {
                // Step 9-10: Advance time (could be 1 unit)
                int delta = AdvanceTime();
                currentTime += delta;
                currentJob.remainingTime -= delta;

                // Step 12-15: Check if job is completed
                if (currentJob.remainingTime <= 0) {
                    currentJob.responseTime = currentTime - currentJob.releaseTime;
                    currentJob.task.worstCaseResponseTime = Math.max(
                            currentJob.task.worstCaseResponseTime,
                            currentJob.responseTime
                    );
                    jobQueue.remove(currentJob);
                }
            } else {
                // Step 18-19: No job is ready, advance time
                currentTime += AdvanceTime();
            }
        }
    }

    private static int AdvanceTime() {
        return 1; // Advances time by 1 unit
    }

    private static void printResults() {
        System.out.println("Task | Worst-Case Response Time (WCRT)");
        for (Task task : tasks) {
            System.out.println(task.name + " | " + task.worstCaseResponseTime);
        }
    }
}