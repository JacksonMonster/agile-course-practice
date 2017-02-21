package ru.unn.agile.IntersectLineAndPlane;


public class Plane3D {
    private final double coefficientA;
    private final double coefficientB;
    private final double coefficientC;
    private final double coefficientD;

    public Plane3D(final double factorA, final double factorB,
                   final double factorC, final double factorD) {
        this.coefficientA = factorA;
        this.coefficientB = factorB;
        this.coefficientC = factorC;
        this.coefficientD = factorD;
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
