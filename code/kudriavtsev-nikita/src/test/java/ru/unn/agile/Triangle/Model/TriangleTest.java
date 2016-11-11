package ru.unn.agile.Triangle.Model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TriangleTest {
    private final double delta = 0.001;
    private Point2D simpleA;
    private Point2D simpleB;
    private Point2D simpleC;
    private Triangle simpleTriangle;
    private Triangle difficultTriangle;
    private Triangle degenerateTriangle;

    @Before
    public void initializeSimpleTriangle() {
        simpleA = new Point2D(0, 0);
        simpleB = new Point2D(0, 1);
        simpleC = new Point2D(1, 0);
        simpleTriangle = new Triangle(simpleA, simpleB, simpleC);

        difficultTriangle = new Triangle(new Point2D(2, 0),
                                         new Point2D(-0.5, 0.4),
                                         new Point2D(0, -4));

        degenerateTriangle = new Triangle(new Point2D(2, 0),
                                          new Point2D(2, 0),
                                          new Point2D(0, 0));
    }
    @Test
    public void canCreateTriangleWithInitialValues() {
        assertNotNull(simpleTriangle);
    }

    @Test
    public void canSetInitialValueOfTrianglePoints() {
        assertEquals(simpleA, simpleTriangle.getA());
        assertEquals(simpleB, simpleTriangle.getB());
        assertEquals(simpleC, simpleTriangle.getC());
    }

    @Test
    public void canSetInitialValueOfLenghtOfSidesForSimpleTriangle() {
        double expectAB = 1;
        double expectBC = 1.414;
        double expectAC = 1;

        assertEquals(expectAB, simpleTriangle.getAB(), delta);
        assertEquals(expectBC, simpleTriangle.getBC(), delta);
        assertEquals(expectAC, simpleTriangle.getAC(), delta);
    }

    @Test
    public void canSetInitialValueOfLenghtOfSidesForDegenerateTriangle() {
        double expectAB = 0;
        double expectBC = 2;
        double expectAC = 2;

        assertEquals(expectAB, degenerateTriangle.getAB(), delta);
        assertEquals(expectBC, degenerateTriangle.getBC(), delta);
        assertEquals(expectAC, degenerateTriangle.getAC(), delta);
    }

    @Test
    public void canReturnValueOfPerimeterForSimpleTriangle() {
        double expected = 3.414;

        double actual = simpleTriangle.perimeter();

        assertEquals(expected, actual, delta);
    }

    @Test
    public void canReturnValueOfPerimeterForDifficultTriangle() {
        double expected = 11.432;

        double actual = difficultTriangle.perimeter();

        assertEquals(expected, actual, delta);
    }

    @Test
    public void canReturnValueOfPerimeterForDegenerateTriangle() {
        double expected = 4;

        double actual = degenerateTriangle.perimeter();

        assertEquals(expected, actual, delta);
    }

    @Test
    public void canReturnValueOfAreaForSimpleTriangle() {
        double expected = 0.5;

        double actual = simpleTriangle.area();

        assertEquals(expected, actual, delta);
    }

    @Test
    public void canReturnValueOfAreaForDifficultTriangle() {
        double expected = 5.400;

        double actual = difficultTriangle.area();

        assertEquals(expected, actual, delta);
    }

    @Test
    public void canReurnValueOfAreaForDegenerateTriangle() {
        double expected = 0;

        double actual = degenerateTriangle.area();

        assertEquals(expected, actual, delta);
    }
}
