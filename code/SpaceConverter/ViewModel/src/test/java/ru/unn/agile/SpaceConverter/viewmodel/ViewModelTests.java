package ru.unn.agile.SpaceConverter.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ru.unn.agile.SquareConverter.SquareConverter.Cons;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.sqMeterTextBoxProperty().get());
        assertEquals(Cons.TO_AR_MULTIPLIER, viewModel.constantProperty().get());
        assertEquals("", viewModel.resultValueProperty().get());
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingWhenCalculateWithEmptyFields() {
        viewModel.convert();
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenFieldsAreFill() {
        setInputData();
        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    private void setInputData() {
        viewModel.sqMeterTextBoxProperty().set("1");
    }

    @Test
    public void canReportBadFormat() {
        viewModel.sqMeterTextBoxProperty().set("a");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledInitially() {
        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWhenFormatIsBad() {
        setInputData();
        viewModel.sqMeterTextBoxProperty().set("trash");

        assertTrue(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsEnabledWithCorrectInput() {
        setInputData();

        assertFalse(viewModel.calculationDisabledProperty().get());
    }

    @Test
    public void canSetSuccessMessage() {
        setInputData();

        viewModel.convert();

        assertEquals(Status.SUCCESS.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.sqMeterTextBoxProperty().set("#selfie");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenSetProperData() {
        setInputData();

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void consToKilometerMulHasCorrectResult() {
        viewModel.sqMeterTextBoxProperty().set("1");
        viewModel.constantProperty().set(Cons.TO_KILOMETER_MULTIPLIER);

        viewModel.convert();

        assertEquals("1.0E-6", viewModel.resultValueProperty().get());
    }
    @Test
    public void consToHectareMulHasCorrectResult() {
        viewModel.sqMeterTextBoxProperty().set("1");
        viewModel.constantProperty().set(Cons.TO_HECTARE_MULTIPLIER);

        viewModel.convert();

        assertEquals("1.0E-4", viewModel.resultValueProperty().get());
    }
    @Test
    public void consToArMulHasCorrectResult() {
        viewModel.sqMeterTextBoxProperty().set("1");
        viewModel.constantProperty().set(Cons.TO_AR_MULTIPLIER);

        viewModel.convert();

        assertEquals("0.01", viewModel.resultValueProperty().get());
    }
    @Test
    public void consToSantimeterMulHasCorrectResult() {
        viewModel.sqMeterTextBoxProperty().set("1");
        viewModel.constantProperty().set(Cons.TO_SQR_SANTIMETER_MULTIPLIER);

        viewModel.convert();

        assertEquals("10000.0", viewModel.resultValueProperty().get());
    }
    @Test
    public void consToMillimeterMulHasCorrectResult() {
        viewModel.sqMeterTextBoxProperty().set("1");
        viewModel.constantProperty().set(Cons.TO_SQR_MILLIMETER_MULTIPLIER);

        viewModel.convert();

        assertEquals("1000000.0", viewModel.resultValueProperty().get());
    }
    @Test
    public void consToYardMulHasCorrectResult() {
        viewModel.sqMeterTextBoxProperty().set("1");
        viewModel.constantProperty().set(Cons.TO_SQR_YARD_MULTIPLIER);

        viewModel.convert();

        assertEquals("1.196", viewModel.resultValueProperty().get());
    }
    @Test
    public void consToFootMulHasCorrectResult() {
        viewModel.sqMeterTextBoxProperty().set("1");
        viewModel.constantProperty().set(Cons.TO_SQR_FOOT_MULTIPLIER);

        viewModel.convert();

        assertEquals("10.76", viewModel.resultValueProperty().get());
    }
    @Test
    public void consToInchMulHasCorrectResult() {
        viewModel.sqMeterTextBoxProperty().set("1");
        viewModel.constantProperty().set(Cons.TO_SQR_INCH_MULTIPLIER);

        viewModel.convert();

        assertEquals("1550.0", viewModel.resultValueProperty().get());
    }
    @Test
    public void consToAcreMulHasCorrectResult() {
        viewModel.sqMeterTextBoxProperty().set("1");
        viewModel.constantProperty().set(Cons.TO_SQR_ACRE_MULTIPLIER);

        viewModel.convert();

        assertEquals("2.471E-4", viewModel.resultValueProperty().get());
    }
    @Test
    public void consToMileMulHasCorrectResult() {
        viewModel.sqMeterTextBoxProperty().set("1");
        viewModel.constantProperty().set(Cons.TO_SQR_MILE_MULTIPLIER);

        viewModel.convert();

        assertEquals("3.861E-7", viewModel.resultValueProperty().get());
    }
}
