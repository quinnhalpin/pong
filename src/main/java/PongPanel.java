
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author halpin
 */
public class PongPanel extends javax.swing.JPanel {
    private Graphics g;
    private Graphics2D g2D;
    PongModel model;
    
    /**
     * Creates new form PongPanel
     */
    public PongPanel(Pong controller, PongModel m) {
        model = m;
        initComponents();
    }
    
    
    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }
    
    @Override
    public Dimension getPreferredSize() {
        return model.d;
    }
    
    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);        
        drawBall(g, model.ball);
        drawPlayer(g, model.players.get(0));        
        drawPlayer(g, model.players.get(1));
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    
    private void drawPlayer(Graphics g, Player player) {
        g.setColor(Color.WHITE);
        Point p = player.getBottomLeft();
        g.fillRect(p.x, p.y, player.getWidth(), player.getHeight());
        g.drawRect(p.x, p.y, player.getWidth(), player.getHeight());
    }
    
    private void drawBall(Graphics g, Ball b) {
        g.setColor(Color.RED);
        Point bL = b.getBottomLeft();
        g.drawOval(bL.x, bL.y, b.radius, b.radius);
        g.fillOval(bL.x, bL.y, b.radius, b.radius);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
