/*
 * The MIT License
 *
 * Copyright 2018 Allen Raab.
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
 */
public class Player {

    private int playerNumber;
    private int[] diceNumbers;
    private int numberOfDice;
    
    Player(){
        int playerNumber;
        int numberOfDice;
        int[] diceNumbers;
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
    
    public void setDiceNumbers(int[] diceNumb){
        diceNumbers = diceNumb;
    }
    
}
