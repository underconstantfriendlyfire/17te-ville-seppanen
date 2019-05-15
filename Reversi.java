package reversi;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

public class Reversi extends Canvas implements MouseListener {

    private static final int width = 300;
    private static final int height = 300;
    private static final int boxSize = 30;
    static int board[][] = new int[8][8];
    int playerTurn = 1; //1 = svart 2 = vit  
    int other = 2;
    int blackPoints = 0;
    int whitePoints = 0;
    boolean canMove = false;
    public Reversi() {

        board[3][3] = 2;
        board[3][4] = 1;
        board[4][3] = 1;
        board[4][4] = 2;
        checkPossibleMoves(); 
        checkScore();
        addMouseListener(this);
        
    }
   
    
    
    public void paint(Graphics g) {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int posX = i * 30;
                int posY = j * 30;
                switch (board[i][j]) {
                    case 0:
                        g.setColor(Color.GREEN);
                        g.fill3DRect(posX, posY, boxSize, boxSize, true);
                        break;
                    case 1:
                        g.setColor(Color.BLACK);
                        g.fill3DRect(posX, posY, boxSize, boxSize, true);
                        break;
                    case 2:
                        g.setColor(Color.WHITE);
                        g.fill3DRect(posX, posY, boxSize, boxSize, true);
                        break;
                    case 3:
                        g.setColor(Color.GREEN);
                        g.fill3DRect(posX, posY, boxSize, boxSize, true);
                        g.setColor(Color.RED);
                        g.fill3DRect(posX+7, posY+7, boxSize-14, boxSize-14, true);
                        break;
                    default:
                        break;
                }
            }
        }
        
        g.setColor(Color.BLACK);
        g.setFont(new Font("Consolas", Font.PLAIN, 22)); 
        g.drawString("Black: " + blackPoints, 1, 280); 
        g.drawString("White: " + whitePoints, 120, 280);
     
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        JFrame win = new JFrame("Reversi");

        Container c = win.getContentPane();

        c.setPreferredSize(new Dimension(230, 300));
        win.pack();
        win.setResizable(false);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = 0;
            }
        }
        Reversi canvas = new Reversi();
        win.add(canvas);
        win.setVisible(true);
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int posX = e.getX();
        int posY = e.getY();
        int gridPosX = posX / boxSize;
        int gridPosY = posY / boxSize;
        //  board[gridPosX][gridPosY] = board[gridPosX][gridPosY] == 1 ? 0 : 1;

// 1= svart 2=vit

       
        
        


        if (board[gridPosX][gridPosY] == 0||board[gridPosX][gridPosY] == 3) {
            if (legalMove(gridPosX,gridPosY) == true) {

                if (playerTurn == 1) {
                    board[gridPosX][gridPosY] = 1;
                    playerTurn = 2;
                    other = 1;
                } else {
                    board[gridPosX][gridPosY] = 2;
                    playerTurn = 1;
                    other = 2;
                    
                    
                }
                  checkPossibleMoves();
            }
        } else {

            

        }

        System.out.println(posX + "." + posY);
        checkScore();
        
        repaint();
        
        
        passCheck();
        
        
    }
    
    void passCheck(){
        
        if(canMove==false){
            
            if (playerTurn == 1){
                playerTurn = 2;
                     other = 1;
            }
            else {
                    
                    playerTurn = 1;
                    other = 2;
                           
                }
            System.out.print("pass");
            
       }
    }
    
    
    
    
void resetRed(){
     for(int x = 0; x<8;x++){
          for(int y = 0; y<8;y++){
        
              if(board[x][y]==3){
                  board[x][y]=0;
              }
              
          }
        }
}
    
    boolean legalMove(int x, int y){
        
        for(int i = -1; i <= 1; i++){
           
            for(int j = -1; j<=1;j++){
                if(i == 0 && j == 0){
                    
                }
                else{
                    try{
                   
                      int dist = 1;
                       while(board[x+(dist*i)][y+(dist*j)] == other){
                       
                       dist++;
                       
                           }
                       if (dist>1){
                       if(board[x+(dist*i)][y+(dist*j)] == playerTurn){
                           
                       flipTiles(x,y); 
                       resetRed();
                           
                       return true;
                       }
                       }
                    
                  }
                  catch(Exception ex){    }
                }
            }
        }
        System.out.print("hihi");
        return false;
    }
    
    void checkScore() {
        blackPoints = 0;
        whitePoints = 0;
        for(int x = 0; x<8;x++){
          for(int y = 0; y<8;y++){
              if(board[x][y] == 1){
                  blackPoints++;
              }
              
              else if(board[x][y] == 2){
                  whitePoints++;
              }
              
          }
        }
        
    }
    
    void checkPossibleMoves() {
    canMove = false;
      for(int x = 0; x<8;x++){
          for(int y = 0; y<8;y++){
              if(board[x][y] == 0 ||board[x][y] == 3){
                  for(int i = -1; i <= 1; i++){
           
            for(int j = -1; j<=1;j++){
                if(i == 0 && j == 0){
                    
                }
                else{
                    try{
                   
                      int dist = 1;
                       while(board[x+(dist*i)][y+(dist*j)] == other){
                       
                       dist++;
                       
                           }
                       if (dist>1){
                       if(board[x+(dist*i)][y+(dist*j)] == playerTurn){
                           
                           board[x][y] = 3;
                           
                           canMove = true;
                       }
                       }
                    
                  }
                  catch(Exception ex){    }
                }
            }
        }
                  
              }
          }
      }
        
        
    }
    
    
    
    void flipTiles(int x,int y){
         for(int i = -1; i <= 1; i++){
           
            for(int j = -1; j<=1;j++){
                
                if(i == 0 && j == 0){
                    
                }
                else{
                    try{
                   
                      int dist = 1;
                       while(board[x+(dist*i)][y+(dist*j)] == other){
                       
                       dist++;
                       
                           }
                       if (dist>1){
                       if(board[x+(dist*i)][y+(dist*j)] == playerTurn){
                           
                           for(int k=1; k<dist;k++){
                               board[x+(k*i)][y+(k*j)] = playerTurn;
                           }
                           
                       
                       }
                       }
                    
                  }
                  catch(Exception ex){    }
                }
                
            }
            
         }
         
    }
    //passa om man inte kan göra drag
    //avsluta om ingen kan göra drag
    //visa vinnare eller ovavgjort
    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}