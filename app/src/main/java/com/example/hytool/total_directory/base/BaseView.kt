package com.example.hytool.total_directory.base

/**
 * 用于Dagger2
 */
interface BaseView {
    fun success(vararg o: Any?)
    fun error(vararg o: Any?)
}