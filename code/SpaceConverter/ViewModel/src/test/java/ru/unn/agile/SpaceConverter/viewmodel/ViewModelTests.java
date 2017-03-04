package ru.unn.agile.SpaceConverter.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.SquareConverter.SquareConverter.Cons;

import java.util.List;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    public void setExternalViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Before
    public void setUp() {
        if (viewModel == null) {
            viewModel = new ViewModel(new FakeLogger());
        }
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

    @Test
    public void viewModelConstructorThrowsExceptionWithNullLogger() {
        try {
            new ViewModel(null);
            fail("Exception wasn't thrown");
        } catch (IllegalArgumentException ex) {
            assertEquals("Logger parameter can't be null", ex.getMessage());
        } catch (Exception ex) {
            fail("Invalid exception type");
        }
    }

    @Test
    public void logIsEmptyInTheBeginning() {
        List<String> log = viewModel.getLog();

        assertTrue(log.isEmpty());
    }

    @Test
    public void logContainsProperMessageAfterConvertation() {
        setInputData();
        viewModel.convert();
        String message = viewModel.getLog().get(0);

        assertTrue(message.matches(".*" + LogMessages.CALCULATE_WAS_PRESSED + ".*"));
    }

    @Test
    public void logContainsInputArgumentsAfterConvertation() {
        setInputData();

        viewModel.convert();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + viewModel.sqMeterTextBoxProperty().get()
                + ".*"));
    }

    @Test
    public void argumentsInfoIssProperlyFormatted() {
        setInputData();

        viewModel.convert();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*Argument"
                + ": sqMeter = " + viewModel.sqMeterTextBoxProperty().get() + ".*"));
    }

    @Test
    public void consTypeIsMentionedInTheLog() {
        setInputData();

        viewModel.convert();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*TO_AR_MULTIPLIER.*"));
    }

    @Test
    public void canPutSeveralLogMessages() {
        setInputData();

        viewModel.convert();
        viewModel.convert();
        viewModel.convert();

        assertEquals(3, viewModel.getLog().size());
    }

    @Test
    public void canSeeConsChangeInLog() {
        setInputData();

        viewModel.onConsChanged(Cons.TO_AR_MULTIPLIER, Cons.TO_HECTARE_MULTIPLIER);

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + LogMessages.CONS_WAS_CHANGED
                + "TO_HECTARE_MULTIPLIER.*"));
    }

    @Test
    public void consIsNotLoggedIfNotChanged() {
        viewModel.onConsChanged(Cons.TO_AR_MULTIPLIER, Cons.TO_HECTARE_MULTIPLIER);

        viewModel.onConsChanged(Cons.TO_HECTARE_MULTIPLIER, Cons.TO_AR_MULTIPLIER);

        assertEquals(2, viewModel.getLog().size());
    }

    /*@Test
    public void argumentsAreCorrectlyLogged() {
        setInputData();

        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + LogMessages.EDITING_FINISHED
                + "Input arguments are: \\["
                + viewModel.sqMeterTextBoxProperty().get() + "; " + "\\]"));
    }*/

    @Test
    public void convertIsNotCalledWhenButtonIsDisabled() {
        viewModel.convert();

        assertTrue(viewModel.getLog().isEmpty());
    }

    @Test
    public void doNotLogSameParametersTwiceWithPartialInput() {
        viewModel.sqMeterTextBoxProperty().set("12");
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);
        viewModel.sqMeterTextBoxProperty().set("12");
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);

        assertEquals(1, viewModel.getLog().size());
    }

}
