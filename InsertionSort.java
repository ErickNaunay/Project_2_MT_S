package application;

import java.time.Duration;
import java.time.Instant;

import javafx.concurrent.Task;

public class InsertionSort extends Task<int[]>{
	
	private int[] array_to_sort;
	private long exec_time;
	
	public InsertionSort(int[] array_to_sort) {
		this.array_to_sort = array_to_sort.clone();
	}
	
	@Override
	protected int[] call() {
		
		return insertionAlgorithm();
		
	}
	
	private int[] insertionAlgorithm() {
		
		Instant begin_exec = Instant.now();
		
		int[] data = array_to_sort.clone();
		
		// loop over data.length - 1 elements           
	      for (int next = 1; next < data.length; next++) {
	    	  
	         int insert = data[next]; // value to insert      
	         int moveItem = next; // location to place element

	         // search for place to put current element         
	         while (moveItem > 0 && data[moveItem - 1] > insert) {
	            // shift element right one slot
	        	 
	        	 data[moveItem] = data[moveItem - 1];

	            moveItem--;                                       
	         }                                                    
	         
	         //update the progressbar value
	         updateProgress(next, data.length);
	         
	         //print debuggin data from the algorithm
	         if (next%1000 == 0)
	        	 System.out.printf("INSERTION SORT: insert [%d] in position %d\n", insert, moveItem);
	         
	         data[moveItem] = insert; // place inserted element    
	      }                                             
	      
	      Instant end_exec = Instant.now();
	      setExecutionTime(begin_exec, end_exec);
	      
	      return data;
	}
	
	private void setExecutionTime(Instant init, Instant end) {
		
		exec_time = Duration.between(init, end).toMillis();
		
	}
	
	public long getExecutionTime() {
		return exec_time;
	}
}
