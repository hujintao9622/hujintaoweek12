package com.bawei.hujintaoweek1.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 功能:  工具类
 * 作者:  胡锦涛
 * 时间:  2019/12/2 0002 下午 1:57
 */
public class NetUtil {
    //单例封装
    private static NetUtil netUtil=new NetUtil();
    private NetUtil (){}

    public static NetUtil getInstance() {
        return netUtil;
    }
    //是否有网
    public boolean hasNet(Context context){
        //管理者
        ConnectivityManager manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //网络状态信息
        NetworkInfo info = manager.getActiveNetworkInfo();
        //判断网络
        if (info != null&&info.isAvailable()) {
            return true;
        }else {
            return false;
        }
    }
    //是否是Wifi
    public boolean isWifi(Context context){
        //管理者
        ConnectivityManager manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //网络状态信息
        NetworkInfo info = manager.getActiveNetworkInfo();
        //判断网络
        if (info != null&&info.isAvailable()&&info.getType()==ConnectivityManager.TYPE_WIFI) {
            return true;
        }else {
            return false;
        }
    }
    //是否是Mobile
    public boolean isMobile(Context context){
        //管理者
        ConnectivityManager manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //网络状态信息
        NetworkInfo info = manager.getActiveNetworkInfo();
        //判断网络
        if (info != null&&info.isAvailable()&&info.getType()==ConnectivityManager.TYPE_MOBILE) {
            return true;
        }else {
            return false;
        }
    }
    //获取数据
    @SuppressLint("StaticFieldLeak")
    public void getJson(final String urlpath, final MyCallBack myCallBack){
        new AsyncTask<Void, Void, String>() {

            private String s;
            private InputStream inputStream;

            @Override
            protected void onPostExecute(String value) {
                if (value==null){
                    myCallBack.onfailure(new Throwable("请求失败"));
                }else {
                    myCallBack.onGetJson(value);
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlpath);
                    HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                    //设置请求方式
                    httpURLConnection.setRequestMethod("GET");
                    //设置连接读取超时
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setReadTimeout(5000);
                    //开启连接
                    httpURLConnection.connect();
                    //是否请求成功
                    if (httpURLConnection.getResponseCode()==200){
                        //获取输入流
                        inputStream = httpURLConnection.getInputStream();
                        //流转字符串
                        s = io2String(inputStream);
                    }else {
                        Log.i("xx","请求失败");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    //关流
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return s;
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private String io2String(InputStream inputStream) throws IOException {
        //三件套
        int len=-1;
        byte[]bytes=new byte[1024];
        ByteArrayOutputStream b=new ByteArrayOutputStream();
        while ((len=inputStream.read(bytes))!=-1){
            b.write(bytes,0,len);
        }
        byte[] bytes1 = b.toByteArray();
        String s = new String(bytes1);
        return s;
    }
//获取图片
    @SuppressLint("StaticFieldLeak")
    public void getBitmap(final String bitpath, final ImageView img){
        new AsyncTask<Void, Void, Bitmap>() {

            private Bitmap s;
            private InputStream inputStream;

            @Override
            protected void onPostExecute(Bitmap value) {
               img.setImageBitmap(value);
            }

            @Override
            protected Bitmap doInBackground(Void... voids) {
                try {
                    URL url = new URL(bitpath);
                    HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                    //设置请求方式
                    httpURLConnection.setRequestMethod("GET");
                    //设置连接读取超时
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setReadTimeout(5000);
                    //开启连接
                    httpURLConnection.connect();
                    //是否请求成功
                    if (httpURLConnection.getResponseCode()==200){
                        //获取输入流
                        inputStream = httpURLConnection.getInputStream();
                        //流转图片
                        s = io2Bitmap(inputStream);
                    }else {
                        Log.i("xx","请求失败");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    //关流
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return s;
            }
        }.execute();
    }

    private Bitmap io2Bitmap(InputStream inputStream) {
        return BitmapFactory.decodeStream(inputStream);
    }

    public interface MyCallBack{
        //成功
        void onGetJson(String json);
        //失败
        void onfailure(Throwable e);
    }
}
