package com.hyy.hytool.mvp.view.fragment.fragment_home_v3

import android.view.View
import com.hyy.htool.utils.HySpTool.getContent
import com.hyy.htool.utils.HyToast
import com.hyy.htool.utils.SkipActivity
import com.hyy.hytool.R
import com.hyy.hytool.base.BaseFragment
import com.hyy.hytool.databinding.HomeFragmentV3Binding
import com.hyy.hytool.mvp.modile.WxPayBean
import com.hyy.hytool.mvp.view.activity.activity_home_v3.txim.ContactAvtivity
import com.hyy.hytool.mvp.view.activity.activity_home_v3.txim.TxImAnswerActivity
import com.hyy.hytool.pay.AliPayEntry
import com.hyy.hytool.pay.PayEntry
import com.hyy.hytool.pay.WeixinPayEntry
import com.hyy.hytool.util.GenerateTestUserSig
import com.tencent.imsdk.TIMCallBack
import com.tencent.imsdk.TIMFriendshipManager
import com.tencent.imsdk.TIMManager
import com.tencent.imsdk.TIMUserProfile
import com.tencent.qcloud.tim.uikit.TUIKit
import com.tencent.qcloud.tim.uikit.base.IUIKitCallBack
import com.tencent.qcloud.tim.uikit.utils.ToastUtil

/**
 * @Author : HYY
 * 描述:
 * 时间:      2022/05/2022/5/31
 * 软件包名:   com.hyy.hytool.mvp.view.fragment
 */
class HomeFragment_v3 : BaseFragment(), View.OnClickListener, PayEntry.OnPayListener{

    private var mAliPayEntry: AliPayEntry? = null
    private var mWeixinPayEntry: WeixinPayEntry? = null
    private var listImage: MutableList<Int> = ArrayList()
    lateinit var binding: HomeFragmentV3Binding


    override fun getLayoutId(): View {
        binding = HomeFragmentV3Binding.inflate(layoutInflater)
        return binding.root
    }
    override fun initViews() {
        binding.tvAliPay.setOnClickListener(this)
        binding.tvWxPay.setOnClickListener(this)
        binding.tvTxIm.setOnClickListener(this)
        binding.tvImage.setOnClickListener(this)
        binding.titleBase.tvTitle.text = "支付,腾讯IM"
        binding.titleBase.ivBack.visibility = View.GONE
        mAliPayEntry = AliPayEntry.getInstance()
        mWeixinPayEntry = WeixinPayEntry.getInstance()
        listImage.add(R.mipmap.v1)
        listImage.add(R.mipmap.v2)
        listImage.add(R.mipmap.v3)
        listImage.add(R.mipmap.v4)
        listImage.add(R.mipmap.v5)
        listImage.add(R.mipmap.v6)
        listImage.add(R.mipmap.v7)
        listImage.add(R.mipmap.v8)


    }

    override fun success(vararg o: Any?) {
        TODO("Not yet implemented")
    }

    override fun error(vararg o: Any?) {
        TODO("Not yet implemented")
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.tv_ali_pay -> {
                HyToast.error("没有后台返回信息无法掉起支付")
//                aliPay()
            }

            R.id.tv_wx_pay -> {
                HyToast.error("没有后台返回信息无法掉起支付")
//                wxPay()

            }
            R.id.tv_tx_im -> {
                val loginUser = TIMManager.getInstance().loginUser //判断用户是否登录
                //int sdkappid = GenerateTestUserSig.SDKAPPID;//用户的User
                val userSig = GenerateTestUserSig.genTestUserSig("1400384334") //后台生成的
                if (loginUser == null) {
                    TUIKit.login("1400384334", userSig, object : IUIKitCallBack {
                        override fun onError(module: String?, code: Int, desc: String?) {
                            activity!!.runOnUiThread {
                                ToastUtil.toastLongMessage("腾讯IM授权失败请联系客服")
                            }
                        }

                        override fun onSuccess(data: Any?) {
                            // 头像，mIconUrl 就是您上传头像后的 URL，可以参考 Demo 中的随机头像作为示例
                            //设置本人昵称
                            val hashMap = HashMap<String, Any>()
                            val icon = getContent(activity!!, "头像")
                            hashMap[TIMUserProfile.TIM_PROFILE_TYPE_KEY_FACEURL] = icon //设置头像
                            // hashMap.put(TIMUserProfile.TIM_PROFILE_TYPE_KEY_NICK, avatar);//设置昵称
                            // hashMap.put(TIMUserProfile.TIM_PROFILE_TYPE_KEY_SELFSIGNATURE, avatar);//设置签名
                            TIMFriendshipManager.getInstance().modifySelfProfile(hashMap, object : TIMCallBack {
                                override fun onError(code: Int, desc: String) {
                                    HyToast.error("modifySelfProfile failed: $code desc$desc")
                                }

                                override fun onSuccess() {
                                    HyToast.success("首次登陆成功")
                                    //                                            startActivity(TxImAnswerActivity.class, false);
                                    SkipActivity.startFragmentActivity(ContactAvtivity::class.java, false, activity)
                                }
                            })
                        }
                    })
                } else {
                    HyToast.success("登陆成功跳转到会话列表")
                    SkipActivity.startFragmentActivity(TxImAnswerActivity::class.java, false, activity)
                }
            }
            R.id.tv_image -> {
                HyToast.showToast("点击了")
//                ImageShow(activity, 1, listImage as ArrayList<Int>?,pager)
            }
        }
    }



    /**
     * 调用支付宝支付
     */
    private fun aliPay(info: String) {
        //后台返回的支付信息 String info;
        mAliPayEntry?.setModel(info)
        mAliPayEntry?.registerListener(this)
        mAliPayEntry?.setActivity(activity)
        mAliPayEntry?.pay()
    }


    /**
     * 调用微信支付
     */
    /**
     * 调用微信支付
     */
    private fun wxPay(wechatPayInfo: WxPayBean.DataBean) {
//        //后台返回的支付信息 组装成WeixinPayModel对象
        val model = WeixinPayEntry.WeixinPayModel()
        model.appid = wechatPayInfo.appid
        model.partnerid = wechatPayInfo.partnerid
        model.prepayid = wechatPayInfo.prepayid
        model.packageValue = "Sign=WXPay"
        model.noncestr = wechatPayInfo.noncestr
        model.timestamp = wechatPayInfo.timestamp
        model.sign = wechatPayInfo.sign
        mWeixinPayEntry!!.setModel(model)
        mWeixinPayEntry!!.registerListener(this)
        mWeixinPayEntry!!.pay()
    }


    override fun onPayResult(type: Int, errCode: Int, result: String?) {
        if (type == PayEntry.ENTRY_ALI) {
            if (errCode == AliPayEntry.CODE_9000) {
                HyToast.showToast("支付宝支付成功")
            } else if (errCode == AliPayEntry.CODE_6001) {
                HyToast.showToast("取消支付宝支付")
            } else {
                HyToast.showToast("支付宝支付失败")
            }
        } else if (type == PayEntry.ENTRY_WEIXIN) {
            //wxpay支付回调 根据错误码做相应处理
            if (errCode == WeixinPayEntry.RET_SUCC) {
                HyToast.showToast("微信支付成功")
            } else {
                HyToast.showToast("微信支付失败")
            }
        }
    }
}