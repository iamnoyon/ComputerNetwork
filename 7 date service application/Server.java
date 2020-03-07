import java.io.*;
import java.net.*;
import java.util.Calendar;
import java.util.Date;
import java.util.*;
import java.lang.String;
import java.lang.Integer; 

public class Server
{
	public static void main (String[] args) 
	{
	try
	  {
	  
	  ServerSocket c2=new ServerSocket(4444);
	  
	  System.out.println("Waiting for Client request...");
	  Socket c1=c2.accept();
	  
	  System.out.println("Request accepted by Server.");	
	  
	  PrintWriter p=new PrintWriter(new OutputStreamWriter(c1.getOutputStream()),true);
	  BufferedReader br=new BufferedReader(new InputStreamReader(c1.getInputStream()));
	  
	  
	  String command;
	  
	  
	  Date D=new Date();
	  
	 while(true)
	 {
	 	 command=br.readLine();
	 	 System.out.println("MSG from client is : "+command);
	      
	 	 
	 	 
	 	 
	 	 int d,m,y;
	 	 
	 	 d=D.getDay();
	 	 m=D.getMonth();
	     y=D.getYear(); 
	 	  
	     if (command.equalsIgnoreCase("exit"))
	 	 {
	 	 	System.out.println("Client is disConnected.");
	 	 	try{
	 	 		
	 	 	 p.println("Disconnected from Server");  	
	 	 	}
	 	 	catch(Exception e)
	 	 	{
	 	 	  System.out.println("Exception in disconnecting.");	
	 	 	}	
	 	 	break;
	 	 }
	 	 
	 	 
	     if(command.equalsIgnoreCase("date"))
	     {		
	       D=new Date();
	       p.println(D);
	      // System.out.println("complete  the request of the client.");
	     }
	    else if(command.equalsIgnoreCase("getday"))
	     { 
	       String day=new String();	
	       		
	       
	       
            if     (d==0) {day="Sunday";    } 
            else if(d==1) {day="Monday";    } 
            else if(d==2) {day="Tuesday";   } 
            else if(d==3) {day="Wednesday"; } 
            else if(d==4) {day="Thursday";  } 
            else if(d==5) {day="Friday";    } 
            else if(d==6) {day="Satureday"; } 
            
	       	p.println(day);
	       	
	     }
	    else if(command.equalsIgnoreCase("getMonth"))
	     {
	     	
          String mth=new String();  
	       	
	     	 
	       
                 if(m==0)  {mth="January";   }  
            else if(m==1)  {mth="February";  } 
            else if(m==2)  {mth="Merch";     } 
            else if(m==3)  {mth="April";     } 
            else if(m==4)  {mth="May";       } 
            else if(m==5)  {mth="June";      } 
            else if(m==6)  {mth="July";      } 
            else if(m==7)  {mth="August";    } 
            else if(m==8)  {mth="September"; } 
            else if(m==9)  {mth="October";   } 
            else if(m==10) {mth="November";  } 
            else if(m==11) {mth="December";  } 
             
           	p.println(mth);
	       	
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
	       System.out.println("complete  the request of the client.");
	     }	
      }
      
     //  p.close();
	 //  br.close();
	 //  c1.close();  
      
	  } 
    catch(Exception e)
      {
       System.out.println("Exception caught");	
      }
	  
	 	
	 }	 
	  
 }
