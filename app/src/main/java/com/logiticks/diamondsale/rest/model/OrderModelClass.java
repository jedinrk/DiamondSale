package com.logiticks.diamondsale.rest.model;

/**
 * Created by Naveen on 05-06-2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderModelClass {

    @SerializedName("$class")
    @Expose
    private String $class;
    @SerializedName("orderId")
    @Expose
    private String orderId;
    @SerializedName("amount")
    @Expose
    private Double amount;
    @SerializedName("orderStatus")
    @Expose
    private String orderStatus;
    @SerializedName("diamond")
    @Expose
    private DiamondModelClass diamond;
    @SerializedName("merchant")
    @Expose
    private MerchantModelClass merchant;
    @SerializedName("buyer")
    @Expose
    private CustomerModelClass buyer;

    public String get$class() {
        return $class;
    }

    public void set$class(String $class) {
        this.$class = $class;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public DiamondModelClass getDiamond() {
        return diamond;
    }

    public void setDiamond(DiamondModelClass diamond) {
        this.diamond = diamond;
    }

    public MerchantModelClass getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantModelClass merchant) {
        this.merchant = merchant;
    }

    public CustomerModelClass getBuyer() {
        return buyer;
    }

    public void setBuyer(CustomerModelClass buyer) {
        this.buyer = buyer;
    }

}