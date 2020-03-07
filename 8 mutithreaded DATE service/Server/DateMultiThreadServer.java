import java.io.*;
import java.net.*;
import java.util.Calendar;
import java.util.Date;
import java.util.*;
import java.lang.String;
import java.lang.Integer;
import javax.swing.*; 

public class DateMultiThreadServer {
        
    public static ServerSocket c2;
    
    public DateMultiThreadServer(){
    	
    }
    
    
    public static void main(String[] args) {
    	
    try{
         
       System.out.println("Server Started....");
         
        int kase=1;
    	
    	ServerSocket c2=new ServerSocket(4444);
    	
    	while(true)
    	{
    	  Socket c1=c2.accept();	
          
          System.out.printf("Client %d accepted by Server.\n",kase);
          
          MyServer th=new MyServer(c1,kase++);
          
          th.start();		
    	}	    	
    }
    catch(Exception e)
    {
    	System.out.println("Exception caught in server");
    }		
    	
    		    
	       
    
    } // end main 
} // end class DateMultiThreadServer


class MyServer extends Thread 
{
  
    Socket C;
	int clientNumber; 	
	
	MyServer(Socket c,int CN)
	{
		C=c;
		clientNumber=CN;
		
	}
	
	public void run()
	{		
	  try
	  {
		   
	 
	  PrintWriter p=new PrintWriter(new OutputStreamWriter(C.getOutputStream()),true);
	  BufferedReader br=new BufferedReader(new InputStreamReader(C.getInputStream()));
	  
	  
	  String command;
	  
	  
	  Date D=new Date();
	  
	 while(true)
	 {
	 	 command=br.readLine();
	 	 if (command.equalsIgnoreCase("exit"))
	 	 {
	 	 	System.out.printf("Client %d is disConnected.\n",clientNumber);
	 	 	try{
	 	 		
	 	 	 p.println("Disconnected from Server");  	
	 	 	}
	 	 	catch(Exception e)
	 	 	{
	 	 	  System.out.println("Exception in disconnecting.");	
	 	 	}	
	 	 	break;
	 	 }
	 	 	
	 	 	
	 	 System.out.printf("MSG from client %d is : %s\n",clientNumber,command);
	      	 	 
	 	 
	 	 int d,m,y;
	 	 
	 	 d=D.getDay();
	 	 m=D.getMonth();
	     y=D.getYear(); 
	 	  
	     if(command.equalsIgnoreCase("date"))
	     {		
	       D=new Date();
	       p.println(D);
	      // System.out.println("complete  the request of the client.");
	     }
	    else if(command.equalsIgnoreCase("getday"))
	     { 
	       String day;	
	       		
	       
	       
            if     (d==0) {day="Sunday";    p.println(day);} 
            else if(d==1) {day="Monday";    p.println(day);} 
            else if(d==2) {day="Tuesday";   p.println(day);} 
            else if(d==3) {day="Wednesday"; p.println(day);} 
            else if(d==4) {day="Thursday";  p.println(day);} 
            else if(d==5) {day="Friday";    p.println(day);} 
            else if(d==6) {day="Satureday"; p.println(day);} 
            
           
	       	
	     }
	    else if(command.equalsIgnoreCase("getMonth"))
	     {
	     	
          String mth;  
	       	
	     	 
	       
                 if(m==0)  {mth="January";   p.println(mth);}  
            else if(m==1)  {mth="February";  p.println(mth);} 
            else if(m==2)  {mth="Merch";     p.println(mth);} 
            else if(m==3)  {mth="April";     p.println(mth);} 
            else if(m==4)  {mth="May";       p.println(mth);} 
            else if(m==5)  {mth="June";      p.println(mth);} 
            else if(m==6)  {mth="July";      p.println(mth);} 
            else if(m==7)  {mth="August";    p.println(mth);} 
            else if(m==8)  {mth="September"; p.println(mth);} 
            else if(m==9)  {mth="October";   p.println(mth);} 
            else if(m==10) {mth="November";  p.println(mth);} 
            else if(m==11) {mth="December";  p.println(mth);} 
             
           	
	       	
	     }
	    else if(command.equalsIgnoreCase("getYear"))
	     {
	       String yr;
	       yr=String.valueOf(y+1900);	
	     	p.println(yr);
	       	
	     }  	 
	    else
	     {
	       p.println("Unknown command!");
	       System.out.printf("complete  the request of the client %d.\n",clientNumber);
	     }	
      }
      
       p.close();
	   br.close();
	   C.close();  
      
		
        } //end try 
		    
		catch(Exception e){
	    
	    }
		
	}// end run	
	
} // end MyServer class

