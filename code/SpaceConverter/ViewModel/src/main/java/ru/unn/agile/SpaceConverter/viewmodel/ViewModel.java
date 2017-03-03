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

    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();

    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();

    public ViewModel() {
        sqMeterValue.set("");
        constant.set(Cons.TO_AR_MULTIPLIER);
        result.set("");
        status.set(Status.WAITING.toString());

        BooleanBinding couldCalculate = new BooleanBinding() {
            {
                super.bind(sqMeterValue);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        calculationDisabled.bind(couldCalculate.not());

        final List<StringProperty> fields = new ArrayList<StringProperty>() { {
            add(sqMeterValue);
        } };

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

    public StringProperty resultProperty() {
        return result;
    }
    public final String getResult() {
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

    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            status.set(getInputStatus().toString());
        }
    }

}

enum Status {
    WAITING("Please provide input data"),
    READY("Press 'Convert' or Enter"),
    BAD_FORMAT("Bad format"),
    SUCCESS("Success");

    private final String name;
    Status(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}
