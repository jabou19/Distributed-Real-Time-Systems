package utils;

import model.Task;
import java.io.*;
import java.util.*;

public class TaskLoader {
    // loadTasks method reads the tasks from the file and returns a list of tasks
    public static List<Task> loadTasks(String filename) throws IOException {
        List<Task> tasks = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        br.readLine();
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length != 6) continue;

            String name = parts[0];
            int wcet = Integer.parseInt(parts[2]);
            int bcet = Integer.parseInt(parts[1]);
            int period = Integer.parseInt(parts[3]);
            int deadline = Integer.parseInt(parts[4]);

            tasks.add(new Task(name, period, bcet, wcet,deadline));
        }
        br.close();
        return tasks;
    }
}
