package application;

import javafx.concurrent.Task;

public class InsertionSort extends Task<int[]>{
	
	private int[] array_to_sort;
	
	public InsertionSort(int[] array_to_sort) {
		//super(array_to_sort);
		this.array_to_sort = array_to_sort.clone();
	}
	
	@Override
	protected int[] call() {
		
		return insertionAlgorithm();
		
	}
	
	private int[] insertionAlgorithm() {
		
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
	         
	         data[moveItem] = insert; // place inserted element    
	      }                                             
		
	      return data;
	}
}
