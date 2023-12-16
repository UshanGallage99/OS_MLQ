import java.util.List;

public class Main {
    public static void main(String[] args) {
        MLQ mlq = new MLQ(3); // Set number of ques to 3

        // Add processes with different priorities and CPU bursts
        mlq.addProcess(new Process(1, 0, 10, 2)); // High priority, arrives at start
        mlq.addProcess(new Process(2, (int) (Math.random() * 5), 5, 1)); // Medium priority, arrives with random delay within 5 seconds
        mlq.addProcess(new Process(3, 2 + (int) (Math.random() * 3), 8, 0)); // Low priority, arrives with random delay between 2 and 5 seconds


        mlq.schedule();

        List<Double> stats = mlq.getStatistics();

        //Out put for remaining current process
        System.out.println("Average Waiting Time: " + stats.get(0));
        System.out.println("Average Turnaround Time: " + stats.get(1));
    }
}