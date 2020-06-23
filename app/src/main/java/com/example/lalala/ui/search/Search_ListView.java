package com.example.lalala.ui.search;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class Search_ListView extends ListView {
    public Search_ListView(Context context) {
        super(context);
    }

    public Search_ListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Search_ListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {                //ListView和ScrollView适配
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
