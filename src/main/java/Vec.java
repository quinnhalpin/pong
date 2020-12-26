/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author halpin
 */
public class Vec {
    private final double degrees;
    private final double magnitude;

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
