package trainLine.utils;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class LogicUpdate extends TimerTask{

	Date now;

	@Override
	public void run() {
		now = new Date();
	    System.out.println("Time is : "+now); //date et l'heure d'aujourdhui
	}

	public static void main(String[] args) {
		   Timer timer = new Timer();					 // Instantiate Timer Object
		   LogicUpdate scheduler = new LogicUpdate();    // Instantiate SheduledTask class
		   timer.schedule(scheduler, 0, 1000); 			 // 1 hour between calls - create Repetitively task for every 1 secs
		   while(true){
			   System.out.println("\nexecut something  ...");
			   try {
				   Thread.sleep(2000);
			   }	catch (InterruptedException e) {
				   e.printStackTrace();
			   }
		   }
		}
}
