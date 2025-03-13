package model;

public class Task {
    public String name;
    public int period;
    public int bcet;
    public int wcet;
    public int deadline;
    public double wcrt;

    public Task(String name, int period, int bcet, int wcet, int deadline) {
        this.name = name;
        this.period = period;
        this.bcet = bcet;
        this.wcet = wcet;
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "model.Task " + name + " [T=" + period + ", BCET=" + bcet + ", WCET=" + wcet + ",DeadLine=" + deadline + "]";
    }
}


