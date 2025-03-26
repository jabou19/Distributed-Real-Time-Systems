import model.Task;
import scheduler.RTA;
import scheduler.Simulator;
import utils.TaskLoader;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Task> tasks = TaskLoader.loadTasks("files/exercise-TC3.csv");
        int simulationTime = 10000; // Set the simulation for 100 time units.
        Simulator simulator = new Simulator(tasks, simulationTime);
        simulator.run();
        RTA rta = new RTA(tasks);

        System.out.println("\n===(RTA) Worst Case Response Times ===");
        rta.RTAAnalysis();
        for (Task task : tasks) {
            System.out.println("Task " + task.name + " WCRT: "+  task.wcrt);
        }
    }
}
