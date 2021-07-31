package top.dreamyy.hrm.domain;

import java.util.Date;

public class TestResult implements java.io.Serializable {
    private int id;
    private String virtualPartnumber;
    private String result;
    private Integer failCode;
    private Double ntcResistant;
    private String ccdPictureNames;
    private String shuntCode;
    private String fluxPictureNames;
    private String weldPictureNames;
    private String aoiResult;
    private String aoiPictureNames;
    private double rntc;
    private String printCode;
    private java.util.Date proDate;
    private Double r25;
    private Double r16;
    private double tntc;
    private java.util.Date startTime;
    private java.util.Date endTime;
    private Integer batchInfo;

    // 无参数构造器
    public TestResult() {
        super();
        // TODO Auto-generated constructor stub
    }

    public int getId() {
        return id;
    }

    public String getVirtualPartnumber() {
        return virtualPartnumber;
    }

    public String getResult() {
        return result;
    }

    public Integer getFailCode() {
        return failCode;
    }

    public Double getNtcResistant() {
        return ntcResistant;
    }

    public String getCcdPictureNames() {
        return ccdPictureNames;
    }

    public String getShuntCode() {
        return shuntCode;
    }

    public String getFluxPictureNames() {
        return fluxPictureNames;
    }

    public String getWeldPictureNames() {
        return weldPictureNames;
    }

    public String getAoiResult() {
        return aoiResult;
    }

    public String getAoiPictureNames() {
        return aoiPictureNames;
    }

    public double getRntc() {
        return rntc;
    }

    public String getPrintCode() {
        return printCode;
    }

    public Date getProDate() {
        return proDate;
    }

    public Double getR25() {
        return r25;
    }

    public Double getR16() {
        return r16;
    }

    public double getTntc() {
        return tntc;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Integer getBatch() {
        return batchInfo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVirtualPartnumber(String virtualPartnumber) {
        this.virtualPartnumber = virtualPartnumber;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setFailCode(Integer failCode) {
        this.failCode = failCode;
    }

    public void setNtcResistant(Double ntcResistant) {
        this.ntcResistant = ntcResistant;
    }

    public void setCcdPictureNames(String ccdPictureNames) {
        this.ccdPictureNames = ccdPictureNames;
    }

    public void setShuntCode(String shuntCode) {
        this.shuntCode = shuntCode;
    }

    public void setFluxPictureNames(String fluxPictureNames) {
        this.fluxPictureNames = fluxPictureNames;
    }

    public void setWeldPictureNames(String weldPictureNames) {
        this.weldPictureNames = weldPictureNames;
    }

    public void setAoiResult(String aoiResult) {
        this.aoiResult = aoiResult;
    }

    public void setAoiPictureNames(String aoiPictureNames) {
        this.aoiPictureNames = aoiPictureNames;
    }

    public void setRntc(double rntc) {
        this.rntc = rntc;
    }

    public void setPrintCode(String printCode) {
        this.printCode = printCode;
    }

    public void setProDate(Date proDate) {
        this.proDate = proDate;
    }

    public void setR25(Double r25) {
        this.r25 = r25;
    }

    public void setR16(Double r16) {
        this.r16 = r16;
    }

    public void setTntc(double tntc) {
        this.tntc = tntc;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setWorkorder(Integer batchInfo) {
        this.batchInfo = batchInfo;
    }

    @Override
    public String toString() {
        return "TestResult{" +
                "id=" + id +
                ", virtualPartnumber='" + virtualPartnumber + '\'' +
                ", result='" + result + '\'' +
                ", failCode=" + failCode +
                ", ntcResistant=" + ntcResistant +
                ", ccdPictureNames='" + ccdPictureNames + '\'' +
                ", shuntCode='" + shuntCode + '\'' +
                ", fluxPictureNames='" + fluxPictureNames + '\'' +
                ", weldPictureNames='" + weldPictureNames + '\'' +
                ", aoiResult='" + aoiResult + '\'' +
                ", aoiPictureNames='" + aoiPictureNames + '\'' +
                ", rntc=" + rntc +
                ", printCode='" + printCode + '\'' +
                ", proDate=" + proDate +
                ", r25=" + r25 +
                ", r16=" + r16 +
                ", tntc=" + tntc +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", batchInfo='" + batchInfo + '\'' +
                '}';
    }
}
