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
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;


/**
 *
 * @author Allen Raab
 * @author Tyler O'Connell
 */

/*  TO DO IN UI TYPE STUFF AND ALSO SOME LOGIC MIXED IN FOR SHITS AND GIGGLES

    PRE PLAY
    UI needs to open to welcome page, ask if client or server or both.
    if server/both,(on server window/part)ask for how many players and on what port to host.
        select # of players, # of dice per player, then have accept button for confirm.
        default port sets to 7777.
        
    if client, have inputs for address and port. make clear can just enter address with just colon port
        default port sets to 7777.
    
    lobby
        need chat
        need list of players
        general info
    
    PLAY
    need QUIT and CHAT

        BEGIN ROUND
        some kinda animation/feedback for rolling

        show hand
        show number of values in condensed form in corner*

        show size of hands in players display

        if first player, need VALUE SELECT and AMOUNT SELECT

        feedback on whose turn it is

        if not first, need RAISE and CALL
        
        if CALL, feedback & round end
        
        AFTER ROUND END
        show overall value & numbers listing

        begin new round
*/

public class LiarsDice extends Application {


    static int[] diceRolled;
    static int diceIndex = 0;
    static int diceTotals;
    static List<Player> list = new ArrayList<Player>();
    
    public static void main(String[] args) {
        launch(args);
        //new Server(7777,2);
        //new Client("127.0.0.1",7777);
        //new Client("127.0.0.1",7777);
    }
    
  
    public void start(Stage liarsDiceGame){
        StackPane root = new StackPane();
        liarsDiceGame.setScene(new Scene(root, 500, 500));
        liarsDiceGame.show();
        
        
        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setHgap(10);
        grid.setPadding(new Insets(5, 5, 5, 5));
        
        Label choiceLabel = new Label("Run as client or host?");
        grid.add(choiceLabel, 1, 0);
        
        Button clientButton = new Button("Client");
        grid.add(clientButton, 0,2);
        clientButton.setVisible(true);
        
        Button serverButton = new Button("Server");
        grid.add(serverButton, 3, 2);
        serverButton.setVisible(true);
        
        root.getChildren().add(grid);
        
        clientButton.setOnAction(new EventHandler<ActionEvent>(){
        public void handle (ActionEvent e) {
           
            serverButton.setVisible(false);
            clientButton.setVisible(false);
            choiceLabel.setVisible(false);
            clientStartUpDisplay(liarsDiceGame, root, grid);
        }
        });
        
        serverButton.setOnAction(new EventHandler<ActionEvent>(){
        public void handle (ActionEvent e) {
           
            serverButton.setVisible(false);
            clientButton.setVisible(false);
            choiceLabel.setVisible(false);
            serverStartUpDisplay(liarsDiceGame,root, grid);
        }
        });
        
        //display(liarsDiceGame,root);
    }
    
    public static void clientStartUpDisplay(Stage liarsDiceGame, StackPane root, GridPane grid){
        root.getChildren().remove(grid);
        Label portLabel = new Label("Port Number");
        grid.add(portLabel,0,2);
        
        final TextField portTextField = new TextField("7777");
        grid.add(portTextField,1,2);
        
        Button confirmButton = new Button("Confirm");
        confirmButton.setPrefHeight(20);
        grid.add(confirmButton, 4, 3);
        root.getChildren().add(grid);
        
        
        confirmButton.setOnAction(new EventHandler<ActionEvent>(){
        public void handle (ActionEvent e) {
            
            confirmButton.setText("Clicked");
            //
            //
            //This is where the code needs to go for client stuff
            //
            //
            //
        }
        });
        
    }
    
    public static void serverStartUpDisplay(Stage liarsDiceGame, StackPane root, GridPane grid){

        root.getChildren().remove(grid);

        Label chooseLabel = new Label("How many players?");
        grid.add(chooseLabel, 0, 0);

            ObservableList<Integer> options = 
        FXCollections.observableArrayList(
            1,
            2,
           3,
           4,
           5,
           6,
           7,
           8,
           9,
           10
        );
            
            
        final ComboBox comboBox = new ComboBox(options);
        grid.add(comboBox,1,0);
        
        Label diceCountLabel = new Label("How many dice?");
        grid.add(diceCountLabel,0,1);
        diceCountLabel.setVisible(false);

        
        final ComboBox diceComboBox = new ComboBox(options);
        grid.add(diceComboBox, 1, 1);
        diceComboBox.setVisible(false);
        
        Label portLabel = new Label("Port Number");
        grid.add(portLabel,0,2);
        portLabel.setVisible(false);
        
        final TextField portTextField = new TextField("7777");
        grid.add(portTextField,1,2);
        portTextField.setVisible(false);
        
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
                diceCountLabel.setVisible(true);
                diceComboBox.setVisible(true);
                portTextField.setVisible(true);
                portLabel.setVisible(true);
            }    
        });
        
        diceComboBox.valueProperty().addListener(new ChangeListener<Integer>() {
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
            
            diceRolled = new int[(diceComboBox.getSelectionModel().getSelectedIndex()+1) * (comboBox.getSelectionModel().getSelectedIndex()+1)];
            reroll();
            confirmButton.setText("Clicked");
            for(int i = 0; i <= comboBox.getSelectionModel().selectedIndexProperty().get(); i++){
            playerGeneration(i, diceComboBox.getSelectionModel().getSelectedIndex()+1);
            }
            
            //
            //
            //This is where the code needs to go for server stuff
            //
            //
            //
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
        
 
    public static void playerGeneration(int counter, int diceNumber){
        
        //testing out the player creation
        Player player1 = createPlayer("Player 1", diceNumber, counter);
        
        //this sets it into its designated area
        player1.setDiceNumbers(Arrays.copyOfRange(diceRolled, diceNumber*counter, player1.getNumberOfDice()+(diceNumber*counter)));
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
