package ru.unn.agile.IntersectLineAndPlane;


public class Plane3D {
    private double coefficientA;
    private double coefficientB;
    private double coefficientC;
    private double coefficientD;

    public Plane3D(final double A,final double B, final double C, final double D){
        this.coefficientA = A;
        this.coefficientB = B;
        this.coefficientC = C;
        this.coefficientD = D;
    }

    public double getA() {
        return coefficientA;
    }
    public double getB() {
        return coefficientB;
    }
    public double getC() {
        return coefficientC;
    }
    public double getD() {
        return coefficientD;
    }
    public String toFormula() {
        return FormaterPlane.getFormatedPlane(this);
    }
}
