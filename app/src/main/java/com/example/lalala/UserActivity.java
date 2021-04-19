//package com.example.lalala;
//
//import android.os.Bundle;
//
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.google.android.material.snackbar.Snackbar;
//
//import android.view.View;
//
//import androidx.navigation.NavController;
//import androidx.navigation.Navigation;
//import androidx.navigation.ui.AppBarConfiguration;
//import androidx.navigation.ui.NavigationUI;
//
//import com.google.android.material.navigation.NavigationView;
//
//import androidx.drawerlayout.widget.DrawerLayout;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//
//import android.view.Menu;
//
//public class UserActivity extends AppCompatActivity {
//
//    private AppBarConfiguration mAppBarConfiguration;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_user);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
////        //邮件图标
////        FloatingActionButton fab = findViewById(R.id.fab);
////        fab.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
////            }
////        });
//
//        //抽屉布局
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        //导航视图
//        NavigationView navigationView = findViewById(R.id.nav_view);
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.nav_follow, R.id.nav_collection,
//                R.id.nav_setting, R.id.nav_share, R.id.nav_send)
//                .setDrawerLayout(drawer)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        //getMenuInflater().inflate(R.menu.user, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp();
//    }
//}
package com.example.lalala;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.lalala.shared_info.SaveUser;
import com.example.lalala.ui.search.SearchActivity;
import com.google.android.material.navigation.NavigationView;


public class UserActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private TextView tvUsername;
    private DrawerLayout drawer;
    NavigationView navigationView;

    //private TextView tvIdentity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //SharedPreferences sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);

        //抽屉布局
        drawer = findViewById(R.id.drawer_layout);
        //导航视图
        navigationView = findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener(this);

        View headerLayout = navigationView.inflateHeaderView(R.layout.nav_header_user2);
        tvUsername = headerLayout.findViewById(R.id.et_header_username);

        //如果用户信息为空(刚注册/登录),就获取信息
//        if (SaveUser.userInfor == null) {
//            UserInfoTask userInfoTask = new UserInfoTask();
//            userInfoTask.setMessageResponse(UserActivity.this);
//            //userInfoTask.execute(sharedPreferences.getString("username", "username"));
//            userInfoTask.execute(SaveUser.username);
//            try {
//                userInfoTask.get();
//            } catch (ExecutionException | InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_homeP, R.id.nav_home, R.id.nav_follow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.user, menu);
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.search,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.top_search:
                Intent intent = new Intent(UserActivity.this, SearchActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onDestroy() {
        Log.d("UserActivity", "onDestroy: asdasdsad");
        super.onDestroy();
    }

//    @Override
//    public void onReceived(String resJson) {
//        Log.d("UserActivity", "onReceived!");
//        Gson gson = new Gson();
//        SaveUser.userInfor = gson.fromJson(resJson, UserInfor.class);
//        BrowseFragment.userInfor=SaveUser.userInfor;
//        tvUsername.setText(SaveUser.userInfor.getName());
//        if(SaveUser.userInfor==null){
//            Log.d("User1", "User is null");
//        }else {
//            Log.d("User1", "User is not null");
//        }
//        //Log.d("UserActivity", "onCreate: before initial!");
//        //如果用户初始化信息不为空(刚注册)
//        if(SaveUser.initial){
//            while(SaveUser.initialUserTagData==null){
//
//            }
//            //Log.d("id", "onReceived: "+SaveUser.userInfor.getUsr_id());
//            SaveUser.initialUserTagData.setUsr_id(SaveUser.userInfor.getUsr_id());
//            InitTask initTask =  new InitTask();
//            initTask.execute();
//            //Log.d("UserActivity", "onCreate: begin initial!");
//            try {
//                initTask.get();
//
//            } catch (ExecutionException | InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        if(SaveUser.userInfor==null){
//            Log.d("User2", "User is null");
//        }else {
//            Log.d("User2", "User is not null");
//        }
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_homeP, R.id.nav_home, R.id.nav_follow)
//                .setDrawerLayout(drawer)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
//    }

}
