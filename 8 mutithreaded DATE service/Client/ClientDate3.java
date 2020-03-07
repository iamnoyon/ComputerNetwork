import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Scanner;
import java.lang.String;
import java.io.IOException;

public class ClientDate3 extends JFrame
{
	public JButton getDay,getMonth,getYear,Clear,Connect,date,disconnect;
	public JTextArea dateTextArea; 
	public JLabel Date1 =new JLabel("                                                         get date and time                   "); 
	public Scanner input;
    public DataInputStream inData; 
    
    // create socket
    public PrintWriter sout ;	
    public BufferedReader sin ;
    public  Socket  echoSocket; 
    public int port;
	
	ClientDate3(){
	 
         	 
	 //GUI code	
  	  super.setTitle("Client3 ");
  	  super.setSize(450,190);
  	  super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  	  super.setResizable(false);
  	  
  	  setLayout(new FlowLayout());
      
     // Button Part
       // Creat Button 	
       //Font     f = new Font("Courier", Font.BOLD, 10);
  	    getDay = new JButton("Day");
  	    getDay.setBackground(Color.ORANGE);
  	   getMonth= new JButton("Month");
  	   getMonth.setBackground(Color.orange);
  	    getYear= new JButton("Year");
  	  getYear.setBackground(Color.ORANGE);  
  	     Clear = new JButton("Clear");	
  	     	Clear.setBackground(Color.ORANGE);
  	   Connect = new JButton("Connect");
  	  Connect.setBackground(Color.ORANGE);
  	     date  = new JButton("Date");
  	    date.setBackground(Color.ORANGE);
  	disconnect = new JButton("DisConnect");
  	disconnect.setBackground(Color.ORANGE);
  	   // ButtonHandler declearation	
  	
  	   ConnectButtonHandler Connecthandler=new ConnectButtonHandler();
  	   Connect.addActionListener(Connecthandler);
  	   
  	   disconnectButtonHandler disconnecthandler=new disconnectButtonHandler();
  	   disconnect.addActionListener(disconnecthandler);
  	
  	   ClearButtonHandler Clearhandler=new ClearButtonHandler();
  	   Clear.addActionListener(Clearhandler);
  	
  	   getDayButtonHandler getDayhandler=new getDayButtonHandler();
  	   getDay.addActionListener(getDayhandler);
  	   
  	   getMonthButtonHandler getMonthhandler=new getMonthButtonHandler();
  	   getMonth.addActionListener(getMonthhandler);
  	   
  	   getYearButtonHandler getYearhandler=new getYearButtonHandler();
  	   getYear.addActionListener(getYearhandler);
  	
  	   dateButtonHandler datehandler= new dateButtonHandler();
  	   date.addActionListener(datehandler);
  	    
  	    
       //Container declearation	
  	   java.awt.Container C=getContentPane();
  
       //Creating Lebel for Client & Server	
  	   javax.swing.JLabel Labeldate=new JLabel();
  	     
  	  	
  	     Labeldate.setText("Date:");
  	       
  	
  	     dateTextArea = new JTextArea(5,20);
  	
       // Creating Panel	
  	    JPanel P=new JPanel();
  	   
  	   
  	    add(Connect);
  	    add(disconnect);
        add(getDay);   // add getDate Button
  	    add(getMonth);  // add getMonth Button
  	    add(getYear);   // add getYear Button
  	    
        P.add(Labeldate);   
  	    P.add(dateTextArea);
  	    add(Date1);
  	     add(date);
  	   
  	     add(Clear);
  	    C.add(P);
  	    
  	    
  	    
  	    super.show(true);
   	
	}
	
	// Connect buttonhandler Class	
  public class ConnectButtonHandler implements ActionListener
  {
  	public void actionPerformed(ActionEvent event)
  	{
  	   input=new Scanner(System.in);
       inData=new DataInputStream(System.in);
            	
          
      //  System.out.println("Started client to port " + port);
        // open up IO streams
        try
        {    	
    	 int port = 4444;
  	     Socket echoSocket= new Socket("127.0.0.1",port);
  	     
  	     sin  = new BufferedReader(new InputStreamReader(echoSocket.getInputStream())); 
         sout = new PrintWriter(echoSocket.getOutputStream(),true);  
        	       	  	
         }
        catch(IOException ioEx)
         {
         	String res="Exception Occured in Connection";
           JOptionPane.showMessageDialog(ClientDate3.this,String.format("%s",res));
         }		
        
  	     	
  	}
  }
  
   // Clear buttonhandler Class  
   public class ClearButtonHandler implements ActionListener
   {
  	  public void actionPerformed(ActionEvent event)
  	  {
  	    dateTextArea.setText(" ");
  	   // sout.println("exit");	
  	  }
   }
   
   // disconnet buttonhandler Class  
   public class disconnectButtonHandler implements ActionListener
   {
  	  public void actionPerformed(ActionEvent event)
  	  {
  	    sout.println("exit");
  	    try{
  	    
  	    String str=sin.readLine();
  	    if(str.equals("Disconnected from Server"))
  	     {
  	      JOptionPane.showMessageDialog(ClientDate3.this,String.format("%s",str)); 	
  	     }	
  	    }
  	    catch(Exception e)
  	    {
  	      System.out.println("Exception in disconnection.");	
  	    }	
  	    
  	  }
   }
   
   // date buttonhandler class
   
   public class dateButtonHandler implements ActionListener
   {
  	  public void actionPerformed(ActionEvent event)
  	  {
  	    sout.println("date");
  	    try
  	    {
  	      	
  	      String  str=sin.readLine();
  	      dateTextArea.setText(str);
  	 	
  	    }
  	    catch(IOException ex)
  	    {
  	    }	
  	    	
  	  }
   }
   
   
 //  getDate ButtonHandler class  
   
   public class getDayButtonHandler implements ActionListener
   {
  	  public void actionPerformed(ActionEvent event)
  	  {
  	    
  	    try
  	    {
  	      sout.println("getday");	
  	      String str=sin.readLine();
  	      dateTextArea.setText(str);
  	    	
  	    }
  	    catch(IOException e)
  	    {
  	    }	
  	    	
  	  }
   }
   
  //  getMonth ButtonHandler class  
   
   public class getMonthButtonHandler implements ActionListener
   {
  	  public void actionPerformed(ActionEvent event)
  	  {
  	  	try
  	  	{
  	  	  sout.println("getMonth");
  	      String str=sin.readLine();
  	      dateTextArea.setText(str);	
  	  	}
  	  	catch(IOException e)
  	  	{
  	  	}	
  	    
  	    	
  	  }
   }
   
  //  getYear ButtonHandler class  
   
   public class getYearButtonHandler implements ActionListener
   {
  	  public void actionPerformed(ActionEvent event)
  	  {
  	  	try
  	  	{
  	  	  sout.println("getYear");
  	     String str=sin.readLine();
  	     dateTextArea.setText(str);
  	    	
  	  	}
  	  	catch(IOException e)
  	  	{
  	  	 	
  	  	}	
  	    	
  	  }
   }
   
	
	public static void main (String[] args) 
	{
	  	
	   new ClientDate3();	

    }
}