package com.kalbenutritionals.app.kalbespgmobile.Network;

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
        "TxtGrupMasterID",
        "TxtKeterangan",
        "TxtKeteranganJenis",
        "TxtMasterID",
        "TxtNamaMasterData"
})

public class mTypeSubmissionTempData {

    @JsonProperty("_pboolValid")
    private Integer pboolValid;
    @JsonProperty("_pstrArgument")
    private String pstrArgument;
    @JsonProperty("_pstrMessage")
    private String pstrMessage;
    @JsonProperty("_pstrMethodRequest")
    private String pstrMethodRequest;
    @JsonProperty("TxtGrupMasterID")
    private String txtGrupMasterID;
    @JsonProperty("TxtKeterangan")
    private String txtKeterangan;
    @JsonProperty("TxtKeteranganJenis")
    private String txtKeteranganJenis;
    @JsonProperty("TxtMasterID")
    private String txtMasterID;
    @JsonProperty("TxtNamaMasterData")
    private String txtNamaMasterData;
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

    @JsonProperty("TxtGrupMasterID")
    public String getTxtGrupMasterID() {
        return txtGrupMasterID;
    }

    @JsonProperty("TxtGrupMasterID")
    public void setTxtGrupMasterID(String txtGrupMasterID) {
        this.txtGrupMasterID = txtGrupMasterID;
    }

    @JsonProperty("TxtKeterangan")
    public String getTxtKeterangan() {
        return txtKeterangan;
    }

    @JsonProperty("TxtKeterangan")
    public void setTxtKeterangan(String txtKeterangan) {
        this.txtKeterangan = txtKeterangan;
    }

    @JsonProperty("TxtKeteranganJenis")
    public String getTxtKeteranganJenis() {
        return txtKeteranganJenis;
    }

    @JsonProperty("TxtKeteranganJenis")
    public void setTxtKeteranganJenis(String txtKeteranganJenis) {
        this.txtKeteranganJenis = txtKeteranganJenis;
    }

    @JsonProperty("TxtMasterID")
    public String getTxtMasterID() {
        return txtMasterID;
    }

    @JsonProperty("TxtMasterID")
    public void setTxtMasterID(String txtMasterID) {
        this.txtMasterID = txtMasterID;
    }

    @JsonProperty("TxtNamaMasterData")
    public String getTxtNamaMasterData() {
        return txtNamaMasterData;
    }

    @JsonProperty("TxtNamaMasterData")
    public void setTxtNamaMasterData(String txtNamaMasterData) {
        this.txtNamaMasterData = txtNamaMasterData;
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