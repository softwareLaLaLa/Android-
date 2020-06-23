package com.example.lalala;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.*;

public class UserActivityTest {
    @Rule
    public ActivityTestRule<UserActivity> mActivityTest=new ActivityTestRule<>(UserActivity.class);

    @Test
    public void testBrowse1(){
        onView(withText("热榜")).perform(click());
        onView(withText("推荐")).perform(click());
        onView(withText("热榜")).perform(click());
        onView(withText("推荐")).perform(click());

    }
    @Test
    public void testBrowse2(){
        onView(withText("热榜")).perform(click());
        onView(withText("推荐")).perform(click());
        onView(withText("热榜")).perform(click());
        onView(withText("推荐")).perform(click());
        onView(allOf(withId(R.id.paperList),isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        Espresso.pressBack();
    }
    @Test
    public void testBrowse3(){
        onView(withText("热榜")).perform(click());
        onView(withText("推荐")).perform(click());
        onView(withText("热榜")).perform(click());
        onView(withText("推荐")).perform(click());
        onView(allOf(withId(R.id.paperList),isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        Espresso.pressBack();
        onView(allOf(withId(R.id.paperList),isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        Espresso.pressBack();
    }



}