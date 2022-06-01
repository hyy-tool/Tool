package com.example.toolgather.mvp.view.activity.activity_home_v3.txIm;


import android.content.Intent;
import android.text.TextUtils;

import com.example.toolgather.Base.BaseActivity;
import com.example.toolgather.mvp.view.MainActivity;
import com.example.toolgather.R;
import com.example.toolgather.Utils.Constants;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;
import com.tencent.qcloud.tim.uikit.modules.contact.ContactItemBean;
import com.tencent.qcloud.tim.uikit.modules.contact.FriendProfileLayout;
import com.tencent.qcloud.tim.uikit.utils.TUIKitConstants;

public class FriendProfileActivity extends BaseActivity {
    private FriendProfileLayout layout;

    @Override
    public int getLayoutId() {
        return R.layout.contact_friend_profile_activity;
    }

    @Override
    protected void initViews() {
        layout = $(R.id.friend_profile);
        layout.initData(getIntent().getSerializableExtra(TUIKitConstants.ProfileType.CONTENT));
        layout.setOnButtonClickListener(new FriendProfileLayout.OnButtonClickListener() {
            @Override
            public void onStartConversationClick(ContactItemBean info) {
                ChatInfo chatInfo = new ChatInfo();
                chatInfo.setType(TIMConversationType.C2C);
                chatInfo.setId(info.getId());
                String chatName = info.getId();
                if (!TextUtils.isEmpty(info.getRemark())) {
                    chatName = info.getRemark();
                } else if (!TextUtils.isEmpty(info.getNickname())) {
                    chatName = info.getNickname();
                }
                chatInfo.setChatName(chatName);
                Intent intent = new Intent(FriendProfileActivity.this, ChatActivity.class);
                intent.putExtra(Constants.CHAT_INFO, chatInfo);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }

            @Override
            public void onDeleteFriendClick(String id) {
                Intent intent = new Intent(FriendProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void success(Object... o) {

    }

    @Override
    public void error(Object... o) {

    }
}
