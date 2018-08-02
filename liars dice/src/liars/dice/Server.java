/*
 * The MIT License
 *
 * Copyright 2018 Allen Raab.
 * Copyright 2018 Shawn McGee.
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
 * @author Shawn McGee
 * TODO:  setup protocols for sending and receiving data
 */
public class Server {

    private int port, maxClients; //intended max connections
    private boolean vote; //for starting prior to clients at capacity of maxClients
    Connection[] connections;
    static int readies = 0;
    private Thread[] threads;
    
    public Server(int p, int mC){
        vote = false;
        port = p;
        maxClients = mC;
        readies = 0;
        threads = new Thread[maxClients];
        connections = new Connection[maxClients];
    }
    
    public void start(){
        new Thread(() -> {
            try{
            //creates new server socket for hosting comms at the specified port
            ServerSocket servSock = new ServerSocket(port);
            
            //waits until full clients, plan on allowing early start via isReady
            for(int i=0; i<maxClients; i++){
                System.out.println("Waiting for clients to connect...");
                connections[i] = new Connection(servSock.accept(), i, this).start();
                connections[i].send("Welcome to the server!");
                System.out.println("Client has connected from: "+connections[i].socket.toString());
                if(readies >= (connections.length/2))
                    vote = true;
                if(vote)
                    break;
            }
            System.out.println("Setup Complete! Moving to gameplay!");
        }
        catch (IOException ex) {
            System.err.print(ex);
        }}).start();
    }
    
    public static boolean processInput(String in, int pl){
        if(in.contains("voteStart")){
            readies++;
            return true;
        }else if(in.contains("")){
            
        }
        return false;
    }
    
    
    public String sendCommand(String command, int clientNum){
        connections[clientNum].send(command);
        return command;
    } 
    public void ready(){
        vote = true;
    }
    public void setMaxClients(int m){
        maxClients = m;
    }
    public int getMaxClients(){
        return maxClients;
    }
    public void setPort(int p){
        port = p;
    }
    public int getPort(){
        return port;
    }
    


    private static class Connection implements Runnable{
        PrintWriter out;
        BufferedReader in;
        Socket socket;
        Server serv;
        boolean isConnected;
        Thread me;
        int playerNumber;
        
        public Connection(Socket sock, int pN, Server s) {
            try {
                socket = sock;
                out = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                playerNumber = pN;
                serv = s;
                isConnected = true;
            } catch (IOException ex) {
                System.err.print(ex);
            }
        }
        public Connection start(){
            if(me == null){
                me = new Thread(this);
                me.start();
            }
            return this;
        }
        public void stop(){
            try {
                isConnected = false;
                me.join();
            } catch (InterruptedException ex) {
               System.err.print(ex);
            }
        }
        public void send(String cmd){
            out.println(cmd);
        }
        public void run() {
            try{
                while(isConnected){
                    serv.processInput(in.readLine(), playerNumber);
                }
            }catch(Exception e){
                stop();
            }
        }
    }
}