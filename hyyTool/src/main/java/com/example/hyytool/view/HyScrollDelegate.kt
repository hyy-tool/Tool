package com.stefan.hyyTool.view

/**
 * @Author : HYY
 * 描述:      整合修改
 * 时间:      2022/05/2022/5/9
 * 软件包名:   com.stefan.hyyTool.view
 */
interface HyScrollDelegate {
    fun scrollViewTo(x: Int, y: Int)
    var viewScrollY: Int
    var viewScrollX: Int
}