package application;
	
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			
			//set fxml location
			String path_to_fxml = "main_frame.fxml";
			URL url_fxml = getClass().getResource(path_to_fxml);
			
			//load fxml file into the scene
			Parent root = FXMLLoader.load(url_fxml);
			Scene scene = new Scene(root);
			
			primaryStage.setScene(scene);
			primaryStage.show();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
