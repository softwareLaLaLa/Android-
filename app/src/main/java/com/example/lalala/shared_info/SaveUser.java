package com.example.lalala.shared_info;

import com.example.lalala.entity.PaperSimpleData;
import com.example.lalala.entity.SquarePaperData;
import com.example.lalala.entity.UserHistoryEntity;
import com.example.lalala.entity.UserInfoEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SaveUser {

    public static UserInfoEntity userInfoEntity = new UserInfoEntity();
    //广场与热榜的推荐论文数据
    public static List<PaperSimpleData> hotPapers = new ArrayList<>();
    public static List<SquarePaperData> squarePaper = new ArrayList<>();
    public static int squarePaperPageNum = -1;
    public static int pageNum = -1;
    public static PaperSimpleData currentPaper;

    //用户侧栏相关数据
    public static List<UserHistoryEntity> browseHistory = new ArrayList<>();   //要提交的历史记录
    public static List<PaperSimpleData> browsePaperData = new ArrayList<>();   //显示的历史记录信息
    public static Set<Integer> userSubscribe = new HashSet<>();
    public static Set<Integer> userDislike = new HashSet<>();

    //用户为论文添加的标签数据
    public static Map<Integer, Set<String>> paperTagData = new HashMap<>();
    //用户的历史记录
    public static List<UserHistoryEntity> userHistoryEntities = new ArrayList<>();
}
