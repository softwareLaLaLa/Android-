package com.example.lalala;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

public class RegisterActivityTest {

    @Rule
    public ActivityTestRule<RegisterActivity> mActivityRule = new ActivityTestRule<>(RegisterActivity.class);

    @Test
    public void testRegister1(){
        onView(withId(R.id.et_username)).perform(typeText(""),closeSoftKeyboard());
        onView(withId(R.id.et_password)).perform(typeText("password"),closeSoftKeyboard());
        onView(withId(R.id.et_password_1)).perform(typeText("password"),closeSoftKeyboard());
        onView(withText("tag1")).perform(click());
        onView(withText("tag2")).perform(click());
        onView(withText("tag3")).perform(click());
        onView(withId(R.id.btn_register)).perform(click());
    }
    @Test
    public void testRegister2(){
        onView(withId(R.id.et_username)).perform(typeText("username"),closeSoftKeyboard());
        onView(withId(R.id.et_password)).perform(typeText(""),closeSoftKeyboard());
        onView(withId(R.id.et_password_1)).perform(typeText("password"),closeSoftKeyboard());
        onView(withText("tag1")).perform(click());
        onView(withText("tag2")).perform(click());
        onView(withText("tag3")).perform(click());
        onView(withId(R.id.btn_register)).perform(click());
    }
    @Test
    public void testRegister3(){
        onView(withId(R.id.et_username)).perform(typeText("username"),closeSoftKeyboard());
        onView(withId(R.id.et_password)).perform(typeText("password"),closeSoftKeyboard());
        onView(withId(R.id.et_password_1)).perform(typeText(""),closeSoftKeyboard());
        onView(withText("tag1")).perform(click());
        onView(withText("tag2")).perform(click());
        onView(withText("tag3")).perform(click());
        onView(withId(R.id.btn_register)).perform(click());
    }
    @Test
    public void testRegister4(){
        onView(withId(R.id.et_username)).perform(typeText("username"),closeSoftKeyboard());
        onView(withId(R.id.et_password)).perform(typeText("password"),closeSoftKeyboard());
        onView(withId(R.id.et_password_1)).perform(typeText("password"),closeSoftKeyboard());
        onView(withId(R.id.btn_register)).perform(click());
    }
    @Test
    public void testRegister5(){
        onView(withId(R.id.et_username)).perform(typeText("username"),closeSoftKeyboard());
        onView(withId(R.id.et_password)).perform(typeText("password"),closeSoftKeyboard());
        onView(withId(R.id.et_password_1)).perform(typeText("password1"),closeSoftKeyboard());
        onView(withText("tag1")).perform(click());
        onView(withText("tag2")).perform(click());
        onView(withText("tag3")).perform(click());
        onView(withId(R.id.btn_register)).perform(click());
    }
    @Test
    public void testRegister(){
        onView(withId(R.id.et_username)).perform(typeText("username"),closeSoftKeyboard());
        onView(withId(R.id.et_password)).perform(typeText("password"),closeSoftKeyboard());
        onView(withId(R.id.et_password_1)).perform(typeText("password"),closeSoftKeyboard());
        onView(withText("tag1")).perform(click());
        onView(withText("tag3")).perform(click());
        onView(withText("tag5")).perform(click());
        onView(withId(R.id.btn_register)).perform(click());
    }
}