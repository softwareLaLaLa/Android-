package com.example.lalala;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import org.w3c.dom.Text;

public class PaperActivity extends AppCompatActivity {
    private FlexboxLayout flexboxLayout_show;
    private FlexboxLayout flexboxLayout_suggest;
    private FlexboxLayout flexboxLayout_add;
    private Button btnAddTag;
    private Button btnSubbmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paper);
        flexboxLayout_show=findViewById(R.id.fl_tags);
        flexboxLayout_suggest=findViewById(R.id.fl_suggest_tags);
        flexboxLayout_add=findViewById(R.id.fl_add_tags);
        btnAddTag=findViewById(R.id.btn_add_tag);
    }
    public void Add_Tag(View view){
        final EditText et=new EditText(this);
        new AlertDialog.Builder(this).setTitle("请输入要添加的Tag名称")
                .setView(et)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String tagName=et.getText().toString();
                        Button btn_tag=new Button(PaperActivity.this);
                        btn_tag.setText(tagName);
                        btn_tag.setBackgroundResource(R.drawable.tag_outline);
                        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,30);
                        btn_tag.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                        flexboxLayout_add.addView(btn_tag);
                    }
                }).setNegativeButton("取消",null).show();
    }


}
