package com.example.lalala.ui.home;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.lalala.R;
import com.example.lalala.shared_info.SaveUser;

import java.util.Objects;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment {

    //    private HomeViewModel homeViewModel;
//    private MyGridView gridView;
    private TextView tv_username;
    private Button btn_upload;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

//        gridView = root.findViewById(R.id.grid_follow_field);
//        gridView.setAdapter(new FollowFieldAdapter(getActivity()));
//
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                //Intent intent =new Intent(getActivity(),);
//                //startActivity(intent);
//                Toast toast=Toast.makeText(getActivity(),"点击了"+position,Toast.LENGTH_SHORT);
//                toast.show();
//            }
//        });

        tv_username = root.findViewById(R.id.user_name);
        btn_upload = root.findViewById(R.id.btn_upload);
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, 1);
            }
        });


        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                if (uri != null) {
                    String testS = DocumentsContract.getDocumentId(uri);
                    if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                        Log.d("uri", "onActivityResult: " + uri.toString());
                        Log.d("testS", "onActivityResult: " + testS);
                        final String id = DocumentsContract.getDocumentId(uri);
                        final Uri contentUri = ContentUris.withAppendedId(
                                Uri.parse("content://downloads/public_downloads"), 25L);

                        String path = getDataColumn(Objects.requireNonNull(getActivity()), contentUri, null, null);
                        Log.d("uri", "Download: " + path);

                    } else if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                        Log.d("uri", "onActivityResult: is Media");
                    }
//                    Log.d("uri", "onActivityResult: " + uri.toString());
//                    Log.d("path", "onActivityResult: " + testS);
                    //String path = getPath(this,uri);
                }
            }
        }
    }

    public String getDataColumn(Context context, Uri uri, String selection,
                                String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

}