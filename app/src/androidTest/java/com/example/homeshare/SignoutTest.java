//package com.example.homeshare;
//
//import static androidx.test.espresso.Espresso.onView;
//
//import android.accounts.Account;
//import android.content.Context;
//
//
//import androidx.test.espresso.NoMatchingViewException;
//import androidx.test.espresso.matcher.ViewMatchers;
//import androidx.test.ext.junit.rules.ActivityScenarioRule;
//import androidx.test.filters.LargeTest;
//import androidx.test.platform.app.InstrumentationRegistry;
//import androidx.test.ext.junit.runners.AndroidJUnit4;
//
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import static org.junit.Assert.*;
//
//
//import static androidx.test.espresso.Espresso.onView;
//import static androidx.test.espresso.action.ViewActions.click;
//import static androidx.test.espresso.assertion.ViewAssertions.matches;
//import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static androidx.test.espresso.matcher.ViewMatchers.withId;
//import static androidx.test.espresso.matcher.ViewMatchers.withText;
//import static androidx.test.espresso.action.ViewActions.typeText;
//import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
//import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
//import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
//import static org.hamcrest.CoreMatchers.not;
//
//
//import static org.hamcrest.core.AllOf.allOf;
//
//import junit.framework.AssertionFailedError;
//
//
//@RunWith(AndroidJUnit4.class)
//@LargeTest
//public class SignoutTest {
//
//    @Rule
//    public FragmentScenarioRule<AccountFragment> activityRule =
//            new ActivityScenarioRule<>(AccountFragment.class);
//
//    String validEmail = "test@usc.edu";
//    String validPassword = "red1234";
//
//
//    @Test
//    public void signupSignout() {
//        // Type text and then press the button.
//        onView(withId(R.id.signUpEmail))
//                .perform(typeText(validEmail), closeSoftKeyboard());
//
//        onView(withId(R.id.signUpPassword))
//                .perform(typeText(validPassword), closeSoftKeyboard());
//
//        onView(withId(R.id.confPassword))
//                .perform(typeText(validPassword), closeSoftKeyboard());
//
//        onView(withId(R.id.signUpButton)).perform(click());
//
//        //Click my account page on nav menu (AccountFragment)
//        //click signout
//        //check page is MainActivity page
//    }
//
//
//}