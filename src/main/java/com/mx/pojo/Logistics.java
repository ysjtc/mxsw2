package com.mx.pojo;

import java.io.Serializable;
/**
 *
 * 物流表实体类
 *
 */
public class Logistics implements Serializable {
    private Integer lId;

    private Integer oId;

    private String waybillNum;

    private String company;

    private Boolean status;

    private static final long serialVersionUID = 1L;

    public Integer getlId() {
        return lId;
    }

    public void setlId(Integer lId) {
        this.lId = lId;
    }

    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    public String getWaybillNum() {
        return waybillNum;
    }

    public void setWaybillNum(String waybillNum) {
        this.waybillNum = waybillNum == null ? null : waybillNum.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}