package com.example.hytool.total_directory.mvp.module;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;

/**
 * @author Administrator : HYY
 * 日期 :  2020/6/19
 * 备注 :
 */

@Entity
public class LoginBean {
    public static final int TYPE_STORAGE = 0x01;
    @Id(autoincrement = true)
    private Long id;

    @NotNull  // @NotNull 设置数据库表当前列不能为空
    @Unique  //唯一
    private String name;//登录姓名


    private String password;//登录密码
    private int type;
    @Generated(hash = 432324889)
    public LoginBean(Long id, @NotNull String name, String password, int type) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.type = type;
    }
    @Generated(hash = 1112702939)
    public LoginBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }

}
