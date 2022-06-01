package com.hyy.hytool.mvp.view.avtivity

import android.content.Intent
import android.view.View
import android.widget.ImageView
import cn.bingoogolapple.bgabanner.BGABanner
import cn.bingoogolapple.bgabanner.BGALocalImageSize
import com.hyy.hytool.R
import com.hyy.hytool.base.BaseActivity
import com.hyy.hytool.databinding.ActivitySplashBinding

/**
 * @Author : HYY
 * 描述:      引导页
 * 时间:      2022/05/2022/5/31
 * 软件包名:   com.hyy.hytool.mvp.view
 */
class SplashActivity : BaseActivity() {
    lateinit var binding: ActivitySplashBinding
    override fun getLayoutId(): View {
        binding = ActivitySplashBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initData() {
        //判断home键返回是否点击重新启动页
        if (!isTaskRoot) {
            finish()
            return
        }
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
        binding.bannerGuideContent.setData(
            localImageSize, ImageView.ScaleType.CENTER_CROP,
            R.mipmap.v5,
            R.mipmap.v2,
            R.mipmap.v3,
            R.mipmap.v4,
            R.mipmap.v1,
            R.mipmap.v6,
            R.mipmap.v7,
            R.mipmap.v8
        )
        binding.bannerGuideContent.setEnterSkipViewIdAndDelegate(
            0,
            R.id.tv_guide_skip,
            BGABanner.GuideDelegate { //                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                startActivity(Intent(this@SplashActivity, LogInActivity::class.java))
                finish()
            })
    }

    override fun onResume() {
        super.onResume()
        // 如果开发者的引导页主题是透明的，需要在界面可见时给背景 Banner 设置一个白色背景，避免滑动过程中两个 Banner 都设置透明度后能看到 Launcher
        binding.bannerGuideContent.setBackgroundResource(android.R.color.white)
    }

    override fun success(vararg o: Any?) {
    }

    override fun error(vararg o: Any?) {
    }
}