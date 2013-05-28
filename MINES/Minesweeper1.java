   import javax.swing.JFrame; 

   public class Minesweeper1 extends JFrame {
      public Minesweeper1(){    
         add(new Board());    
         setTitle("Minesweeper");  
         setDefaultCloseOperation(EXIT_ON_CLOSE);
         setSize(800,600);
         setLocationRelativeTo(null);
         setVisible(true);
         setResizable(false); 
    	
         
      }    
   	
      public static void main(String[] args) {
         new Minesweeper1(); 
      }   
   }