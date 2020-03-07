/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ip;
import javax.swing.*;
import  java.util.*;
import java.lang.*;
import  java.io.*;

/**
 *
 * @author Administrator
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    
    
    public  static void validIPaddress()
    {
       //int aray
              int val[];
         val=new int[10];
         String IPadd=null;
	    String[] token;
         String IP1=null;
        
        
          String INPUTIP=JOptionPane.showInputDialog("Insert the IP Address");
        System.out.println("the value is"+INPUTIP);
        
             
        
        IPadd=INPUTIP.replace('.',' ');
    	token=IPadd.split(" ");

        
        System.out.println("token length= " + token.length);     
   
   
   
   
       int kase=0;
     
     for(String t: token)
     {
     int u=kase++;
     	val[u]=Integer.valueOf(t);
    
     } 
     
     int test=0;
     int i;
       for(i=0;i<token.length;i++)
       {
           int x =val[i];
       if (x>255)
           test=1;
           if(x<0)    
          test=1;
               
       }
     
     
     
     
     int p=token.length;
       if(p!=4)
      System.out.println("invalid IP");
       
       else if (test==1)
            System.out.println("invalid IP");
       else 
            System.out.println("valid IP");
      	

  
      
        
    
    }
    public static void main(String[] args) {
        
        
        validIPaddress();
               
    }   


}
