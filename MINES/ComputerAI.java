   public class ComputerAI extends Game{
   
      public ComputerAI(int Line, int Column){
        Line = Line;
        winner = false; 
         Column= Column;
      }
      public boolean gotWinner(){ 
         while(winner!=true){
            if(win()){
               winner = true;
						System.out.println("Cool"); 
						}
            else{
               return false; 
            }
         }
      }
	
   }


