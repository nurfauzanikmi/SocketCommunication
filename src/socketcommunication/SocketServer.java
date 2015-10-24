/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketcommunication;

/**
 *
 * @author Fauzan
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
 
public class SocketServer
{
 
    private static Socket socket;
 
    public static void main(String[] args)
    {
        try
        {
            //Initialize local port
            int port = 8080;
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server Started and listening to the port "+port);
 
            //Server is running always. This is done using this while(true) loop
            while(true)
            {
                //Reading the message from the client
                socket = serverSocket.accept();
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String DOB = br.readLine();
                System.out.println("Message received from client >> "+DOB);
 
                //Get age of user and return to client
                String returnMessage;
                try
                {
                    int numberInIntFormat = Integer.parseInt(DOB);
                    //get current year
                    Calendar now = Calendar.getInstance();
                    int currYear = now.get(Calendar.YEAR); 
                    
                    //minus the year of birth to get age
                    int returnValue = currYear - numberInIntFormat;
                    
                    returnMessage = String.valueOf(returnValue) + "\n";
                }
                catch(NumberFormatException e)
                {
                    //Input was not a number. Sending proper message back to client.
                    returnMessage = "Please send a proper number\n";
                }

                //Sending the response back to the client.
                OutputStream os = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);
                bw.write(returnMessage);
                System.out.println("Message sent to the client >> "+returnMessage);
                bw.flush();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                socket.close();
            }
            catch(Exception e){}
        }
    }
}
