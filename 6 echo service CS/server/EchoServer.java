import java.net.Socket;
import java.net.ServerSocket;
import java.io.*;
import java.util.Scanner;
import java.lang.String;


public class EchoServer {
	

    public static void main(String[] args) throws Exception 
    {
       
     DataInputStream inData=new DataInputStream(System.in);
       
        // create socket
        int port = 4444;
        PrintWriter sout = null;
	   
	    //Scanner sin=null;
        BufferedReader sin = null;

        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Started server on port " + port);
        
        Socket clientSocket = serverSocket.accept();
        System.out.println("Connection ACCEPTED ");
        
        sin  = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 
        sout = new PrintWriter(clientSocket.getOutputStream(),true);
        
        
        while(true)
        {
         String str; 	
         str=sin.readLine();
         
         System.out.println("Client: "+str);
         
         
         if(str.equals("exit"))
         {
           System.out.println(" Disconnected server.");
          break;
         }
         System.out.println("S:"+str);
      //   str=inData.readLine();             
         sout.println(str);
        }	
        
       
    }
}

