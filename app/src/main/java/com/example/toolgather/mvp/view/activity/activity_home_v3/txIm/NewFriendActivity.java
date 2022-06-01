package com.example.toolgather.mvp.view.activity.activity_home_v3.txIm;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.toolgather.Adapter.adapter_hemo_v1.NewFriendListAdapter;
import com.example.toolgather.Base.BaseActivity;
import com.example.toolgather.R;
import com.example.toolgather.Utils.DemoLog;
import com.tencent.imsdk.TIMFriendshipManager;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.friendship.TIMFriendPendencyItem;
import com.tencent.imsdk.friendship.TIMFriendPendencyRequest;
import com.tencent.imsdk.friendship.TIMFriendPendencyResponse;
import com.tencent.imsdk.friendship.TIMPendencyType;
import com.tencent.qcloud.tim.uikit.component.TitleBarLayout;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator : HYY
 * 日期 :  2020/6/19
 * 备注 : 新的联系人
 */
public class NewFriendActivity extends BaseActivity {
    private static final String TAG = NewFriendActivity.class.getSimpleName();
    private TitleBarLayout mTitleBar;
    private ListView mNewFriendLv;
    private NewFriendListAdapter mAdapter;
    private TextView mEmptyView;
    private final List<TIMFriendPendencyItem> mList = new ArrayList<>();


    @Override
    public int getLayoutId() {
        return R.layout.contact_new_friend_activity;
    }

    @Override
    protected void initViews() {
        mTitleBar = findViewById(R.id.new_friend_titlebar);
        mTitleBar.setTitle(getResources().getString(R.string.new_friend), TitleBarLayout.POSITION.LEFT);
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitleBar.setTitle(getResources().getString(R.string.add_friend), TitleBarLayout.POSITION.RIGHT);
        mTitleBar.getRightIcon().setVisibility(View.GONE);
        mTitleBar.setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewFriendActivity.this, AddMoreActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("isGroup", false);
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

    @Override
    protected void onResume() {
        super.onResume();
        initPendency();
    }

    private void initPendency() {
        final TIMFriendPendencyRequest timFriendPendencyRequest = new TIMFriendPendencyRequest();
        timFriendPendencyRequest.setTimPendencyGetType(TIMPendencyType.TIM_PENDENCY_COME_IN);
        timFriendPendencyRequest.setSeq(0);
        timFriendPendencyRequest.setTimestamp(0);
        timFriendPendencyRequest.setNumPerPage(10);
        TIMFriendshipManager.getInstance().getPendencyList(timFriendPendencyRequest, new TIMValueCallBack<TIMFriendPendencyResponse>() {
            @Override
            public void onError(int i, String s) {
                DemoLog.e(TAG, "getPendencyList err code = " + i + ", desc = " + s);
                ToastUtil.toastShortMessage("Error code = " + i + ", desc = " + s);
            }

            @Override
            public void onSuccess(TIMFriendPendencyResponse timFriendPendencyResponse) {
                DemoLog.i(TAG, "getPendencyList success result = " + timFriendPendencyResponse.toString());
                if (timFriendPendencyResponse.getItems() != null) {
                    if (timFriendPendencyResponse.getItems().size() == 0) {
                        mEmptyView.setText(getResources().getString(R.string.no_friend_apply));
                        mNewFriendLv.setVisibility(View.GONE);
                        mEmptyView.setVisibility(View.VISIBLE);
                        return;
                    }
                }
                mNewFriendLv.setVisibility(View.VISIBLE);
                mList.clear();
                mList.addAll(timFriendPendencyResponse.getItems());
                mAdapter = new NewFriendListAdapter(NewFriendActivity.this, R.layout.contact_new_friend_item, mList);
                mNewFriendLv.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }
        });
    }
}
