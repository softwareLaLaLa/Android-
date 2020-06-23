package com.example.lalala;

import android.text.InputType;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import com.example.lalala.entity.PaperData;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withInputType;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.*;

public class PaperActivityTest {
    @Rule
    public ActivityTestRule<PaperActivity> mActivityRule = new ActivityTestRule<>(PaperActivity.class);

    @Test
    public void testPaper1(){
        //onView(withId(R.id.sv_paper)).perform(scrollTo());
      //  onView(allOf(withId(R.id.paperList),isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        onView(withText("自定义标签")).perform(scrollTo());
        onView(withText("自定义标签")).perform(click());
        //onView(withInputType(InputType.TYPE_CLASS_TEXT)).perform(typeText("tag11"),closeSoftKeyboard());
        onView(withHint("请输入标签名")).perform(typeText("tag11"),closeSoftKeyboard());
        onView(withText("确定")).perform(click());
        onView(allOf(withParent(withId(R.id.fl_suggest_tags)),withText("tag3"))).perform(click());
        //onView(withText("tag3")).perform(click());
    }
}