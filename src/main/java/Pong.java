
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
                moveBall();
                moveRobot();
                pongPanel.repaint();
            }
        };
        Timer timer = new Timer(100, listener);
        timer.start();
    }
    
    private void moveBall() {
        Point newCenter = model.ball.v.step(model.ball.center);
        model.ball.center = newCenter;
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
