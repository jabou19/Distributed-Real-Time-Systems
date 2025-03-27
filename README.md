# Distributed-Real-Time-Systems (DRTS) – VSS & RTA

This Java project is part of the **02225 Distributed Real-Time Systems (DRTS)** course exercise. It includes two core components:

-  **Very Simple Simulator (VSS)**: A simple fixed-priority preemptive scheduler simulator.
-  **Response-Time Analysis (RTA)**: A schedulability analysis tool based on response-time bounds.

---

## 📥 Input Format

### 🧪 Task Set Format (CSV)

The files in the `Exercise/Content` tab are **test cases** provided as a starting point for the exercise.

They are in **CSV (Comma-Separated Values)** format and describe periodic task parameters:

- `Task`: Task name
- `BCET`: Best-case execution time
- `WCET`: Worst-case execution time
- `Period`: Task period
- `Deadline`: Task deadline (usually equal to the period)
- `Priority`: Task priority (assigned by **Rate Monotonic scheduling**, i.e., lower period → higher priority)

### 📝 Example (`tasks.csv`)
```csv
Task,BCET,WCET,Period,Deadline,Priority
T1,1,3,10,10,1
T2,2,5,20,20,2
T3,1,4,50,50,3
```
These examples illustrate one possible format. You are free to use different formats and create your own test cases.

---

##  Features

### Very Simple Simulator (VSS)
- Implements **fixed-priority preemptive scheduling**.
- Generates random job execution times in the range **[BCET, WCET]**.
- Outputs **observed Worst-Case Response Time (WCRT)** for each task based on the simulation.

### Response-Time Analysis (RTA)
- Calculates **WCRT analytically** using interference-based iterative computation.
- Checks **deadline constraints** for all tasks.
- Declares the task set as either **SCHEDULABLE** or **UNSCHEDULABLE**.

---

### 📌 Notes
- Execution time distribution: Jobs in the simulator get a random execution time from [BCET, WCET], uniformly.

- Priority: Tasks follow fixed priorities (e.g., RM) as provided in the input.

- Worst-Case vs. Observed:

  - RTA provides analytical upper bounds.

  - VSS shows observed WCRTs which may vary across runs.

- You can customize how AdvanceTime() is implemented (step-by-step or jump to next job release).


