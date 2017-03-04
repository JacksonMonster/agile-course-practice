package ru.unn.agile.SpaceConverter.infrastructure;

import ru.unn.agile.SpaceConverter.viewmodel.ViewModel;
import ru.unn.agile.SpaceConverter.viewmodel.ViewModelTests;

public class ViewModelWithTxtLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        TxtLogger realLogger =
            new TxtLogger("./ViewModel_with_TxtLogger_Tests.log");
        super.setExternalViewModel(new ViewModel(realLogger));
    }
}
