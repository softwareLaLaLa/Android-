package com.example.lalala.ui.search;

import android.view.KeyEvent;

import androidx.test.rule.ActivityTestRule;

import com.example.lalala.LoginActivity;
import com.example.lalala.R;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressKey;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

public class SearchActivityTest {
    @Rule
    public ActivityTestRule<SearchActivity> mActivityRule = new ActivityTestRule<>(SearchActivity.class);


    @Test
    public void testEnter1(){
        onView(withId(R.id.et_search)).perform(typeText(""),pressKey(KeyEvent.KEYCODE_ENTER));
    }
    @Test
    public void testEnter2(){
        onView(withId(R.id.et_search)).perform(typeText("text"),pressKey(KeyEvent.KEYCODE_ENTER));
    }
    @Test
    public void testEnter3(){
        onView(withId(R.id.et_search)).perform(typeText("aasdsas"),pressKey(KeyEvent.KEYCODE_ENTER));


    }
}