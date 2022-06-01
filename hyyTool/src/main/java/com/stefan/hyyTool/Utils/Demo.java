package com.stefan.hyyTool.Utils;

import android.app.Activity;
import android.content.Intent;

/**
 * @Author : HYY
 * 描述:
 * 时间:      2022/05/2022/5/11
 * 软件包名:   com.stefan.hyyTool.Utils
 */
public class Demo {
    public void startActivity(Class clazz, boolean isFinish, Activity activity) {
        Intent intent = new Intent(activity, clazz);
        activity.startActivity(intent);
        if (isFinish) {
            activity.finish();
        }
    }
}
