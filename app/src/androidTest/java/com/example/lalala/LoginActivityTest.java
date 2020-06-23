package com.example.lalala;

import android.view.KeyEvent;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressKey;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;

import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(LoginActivity.class);
    @Test
    public void testEnter1(){
        onView(withId(R.id.et_username)).perform(typeText(""),closeSoftKeyboard());
        onView(withId(R.id.et_password)).perform(typeText("password"),closeSoftKeyboard());
        onView(withText("登录")).perform(click());
    }
    @Test
    public void testEnter2(){
        onView(withId(R.id.et_username)).perform(typeText("username"),closeSoftKeyboard());
        onView(withId(R.id.et_password)).perform(typeText(""),closeSoftKeyboard());
        onView(withText("登录")).perform(click());
    }
    @Test
    public void testEnter3(){
        onView(withId(R.id.et_username)).perform(typeText("username1"),closeSoftKeyboard());
        onView(withId(R.id.et_password)).perform(typeText("password"),closeSoftKeyboard());
        onView(withText("登录")).perform(click());
    }
    @Test
    public void testEnter4(){
        onView(withId(R.id.et_username)).perform(typeText("username"),closeSoftKeyboard());
        onView(withId(R.id.et_password)).perform(typeText("password1"),closeSoftKeyboard());
        onView(withText("登录")).perform(click());
    }
    @Test
    public void testEnter5(){
        onView(withId(R.id.et_username)).perform(typeText("username"),closeSoftKeyboard());
        onView(withId(R.id.et_password)).perform(typeText("password"),closeSoftKeyboard());
        onView(withText("登录")).perform(click());
    }
}