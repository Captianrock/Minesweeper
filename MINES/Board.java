
   import java.util.Random;
   import java.util.Scanner;
   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;

   public class Board extends JPanel implements MouseListener, MouseMotionListener {
      private int Line, Column, player1, player2, mark;
      Random random = new Random();
      Scanner input = new Scanner(System.in);
      private boolean flag; 
      private Board board;
      boolean lose = false;
      boolean win = false;
      boolean firing, bombing, flagging;
      private final int num_imgs = 14;
      private final int pixelsize = 15;
      int turn=2;
      int playercount = 2; 
      public int tempLine,tempColumn;  
      private Image[] img;
      Image image , Startup, Classic, Computer, Battle,Classic2, Easy, Medium, Hard,ClassicBack,WinnerBack,TwoPlayBack,Bomb,Fire,MultiFlag,Retry;
      boolean starting = true; 
      boolean solo,ai,twoplay,EASY,MEDIUM,HARD,checkstart;
      boolean playeroneturn = true; 
      Button classic, computer, battle, easy, medium, hard, Bombbutt,Firebutt, Flagbutt,Retrybutt; 
      Game gameeasy = new Game(1);
      Game gamemed = new Game(2);
      Game gamehard= new Game(3);
      TwoGame gametwo = new TwoGame(); 
      public Board(){
         setFocusable(true);
         requestFocus();
         addMouseListener(this);  
         ImageIcon a = new ImageIcon(this.getClass().getResource("Startup.png"));
         ImageIcon b = new ImageIcon(this.getClass().getResource("Classic.png"));
         ImageIcon c = new ImageIcon(this.getClass().getResource("computer.png"));
         ImageIcon d	= new ImageIcon(this.getClass().getResource("BATTLE.png"));	
         ImageIcon e = new ImageIcon(this.getClass().getResource("Classic part 2.png"));
         ImageIcon f = new ImageIcon(this.getClass().getResource("Easy.png"));
         ImageIcon g = new ImageIcon(this.getClass().getResource("Medium.png"));
         ImageIcon h	= new ImageIcon(this.getClass().getResource("Hard.png"));
         ImageIcon hh	= new ImageIcon(this.getClass().getResource("ClassicBack.png"));	
         ImageIcon gg = new ImageIcon(this.getClass().getResource("Winner.png"));
         ImageIcon ii = new ImageIcon(this.getClass().getResource("TwoPlayBack.png"));	
         ImageIcon aa = new ImageIcon(this.getClass().getResource("bomb.png"));	
         ImageIcon bb = new ImageIcon(this.getClass().getResource("fire.png")); 	
         ImageIcon cc = new ImageIcon(this.getClass().getResource("Retry.png")); 	
         Startup = a.getImage(); 
         Classic = b.getImage();
         Computer = c.getImage();
         Battle = d.getImage();  
         Classic2 = e.getImage(); 
         Easy = f.getImage();
         Medium = g.getImage();
         Hard = h.getImage(); 
         ClassicBack = hh.getImage();
         WinnerBack = gg.getImage(); 
         TwoPlayBack = ii.getImage(); 
         Bomb = aa.getImage();
         Fire = bb.getImage(); 
         Retry = cc.getImage(); 
         classic = new Button(50,300,250,400,Classic);
         battle = new Button(300,300,500,400, Battle);
         computer = new Button(550, 300,750,400, Computer);
         easy = new Button(50,300,250,400,Easy);
         medium= new Button(300,300,500,400,Medium);
         hard= new Button(550, 300, 750, 400, Hard);
         Bombbutt= new Button( 725,475, 750, 500, Bomb); 
         Firebutt= new Button(725,275, 750,300, Fire); 
         Flagbutt = new Button(725, 75, 750, 100, MultiFlag);  
         Retrybutt= new Button(200,10, 350, 110, Retry); 
         img = new Image[num_imgs];
         for (int i = 0; i < num_imgs; i++) {
            img[i] =(new ImageIcon(this.getClass().getResource("/img/j"+(i)+ ".png"))).getImage();
         }
      }    
      public int gettempLine(){
         return tempLine;
      }
      public int gettempColumn(){
         return tempColumn; 
      }
      public void changeFlag(){
         System.out.println("ok");
         if(playeroneturn)
            MultiFlag = img[11]; 
         else{
            System.out.println("not ok"); 
            MultiFlag = img[13]; 
         }
      }
      public void paint(Graphics gr){
         Graphics2D g = (Graphics2D) gr;
         if(starting){
            g.drawImage(Startup,0,0,null); 
            g.drawImage(Classic,50,300,null);
            g.drawImage(Computer,550,300,null); 
            g.drawImage(Battle, 300,300,null);      
            if(solo && !ai && !twoplay){
               g.drawImage(Classic2,0 , 0 , null);
               g.drawImage(Easy,50,300,null);
               g.drawImage(Medium,300,300,null);
               g.drawImage(Hard,550,300,null);
            }
         }
         else if (twoplay && !ai && !solo){
            g.drawImage(TwoPlayBack, 0 , 0 , null); 
            g.drawImage(Bomb, 750, 500 , null); 
            g.drawImage(Fire, 750, 300 , null); 
            g.drawImage(MultiFlag, 750, 100, null); 
            g.drawImage(Retry, 350, 10, null); 
            for (int i = 0; i < 16; i++) {
               for (int j = 0; j < 30; j++) {
                  int temp = gametwo.getPosition(i,j);
                  if(temp == -2){
                     g.drawImage(img[12],(j*20) + 90, (i*20)+120, this); 
                  }            
                  if( temp == -1)
                     g.drawImage(img[9],(j*20) + 90, (i*20)+120, this); 
                  if( temp == 0)
                     g.drawImage(img[0],(j*20) + 90, (i*20)+120, this); 
                  if( temp == 1)
                     g.drawImage(img[1],(j*20) + 90, (i*20)+120, this); 
                  if( temp == 2)
                     g.drawImage(img[2],(j*20) + 90, (i*20)+120, this); 
                  if( temp == 3)
                     g.drawImage(img[3],(j*20) + 90, (i*20)+120, this); 
                  if( temp == 4)
                     g.drawImage(img[4],(j*20) + 90, (i*20)+120, this); 
                  if( temp == 5)
                     g.drawImage(img[5],(j*20) + 90, (i*20)+120, this); 
                  if( temp == 6)
                     g.drawImage(img[6],(j*20) + 90, (i*20)+120, this); 
                  if( temp == 7)
                     g.drawImage(img[7],(j*20) + 90, (i*20)+120, this);   
                  if( temp == 8)
                     g.drawImage(img[8],(j*20) + 90, (i*20)+120, this);
                  if(gametwo.Covered(i, j) == true)
                     g.drawImage(img[10],(j*20) + 90, (i*20)+120, this); 
                  if(gametwo.getMarking(i,j) == true){
                     g.drawImage(img[11],(j*20) + 90, (i*20)+120, this);
                  
                  } 
               
               }
            }
         
         }
         else if(solo && HARD && !EASY && !MEDIUM && lose !=true && win !=true){ 
            g.drawImage(ClassicBack,0,0,null);
            g.drawImage(Retry, 350, 10, null); 
            for (int i = 0; i < 16; i++) {
               for (int j = 0; j < 30; j++) {
                  int temp = gamehard.getPosition(i,j);
                  if( temp == -1)
                     g.drawImage(img[9],(j*20) + 90, (i*20)+110, this); 
                  if( temp == 0)
                     g.drawImage(img[0],(j*20) + 90, (i*20)+110, this); 
                  if( temp == 1)
                     g.drawImage(img[1],(j*20) + 90, (i*20)+110, this); 
                  if( temp == 2)
                     g.drawImage(img[2],(j*20) + 90, (i*20)+110, this); 
                  if( temp == 3)
                     g.drawImage(img[3],(j*20) + 90, (i*20)+110, this); 
                  if( temp == 4)
                     g.drawImage(img[4],(j*20) + 90, (i*20)+110, this); 
                  if( temp == 5)
                     g.drawImage(img[5],(j*20) + 90, (i*20)+110, this); 
                  if( temp == 6)
                     g.drawImage(img[6],(j*20) + 90, (i*20)+110, this); 
                  if( temp == 7)
                     g.drawImage(img[7],(j*20) + 90, (i*20)+110, this);   
                  if( temp == 8)
                     g.drawImage(img[8],(j*20) + 90, (i*20)+110, this);
                  if(gamehard.Covered(i, j) == true)
                     g.drawImage(img[10],(j*20) + 90, (i*20)+110, this); 
                  if(gamehard.getMarking(i,j) == true){
                     g.drawImage(img[11],(j*20) + 90, (i*20)+110, this);
                  
                  } 
               
               }
            }
         }
         else if(solo && !HARD && EASY && !MEDIUM && lose !=true && win !=true){
            g.drawImage(ClassicBack,0,0,null);
            g.drawImage(Retry, 350, 10, null); 
            for (int i = 0; i < 10; i++) {
               for (int j = 0; j < 10; j++) {
                  int temp = gameeasy.getPosition(i,j);
                  if( temp == -1)
                     g.drawImage(img[9],(j*20) + 300, (i*20)+200, this); 
                  if( temp == 0)
                     g.drawImage(img[0],(j*20) + 300, (i*20)+200, this); 
                  if( temp == 1)
                     g.drawImage(img[1],(j*20) + 300, (i*20)+200, this); 
                  if( temp == 2)
                     g.drawImage(img[2],(j*20) + 300, (i*20)+200, this); 
                  if( temp == 3)
                     g.drawImage(img[3],(j*20) + 300, (i*20)+200, this); 
                  if( temp == 4)
                     g.drawImage(img[4],(j*20) + 300, (i*20)+200, this); 
                  if( temp == 5)
                     g.drawImage(img[5],(j*20) + 300, (i*20)+200, this); 
                  if( temp == 6)
                     g.drawImage(img[6],(j*20) + 300, (i*20)+200, this); 
                  if( temp == 7)
                     g.drawImage(img[7],(j*20) + 300, (i*20)+200, this);   
                  if( temp == 8)
                     g.drawImage(img[8],(j*20) + 300, (i*20)+200, this);
                  if(gameeasy.Covered(i, j) == true)
                     g.drawImage(img[10],(j*20) + 300, (i*20)+200, this); 
                  if(gameeasy.getMarking(i,j) == true)
                     g.drawImage(img[11],(j*20) + 300, (i*20)+200, this);
                
               }                
            
            }  
         }
         else if(solo && !HARD && !EASY && MEDIUM && lose !=true && win !=true){
            g.drawImage(ClassicBack,0,0,null);
            g.drawImage(Retry, 350, 10, null); 
            for (int i = 0; i < 16; i++) {
               for (int j = 0; j < 16; j++) {
                  int temp = gamemed.getPosition(i,j);
                  if( temp == -1)
                     g.drawImage(img[9],(j*20) + 230, (i*20)+100, this); 
                  if( temp == 0)
                     g.drawImage(img[0],(j*20) + 230, (i*20)+100, this); 
                  if( temp == 1)
                     g.drawImage(img[1],(j*20) + 230, (i*20)+100, this); 
                  if( temp == 2)
                     g.drawImage(img[2],(j*20) + 230, (i*20)+100, this); 
                  if( temp == 3)
                     g.drawImage(img[3],(j*20) + 230, (i*20)+100, this); 
                  if( temp == 4)
                     g.drawImage(img[4],(j*20) + 230, (i*20)+100, this); 
                  if( temp == 5)
                     g.drawImage(img[5],(j*20) + 230, (i*20)+100, this); 
                  if( temp == 6)
                     g.drawImage(img[6],(j*20) + 230, (i*20)+100, this); 
                  if( temp == 7)
                     g.drawImage(img[7],(j*20) + 230, (i*20)+100, this);   
                  if( temp == 8)
                     g.drawImage(img[8],(j*20) + 230, (i*20)+100, this);
                  if(gamemed.Covered(i, j) == true)
                     g.drawImage(img[10],(j*20) + 230, (i*20)+100, this); 
                  if(gamemed.getMarking(i,j) == true)
                     g.drawImage(img[11],(j*20) + 230, (i*20)+100, this);
               
               } 
               
            }
         
         }
         
         
         else if(solo && !HARD && EASY && !MEDIUM && lose == true && win!= true){ 
            System.out.println("PAINT THE LOSER"); 
            for (int i = 0; i < 10; i++) {
               for (int j = 0; j < 10; j++) {
                  int temp = gameeasy.getPosition(i,j);
                  if(gameeasy.Covered(i,j) == true){
                     g.drawImage(img[10],(j*20) + 300, (i*20)+200, this); 
                  }
                  if(temp == -1) {
                     g.drawImage(img[9],(j*20) + 300, (i*20)+200, this); 
                  }
               }
            }
         }
         else if(solo && !HARD && EASY && !MEDIUM && win == true && lose !=true ){ 
            System.out.println("PAINT THE Winner"); 
            g.drawImage(WinnerBack,0,0,null);
         
         }
         else if(solo && !HARD && !EASY && MEDIUM && lose == true && win!= true){ 
            System.out.println("PAINT THE LOSER"); 
            for (int i = 0; i < 16; i++) {
               for (int j = 0; j < 16; j++) {
                  int temp = gamemed.getPosition(i,j);
                  if(gamemed.Covered(i,j) == true){
                     g.drawImage(img[10],(j*20) + 230, (i*20)+100, this); 
                  }
                  if(temp == -1) {
                     g.drawImage(img[9],(j*20) + 230, (i*20)+100, this); 
                  }
               }
            }
         }
         else if(solo && !HARD && !EASY && MEDIUM && win == true && lose !=true ){ 
            System.out.println("PAINT THE Winner"); 
            g.drawImage(WinnerBack,0,0,null);
            for (int i = 0; i < 16; i++) {
               for (int j = 0; j < 16; j++) {
                  int temp = gamemed.getPosition(i,j);
                  if(gamemed.Covered(i,j) != true){
                     g.drawImage(img[10],(j*20) + 230, (i*20)+100, this); 
                  }
                  if(temp == -1) {
                     g.drawImage(img[9],(j*20) + 230, (i*20)+100, this); 
                  }
               
               }
            }
         }
         else if(solo && HARD && !EASY && !MEDIUM && lose == true && win!= true){ 
            System.out.println("PAINT THE LOSER"); 
            for (int i = 0; i < 16; i++) {
               for (int j = 0; j < 30; j++) {
                  int temp = gamehard.getPosition(i,j);
                  if(gamehard.Covered(i,j) == true){
                     g.drawImage(img[10],(j*20) + 90, (i*20)+110, this); 
                  }
                  if(temp == -1) {
                     g.drawImage(img[9],(j*20) + 90, (i*20)+110, this); 
                  }
               }
            }
         }
         else if(solo && HARD && !EASY && !MEDIUM && win == true && lose !=true ){ 
            System.out.println("PAINT THE Winner"); 
            g.drawImage(WinnerBack,0,0,null);
            for (int i = 0; i < 16; i++) {
               for (int j = 0; j < 30; j++) {
                  int temp = gamehard.getPosition(i,j);
                  if(gamehard.Covered(i,j) != true){
                     g.drawImage(img[10],(j*20) + 90, (i*20)+110, this); 
                  }
                  if(temp == -1) {
                     g.drawImage(img[9],(j*20) + 90, (i*20)+110, this); 
                  }
               
               }
            }
         }
         
         else if(twoplay && lose == true && win!= true && !solo){ 
           
            System.out.println("PAINT THE LOSER"); 
            for (int i = 0; i < 16; i++) {
               for (int j = 0; j < 30; j++) {
                  int temp = gamehard.getPosition(i,j);
                  if(gamehard.Covered(i,j) == true){
                     g.drawImage(img[10],(j*20) + 90, (i*20)+110, this); 
                  }
                  if(temp == -1) {
                     g.drawImage(img[9],(j*20) + 90, (i*20)+110, this); 
                  }
               }
            }
         }
         else if(twoplay && win == true && lose !=true && !solo){ 
            System.out.println("PAINT THE Winner"); 
            g.drawImage(WinnerBack,0,0,null);
         }
      }
   
      public void setTurn(boolean a){
         if (a==true)
            turn=1;
         else
            turn=2;
      }
      public void mouseClicked(MouseEvent e){ //press and released
      }
      public void mousePressed(MouseEvent e){ //holding and dragging 
         if(e.getButton() == 1){
            if (starting== true){      
               if( solo != true && ai != true && twoplay !=true){ 
                  if(classic.isInside(e)){
                     solo = true; 
                     ai = false; 
                     twoplay = false;
                     repaint();
                  
                  }
                  else if(computer.isInside(e)){
                     solo = false; 
                     ai = true; 
                     twoplay = false;
                     starting = false; 
                     repaint(); 
                  }
                  else if(battle.isInside(e)){
                     solo = false; 
                     ai = false; 
                     twoplay = true;
                     starting = false; 
                     repaint();  
                  }
               }	
                  
               else if (solo == true && ai !=true && twoplay !=true){
                  if(easy.isInside(e) && HARD != true && MEDIUM !=true ){
                     starting=false; 
                     EASY=true;
                     MEDIUM = false;
                     HARD = false;
                     repaint();
                  }
                  else if(hard.isInside(e) && MEDIUM !=true && EASY !=true){
                     starting=false;
                     HARD=true;
                     EASY = false;
                     MEDIUM = false; 
                     repaint();
                  }
                  else if(medium.isInside(e) && EASY != true && HARD != true){
                     starting=false;
                     MEDIUM=true;
                     EASY = false;
                     HARD = false;
                     repaint();
                  }
               }
            
            }
            else if(twoplay == true && solo != true && ai != true){ 
               if(Retrybutt.isInside(e)){
                  gametwo = new TwoGame(); 
                  repaint(); 
               }
               if(turn <  5){
                  System.out.println("TURN < 5"); 
                  int boxLine =0;
                  int boxColumn = 0; 	
                  for(int i = 90 ; i<330; i+=20){
                     for(int j = 120; j < 520 ; j+=20){
                        if(e.getX()>i || e.getX()<(i+20) && e.getY() > j || e.getY()< (j+20)){
                           boxColumn = ((e.getX()-90)/20);
                           boxLine = ((e.getY()-120)/20);	
                           tempLine= boxLine;
                           tempColumn = boxColumn; 
                        }
                     }
                  }
                  System.out.println(boxColumn+ " " + boxLine+ " " + e.getX() + " " + e.getY()); 
                  if(boxColumn > -1 && boxLine < 16 && boxLine > -1 && boxColumn < 30){
                     if(gametwo.loser(boxLine, boxColumn) != true && gametwo.win()!= true){
                        System.out.println("WHYz");                   
                        gametwo.play(boxLine, boxColumn); 
                        turn++;
                        repaint();       
                     }
                     else if(gametwo.win() == true){
                        System.out.println("WHY");                 
                        win = true;
                        repaint(); 
                     }
                     else{
                        lose = true; 
                        System.out.println("WHY NOT"); 
                        repaint(); 
                        
                     }
                     
                  }
                  if(bombing){
                     gametwo.Bomb(boxLine,boxColumn);
                     bombing=false;
                     repaint(); 
                  }
                  if(firing){
                     gametwo.fire(boxLine,boxColumn); 
                     firing=false;
                     repaint(); 
                  }
                  if(Bombbutt.isInside(e)){ 
                     bombing=true;
                     repaint(); 
                  }  
                  if(Firebutt.isInside(e)&&(!Bombbutt.isInside(e))){
                     firing=true;
                     repaint(); 
                  }            	
               }
                      	
               else{
                  System.out.println("TURN > 5"); 
                  if(playercount%2 ==0){
                     playercount++;
                     playeroneturn = false; 
                  }
                  if(playercount%2 !=0){
                     playercount++;
                     playeroneturn = true; 
                  }
                  turn = 0; 
                  changeFlag(); 
                  int boxLine =0;
                  int boxColumn = 0; 	
                  for(int i = 90 ; i<330; i+=20){
                     for(int j = 120; j < 520 ; j+=20){
                        if(e.getX()>i || e.getX()<(i+20) && e.getY() > j || e.getY()< (j+20)){
                           boxColumn = ((e.getX()-90)/20);
                           boxLine = ((e.getY()-120)/20);	
                           tempLine= boxLine;
                           tempColumn = boxColumn; 
                        }
                     }
                     System.out.println(boxColumn+ " " + boxLine+ " " + e.getX() + " " + e.getY()); 
                     if(boxColumn > -1 && boxLine < 16 && boxLine > -1 && boxColumn < 30){
                        if(gametwo.loser(boxLine, boxColumn) != true && gametwo.win()!= true){
                           System.out.println("WHYz");                   
                           gametwo.play(boxLine, boxColumn); 
                           turn++;
                           repaint();       
                        }
                        else if(gametwo.win() == true){
                           System.out.println("WHY");                 
                           win = true;
                           repaint(); 
                        }
                        else{
                           lose = true; 
                           System.out.println("WHY NOT"); 
                           repaint(); 
                        }
                     
                     }
                     if(bombing){
                        gametwo.Bomb(boxLine,boxColumn);
                        bombing=false;
                        repaint(); 
                     }
                     if(firing){
                        gametwo.fire(boxLine,boxColumn); 
                        firing=false;
                        repaint(); 
                     }
                     if(Bombbutt.isInside(e)){ 
                        bombing=true;
                        repaint(); 
                     }  
                     if(Firebutt.isInside(e)&&(!Bombbutt.isInside(e))){
                        firing=true;
                        repaint(); 
                     }            
                  }
               }
            }
            	
                        
             
            else if(EASY == true && MEDIUM != true && HARD != true && win != true && lose !=true){
               if(Retrybutt.isInside(e)){
                  gameeasy = new Game(1); 
                  repaint(); 
               }
            
               int boxLine =0;
               int boxColumn = 0; 	
               for(int i = 300 ; i<435; i+=20){
                  for(int j = 200; j < 325 ; j+=20){
                     if(e.getX()>i || e.getX()<(i+20) && e.getY() > j || e.getY()< (j+20)){
                        boxColumn = ((e.getX()-300)/20);
                        boxLine = ((e.getY()-200)/20);	
                        tempLine= boxLine;
                        tempColumn = boxColumn; 
                     }
                  }  
               }
               System.out.println(boxColumn+ " " + boxLine+ " " + e.getX() + " " + e.getY()); 
               if(boxColumn > -1 && boxColumn < 10 && boxLine > -1 && boxLine < 10){
                  if(gameeasy.loser(boxLine, boxColumn) != true && gameeasy.win()!= true){
                     gameeasy.play(boxLine, boxColumn); 
                     repaint();       
                  }
                  else if( gameeasy.win() == true){
                     win = true;
                     repaint(); 
                  }
                  else{
                  
                     lose = true; 
                     repaint(); 
                  }
               
               }
            
            }
            if(lose == true || win == true){
               if(Retrybutt.isInside(e)){
                  gameeasy = new Game(1);
                  gamemed = new Game(2); 
               	gamehard = new Game(3); 
               	gametwo = new TwoGame();  
                  lose = false; 
               	win = false; 
                  repaint(); 
               }
            }
            else if(EASY != true && MEDIUM == true && HARD != true && win != true && lose !=true){
               if(Retrybutt.isInside(e)){
                  gamemed = new Game(2); 
                  repaint(); 
               }
            
               int boxLine =0;
               int boxColumn = 0; 	
               for(int i = 230 ; i<470; i+=20){
                  for(int j = 100; j < 290 ; j+=20){
                     if(e.getX()>i || e.getX()<(i+20) && e.getY() > j || e.getY()< (j+20)){
                        boxColumn = ((e.getX()-230)/20);
                        boxLine = ((e.getY()-100)/20);	
                        tempLine= boxLine;
                        tempColumn = boxColumn; 
                     }
                  }  
               }
               System.out.println(boxColumn+ " " + boxLine+ " " + e.getX() + " " + e.getY()); 
               if(boxColumn > -1 && boxColumn < 16 && boxLine > -1 && boxLine < 16){
                  if(gamemed.loser(boxLine, boxColumn) != true && gamemed.win()!= true){
                     System.out.println("WHYz");                 
                     gamemed.play(boxLine, boxColumn); 
                     repaint();       
                  }
                  else if(gamemed.win() == true){
                     System.out.println("WHY"); 
                     win = true;
                     repaint(); 
                  }
                  else{
                   
                     lose = true; 
                     repaint(); 
                  }
                 
               }
               
            }
            else if(EASY != true && MEDIUM != true && HARD == true && win != true && lose !=true){
               if(Retrybutt.isInside(e)){
                  gamehard = new Game(3); 
                  repaint(); 
               }
            
               int boxLine =0;
               int boxColumn = 0; 	
               for(int i = 90 ; i<330; i+=20){
                  for(int j = 110; j < 510 ; j+=20){
                     if(e.getX()>i || e.getX()<(i+20) && e.getY() > j || e.getY()< (j+20)){
                        boxColumn = ((e.getX()-90)/20);
                        boxLine = ((e.getY()-110)/20);	
                        tempLine= boxLine;
                        tempColumn = boxColumn; 
                     }
                  }
                  System.out.println(boxColumn+ " " + boxLine+ " " + e.getX() + " " + e.getY()); 
                  if(boxColumn > -1 && boxLine < 16 && boxLine > -1 && boxColumn < 30){
                     if(gamehard.loser(boxLine, boxColumn) != true && gamehard.win()!= true){
                        System.out.println("WHYz");                   
                        gamehard.play(boxLine, boxColumn); 
                        repaint();       
                     }
                     else if(gamehard.win() == true){
                        System.out.println("WHY");                 
                        win = true;
                        repaint(); 
                     }
                     else{
                        lose = true; 
                        System.out.println("WHY NOT"); 
                        repaint(); 
                     }
                  
                  }
               
               }              	
            }
            
         }
         else if(e.getButton() == 3){   
            if (starting== true){      
               if( solo != true && ai != true && twoplay !=true){ 
                  if(classic.isInside(e)){
                     solo = true; 
                     ai = false; 
                     twoplay = false;
                     repaint();
                     
                  }
                  else if(computer.isInside(e)){
                     solo = false; 
                     ai = true; 
                     twoplay = false;
                     repaint(); 
                  }
                  else if(battle.isInside(e)){
                     solo = false; 
                     ai = false; 
                     twoplay = true; 
                     repaint();  
                  }
               }	
               else if (solo == true && ai !=true && twoplay !=true){
                  if(easy.isInside(e) && HARD != true && MEDIUM !=true ){
                     starting=false; 
                     EASY=true;
                     MEDIUM = false;
                     HARD = false;
                     repaint();
                  }
                  else if(hard.isInside(e) && MEDIUM !=true && EASY !=true){
                     starting=false;
                     HARD=true;
                     EASY = false;
                     MEDIUM = false; 
                     repaint(); 
                  }
                  else if(medium.isInside(e) && EASY != true && HARD != true){
                     starting=false;
                     MEDIUM=true;
                     EASY = false;
                     HARD = false;
                     repaint();
                  }
               }	
            }
            else if(EASY == true && MEDIUM != true && HARD != true && win != true && lose !=true){
               int boxLine =0;
               int boxColumn = 0; 	
               for(int i = 300 ; i<435; i+=20){
                  for(int j = 200; j < 325 ; j+=20){
                     if(e.getX()>i || e.getX()<(i+20) && e.getY() > j || e.getY()< (j+20)){
                        boxColumn = ((e.getX()-300)/20);
                        boxLine = ((e.getY()-200)/20);	
                        
                     }
                  }  
               }
               System.out.println(boxLine+ " " + boxColumn+ " " + e.getX() + " " + e.getY()); 
               gameeasy.Mark(boxLine, boxColumn);
               repaint();       
            }
            else if(EASY != true && MEDIUM == true && HARD != true && win != true && lose !=true){
               int boxLine =0;
               int boxColumn = 0; 	
               for(int i = 230 ; i<470; i+=20){
                  for(int j = 100; j < 290 ; j+=20){
                     if(e.getX()>i || e.getX()<(i+20) && e.getY() > j || e.getY()< (j+20)){
                        boxColumn = ((e.getX()-230)/20);
                        boxLine = ((e.getY()-100)/20);	
                     }
                  }  
               }
               System.out.println(boxLine+ " " + boxColumn+ " " + e.getX() + " " + e.getY()); 
               gamemed.Mark(boxLine, boxColumn);
               repaint();             
            }
            else if(EASY != true && MEDIUM != true && HARD == true && win != true && lose !=true){
               int boxLine =0;
               int boxColumn = 0; 	
               for(int i = 90 ; i<330 ; i+=20){
                  for(int j = 110; j < 510 ; j+=20){
                     if(e.getX()>i || e.getX()<(i+20) && e.getY() > j || e.getY()< (j+20)){
                        boxColumn = ((e.getX()-90)/20);
                        boxLine = ((e.getY()-110)/20);	
                     }
                  }
               }
               System.out.println("Mark " + boxLine+ " " + boxColumn+ " " + e.getX() + " " + e.getY()); 
               gamehard.Mark(boxLine, boxColumn);
               repaint();       
            }
            else if(twoplay == true && solo != true && ai != true){
               System.out.println("Come out"); 
               if(turn <  5){
                  int boxLine =0;
                  int boxColumn = 0; 	
                  for(int i = 90 ; i<330; i+=20){
                     for(int j = 120; j < 520 ; j+=20){
                        if(e.getX()>i || e.getX()<(i+20) && e.getY() > j || e.getY()< (j+20)){
                           boxColumn = ((e.getX()-90)/20);
                           boxLine = ((e.getY()-120)/20);	
                           tempLine= boxLine;
                           tempColumn = boxColumn; 
                        }
                     }
                  }
                  System.out.println(boxColumn+ " " + boxLine+ " " + e.getX() + " " + e.getY()); 
                  if(boxColumn > -1 && boxLine < 16 && boxLine > -1 && boxColumn < 30){
                     if(gametwo.loser(boxLine, boxColumn) != true && gametwo.win()!= true){
                        System.out.println("WHYz");                   
                        gametwo.play(boxLine, boxColumn); 
                        turn++;
                        repaint();       
                     }
                     
                  }
               }
                      	
               else{
                  if(playercount%2 ==0){
                     playercount++;
                     playeroneturn = false; 
                  }
                  if(playercount%2 !=0){
                     playercount++;
                     playeroneturn = true; 
                  }
                  turn = 0; 
                  changeFlag(); 
                  int boxLine =0;
                  int boxColumn = 0; 	
                  for(int i = 90 ; i<330; i+=20){
                     for(int j = 120; j < 520 ; j+=20){
                        if(e.getX()>i || e.getX()<(i+20) && e.getY() > j || e.getY()< (j+20)){
                           boxColumn = ((e.getX()-90)/20);
                           boxLine = ((e.getY()-120)/20);	
                           tempLine= boxLine;
                           tempColumn = boxColumn; 
                        }
                     }
                     System.out.println(boxColumn+ " " + boxLine+ " " + e.getX() + " " + e.getY()); 
                     if(boxColumn > -1 && boxLine < 16 && boxLine > -1 && boxColumn < 30){
                        if(gametwo.loser(boxLine, boxColumn) != true && gametwo.win()!= true){
                           System.out.println("WHYz");                   
                           gametwo.play(boxLine, boxColumn); 
                           turn++;
                           repaint();       
                        }
                     }
                     
                  }
               }
            }
         }	
      
      }                       
         
      
      public void mouseReleased(MouseEvent e){ //letting go of the mouse
      
      }
      public void mouseMoved(MouseEvent e){ //Moving the mouse around
      
      }
      public void mouseDragged(MouseEvent e){ // Having he mouse drag by pressing
      
      }
      public void mouseEntered(MouseEvent e){ // Entered a component
      
      }	
      public void mouseExited(MouseEvent e){ //Exited a component 
      
      }
   }
