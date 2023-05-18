import java.util.*;

class RoundRobinScheduling {
    int pid; // Process ID
    int bt; // Burst Time
    int wt; // Waiting Time
    int tat; // Turnaround Time

    public Process(int pid, int bt) {
        this.pid = pid;
        this.bt = bt;
        this.wt = 0;
        this.tat = 0;
    }
}

public class RoundRobinScheduling_22BTRCC011 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();

        System.out.print("Enter the time quantum: ");
        int quantum = scanner.nextInt();

        List<Process> processes = new ArrayList<>();

        // Input burst time for each process
        for (int i = 0; i < n; i++) {
            System.out.print("Enter burst time for process " + (i + 1) + ": ");
            int burstTime = scanner.nextInt();
            processes.add(new Process(i + 1, burstTime));
        }

        // Calculate waiting time and turnaround time
        calculateTimes(processes, quantum);

        // Display the results
        displayResults(processes);

        scanner.close();
    }

    public static void calculateTimes(List<Process> processes, int quantum) {
        int n = processes.size();
        int[] remainingBurstTime = new int[n];

        // Initialize remaining burst time of processes
        for (int i = 0; i < n; i++) {
            remainingBurstTime[i] = processes.get(i).bt;
        }

        int currentTime = 0; // Current time

        // Run the round robin scheduling algorithm
        while (true) {
            boolean allProcessesCompleted = true;

            for (int i = 0; i < n; i++) {
                if (remainingBurstTime[i] > 0) {
                    allProcessesCompleted = false;

                    if (remainingBurstTime[i] > quantum) {
                        currentTime += quantum;
                        remainingBurstTime[i] -= quantum;
                    } else {
                        currentTime += remainingBurstTime[i];
                        processes.get(i).wt = currentTime - processes.get(i).bt;
                        remainingBurstTime[i] = 0;
                    }
                }
            }

            if (allProcessesCompleted) {
                break;
            }
        }

        // Calculate turnaround time for each process
        for (int i = 0; i < n; i++) {
            processes.get(i).tat = processes.get(i).wt + processes.get(i).bt;
        }
    }

    public static void displayResults(List<Process> processes) {
        System.out.println("\nProcess\tBurst Time\tWaiting Time\tTurnaround Time");

        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        for (Process process : processes) {
            System.out.println(process.pid + "\t\t" + process.bt + "\t\t" + process.wt + "\t\t" + process.tat);
            totalWaitingTime += process.wt;
            totalTurnaroundTime += process.tat;
        }

        double averageWaitingTime = (double) totalWaitingTime / processes.size();
        double averageTurnaroundTime = (double) totalTurnaroundTime / processes.size();

        System.out.println("\nAverage Waiting Time: " + averageWaitingTime);
        System.out.println("Average Turnaround Time: " + averageTurnaroundTime);
    }
}
