// import classes & package
import java.net.Socket;
import java.net.ServerSocket;
import java.io.*;
import java.util.Scanner;
import java.lang.String;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.PrintWriter;



public class EchoServer {
	

    public static void main(String[] args) throws Exception 
    {
       
     DataInputStream inData=new DataInputStream(System.in);
       
        // create socket
        int port = 4444;
        PrintWriter sout = null;
	   
	    
        BufferedReader sin = null;

        ServerSocket serverSocket = new ServerSocket(port);  // Creating srever socket
        System.out.println("Started server on port " + port);
        
        Socket clientSocket = serverSocket.accept(); // accepting request from server
        System.out.println("Connection ACCEPTED "); // print in server console that client request is accepted
   
 // Creating BufferedReader for input from client         
        sin  = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 
 // Creating PrintWriter for output to client         
        sout = new PrintWriter(clientSocket.getOutputStream(),true);
        
      
     while(true)
      {
     	// Creating FileReader & BufferReader
        String file;
            file=sin.readLine();
            
        System.out.println("read from Client "+ file);
          
          if(file.equalsIgnoreCase("exit"))
          { 
          	 System.out.println("Disconnected Client");
             break;  
          }
         
          	          	  
        FileWriter outputFileReader = new FileWriter(file); 
        PrintWriter  outputStream  = new PrintWriter(outputFileReader);       
        
       
        System.out.println("#File receiving started# "+file); // Printing in server console "#File Open#"    
        String inLine=null;  // String for reading line from file
     
        while(true)
        {
         
         inLine=sin.readLine(); 
         outputStream.println(inLine);
          
         if(inLine.equals("EOF"))   // checking end of file 
         {
           System.out.println("#File receiving done # "+file); 
          break;
         }
         
         System.out.println(inLine);  // printing in server console
        }	
       
         outputStream.close(); 

      }	   
    } // end main method
} // end class EchoServer

