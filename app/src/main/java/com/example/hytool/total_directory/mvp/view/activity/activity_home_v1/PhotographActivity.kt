package com.example.toolgather.mvp.view.activity.activity_home_v1

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.text.TextUtils
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.hytool.R
import com.example.hytool.databinding.ActivityPhotographBinding
import com.example.hytool.total_directory.adapter.adapter_hemo_v1.PhotographAdapter
import com.example.hytool.total_directory.base.BaseActivity
import com.example.hytool.total_directory.util.GlideEngine
import com.example.hytool.total_directory.util.GlideEngine.createGlideEngine
import com.example.hytool.total_directory.util.PhotographUtil
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.style.PictureParameterStyle
import com.stefan.hyyTool.HyTool.getContext


/**
 * @author Administrator : HYY
 * 日期 :  2020/6/24
 * 备注 :  照片选择
 */
class PhotographActivity : BaseActivity() {
    private val list: List<LocalMedia> = ArrayList()
    private var mAdapter: PhotographAdapter? = null
    private val mPictureParameterStyle: PictureParameterStyle? = null
    lateinit var binding: ActivityPhotographBinding

    override fun getLayoutId(): View {
        binding = ActivityPhotographBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun initViews() {
        binding.titleBase.tvTitle.text = "选择照片"
        binding.titleBase.ivBack.setOnClickListener(View.OnClickListener {
            finish()
        })
        mAdapter = PhotographAdapter(this@PhotographActivity, onAddPicClickListener)
        binding.recycler.layoutManager = GridLayoutManager(getContext(), 4)
        binding.recycler.adapter = mAdapter
        mAdapter!!.setOnItemClickListener { v: View?, position: Int ->
            val selectList = mAdapter!!.data
            if (selectList.size > 0) {
                val media = selectList[position]
                val mimeType = media.mimeType
                val mediaType = PictureMimeType.getMimeType(mimeType)
                when (mediaType) {
                    PictureConfig.TYPE_VIDEO ->                         // 预览视频
                        PictureSelector.create(this@PhotographActivity)
                            .themeStyle(R.style.picture_default_style)
                            .setPictureStyle(mPictureParameterStyle) // 动态自定义相册主题
                            .externalPictureVideo(if (TextUtils.isEmpty(media.androidQToPath)) media.path else media.androidQToPath)
                    PictureConfig.TYPE_AUDIO ->                         // 预览音频
                        PictureSelector.create(this@PhotographActivity)
                            .externalPictureAudio(if (PictureMimeType.isContent(media.path)) media.androidQToPath else media.path)
                    else ->                         // 预览图片 可自定长按保存路径
                        PictureSelector.create(this@PhotographActivity)
                            .themeStyle(R.style.picture_default_style) // xml设置主题
                            .setPictureStyle(mPictureParameterStyle) // 动态自定义相册主题
                            .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED) // 设置相册Activity方向，不设置默认使用系统
                            .isNotPreviewDownload(true) // 预览图片长按是否可以下载
                            .imageEngine(GlideEngine.createGlideEngine()) // 外部传入图片加载引擎，必传项
                            .openExternalPreview(position, selectList)
                }
            }
        }
    }


    private val onAddPicClickListener = PhotographAdapter.onAddPicClickListener {
        PhotographUtil.getInstance().fromGallery(this@PhotographActivity, 5, true, mAdapter!!.data)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                PictureConfig.CHOOSE_REQUEST -> {
                    // 图片选择结果回调
                    val selectList = PictureSelector.obtainMultipleResult(data)
                    //                    // 例如 LocalMedia 里面返回五种path
//                    // 1.media.getPath(); 原图path
//                    // 2.media.getCutPath();裁剪后path，需判断media.isCut();切勿直接使用
//                    // 3.media.getCompressPath();压缩后path，需判断media.isCompressed();切勿直接使用
//                    // 4.media.getOriginalPath()); media.isOriginal());为true时此字段才有值
//                    // 5.media.getAndroidQToPath();Android Q版本特有返回的字段，但如果开启了压缩或裁剪还是取裁剪或压缩路径；注意：.isAndroidQTransform 为false 此字段将返回空
//                    // 如果同时开启裁剪和压缩，则取压缩路径为准因为是先裁剪后压缩
//                    for (LocalMedia media : selectList) {
//                        Log.i(TAG, "是否压缩:" + media.isCompressed());
//                        Log.i(TAG, "压缩:" + media.getCompressPath());
//                        Log.i(TAG, "原图:" + media.getPath());
//                        Log.i(TAG, "是否裁剪:" + media.isCut());
//                        Log.i(TAG, "裁剪:" + media.getCutPath());
//                        Log.i(TAG, "是否开启原图:" + media.isOriginal());
//                        Log.i(TAG, "原图路径:" + media.getOriginalPath());
//                        Log.i(TAG, "Android Q 特有Path:" + media.getAndroidQToPath());
//                        Log.i(TAG, "宽高: " + media.getWidth() + "x" + media.getHeight());
//                        Log.i(TAG, "Size: " + media.getSize());
//
//                        // TODO 可以通过PictureSelectorExternalUtils.getExifInterface();方法获取一些额外的资源信息，如旋转角度、经纬度等信息
//                    }
                    mAdapter!!.setList(selectList)
                    mAdapter!!.notifyDataSetChanged()
                }
            }
        }
    }

    override fun success(vararg o: Any?) {
        TODO("Not yet implemented")
    }

    override fun error(vararg o: Any?) {
        TODO("Not yet implemented")
    }

    override fun initDatas() {

    }
}