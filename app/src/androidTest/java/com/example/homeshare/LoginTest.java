package com.example.homeshare;

import static androidx.test.espresso.Espresso.onView;

import android.content.Context;


import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.espresso.intent.rule.IntentsRule;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static org.hamcrest.CoreMatchers.not;


import static org.hamcrest.core.AllOf.allOf;

import junit.framework.AssertionFailedError;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
//@RunWith(AndroidJUnit4.class)
//public class ExampleInstrumentedTest {
//    @Test
//    public void useAppContext() {
//        // Context of the app under test.
//        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
//        assertEquals("com.example.homeshare", appContext.getPackageName());
//    }
//}

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

//        onView(withId(R.id.fragment_listings)).check(matches(isDisplayed()));
        onView(withId(R.id.loginButton)).check(matches(not(isDisplayed())));
    }
}




//onView(withId(R.id.loginErrMsg)).check(matches(withText("")));
//onView(withText("Error: Please fill out all fields")).check(matches(isDisplayed()));


//test if view is in hierarchy
//        try {
//                onView(withText("Error: Please fill out all fields")).perform(click());
//                // View is in hierarchy
//
//                } catch (NoMatchingViewException e) {
//                // View is not in hierarchy
//                }