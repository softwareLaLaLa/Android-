package com.example.lalala.ui.search;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;

import com.example.lalala.R;

public class EditText_Search extends AppCompatEditText {          //自定义搜索框

    private Drawable clearDrawable, searchDrawable;               //搜索图标和清空图标

    public EditText_Search(Context context) {
        super(context);
        init(context);
    }

    public EditText_Search(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public EditText_Search(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        clearDrawable = ContextCompat.getDrawable(context, R.drawable.clear);
        searchDrawable = ContextCompat.getDrawable(context, R.drawable.search);

        setCompoundDrawablesWithIntrinsicBounds(searchDrawable, null, null, null);   //设置上下左右的图标
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {     //如果有输入，就显示清空图标
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        setClearIcon(hasFocus() && text.length() > 0);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {         //获得焦点时，显示清空图标
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        setClearIcon(focused && length() > 0);
    }

    private void setClearIcon(boolean visible) {            //设置清空图标
        setCompoundDrawablesWithIntrinsicBounds(searchDrawable, null,
                visible ? clearDrawable : null, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){                       //清空图标，手指抬起时，清空搜索框
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                Drawable drawable = clearDrawable;
                if(drawable!=null&&event.getX()<=(getWidth()-getPaddingRight())
                    &&event.getX()>=(getWidth()-getPaddingRight()-drawable.getBounds().width())){
                    setText("");
                }
                break;
        }
        return super.onTouchEvent(event);
    }

}
