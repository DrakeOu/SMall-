package com.xjtuse.mall.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ExpressInfo {
    @JsonProperty("LogisticCode")
    private String LogisticCode;
    @JsonProperty("ShipperCode")
    private String ShipperCode;
    @JsonProperty("Traces")
    private List<Traces> Traces;
    @JsonProperty("State")
    private String State;
    @JsonProperty("EBusinessID")
    private String EBusinessID;
    @JsonProperty("Success")
    private boolean Success;
    @JsonProperty("Reason")
    private String Reason;
    private String ShipperName;

    public ExpressInfo() {
    }

    public String getLogisticCode() {
        return this.LogisticCode;
    }

    public void setLogisticCode(String LogisticCode) {
        this.LogisticCode = LogisticCode;
    }

    public String getShipperCode() {
        return this.ShipperCode;
    }

    public void setShipperCode(String ShipperCode) {
        this.ShipperCode = ShipperCode;
    }

    public List<Traces> getTraces() {
        return this.Traces;
    }

    public void setTraces(List<Traces> Traces) {
        this.Traces = Traces;
    }

    public String getState() {
        return this.State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public String getEBusinessID() {
        return this.EBusinessID;
    }

    public void setEBusinessID(String EBusinessID) {
        this.EBusinessID = EBusinessID;
    }

    public boolean getSuccess() {
        return this.Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public String getReason() {
        return this.Reason;
    }

    public void setReason(String Reason) {
        this.Reason = Reason;
    }

    public String getShipperName() {
        return this.ShipperName;
    }

    public void setShipperName(String shipperName) {
        this.ShipperName = shipperName;
    }

    public String toString() {
        return "ExpressInfo{LogisticCode='" + this.LogisticCode + '\'' + ", ShipperCode='" + this.ShipperCode + '\'' + ", Traces=" + this.Traces + ", State='" + this.State + '\'' + ", EBusinessID='" + this.EBusinessID + '\'' + ", Success=" + this.Success + ", Reason=" + this.Reason + ", ShipperName='" + this.ShipperName + '\'' + '}';
    }
}
