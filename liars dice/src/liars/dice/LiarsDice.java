/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liars.dice;

/**
 *
 * @author allen
 */
public class LiarsDice {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //need to run them in separate threads
        Server serv = new Server(7777, 2);
        Client client = new Client("10.0.0.1",7777);
    }
}
