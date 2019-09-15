package application;

import java.security.SecureRandom;
import java.util.stream.IntStream;

//Class that generates and manipulates an array of n length with random ints
public class ArrayGenerator {
	
	static  int[] generatorArray(int lenght) {
		
		SecureRandom rand = new SecureRandom();
		
		int array_random[] = rand.ints(lenght, 0, 100).toArray();
		
		return array_random;
		
	}
	
	//get a subarray of array_nums iterating by a pattern
	static int[] getResumeArray(int[] array_nums) {
		
		int lenght_sub_array = 20;
		int index = array_nums.length/lenght_sub_array;
		
		return IntStream.range(0, lenght_sub_array).map(x -> array_nums[x*index]).toArray();
		
	}
	
}
