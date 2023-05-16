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
public class TripleHitBrick extends Brick{
    
    private boolean isHit;
    private boolean isDoubleHit;
    
    public TripleHitBrick(int x, int y, String path) 
    {
        super(x, y, path);
        isHit = false;
        isDoubleHit = false;
    }
    
    public boolean isHit()
    {
        return isHit;
    }
    
    public boolean isDoubleHit()
    {
        return isDoubleHit;
    }
    
    public void hit()
    {
        isHit = true;
    }
    
    public void doubleHit()
    {
        isDoubleHit = true;
    }
    
    public void damageBrick()
    {
        this.setImagePath(this.getImagePath()+"-Crack");
        ImageIcon imageIcon = new ImageIcon(this.getImagePath() + ".jpg");
        this.setImage(imageIcon.getImage());
    }
    
    
}
