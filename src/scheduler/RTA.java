package scheduler;

import model.Job;
import model.Task;

import java.util.*;

public class RTA {
    public List<Task> tasks;

    public RTA(List<Task> tasks) {
        this.tasks = tasks;
    }

    public boolean RTAAnalysis() {
        // sort the tasks by priority(the shorter the period, the higher the priority)
        tasks.sort((t1, t2) -> Integer.compare(t1.period, t2.period));
//        // Analyze each task
//        for (int i = 0; i < tasks.size(); i++) {
//            Task task = tasks.get(i);
//            int I = 0;
//            int iteration = 0;
//
//            while (true) {
//                int R = I + task.wcet;
//
//                if (R > task.deadline) {
//                    System.out.println("UNSCHEDULABLE");
//                    return false;
//                }
//
//                int newI = 0;
//                for (int j = 0; j < i; j++) { // only higher-priority tasks
//                    Task hpTask = tasks.get(j);
//                    newI += Math.ceil((double) R / hpTask.period) * hpTask.wcet;
//                }
//
//                if (newI == I) {
//                    task.wcrt = R;
//                    break;
//                }
//
//                I = newI;
//
//                iteration++;
//                if (iteration > 1000) {
//                    System.out.println("Exceeded max iterations for task: " + task.name);
//                    return false;
//                }
//            }
//
////            System.out.println("Task " + task.name + " WCRT: " + task.wcrt);
//        }
//
//        System.out.println("SCHEDULABLE");
//        return true;
//    }

        // analysis every tasks
        for (Task task : tasks) {
            int I = 0;  // initial the I

            // perform iterative calculation until the response time no longer changes
            while (true) {
                int R = I + task.wcet;  // current task's response time R = I + Ci

                // check if it exceeds the deadline
                if (R > task.deadline) {
                    System.out.println("UNSCHEDULABLE");
                    return false;
                }

                // calculate new I
                int newI = 0;
                for (int i = 0; i < tasks.indexOf(task); i++) {
                    Task higherPriorityTask = tasks.get(i);
                    newI += Math.ceil((double) R / higherPriorityTask.period) * higherPriorityTask.wcet;
                }

                // if I not change, stop iterative
                if (newI == I) {
                    task.wcrt = R;  // record worse response time
                    break;
                }
                I = newI;  // update I
            }
        }
        System.out.println("SCHEDULABLE");
        return true;
    }

}