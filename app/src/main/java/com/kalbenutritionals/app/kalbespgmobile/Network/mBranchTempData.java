package com.kalbenutritionals.app.kalbespgmobile.Network;

/**
 * Created by arick.anjasmara on 11/12/2017.
 */

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "_pboolValid",
        "_pstrArgument",
        "_pstrMessage",
        "_pstrMethodRequest",
        "EmpId",
        "IntBranchId",
        "IntEmployeeId",
        "IntID",
        "TxtBranchCode",
        "TxtBranchName",
        "TxtNIK",
        "TxtName"
})
public class mBranchTempData {

    @JsonProperty("_pboolValid")
    private Integer pboolValid;
    @JsonProperty("_pstrArgument")
    private String pstrArgument;
    @JsonProperty("_pstrMessage")
    private String pstrMessage;
    @JsonProperty("_pstrMethodRequest")
    private String pstrMethodRequest;
    @JsonProperty("EmpId")
    private String empId;
    @JsonProperty("IntBranchId")
    private String intBranchId;
    @JsonProperty("IntEmployeeId")
    private String intEmployeeId;
    @JsonProperty("IntID")
    private String intID;
    @JsonProperty("TxtBranchCode")
    private String txtBranchCode;
    @JsonProperty("TxtBranchName")
    private String txtBranchName;
    @JsonProperty("TxtNIK")
    private String txtNIK;
    @JsonProperty("TxtName")
    private String txtName;
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

    @JsonProperty("EmpId")
    public String getEmpId() {
        return empId;
    }

    @JsonProperty("EmpId")
    public void setEmpId(String empId) {
        this.empId = empId;
    }

    @JsonProperty("IntBranchId")
    public String getIntBranchId() {
        return intBranchId;
    }

    @JsonProperty("IntBranchId")
    public void setIntBranchId(String intBranchId) {
        this.intBranchId = intBranchId;
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

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}