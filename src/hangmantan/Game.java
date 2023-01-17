package hangmantan;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class Game {
    @FXML
    ImageView img;
    @FXML
    AnchorPane mainPane;
    @FXML
    Label hint;
    @FXML
    Label incorrectLetters;
    @FXML
    Label guessedWordLabel;

    private static final Map<String, String> WORDS = Map.of(
            "TENANG", "Ikan bernapas di air dengan...",
            "GNADU", "Ada udang di balik…",
            "BENANG", "Ada guling ada...",
            "JANGAN", "Bekas pacar disebut...",
            "BENAR", "Makan dianjurkan pakai tangan...",
            "BANYAK", "Temenya Nobita...",
            "MATANG", "Nasi yang enak buat sarapan, biasanya nasi?",
            "USUS",  "Jauh di mata, tapi dekat di hati, apakah itu?",
            "TAKUT", "Lawannya kuat?",
            "SALAH", "Koran disebut surat ?"       
    );
    private static String correctWord;
    private static char[] guessedWord;
    private static Set<Character> guessedLetters;
    private static List<String> wrongLetters;

    public void initialize(){
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/bg.gif")));
    	mainPane.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, null)));
        clearData();
        randomizeWord();
        printGuessedWord();
        setImage();
        printWrongLetters();
    }

    private void clearData() {
        correctWord = "";
        guessedWord = new char[0];
        guessedLetters = new HashSet<>();
        wrongLetters = new ArrayList<>();
    }

    private void randomizeWord() {
        int random = new Random().nextInt(WORDS.size());
        correctWord = WORDS.keySet().toArray()[random].toString();
        guessedWord = new char[correctWord.length()];
        hint.setText(WORDS.get(correctWord));
    }

    public void printWrongLetters(){
        incorrectLetters.setText(String.join(" ", wrongLetters));
    }

    public void printGuessedWord() {
        String print = "";

        for (int i = 0; i < guessedWord.length; i++) {
            char newLetter = '_';
            if (Character.isLetter(guessedWord[i])) {
                newLetter = guessedWord[i];
            }

            print = print + "  " + newLetter;
        }

        if (!print.contains("_")) {
            victory();
        }
        guessedWordLabel.setText(print);
        AnchorPane.setLeftAnchor(guessedWordLabel, 0.0);
        AnchorPane.setRightAnchor(guessedWordLabel, 0.0);
        guessedWordLabel.setAlignment(Pos.CENTER);
        guessedWordLabel.setTextAlignment(TextAlignment.CENTER);
    }

    public void checkInput(String pressedButton){
     
        char pressed = pressedButton.charAt(0);
        if (guessedLetters.contains(pressed)) {
            return;
        }
        
        guessedLetters.add(pressed);
        boolean correctInput = false;
        for (int i = 0; i < correctWord.length(); i++) {
            if (correctWord.charAt(i) == pressed) {
                correctInput = true;
                guessedWord[i] = pressed;
            }
        }

        if (!correctInput) {
            if (wrongLetters.size() >= 6) {
                defeat();
                return;
            }
            wrongLetters.add(pressedButton);
            setImage();
            printWrongLetters();
        }

        printGuessedWord();
    }


    public void setImage(){
        String imageFile = String.format("images/%d.png", wrongLetters.size() + 1);
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imageFile)));
        this.img.setImage(image);
    }


    public void defeat()  {
        Main.defeatController.printMessage(correctWord);
        Main.stringStage = "FINISH";
        Main.stage.setScene(Main.defeatScene);
    }

    public void victory()  {
        Main.defeatController.printMessage(correctWord);
        Main.stringStage = "FINISH";
        Main.stage.setScene(Main.victoryScene);
    }
}
