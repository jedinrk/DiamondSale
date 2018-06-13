package com.logiticks.diamondsale.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Naveen on 12-06-2018.
 */

public class PlaceOrderModelClass {
    @SerializedName("$class")
    @Expose
    private String $class = "com.logiticks.diamond.lifecycle.merchant.PlaceOrder";
    @SerializedName("orderId")
    @Expose
    private String orderId;
    @SerializedName("diamond")
    @Expose
    private String diamond;
    @SerializedName("merchant")
    @Expose
    private String merchant;
    @SerializedName("buyer")
    @Expose
    private String buyer;
    @SerializedName("transactionId")
    @Expose
    private String transactionId;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("amount")
    @Expose
    private Double amount;

    /*public String get$class() {
        return $class;
    }

    public void set$class(String $class) {
        this.$class = $class;
    }*/

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDiamond() {
        return diamond;
    }

    public void setDiamond(DiamondModelClass diamond) {
        this.diamond = "resource:com.logiticks.diamondsale.dda.Diamond#" + diamond.getProductID();
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantModelClass merchant) {
        this.merchant = "resource:com.logiticks.diamond.lifecycle.merchant.Merchant#"+ merchant.getCompanyId();
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(CustomerModelClass buyer) {
        this.buyer = "resource:com.logiticks.diamond.lifecycle.merchant.Customer#"+ buyer.getCustomerId();
    }

    /*public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }*/

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
