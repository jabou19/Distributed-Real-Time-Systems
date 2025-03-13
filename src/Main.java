import model.Task;
import scheduler.RTA;
import scheduler.Simulator;
import utils.TaskLoader;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Task> tasks = TaskLoader.loadTasks("files/schedulable/Full_Utilization_Unique_Periods_taskset.csv");
//        int simulationTime = 100; // Set the simulation for 100 time units.
//        Simulator simulator = new Simulator(tasks, simulationTime);
//        simulator.run();
        RTA rta = new RTA(tasks);
        rta.RTAAnalysis();
        for (Task task : tasks) {
            System.out.println(task.wcrt + task.name);
        }
    }
}
