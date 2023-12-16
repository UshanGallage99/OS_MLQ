import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

class MLQ {
    private final int numQueues;
    private final List<Deque<Process>> queues;
    private Process currentProcess;
    private int time;
    private int starvationThreshold; // Adjustable parameter

    MLQ(int numQueues) {
        this.numQueues = numQueues;
        this.queues = new ArrayList<>();
        for (int i = 0; i < numQueues; i++) {
            this.queues.add(new LinkedList<>());
        }
        this.starvationThreshold = 1; // Adjust this value
    }

    void addProcess(Process process) {
        queues.get(process.priority).addLast(process);
    }

    void schedule() {
        while (true) {
            // Find highest non-empty queue
            int activeQueue = -1;
            for (int i = 0; i < numQueues; i++) {
                if (!queues.get(i).isEmpty()) {
                    activeQueue = i;
                    break;
                }
            }

            if (activeQueue == -1) {
                break; // No processes left
            }

            // Starvation prevention
            if (currentProcess != null && currentProcess.waitingTime > starvationThreshold) {
                queues.get(activeQueue - 1).addLast(currentProcess);
                currentProcess.waitingTime = 0;
            }

            // Get process from active queue
            currentProcess = queues.get(activeQueue).removeFirst();

            // Update waiting time for other processes
            for (int i = activeQueue + 1; i < numQueues; i++) {
                for (Process p : queues.get(i)) {
                    p.waitingTime += currentProcess.cpuBurst;
                }
            }

            // Execute process
            currentProcess.remainingTime--;
            time++;

            // Check if process is finished
            if (currentProcess.remainingTime == 0) {
                currentProcess.turnaroundTime = time - currentProcess.arrivalTime;
                currentProcess = null;
            }
        }
    }

    List<Double> getStatistics() {
        double avgWaitingTime = 0;
        double avgTurnaroundTime = 0;
        for (Deque<Process> queue : queues) {
            for (Process p : queue) {
                avgWaitingTime += p.waitingTime;
                avgTurnaroundTime += p.turnaroundTime;
            }
        }
        return List.of(avgWaitingTime / queues.get(0).size(), avgTurnaroundTime / queues.get(0).size());
    }
}