package ru.unn.agile.SpaceConverter.viewmodel;

/**
 * Created by Евгений on 04.03.2017.
 */
import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private final ArrayList<String> log = new ArrayList<>();

    @Override
    public void log(final String val) {
        log.add(val);
    }

    @Override
    public List<String> getLog() {
        return log;
    }
}
