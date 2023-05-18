import java.util.*;

public class FCFS{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();

        int[] burstTime = new int[n];
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];

        System.out.println("\nEnter the burst time for each process: ");
        for(int i = 0; i < n; i++) {
            System.out.print("Process " + (i+1) + ": ");
            burstTime[i] = sc.nextInt();
        }

        waitingTime[0] = 0;
        for(int i = 1; i < n; i++) {
            waitingTime[i] = waitingTime[i-1] + burstTime[i-1];
        }

        for(int i = 0; i < n; i++) {
            turnaroundTime[i] = waitingTime[i] + burstTime[i];
        }

        float totalWaitingTime = 0, totalTurnaroundTime = 0;
        for(int i = 0; i < n; i++) {
            totalWaitingTime += waitingTime[i];
            totalTurnaroundTime += turnaroundTime[i];
        }

        System.out.println("\nFCFS Scheduling: ");
        System.out.println("Process\tBurst Time\tWaiting Time\tTurnaround Time");
        for(int i = 0; i < n; i++) {
            System.out.println((i+1) + "\t" + burstTime[i] + "\t\t" + waitingTime[i] + "\t\t" + turnaroundTime[i]);
        }

        System.out.println("\nAverage Waiting Time: " + (totalWaitingTime/n));
        System.out.println("Average Turnaround Time: " + (totalTurnaroundTime/n));

        sc.close();
    }
}
