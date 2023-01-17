package hangmantan;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {

    public static Stage stage;
    public static String stringStage;

    public static Scene gameScene;
    public static Game gameController;

    public static Defeat defeatController;
    public static Scene defeatScene;
    public static Scene victoryScene;


    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader root = new FXMLLoader(getClass().getResource("mainmenu.fxml"));
        FXMLLoader game = new FXMLLoader(getClass().getResource("game.fxml"));
        FXMLLoader defeat = new FXMLLoader(getClass().getResource("defeat.fxml"));
        FXMLLoader victory = new FXMLLoader(getClass().getResource("victory.fxml"));

        primaryStage.setTitle("HangMan-Tan");
        primaryStage.setScene(new Scene(root.load(), 800, 650));
        primaryStage.show();

        stage = primaryStage;
        stringStage = "MAIN";

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {

            if(key.getCode()== KeyCode.ENTER && stringStage.equals("FINISH")) {
                Stage window = Main.stage;
                Main.stringStage = "GAME";
                window.setTitle("HangMan-Tan");
                window.setScene(Main.gameScene);
                window.show();
            }else if(key.getCode()== KeyCode.ENTER && !stringStage.equals("GAME")) {
                try {
                    MainMenu mainMenu = root.getController();
                    mainMenu.goToMainGame(game.load());
                    gameController = game.getController();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else if(key.getCode().isLetterKey() && stringStage.equals("GAME")) {
                gameController.checkInput(key.getCode().getChar().toUpperCase());
            }
        });

        Parent defeatParent = defeat.load();

        defeatController = defeat.getController();
        defeatScene = new Scene(defeatParent, 800, 650);
        victoryScene = new Scene(victory.load(), 800, 650);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
