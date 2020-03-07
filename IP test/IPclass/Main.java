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
    
    
    
    public  static void CLASSIPaddress()
    {
       
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
     
  if(val[0]>0)
      if(val[0]<124)
      JOptionPane.showMessageDialog(null,"CLASS A");
     
      else if(val[0]>123)
      if(val[0]<192)
      JOptionPane.showMessageDialog(null,"CLASS B");
     
     
      else if(val[0]>191)
      if(val[0]<224)
      JOptionPane.showMessageDialog(null,"CLASS C");
     
         else if(val[0]>223)
      if(val[0]<240)
      JOptionPane.showMessageDialog(null,"CLASS D\n Reserved for multicasting");
     
      else if(val[0]>239)
      if(val[0]<255)
      JOptionPane.showMessageDialog(null,"CLASS E\n Reserved for experimental use");
     
     
     
    
    }
    public static void main(String[] args) {
        
        
       CLASSIPaddress();
               
    }   


}
