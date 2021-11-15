package ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.CYKGUI;

public class Main extends Application{
	
	private CYKGUI cykgui;
	public static void main(String[] args)throws IOException {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("welcomeFX.fxml"));
		cykgui = new CYKGUI();
		loader.setController(cykgui);
		Parent root = loader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.sizeToScene();
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.setResizable(true);
		stage.setTitle("CYK Algortinm");
		stage.show();
		stage.sizeToScene();
		cykgui.init();
	}

}
