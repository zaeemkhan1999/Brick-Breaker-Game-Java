/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreakergame_zaeem23052;

import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

/**
 *
 * @author Muhammad Zaeem Khan

 */
public class Ball extends MasterObject{
    
    private long powerupCount;
    private boolean fireball;
    
    public Ball(int x, int y, String path) 
    {
        super(x, y, path);
        powerupCount = 0;
        fireball = false;
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
        if(e.getKeyCode()==KeyEvent.VK_LEFT)
        {
            this.setDx(0);
        }
        else if(e.getKeyCode()==KeyEvent.VK_RIGHT)
        {
            this.setDx(0);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
        if(e.getKeyCode()==KeyEvent.VK_LEFT)
        {
            this.setDx(-10);
        }
        else if(e.getKeyCode()==KeyEvent.VK_RIGHT)
        {
            this.setDx(+10);
        }
        else if(e.getKeyCode()==KeyEvent.VK_SPACE)
        {
            this.setDy(-5);
        }
    }    
    
    public boolean isFireball()
    {
        return fireball;
    }
    
    @Override
    public void move()
    {  
        if(this.getX() + this.getWidth()/2 + this.getDx() + 15 <= 1024 && this.getX() - this.getWidth()/2 + this.getDx() >= 0)
        {
            this.setX(this.getX()+this.getDx());
            this.setY(this.getY()+this.getDy());
        }
    }
    
    public void makeFireball()
    {
        powerupCount = 0;
        fireball = true;
        
        this.setPath("Sprites/70-Breakout-Tiles.png");
        ImageIcon imageIcon = new ImageIcon(this.getPath());
        this.setImage(imageIcon.getImage());
    }
    
    public void hasPowerupExpired()
    {
        if(fireball && powerupCount>=1000)
        {
           reverseChanges();
        }
        else
        {
            powerupCount++;
        }
    }
    
    public void reverseChanges()
    {
        this.setPath("Sprites/58-Breakout-Tiles.png");
        ImageIcon imageIcon = new ImageIcon(this.getPath());
        this.setImage(imageIcon.getImage());
        fireball = false;
        powerupCount = 0;
    }
    
}
