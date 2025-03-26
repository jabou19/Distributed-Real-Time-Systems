package scheduler;

import model.Job;
import model.Task;

import java.util.*;
// RTA is a class that performs Response Time Analysis on a set of tasks.
public class RTA {
    public List<Task> tasks;

    public RTA(List<Task> tasks){
        this.tasks = tasks;
    }

    public boolean RTAAnalysis(){
        // sort the tasks by priority(the shorter the period, the higher the priority)
        tasks.sort((t1, t2) -> Integer.compare(t1.period, t2.period));

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
                int newI = 0;
                /*for (int i = 0; i < tasks.indexOf(task); i++) {
                    Task higherPriorityTask = tasks.get(i);
                    newI += Math.ceil((double) R / higherPriorityTask.period) * higherPriorityTask.wcet;
                }*/
                for (Task higherPriorityTask : tasks) {
                    if (higherPriorityTask.period < task.period) {
                        newI += Math.ceil((double) R / higherPriorityTask.period) * higherPriorityTask.wcet;
                    }
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
        System.out.println("RTA results for task ");
        for (Task task : tasks) {
            System.out.println("Task name: " + task.name + " WCRT: " + task.wcrt + " deadline: " + task.deadline);
        }
        return true;
    }
}
