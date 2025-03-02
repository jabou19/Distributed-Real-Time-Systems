package utils;

import model.Task;
import java.io.*;
import java.util.*;

public class TaskLoader {
    public static List<Task> loadTasks(String filename) throws IOException {
        List<Task> tasks = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        br.readLine();
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length != 6) continue;

            String name = parts[0];
            int wcet = Integer.parseInt(parts[1]);
            int bcet = Integer.parseInt(parts[2]);
            int period = Integer.parseInt(parts[3]);

            tasks.add(new Task(name, period, bcet, wcet));
        }
        br.close();
        return tasks;
    }
}
