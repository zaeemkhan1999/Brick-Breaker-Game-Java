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
public class PowerupFactory extends AbstractFactory{

    @Override
    public MasterObject createObject(String object) 
    {
        if(object.equalsIgnoreCase("SizeIncreasePowerup"))
        {
            return new SizeIncreasePowerup(0,0,"Sprites/47-Breakout-Tiles.png");
        }
        else if(object.equalsIgnoreCase("SizeDecreasePowerup"))
        {
            return new SizeDecreasePowerup(0,0,"Sprites/46-Breakout-Tiles.png");
        }
        else if(object.equalsIgnoreCase("SpeedDecreasePowerup"))
        {
            return new SpeedDecreasePowerup(0,0,"Sprites/41-Breakout-Tiles.png");
        }
        else if(object.equalsIgnoreCase("SpeedIncreasePowerup"))
        {
            return new SpeedIncreasePowerup(0,0,"Sprites/42-Breakout-Tiles.png");
        }
        else if(object.equalsIgnoreCase("MultiBallPowerup"))
        {
            return new MultiBallPowerup(0,0,"Sprites/43-Breakout-Tiles.png");
        }
        else if(object.equalsIgnoreCase("FireballPowerup"))
        {
            return new FireballPowerup(0,0,"Sprites/44-Breakout-Tiles.png");
        }
        else if(object.equalsIgnoreCase("ExtraLifePowerup"))
        {
            return new ExtraLifePowerup(0,0,"Sprites/49-Breakout-Tiles.png");
        }
        else
        {
            return null;
        }
    }
    
}
