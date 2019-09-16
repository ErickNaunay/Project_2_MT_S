import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.paint.Color;

public class ApplicationController {
	
	//setup javafx id in the controller
	
	//label info original array
	@FXML
	private Label label_org_text;
	@FXML
	private Label label_org_array;
	
	//label for types of sorting method
	@FXML
	private Label label_1_array;
	@FXML
	private Label label_2_array;
	@FXML
	private Label label_3_array;
	
	//label of sorted arrays
	@FXML
	private Label label_sort_1;
	@FXML
	private Label label_sort_2;
	@FXML
	private Label label_sort_3;
	
	//statistics labels
	
	@FXML
	private Label label_stata_1[][] = new Label[5][2];
	
	@FXML
	private Label label_stata_1_1;
	@FXML
	private Label label_stata_1_2;
	@FXML
	private Label label_stata_1_3;
	@FXML
	private Label label_stata_1_4;
	@FXML
	private Label label_stata_1_5;
	
	@FXML
	private Label label_stata_2_1;
	@FXML
	private Label label_stata_2_2;
	@FXML
	private Label label_stata_2_3;
	@FXML
	private Label label_stata_2_4;
	@FXML
	private Label label_stata_2_5;
	
	@FXML
	private Label label_stata_3_1;
	@FXML
	private Label label_stata_3_2;
	@FXML
	private Label label_stata_3_3;
	@FXML
	private Label label_stata_3_4;
	@FXML
	private Label label_stata_3_5;
	
	@FXML
	private Label label_stata_vl_1_1;
	@FXML
	private Label label_stata_vl_1_2;
	@FXML
	private Label label_stata_vl_1_3;
	@FXML
	private Label label_stata_vl_1_4;
	@FXML
	private Label label_stata_vl_1_5;
	
	@FXML
	private Label label_stata_vl_2_1;
	@FXML
	private Label label_stata_vl_2_2;
	@FXML
	private Label label_stata_vl_2_3;
	@FXML
	private Label label_stata_vl_2_4;
	@FXML
	private Label label_stata_vl_2_5;

	@FXML
	private Label label_stata_vl_3_1;
	@FXML
	private Label label_stata_vl_3_2;
	@FXML
	private Label label_stata_vl_3_3;
	@FXML
	private Label label_stata_vl_3_4;
	@FXML
	private Label label_stata_vl_3_5;
	
	//Execution time label for stat
	@FXML
	private Label label_time_1;
	@FXML
	private Label label_time_2;
	@FXML
	private Label label_time_3;
	
	
	//progress bars components
	@FXML 
	private ProgressBar progress_sort_1;
	@FXML 
	private ProgressBar progress_sort_2;
	@FXML 
	private ProgressBar progress_sort_3;
	
	private int[] orig_array;
	
	private final int length = 100000;
	
	//constructor
	public ApplicationController() {
		
		//generate the arrays of random ints
		orig_array = ArrayGenerator.generatorArray(length).clone();
		
		//System.out.println(Arrays.toString(ArrayGenerator.getResumeArray(orig_array)));
	}
	
	@FXML
	public void initialize() {
		
		//initialize all javafx components in the fxml
		
		label_org_text.setText("Original Array:");
		label_sort_1.setText("HEAP SORT: \tExec Time:");
		label_sort_1.setTextFill(Color.web("#ff0000", 0.8));
		label_sort_2.setText("BST SORT: \tExec Time:");
		label_sort_2.setTextFill(Color.web("#ff0000", 0.8));
		label_sort_3.setText("INSERTION SORT: \tExec Time:");
		label_sort_3.setTextFill(Color.web("#ff0000", 0.8));
		
		label_stata_1_1.setText("Summatory:");
		label_stata_1_2.setText("Mean:");
		label_stata_1_3.setText("Median:");
		label_stata_1_4.setText("Standard Desviation:");
		label_stata_1_5.setText("Quartiles:");
		label_time_1.setText("Stat Exec time:");

		label_stata_2_1.setText("Summatory:");
		label_stata_2_2.setText("Mean:");
		label_stata_2_3.setText("Median:");
		label_stata_2_4.setText("Standard Desviation:");
		label_stata_2_5.setText("Quartiles:");
		label_time_2.setText("Stat Exec time:");
		
		label_stata_3_1.setText("Summatory:");
		label_stata_3_2.setText("Mean:");
		label_stata_3_3.setText("Median:");
		label_stata_3_4.setText("Standard Desviation:");
		label_stata_3_5.setText("Quartiles:");
		label_time_3.setText("Stat Exec time:");
		
		label_time_1.setTextFill(Color.web("#ff0000", 0.8));
		label_time_2.setTextFill(Color.web("#ff0000", 0.8));
		label_time_3.setTextFill(Color.web("#ff0000", 0.8));
		
		label_org_array.setText(Arrays.toString(ArrayGenerator.getResumeArray(orig_array)));
		
	}
	
	//event handler for start button
	@FXML
	public void getSortedButtonPressed(ActionEvent e) {
		
		//create thread executor pool, 3 threads created
		ExecutorService threadExecutor = Executors.newFixedThreadPool(3);
		
		//array that storages all sorted arrays by different algorithms
		int [][] arrays_sort = new int[3][];
		
		//create sorting tasks
		MergeSort sort_task_1 = new MergeSort(orig_array);
		BST sort_task_2 = new BST(orig_array);
		InsertionSort sort_task_3 = new InsertionSort(orig_array);
		
		//create stat tasks
		StatAnalysis stat_task[] = new StatAnalysis[3];
		stat_task[0] = new StatAnalysis();
		stat_task[1] = new StatAnalysis();
		stat_task[2] = new StatAnalysis();
		
		//get values and update labels while the task is running or has finished
		progress_sort_1.progressProperty().bind(sort_task_1.progressProperty());
		progress_sort_2.progressProperty().bind(sort_task_2.progressProperty());
		progress_sort_3.progressProperty().bind(sort_task_3.progressProperty());
		
		//when the sort thread is completed
		sort_task_1.setOnSucceeded(succedEvent -> {
			
			arrays_sort[0] = sort_task_1.getValue();
			
			label_1_array.setText(Arrays.toString(ArrayGenerator.getResumeArray(arrays_sort[0])));
			label_sort_1.setText(label_sort_1.getText().concat(" " + Long.toString(sort_task_1.getExecutionTime()) + " mili seconds"));
			
			//create the next thread for statistics with a sorted array
			stat_task[0].setArray(arrays_sort[0]);
			threadExecutor.execute(stat_task[0]);
			
		});
		
		sort_task_2.setOnSucceeded(succedEvent -> {
			
			arrays_sort[1] = sort_task_2.getValue();
			
			label_2_array.setText(Arrays.toString(ArrayGenerator.getResumeArray(arrays_sort[1])));
			label_sort_2.setText(label_sort_3.getText().concat(" " + Long.toString(sort_task_2.getExecutionTime()) + " mili seconds"));
			
			//create the next thread for statistics with a sorted array
			stat_task[1].setArray(arrays_sort[1]);
			threadExecutor.execute(stat_task[1]);
			
		});

		sort_task_3.setOnSucceeded(succedEvent -> {
			
			arrays_sort[2] = sort_task_3.getValue();
			
			label_3_array.setText(Arrays.toString(ArrayGenerator.getResumeArray(arrays_sort[2])));
			label_sort_3.setText(label_sort_3.getText().concat(" " + Long.toString(sort_task_3.getExecutionTime()) + " mili seconds"));
			
			//create the next thread for statistics with a sorted array
			stat_task[2].setArray(arrays_sort[2]);
			threadExecutor.execute(stat_task[2]);
			
		});
		
		//INSERT stat_task[0], [1] [2] setOnSuceeded
		
		stat_task[0].setOnSucceeded(event -> {
			
			label_stata_vl_1_1.setText(Double.toString(stat_task[0].getValue()[0]));
			label_stata_vl_1_2.setText(Double.toString(stat_task[0].getValue()[1]));
			label_stata_vl_1_3.setText(Double.toString(stat_task[0].getValue()[2]));
			label_stata_vl_1_4.setText(Double.toString(stat_task[0].getValue()[3]));
			label_stata_vl_1_5.setText("25%: "+ Double.toString(stat_task[0].getValue()[4]) +
										"\t50%: " + Double.toString(stat_task[0].getValue()[5]) +
										"\t75%: " + Double.toString(stat_task[0].getValue()[6]));
			
			label_time_1.setText(label_time_1.getText().concat(" " + Long.toString(stat_task[0].getExecutionTime()) + " mili seconds"));
			
		});
		
		stat_task[1].setOnSucceeded(event -> {
			
			label_stata_vl_2_1.setText(Double.toString(stat_task[1].getValue()[0]));
			label_stata_vl_2_2.setText(Double.toString(stat_task[1].getValue()[1]));
			label_stata_vl_2_3.setText(Double.toString(stat_task[1].getValue()[2]));
			label_stata_vl_2_4.setText(Double.toString(stat_task[1].getValue()[3]));
			
			label_stata_vl_2_5.setText("25%: "+ Double.toString(stat_task[1].getValue()[4]) +
										"\t50%: " + Double.toString(stat_task[1].getValue()[5]) +
										"\t75%: " + Double.toString(stat_task[1].getValue()[6]));
			
			label_time_2.setText(label_time_2.getText().concat(" " + Long.toString(stat_task[1].getExecutionTime()) + " mili seconds"));
			
		});
		
		stat_task[2].setOnSucceeded(event -> {
			
			label_stata_vl_3_1.setText(Double.toString(stat_task[2].getValue()[0]));
			label_stata_vl_3_2.setText(Double.toString(stat_task[2].getValue()[1]));
			label_stata_vl_3_3.setText(Double.toString(stat_task[2].getValue()[2]));
			label_stata_vl_3_4.setText(Double.toString(stat_task[2].getValue()[3]));
			
			label_stata_vl_3_5.setText("25%: "+ Double.toString(stat_task[2].getValue()[4]) +
										"\t50%: " + Double.toString(stat_task[2].getValue()[5]) +
										"\t75%: " + Double.toString(stat_task[2].getValue()[6]));
			
			label_time_3.setText(label_time_3.getText().concat(" " + Long.toString(stat_task[2].getExecutionTime()) + " mili seconds"));
			
		});
		
		//execute tasks
		threadExecutor.execute(sort_task_1);
		threadExecutor.execute(sort_task_2);
		threadExecutor.execute(sort_task_3);
		
		
		//threadExecutor.shutdown();
		
	}
	
	
	
}
