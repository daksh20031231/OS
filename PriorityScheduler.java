import java.util.*;
public class practice {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("ENTER NUMBER OF PROCESSES: ");
        int n  = sc.nextInt();

        int[] priority = new int[n];
        int[] bursttime = new int[n];
        int[] waitingtime = new int[n];
        int[] turnaroundtime = new int[n];

        System.out.println("ENTER PRIORITY AND BURST TIME FOR EACH PROCESS: ");
        for(int i = 0 ; i < n ; i++ ){
            System.out.print("PROCESS " + (i+1) + ": ");
            priority[i] = sc.nextInt();
            bursttime[i] = sc.nextInt();
        }


//        ARRANGING BURST TIME IN ASCENDING ORDER
        for(int i = 0; i < n-1 ; i++){
            for(int j = i+1 ; j < n ; j++){
                if(priority[i] < priority[j]){
                    int temppriority = priority[i];
                    int tempbursttime = bursttime[i];
                    priority[i] = priority[j];
                    bursttime[i] = bursttime[j];
                    priority[j] = temppriority;
                    bursttime[j] = tempbursttime;
                }
            }
        }



        waitingtime[0] = 0;
        for(int i = 1; i < n ; i++){
            waitingtime[i] = waitingtime[i-1] + bursttime[i-1];
        }

        for (int i = 0 ; i < n ; i++){
            turnaroundtime[i] = waitingtime[i] + bursttime[i];
        }

        int  tat = 0 , avgwait = 0 ;
        for (int i = 0 ; i < n ; i++){
            tat += turnaroundtime[i];
            avgwait += waitingtime[i];
        }

//        PRINTING VALUES

        System.out.println("PRIORITY SCHEDULING: ");
        System.out.println("PROCESS \t\t PRIORITY \t\t BURST TIME \t\t WAITING TIME \t\t TURNAROUND TIME");
        for (int  i = 0 ; i < n ; i++){
            System.out.println((i+1) + "\t\t" + priority[i] + "\t\t" + bursttime[i] + "\t\t" + waitingtime[i]  + "\t\t" + turnaroundtime[i]);
        }

        System.out.println("AVG WAITING TIME = " + (avgwait/n));
        System.out.println("AVG TURNAROUND TIME = " + (tat/n));

    }
}
