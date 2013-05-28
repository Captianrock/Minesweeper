   public class testingstuff {
      static Game game = new Game(3);
      public testingstuff() {
      }
      public static void getPositions(){
         System.out.println(game.getPosition(4,5));
      }
      public static void main (String[] args){
         getPositions(); 
      }
   }