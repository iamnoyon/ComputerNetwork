import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.lang.String;
import java.net.Socket;
import java.util.Scanner;
import java.awt.event.*;
import java.io.IOException;

public class EchoClient extends JFrame
{
	
	public JPanel  P=new JPanel();
	public Container C=new Container();
	
	public Scanner input=new Scanner(System.in);
	public PrintWriter sout ;	
    public BufferedReader sin ;
    Socket  echoSocket; 
    public int port;
	 public JButton Connect,DisConnect,Send,Clear;
	 public JLabel label1,label2;
	 public JTextField textfield1,textfield2;
	EchoClient()
	{
	  	
	  
	  super.setTitle("Echo Service");	
	  super.setSize(500,400);
	  
	   label1=new JLabel("  Client: ");
	   label1.setForeground(Color.BLUE);
	   label2=new JLabel("                                      echo mgs From Server:                        ");
	    label2.setForeground(Color.BLUE);
	  textfield1=new JTextField(15);
      textfield2=new JTextField(40);
       Connect=new JButton("Connect");
       Connect.setBackground(Color.GREEN);
      DisConnect=new JButton("DisConnect");
       DisConnect.setBackground(Color.RED);
       Clear=new JButton("Next MGS");
       Clear.setBackground(Color.PINK);
       Send=new JButton("Send");
       Send.setBackground(Color.LIGHT_GRAY);
  
	  super.setDefaultCloseOperation(EXIT_ON_CLOSE);
	  super.setResizable(false);
	  super.show(true);
	  
	   
	  C=getContentPane();		
	  P.add(label1);
	  P.add(textfield1);
	  C.add(P);
	  
	  P.add(label2);
	  P.add(textfield2);
	  
	  C.add(P);
	  
	  P.add(Connect);
	  P.add(DisConnect);
	  P.add(Send);
	  P.add(Clear);
	
	  C.add(P);
	  
	  Buttonhandler bh=new Buttonhandler();
	  Connect.addActionListener(bh);
	  Send.addActionListener(bh);
	  Clear.addActionListener(bh);
	  DisConnect.addActionListener(bh);
	  
	}
	 	
	public class Buttonhandler implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			
		 // st1=jtext1.getText();
		 // st2=jtext2.getText();
		
		   if(event.getSource()==Connect)
		   {
			   
			   try
               {
    	         int port = 4444;
  	             Socket echoSocket= new Socket("127.0.0.1",port);
  	             sin  = new BufferedReader(new InputStreamReader(echoSocket.getInputStream())); 
                 sout = new PrintWriter(echoSocket.getOutputStream(),true); 
        	 
                // sout.println("Connect");	
   
               }
               catch(IOException ioEx)
               {
               	  String res="Client is already connected";
                  JOptionPane.showMessageDialog(EchoClient.this,String.format("%s",res));
               }		
		
			}
		   else if(event.getSource()==Clear)
		    {
		   	
  	          textfield1.setText("");
  	          textfield2.setText("");	
		    }
		   else if(event.getSource()==Send)
		    {
		   	   try
  		       {
  		          String str;
         
                   str=textfield1.getText();
                   sout.println(str);
         
                    try
                    {
                       str=sin.readLine();	
                    }	
                    catch(IOException ex)
                    {
                    }		
          
                  textfield2.setText(str); 
  		      }
  		     finally
             {
         	
             }		
		   }
		   else if(event.getSource()==DisConnect)
		   {
		   	    try
		   	  	{
  	       	
  	               sout.println("exit");
  	       	       sin.close();
  	            }
  	         catch(IOException x)
  	            {
  	            	
  	               String res="DisConnected from Server";
                  JOptionPane.showMessageDialog(EchoClient.this,String.format("%s",res));	
  	            }
  		 	 sout.close();		 
  		     
  		        try
  		     	{
  		 	      echoSocket.close();
  		        }
  		     catch(IOException y)
  		        {
  		        	String res="Exception occure in DisConnect.";
                  JOptionPane.showMessageDialog(EchoClient.this,String.format("%s",res));
  		        }		 
		   }	
		
		}// end	actionPerformed method
	}// end Buttonhandler class
		
	public static void main (String[] args) 
	{
		new EchoClient();
			
    }// end main Method
}  // end EchoClient class