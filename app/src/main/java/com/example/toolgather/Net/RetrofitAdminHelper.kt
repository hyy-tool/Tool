package com.example.toolgather.net

import com.example.toolgather.mvp.module.AreaSelectBean
import com.example.toolgather.ToolGatherApp
import rx.Observable


class RetrofitAdminHelper {
    private val mApiService: CommerceAdminService? = ToolGatherApp.getRetrofit().create(CommerceAdminService::class.java)


    companion object {
        private var sMRetrofitAdminHelper: RetrofitAdminHelper? = null

        @JvmStatic
        val instance: RetrofitAdminHelper?
            get() {
                if (sMRetrofitAdminHelper == null) {
                    synchronized(RetrofitAdminHelper::class.java) {
                        if (sMRetrofitAdminHelper == null) {
                            sMRetrofitAdminHelper = RetrofitAdminHelper()
                        }
                    }
                }
                return sMRetrofitAdminHelper
            }
    }


//    fun home(id: String?): Observable<BaseBean?>? {
//        if (mApiService == null) {
//            RetrofitAdminHelper()
//        }
//        return mApiService!!.home(id, getString(getContext(), "token"))
//    }

    fun follow_messages(pid: Int): Observable<AreaSelectBean?>? {
        if (mApiService == null) {
            RetrofitAdminHelper()
        }
        return mApiService!!.follow_messages( pid, "5f0d1738014f25f0d1738014f6121197")
    }
}