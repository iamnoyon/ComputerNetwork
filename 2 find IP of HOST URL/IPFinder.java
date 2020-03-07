/**
 * @(#)IPFinder.java
 *
 *
 * @author 
 * @version 1.00 2012/7/27
 */
 import java.lang.*;
import javax.swing.*;
import java.net.*;
import java.util.*;
public class IPFinder
{
public static void main(String[] args)
{
	Integer a=1;
String x;		
String host;
Scanner input = new Scanner(System.in);
while(a>0) {
System.out.print("\n\nEnter host name: ");
host = input.next();
try
{
InetAddress address =
InetAddress.getByName(host);
System.out.println("IP address: "
+ address.toString());
}
catch (UnknownHostException uhEx)
{
System.out.println("Could not find " + host);
} 
System.out.println("\n\n   insert 1 to continue"+"\n"+"   insert 0 while to quite");
a=input.nextInt();
	}
}
}