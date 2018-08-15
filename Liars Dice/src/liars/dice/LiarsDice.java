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
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


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
        liarsDiceGame.setTitle("Liars' Dice");
        
        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setHgap(10);
        grid.setPadding(new Insets(5, 5, 5, 5));
        
        Label choiceLabel = new Label("Run as client or host?");
        grid.add(choiceLabel, 1, 0);
        
        Button clientButton = new Button("Client");
        grid.add(clientButton, 0,2);
        clientButton.setVisible(true);
        
        Button serverButton = new Button("Host");
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
        
        
        Label iPLabel = new Label("IP:");
        grid.add(iPLabel,0,2);
        
        final TextField iPField = new TextField("");
        grid.add(iPField,1,2);
        
        Label portLabel = new Label("Port Number");
        grid.add(portLabel,0,3);
        
        final TextField portTextField = new TextField("7777");
        grid.add(portTextField,1,3);
        
        Label nameLabel = new Label("Name:");
        grid.add(nameLabel,0,4);
        
        final TextField nameField = new TextField("I am a dummy");
        grid.add(nameField,1,4);
        
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
        
        Label nameLabel = new Label("Name:");
        grid.add(nameLabel,0,3);
        
        final TextField nameField = new TextField("I am a dummy");
        grid.add(nameField,1,3);
        
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
            playerGeneration(i, diceComboBox.getSelectionModel().getSelectedIndex()+1, nameField.getText());
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
        
 
    public static void playerGeneration(int counter, int diceNumber, String playerName){
        
        BorderPane border = new BorderPane();
        border.setPadding(new Insets(5, 5, 5, 5));
        
        
        //testing out the player creation
        Player player = createPlayer(playerName, diceNumber, counter);
        
        //this sets it into its designated area
        player.setDiceNumbers(Arrays.copyOfRange(diceRolled, diceNumber*counter, player.getNumberOfDice()+(diceNumber*counter)));
        list.add(player);
        
        Stage liarsDiceGame = new Stage();
        StackPane root = new StackPane();
        
        liarsDiceGame.setScene(new Scene(root, 500,500));
        liarsDiceGame.setX(counter*30);
        liarsDiceGame.setTitle("Liar's Dice");
        liarsDiceGame.setMinHeight(500);
        
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        
        Label turnLabel = new Label();
        turnLabel.setText(player.getPlayerName());
        
        grid.add(turnLabel, 1, 0);
        
        Label dice1 = new Label();
        dice1.setText(Arrays.toString(player.getDiceNumbers()));
        
        grid.add(dice1, 1, 1);
        
        TextField textbox = new TextField();
        
        grid.add(textbox, 1, 2);
        
        Button sendButton = new Button("Send");
        grid.add(sendButton, 2, 2);
        
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #333333;");
        hbox.setAlignment(Pos.CENTER);
        
        Button raiseButton = new Button("Raise");
        Button callButton = new Button("Call");
        
        
        
        ScrollPane textchat = new ScrollPane();
        textchat.setStyle("-fx-background-color: #ffffff;");
        textchat.setPrefHeight(100);
        textchat.setMaxHeight(100);
        
        
        
        textchat.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        textchat.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        textchat.setContent(textchat);
        
        

        
        
        Label text = new Label("");
        text.setWrapText(true);
        
        textchat.setContent(text);
        textchat.setFitToWidth(true);
        
        hbox.getChildren().add(raiseButton);
        hbox.getChildren().add(callButton);
        
        border.setCenter(grid);
        border.setTop(hbox);
        border.setBottom(textchat);
        border.bottomProperty();
        root.getChildren().add(border);
        liarsDiceGame.show();
        
        
                sendButton.setOnAction(new EventHandler<ActionEvent>(){
        public void handle (ActionEvent e) {
            
            
           sendButton.setText("Sent");
           text.setText(text.getText() + "\n" + textbox.getText());
            textbox.clear();
        }
        });
        
        }
}
