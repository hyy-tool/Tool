package com.hyy.hytool.mvp.view.activity

import android.view.View
import com.hyy.htool.utils.HySpTool.getBoolean
import com.hyy.htool.utils.HySpTool.putBoolean
import com.hyy.htool.utils.HyToast
import com.hyy.htool.utils.SkipActivity
import com.hyy.hytool.R
import com.hyy.hytool.base.BaseActivity
import com.hyy.hytool.databinding.ActivityLoginBinding
import com.hyy.hytool.mvp.MainActivity
import com.hyy.hytool.mvp.view.activity.activity_home_v1.SignInActivity
import com.hyy.hytool.util.LogInGreenDao

/**
 * @Author : HYY
 * 描述:
 * 时间:      2022/05/2022/5/31
 * 软件包名:   com.hyy.hytool.mvp.view
 */
class LogInActivity : BaseActivity(), View.OnClickListener {
    private var mName: String? = null
    private var mPassword: String? = null

    lateinit var binding: ActivityLoginBinding
    override fun getLayoutId(): View {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initData() {
        val login = getBoolean(this, "login")
        if (login) {
            SkipActivity.startActivity(MainActivity::class.java, true, this)
        } else {
            binding.tvLogin.setOnClickListener(this)
            binding.tvSignIn.setOnClickListener(this)
        }
    }
    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_sign_in -> SkipActivity.startActivity(SignInActivity::class.java, false, this)
            R.id.tv_login -> {
                val Passname = binding.etName.text.toString()
                val pass_word = binding.passWord.text.toString()
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
                SkipActivity.startActivity(MainActivity::class.java, true, this)
            }
            else -> {
            }
        }
    }
    override fun success(vararg o: Any?) {}
    override fun error(vararg o: Any?) {}
}