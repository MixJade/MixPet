package com.ship.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 生成新的订单号
 */
public enum CodeEnum {
    FOSTER(1);
    private final int typeNum;

    CodeEnum(int typeNum) {
        this.typeNum = typeNum;
    }

    public String newCode(Integer maxID) {
        String nowDate = new SimpleDateFormat("yyMMdd").format(new Date());
        return typeNum + nowDate + (maxID + 1);
    }
}
