/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreakergame_zaeem23052;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

/**
 *
 * @author Muhammad Zaeem Khan
 */
public abstract class MasterObject {
    
    private int x;
    private int y;
    private Image image;
    private int width;
    private int height;
    private int dx;
    private int dy;
    private String path; 
    
    MasterObject(int x, int y, String path)
    {
        this.x = x;
        this.y = y;
        this.path = path;
        init();
    }
    
    private void init()
    {
        ImageIcon imageIcon = new ImageIcon(path);
        image = imageIcon.getImage();
        width = image.getWidth(null);
        height = image.getHeight(null);
    }
    
    public void move()
    {  
       this.x += dx;
       this.y += dy;     
    }
    
    public Rectangle getBounds()
    {
        return new Rectangle( x-width/2, y-height/2, width, height);
    }
    
    public void moveTo(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public void paintComponent(Graphics2D g)  
    {       
        g.drawImage(image, x - image.getWidth(null)/2, y - image.getHeight(null)/2, null);
        g.setColor(new Color(255,0,0));
    }
    
    public int getDx() 
    {
        return dx;
    }

    public void setDx(int dx) 
    {
        this.dx = dx;
    }

    public int getDy() 
    {
        return dy;
    }

    public void setDy(int dy) 
    {
        this.dy = dy;
    }
    
    public int getHeight() 
    {
        return height;
    }
    
    public int getWidth() 
    {
        return width;
    }
    
    public int getX()
    {
        return x;
    }
    
    public void setX(int x)
    {
        this.x = x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public void setY(int y)
    {
        this.y = y;
    }
    
    public String getPath()
    {
        return path;
    }
    
    public void setImage(Image image)
    {
        this.image = image;
    }
    
    public void setPath(String path)
    {
        this.path = path;
    }
    
    public Image getImage()
    {
        return image;
    }
    
    public void setHeight(int height)
    {
        this.height = height;
    }
    
    public void setWidth(int width)
    {
        this.width = width;
    }
    public abstract void keyReleased(KeyEvent e);
    
    public abstract void keyPressed(KeyEvent e);
}
