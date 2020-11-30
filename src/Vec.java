package src;

public class Vec {
	private double degrees;
	private double magnitude;

	/**
	 * @param degrees from 0 to 360
	 * @param magnitude from 0 to n
	 */
	public Vec(double degrees, double magnitude) {
		this.degrees = degrees;
		this.magnitude = magnitude;
	}

	public double getDegrees() {
		return this.degrees;
	}

	public double getMagnitude() {
		return this.magnitude;
	}

	public String toString() {
		return "Deg:" + this.degrees + " Mag:" + this.magnitude;
	}
}
