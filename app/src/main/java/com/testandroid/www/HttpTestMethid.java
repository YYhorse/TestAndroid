package com.testandroid.www;

import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.testandroid.www.HttpxUtils.FileCallBack;
import com.testandroid.www.HttpxUtils.HttpxUtils;
import com.testandroid.www.HttpxUtils.SendCallBack;
import com.testandroid.www.sha256encrypt.SHA256Encrypt;

import org.xutils.common.Callback;

import java.io.File;
import java.text.SimpleDateFormat;

/**
 * Created by yy on 2017/9/28.
 */
public class HttpTestMethid {
    private void HackTokenInit() {
        //----------------时间戳计算---------------------//
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String Timedate = sDateFormat.format(new java.util.Date());
        String str = "inittoken" + PublicUrl.Serialnumber + "&" + Timedate;
        String secret = SHA256Encrypt.bin2hex(str);
        Log.e("YY", "serial_number=" + PublicUrl.Serialnumber + "\n密钥=" + secret);

        HttpxUtils.postHttp(new SendCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.e("POST", result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("POST", "失败" + ex.getMessage());
            }

            @Override
            public void onCancelled(Callback.CancelledException cex) {
                Log.e("POST", "Cancel");
            }

            @Override
            public void onFinished() {
                Log.e("POST", "Finished");
            }
        }).setUrl(PublicUrl.TokenInitUrl)
                .addBodyParameter("serial_number", PublicUrl.Serialnumber)
                .addBodyParameter("timestamp", Timedate)
                .addBodyParameter("secret", secret).send();

        HttpxUtils.getHttp(new SendCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.e("GET",result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("GET","失败"+ex.getMessage());
            }

            @Override
            public void onCancelled(Callback.CancelledException cex) {
                Log.e("GET","Cancel");
            }

            @Override
            public void onFinished() {
                Log.e("GET","Finished");
            }
        }).setUrl("http://pay.zhuhemedia.com/first/2042611279/first.json").send();
    }



    public void ClickDownloadMethod(View view){
        String url = "http://management.mealadd.com/uploads/admin_client/path/8/CanjiaV1.1.4.apk";
        String path = Environment.getExternalStorageDirectory().getPath() + "/AdMachine/CanjiaV1.1.4.apk";
        Log.e("地址",path);
        HttpxUtils.downloadFiles(new FileCallBack() {
            @Override
            public void onSuccess(File result) {
                Log.e("下载成功","完成");
                //apk下载完成后，调用系统的安装方法
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setDataAndType(Uri.fromFile(result), "application/vnd.android.package-archive");
//                startActivity(intent);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("下载错误",ex.toString());
            }

            @Override
            public void onCancelled(Callback.CancelledException cex) {

            }

            @Override
            public void onFinished() {
                Log.e("完成下载","完成下载");
            }

            @Override
            public void onWaiting() {
                Log.e("等待下载","等待下载");
            }

            @Override
            public void onStarted() {
                Log.e("开始下载","开始下载");
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                Log.e("正在下载",String.valueOf(current)+"/"+String.valueOf(total)+" "+String.valueOf(isDownloading));
            }
        }).setUrl(url)
                .setSaveFilePath(path)
                .downloadFile();
    }
}
