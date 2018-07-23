/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liars.dice;
import java.util.Arrays;
/**
 *
 * @author allen
 */
public class LiarsDice {


    static int[] diceRolled = new int[12];
    static int diceIndex = 0;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //need to run them in separate threads
        //Server serv = new Server(7777, 2);
        //Client client = new Client("10.0.0.1",7777);
        initializeGame();
    }
    
    public static void initializeGame(){
        
        
        //Creates the randomly generated set
        //will become its own method
        for(int i = 0; i<diceRolled.length; i++){
        diceRolled[i] = (int) (Math.random() * 6 + 1);
        //System.out.println(diceRolled[i]);
    }
        
        //testing out the player creation
        Player player1 = new Player();
        player1.setPlayerNumber(1);
        player1.setNumberOfDice(6);
        
        //this sets it into its designated area
        player1.setDiceNumbers(Arrays.copyOfRange(diceRolled, 0, player1.getNumberOfDice()));
        int[] temp = player1.getDiceNumbers();
        System.out.println("Player 1 Rolls");
        
        //print method for testing currently
        for(int i = 0; i < player1.getNumberOfDice(); i++){
            System.out.println(temp[i]);
        }
        
        //shifts the read point for the next player
        diceIndex += player1.getNumberOfDice();

        //testing out player 2
        Player player2 = new Player();
        player2.setPlayerNumber(2);
        player2.setDiceNumbers(Arrays.copyOfRange(diceRolled, diceIndex, diceRolled.length));
        player2.setNumberOfDice(6);
        
        //printing out the products
        temp = player2.getDiceNumbers();
        System.out.println("Player 2 Rolls");
        for(int i = 0; i < player2.getNumberOfDice(); i++){
            System.out.println(temp[i]);
        }
        
    }
}
