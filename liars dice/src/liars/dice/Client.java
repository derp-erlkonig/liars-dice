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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Allen Raab
 * TODO: command interpreter
 */
public class Client {
    private Socket me;
    private String address;
    private InetAddress adrs;
    private int port;
    private BufferedReader in;
    private Player plr;
    private boolean isConnected;
    
    //*instantiation of Client automatically attempts connection
    public Client(String a){
        plr = new Player();
        String[] parser = a.split("\\:");
        port = Integer.parseInt(parser[2]);
        byte[] BAddress = new byte[4];
        String[] str = parser[0].split("\\.");
        for(int i = 0; i<4; i++){
            BAddress[i] = (byte) Integer.parseInt(str[i]);
        }
        System.out.println("Attempting to reach address: "+ a);
        try {
            adrs = InetAddress.getByAddress(BAddress);
            me = new Socket(adrs, port);
            in =new BufferedReader(
                    new InputStreamReader(me.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(me != null){
            isConnected = true;
            System.out.println("Successfully connected!");
        }
        
        while(isConnected){
            try {
                if(!plr.processCommands(in.readLine()))
                    System.out.println("Error processing command");
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                isConnected = false;
            }
        }
    }
    public Client(String a, int p){
        plr = new Player();
        address = a;
        port = p;
        
        byte[] BAddress = new byte[4];
        String[] str = address.split("\\.");
        for(int i = 0; i<4; i++){
            BAddress[i] = (byte) Integer.parseInt(str[i]);
        }
        System.out.println("Attempting to reach address: "+address+":"+port);
        try {
            adrs = InetAddress.getByAddress(BAddress);
            me = new Socket(adrs, port);
            in =new BufferedReader(
                    new InputStreamReader(me.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(me != null){
            isConnected = true;
            System.out.println("Successfully connected!");
        }
        
        while(isConnected){
            try {
                if(!plr.processCommands(in.readLine()))
                    System.out.println("Error processing command");
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                isConnected = false;
            }
        }
    }
    
}