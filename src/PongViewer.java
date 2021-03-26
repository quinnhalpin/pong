package src;
import java.awt.Canvas;
import javax.swing.JFrame;

public class PongViewer extends Canvas {
	private PongModel model;

	public PongViewer(PongModel model) {
		this.model = model;
		JFrame frame = new JFrame("my drawing");
		Canvas canvas = new PongViewer();
		canvas.setSize(400, 400);
		frame.add(canvas);
		frame.pack();
		frame.setVisible(true);
	}

	// public void keyPressed(KeyEvent e) {
	// }

	// public void keyReleased(KeyEvent e) {
		
	// }

	// public void keyTyped(KeyEvent e) {
	// 	char c = e.getKeyChar();
	// 	System.out.println("here");
	// }
}
