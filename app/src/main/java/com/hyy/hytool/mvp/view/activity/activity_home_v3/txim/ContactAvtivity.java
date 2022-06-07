package com.hyy.hytool.mvp.view.activity.activity_home_v3.txim;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hyy.htool.HyTool;
import com.hyy.hytool.R;
import com.hyy.hytool.util.DemoLog;
import com.tencent.qcloud.tim.uikit.component.TitleBarLayout;
import com.tencent.qcloud.tim.uikit.modules.contact.ContactItemBean;
import com.tencent.qcloud.tim.uikit.modules.contact.ContactLayout;
import com.tencent.qcloud.tim.uikit.modules.contact.ContactListView;
import com.tencent.qcloud.tim.uikit.utils.TUIKitConstants;

/**
 * @Author : HYY
 * 描述:
 * 时间:      2022/05/2022/5/31
 * 软件包名:   com.hyy.hytool.mvp.view.avtivity.actvity_home_v3.txim
 */
public class ContactAvtivity extends AppCompatActivity {
    /**
     * 一定要添加依赖三个
     * compile 'com.github.promeg:tinypinyin:2.0.3' // TinyPinyin核心包，约80KB
     * compile 'com.github.promeg:tinypinyin-lexicons-android-cncity:2.0.3' // 可选，适用于Android的中国地区词典
     * compile 'com.github.promeg:tinypinyin-lexicons-java-cncity:2.0.3' // 可选，适用于Java的中国地区词典
     * <p>
     * 要不然会报错找不到
     */
    private ContactLayout mContactLayout;
    private Menu mMenu;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        initViews();
    }


    protected void initViews() {
        mContactLayout = findViewById(R.id.contact_layout);
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TitleBarLayout titleBar = mContactLayout.getTitleBar();
        titleBar.setVisibility(View.GONE);

        mMenu = new Menu(ContactAvtivity.this, mContactLayout.getTitleBar(), Menu.MENU_TYPE_CONTACT);
        mContactLayout.getTitleBar().setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMenu.isShowing()) {
                    mMenu.hide();
                } else {
                    mMenu.show();
                }
            }
        });
        mContactLayout.getContactListView().setOnItemClickListener(new ContactListView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, ContactItemBean contact) {
                if (position == 0) {
                    Intent intent = new Intent(HyTool.getContext(), NewFriendActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else if (position == 1) {
//                    Intent intent = new Intent(RxTool.getContext(), GroupListActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent);
                } else if (position == 2) {
//                    Intent intent = new Intent(RxTool.getContext(), BlackListActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent);
                } else {
                    Intent intent = new Intent(HyTool.getContext(), FriendProfileActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra(TUIKitConstants.ProfileType.CONTENT, contact);
                    startActivity(intent);
                }
            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();
        // 通讯录面板的默认UI和交互初始化
        refreshData();
        DemoLog.i("TAG", "onResume");
    }

    private void refreshData() {
        // 通讯录面板的默认UI和交互初始化
        mContactLayout.initDefault();
    }
}
