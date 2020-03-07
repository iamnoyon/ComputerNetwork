

 
   import java.io.EOFException;
   import java.io.IOException;
   import java.io.ObjectInputStream;
   import java.io.ObjectOutputStream;
   import java.net.InetAddress;
   import java.net.Socket;     
   import java.awt.BorderLayout;
   import java.awt.FlowLayout;
   import java.awt.Dimension;
   import java.awt.event.ActionEvent;
   import java.awt.event.ActionListener;
   import javax.swing.JFrame;
   import javax.swing.JScrollPane;
   import javax.swing.JTextArea;
   import javax.swing.JTextField;
   import javax.swing.JLabel;
   import javax.swing.JPanel;
   import javax.swing.JButton;
   import java.awt.Color;
   import javax.swing.BorderFactory;
   import javax.swing.SwingUtilities;  
 
public class Client_2 extends JFrame
   {
          private JButton exit,send,clear;
          private JLabel L,L1;                
          private JTextField enterField;    
          private JTextArea displayArea;    
          private ObjectOutputStream output; 
          private ObjectInputStream input;   
          private String message = "";       
          private String chatServer;         
          private Socket client;            
 
 
 

public Client_2( String host )
         {         
         chatServer = host; 
  
         L = new JLabel("chatting with own pair partner");
         L.setForeground(Color.blue); 
         L1 = new JLabel("Client_2");
         L1.setForeground(Color.blue);
         
          
  
         clear=new JButton("Clear");
         clear.setPreferredSize(new Dimension(90,30));
         clear.setForeground(Color.blue);
         clear.setBackground(Color.cyan);    
         clear.addActionListener(
               new ActionListener() {
                   public void actionPerformed( ActionEvent event )
                       {	
                       displayArea.setText( "" );  
                       enterField.setText( "" );
                       }
                   } );
       
       
       
          send=new JButton("Send");
          send.setPreferredSize(new Dimension(90,30));
          send.setForeground(Color.blue);   
          send.setBackground(Color.PINK);	 
          send.addActionListener(
              new ActionListener() {
                  public void actionPerformed( ActionEvent event )
                      {	
        	          try{
                         sendData( enterField.getText() );
                         enterField.setText( "" );
        	             } 
        	          catch (NullPointerException nullexp){
        	             displayArea.setText("Please activate server at first\n\n");
        	             }	         	
                      }
                  } );
 
 
 
          exit=new JButton("Exit");
          exit.setPreferredSize(new Dimension(70,30));
          exit.setForeground(Color.BLACK);
          exit.setBackground(Color.RED);   
          exit.addActionListener(
              new ActionListener() {
                  public void actionPerformed( ActionEvent event )
                      {	
                      System.exit(0);      
                      }
                  }
              );
 
 
 
          enterField = new JTextField(25); 
          enterField.setEditable( false );
          enterField.addActionListener(
            new ActionListener()
               { 
               public void actionPerformed( ActionEvent event )
                  {
                  sendData( event.getActionCommand() );
                  enterField.setText( "" );
                  }
               } 
          ); 


          displayArea = new JTextArea("",15,40); 
          displayArea.setEditable(false);
         
         
         
          JPanel  text_button = new JPanel();
          text_button.setLayout(new FlowLayout());
          text_button.add(L1);
          text_button.add(enterField);
          text_button.add(send);
          text_button.add(clear);
          text_button.add(exit);
          
          JPanel content=new JPanel();
          content.setLayout(new BorderLayout(5, 5));
          content.add(L, BorderLayout.NORTH);
          content.add(displayArea, BorderLayout.CENTER);
          content.add(new JScrollPane(displayArea));  
          content.add(text_button, BorderLayout.SOUTH);
          content.setBorder(BorderFactory.createEmptyBorder(10,25,10,25));
         
         

          this.setTitle("Client_2");
          this.setContentPane(content);    
          this.pack();
          this.setResizable(false);
          this.setLocationRelativeTo(null);
          this.setVisible( true );
       
} // end Client constructor 
     

public void runClient()
     {
          try 
             {
             connectToServer();   // create a Socket to make connection
             getStreams();        // get the input and output streams
             processConnection(); // process connection
             } 
          catch ( EOFException eofException ){
             displayMessage( "\nClient terminated connection" );
             } 
          catch ( IOException ioException ){
             ioException.printStackTrace();
             } 
          finally{
             closeConnection(); 
             } 
     }  //... ...


private void connectToServer() throws IOException
     {
          displayMessage( "Attempting connection\n" );
          client = new Socket( InetAddress.getByName( chatServer ), 7788 );
          displayMessage( "Connected to: " +client.getInetAddress().getHostName() );
     }  //... ... 
     
    
private void getStreams() throws IOException
     {
         output = new ObjectOutputStream( client.getOutputStream() );     
         output.flush(); // flush output buffer to send header information
         
         input = new ObjectInputStream( client.getInputStream() );
         displayMessage( "\nGot I/O streams\n" );
     }  //... ... 


private void processConnection() throws IOException
     {
          setTextFieldEditable( true );
          do 
             {
             try 
                {
                message = ( String ) input.readObject(); // read new message
                displayMessage( "\n" + message );        // display message
                }
             catch ( ClassNotFoundException classNotFoundException )
                {
                displayMessage( "\nUnknown object type received" );
                } 
  
          } while ( !message.equals( "Client_1: Quit" ) );
     }  //... ... 


private void closeConnection()
     {
          displayMessage( "\nClosing connection" );
          setTextFieldEditable( false );

          try
              {
              output.close(); // close output stream
              input.close();  // close input stream  1
              client.close(); // close socket       
              } 
          catch ( IOException ioException ){
              ioException.printStackTrace();
              } 
     }   //... ...  


private void sendData( String message )
     {
          try // send object to server
              {
              output.writeObject( "Client_2 : " + message );
              output.flush();    
              displayMessage( "\nClient_2 : " + message );
              } 
          catch ( IOException ioException ){
              displayArea.append( "\nError writing object" );
              }
     }   //... ...


private void displayMessage( final String messageToDisplay )
     {
          SwingUtilities.invokeLater(
          new Runnable()
              {
              public void run() 
                 {
                 displayArea.append( messageToDisplay );
                 } 
              }  
           );
     }   //... ...


private void setTextFieldEditable( final boolean editable )
     {
          SwingUtilities.invokeLater(
          new Runnable()
              {
              public void run() 
                 {
                 enterField.setEditable( editable );
                 } 
              } 
           ); 
     }   //... ... 
     	
     	
public static void main( String args[] )
      {
         Client_2 application; 
         if ( args.length == 0 )
            application = new Client_2( "127.0.0.1" ); // connect to localhost
         else
            application = new Client_2( args[ 0 ] ); // use args to connect   
         application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
         application.runClient(); // run client application
      }  //... ...
      
      
} // end class Client