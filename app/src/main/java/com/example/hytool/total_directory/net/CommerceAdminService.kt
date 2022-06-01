package com.example.toolgather.net

import com.example.hytool.total_directory.mvp.module.AreaSelectBean
import com.example.toolgather.mvp.module.BaseBean
import retrofit2.http.*
import rx.Observable


interface CommerceAdminService {

    @FormUrlEncoded
    @POST("api/jobresumes")
    fun home(@Field("company_id") company_id: String?, @Field("token") token: String?): Observable<BaseBean?>?

    /**
     * 关注消息列表
     */
    @GET("app/areaTwo")
    fun follow_messages(@Query("pid") pid: Int, @Query("token") token: String?): Observable<AreaSelectBean?>?

}