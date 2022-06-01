package com.hyy.hytool.mvp.view.avtivity.actvity_home_v3.txim;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hyy.hytool.R;
import com.hyy.hytool.mvp.view.SplashActivity;
import com.hyy.hytool.util.Constants;
import com.hyy.hytool.util.SoftHideKeyBoardUtil;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;

import java.util.Set;

/**
 * @Author : HYY
 * 描述:
 * 时间:      2022/05/2022/5/31
 * 软件包名:   com.hyy.hytool.mvp.view.avtivity.actvity_home_v3.txim
 */
public class ChatActivity extends AppCompatActivity {
    private static final String TAG = ChatActivity.class.getSimpleName();
    private ChatFragment mChatFragment;
    private ChatInfo mChatInfo;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);
        initViews();
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

    protected void initViews() {
        //输入法遮挡键盘输入
        SoftHideKeyBoardUtil.assistActivity(this);
        chat(getIntent());
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
