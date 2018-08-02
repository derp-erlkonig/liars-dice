/*
 * The MIT License
 *
 * Copyright 2018 Allen Raab.
 * Copyright 2018 Tyler O'Connell.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package liars.dice;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.stage.Stage;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;


/**
 *
 * @author Allen Raab
 * @author Tyler O'Connell
 */
public class LiarsDice extends Application {


    static int[] diceRolled = new int[18];
    static int diceIndex = 0;
    static int diceTotals;
    static List<Player> list = new ArrayList<Player>();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    
  
    public void start(Stage liarsDiceGame){
        reroll();
        StackPane root = new StackPane();
        liarsDiceGame.setScene(new Scene(root, 500, 500));
        liarsDiceGame.show();
        numberOfPlayers(liarsDiceGame,root);
        //display(liarsDiceGame,root);
    }
    
    public static void numberOfPlayers(Stage liarsDiceGame, StackPane root){
       GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setHgap(10);
        grid.setPadding(new Insets(5, 5, 5, 5));

        Label chooseLabel = new Label("How many players?");
        grid.add(chooseLabel, 0, 0);

            ObservableList<Integer> options = 
        FXCollections.observableArrayList(
            1,
            2,
           3
        );
        final ComboBox comboBox = new ComboBox(options);


        grid.add(comboBox, 3, 0);

        Button confirmButton = new Button("Confirm");
        confirmButton.setPrefHeight(20);
        confirmButton.setVisible(false);
        grid.add(confirmButton, 4, 3);
        root.getChildren().add(grid);

        comboBox.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue ov, Integer t, Integer t1) {
              System.out.println(ov);
                System.out.println(t);
                System.out.println(t1);
                confirmButton.setVisible(true);
            }    
        });

        confirmButton.setOnAction(new EventHandler<ActionEvent>(){
        public void handle (ActionEvent e) {
            confirmButton.setText("Clicked");
            for(int i = 0; i <= comboBox.getSelectionModel().selectedIndexProperty().get(); i++){
            display(i);
            }
            liarsDiceGame.hide();
        }
        });
    
    }
    
    //Creates the randomly generated set
    public static void reroll(){
    for(int i = 0; i<diceRolled.length; i++){
    diceRolled[i] = (int) (Math.random() * 6 + 1);
    //System.out.println(diceRolled[i]);
        }
    }

    //creates a new Player
    static public Player createPlayer(String Name, int startingDice, int playerNumber){
        Player newPlayer = new Player(playerNumber,startingDice,Name);
        return newPlayer;
    }
        
 
    public static void display(int counter){
        
        //testing out the player creation
        Player player1 = createPlayer("Player 1", 6, 0);
        
        //this sets it into its designated area
        player1.setDiceNumbers(Arrays.copyOfRange(diceRolled, 6*counter, player1.getNumberOfDice()+(6*counter)));
        list.add(player1);
        
        Stage liarsDiceGame = new Stage();
        StackPane root = new StackPane();
        
        liarsDiceGame.setScene(new Scene(root, 500,500));
        liarsDiceGame.setX(counter*30);
        liarsDiceGame.setTitle("Liar's Dice");
        Label dice1 = new Label();
        dice1.setText(Arrays.toString(player1.getDiceNumbers()));
        
        root.getChildren().add(dice1);
        liarsDiceGame.show();
        
        
        }
}
