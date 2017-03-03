package ru.unn.agile.SpaceConverter.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.SpaceConverter.viewmodel.ViewModel;
import ru.unn.agile.SquareConverter.SquareConverter.Cons;

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
        tfSqMeter.textProperty().bindBidirectional(viewModel.sqMeterTextBoxProperty());

        cbChoiceOfConvert.valueProperty().bindBidirectional(viewModel.constantProperty());
        btConvert.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.convert();
            }
        });
    }
}
