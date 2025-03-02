import model.Task;
import scheduler.Simulator;
import utils.TaskLoader;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Task> tasks = TaskLoader.loadTasks("files/exercise-TC2.csv");
        int simulationTime = 100; // Set the simulation for 100 time units.

        Simulator simulator = new Simulator(tasks, simulationTime);
        simulator.run();
    }
}
