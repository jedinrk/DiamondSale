package com.logiticks.diamondsale.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.security.acl.Owner;

/**
 * Created by Naveen on 07-06-2018.
 */

public class LogEntry {
    @SerializedName("$class")
    @Expose
    private String $class;
    @SerializedName("diamond")
    @Expose
    private DiamondModelClass diamond;
    @SerializedName("buyer")
    @Expose
    private CustomerModelClass buyer;
    @SerializedName("seller")
    @Expose
    private CustomerModelClass seller;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("id")
    @Expose
    private String id;

    public String get$class() {
        return $class;
    }

    public void set$class(String $class) {
        this.$class = $class;
    }

    public DiamondModelClass getDiamond() {
        return diamond;
    }

    public void setDiamond(DiamondModelClass diamond) {
        this.diamond = diamond;
    }

    public CustomerModelClass getBuyer() {
        return buyer;
    }

    public void setBuyer(CustomerModelClass buyer) {
        this.buyer = buyer;
    }

    public CustomerModelClass getSeller() {
        return seller;
    }

    public void setSeller(CustomerModelClass seller) {
        this.seller = seller;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
