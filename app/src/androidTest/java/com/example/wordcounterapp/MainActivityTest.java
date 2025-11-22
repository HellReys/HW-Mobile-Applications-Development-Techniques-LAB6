package com.example.wordcounterapp;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static org.hamcrest.Matchers.*;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testWordCountingUI() {
        onView(withId(R.id.editTextInput))
                .perform(typeText("Hello world test"), closeSoftKeyboard());

        onView(withId(R.id.spinnerMetrics)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Words"))).perform(click());

        onView(withId(R.id.btnCount)).perform(click());

        onView(withId(R.id.textViewResult))
                .check(matches(withText(containsString("3"))));
    }

    @Test
    public void testSentenceCountingUI() {
        onView(withId(R.id.editTextInput))
                .perform(typeText("Hello. Hi? OK!"), closeSoftKeyboard());

        onView(withId(R.id.spinnerMetrics)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Sentences"))).perform(click());

        onView(withId(R.id.btnCount)).perform(click());

        onView(withId(R.id.textViewResult))
                .check(matches(withText(containsString("3"))));
    }

    @Test
    public void testEmptyInputShowsToast() {
        onView(withId(R.id.btnCount)).perform(click());

        activityRule.getScenario().onActivity(activity -> {
            onView(withText(R.string.empty_warning))
                    .inRoot(withDecorView(not(activity.getWindow().getDecorView())))
                    .check(matches(isDisplayed()));
        });
    }
}
