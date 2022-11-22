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
public class CreateListingTest {
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
    public void showCreateListingsFragment() throws InterruptedException {
        setUp();
        //click on nav button for create listings
        onView(withId(R.id.create_listings)).perform(click());
        Thread.sleep(2000);
        //check that it is on createListingsPage
        onView(withId(R.id.createListingPage)).check(matches(isDisplayed()));
    }

    @Test
    public void createInvalidListing() throws InterruptedException {
        setUp();
        onView(withId(R.id.create_listings)).perform(click());
        Thread.sleep(2000);
        String emptyString = "";
        //click on nav button for create listings
        onView(withId(R.id.CLleaseEnd)).perform(typeText(emptyString), closeSoftKeyboard());
        onView(withId(R.id.CLlistingTitle)).perform(typeText(emptyString), closeSoftKeyboard());
        onView(withId(R.id.CLlistingAddr)).perform(typeText(emptyString), closeSoftKeyboard());
        onView(withId(R.id.CLleaseStart)).perform(typeText(emptyString), closeSoftKeyboard());
        onView(withId(R.id.CLlistingDescription)).perform(typeText(emptyString), closeSoftKeyboard());
        onView(withId(R.id.CLnumSpotsAvailable)).perform(typeText(emptyString), closeSoftKeyboard());
        onView(withId(R.id.CLpricePerMonth)).perform(typeText(emptyString), closeSoftKeyboard());
        onView(withId(R.id.CLresponseDeadline)).perform(typeText(emptyString), closeSoftKeyboard());

        onView(withId(R.id.postListingButton)).perform(scrollTo(), click());
        Thread.sleep(2000);
        onView(withId(R.id.postListingButton)).check(matches(isDisplayed()));
    }


    @Test
    public void createValidTesting() throws InterruptedException {
        setUp();
        onView(withId(R.id.create_listings)).perform(click());
        Thread.sleep(2000);
        String emptyString = "";

        //click on nav button for create listings
        onView(withId(R.id.CLlistingTitle)).perform(typeText("This is a Listing Title"), closeSoftKeyboard());
        onView(withId(R.id.CLlistingDescription)).perform(typeText("This is a Listing Description"), closeSoftKeyboard());
        onView(withId(R.id.CLfemaleRadioButton)).perform(click());
        onView(withId(R.id.CLleaseStart)).perform(typeText("12/12/2022"), closeSoftKeyboard());
        onView(withId(R.id.CLleaseEnd)).perform(typeText("12/12/2023"), closeSoftKeyboard());
        onView(withId(R.id.CLlistingAddr)).perform(typeText("1171 West 36th Place"), closeSoftKeyboard());
        onView(withId(R.id.CLnumSpotsAvailable)).perform(typeText("2"), closeSoftKeyboard());
        onView(withId(R.id.CLpricePerMonth)).perform(typeText("1200"), closeSoftKeyboard());
        onView(withId(R.id.CLresponseDeadline)).perform(typeText("11/11/2022"), closeSoftKeyboard());

        onView(withId(R.id.postListingButton)).perform(scrollTo(), click());
        Thread.sleep(2000);
        onView(withId(R.id.fragment_listings)).check(matches(isDisplayed()));
    }

}
