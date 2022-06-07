package com.hyy.hytool.mvp.view.activity.activity_home_v3.txim;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.gyf.immersionbar.ImmersionBar;
import com.hyy.hytool.R;
import com.hyy.hytool.util.Constants;
import com.tencent.imsdk.TIMFriendshipManager;
import com.tencent.imsdk.TIMUserProfile;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.qcloud.tim.uikit.base.BaseFragment;
import com.tencent.qcloud.tim.uikit.component.AudioPlayer;
import com.tencent.qcloud.tim.uikit.component.TitleBarLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.ChatLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;
import com.tencent.qcloud.tim.uikit.modules.chat.layout.message.MessageLayout;
import com.tencent.qcloud.tim.uikit.modules.message.MessageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : HYY
 * 描述:
 * 时间:      2022/05/2022/5/31
 * 软件包名:   com.hyy.hytool.mvp.view.avtivity.actvity_home_v3.txim
 */
public class ChatFragment extends BaseFragment {
    private View mBaseView;
    private ChatLayout mChatLayout;
    private TitleBarLayout mTitleBar;
    private ChatInfo mChatInfo;
    private ImageView title_iv;
    private TextView tv_nikenname;
    private ImageView mIv_back;
    private String mId;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.chat_fragment, container, false);
        return mBaseView;
    }

    private void initView() {
        mIv_back = mBaseView.findViewById(R.id.iv_back);
        mIv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        title_iv = mBaseView.findViewById(R.id.title_iv);

        //从布局文件中获取聊天面板组件
        mChatLayout = mBaseView.findViewById(R.id.chat_layout);
        //单聊组件的默认UI和交互初始化
        mChatLayout.initDefault();
        MessageLayout messageLayout = mChatLayout.getMessageLayout();
        messageLayout.setBackground(getResources().getDrawable(R.mipmap.v4));  //设置聊天背景
        messageLayout.setNameFontSize(12);//设置名字大小
        messageLayout.setNameFontColor(getResources().getColor(R.color.F40736));//设置名字颜色
        messageLayout.setLeftNameVisibility(1);//设置左面昵称是否显示{你他妈敢在坑点不}
        messageLayout.setRightNameVisibility(1);//设置右面昵称是否显示  {你他妈敢在坑点不}
        int leftNameVisibility = messageLayout.getLeftNameVisibility();//获取左面名字显示状态

        /*
         * 需要聊天的基本信息
         */
        mChatLayout.setChatInfo(mChatInfo);
        //获取单聊面板的标题栏
        mTitleBar = mChatLayout.getTitleBar();
        mTitleBar.setVisibility(View.GONE);
        //设置标题名字
        tv_nikenname = mBaseView.findViewById(R.id.tv_nikenname);

        // 聊天界面设置昵称


        mChatLayout.getMessageLayout().setOnItemClickListener(new MessageLayout.OnItemClickListener() {
            @Override
            public void onMessageLongClick(View view, int position, MessageInfo messageInfo) {
                //因为adapter中第一条为加载条目，位置需减1
                mChatLayout.getMessageLayout().showItemPopMenu(position - 1, messageInfo, view);
            }

            @Override
            public void onUserIconClick(View view, int position, MessageInfo messageInfo) {
                if (null == messageInfo) {
                    return;
                }
                ChatInfo info = new ChatInfo();
                info.setId(messageInfo.getFromUser());

                //获取服务器保存的自己的资料
                TIMFriendshipManager.getInstance().getSelfProfile(new TIMValueCallBack<TIMUserProfile>() {
                    @Override
                    public void onError(int code, String desc) {
                        //错误码 code 和错误描述 desc，可用于定位请求失败原因
                        //错误码 code 列表请参见错误码表
                    }

                    @Override
                    public void onSuccess(TIMUserProfile result) {
//                        String uid = SPUtils.getInstance().getString("uid");
                        //点击头像查看详细信息
                    }
                });

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        ImmersionBar.with(this).navigationBarColor(R.color.colorPrimary).init();
        Bundle bundle = getArguments();
        mChatInfo = (ChatInfo) bundle.getSerializable(Constants.CHAT_INFO);
        if (mChatInfo == null) {
            return;
        }


        mId = mChatInfo.getId();
        //待获取用户资料的用户列表
        List<String> users = new ArrayList<String>();
        users.add(mId);
        //获取用户资料
        TIMFriendshipManager.getInstance().getUsersProfile(users, true, new TIMValueCallBack<List<TIMUserProfile>>() {
            @Override
            public void onError(int code, String desc) {
                //错误码 code 和错误描述 desc，可用于定位请求失败原因
                //错误码 code 列表请参见错误码表
            }

            @Override
            public void onSuccess(List<TIMUserProfile> result) {
                for (TIMUserProfile res : result) {
                    tv_nikenname.setText(res.getNickName());
                }
            }
        });


        initView();

//         TODO 通过api设置ChatLayout各种属性的样例
//        ChatLayoutHelper helper = new ChatLayoutHelper(getActivity());
//        helper.customizeChatLayout(mChatLayout);
    }

    @Override
    public void onPause() {
        super.onPause();
        AudioPlayer.getInstance().stopPlay();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mChatLayout != null) {
            mChatLayout.exitChat();
        }
    }
}
