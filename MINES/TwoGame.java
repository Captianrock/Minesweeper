   import java.util.Random;
   import java.util.Scanner;
   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;

   public class TwoGame{
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
      private int [][] isCovered;
     
      public TwoGame(){
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
         startMines();
         randomMines();  
         fillNumber();
         startBoard(); 
      
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
      
   
      public void openNeighbors(int Line1, int Column1){      
         System.out.println(" The openning line and columns: Line : " + Line1 + " Column: " + Column1); 
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
      public void Fire(){
      
      }
      public void multiFlag(){
      
      }
      public void Bomb(){
      
      }
    
   }