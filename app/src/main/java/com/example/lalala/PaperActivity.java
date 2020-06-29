package com.example.lalala;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lalala.entity.EvalEntity;
import com.example.lalala.entity.PaperSimpleData;
import com.example.lalala.entity.TagSimpleData;
import com.example.lalala.http.FinalTask;
import com.example.lalala.shared_info.SaveUser;
import com.example.lalala.tool.PixelTool;
import com.google.android.flexbox.FlexboxLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PaperActivity extends AppCompatActivity {
    private ScrollView scrollView;
    private LinearLayout linearLayout;
    private FlexboxLayout flexboxLayout_add;
    private FlexboxLayout flexboxLayout_exist;
    private FlexboxLayout flexboxLayout_rec;

    private int seconds;             //在页面的停留时间

    private Button btnTest;

    private Set<String> tagNames = new HashSet<>();            //添加的总tag，防止重复

    private Map<String, Integer> recTags = new HashMap<>();    //推荐tags id和name对应
    private Set<Integer> existTags = new HashSet<>();         //推荐的tags
    private Set<String> existTagsName = new HashSet<>();         //推荐的tags名称
    private Set<String> addTags = new HashSet<>();            //自定义的tags


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paper);

        //记录打开时的时间
        seconds = 0;
        setTime();

        //用来获取用户位置的view
        scrollView = findViewById(R.id.sv_paper);
        linearLayout = findViewById(R.id.rl_parent);

        //从当前打开的论文中获取相关信息并显示
        TextView tvPaperTitle = findViewById(R.id.tv_paper_title);
        TextView tvPaperAbstract = findViewById(R.id.tv_paper_abstract);
        TextView tvDownload = findViewById(R.id.tv_download);

        if(SaveUser.Debug){
            tvPaperTitle.setText("这是标题");
        }
        else{
            Intent intent = getIntent();
            tvPaperTitle.setText(intent.getStringExtra("title"));
        }

        tvPaperAbstract.setText(SaveUser.curPaper.getAbst());
        tvDownload.setText(SaveUser.curPaper.getResUrl());

        //初始化推荐tags的Set
        for (TagSimpleData tag : SaveUser.curPaper.getRecomTag()) {
            tagNames.add(tag.getName());
            existTags.add(tag.getId());
            existTagsName.add(tag.getName());
            recTags.put(tag.getName(), tag.getId());
        }

        //显示已有tags
        flexboxLayout_exist = findViewById(R.id.fl_tags);
        for (TagSimpleData tag : SaveUser.curPaper.getExistTag()) {
            newButton(tag.getName(),flexboxLayout_exist);
        }
        //显示推荐tags
        flexboxLayout_rec = findViewById(R.id.fl_suggest_tags);
        for (TagSimpleData tag : SaveUser.curPaper.getRecomTag()) {
            newButton(tag.getName(), flexboxLayout_rec);
        }
        //显示添加的tags
        flexboxLayout_add = findViewById(R.id.fl_add_tags);
        for (TagSimpleData tag : SaveUser.curPaper.getRecomTag()) {
            newButton(tag.getName(), flexboxLayout_add);
        }

        Button btnSubmit = findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    @Override
    protected void onDestroy() {
        //根据页面位置判断兴趣程度
        int scrollY = scrollView.getScrollY();
        int height = linearLayout.getMeasuredHeight();
        int contentHeight = getWindow().findViewById(Window.ID_ANDROID_CONTENT).getHeight();
        float interestP = 0;
        if (height <= contentHeight) {
            interestP = 0.5f;
        } else {
            interestP = (float) (scrollY / (height - contentHeight));
        }
        //根据时长判断感兴趣程度
        setTime();
        float interestT = 0;
        if (seconds >= 10) {
            interestT = 1;
        } else if (seconds > 5) {
            interestT = 0.5f + (float) ((seconds - 5) / 10);
        } else {
            interestT = (float) (seconds / 20);
        }

        //加权平均求兴趣程度
        float interests = (float) (interestP * 0.3 + interestT * 0.7);
        if (interests > 0.7) {
            interests = 2;
        } else if (interests > 0.4) {
            interests = 1.5f;
        } else {
            interests = 0;
        }

        //获取paperId
        Intent intent = getIntent();
        int paperId = intent.getIntExtra("paperId", 111);

        //添加tag的信息
        if (addTags.size() > 0) {
            SaveUser.paperNewTag.put(paperId, addTags);
        }
        if (existTags.size() > 0) {
            SaveUser.paperTag.put(paperId, existTags);
        }
        //动态更新用户的兴趣列表
        int groupId = SaveUser.curPaper.getGroupId();
        if (interests == 2) {
            SaveUser.evalPapers.add(new EvalEntity(SaveUser.userInfor.getUsr_id(), paperId, interests, new Date()));
            //当感兴趣时，将groupId添加到感兴趣列表(本来已有不添加)
            //设置用户tag更新为true
            SaveUser.updateUserTag = true;
            if (!SaveUser.userInfor.getGroupID().contains(groupId)) {
                SaveUser.userInfor.getGroupID().add(groupId);
                //如果groupId在可能感兴趣列表中，就移除
                if (SaveUser.userInfor.getCandidateGroupID().contains(groupId)) {
                    SaveUser.userInfor.getCandidateGroupID().remove((Integer) groupId);
                }else{
                    SaveUser.groupPage.put(groupId,0);
                }
            }

        } else if (interests == 1.5) {
            SaveUser.evalPapers.add(new EvalEntity(SaveUser.userInfor.getUsr_id(), paperId, interests, new Date()));
            //当可能感兴趣时，将groupId添加到可能感兴趣列表中, 要在两个列表都不存在时才添加
            if ((!SaveUser.userInfor.getGroupID().contains(groupId)) && (!SaveUser.userInfor.getCandidateGroupID().contains(groupId))) {
                SaveUser.userInfor.getCandidateGroupID().add(groupId);
                SaveUser.groupPage.put(groupId,0);
            }
        }else{

        }

        Log.d("PaperActivity", "onDestroy: before final");
        if(!SaveUser.Debug){
            FinalTask finalTask = new FinalTask();
            finalTask.execute();
        }

        Log.d("PaperActivity", "onDestroy: begin final");
        super.onDestroy();
    }

    public void Add_Tag(View view) {
        final EditText et = new EditText(this);
        et.setHint("请输入标签名");
        new AlertDialog.Builder(this).setTitle("请输入要添加的Tag名称")
                .setView(et)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String tagName = et.getText().toString();

                        if (!existTagsName.contains(tagName)) {
                            addTags.add(tagName);
                        } else {
                            existTags.add(recTags.get(tagName));
                        }
                        tagNames.add(tagName);
                        newButton(tagName, flexboxLayout_add);

                    }
                }).setNegativeButton("取消", null).show();
    }

    //在自定义标签框里添加标签
    private void newButton(final String tagName, FlexboxLayout flexboxLayout) {
        Button btn_tag = new Button(PaperActivity.this);
        btn_tag.setText(tagName);
        btn_tag.setBackgroundResource(R.drawable.tag_outline);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, PixelTool.dpToPx(PaperActivity.this, 30));
        lp.setMargins(PixelTool.dpToPx(PaperActivity.this, 5),
                PixelTool.dpToPx(PaperActivity.this, 8),
                PixelTool.dpToPx(PaperActivity.this, 5),
                PixelTool.dpToPx(PaperActivity.this, 8));

        btn_tag.setPadding(PixelTool.dpToPx(PaperActivity.this, 10),
                PixelTool.dpToPx(PaperActivity.this, 0),
                PixelTool.dpToPx(PaperActivity.this, 10),
                PixelTool.dpToPx(PaperActivity.this, 0));

        btn_tag.setTextSize(18);
        btn_tag.setLayoutParams(lp);

        switch (flexboxLayout.getId()) {
            case R.id.fl_tags:
                break;
            case R.id.fl_add_tags:
                btn_tag.setOnClickListener(new onClickAdd());
                break;
            case R.id.fl_suggest_tags:
                btn_tag.setOnClickListener(new onClickRec());
                break;
        }

        flexboxLayout.addView(btn_tag);
    }

    //用当前时间减去上一次时间
    private void setTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        seconds = hour * 3600 + minute * 60 + second * minute - seconds;
    }

    //弹出对话框
    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("提交成功!");
        builder.setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    class onClickRec implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String tagName = ((Button) v).getText().toString();
            if (tagNames.contains(tagName)) {
                Toast toast = Toast.makeText(PaperActivity.this, "标签已存在！", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                tagNames.add(tagName);
                existTags.add(recTags.get(tagName));
                newButton(tagName, flexboxLayout_add);
            }
        }
    }

    class onClickAdd implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String tagName = ((Button) v).getText().toString();
            if (!existTagsName.contains(tagName)) {
                addTags.remove(tagName);
            } else {
                existTags.remove(recTags.get(tagName));
            }
            tagNames.remove(tagName);
            flexboxLayout_add.removeView(v);
        }
    }
}
