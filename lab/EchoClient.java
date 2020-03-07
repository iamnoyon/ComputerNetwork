import java.net.Socket;
import java.net.ServerSocket;
import java.io.*;
import java.util.Scanner;

public class EchoClient{
	public static void main(String[] args) throws Exception{
		int port=4444;
		BufferedReader sin=null;
		PrintWriter sout=null;
		
		Socket clientSocket=new Socket("127.0.0.1",port);
		System.out.println("Client started on port"+port);
		
		sin=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		sout=new PrintWriter(clientSocket.getOutputStream(),true);
		
		sout.println("Hello.");
		System.out.println(sin.readLine());
		sin.close();
		sout.close();
		clientSocket.close();
	}
}