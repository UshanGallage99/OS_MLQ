class Process {
    int id;
    int arrivalTime;
    int cpuBurst;
    int priority;
    int remainingTime;
    int waitingTime;
    int turnaroundTime;

    Process(int id, int arrivalTime, int cpuBurst, int priority) {
        this.id = id;
        this.arrivalTime = arrivalTime; //The time when the process arrived in the system
        this.cpuBurst = cpuBurst; //The total amount of CPU time the process requires to complete.
        this.priority = priority; //An integer value indicating the process's priority level (higher value = higher priority).
        this.remainingTime = cpuBurst; //The remaining CPU time needed for the process to finish execution.
        this.waitingTime = 0; //The cumulative amount of time the process has spent waiting in the queues.
        this.turnaroundTime = 0; //The total time it takes for the process to complete execution, from arrival to completion.
    }
}