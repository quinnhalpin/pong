
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
    private JLabel scoreLabel;
    private Timer timer;
   
    public Pong() {
        Dimension d = new Dimension(500,300);
        model = new PongModel(d);
        pongPanel = new PongPanel(this, model);
        mainPane = new JPanel();
    }
    
    /**
     * Move player safely within the bounds of the board
     * stepSize < 0: move down (up on board)
     * stepSize > 0: move up (down on board)
     * @param player
     * @param stepSize
     */
    public void movePlayerVertically(Player player, int stepSize) {
        double d = (stepSize > 0) ? 90 : 270;
        player.setAcc(new Vec(d, Math.abs(stepSize)));
    }
    
    public void movePlayerInsideBoard(Player player) {
        // Check if player is still in bounds
        int halfPlayerHeight = player.getHeight()/2;
        int boardHeight = model.board.getHeight();
        if ((player.getPos().y - halfPlayerHeight) < 0) {
            player.setPos(player.getPos().x, halfPlayerHeight);
        }
        else if ((player.getPos().y + halfPlayerHeight) > boardHeight) {
            player.setPos(player.getPos().x, boardHeight - halfPlayerHeight);
        }
    }
    
    public void addKeyStrokeActions() {
                
        Action upAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (gameIsRunning()) {
                    movePlayerVertically(model.players.get(0), -10);
                }
            }
        };
        Action downAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (gameIsRunning()) {
                    movePlayerVertically(model.players.get(0), 10);
                }
            }
        };
        
        SingleKeyAction up = new SingleKeyAction("UP", upAction);
        SingleKeyAction down = new SingleKeyAction("DOWN", downAction);
        
        SingleKeyAction[] keys = {up, down};
        for (SingleKeyAction key : keys) {
            String label = key.getLabel();
            String actionName = key.getActionName();
            Action action = key.getAction();
            mainPane.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(label), actionName);
            mainPane.getActionMap().put(actionName, action);
        }
    }
    
    public boolean gameIsRunning() {
        return timer != null;
    }
    
    public void addComponents() {
        // add key bindings
        addKeyStrokeActions();
        
        mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.PAGE_AXIS));
        
        JPanel buttonPanel = makeButtonPanel();
        mainPane.add(buttonPanel);
        
        JPanel scorePanel = makeScorePanel();
        mainPane.add(scorePanel);
        
        mainPane.add(pongPanel);
        mainPane.add(Box.createGlue());
    }
    
    private JPanel makeScorePanel() {
        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.LINE_AXIS));
        scorePanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        scoreLabel = new JLabel();
        updateScoreBoard();
        scorePanel.add(scoreLabel);
        return scorePanel;
    }
    
    private JPanel makeButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        
        String[] buttonLabels = {"Start", "Pause", "Reset"};
        for (String label: buttonLabels) {
            JButton button = createButton(label);
            buttonPanel.add(button);
        }
        return buttonPanel;
    }
    
    private JButton createButton(String label) {
        JButton b = new javax.swing.JButton();

        b.setText(label);
        b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActionPerformed(evt, label);
                mainPane.requestFocusInWindow();
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
    
    private void moveMainPlayer() {
        Player player = model.players.get(0);
        player.step();
        movePlayerInsideBoard(player);
    }
    
    private void startGame() {
        pongPanel.repaint();
        
        ActionListener listener = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Boundary detection to change model.ball.v
                Vec ballVec = model.ball.getVel();
                double deg = ballVec.getDegrees();

                int hitWall = ballHitWall();
                if (hitWall == 0 || hitWall == 2) {
                    playerScored(hitWall == 2 ? 1 : 0);
                    
                    if (aPlayerWon()) {
                        timer.removeActionListener(this);
                        return;
                    }
                    else {
                        resetForNextPoint();
                    }
                    
                }
                if (hitWall == 1 || hitWall == 3) {
                    refractBallHorizontally(hitWall);
                }
                if (ballHitAPlayer()) {
                    refractBallVertically();
                }
                moveBall();
                moveRobot();
                moveMainPlayer();
                pongPanel.repaint();
            }
        };
        timer = new Timer(50, listener);
        timer.start();
    }
    
    private boolean aPlayerWon() {
        return model.score.get(0) >= 7 || model.score.get(1) >= 7;
    }
    
    /**
     * 0: left player
     * 1: right player
     */
    private void playerScored(int playerId) {
        int currentPlayerScore = model.score.get(playerId);
        model.score.set(playerId, currentPlayerScore + 1);
        updateScoreBoard();
    }
    
    private void updateScoreBoard() {
        scoreLabel.setText(model.score.get(0) + " to " + model.score.get(1));
    }
    
    private void pauseGame() {
        if (timer != null) {
            timer.stop();
        }
    }
    
    private void resetGame() {
        if (timer != null) {
            timer.stop();
            model.resetGame();
            updateScoreBoard();
            pongPanel.repaint();
        }
    }
    
    private void resetForNextPoint() {
        model.reset();
        pongPanel.repaint();
    }
    
    private void moveBall() {
        model.ball.step();
    }
    
    private void refractBallVertically() {
        boolean isLeftSide = model.ball.getCenter().x < (int)(model.d.width/2);
        Vec ballVec = model.ball.getVel();

        double deg = ballVec.getDegrees();
        boolean isRight = ballVec.isRight();        
        boolean isDown = ballVec.isDown();
        boolean rightDirection = (isLeftSide && isRight) || (!isLeftSide && !isRight);

        if (!rightDirection) {
            model.ball.setVel(model.ball.getVel().refractVertically());
        }
    }
    
    /**
     * 1: top wall
     * 3: bottom wall
     * @param hitWall 
     */
    private void refractBallHorizontally(int hitWall) {
        Vec ballVec = model.ball.getVel();
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
                model.ball.setVel(new Vec(newTheta, ballVec.getMagnitude()));    
            }
            else {
                model.ball.setVel(model.ball.getVel().refractHorizontally());
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
        if (model.ball.getCenter().x - radius <= 0) {
            result = 2;
        }
        else if (model.ball.getCenter().x >= model.d.width) {
            result = 0;
        }
        else if ((model.ball.getCenter().y - radius) <= 0) {
            result = 1;
        }
        else if ((model.ball.getCenter().y + radius) >= model.d.height) {
            result = 3;
        }
        else {
            result = -1;
        }
        return result;
    }
    
    private boolean goodHeight(Player player) {
        int ballCenterY = model.ball.getCenter().y;
        int r = model.ball.radius;
        return ((ballCenterY + r) >= player.getTopEdge())
                && ((ballCenterY - r) <= player.getBottomEdge());
    }
    
    
    private boolean ballHitAPlayer() {
        Player leftPlayer = model.players.get(0);
        Player rightPlayer = model.players.get(1);
        
        int ballCenterX = model.ball.getCenter().x;
        int r = model.ball.radius;
        
        boolean hitLeftPlayer = goodHeight(leftPlayer) && ((ballCenterX - r) <= leftPlayer.getRightEdge()) && ((ballCenterX) >= leftPlayer.getRightEdge());
        boolean hitRightPlayer = goodHeight(rightPlayer) && ((ballCenterX + r >= rightPlayer.getLeftEdge())) && ((ballCenterX) <= rightPlayer.getLeftEdge());

        return hitLeftPlayer || hitRightPlayer;
    }
    
    private int addNoise() {
        return (int) (Math.random() * 20) - 10;
    }
    
    private void moveRobot() {
        Player robotPlayer = model.players.get(1);
        
        // Try and set acceleration just in the general direction of difference between the ball and the robot
        int yDiff = model.ball.getCenter().y - robotPlayer.getCenter().y;
        double accDegrees = (yDiff > 0) ? 90 : 270;
        robotPlayer.setAcc(new Vec(accDegrees, 2));
        robotPlayer.step();
        movePlayerInsideBoard(robotPlayer);
    }
    
    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Pong");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Pong pong = new Pong();
        pong.addComponents();
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
