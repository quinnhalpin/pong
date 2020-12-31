
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Pong Controller
 * @author halpin
 */
public class Pong {
    private final JPanel mainPane;
    private final PongPanel pongPanel;
    private JButton startButton;
    private final PongModel model;
    private Timer timer;
    
    public Pong() {
        Dimension d = new Dimension(500,300);
        model = new PongModel(d);
        pongPanel = new PongPanel(this, model);

        mainPane = new JPanel();
        mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.PAGE_AXIS));
        mainPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        mainPane.add(Box.createRigidArea(new Dimension(0, 5)));
        
        // Creates Start Button
        startButton = createStartButton();
        mainPane.add(startButton);
        
        mainPane.add(pongPanel);
        mainPane.add(Box.createGlue());
        
        // TODO: check is down and is right working Q1
        System.out.println(new Vec(10, 1).isRight() == true);
        System.out.println(new Vec(10, 1).isDown() == false);
        
        // Q2
        System.out.println(new Vec(100, 1).isRight() == false);
        System.out.println(new Vec(100, 1).isDown() == false);
        
        // Q3
        System.out.println(new Vec(190, 1).isRight() == false);
        System.out.println(new Vec(190, 1).isDown() == true);
        
        // Q4
        System.out.println(new Vec(280, 1).isRight() == true);
        System.out.println(new Vec(280, 1).isDown() == true);

    }
    
    private JButton createStartButton() {
        startButton = new javax.swing.JButton();

        startButton.setText("Start");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });
        return startButton;
    }
    
    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
        startGame();
    }
    
    private void startGame() {
        System.out.println("Start Game");
        pongPanel.repaint();
        
        ActionListener listener = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Boundary detection to change model.ball.v
                System.out.println("Degrees");
                Vec ballVec = model.ball.v;
                double deg = ballVec.getDegrees();

//                boolean isDown = Math.cos(Math.toRadians(deg)) > 0;        
//                boolean isRight = Math.sin(Math.toRadians(deg)) > 0;
//                System.out.println(Math.toRadians(deg));
//                System.out.println("Down");
//                System.out.println(isDown);
//                System.out.println("Right");
//                System.out.println(isRight);
                int hitWall = ballHitWall();
                System.out.println(model.ball.v.getDegrees());
                if (hitWall == 0 || hitWall == 2) {
                    System.out.println("Ball scored");
                    timer.removeActionListener(this);
                    return;
                }
                if (hitWall == 1 || hitWall == 3) {
                    System.out.println("hit wall")
                    refractBallHorizontally(hitWall);
                }
                if (ballHitAPlayer()) {
                    refractBallVertically();
                }
                moveBall();
                moveRobot();
                pongPanel.repaint();
            }
        };
        timer = new Timer(100, listener);
        timer.start();
    }
    
    private void moveBall() {
        Point newCenter = model.ball.v.step(model.ball.center);
        model.ball.center = newCenter;
    }
    
    
    private void refractBallVertically() {
        boolean isLeftSide = model.ball.center.x < (int)(model.d.width/2);
        Vec ballVec = model.ball.v;
        System.out.println("Vertical");

        double deg = ballVec.getDegrees();
        boolean isRight = ballVec.isRight();        
        boolean isDown = ballVec.isDown();
        boolean rightDirection = (isLeftSide && isRight) || (!isLeftSide && !isRight);

        if (!rightDirection) {
            double modifier = ((isRight && isDown) || (!isRight && !isDown)) ? -1.0 : 1.0;
            Double newTheta = (deg + modifier*90) % 360;
            model.ball.v = new Vec(newTheta, ballVec.getMagnitude());
        }
    }
    
    /**
     * 1: top wall
     * 3: bottom wall
     * @param hitWall 
     */
    private void refractBallHorizontally(int hitWall) {
        System.out.println("Horizontal");
        System.out.println(hitWall);
        Vec ballVec = model.ball.v;
        double deg = ballVec.getDegrees();
        
        boolean isRight = ballVec.isRight();        
        boolean isDown = ballVec.isDown();
        System.out.println("Down");
        System.out.println(isDown);
        System.out.println("Right");
        System.out.println(isRight);
        boolean rightDirection = (hitWall == 1 && !isDown) || (hitWall == 3 && isDown);

        if (!rightDirection) {
            double modifier = ((isRight && isDown) || (!isRight && !isDown)) ? 1.0 : -1.0;
            System.out.println("modifier: " + modifier);
            Double newTheta = (deg + modifier*90.0) % 360;
            model.ball.v = new Vec(newTheta, ballVec.getMagnitude());    
        }
    }
    
    
    
    /**
     * -1: Didn't hit a wall
     * 0: right wall
     * 1: top wall
     * 2: left wall
     * 3: bottom wall
     * @return 
     */
    private int ballHitWall() {
        int result;
        int radius = model.ball.radius;
        if (model.ball.center.x - radius <= 0) {
            result = 2;
        }
        else if (model.ball.center.x >= model.d.width) {
            result = 0;
        }
        else if ((model.ball.center.y - radius) <= 0) {
            result = 1;
        }
        else if ((model.ball.center.y + radius) >= model.d.height) {
            result = 3;
        }
        else {
            result = -1;
        }
        return result;
    }
    
    
    
    private boolean ballHitAPlayer() {
        Player leftPlayer = model.players.get(0);
        Player rightPlayer = model.players.get(1);
        int ballCenterX = model.ball.center.x;
        int r = model.ball.radius;
        
        Boolean result;
        if ((ballCenterX - r) < leftPlayer.getRightEdge()) {
            result = true;
        }
        else if ((ballCenterX + r > rightPlayer.getLeftEdge())) {
            result = true;
        }
        else {
            result = false;
        }
        
        return result;
    }
    
    private void moveRobot() {
        Player robotPlayer = model.players.get(1);
        Point robotPlayerCenter = new Point(
                robotPlayer.getCenter().x, 
                model.ball.center.y
        );
        robotPlayer.setCenter(robotPlayerCenter);
    }
    
    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Pong");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Pong pong = new Pong();
        pong.mainPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(pong.mainPane);
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
