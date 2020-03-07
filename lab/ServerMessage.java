import java.net.Socket;
import java.net.ServerSocket;
import java.io.*;
import java.util.Scanner;

public class ServerMessage{
	public static void main(String[] args) throws Exception{
		int port=4444;
		BufferedReader sin=null;
		PrintWriter sout=null;
		
		ServerSocket serverSocket=new ServerSocket(port);
		System.out.println("Connection started on port"+port);
		
		Socket clientSocket=serverSocket.accept();
		System.out.println("Connection Accepted.");
		
		sin=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		sout=new PrintWriter(clientSocket.getOutputStream(),true);
		sout.println("WelCome to Server.");
		
		Scanner sc=new Scanner(System.in);
		String str;
		
		while(true)
		{
			str=sin.readLine();
			if(str.equals("quit"))
			{
				sout.println("Ok bye");
				break;
			}
			
			System.out.println(str);
			str=sc.nextLine();
			sout.println(str);
		}
		
		//System.out.println(sin.readLine());
		//sout.println("Thank you.");
	}
}
