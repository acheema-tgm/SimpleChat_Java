package acheema;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadDemo {
	
	static class Zaehlen implements Runnable{

		@Override
		public void run() {
			
			for(int i = 0; i < 20; i++) {
				System.out.println(Thread.currentThread().getName() + "zählt: " + i);
				try {
					
					Thread.sleep(600);
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		
		System.out.println("<-------Start------->");
		
		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		Thread t1 = new Thread(new Zaehlen());
		Thread t2 = new Thread(new Zaehlen());
		
		//Dem Thread wird ein name zugewiesen.
		
		t1.setName("Erster");
		t2.setName("Zweiter");
		
		//Threads starten
		//t1.start();
		//t2.start(); <- threads können so gestartet werden
		
		executor.execute(t1);  // oder über executor (runnable für executor)
		executor.execute(t2);
		executor.shutdown();
		
		try {
			
			//Warten bis die Threads sterben
			//t1.join();
			//t2.join();
			
			while(!executor.awaitTermination(1, TimeUnit.SECONDS)) {
				System.out.println("Das Zählen läuft noch");
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("<-------Ende------->");
	}
}
