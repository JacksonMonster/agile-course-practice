package ru.unn.agile.IntersectLineAndPlane;




public class Line3D {
    private double coordinateOfVectorL;
    private double coordinateOfVectorM;
    private double coordinateOfVectorN;
    private double coordinateOfPointX0;
    private double coordinateOfPointY0;
    private double coordinateOfPointZ0;


    public Line3D(final double l, final double m, final double n,final double x0, final double y0, final double z0) {
        this.coordinateOfVectorL = l;
        this.coordinateOfVectorM = m;
        this.coordinateOfVectorN = n;
        this.coordinateOfPointX0 = x0;
        this.coordinateOfPointY0 = y0;
        this.coordinateOfPointZ0 = z0;
    }

    public double getL() {
        return coordinateOfVectorL;
    }
    public double getM() {
        return coordinateOfVectorM;
    }
    public double getN() {
        return coordinateOfVectorN;
    }
    public double getX0() {
        return coordinateOfPointX0;
    }
    public double getY0() {
        return coordinateOfPointY0;
    }
    public double getZ0() {
        return coordinateOfPointZ0;
    }
    public String toFormulaLine() {
        return FormaterLine.getFormatedLine(this);
    }

    public String checkIntersection( final Plane3D plane ){
        double coordinateX, coordinateY, coordinateZ, parametrT;
        parametrT= (-plane.getD()-plane.getC()*getZ0()-plane.getB()*getY0()-plane.getA()*getX0())/
                (plane.getA()*getL()+plane.getB()*getM()+plane.getC()*getN());
        coordinateX=getX0()+getL()*parametrT;
        coordinateY=getY0()+getM()*parametrT;
        coordinateZ=getZ0()+getN()*parametrT;
        if(plane.getA()*getL()+plane.getB()*getM()+plane.getC()*getN()== 0 &
                plane.getA()*getX0()+plane.getB()*getY0()+ plane.getC()*getZ0()+plane.getD()!=0)
            return "Line and plane parallel";
        else if(plane.getA()*getL()+plane.getB()*getM()+plane.getC()*getN()== 0 &
                plane.getA()*getX0()+plane.getB()*getY0()+ plane.getC()*getZ0()+plane.getD()==0)
            return "Line lies in the plane";
        else

        return "Intersection point (" + coordinateX + ";" + coordinateY + ":" + coordinateZ + ")";
    }

}




