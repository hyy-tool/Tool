package com.hyy.hytool.util;

import com.hyy.hytool.ToolGatherApp;
import com.hyy.hytool.mvp.modile.LoginBean;
import com.hyy.hytool.mvp.modile.LoginBeanDao;

import java.util.List;

/**
 * @Author : HYY
 * 描述:
 * 时间:      2022/05/2022/5/31
 * 软件包名:   com.hyy.hytool.util
 */
public class LogInGreenDao {
    /**
     * 添加数据
     *
     * @param shop
     */
    public static void insertAddLogin(LoginBean shop) {
        ToolGatherApp.getStorageDao().getLoginBeanDao().insert(shop);
    }

    /**
     * 添加数据，如果有重复则覆盖
     *
     * @param shop
     */
    public static void insertLogIn(LoginBean shop) {
        ToolGatherApp.getStorageDao().getLoginBeanDao().insertOrReplace(shop);
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public static void deleteLogIn(long id) {
        ToolGatherApp.getStorageDao().getLoginBeanDao().deleteByKey(id);
    }

    /**
     * 更新数据
     *
     * @param shop
     */
    public static void updateLogIn(LoginBean shop) {
        ToolGatherApp.getStorageDao().getLoginBeanDao().update(shop);
    }

    /**
     * 删除全部
     */
    public static void deleteLogInAll() {
        ToolGatherApp.getStorageDao().getLoginBeanDao().deleteAll();
    }


    /**
     * 查询条件为Type=TYPE_LOVE的数据
     *
     * @return
     */
    public static List<LoginBean> queryLogIn() {
        return ToolGatherApp.getStorageDao().getLoginBeanDao().queryBuilder().where(LoginBeanDao.Properties.Type.eq(LoginBean.TYPE_STORAGE)).list();
    }

    /**
     * 查询条件为Type=TYPE_LOVE的数据
     *
     * @return
     */
    public static List<LoginBean> inquireLogInName(String name) {
        return ToolGatherApp.getStorageDao().getLoginBeanDao().queryBuilder().where(LoginBeanDao.Properties.Name.like("%" + name + "%")).list();
    }
}
