/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketcommunication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
 
public class SocketClient
{
 
    private static Socket socket;
 
    public static void main(String args[])
    {
        Scanner input = new Scanner(System.in);
        try
        {
            String host = "localhost";
            //Port must be same with Server to communicate
            int port = 8080;
            //Get Adress and assign to socket
            InetAddress address = InetAddress.getByName(host);
            socket = new Socket(address, port);
 
            //Send the message to the server
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
 
            //User Input
            System.out.print("Year of Birth (YYYY) : ");
            String DOB = input.nextLine();
 
            String sendMessage = DOB + "\n";
            bw.write(sendMessage);
            bw.flush();
            System.out.println("Message sent from client >> "+sendMessage);
 
            //Get the return message from the server
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String message = br.readLine();
            System.out.println("Your age is >> " +message);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            //Closing the socket
            try
            {
                socket.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
