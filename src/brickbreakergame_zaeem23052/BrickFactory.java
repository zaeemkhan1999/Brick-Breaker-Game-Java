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
public class BrickFactory extends AbstractFactory{

    @Override
    public MasterObject createObject(String object) 
    {
        Brick brick;

        int imageNumber = (int)(Math.random()*10) + 1;
        String imagePath = "Sprites/Brick-" + imageNumber;
        
        if(object.equalsIgnoreCase("SingleHitBrick"))
        {
            brick = new SingleHitBrick(0,0,imagePath + ".jpg");
            brick.setImagePath(imagePath);
        }
        else if(object.equalsIgnoreCase("DoubleHitBrick"))
        {
            brick = new DoubleHitBrick(0,0,imagePath + ".jpg");
            brick.setImagePath(imagePath);
        }
        else if(object.equalsIgnoreCase("TripleHitBrick"))
        {
            brick = new TripleHitBrick(0,0,imagePath + ".jpg");
            brick.setImagePath(imagePath);
        }
        else if(object.equalsIgnoreCase("UnbreakableBrick"))
        {
            brick = new UnbreakableBrick(0,0,imagePath + ".jpg");
            brick.setImagePath(imagePath);
        }
        else
        {
            brick = null;
        }
        
        return brick;
    }
    
}
