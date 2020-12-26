
import java.awt.Dimension;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author halpin
 */
public class Board {
    public final Dimension d;
    private final double score_width;
    
    public Board(Dimension d, double score_width) {
        this.d = d;
        this.score_width = score_width;
    }
}
