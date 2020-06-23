package com.example.lalala.http;

import android.net.http.AndroidHttpClient;
import android.util.Log;

import com.android.internal.http.multipart.MultipartEntity;
import com.google.gson.Gson;

import java.io.*;
import java.lang.reflect.GenericSignatureFormatError;
import java.net.HttpURLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;


import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.entity.mime.HttpMultipartMode;

import java.util.Map;
import java.util.StringJoiner;

public class HttpHandler {
    static public final String url = "http://192.168.43.203:50001";

    static public String getUrlContent(String sUrl) throws IOException {

        StringBuffer document = new StringBuffer();
        URL url = new URL(sUrl);
        URLConnection connection = url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line = null;
        while ((line = reader.readLine()) != null) {
            document.append(line);
        }
        reader.close();
        return document.toString();
    }

    static public String doGet(String sUrl, String content) {
        String result = null;
        PrintWriter out = null;
        try {

            URL url = new URL(sUrl);
//            if (content.isEmpty()) {
//                for(String key : map.keySet()){
//
//                }
//            }
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //connection.setDoOutput(true);

            //connection.setDoInput(true);
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Accept", "application/json");

            connection.connect();

            //读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes());
                sb.append(lines);
            }
            System.out.println(sb);
            connection.disconnect();
            result = sb.toString();
            Log.d("tagRela", result);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    static public String doPost(String sUrl, String content) {
        String result = null;
        PrintWriter out = null;
        try {
            URL url = new URL(sUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();
            out = new PrintWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
            //POST请求
            out.print(content);
            out.flush();
            out.close();
            //读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes());
                sb.append(lines);
            }
            //System.out.println(sb);
            connection.disconnect();
            result = sb.toString();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(result);
        return result;
    }

    public static String doPostWithForm(String url, Map<String, String> paramsMap) {
        String result = null;
        //PrintWriter out = null;
//
//        AndroidHttpClient androidHttpClient = AndroidHttpClient.newInstance("");
//        HttpResponse httpResponse = null;
        HttpURLConnection connection = null;
        URL sUrl = null;
        try {
            sUrl = new URL(url);
            connection = (HttpURLConnection) sUrl.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.connect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, String> arguments = new HashMap<>();
        arguments.put("username", paramsMap.get("name"));
        arguments.put("password", paramsMap.get("password")); // This is a fake password obviously
        StringJoiner sj = new StringJoiner("&");
        for (Map.Entry<String, String> entry : arguments.entrySet()) {
            try {
                sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "="
                        + URLEncoder.encode(entry.getValue(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        byte[] out = sj.toString().getBytes(StandardCharsets.UTF_8);


        try (OutputStream os = connection.getOutputStream()) {
            try {
                os.write(out);
                os.flush();
                os.close();
                //读取响应
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String lines;
                StringBuffer sb = new StringBuffer("");
                while ((lines = reader.readLine()) != null) {
                    lines = new String(lines.getBytes());
                    sb.append(lines);
                }
                System.out.println(sb);
                connection.disconnect();
                result = sb.toString();
                Log.d("result", result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
