package ru.unn.agile.SpaceConverter.infrastructure;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class RegexMatcher extends BaseMatcher {
    private final String regexe;

    public RegexMatcher(final String regex) {
        this.regexe = regex;
    }

    public boolean matches(final Object o) {
        return ((String) o).matches(regexe);
    }

    public void describeTo(final Description description) {
        description.appendText("matches regex = ");
        description.appendText(regexe);
    }

    public static Matcher<? super String> matchesPattern(final String regexe) {
        RegexMatcher matcher = new RegexMatcher(regexe);
        //NOTE: this ugly cast is needed to workaround 'unchecked' Java warning
        @SuppressWarnings (value = "unchecked")
        Matcher<? super String> castedMatcher = (Matcher<? super String>)   matcher;
        return castedMatcher;
    }
}
