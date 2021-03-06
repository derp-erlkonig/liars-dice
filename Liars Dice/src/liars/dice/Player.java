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

/**
 *
 * @author Allen Raab
 * @author Tyler O'Connell
 * 
 * TODO: link processCommands to all other commands
 */
public class Player {

    private int playerNumber;
    private int[] diceNumbers;
    private int numberOfDice;
    private String playerName;
    
    Player(){
    }
    
    Player(int pN, int sD, String nm){
        playerNumber = pN;
        numberOfDice = sD;
        diceNumbers = new int[numberOfDice];
        playerName = nm;
    }
    //biggest waste of a method
    public boolean processCommands(String c){
        if(c.contains("setDiceNumbers")){
            String[] parser = c.substring(c.indexOf("\\[")+1,c.indexOf("\\]")).split("\\,");
            
            int[] n = new int[parser.length];
            for(int i=0;i<parser.length;i++){
                n[i] = Integer.parseInt(parser[i]);
            }
            if(setDiceNumbers(n))
                return true;
        }
        else if(c.contains("setPlayerName")){
            String[] parser = c.split("\\ ");
            String nam = "";
            for(int i=1;i<parser.length;i++){
                nam += parser[i];
            }
            setPlayerName(nam);
            return true;
        }
        else if(c.contains("setPlayerNumber")){
            String[] parser = c.split("\\ ");
            setPlayerNumber(Integer.parseInt(parser[1]));
            return true;
        }
        
        return false;
    }
    
    public int getPlayerNumber(){
        return playerNumber;
    }
    
    public void setNumberOfDice(int numb){
        numberOfDice = numb;
    }
    
    public void setPlayerNumber(int numb){
        playerNumber = numb;
    }
    
    public int getNumberOfDice(){
        return numberOfDice;
    }
    
    public int[] getDiceNumbers(){
        return diceNumbers;
    }
    
    public boolean setDiceNumbers(int[] diceNumb){
        if(diceNumb.length == diceNumbers.length){
            diceNumbers = diceNumb;
            return true;
        }
        else{
            System.err.println("Hand Size Mismatch");
            return false;
        }
    }
    
    public String getPlayerName(){
        return playerName;
    }
    
    public void setPlayerName(String name){
        playerName = name;
    }
}