package com.bigdata.ct.common.constant;

import com.bigdata.ct.common.bean.Val;

/**
 * 枚举也可以实现接口
 * 名称常量枚举类
 * 与名称相关的都放在这里
 */

public enum Names implements Val {

    NAMESPACE("ct")
    ,TOPIC("ct");    //NAMESPACE-命名空间

    private String name;

    private Names (String name){
        this.name = name;
    }

    public void setvalue(Object val) {
        this.name = (String)val;
    }

    public String getvalue() {
        return name;
    }
}
