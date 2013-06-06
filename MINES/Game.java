   import java.util.Random;
   import java.util.Scanner;
   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;

   public class Game{
      private boolean [][] covering;
      private boolean [][] marking; 
      int count = 0;    
      private int[][] mines;
      private char[][] boardgame;
      public int Line, Column,mark, inputLine,inputColumn,difficult,numbamines;
      Random random = new Random(); 
      boolean finish = false;
      boolean flag = false;
      boolean marks = false;
      int turn = 1 ;  
      int totalturn,score1,score2,goal = 0; 
      int countergoal = 0; 
      Scanner input = new Scanner(System.in); 
      private int [][] isCovered;
     
      public Game(){
         startMines();
         randomMines();
         fillNumber();
         startBoard();
         
      }
   	
    
      public Game(int difficult1){
         difficult = difficult1; 
         difficultyset(difficult);         
         startMines();
         randomMines();  
         fillNumber();
         startBoard(); 
      }
    
      public void difficultyset(int difficulty){
         if(difficulty==1){
            Line = 10; 
            Column = 10;
            goal = 85 ; 
            isCovered=new int[10][10];
            covering = new boolean [10][10];
            marking = new boolean [10][10]; 
            mines = new int[Line+3][Column+3];
            boardgame = new char[Line+3][Column+3]; 
            numbamines= 14; 
            for (int i = 0 ; i < 10 ; i++){
               for (int j = 0 ; j < 10 ; j++){
                  covering[i][j] = true; 
                  isCovered[i][j] = 0;
               }
            }
         }
         
         else if(difficulty==2){
            Line = 16;
            Column = 16;
            goal = 195;
            isCovered=new int[16][16];
            covering = new boolean [16][16]; 
            marking = new boolean [16][16]; 
            mines = new int[Line+3][Column+3];
            boardgame = new char[Line+3][Column+3];
            numbamines = 60; 
            for (int i = 0 ; i < 16 ; i++){
               for (int j = 0 ; j < 16 ; j++){
                  covering[i][j] = true;
                  isCovered[i][j] = 0; 
               }
            }
         }
         else if(difficulty==3){
            Line = 16;
            Column = 30;
            goal = 380;
            isCovered=new int[16][30];
            covering = new boolean [16][30]; 
            marking = new boolean [16][30]; 
            mines = new int[Line+3][Column+3];
            boardgame = new char[Line+3][Column+3];
            numbamines = 99;
            for (int i = 0 ; i < 16 ; i++){
               for (int j = 0 ; j < 30 ; j++){
                  covering[i][j] = true;
                  isCovered[i][j] = 0; 
               }
            } 
         }
      	      
      }
      public void play(int Line1, int Column1){       
         finish = isBomb(Line1, Column1);
         if(!finish){
            openNeighbors(Line1, Column1);
            finish = win();
         }
      }
      public boolean loser(int Line, int Column){
         finish = isBomb(Line,Column);
         if(finish){
            return true;
         }
         else {
            return false;
         } 
      }
       
      public boolean win(){       
         if(goal == countergoal)
            return true;
         return false;              
      }
      public boolean winbattle(){
         if(score1 > score2)
            return true;
         else 
            return false;
      }
      public void addScore(){
         if(turn==1){
            score1++;
         }
         else
            score2++;	      
      }
   
      public void openNeighbors(int Line1, int Column1){      
         System.out.println(" The openning line and columns: Line : " + Line + " Column: " + Column); 
         if(Line1>=Line || Column1>=Column || Line1<0 || Column1<0 ){
            return;
         }
         if(isCovered[Line1][Column1]>1){
            return;
         }
         
         if(mines[Line1][Column1]== -1){
            setCovered(Line1, Column1);
            System.out.println("AAAAAAAAAAAAA BOMB :(");
            return; 
         }
         if(mines[Line1][Column1]>0){
            setCovered(Line1, Column1);
            return;
         }
         else{
            setCovered(Line1,Column1);
            algorthim(Line1,Column1); 
         }         
         
         
      }
      public void algorthim(int Line1, int Column1){
         for(int i=-1 ; i<2 ; i++){
            for(int j=-1 ; j<2 ; j++){
               int tempLine = Line1 + i;
               int tempColumn = Column1 + j;         
               if(Line1 + i < 0){
                  tempLine =0; 
               } 
               if(Column1 + j < 0){
                  tempColumn = 0; 
               }
               if(Column1 + j >= Column){
                  tempColumn = Column-1; 
               }
               if(Line1 + i >= Line){
                  tempLine = Line -1; 
               }
               if(covering[tempLine][tempColumn] == true){
                  if(mines[tempLine][tempColumn] > 0){
                     setCovered(tempLine, tempColumn);
                     covering[tempLine][tempColumn] = false; 
                  } 
               
                  if(mines[tempLine][tempColumn] == 0){             
                     openNeighbors(tempLine,tempColumn);
                     covering[tempLine][tempColumn]= false; 
                  
                  }
               }
               
            }  
            
         }
            
      }
      public void Mark( int Line, int Column){
         if(covering[Line][Column] == true && marking[Line][Column] == false){       
            marking[Line][Column] = true; 
         }
         else if( covering[Line][Column] == true && marking[Line][Column] == true){
            marking[Line][Column] = false; 
         }
      }
      public boolean getMarking (int Line, int Column){
         return marking[Line][Column];
      }
      public int getPosition(int Line, int Column){
         return mines[Line][Column];
      }
      public char getCoverPosition(int Line, int Column){
         return boardgame[Line][Column];
      }
      public boolean Covered(int Line, int Column){
         return covering[Line][Column]; 
      }
      public void setCovered(int Line1, int Column1){
         if(Line1<Line && Column1<Column && Line>=0 && Column1>=0 && mines[Line1][Column1] != -1 && covering[Line1][Column1] ==true){    
            covering[Line1][Column1]=false; 
            isCovered[Line1][Column1]+=1;
            countergoal++;    
         } 
         else{
            return; 
         }
         
      }
      public boolean isBomb(int inputLine, int inputColumn){
         if((inputLine >= 0 || inputLine < Line || inputColumn >= 0 || inputColumn < Column)){
            if(getPosition(inputLine, inputColumn)== -1)
               return true;
         }      
         return false; 
      }    
       
      public void fillNumber(){
         for(int line=0 ; line < Line ; line++)
            for(int column=0 ; column < Column; column++){
               if(column == 0 && line ==0){
                  if(mines[line][column] != -1)
                     if(mines[line+1][column] ==-1 || mines[line][column+1]==-1|| mines[line+1][column+1]==-1)
                        mines[line][column]++;
               } 
               else if (column ==0 && line != 0){
                  if(mines[line][column] != -1){
                     if(mines[line+1][column] ==-1)
                        mines[line][column]++;
                     if(mines[line-1][column]==-1)
                        mines[line][column]++;
                     if(mines[line][column+1]==-1)
                        mines[line][column]++;
                     if(mines[line-1][column+1]==-1)
                        mines[line][column]++;
                     if(mines[line+1][column+1]==-1)
                        mines[line][column]++;
                  }
               }
               else if (line ==0 && column !=0){
                  if(mines[line][column] != -1){
                     if(mines[line+1][column] ==-1)
                        mines[line][column]++;
                     if(mines[line+1][column-1]==-1)
                        mines[line][column]++;
                     if(mines[line+1][column+1]==-1)
                        mines[line][column]++;
                     if(mines[line][column-1]==-1)
                        mines[line][column]++;
                     if(mines[line][column+1]==-1)
                        mines[line][column]++;
                  }
               }
               else{
                  for(int i=-1 ; i<=1 ; i++)
                     for(int j=-1 ; j<=1 ; j++)
                        if(mines[line][column] != -1)
                           if(mines[line+i][column+j] == -1)
                              mines[line][column]++;
               }
            }
            
      }
    
    
      public void startBoard(){
         for(int i=0 ; i<Line ; i++){
            for(int j=0 ; j<Column; j++){
               marking[i][j] = false; 
               
            }
         }
      }
    
      public void startMines(){
         for(int i=0 ; i<Line ; i++)
            for(int j=0 ; j<Column ; j++)
               mines[i][j]=0;
      }
   
      public void randomMines(){
         boolean shuffle;
         int x, y;
         for(int i=0 ; i<numbamines ; i++){
            
            do{
               x = random.nextInt(Line);
               y = random.nextInt(Column);
                
               if(mines[x][y] == -1)
                  shuffle=true;
               else
                  shuffle = false;
            }while(shuffle);
            
            mines[x][y] = -1;
         }
      }
    
    /* TEXT BASED GAME */ 
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      public void show(){
         System.out.println("\n     Lines");
         for(int i = 1 ; i < Line+1 ; i++){
            if(i > 10)
               System.out.print("      "+i + " ");  
            else{   
               System.out.print("       "+ i + " ");
            }
         	
            
            for(int j = 1 ; j< Column + 1 ; j++){
               System.out.print("   "+ boardgame[i][j]);
            }
                
            System.out.println();
         }
            
         System.out.println("\n            1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20  21  22  23  24  25  26  27  28  29  30 ");
         System.out.println("                                                        				Columns");
        
      }
      public boolean isBombtext(){
            
         do{
            flag = false; 
            System.out.print("\nLine: "); 
            inputLine = input.nextInt();
            System.out.print("Column: "); 
            inputColumn = input.nextInt();
            System.out.print("Mark/unmark? (1 for yes or 2 for no) : ");
            mark = input.nextInt();
            if(mark==1)
               Marktext(inputLine,inputColumn);
            else if(mark==2)
               flag=false;
            else{
               System.out.println("No, you idiot 1 or 2"); 
            }
            if( boardgame[inputLine][inputColumn] == '!')
               System.out.println("You have marked it");
         	  
            if( (boardgame[inputLine][inputColumn] != '_') && ((inputLine < Line +1 && inputLine > 0) && (inputColumn < Column + 1 && inputColumn > 0)))
               System.out.println("You already choose this");
                
            if( inputLine < 1 || inputLine > Line || inputColumn < 1 || inputColumn > Column)
               System.out.println("Choose a number between 1 and 20, idiot");
                
         }
         while((inputLine < 1 || inputLine > Line || inputColumn < 1 || inputColumn > Column) || (boardgame[inputLine][inputColumn] != '_') );
            
         if(getPosition(inputLine, inputColumn)== -1)
            return true;
         else
            return false;
            
      }
      public void playtext(){
         do{
            if(turn==1){
               turn=2;
               totalturn++;
            }
            else{
               turn=1;
               totalturn++;
            }        
            finish = isBombtext();
            
            if(!finish){
               openNeighborstext();
               finish = win();
            }
            
         }while(!finish);
        
         if(win()){
            System.out.println("Congratulations, Player " + turn + " you sexy son of a gun");
           
         } 
         else {
            System.out.println("You blew yourself to bits Player " + turn);
            
         }
      }
      public void openNeighborstext(){
         for(int i=-1 ; i<2 ; i++)
            for(int j=-1 ; j<2 ; j++)
               if((mines[Line+i][Column+j] != -1) && (Line != 0 && Line != Line+1 && Column != 0 && Column != Column+ 1)){ //&& mines[inputLine+i][inputColumn+j]==0){
                  setCovered(Line+i,Column+j); 
                  boardgame[Line+i][Column+j]=Character.forDigit(mines[Line+i][Column+j], 10);
               }
      
      
      
      }
      public void Marktext(int x , int y){
         if(boardgame[x][y]!= '!'){
            boardgame[x][y]='!';
            marks = true; 
            Markscoretext();
         }
         else{
            boardgame[x][y]= '_';
            marks = false;
            Markscoretext();  
         } 
      }
      public void Markscoretext(){
         int wha = totalturn%2;
         if(marks == true){
            if(wha == 0){
               score1++; 
            } 
            else{
               score2++; 
            }
         }
         else if(marks== false){
            if(wha == 0){
               score1--;
            } 
            else{
               score2--; 
            }
         
         }
         marks = false; 
      }
   
   }