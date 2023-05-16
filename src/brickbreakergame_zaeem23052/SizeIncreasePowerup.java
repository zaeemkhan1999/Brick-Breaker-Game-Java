/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreakergame_zaeem23052;

import java.awt.event.KeyEvent;

/**
 *
 * @author Muhammad Zaeem Khan
 */
public class SizeIncreasePowerup extends MasterObject {

    public SizeIncreasePowerup(int x, int y, String path) 
    {
        super(x, y, path);
        this.setDy(2);
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
        
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
        
    }    
}
