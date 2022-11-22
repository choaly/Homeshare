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
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.action.ViewActions.scrollTo;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class NavMenu {
    @Rule
    public ActivityScenarioRule<login> activityRule =
            new ActivityScenarioRule<>(login.class);

    String testEmail = "test@usc.edu";
    String testPassword = "red1234";

    public void setUp() throws InterruptedException {
        //log in
        onView(withId(R.id.email)).perform(typeText(testEmail), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(testPassword), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
        Thread.sleep(2000);
    }

    @Test
    public void showResponseFragment() throws InterruptedException {
        setUp();
        //click on nav button for create listings
        onView(withId(R.id.responses)).perform(click());
        Thread.sleep(2000);
        //check that it is on createListingsPage
        onView(withId(R.id.responsePage)).check(matches(isDisplayed()));
    }

    @Test
    public void showNotificationsFragment() throws InterruptedException {
        setUp();
        //click on nav button for create listings
        onView(withId(R.id.notifications)).perform(click());
        Thread.sleep(2000);
        //check that it is on createListingsPage
        onView(withId(R.id.notificationsPage)).check(matches(isDisplayed()));
    }

    @Test
    public void showAccountFragment() throws InterruptedException {
        setUp();
        //click on nav button for create listings
        onView(withId(R.id.my_account)).perform(click());
        Thread.sleep(2000);
        //check that it is on createListingsPage
        onView(withId(R.id.accountPage)).check(matches(isDisplayed()));
    }
}
