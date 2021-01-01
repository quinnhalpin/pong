
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
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
        
        JButton startButton = createButton("Start");
        mainPane.add(startButton);
        JButton pauseButton = createButton("Pause");
        mainPane.add(pauseButton);
        JButton resetButton = createButton("Reset");
        mainPane.add(resetButton);
        
        mainPane.add(pongPanel);
        mainPane.add(Box.createGlue());
        
        
    }

    
    
    private JButton createButton(String label) {
        JButton b = new javax.swing.JButton();

        b.setText(label);
        b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActionPerformed(evt, label);
            }
        });
        return b;
    }
    
    private void buttonActionPerformed(java.awt.event.ActionEvent evt, String label) {                                            
        // TODO add your handling code here:
        if (label.equals("Start")) {
            startGame();
        }
        else if (label.equals("Pause")) {
            pauseGame();
        }
        else {
            resetGame();
        }
    }
    
    private void startGame() {
        System.out.println("Start Game");
        pongPanel.repaint();
        
        ActionListener listener = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Boundary detection to change model.ball.v
                Vec ballVec = model.ball.v;
                double deg = ballVec.getDegrees();

                int hitWall = ballHitWall();
                System.out.println(model.ball.v.getDegrees());
                if (hitWall == 0 || hitWall == 2) {
                    System.out.println("Ball scored");
                    timer.removeActionListener(this);
                    return;
                }
                if (hitWall == 1 || hitWall == 3) {
                    System.out.println("hit wall");
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
        timer = new Timer(50, listener);
        timer.start();
    }
    
    private void pauseGame() {
        timer.stop();
    }
    
    private void resetGame() {
        timer.stop();
        model.reset();
        pongPanel.repaint();
    }
    
    private void moveBall() {
        Point newCenter = model.ball.v.step(model.ball.center);
        model.ball.center = newCenter;
    }
    
    
    private void refractBallVertically() {
        boolean isLeftSide = model.ball.center.x < (int)(model.d.width/2);
        Vec ballVec = model.ball.v;

        double deg = ballVec.getDegrees();
        boolean isRight = ballVec.isRight();        
        boolean isDown = ballVec.isDown();
        boolean rightDirection = (isLeftSide && isRight) || (!isLeftSide && !isRight);

        if (!rightDirection) {
            model.ball.v = model.ball.v.refractVertically();
        }
    }
    
    /**
     * 1: top wall
     * 3: bottom wall
     * @param hitWall 
     */
    private void refractBallHorizontally(int hitWall) {
        Vec ballVec = model.ball.v;
        double deg = ballVec.getDegrees();
        
        boolean isRight = ballVec.isRight();        
        boolean isDown = ballVec.isDown();
        boolean rightDirection = (hitWall == 1 && !isDown) || (hitWall == 3 && isDown);

        if (!rightDirection) {
            double change = 90;
            
            if (deg == 90 || deg == 270) {
                // TODO: keep?
                int minorChange = (int) new Random().nextDouble()*10 + 1;
                change = 180 + minorChange;
                Double newTheta = (deg + change) % 360;
                model.ball.v = new Vec(newTheta, ballVec.getMagnitude());    
            }
            else {
                model.ball.v = model.ball.v.refractHorizontally();
            }
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
    
    private boolean goodHeight(Player player) {
        int ballCenterY = model.ball.center.y;
        int r = model.ball.radius;
        return ((ballCenterY + r) >= player.getTopEdge())
                && ((ballCenterY - r) <= player.getBottomEdge());
    }
    
    
    private boolean ballHitAPlayer() {
        Player leftPlayer = model.players.get(0);
        Player rightPlayer = model.players.get(1);
        
        int ballCenterX = model.ball.center.x;
        int r = model.ball.radius;
        
        boolean hitLeftPlayer = (goodHeight(leftPlayer) && ((ballCenterX - r) < leftPlayer.getRightEdge()));
        boolean hitRightPlayer = (goodHeight(rightPlayer) && ((ballCenterX + r > rightPlayer.getLeftEdge())));

        return hitLeftPlayer || hitRightPlayer;
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
