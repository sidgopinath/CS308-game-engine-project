package gae.view.gameEditor;

import gae.model.Model;
import gae.model.Receiver;
import gae.view.titleScreenEditor.TitleScreenEditor;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Receiver model = new Model();
//		TitleScreenEditor e = new TitleScreenEditor();
//		primaryStage.setScene(new Scene(e.getPane()));
//		GameEditor e = new GameEditor(model);
//		primaryStage.setScene(new Scene(e.drawGameEditor()));
		primaryStage.setHeight(600);
		primaryStage.setWidth(600);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
