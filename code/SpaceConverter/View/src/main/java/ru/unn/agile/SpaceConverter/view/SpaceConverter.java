package ru.unn.agile.SpaceConverter.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.SpaceConverter.viewmodel.ViewModel;
import ru.unn.agile.SquareConverter.SquareConverter.Cons;
import ru.unn.agile.SpaceConverter.infrastructure.TxtLogger;

public class SpaceConverter {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField tfSqMeter;
    @FXML
    private ComboBox<Cons> cbChoiceOfConvert;
    @FXML
    private Button btConvert;

    @FXML
    void initialize() {
        viewModel.setLogger(new TxtLogger("./TxtLogger.log"));

        final ChangeListener<Boolean> focusChangeListener = new ChangeListener<Boolean>() {
            @Override
            public void changed(final ObservableValue<? extends Boolean> observable,
                                final Boolean oldVal, final Boolean newVal) {
                viewModel.onFocusChanged(oldVal, newVal);
            }
        };

        tfSqMeter.textProperty().bindBidirectional(viewModel.sqMeterTextBoxProperty());
        tfSqMeter.focusedProperty().addListener(focusChangeListener);

        cbChoiceOfConvert.valueProperty().bindBidirectional(viewModel.constantProperty());
        cbChoiceOfConvert.valueProperty().addListener(new ChangeListener<Cons>() {
            @Override
            public void changed(final ObservableValue<? extends Cons> observable,
                                final Cons oldValue,
                                final Cons newValue) {
                viewModel.onConsChanged(oldValue, newValue);
            }
        });

        btConvert.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.convert();
            }
        });
    }
}
