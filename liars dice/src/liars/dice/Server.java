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
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Allen Raab
 * TODO:  setup protocols for sending and receiving data
 */
public class Server {

    private int port, maxClients; //intended max connections
    private Socket[] clients; // list of connections
    private boolean isReady; //for starting prior to clients at capacity of maxClients
    private PrintWriter[] outs; //testing making individual ins and outs for every client
    private BufferedReader[] ins;
    int index;
    private Thread[] threads;
    
    public Server(int p, int mC){
        
        boolean vote = false;
        port = p;
        maxClients = mC;
        clients = new Socket[maxClients];
        outs = new PrintWriter[maxClients];
        ins = new BufferedReader[maxClients];
        threads = new Thread[maxClients];
        try{
            //creates new server socket for hosting comms at the specified port
            ServerSocket servSock = new ServerSocket(port);
            
            //waits until full clients, plan on allowing early start via isReady
            for(int i=0; i<maxClients; i++){
                System.out.println("Waiting for clients to connect...");
                
                index = i;
                clients[i] = servSock.accept();
                outs[i] =
                    new PrintWriter(clients[i].getOutputStream(), true);
                ins[i] = new BufferedReader(
                    new InputStreamReader(clients[i].getInputStream()));
                
                threads[i] = new Thread(() -> {
                    boolean isConnected = true;
                    outs[index].println("setPlayerNumber"+index);
                    while(isConnected) {
                        try {
                            if(!Server.processInput(ins[index].readLine()))
                                isConnected = false;
                        } catch (Exception ex) {
                            outs[index].println("Oh shit.");
                            System.out.println("Oh shit.");
                            if(ex instanceof IOException){
                                try {
                                    ins[index].close();
                                    outs[index].close();
                                    clients[index].close();
                                    
                                } catch (IOException ex1) {
                                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex1);
                                }
                                
                                isConnected = false;
                            }
                        }
                    }
                });
                
                threads[i].start();

                //prints welcome message to client (hopefully, havent tested yet)
                outs[i].println("Welcome to the server!");
                
                System.out.println("Client has connected from: "+clients[i].toString());
                
                if(isReady || vote)
                    break;
                }
            
            System.out.println("Setup Complete! Moving to gameplay!");
        }
        catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        //insert game logic here v
        
        
        
    }
    
    public static boolean processInput(String in){
        if(in.contains("vote")){
            
        }
        return false;
    }
    
    
    public String sendCommand(String command, int clientNum){
        outs[clientNum].println(command);
        return command;
    }
    
    
    public void setMaxClients(int m){
        maxClients = m;
    }
    public int getMaxClients(){
        return maxClients;
    }
    public void setPort(int p){
        port = p;
        System.out.println("lol no can do, bud");
    }
    public int getPort(){
        return port;
    }
    
}