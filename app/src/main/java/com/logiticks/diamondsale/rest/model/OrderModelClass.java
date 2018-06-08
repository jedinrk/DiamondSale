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
    @SerializedName("diamondDetails")
    @Expose
    private DiamondDetails diamondDetails;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("orderStatus")
    @Expose
    private String orderStatus;
    /*@SerializedName("merchant")
    @Expose
    private Merchant merchant;
    @SerializedName("orderer")
    @Expose
    private Orderer orderer;*/

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

    public DiamondDetails getDiamondDetails() {
        return diamondDetails;
    }

    public void setDiamondDetails(DiamondDetails diamondDetails) {
        this.diamondDetails = diamondDetails;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    /*public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public Orderer getOrderer() {
        return orderer;
    }

    public void setOrderer(Orderer orderer) {
        this.orderer = orderer;
    }*/

}