package server;

import java.net.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;


import java.io.*; 
  
public class SocketServer 
{ 
    
    private Socket          socket   = null; 
    private ServerSocket    server   = null; 
    private DataInputStream in       =  null; 
  
    
    public SocketServer(int port) 
    { 
        try
        { 
            server = new ServerSocket(port); 
            System.out.println("Server started"); 
  
            System.out.println("Waiting for a client ..."); 
  
            socket = server.accept(); 
            
  
            in = new DataInputStream( 
                new BufferedInputStream(socket.getInputStream())); 
  
            String line = ""; 
  
            while (!line.toLowerCase().equals("exit")) 
            { 
                try
                { 
                    line = in.readUTF();
                    if(line.equals("exit"))
                    	break;
                    System.out.println(processResponse(line)); 
  
                } 
                catch(IOException i) 
                { 
                    System.out.println(i); 
                } 
            } 
            System.out.println("Closing connection"); 
  
            // close connection 
            socket.close(); 
            in.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
  
    public static void main(String args[]) 
    { 
    	SocketServer server = new SocketServer(3333); 
    } 
    

    private static String processResponse(String request){
        if(request.toLowerCase().equals("hello")){
            return "Welcome";
        }else if(request.toLowerCase().equals("time")){
            SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
            return df.format(Calendar.getInstance().getTime());
        }else if(request.toLowerCase().equals("date")){
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            return df.format(Calendar.getInstance().getTime());
        }else if(request.toLowerCase().contains("age")){
            String response = "";
            try{
                String dates[] = request.split(" ");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dob = LocalDate.parse(dates[1],dtf);
                Period period = Period.between(dob, LocalDate.now());
                response = period.getYears()+" years, "+period.getMonths()+" months,"+period.getDays()+" days";
            }catch(Exception ex){
                response = "Invalid date format. Date should be provided in dd/MM/yyyy format";
            }            
            return response;
        }
        
        return "Invalid Request";
    }
}