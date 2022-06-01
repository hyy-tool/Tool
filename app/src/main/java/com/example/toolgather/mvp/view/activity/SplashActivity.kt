package com.example.toolgather.mvp.view.activity

import android.content.Intent
import android.widget.ImageView
import cn.bingoogolapple.bgabanner.BGABanner.GuideDelegate
import cn.bingoogolapple.bgabanner.BGALocalImageSize
import com.example.toolgather.Base.BaseActivity
import com.example.toolgather.R
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * @author Administrator : HYY
 * TIME    2020/6/11
 * 备注 :
 */
class SplashActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initViews() {
        if (!isTaskRoot) {
            finish()
            return
        } //判断home键返回是否点击重新启动页


//        //延时跳转
//        RxTool.delayToDo(Constant.COUNT, new OnSimpleListener() {
////            @Override
////            public void doSomething() {
////                startActivity(MainActivity.class, true);
////            }
////        });
//        mContentBanner = `$`(R.id.banner_guide_content)
        //        mContentBanner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
//            @Override
//            public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
//                Glide.with(SplashActivity.this)
//                        .load(model)
//                        .placeholder(R.drawable.holder)
//                        .error(R.drawable.holder)
//                        .centerCrop()
//                        .dontAnimate()
//                        .into(itemView);
//            }
//        });
//
//        mContentBanner.setData(Arrays.asList("网络图片路径1", "网络图片路径2", "网络图片路径3"), Arrays.asList("提示文字1", "提示文字2", "提示文字3"));
        val localImageSize = BGALocalImageSize(720, 1280, 320F, 320F)
        banner_guide_content?.setData(localImageSize, ImageView.ScaleType.CENTER_CROP,
                R.mipmap.v5,
                R.mipmap.v2,
                R.mipmap.v3,
                R.mipmap.v4,
                R.mipmap.v1,
                R.mipmap.v6,
                R.mipmap.v7,
                R.mipmap.v8)
        banner_guide_content?.setEnterSkipViewIdAndDelegate(0, R.id.tv_guide_skip, GuideDelegate { //                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            startActivity(Intent(this@SplashActivity, LogInActivity::class.java))
            finish()
        })
    }

    override fun onResume() {
        super.onResume()
        // 如果开发者的引导页主题是透明的，需要在界面可见时给背景 Banner 设置一个白色背景，避免滑动过程中两个 Banner 都设置透明度后能看到 Launcher
        banner_guide_content!!.setBackgroundResource(android.R.color.white)
    }

    override fun success(vararg o: Any) {}
    override fun error(vararg o: Any) {}
}