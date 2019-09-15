package application;

import java.util.Arrays;

import javafx.concurrent.Task;

//class that get all statistics information about an array
public class StatAnalysis extends Task<double[]>{
	
	private int[] array_to_analyze;
	
	@Override
	protected double[] call() {
		
		double results[] = new double[7];
		
		//usage of functional programming
		results[0] = Arrays.stream(array_to_analyze).sum();
		results[1] = Arrays.stream(array_to_analyze).average().getAsDouble();
		results[2] = getMedian();
		
		//INSERT STAT FUNCITIONS
		
		return results;
	}
	
	private double getMedian() {
		
		int length = array_to_analyze.length;
		int idx = length/2;
		
		//if is even 
		if (length % 2 == 0) {
			//System.out.printf("%d %d", array_to_analyze[idx],array_to_analyze[idx-1]);
			return ((double)array_to_analyze[idx] + (double)array_to_analyze[idx-1])/2;
		}
		
		//else odd
		//System.out.println("here");
		return array_to_analyze[idx-1];
	}
	
	//set sorted array
	public void setArray(int[] is) {
		// TODO Auto-generated method stub
		this.array_to_analyze = is.clone();
	}
	
	

}
