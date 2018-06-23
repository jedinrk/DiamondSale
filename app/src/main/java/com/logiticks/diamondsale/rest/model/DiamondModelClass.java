package com.logiticks.diamondsale.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.security.acl.Owner;
import java.util.List;

/**
 * Created by Naveen on 05-06-2018.
 */

public class DiamondModelClass {

    @SerializedName("$class")
    @Expose
    private String $class;
    @SerializedName("productID")
    @Expose
    private String productID;
    @SerializedName("diamondDetails")
    @Expose
    private DiamondDetails diamondDetails;
    @SerializedName("merchant")
    @Expose
    private String merchant;
    @SerializedName("owner")
    @Expose
    private String owner;
    @SerializedName("logEntries")
    @Expose
    private List<LogEntry> logEntries = null;

    public String get$class() {
        return $class;
    }

    public void set$class(String $class) {
        this.$class = $class;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public DiamondDetails getDiamondDetails() {
        return diamondDetails;
    }

    public void setDiamondDetails(DiamondDetails diamondDetails) {
        this.diamondDetails = diamondDetails;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = "resource:com.logiticks.diamond.lifecycle.merchant.Merchant#"+merchant;
    }

    public List<LogEntry> getLogEntries() {
        return logEntries;
    }

    public void setLogEntries(List<LogEntry> logEntries) {
        this.logEntries = logEntries;
    }



}
