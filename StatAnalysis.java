import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javafx.concurrent.Task;

//class that get all statistics information about an array
public class StatAnalysis extends Task<double[]>{
	
	private int[] array_to_analyze;
	private long exec_time;
	
	@Override
	protected double[] call() {
		
		Instant begin_exec = Instant.now();
		
		double results[] = new double[7];
		
		//usage of functional programming
		results[0] = Arrays.stream(array_to_analyze).sum();
		results[1] = Arrays.stream(array_to_analyze).average().getAsDouble();
		results[2] = getMedian(array_to_analyze);
		results[3] = getSTD_D(array_to_analyze);
		
		double temp[] = getQuartiles(array_to_analyze);
		
		results[4] = temp[0];
		results[5] = temp[1];
		results[6] = temp[2];
		
		//INSERT STAT FUNCITIONS
		
		
	    Instant end_exec = Instant.now();
	    setExecutionTime(begin_exec, end_exec);
		
		return results;
	}
	
	//set sorted array
	public void setArray(int[] is) {
		// TODO Auto-generated method stub
		this.array_to_analyze = is.clone();
	}
	
	private double getMedian(int[] ord) 
	{
		
		int length = ord.length;
		int idx = length/2;
		
		//if is even 
		if (length % 2 == 0) 
		{
			//System.out.printf("%d %d", array_to_analyze[idx],array_to_analyze[idx-1]);
			return (((double)ord[idx] + (double)ord[idx-1])/2);
		}
		
		//else odd
		//System.out.println("here");
		return ord[idx];
	}
	
	
	private double getSTD_D(int[] ord)
	{
		
		//u = results[1];
		
		
		double u = Arrays.stream(ord).average().getAsDouble();
		
		//System.out.println("\nMEDIA.A --> " + u);
		
		double varianza = 
				Arrays.stream(ord)
				.mapToDouble(x -> Math.pow(x -u, 2.0))
				.sum();
		
		double std_dev = Math.sqrt(varianza/(ord.length-1));
		
		return std_dev;
		
	}
	

	private double[] getQuartiles(int[] ord)
	{
		double[] qrs = new double[3];
		
		int length = ord.length;
		int idx = length/2;
		
		qrs[1] = getMedian(ord); //Quartile 2
		
		if (length % 2 == 0) 
		{
			int[] set1 = Arrays.copyOfRange(ord, 0, idx-1);
			int[] set3 = Arrays.copyOfRange(ord, idx+1, length);
			
//			for(int i: set1)
//			{
//				System.out.print(i + " ");
//			}
//			System.out.println( " ");  //eliminar
//			for(int i: set3)
//			{
//				System.out.print(i + " ");
//			}
			
			qrs[0] = getMedian(set1); //quartile1
			qrs[2] = getMedian(set3); //quartile3
		}
		else
		{
			int[] set1 = Arrays.copyOfRange(ord, 0, idx);
			int[] set3 = Arrays.copyOfRange(ord, idx+1, length);
			
//			for(int i: set1)
//			{
//				System.out.print(i + " ");
//			}
//			System.out.println( " "); //eliminar
//			for(int i: set3)
//			{
//				System.out.print(i + " ");
//			}
			
			qrs[0] = getMedian(set1); //quartile1
			qrs[2] = getMedian(set3); //quartile3
		}
		
		
		return qrs;
		
	}
	
	private void setExecutionTime(Instant init, Instant end) {
		
		exec_time = Duration.between(init, end).toMillis();
		
	}
	
	public long getExecutionTime() {
		return exec_time;
	}

	
}
