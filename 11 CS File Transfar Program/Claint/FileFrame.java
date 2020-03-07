
   import java.awt.FlowLayout;
   import java.awt.Color;
   import javax.swing.JFrame;
   import javax.swing.JList;
   import javax.swing.JScrollPane;
   import javax.swing.event.ListSelectionListener;
   import javax.swing.event.ListSelectionEvent;
   import javax.swing.ListSelectionModel;
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
   import java.io.FileReader;
   import java.io.BufferedReader;
   import java.net.Socket;






   public class FileFrame extends JFrame
  {
     private JList fileList; // list of files
     private final String fileNames[] = { "Computer Network.txt","Compiler design.txt", "DBMS concept.txt",  
                                            "Digital signal processing.txt", "nature_0094.jpg","Graphics.txt" ,
                                           "data communication.txt","artificial I.txt"  };
                                           
   
     public JButton  Connect,DisConnect,Send;
     	
	 public JPanel  P=new JPanel();
	 public Container C=new Container();
	 public JLabel label1; 
	 Scanner input=new Scanner(System.in);
	 public PrintWriter sout ;	
     public BufferedReader sin ;
     Socket  echoSocket; 
     public int port;
     FileReader inputFileReader ;
     BufferedReader inputStream;
     
     
   
     DataInputStream inData=new DataInputStream(System.in);
     
     public FileFrame()
     {
        super( "File Transfer Client" );
        setLayout( new FlowLayout() ); 

        fileList = new JList( fileNames ); // create list
        fileList.setVisibleRowCount( 7 ); // display 7 files  at once
        fileList.setBackground(Color.lightGray);
      
 Connect=new JButton("Connect");
     Connect.setBackground(Color.PINK);
  DisConnect=new JButton("DisConnect");
	 DisConnect.setBackground(Color.RED);
     Send=new JButton("Send");
     Send.setBackground(Color.GREEN);
     label1=new JLabel("File List");
	label1.setBackground(Color.ORANGE);
                                   
        fileList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
fileList.setForeground(Color.CYAN);

       
       
        
        add(Connect);
        add(Send);
        add(DisConnect);
        add(label1);
                
        // add a JScrollPane containing JList to frame
        add( new JScrollPane( fileList ) );
     
               

        fileList.addListSelectionListener( 
        	new ListSelectionListener()
           {   
              //  eventhandeller
              public void valueChanged( ListSelectionEvent event )
              {
                
                JOptionPane.showMessageDialog(FileFrame.this,String.format("%s",fileNames[ fileList.getSelectedIndex() ]));
              } 
           } 
        ); // end call to addListSelectionListener
        
        
        // Buttonhandler
        
        Buttonhandler bh=new Buttonhandler();
	    Connect.addActionListener(bh);
	    Send.addActionListener(bh);
	    DisConnect.addActionListener(bh);
	     
     } 
     
     public class Buttonhandler implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
		
		   if(event.getSource()==Connect)
		   {
			   
			   try
               {
    	         int port = 4444;
  	             Socket echoSocket= new Socket("127.0.0.1",port);
  	             sin  = new BufferedReader(new InputStreamReader(echoSocket.getInputStream())); 
                 sout = new PrintWriter(echoSocket.getOutputStream(),true); 
        	 
               
   
               }
               catch(IOException ioEx)
               {
               	  String res="Client is already connected";
                  JOptionPane.showMessageDialog(FileFrame.this,String.format("%s",res));
               }		
		
			}
			
		   else if(event.getSource()==Send)
		    {
		         try
		         {
		         	// File operation 
                    String file=new String(fileNames[ fileList.getSelectedIndex() ]); 
                    System.out.println("File Name: "+file); 	 
                    
                 
                    	FileReader inputFileReader   = new FileReader(file); // Creating FileReader for file read 
                        BufferedReader inputStream   = new BufferedReader(inputFileReader); //Creating BufferReader for read form file as string       
                 	     
                    
               
                    sout.println(file);
                    
                    System.out.println("#sending started# "+ file); // Printing in client console "#File Open#" 
                    String inLine=null;  // String for reading line from file
             
                	
                    while(true)
                    {
                      	  
                       inLine = inputStream.readLine();  // reading a line from File  	
                       
                       sout.println(inLine);                // send to server that line
                  
                       if(inLine.equals("EOF"))  // checking end of file EOF 
                       {
                          System.out.println("#sending done# "+file);// Printing to the client console "#File Close#" 	
                          break;
                       }
                      
                       System.out.println(inLine);  // Printing to the client console  
         	
                     }
                     
                   String res="File Send";	
		           JOptionPane.showMessageDialog(FileFrame.this,String.format("%s",res));  
        
                   inputStream.close();  // closing file 
		         }
		        catch(IOException e)
		        {
		            String resstr="Exception Occured in Sending of file";
                  JOptionPane.showMessageDialog(FileFrame.this,String.format("%s",resstr));	
		          	
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
                  JOptionPane.showMessageDialog(FileFrame.this,String.format("%s",res));	
  	            }
  		 	 sout.close();		 
  		     
  		        try
  		     	{
  		 	      echoSocket.close();
  		        }
  		     catch(IOException y)
  		        {
  		        	String res="Exception occure in DisConnect.";
                  JOptionPane.showMessageDialog(FileFrame.this,String.format("%s",res));
  		        }		 
		   }	
		
		}// end	actionPerformed method
	}// end Buttonhandler class
  } 
