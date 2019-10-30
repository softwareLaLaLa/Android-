package com.example.lalala;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.example.lalala.discussion_fragment.CommendAdapter;
import com.example.lalala.model.CommendItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DiscussionActivity extends AppCompatActivity {
    RecyclerView commendList;
    CommendAdapter commendAdapter;
    List<CommendItem> commendItems = new ArrayList<>();
    EditText inputComment;
    Button btn_submit;
    PopupWindow popupWindow;
    FloatingActionButton fab_comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initData();
        fab_comment = findViewById(R.id.fab_comment);
        fab_comment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                showPopupCommnet(null);
            }
        });
        commendList = findViewById(R.id.commendList);
        commendAdapter = new CommendAdapter(commendItems, this);
        commendList.setAdapter(commendAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        commendList.setLayoutManager(layoutManager);
    }

    public void initData(){
        for(int i=0; i<10; i++){
            List<CommendItem> reply = new ArrayList<>();
            commendItems.add(new CommendItem("usrName"+i, "commendContent"+i, null, i*i, false));
        }
    }

    public void showPopupCommnet(final CommendItem parentComment) {
        final View view = LayoutInflater.from(this).inflate(
                R.layout.comment_popupwindow, null);
        inputComment = view
                .findViewById(R.id.et_discuss);

        btn_submit = view.findViewById(R.id.tv_confirm);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, false);

        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE)
                    popupWindow.dismiss();
                return false;

            }
        });
//
        popupWindow.setFocusable(true);
        // 设置点击窗口外边窗口消失
        popupWindow.setOutsideTouchable(true);
//    popupWindow.setBackgroundDrawable(getResources().getDrawable(
//          R.drawable.popuwindow_white_bg));

        // 设置弹出窗体需要软键盘
        //popupWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        // 再设置模式，和Activity的一样，覆盖，调整大小。
        popupWindow
                .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        ColorDrawable cd = new ColorDrawable(0x000000);
        popupWindow.setBackgroundDrawable(cd);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
//    WindowManager.LayoutParams params = getWindow().getAttributes();
//    params.alpha = 0.4f;
//    getWindow().setAttributes(params);
        // 设置popWindow的显示和消失动画
//    popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        popupWindow.update();
        //popupInputMethodWindow();
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            // 在dismiss中恢复透明度
            public void onDismiss() {
//          WindowManager.LayoutParams params = getWindow().getAttributes();
//          params.alpha = 1f;
//          getWindow().setAttributes(params);
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // btn_submit.setClickable(false);
                String comment1 = inputComment.getText().toString().trim();
                Log.i("comment1--------", comment1);
                if (comment1 == null || "".equals(comment1)) {
                    Toast.makeText(DiscussionActivity.this, "请输入评论内容", Toast.LENGTH_SHORT).show();
                    return;
                }
                //调用提交评论接口
                saveDiscuss(comment1, parentComment);

                popupWindow.dismiss();
            }
        });
    }

    public void saveDiscuss(String comment, CommendItem parentComment){
        //发送添加评论的http请求。
    }
}
