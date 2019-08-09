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
        "DecBobot",
        "DecHJD",
        "TxtBrandDetailGramCode",
        "TxtKeterangan",
        "TxtLobName",
        "TxtMasterId",
        "TxtNIK",
        "TxtNamaMasterData",
        "TxtName",
        "TxtProductBrandDetailGramName",
        "TxtProductDetailCode",
        "TxtProductDetailName"
})
public class mProductSPGTempData {

    @JsonProperty("_pboolValid")
    private Integer pboolValid;
    @JsonProperty("_pstrArgument")
    private String pstrArgument;
    @JsonProperty("_pstrMessage")
    private String pstrMessage;
    @JsonProperty("_pstrMethodRequest")
    private String pstrMethodRequest;
    @JsonProperty("DecBobot")
    private String decBobot;
    @JsonProperty("DecHJD")
    private String decHJD;
    @JsonProperty("TxtBrandDetailGramCode")
    private String txtBrandDetailGramCode;
    @JsonProperty("TxtKeterangan")
    private String txtKeterangan;
    @JsonProperty("TxtLobName")
    private String txtLobName;
    @JsonProperty("TxtMasterId")
    private String txtMasterId;
    @JsonProperty("TxtNIK")
    private String txtNIK;
    @JsonProperty("TxtNamaMasterData")
    private String txtNamaMasterData;
    @JsonProperty("TxtName")
    private String txtName;
    @JsonProperty("TxtProductBrandDetailGramName")
    private String txtProductBrandDetailGramName;
    @JsonProperty("TxtProductDetailCode")
    private String txtProductDetailCode;
    @JsonProperty("TxtProductDetailName")
    private String txtProductDetailName;
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

    @JsonProperty("DecBobot")
    public String getDecBobot() {
        return decBobot;
    }

    @JsonProperty("DecBobot")
    public void setDecBobot(String decBobot) {
        this.decBobot = decBobot;
    }

    @JsonProperty("DecHJD")
    public String getDecHJD() {
        return decHJD;
    }

    @JsonProperty("DecHJD")
    public void setDecHJD(String decHJD) {
        this.decHJD = decHJD;
    }

    @JsonProperty("TxtBrandDetailGramCode")
    public String getTxtBrandDetailGramCode() {
        return txtBrandDetailGramCode;
    }

    @JsonProperty("TxtBrandDetailGramCode")
    public void setTxtBrandDetailGramCode(String txtBrandDetailGramCode) {
        this.txtBrandDetailGramCode = txtBrandDetailGramCode;
    }

    @JsonProperty("TxtKeterangan")
    public String getTxtKeterangan() {
        return txtKeterangan;
    }

    @JsonProperty("TxtKeterangan")
    public void setTxtKeterangan(String txtKeterangan) {
        this.txtKeterangan = txtKeterangan;
    }

    @JsonProperty("TxtLobName")
    public String getTxtLobName() {
        return txtLobName;
    }

    @JsonProperty("TxtLobName")
    public void setTxtLobName(String txtLobName) {
        this.txtLobName = txtLobName;
    }

    @JsonProperty("TxtMasterId")
    public String getTxtMasterId() {
        return txtMasterId;
    }

    @JsonProperty("TxtMasterId")
    public void setTxtMasterId(String txtMasterId) {
        this.txtMasterId = txtMasterId;
    }

    @JsonProperty("TxtNIK")
    public String getTxtNIK() {
        return txtNIK;
    }

    @JsonProperty("TxtNIK")
    public void setTxtNIK(String txtNIK) {
        this.txtNIK = txtNIK;
    }

    @JsonProperty("TxtNamaMasterData")
    public String getTxtNamaMasterData() {
        return txtNamaMasterData;
    }

    @JsonProperty("TxtNamaMasterData")
    public void setTxtNamaMasterData(String txtNamaMasterData) {
        this.txtNamaMasterData = txtNamaMasterData;
    }

    @JsonProperty("TxtName")
    public String getTxtName() {
        return txtName;
    }

    @JsonProperty("TxtName")
    public void setTxtName(String txtName) {
        this.txtName = txtName;
    }

    @JsonProperty("TxtProductBrandDetailGramName")
    public String getTxtProductBrandDetailGramName() {
        return txtProductBrandDetailGramName;
    }

    @JsonProperty("TxtProductBrandDetailGramName")
    public void setTxtProductBrandDetailGramName(String txtProductBrandDetailGramName) {
        this.txtProductBrandDetailGramName = txtProductBrandDetailGramName;
    }

    @JsonProperty("TxtProductDetailCode")
    public String getTxtProductDetailCode() {
        return txtProductDetailCode;
    }

    @JsonProperty("TxtProductDetailCode")
    public void setTxtProductDetailCode(String txtProductDetailCode) {
        this.txtProductDetailCode = txtProductDetailCode;
    }

    @JsonProperty("TxtProductDetailName")
    public String getTxtProductDetailName() {
        return txtProductDetailName;
    }

    @JsonProperty("TxtProductDetailName")
    public void setTxtProductDetailName(String txtProductDetailName) {
        this.txtProductDetailName = txtProductDetailName;
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