package com.netbull.shop.common.vo;

import java.io.Serializable;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

public class Latn implements Serializable {

    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     * 
     * @since 1.0.0
     */

    private static final long serialVersionUID = -9096780388186723796L;

    private String latnId;//本地网编码

    private String latnName;//地市名称

    private String distNum;//区号

    private int sn;//排序号

    public String getLatnId() {
        return latnId;
    }

    public void setLatnId(String latnId) {
        this.latnId = latnId;
    }

    public String getLatnName() {
        return latnName;
    }

    public void setLatnName(String latnName) {
        this.latnName = latnName;
    }

    public String getDistNum() {
        return distNum;
    }

    public void setDistNum(String distNum) {
        this.distNum = distNum;
    }

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    @Override
    public String toString() {
        JSON s = JSONSerializer.toJSON(this);
        return s.toString();
    }

}
