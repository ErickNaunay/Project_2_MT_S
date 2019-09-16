import java.time.Duration;
import java.time.Instant;

import javafx.concurrent.Task;


// developed by: baeldug
// extracted and adapted from: https://www.baeldung.com/java-merge-sort
public class MergeSort extends Task<int[]>
{

  private int[] array_to_sort;
  private int progress = 0;
  private int length;
  
  private long exec_time;
  
  public MergeSort(int[] array) {
	  
	  this.array_to_sort = array.clone();
	  this.length = array.length;
	  
  }
  
  public int[] getArray_to_sort()
  {
	  return array_to_sort;
  }

  @Override
	protected int[] call()
  {
	  Instant begin = Instant.now();
	  
	  mergeSort(array_to_sort);
	  
	  Instant end = Instant.now();
	  setExecutionTime(begin, end);
	  
	  return getArray_to_sort(); //merge-sort done

  }

  public void mergeSort(int[] data) {
      sortArray(data, 0, data.length - 1); // sort entire array
   }                                  

   // splits array, sorts subarrays and merges subarrays into sorted array
   private void sortArray(int[] data, int low, int high) {
      // test base case; size of array equals 1     
      if ((high - low) >= 1) { // if not base case
         int middle1 = (low + high) / 2; // calculate middle of array
         int middle2 = middle1 + 1; // calculate next element over     
 
         // split array in half; sort each half (recursive calls)
         sortArray(data, low, middle1); // first half of array       
         sortArray(data, middle2, high); // second half of array     
         
         updateProgress(progress,length);
         
         if (progress%1000 == 0)
        	 System.out.printf("MERGE SORT: merge arrays from points: [%d] [%d] [%d] [%d]\n", low, middle1, middle2, high);
         
         // merge two sorted arrays after split calls return
         merge(data, low, middle1, middle2, high);
        
         progress++;
      }                                            
   }                               
   
   // merge two sorted subarrays into one sorted subarray             
   private void merge(int[] data, int left, int middle1, 
      int middle2, int right) {

      int leftIndex = left; // index into left subarray              
      int rightIndex = middle2; // index into right subarray         
      int combinedIndex = left; // index into temporary working array
      int[] combined = new int[data.length]; // working array        
      
      // merge arrays until reaching end of either         
      while (leftIndex <= middle1 && rightIndex <= right) {
         // place smaller of two current elements into result  
         // and move to next space in arrays                   
         if (data[leftIndex] <= data[rightIndex]) {       
            combined[combinedIndex++] = data[leftIndex++]; 
         } 
         else {                                                 
            combined[combinedIndex++] = data[rightIndex++];
         } 
      } 
   
      // if left array is empty                                
      if (leftIndex == middle2) {                             
         // copy in rest of right array                        
         while (rightIndex <= right) {                        
            combined[combinedIndex++] = data[rightIndex++];
         } 
      } 
      else { // right array is empty                             
         // copy in rest of left array                         
         while (leftIndex <= middle1) {                        
            combined[combinedIndex++] = data[leftIndex++]; 
         } 
      } 

      // copy values back into original array
      for (int i = left; i <= right; i++) { 
         data[i] = combined[i];          
      } 
   } 
   
	private void setExecutionTime(Instant init, Instant end) {
		
		exec_time = Duration.between(init, end).toMillis();
		
	}
	
	public long getExecutionTime() {
		return exec_time;
	}
}

