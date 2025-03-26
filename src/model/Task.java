package model;

import java.util.Objects;

public class Task {
    public String name; // Task name
    public int period; // Task period
    public int bcet; // Task best-case execution time
    public int wcet; // Task worst-case execution time
    public int deadline; // Task deadline
    public double wcrt; // Task worst-case response time

    public Task(String name, int period, int bcet, int wcet, int deadline) {
        this.name = name;
        this.period = period;
        this.bcet = bcet;
        this.wcet = wcet;
        this.deadline = deadline;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Task task = (Task) obj;
        return Objects.equals(name, task.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


    @Override
    public String toString() {
        return "model.Task " + name + " [T=" + period + ", BCET=" + bcet + ", WCET=" + wcet + ",DeadLine=" + deadline + "]";
    }
}


