package com.hyy.hytool.net

import com.hyy.hytool.mvp.modile.AreaSelectBean
import com.hyy.hytool.mvp.modile.BaseBean
import retrofit2.http.*
import rx.Observable

/**
 * @Author : HYY
 * 描述:
 * 时间:      2022/05/2022/5/31
 * 软件包名:   com.hyy.hytool.net
 */
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