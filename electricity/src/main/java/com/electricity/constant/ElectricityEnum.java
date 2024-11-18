package com.electricity.constant;

/**
 * @author jiaowei
 * @since 2024/10/1 21:10
 */
public enum ElectricityEnum {
    MAX_DAY("maxDay", "maxDay"),

    MAX_TIME("maxTime", "maxTime"),

    DEFAULT_ELECTRICITY("electricity", "electricity"),

    DEFAULT_PRICE("price", "price");


    /**
     * 编码
     */
    private String code;

    /**
     * 描述
     */
    private String desc;

    /**
     * ClueClosedEnum
     */
    ElectricityEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * getCode
     */
    public String getCode() {
        return this.code;
    }

    /**
     * getDesc
     */
    public String getDesc() {
        return this.desc;
    }
}
