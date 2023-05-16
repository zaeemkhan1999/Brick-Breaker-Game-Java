/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreakergame_zaeem23052;

/**
 *
 * @author Muhammad Zaeem Khan
 */
public class Player {
    
    private static Player player;
    private Paddle paddle;
    
    private Player()
    {
        
    }
    
    public static Player getInstance()
    {
        if(player==null)
        {
            player = new Player();
        }
        
        return player;
    }
    
    public void setPaddle(int x, int y, String path)
    {
        //paddle = new Paddle(1024/2 - paddle.getWidth()/2, paddle.getHeight()*2,"Sprites/50-Breakout-Tiles.png");
        paddle = new Paddle(x,y,path);
    }
    
    public Paddle getPaddle()
    {
        return paddle;
    }
    
}
