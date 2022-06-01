package com.example.toolgather.mvp.view.activity

import android.view.View
import com.example.toolgather.Base.BaseActivity
import com.example.toolgather.mvp.view.MainActivity
import com.example.toolgather.mvp.view.activity.activity_home_v1.SignInActivity
import com.example.toolgather.R
import com.example.toolgather.Utils.LogInGreenDao
import com.stefan.hyyTool.Utils.HySpTool.getBoolean
import com.stefan.hyyTool.Utils.HySpTool.putBoolean
import com.stefan.hyyTool.Utils.HyToast
import com.stefan.hyyTool.Utils.SkipActivity
import kotlinx.android.synthetic.main.activity_login.*

/**
 * @author Administrator : HYY
 * 日期 :  2020/6/19
 * 备注 :  登录注册界面
 */
class LogInActivity : BaseActivity(), View.OnClickListener {
    private var mName: String? = null
    private var mPassword: String? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initViews() {
        val login = getBoolean(this, "login")
        if (login) {
            SkipActivity.startActivity(MainActivity::class.java, true,this)
        } else {
            tv_login.setOnClickListener(this)
            tv_sign_in.setOnClickListener(this)
        }
   

    }

    override fun success(vararg o: Any) {}
    override fun error(vararg o: Any) {}
    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_sign_in ->  SkipActivity.startActivity(SignInActivity::class.java, false,this)
            R.id.tv_login -> {
                val Passname = et_name!!.text.toString()
                val pass_word = pass_word!!.text.toString()
                if (Passname == "") {
                   HyToast.error("请输入用户名")
                    return
                }
                if (pass_word == "") {
                    HyToast.error("请输入密码")
                    return
                }
                val listName = LogInGreenDao.inquireLogInName(Passname) //按名字查询数据库
                if (listName.size == 0) {
                    HyToast.error("账户不存在")
                    return
                }
                var i = 0
                while (i < listName.size) {
                    mName = listName[i].name
                    mPassword = listName[i].password
                    i++
                }
                if (Passname != mName) {
                    HyToast.error("用户名不正确")
                    return
                }
                if (pass_word != mPassword) {
                    HyToast.error("密码不正确")
                    return
                }
                success("登录成功")
                putBoolean(this, "login", true)
                SkipActivity.startActivity(MainActivity::class.java, true,this)

            }
            else -> {
            }
        }
    }
}