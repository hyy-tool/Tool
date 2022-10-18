package com.hyy.htool.utils;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author : HYY
 * 描述:
 * 时间:      2022/06/2022/6/8
 * 软件包名:   com.hyy.htool.utils
 */
public class Demo {

    //保存bitmap到fileName，文件名为bitmapName.
    public static String saveBitmap(String filePath, String bitmapName, Bitmap bitmap){
        FileOutputStream outputStream=null;
        try{
            File file = new File(filePath, bitmapName);
            if (!file.exists())file.createNewFile();
            //打印文件路径名
            Log.d("MainActivity:",file.getPath());

            outputStream = new FileOutputStream(file);
            // 将图像写到流中
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
            return file.getAbsolutePath();
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
