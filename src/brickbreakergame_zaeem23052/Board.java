/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreakergame_zaeem23052;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.Timer;
import java.lang.Math;
import javax.swing.ImageIcon;

/**
 *
 * @author Muhammad Zaeem Khan
 */
public class Board extends JPanel implements ActionListener{

    private Timer timer;
    private Player player;
    private int width;
    private int height;
    private boolean brickPattern;
    private AbstractFactory powerupFactory;
    private AbstractFactory brickFactory;
    private FactoryCreator factoryCreator;
    private ArrayList<MasterObject> Objectslist;
    private ArrayList<MasterObject> balls;
    private final int DELAY  = 10;
    private int lives;
    private int score;
    private boolean hasGameEnded;
    private boolean hasLost;
    
    Board()
    {
        initBoard();
    }
    
    private void initBoard()
    {
        timer = new Timer(DELAY, this);
        width = 1024;
        height = 768;
        brickPattern = true;
        lives = 3;
        score = 0;
        player = Player.getInstance();
        player.setPaddle(width/2, 768 - 100,"Sprites/50-Breakout-Tiles.png");
        Objectslist = new <MasterObject>ArrayList();
        balls = new <MasterObject>ArrayList();
        factoryCreator = new FactoryCreator();
        powerupFactory = factoryCreator.createFactory("PowerupFactory");
        brickFactory = factoryCreator.createFactory("BrickFactory");
        this.brickPattern();
        hasGameEnded = false;
        hasLost = false;
        
        
        
        
        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);
       
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(new Dimension(width, height));
        timer.start();
        
        balls.add(new Ball(width/2 ,768 - 131,"Sprites/58-Breakout-Tiles.png"));
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        step();
    }
    
    public void step()
    {
        player.getPaddle().move();
        player.getPaddle().hasSpeedPowerupExpired();
        player.getPaddle().hasSizePowerupExpired();
        
        isGameWon();
        
        unbreakableCheck();
        
        isLiveLost();
        
        for(int i =0; i<balls.size(); i++)
        {
            balls.get(i).move();
            ((Ball)balls.get(i)).hasPowerupExpired();
        }
        
        for(int i=0; i<Objectslist.size(); i++)
        {
            Objectslist.get(i).move();
        }
        
        this.ballCollisionsCheck();
        this.powerupFunction();
        
        repaint();
    }
    
    public void reset()
    {
        
        player.getPaddle().reverseSizePowerup();
        player.getPaddle().setDx(0);
        player.getPaddle().moveTo(width/2, 768 - 100);
        balls.add(new Ball(width/2 ,768 - 131,"Sprites/58-Breakout-Tiles.png"));
        lives--;
        
        for(int i = 0; i<balls.size(); i++)
        {
            ((Ball)balls.get(i)).reverseChanges();
            balls.get(i).setDx(0);
        }
        
        
        if(lives == 0)
        {
            hasGameEnded = true;
            hasLost = true;
        }
    }
    
    public void gameEndPaint()
    {
        String displayText;
        
        if(hasLost)
        {
            displayText = "GameOver.";
        }
        else
        {
            displayText = "Congratulations, You Won.";
        }
        
        WinLost temp = new WinLost(displayText);
        this.hide();
        temp.show();
        
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        
        if(hasGameEnded)
        {
            gameEndPaint();
        }
        else
        {
            g.setColor(getBackground());
            g.clearRect(0, 0, width, height);
            
            Font small = new Font("Helvetica", Font.BOLD, 25);
            FontMetrics fontMetrics = getFontMetrics(small);
            g.setFont(small);
            g.setColor(Color.YELLOW);
            
            
            ImageIcon image = new ImageIcon("Sprites/Background.jpg");
            g.drawImage(image.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
            
            g.drawString("Total Score : " + score, 810, 40);
            g.drawString("Lives left : " + lives, 810, 70);

            player.getPaddle().paintComponent(g2d);

            for(int i =0; i<balls.size(); i++)
            {
                balls.get(i).paintComponent(g2d);
            }

            for(int i = 0; i<Objectslist.size(); i++)
            {
                Objectslist.get(i).paintComponent(g2d);
            }
        }
    }
    
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) 
        {
            player.getPaddle().keyReleased(e);
            
            for(int i = 0; i<balls.size(); i++)
            {
                if(balls.get(i).getBounds().intersects(player.getPaddle().getBounds()))
                {
                    balls.get(i).keyReleased(e);
                }
            }
        }

        @Override
        public void keyPressed(KeyEvent e) 
        {
            player.getPaddle().keyPressed(e);
          
            for(int i = 0; i<balls.size(); i++)
            {
                if(balls.get(i).getBounds().intersects(player.getPaddle().getBounds()))
                {
                    balls.get(i).keyPressed(e);
                }
            }
            
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
            {
                PauseGame temp = new PauseGame(getBoard());
                temp.show();
                hideGame();
                
            }
        }
    } 
    
    public void brickPattern()
    {
        for(int i = 100; i<=484; i+= 32)
        {
            for(int j = 70; j<=934; j+= 96)
            {
                if(brickPattern)
                {
                    int type = (int)(Math.random()*4) + 1;
                    MasterObject brick;
                    
                    switch (type) 
                    {
                        case 1:
                            brick = brickFactory.createObject("SingleHitBrick");
                            break;
                        case 2:
                            brick = brickFactory.createObject("DoubleHitBrick");
                            break;
                        case 3:
                            brick = brickFactory.createObject("TripleHitBrick");
                            break;
                        default:
                            brick = brickFactory.createObject("UnbreakableBrick");
                            break;
                    }
                    
                    brick.moveTo(j, i);
                    Objectslist.add(brick);

                }
                
                brickPattern = !brickPattern;
            }
            
            brickPattern = !brickPattern;
        }
    }
    
    public void ballCollisionsCheck()
    {
        for(int i = 0; i<balls.size(); i++)
        {
            Ball ball = (Ball)balls.get(i);
            
            if(ball.isFireball())
            {
                for(int x = 0; x<Objectslist.size(); x++)
                {
                    if(Objectslist.get(x) instanceof Brick)
                    {
                        if(Objectslist.get(x).getBounds().intersects(ball.getBounds()))
                        {
                            MasterObject temp = createPowerup();
                            temp.setX(Objectslist.get(x).getX());
                            temp.setY(Objectslist.get(x).getY());
                            Objectslist.remove(x);
                            score++;
                            Objectslist.add(temp);
                        }
                    }
                }
            }
            
            if(ball.getY() - ball.getHeight()/2<=0)
            {
                ball.setDy(-ball.getDy());
            }
            else if(ball.getBounds().intersects(player.getPaddle().getBounds()))
            {
                if(ball.getX()>player.getPaddle().getX())
                {
                    ball.setDx(+3);
                }
                else if(ball.getX() < player.getPaddle().getX())
                {
                    ball.setDx(-3);
                }
                
                ball.setDy(-ball.getDy());
            }
            else if(ball.getX() + ball.getWidth()/2 + ball.getDx() + 15 >= 1024 || ball.getX() - ball.getWidth()/2 + ball.getDx() <= 0)
            {
                ball.setDx(-ball.getDx());
            }
            
            for(int j =0; j<Objectslist.size(); j++)
            {
                if(ball.getBounds().intersects(Objectslist.get(j).getBounds()))
                {
                    
                    if(Objectslist.get(j) instanceof SingleHitBrick)
                    {
                        MasterObject powerup = createPowerup();
                        powerup.moveTo(Objectslist.get(j).getX(), Objectslist.get(j).getY());
                        Objectslist.remove(j);
                        Objectslist.add(powerup);
                        score++;
                        ball.setDy(-ball.getDy());
                    }
                    else if(Objectslist.get(j) instanceof DoubleHitBrick)
                    {
                        if(((DoubleHitBrick)Objectslist.get(j)).isHit())
                        {
                            MasterObject powerup = createPowerup();
                            powerup.moveTo(Objectslist.get(j).getX(), Objectslist.get(j).getY());
                            Objectslist.remove(j);
                            Objectslist.add(powerup);
                            score++;
                        }
                        else
                        {
                            ((DoubleHitBrick)Objectslist.get(j)).hit();
                            ((DoubleHitBrick)Objectslist.get(j)).damageBrick();
                            
                        }
                        
                        ball.setDy(-ball.getDy());
                    }
                    else if(Objectslist.get(j) instanceof TripleHitBrick)
                    {
                        if(((TripleHitBrick)Objectslist.get(j)).isHit())
                        {
                            if(((TripleHitBrick)Objectslist.get(j)).isDoubleHit())
                            {
                                MasterObject powerup = createPowerup();
                                powerup.moveTo(Objectslist.get(j).getX(), Objectslist.get(j).getY());
                                Objectslist.remove(j);
                                score++;
                                Objectslist.add(powerup);
                            }
                            else
                            {
                                ((TripleHitBrick)Objectslist.get(j)).doubleHit();
                                ((TripleHitBrick)Objectslist.get(j)).damageBrick();
                            }
                        }
                        else
                        {
                            ((TripleHitBrick)Objectslist.get(j)).hit();
                        }
                        
                        ball.setDy(-ball.getDy());
                    }
                    
                    else if(Objectslist.get(j) instanceof UnbreakableBrick)
                    {
                        ball.setDy(-ball.getDy());
                    }
                }
            }
        }
    }
    
    public MasterObject createPowerup()
    {
        int rand = (int)(Math.random() *7) + 1;
        MasterObject powerup;
        
        switch(rand)
        {
            case 1:
                powerup = powerupFactory.createObject("SpeedDecreasePowerup");
                break;
            case 2:
                powerup = powerupFactory.createObject("MultiBallPowerup");
                break;
            case 3:
                powerup = powerupFactory.createObject("FireballPowerup");
                break;    
            case 4:
                powerup = powerupFactory.createObject("SizeDecreasePowerup");
                break; 
            case 5:
                powerup = powerupFactory.createObject("SizeIncreasePowerup");
                break;
            case 6:
                powerup = powerupFactory.createObject("SpeedIncreasePowerup");
                break;
            default:
                powerup = powerupFactory.createObject("ExtraLifePowerup");
                break;    
        }
        
        return powerup;
    }
    
    public void powerupFunction()
    {
        for(int i = 0; i<Objectslist.size(); i++)
        {
            if(Objectslist.get(i).getBounds().intersects(player.getPaddle().getBounds()))
            {
                MasterObject temp = Objectslist.get(i);
                
                if(temp instanceof ExtraLifePowerup)
                {
                    lives++;
                    Objectslist.remove(temp);
                }
                else if(temp instanceof SpeedIncreasePowerup)
                {
                    player.getPaddle().speedPowerup(1);
                    Objectslist.remove(temp);
                }
                else if(temp instanceof SpeedDecreasePowerup)
                {
                    player.getPaddle().speedPowerup(0);
                    Objectslist.remove(temp);
                }
                else if(temp instanceof SizeIncreasePowerup)
                {
                    player.getPaddle().increaseSize();
                    Objectslist.remove(temp);
                }
                else if(temp instanceof SizeDecreasePowerup)
                {
                    player.getPaddle().decreaseSize();
                    Objectslist.remove(temp);
                }
                else if(temp instanceof FireballPowerup)
                {
                    for(int x = 0; x<balls.size(); x++)
                    {
                        ((Ball)balls.get(x)).makeFireball();
                    }
                    Objectslist.remove(temp);
                }
                else if(temp instanceof MultiBallPowerup)
                {
                    Objectslist.remove(temp);
                    Ball powerupBall = new Ball(balls.get(0).getX() + 5, balls.get(0).getY(), "Sprites/58-Breakout-Tiles.png");
                    powerupBall.setDx(+3);
                    powerupBall.setDy(-5);
                    Ball powerupBallTwo = new Ball(balls.get(0).getX() - 5, balls.get(0).getY(), "Sprites/58-Breakout-Tiles.png");
                    powerupBallTwo.setDx(-3);
                    powerupBallTwo.setDy(-5);
                    balls.add(powerupBall);
                    balls.add(powerupBallTwo);
                    
                }
            }
            
        }
        
    }
    
    public void isLiveLost()
    {
        
        for(int i =0; i<balls.size(); i++)
        {
            if(balls.get(i).getY()-balls.get(i).getHeight()>768)
            {
                balls.remove(i);
            }
            
        }
        
        if(balls.isEmpty())
        {
            reset();
        }
    }
    
    public void unbreakableCheck()
    {
        boolean checker = true;
        
        for(int i =0; i<Objectslist.size(); i++)
        {
            if(Objectslist.get(i) instanceof Brick)
            {
                if(Objectslist.get(i) instanceof UnbreakableBrick)
                {
                    checker = checker && true;
                }
                else
                {
                    checker = checker && false;
                }
            }
        }
        
        if(checker == true)
        {
            
            for(int i =0; i<balls.size(); i++)
            {
                Ball ball = (Ball)balls.get(i);
                ball.makeFireball();
            }
        }
    }
    
    public void isGameWon()
    {
        boolean checker = true;
        
        for(int i = 0; i<Objectslist.size(); i++)
        {
            if(Objectslist.get(i) instanceof Brick)
            {
                checker = checker && false;
            }
            else
            {
                checker = checker && true;
            }
        }
        
        hasGameEnded = checker;
    }
    
    public Board getBoard()
    {
        return this;
    }
    
    public void hideGame()
    {
        this.setVisible(false);
        timer.stop();
    }
   
    public void resumeGame()
    {
        this.setVisible(true);
        timer.start();
    }
}
