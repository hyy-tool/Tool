package com.example.toolgather.mvp.view.activity.activity_home_v1

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.toolgather.Base.BaseActivity
import com.example.toolgather.R
import com.example.toolgather.Utils.SvgaUtils
import kotlinx.android.synthetic.main.activity_svga.*
import java.util.*

/**
 * @author Administrator : HYY
 * TIME    2020/6/13
 * 备注 :  Svga 动画
 */
class SvgaActivity : BaseActivity(), View.OnClickListener {
    private var svgaUtils: SvgaUtils? = null
    private val svga_string = ArrayList<String>()
    private var num = 0
    override fun getLayoutId(): Int {
        return R.layout.activity_svga
    }

    override fun initViews() {
        iv_back?.setOnClickListener(this)
        tv_svge?.setOnClickListener(this)
        svgaUtils = SvgaUtils(this@SvgaActivity, svga_Image)
        svgaUtils!!.initAnimator()
        svgaUtils!!.startAnimator("XZ")
        svga_string.add("BJDWL")
        svga_string.add("CB")
        svga_string.add("CPQ")
        svga_string.add("DG")
        svga_string.add("HBC")
        svga_string.add("HDBS")
        svga_string.add("MSLD")
        svga_string.add("MSMX")
        svga_string.add("PW")
        svga_string.add("QZQY")
        svga_string.add("TXH")
        svga_string.add("XKPC")
        svga_string.add("XZ")
    }

    override fun success(vararg o: Any) {}
    override fun error(vararg o: Any) {}
    override fun initImmersionBar() {
        super.initImmersionBar()
        Glide.with(this).asBitmap().load("")
                .apply(RequestOptions().placeholder(R.drawable.shape_gradient))
                .into(title_iv!!)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.iv_back -> finish()
            R.id.tv_svge -> {
                val s = svga_string[num]
                if (num == 12) {
                    num = 0
                } else {
                    num++
                }
                svgaUtils = SvgaUtils(this@SvgaActivity, svga_Image)
                svgaUtils!!.initAnimator()
                svgaUtils!!.startAnimator(s)
            }
            else -> {
            }
        }
    }
}