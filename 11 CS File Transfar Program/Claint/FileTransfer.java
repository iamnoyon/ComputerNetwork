 // Fig. 11.24: FileTransfer.java
  // Selecting colors from a JList.
  import javax.swing.JFrame;

  public class FileTransfer
   {
      public static void main( String args[] )
      {
        FileFrame fileFrame = new FileFrame(); // create ListFrame
        fileFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        fileFrame.setSize( 300, 200 ); // set frame size
        fileFrame.setResizable(false);
        fileFrame.setVisible( true ); // display frame
     } // end main
  } // end class ListTest


