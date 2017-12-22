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
        "IntmProductUmbrandId",
        "TxtAliasName",
        "TxtProductBrandCode",
        "TxtProductBrandName"
})
public class mProductBrandHeaderTempData {

    @JsonProperty("_pboolValid")
    private Integer pboolValid;
    @JsonProperty("_pstrArgument")
    private String pstrArgument;
    @JsonProperty("_pstrMessage")
    private String pstrMessage;
    @JsonProperty("_pstrMethodRequest")
    private String pstrMethodRequest;
    @JsonProperty("IntmProductUmbrandId")
    private String intmProductUmbrandId;
    @JsonProperty("TxtAliasName")
    private String txtAliasName;
    @JsonProperty("TxtProductBrandCode")
    private String txtProductBrandCode;
    @JsonProperty("TxtProductBrandName")
    private String txtProductBrandName;
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

    @JsonProperty("IntmProductUmbrandId")
    public String getIntmProductUmbrandId() {
        return intmProductUmbrandId;
    }

    @JsonProperty("IntmProductUmbrandId")
    public void setIntmProductUmbrandId(String intmProductUmbrandId) {
        this.intmProductUmbrandId = intmProductUmbrandId;
    }

    @JsonProperty("TxtAliasName")
    public String getTxtAliasName() {
        return txtAliasName;
    }

    @JsonProperty("TxtAliasName")
    public void setTxtAliasName(String txtAliasName) {
        this.txtAliasName = txtAliasName;
    }

    @JsonProperty("TxtProductBrandCode")
    public String getTxtProductBrandCode() {
        return txtProductBrandCode;
    }

    @JsonProperty("TxtProductBrandCode")
    public void setTxtProductBrandCode(String txtProductBrandCode) {
        this.txtProductBrandCode = txtProductBrandCode;
    }

    @JsonProperty("TxtProductBrandName")
    public String getTxtProductBrandName() {
        return txtProductBrandName;
    }

    @JsonProperty("TxtProductBrandName")
    public void setTxtProductBrandName(String txtProductBrandName) {
        this.txtProductBrandName = txtProductBrandName;
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