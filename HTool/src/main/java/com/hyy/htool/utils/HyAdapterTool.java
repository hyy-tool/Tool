package com.hyy.htool.utils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import java.util.List;

/**
 * @Author : HYY
 * 描述:
 * 时间:      2022/05/2022/5/31
 * 软件包名:   com.hyy.htool.utils
 */
public class HyAdapterTool {
    /**
     * 必须和  com.scwang.smart:refresh-layout-kernel:2.0.1  com.scwang.smart:refresh-header-classics:2.0.1  一起使用
     * @param isRefresh  是否刷新
     * @param data   将要展示的数据
     * @param mRefreshLayout       刷新数据
     * @param mAdapter  Adapter
     */
    public static void showList(boolean isRefresh, List data, SmartRefreshLayout mRefreshLayout, BaseQuickAdapter mAdapter) {
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            mRefreshLayout.finishRefresh();
            mAdapter.setList(data);
        } else {
            mRefreshLayout.finishLoadMore();
            if (size > 0) {
                mAdapter.addData(data);
            } else {
                mRefreshLayout.finishLoadMoreWithNoMoreData();//完成加载并标记没有更多数据 1.0.4
            }
        }

    }
}
