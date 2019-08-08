package com.mx.pojo;

import java.io.Serializable;
/**
 *
 * 退换货实体类
 *
 */
public class Logistics_Return implements Serializable {
    private Integer lrId;

    private Integer oId;

    private Integer lId;

    private String lrNum;

    private String reason;

    private static final long serialVersionUID = 1L;

    public Integer getLrId() {
        return lrId;
    }

    public void setLrId(Integer lrId) {
        this.lrId = lrId;
    }

    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    public Integer getlId() {
        return lId;
    }

    public void setlId(Integer lId) {
        this.lId = lId;
    }

    public String getLrNum() {
        return lrNum;
    }

    public void setLrNum(String lrNum) {
        this.lrNum = lrNum == null ? null : lrNum.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }
}