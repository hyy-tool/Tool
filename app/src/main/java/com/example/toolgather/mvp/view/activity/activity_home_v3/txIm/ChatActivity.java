package com.example.toolgather.mvp.view.activity.activity_home_v3.txIm;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.toolgather.Base.BaseActivity;
import com.example.toolgather.mvp.view.activity.SplashActivity;
import com.example.toolgather.R;
import com.example.toolgather.Utils.Constants;
import com.example.toolgather.Utils.SoftHideKeyBoardUtil;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;

import java.util.Set;

public class ChatActivity extends BaseActivity {
    private static final String TAG = ChatActivity.class.getSimpleName();
    private ChatFragment mChatFragment;
    private ChatInfo mChatInfo;

    @Override
    public int getLayoutId() {
        return R.layout.chat_activity;

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        chat(intent);
    }

    @Override
    protected void onResume() {
        Log.e(TAG, "modifySelfProfile");
        super.onResume();
    }

    @Override
    protected void initViews() {
        //输入法遮挡键盘输入
        SoftHideKeyBoardUtil.assistActivity(this);
        chat(getIntent());
    }

    @Override
    public void success(Object... o) {

    }

    @Override
    public void error(Object... o) {

    }



    private void chat(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            Uri uri = intent.getData();
            if (uri != null) {
                // 离线推送测试代码，oppo scheme url解析
                Set<String> set = uri.getQueryParameterNames();
                if (set != null) {
                    for (String key : set) {
                        String value = uri.getQueryParameter(key);
                    }
                }
            }
            startSplashActivity();
        } else {

            // 离线推送测试代码，华为和oppo可以通过在控制台设置打开应用内界面为ChatActivity来测试发送的ext数据
            String ext = bundle.getString("ext");

            Set<String> set = bundle.keySet();
            if (set != null) {
                for (String key : set) {
                    String value = bundle.getString(key);
                }
            }
            // 离线推送测试代码结束

            mChatInfo = (ChatInfo) bundle.getSerializable(Constants.CHAT_INFO);
            if (mChatInfo == null) {
                startSplashActivity();
                return;
            }
            mChatFragment = new ChatFragment();
            mChatFragment.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.empty_view, mChatFragment).commitAllowingStateLoss();
        }
    }

    private void startSplashActivity() {
        Intent intent = new Intent(ChatActivity.this, SplashActivity.class);
        startActivity(intent);
        finish();
    }
}
