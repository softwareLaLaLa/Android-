package com.example.lalala.shared_info;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.lalala.entity.EvalEntity;
import com.example.lalala.entity.InitialUserTagData;
import com.example.lalala.entity.PaperData;
import com.example.lalala.entity.PaperSimpleData;
import com.example.lalala.entity.TagSimpleData;
import com.example.lalala.entity.UserInfor;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SaveUser {

    public static boolean Debug = true;   //不连接服务器

    public static String username;
    public static UserInfor userInfor = new UserInfor();
    public static ArrayList<PaperSimpleData> rePapers = new ArrayList<>();              //获取的感兴趣论文
    public static ArrayList<PaperSimpleData> rePapersC = new ArrayList<>();             //获取的可能感兴趣论文
    public static ArrayList<PaperSimpleData> hotPapers = new ArrayList<>();             //获取的热榜论文
    public static ArrayList<PaperSimpleData> browseHistory = new ArrayList<>();   //要提交的历史记录
    public static Map<Integer, Integer> groupPage = new HashMap<>();                //每个组对应的页数
    public static PaperData curPaper = new PaperData();                                   //当前打开的论文
    public static boolean updateUserTag = false;                             //是否需要更新用户tag信息
    public static ArrayList<EvalEntity> evalPapers = new ArrayList<>();    //用户的评估列表
    public static Map<Integer, Set<Integer>> paperTag = new HashMap<>();        //给论文添加已有tag
    public static Map<Integer, Set<String>> paperNewTag = new HashMap<>();     //给论文添加新tag
    public static boolean initial = false;
    public static InitialUserTagData initialUserTagData = null;
    public static ArrayList<TagSimpleData> reTags = new ArrayList<>();

}
