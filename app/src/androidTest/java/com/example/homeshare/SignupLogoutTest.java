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
public class SignupLogoutTest {

    @Rule
    public ActivityScenarioRule<Signup> activityRule =
            new ActivityScenarioRule<>(Signup.class);

    String validEmail = "aly@usc.edu";
    String validPassword = "verysafepass";

    public void signup() {
        onView(withId(R.id.signUpEmail))
                .perform(typeText(validEmail), closeSoftKeyboard());

        onView(withId(R.id.signUpPassword))
                .perform(typeText(validPassword), closeSoftKeyboard());

        onView(withId(R.id.confPassword))
                .perform(typeText(validPassword), closeSoftKeyboard());

        onView(withId(R.id.signUpButton)).perform(click());
    }

    public void fillWelcomePage() throws InterruptedException {
        onView(withId(R.id.firstName)).perform(typeText("Alyssa"), closeSoftKeyboard());
        onView(withId(R.id.lastName)).perform(typeText("Ch"), closeSoftKeyboard());

        onView(withId(R.id.welcomeNBRadioButton)).perform(click());

        onView(withId(R.id.yearInSchool)).perform(typeText("Senior"), closeSoftKeyboard());

        onView(withId(R.id.welcomeUserBio)).perform(typeText("Hello, this is my bio!"), closeSoftKeyboard());

        onView(withId(R.id.submitWelcomeButton)).perform(click());
    }


    @Test
    public void signupLogout() throws InterruptedException {
        signup();
        Thread.sleep(2000);

        fillWelcomePage();
        Thread.sleep(2000);

        onView(withId(R.id.my_account)).perform(click()); //click on my_account item on nav menu
        Thread.sleep(2000);

        onView(withId(R.id.logoutButton)).perform(click()); //click log

        onView(withId(R.id.main_activity_page)).check(matches(isDisplayed())); //check page is MainActivity page
    }

}