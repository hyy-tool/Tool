package com.hyy.hytool.base

/**
 * @Author : HYY
 * 描述:
 * 时间:      2022/05/2022/5/31
 * 软件包名:   com.hyy.hytool.base
 */
interface  BaseView {
    fun success(vararg o: Any?)
    fun error(vararg o: Any?)
}