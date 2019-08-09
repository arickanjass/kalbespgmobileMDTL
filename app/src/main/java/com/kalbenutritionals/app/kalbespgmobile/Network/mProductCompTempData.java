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
        "TxtBranchCRMCode",
        "TxtGroupProduct",
        "TxtLobName",
        "TxtNIK",
        "TxtName",
        "TxtProductDetailCode",
        "TxtProdukKompetitorID",
        "TxtProdukid"
})

public class mProductCompTempData {

    @JsonProperty("_pboolValid")
    private Integer pboolValid;
    @JsonProperty("_pstrArgument")
    private String pstrArgument;
    @JsonProperty("_pstrMessage")
    private String pstrMessage;
    @JsonProperty("_pstrMethodRequest")
    private String pstrMethodRequest;
    @JsonProperty("TxtBranchCRMCode")
    private String txtBranchCRMCode;
    @JsonProperty("TxtGroupProduct")
    private String txtGroupProduct;
    @JsonProperty("TxtLobName")
    private String txtLobName;
    @JsonProperty("TxtNIK")
    private String txtNIK;
    @JsonProperty("TxtName")
    private String txtName;
    @JsonProperty("TxtProductDetailCode")
    private String txtProductDetailCode;
    @JsonProperty("TxtProdukKompetitorID")
    private String txtProdukKompetitorID;
    @JsonProperty("TxtProdukid")
    private String txtProdukid;
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

    @JsonProperty("TxtBranchCRMCode")
    public String getTxtBranchCRMCode() {
        return txtBranchCRMCode;
    }

    @JsonProperty("TxtBranchCRMCode")
    public void setTxtBranchCRMCode(String txtBranchCRMCode) {
        this.txtBranchCRMCode = txtBranchCRMCode;
    }

    @JsonProperty("TxtGroupProduct")
    public String getTxtGroupProduct() {
        return txtGroupProduct;
    }

    @JsonProperty("TxtGroupProduct")
    public void setTxtGroupProduct(String txtGroupProduct) {
        this.txtGroupProduct = txtGroupProduct;
    }

    @JsonProperty("TxtLobName")
    public String getTxtLobName() {
        return txtLobName;
    }

    @JsonProperty("TxtLobName")
    public void setTxtLobName(String txtLobName) {
        this.txtLobName = txtLobName;
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

    @JsonProperty("TxtProductDetailCode")
    public String getTxtProductDetailCode() {
        return txtProductDetailCode;
    }

    @JsonProperty("TxtProductDetailCode")
    public void setTxtProductDetailCode(String txtProductDetailCode) {
        this.txtProductDetailCode = txtProductDetailCode;
    }

    @JsonProperty("TxtProdukKompetitorID")
    public String getTxtProdukKompetitorID() {
        return txtProdukKompetitorID;
    }

    @JsonProperty("TxtProdukKompetitorID")
    public void setTxtProdukKompetitorID(String txtProdukKompetitorID) {
        this.txtProdukKompetitorID = txtProdukKompetitorID;
    }

    @JsonProperty("TxtProdukid")
    public String getTxtProdukid() {
        return txtProdukid;
    }

    @JsonProperty("TxtProdukid")
    public void setTxtProdukid(String txtProdukid) {
        this.txtProdukid = txtProdukid;
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
