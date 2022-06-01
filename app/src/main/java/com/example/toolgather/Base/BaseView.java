package com.example.toolgather.Base;

/**
 * 用于Dagger2
 */

public interface BaseView {
    void success(Object... o);
    void error(Object... o);
}
