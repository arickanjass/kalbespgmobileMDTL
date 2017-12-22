package com.kalbenutritionals.app.kalbespgmobile;

/**
 * Created by arick.anjasmara on 11/12/2017.
 */

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "_pboolValid",
        "_pstrArgument",
        "_pstrMessage",
        "_pstrMethodRequest",
        "IntBranchId",
        "IntChannelId",
        "IntEmployeeId",
        "IntID",
        "IntOutletId",
        "IntRayonId",
        "IntRegionId",
        "IntRowStatus",
        "TxtBranchCode",
        "TxtBranchName",
        "TxtChannelName",
        "TxtNIK",
        "TxtName",
        "TxtOutletCode",
        "TxtOutletName",
        "TxtRayonCode",
        "TxtRayonName",
        "TxtRegionName",
        "TxtSubmissionID",
        "txtAccuracy",
        "txtDataIdGL",
        "txtLatitude",
        "txtLongitude"
})
public class mEmployeeAreaTempData {

    @JsonProperty("_pboolValid")
    private Integer pboolValid;
    @JsonProperty("_pstrArgument")
    private String pstrArgument;
    @JsonProperty("_pstrMessage")
    private String pstrMessage;
    @JsonProperty("_pstrMethodRequest")
    private String pstrMethodRequest;
    @JsonProperty("IntBranchId")
    private String intBranchId;
    @JsonProperty("IntChannelId")
    private String intChannelId;
    @JsonProperty("IntEmployeeId")
    private String intEmployeeId;
    @JsonProperty("IntID")
    private String intID;
    @JsonProperty("IntOutletId")
    private String intOutletId;
    @JsonProperty("IntRayonId")
    private String intRayonId;
    @JsonProperty("IntRegionId")
    private String intRegionId;
    @JsonProperty("IntRowStatus")
    private String intRowStatus;
    @JsonProperty("TxtBranchCode")
    private String txtBranchCode;
    @JsonProperty("TxtBranchName")
    private String txtBranchName;
    @JsonProperty("TxtChannelName")
    private String txtChannelName;
    @JsonProperty("TxtNIK")
    private String txtNIK;
    @JsonProperty("TxtName")
    private String txtName;
    @JsonProperty("TxtOutletCode")
    private String txtOutletCode;
    @JsonProperty("TxtOutletName")
    private String txtOutletName;
    @JsonProperty("TxtRayonCode")
    private String txtRayonCode;
    @JsonProperty("TxtRayonName")
    private String txtRayonName;
    @JsonProperty("TxtRegionName")
    private String txtRegionName;
    @JsonProperty("TxtSubmissionID")
    private String txtSubmissionID;
    @JsonProperty("txtAccuracy")
    private String txtAccuracy;
    @JsonProperty("txtDataIdGL")
    private String txtDataIdGL;
    @JsonProperty("txtLatitude")
    private String txtLatitude;
    @JsonProperty("txtLongitude")
    private String txtLongitude;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("_pboolValid")
    public Integer getPboolValid() {
        return pboolValid;
    }

    @JsonProperty("_pboolValid")
    public void setPboolValid(Integer pboolValid) {
        this.pboolValid = pboolValid;
    }

    @JsonProperty("_pstrArgument")
    public String getPstrArgument() {
        return pstrArgument;
    }

    @JsonProperty("_pstrArgument")
    public void setPstrArgument(String pstrArgument) {
        this.pstrArgument = pstrArgument;
    }

    @JsonProperty("_pstrMessage")
    public String getPstrMessage() {
        return pstrMessage;
    }

    @JsonProperty("_pstrMessage")
    public void setPstrMessage(String pstrMessage) {
        this.pstrMessage = pstrMessage;
    }

    @JsonProperty("_pstrMethodRequest")
    public String getPstrMethodRequest() {
        return pstrMethodRequest;
    }

    @JsonProperty("_pstrMethodRequest")
    public void setPstrMethodRequest(String pstrMethodRequest) {
        this.pstrMethodRequest = pstrMethodRequest;
    }

    @JsonProperty("IntBranchId")
    public String getIntBranchId() {
        return intBranchId;
    }

    @JsonProperty("IntBranchId")
    public void setIntBranchId(String intBranchId) {
        this.intBranchId = intBranchId;
    }

    @JsonProperty("IntChannelId")
    public String getIntChannelId() {
        return intChannelId;
    }

    @JsonProperty("IntChannelId")
    public void setIntChannelId(String intChannelId) {
        this.intChannelId = intChannelId;
    }

    @JsonProperty("IntEmployeeId")
    public String getIntEmployeeId() {
        return intEmployeeId;
    }

    @JsonProperty("IntEmployeeId")
    public void setIntEmployeeId(String intEmployeeId) {
        this.intEmployeeId = intEmployeeId;
    }

    @JsonProperty("IntID")
    public String getIntID() {
        return intID;
    }

    @JsonProperty("IntID")
    public void setIntID(String intID) {
        this.intID = intID;
    }

    @JsonProperty("IntOutletId")
    public String getIntOutletId() {
        return intOutletId;
    }

    @JsonProperty("IntOutletId")
    public void setIntOutletId(String intOutletId) {
        this.intOutletId = intOutletId;
    }

    @JsonProperty("IntRayonId")
    public String getIntRayonId() {
        return intRayonId;
    }

    @JsonProperty("IntRayonId")
    public void setIntRayonId(String intRayonId) {
        this.intRayonId = intRayonId;
    }

    @JsonProperty("IntRegionId")
    public String getIntRegionId() {
        return intRegionId;
    }

    @JsonProperty("IntRegionId")
    public void setIntRegionId(String intRegionId) {
        this.intRegionId = intRegionId;
    }

    @JsonProperty("IntRowStatus")
    public String getIntRowStatus() {
        return intRowStatus;
    }

    @JsonProperty("IntRowStatus")
    public void setIntRowStatus(String intRowStatus) {
        this.intRowStatus = intRowStatus;
    }

    @JsonProperty("TxtBranchCode")
    public String getTxtBranchCode() {
        return txtBranchCode;
    }

    @JsonProperty("TxtBranchCode")
    public void setTxtBranchCode(String txtBranchCode) {
        this.txtBranchCode = txtBranchCode;
    }

    @JsonProperty("TxtBranchName")
    public String getTxtBranchName() {
        return txtBranchName;
    }

    @JsonProperty("TxtBranchName")
    public void setTxtBranchName(String txtBranchName) {
        this.txtBranchName = txtBranchName;
    }

    @JsonProperty("TxtChannelName")
    public String getTxtChannelName() {
        return txtChannelName;
    }

    @JsonProperty("TxtChannelName")
    public void setTxtChannelName(String txtChannelName) {
        this.txtChannelName = txtChannelName;
    }

    @JsonProperty("TxtNIK")
    public String getTxtNIK() {
        return txtNIK;
    }

    @JsonProperty("TxtNIK")
    public void setTxtNIK(String txtNIK) {
        this.txtNIK = txtNIK;
    }

    @JsonProperty("TxtName")
    public String getTxtName() {
        return txtName;
    }

    @JsonProperty("TxtName")
    public void setTxtName(String txtName) {
        this.txtName = txtName;
    }

    @JsonProperty("TxtOutletCode")
    public String getTxtOutletCode() {
        return txtOutletCode;
    }

    @JsonProperty("TxtOutletCode")
    public void setTxtOutletCode(String txtOutletCode) {
        this.txtOutletCode = txtOutletCode;
    }

    @JsonProperty("TxtOutletName")
    public String getTxtOutletName() {
        return txtOutletName;
    }

    @JsonProperty("TxtOutletName")
    public void setTxtOutletName(String txtOutletName) {
        this.txtOutletName = txtOutletName;
    }

    @JsonProperty("TxtRayonCode")
    public String getTxtRayonCode() {
        return txtRayonCode;
    }

    @JsonProperty("TxtRayonCode")
    public void setTxtRayonCode(String txtRayonCode) {
        this.txtRayonCode = txtRayonCode;
    }

    @JsonProperty("TxtRayonName")
    public String getTxtRayonName() {
        return txtRayonName;
    }

    @JsonProperty("TxtRayonName")
    public void setTxtRayonName(String txtRayonName) {
        this.txtRayonName = txtRayonName;
    }

    @JsonProperty("TxtRegionName")
    public String getTxtRegionName() {
        return txtRegionName;
    }

    @JsonProperty("TxtRegionName")
    public void setTxtRegionName(String txtRegionName) {
        this.txtRegionName = txtRegionName;
    }

    @JsonProperty("TxtSubmissionID")
    public String getTxtSubmissionID() {
        return txtSubmissionID;
    }

    @JsonProperty("TxtSubmissionID")
    public void setTxtSubmissionID(String txtSubmissionID) {
        this.txtSubmissionID = txtSubmissionID;
    }

    @JsonProperty("txtAccuracy")
    public String getTxtAccuracy() {
        return txtAccuracy;
    }

    @JsonProperty("txtAccuracy")
    public void setTxtAccuracy(String txtAccuracy) {
        this.txtAccuracy = txtAccuracy;
    }

    @JsonProperty("txtDataIdGL")
    public String getTxtDataIdGL() {
        return txtDataIdGL;
    }

    @JsonProperty("txtDataIdGL")
    public void setTxtDataIdGL(String txtDataIdGL) {
        this.txtDataIdGL = txtDataIdGL;
    }

    @JsonProperty("txtLatitude")
    public String getTxtLatitude() {
        return txtLatitude;
    }

    @JsonProperty("txtLatitude")
    public void setTxtLatitude(String txtLatitude) {
        this.txtLatitude = txtLatitude;
    }

    @JsonProperty("txtLongitude")
    public String getTxtLongitude() {
        return txtLongitude;
    }

    @JsonProperty("txtLongitude")
    public void setTxtLongitude(String txtLongitude) {
        this.txtLongitude = txtLongitude;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}