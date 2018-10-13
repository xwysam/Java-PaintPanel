package ca.utoronto.utm.paint;

public class Factory {
	public PaintModel model;
	public Factory (PaintModel model) {
		this.model = model;
	}
	public PaintModel getpaintmodel() {
		return this.model;
	}
}
