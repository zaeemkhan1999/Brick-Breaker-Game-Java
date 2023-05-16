/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreakergame_zaeem23052;

import javax.swing.ImageIcon;

/**
 *
 * @author Muhammad Zaeem Khan
 */
public class DoubleHitBrick extends Brick{
    
    private boolean isHit;
    
    public DoubleHitBrick(int x, int y, String path) 
    {
        super(x, y, path);
        isHit = false;
    }
    
    public void damageBrick()
    {
        this.setImagePath(this.getImagePath()+"-Crack");
        ImageIcon imageIcon = new ImageIcon(this.getImagePath() + ".jpg");
        this.setImage(imageIcon.getImage());
        
    }
    
    public boolean isHit()
    {
        return isHit;
    }
    
    public void hit()
    {
        isHit = true;
    }
    
}
