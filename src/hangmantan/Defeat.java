package hangmantan;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class Defeat {
    @FXML
    Label correctAnswer;

    @FXML
    GridPane mainPane;
    
	public void initialize(){
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/bg2.png")));
    	mainPane.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
	}
    
    public void changeScene(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(Defeat.class.getResource("game.fxml")));
        goToMainGame(parent,event);
    }

    public void goToMainGame(Parent parent, Event event) throws IOException {
        Main.gameController.initialize();
        Stage window = Main.stage;
        Main.stringStage = "GAME";
        window.setTitle("HangMan-Tan");
        window.setScene(Main.gameScene);
        window.show();
    }

    public void printMessage(String correctAnswer) {
        ;
        this.correctAnswer.setText(String.format("Jawaban yang benar adalah %s. Klik enter untuk main lagi", correctAnswer));
    }
}
