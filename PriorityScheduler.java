import java.util.*;

public class PriorityScheduler {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();

        int[] priority = new int[n];
        int[] burstTime = new int[n];
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        // Input priorities and burst times for each process
        for (int i = 0; i < n; i++) {
            System.out.printf("Enter priority and burst time for process %d: ", i + 1);
            priority[i] = sc.nextInt();
            burstTime[i] = sc.nextInt();
        }

        // Sort processes based on priority (higher priority comes first)
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (priority[i] < priority[j]) {
                    int tempPriority = priority[i];
                    int tempBurstTime = burstTime[i];
                    priority[i] = priority[j];
                    burstTime[i] = burstTime[j];
                    priority[j] = tempPriority;
                    burstTime[j] = tempBurstTime;
                }
            }
        }

        // Calculate waiting time and turnaround time for each process
        for (int i = 0; i < n; i++) {
            waitingTime[i] = totalWaitingTime;
            turnaroundTime[i] = waitingTime[i] + burstTime[i];
            totalWaitingTime += burstTime[i];
            totalTurnaroundTime += turnaroundTime[i];
        }

        // Calculate average waiting time and turnaround time
        double avgWaitingTime = (double) totalWaitingTime / n;
        double avgTurnaroundTime = (double) totalTurnaroundTime / n;

        // Print results
        System.out.println("Process\tPriority\tBurst Time\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < n; i++) {
            System.out.printf("%d\t\t%d\t\t%d\t\t%d\t\t%d\n", i + 1, priority[i], burstTime[i], waitingTime[i], turnaroundTime[i]);
        }
        System.out.printf("Average waiting time: %.2f\n", avgWaitingTime);
        System.out.printf("Average turnaround time: %.2f\n", avgTurnaroundTime);
    }
}
