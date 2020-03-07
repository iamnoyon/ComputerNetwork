
package javaapplication2;
import java.io.*;
import java.net.*;
import java.util.*;

public class MultiEchoServer{
	
    private static ServerSocket serverSocket;
    //  private static  int PORT ;
    private static final int PORT=7788 ;
    
public static void main(String[] args) throws IOException {
    
    try {
          // int value;
          //    value= (int) (Math.random()*10000);
          //PORT=value;
        serverSocket = new ServerSocket(PORT);
        }
    catch (IOException ioEx){
        System.out.println("\nUnable to set up port!");
        System.exit(1);
        }
      
      
    do
      {
          Socket c_1 = serverSocket.accept();
          System.out.println("Client_1 is connected.");
          Socket c_2 = serverSocket.accept();
          System.out.println("Client_2 is connected.");System.out.println ();
          ClientHandler handler = new ClientHandler(c_1,c_2);
          handler.start();
         
      }while (true);
      
   }// ... ...
}//... ...


class ClientHandler extends Thread {
	
	

      private Socket client1,client2;
      private ObjectInputStream input1,input2;
      private ObjectOutputStream output1,output2;
      
      
      
public ClientHandler(Socket socket1,Socket socket2){
	
      client1 = socket1;
      client2 = socket2;
      try
         {
         output1 = new ObjectOutputStream( client1.getOutputStream() );     
         output1.flush(); 
         input1 = new ObjectInputStream( client1.getInputStream() );
         
         output2 = new ObjectOutputStream( client2.getOutputStream() );     
         output2.flush(); 
         input2 = new ObjectInputStream( client2.getInputStream() );
         }
      catch(IOException ioEx)
         {
         ioEx.printStackTrace();
         }
      }
      
      
public void run()
     {
     datapass th_1 = new datapass(input1,output2);
     datapass th_2 = new datapass(input2,output1);
     th_1.start();
     th_2.start();
     }// ... ... 
}




class datapass extends Thread {

      private ObjectInputStream input;
      private ObjectOutputStream output;
    
public datapass(ObjectInputStream i,ObjectOutputStream o){
	
      input = i;
      output = o;
      }
      
public void run()
     {
     String received="";;
     do
     {
        try{
           received =(String) input.readObject();
           output.writeObject(received);
           }
        catch(IOException e)  {e.printStackTrace();}
        catch(ClassNotFoundException k)  {k.printStackTrace();};
        
      }while (!received.equals("QUIT"));
      
      
     /* try
        {
        if (client!=null){
            System.out.println("Closing down connection...");
            client.close();
            }
        }
      catch(IOException ioEx){
          System.out.println("Unable to disconnect!");
          }*/
     }// ... ... 
}