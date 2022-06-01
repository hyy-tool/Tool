package com.hyy.htool.view

/**
 * @Author : HYY
 * 描述:      整合修改
 * 时间:      2022/06/2022/6/1
 * 软件包名:   com.hyy.htool.view
 */
interface HyScrollDelegate {
    fun scrollViewTo(x: Int, y: Int)
    var viewScrollY: Int
    var viewScrollX: Int
}