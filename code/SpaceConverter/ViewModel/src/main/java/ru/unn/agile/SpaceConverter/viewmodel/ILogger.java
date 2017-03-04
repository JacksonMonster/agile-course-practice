package ru.unn.agile.SpaceConverter.viewmodel;

/**
 * Created by Евгений on 04.03.2017.
 */
import java.util.List;

public interface ILogger {
    void log(String s);

    List<String> getLog();
}
