import model.Task;
import scheduler.RTA;
import scheduler.Simulator;
import utils.TaskLoader;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String fileName = "files/exercise-TC1 - Copy.csv";
        List<Task> tasks = TaskLoader.loadTasks(fileName);
        System.out.println("file name is "+fileName);
        int simulationTime = 100; // Set the simulation for 100 time units.
        Simulator simulator = new Simulator(tasks, simulationTime);
        simulator.run();
        RTA rta = new RTA(tasks);
        rta.RTAAnalysis();
    }
}
