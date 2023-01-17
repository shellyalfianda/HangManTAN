package hangmantan;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class MainMenu {

    @FXML
    private GridPane mainPane;
    
	public void initialize(){
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/bg.gif")));
    	mainPane.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
	}
	 
    public void changeScene() throws IOException {
        FXMLLoader parentLoader = new FXMLLoader(Objects.requireNonNull(MainMenu.class.getResource("game.fxml")));
        goToMainGame(parentLoader.load());
        Main.gameController = parentLoader.getController();
    }

    public void goToMainGame(Parent parent) throws IOException {
        Stage window = Main.stage;
        Main.stringStage = "GAME";
        window.setTitle("HangMan-Tan");
        window.setScene(new Scene(parent, 800, 650));
        Main.gameScene = window.getScene();
        window.show();
    }


}
