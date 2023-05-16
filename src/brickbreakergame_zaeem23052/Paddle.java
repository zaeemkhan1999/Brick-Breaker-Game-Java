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
public class Paddle extends MasterObject{

    private long sizePowerupCount;
    private long speedPowerupCount;
    private boolean sizePowerupFlag;
    private boolean speedPowerupFlag;
    private int minusMovement, plusMovement;
    
    public Paddle(int x, int y, String path) 
    {
        super(x, y, path);
        sizePowerupCount = 0;
        speedPowerupCount = 0;
        minusMovement = -10;
        plusMovement = 10;
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
            this.setDx(minusMovement);
        }
        else if(e.getKeyCode()==KeyEvent.VK_RIGHT)
        {
            this.setDx(plusMovement);
        }
    }
    
    @Override
    public void move()
    {  
        if(this.getX() + this.getWidth()/2 + this.getDx() + 15 <= 1024 && this.getX() - this.getWidth()/2 + this.getDx() >= 0)
        {
            this.setX(this.getX()+this.getDx());
        }
    }
    
    public void speedPowerup(int i)
    {
        speedPowerupCount = 0;
        speedPowerupFlag = true;
        
        if(i == 1)
        {
            minusMovement = -20;
            plusMovement = 20;
        }
        else
        {
            minusMovement = -5;
            plusMovement = 5;
        }
    }
    
    public void hasSpeedPowerupExpired()
    {
        if(speedPowerupCount>=1000 && speedPowerupFlag)
        {
            this.setDx(10);
            minusMovement = -10;
            plusMovement = 10;
            speedPowerupFlag = false;
            speedPowerupCount = 0;
        }
        else
        {
            speedPowerupCount++;
        }
    }
    public void hasSizePowerupExpired()
    {
        if(sizePowerupCount>=1000 && sizePowerupFlag)
        {
            reverseSizePowerup();
        }
        else
        {
            sizePowerupCount++;
        }
    }
    
    public void reverseSizePowerup()
    {
        sizePowerupFlag = false;
        sizePowerupCount = 0;
        this.setPath("Sprites/50-Breakout-Tiles.png");
        ImageIcon imageIcon = new ImageIcon(this.getPath());
        this.setImage(imageIcon.getImage());
        this.setHeight(this.getImage().getHeight(null));
        this.setWidth(this.getImage().getWidth(null));
    }
    
    public void increaseSize()
    {
        sizePowerupCount = 0;
        sizePowerupFlag = true;
        this.setPath("Sprites/150-Breakout-Tiles.png");
        ImageIcon imageIcon = new ImageIcon(this.getPath());
        this.setImage(imageIcon.getImage());
        this.setHeight(this.getImage().getHeight(null));
        this.setWidth(this.getImage().getWidth(null));
    }
    
    public void decreaseSize()
    {
        sizePowerupCount = 0;
        sizePowerupFlag = true;
        this.setPath("Sprites/100-Breakout-Tiles.png");
        ImageIcon imageIcon = new ImageIcon(this.getPath());
        this.setImage(imageIcon.getImage());
        this.setHeight(this.getImage().getHeight(null));
        this.setWidth(this.getImage().getWidth(null));
    }
    
    
    
}
