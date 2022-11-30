package com.example.homeshare;

import static androidx.test.espresso.Espresso.onView;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginTest {

    @Rule
    public ActivityScenarioRule<login> activityRule =
            new ActivityScenarioRule<>(login.class);


    String invalidEmail = "test@gmail.com";

    String emptyEmail = "";
    String emptyPassword = "";

    String testEmail = "test@usc.edu";
    String testPassword = "red1234";


    @Test
    public void loginInvalidEmail() {
        // Type text and then press the button.
        onView(withId(R.id.email))
                .perform(typeText(invalidEmail), closeSoftKeyboard());

        onView(withId(R.id.password))
                .perform(typeText(testPassword), closeSoftKeyboard());

        onView(withId(R.id.loginButton)).perform(click());

        //check that we haven't gone to home (listings) page
        onView(withId(R.id.loginButton)).check(matches(isDisplayed()));
    }


    @Test
    public void loginEmptyEmail() {
        // Type text and then press the button.
        onView(withId(R.id.email))
                .perform(typeText(emptyEmail), closeSoftKeyboard());

        onView(withId(R.id.password))
                .perform(typeText(testPassword), closeSoftKeyboard());

        onView(withId(R.id.loginButton)).perform(click());

        onView(withId(R.id.loginButton)).check(matches(isDisplayed()));
    }

    @Test
    public void loginEmptyPassword() {
        // Type text and then press the button.
        onView(withId(R.id.email))
                .perform(typeText(testEmail), closeSoftKeyboard());

        onView(withId(R.id.password))
                .perform(typeText(emptyPassword), closeSoftKeyboard());

        onView(withId(R.id.loginButton)).perform(click());

        onView(withId(R.id.loginButton)).check(matches(isDisplayed()));
    }


    @Test
    public void loginSuccess() throws InterruptedException {
        onView(withId(R.id.email))
                .perform(typeText(testEmail), closeSoftKeyboard());

        onView(withId(R.id.password))
                .perform(typeText(testPassword), closeSoftKeyboard());

        onView(withId(R.id.loginButton)).perform(click());

        Thread.sleep(2000);
        onView(withId(R.id.fragment_listings)).check(matches(isDisplayed()));
    }
}