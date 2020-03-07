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

public class Client extends JFrame
{

public JButton client1,client2,client3;

public  Client(){

 super.setTitle(" Client ");
  	  super.setSize(450,190);
  	  super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  	  super.setResizable(false);

           setLayout(new FlowLayout());
client1=new JButton("RUN 1st CLIENT");
client1.setBackground(Color.PINK);
client2=new JButton("RUN 2nd CLIENT");
client2.setBackground(Color.GREEN);
client3=new JButton("RUN 3rd CLIENT");
client3.setBackground(Color.ORANGE);

client1ButtonHandler client1handler=new client1ButtonHandler();
  	   client1.addActionListener(client1handler);


client2ButtonHandler client2handler=new client2ButtonHandler();
  	   client2.addActionListener(client2handler);

client3ButtonHandler client3handler=new client3ButtonHandler();
  	   client3.addActionListener(client3handler);
java.awt.Container C=getContentPane();

C.add(client1);
  
C.add(client2);

 C.add(client3);
   
super.show(true);
   	
	}
public class client1ButtonHandler implements ActionListener
  {
  	public void actionPerformed(ActionEvent event)
  	{ ClientDate a=new ClientDate();
}

}
public class client2ButtonHandler implements ActionListener
  {
  	public void actionPerformed(ActionEvent event)
  	{ ClientDate2 b=new ClientDate2();
}

}

	
public class client3ButtonHandler implements ActionListener
  {
  	public void actionPerformed(ActionEvent event)
  	{ ClientDate3 c=new ClientDate3();
}

}


	public static void main (String[] args) 
	{
	  	
	   new Client();	

    }
}

