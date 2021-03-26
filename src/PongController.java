package src;

public class PongController {
	private PongModel model;
	private PongViewer viewer;

	public PongController(PongModel model, PongViewer viewer) {
		this.model = model;
		this.viewer = viewer;
	}
}
