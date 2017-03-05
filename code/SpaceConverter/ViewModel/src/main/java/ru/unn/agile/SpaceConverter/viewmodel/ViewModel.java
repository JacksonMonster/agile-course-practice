package ru.unn.agile.SpaceConverter.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.SquareConverter.SquareConverter.*;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private final StringProperty sqMeterValue = new SimpleStringProperty();

    private final ObjectProperty<ObservableList<Cons>> constants =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Cons.values()));
    private final ObjectProperty<Cons> constant = new SimpleObjectProperty<>();
    private final BooleanProperty calculationDisabled = new SimpleBooleanProperty();

    private final StringProperty logs = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();

    private ILogger logger;
    private List<ValueChangeListener> valueChangedListeners;

    public final void setLogger(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger parameter can't be null");
        }
        this.logger = logger;
    }

    public ViewModel() {
        init();
    }
    public ViewModel(final ILogger logger) {
        setLogger(logger);
        init();
    }
    private void init() {
        sqMeterValue.set("");
        constant.set(Cons.TO_AR_MULTIPLIER);
        result.set("");
        status.set(Status.WAITING.toString());

        BooleanBinding couldCalculat = new BooleanBinding() {
            {
                super.bind(sqMeterValue);
            }
            @Override
            protected boolean computeValue() {
                if (getInputStatus() == Status.READY) {
                    return true;
                }
                return false;
            }
        };
        calculationDisabled.bind(couldCalculat.not());

        final List<StringProperty> fields = new ArrayList<StringProperty>() { {
            add(sqMeterValue);
        } };
        valueChangedListeners = new ArrayList<>();
        for (StringProperty field : fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }


    public void convert() {
        if (calculationDisabled.get()) {
            return;
        }

        double sqM = Double.parseDouble(sqMeterValue.get());
        result.set(Double.toString(constant.get().toDistanationUnit(sqM)));
        status.set(Status.SUCCESS.toString());

        StringBuilder message = new StringBuilder(LogMessages.CALCULATE_WAS_PRESSED);
        message.append("Argument")
                .append(": sqMeter = ").append(sqMeterValue.get())
                .append(" Constant: ").append(constant.get().toString()).append(".");
        logger.log(message.toString());
        updateLogs();
    }

    public void onConsChanged(final Cons oldValue, final Cons newValue) {
        if (oldValue.equals(newValue)) {
            return;
        }
        StringBuilder message = new StringBuilder(LogMessages.CONS_WAS_CHANGED);
        message.append(newValue.toString());
        logger.log(message.toString());
        updateLogs();
    }

    public void onFocusChanged(final Boolean oldVal, final Boolean newVal) {
        if (!oldVal && newVal) {
            return;
        }

        for (ValueChangeListener listener : valueChangedListeners) {
            if (listener.isChanged()) {
                StringBuilder message = new StringBuilder(LogMessages.EDITING_FINISHED);
                message.append("Input arguments are: [")
                        .append(sqMeterValue.get()).append("]");
                logger.log(message.toString());
                updateLogs();

                listener.cache();
                break;
            }
        }
    }


    public final List<String> getLog() {
        return logger.getLog();
    }

    public StringProperty sqMeterTextBoxProperty() {
        return sqMeterValue;
    }

    public ObjectProperty<ObservableList<Cons>> constantsProperty() {
        return constants;
    }
    public final ObservableList<Cons> getConstants() {
        return constants.get();
    }
    public ObjectProperty<Cons> constantProperty() {
        return constant;
    }
    public BooleanProperty calculationDisabledProperty() {
        return calculationDisabled;
    }
    public final boolean getCalculationDisabled() {
        return calculationDisabled.get();
    }
    public StringProperty logsProperty() {
        return logs;
    }
    public final String getLogs() {
        return logs.get();
    }
    public StringProperty resultValueProperty() {
        return result;
    }
    public final String getResultValue() {
        return result.get();
    }
    public StringProperty statusProperty() {
        return status;
    }
    public final String getStatus() {
        return status.get();
    }

    private Status getInputStatus() {
        Status inputStatus = Status.READY;
        if (sqMeterValue.get().isEmpty()) {
            inputStatus = Status.WAITING;
        }
        try {
            if (!sqMeterValue.get().isEmpty()) {
                Double.parseDouble(sqMeterValue.get());
            }
        } catch (NumberFormatException nfe) {
            inputStatus = Status.BAD_FORMAT;
        }

        return inputStatus;
    }

    private void updateLogs() {
        List<String> fullLog = logger.getLog();
        String record = new String();
        for (String log : fullLog) {
            record += log + "\n";
        }
        logs.set(record);
    }

    private class ValueChangeListener implements ChangeListener<String> {
        private String prevVal = new String();
        private String curVal = new String();
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldVal,
                            final String newVal) {
            if (oldVal.equals(newVal)) {
                return;
            }
            status.set(getInputStatus().toString());
            curVal = newVal;
        }
        public boolean isChanged() {
            return !prevVal.equals(curVal);
        }
        public void cache() {
            prevVal = curVal;
        }
    }


}

enum Status {
    WAITING("Please provide input data"),
    READY("Press 'Convert' or Enter"),
    BAD_FORMAT("Bad format"),
    SUCCESS("Success");

    private final String val;
    Status(final String val) {
        this.val = val;
    }
    public String toString() {
        return val;
    }
}

final class LogMessages {
    public static final String CALCULATE_WAS_PRESSED = "Calculate. ";
    public static final String CONS_WAS_CHANGED = "Cons was changed to ";
    public static final String EDITING_FINISHED = "Updated input. ";

    private LogMessages() { }
}

