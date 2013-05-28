   public class ComputerAI extends Game{
   
      public ComputerAI(int Line, int Column){
         this.Line = Line;
         boolean winner = false; 
         this.Column= Column;
      }
      public boolean gotWinner(){ 
         while(winner!=true){
            if(win())
               winner = true;
            else{
               return false; 
            }
         }
      }
   }


