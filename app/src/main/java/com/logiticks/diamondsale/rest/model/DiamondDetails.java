package com.logiticks.diamondsale.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by Naveen on 05-06-2018.
 */

public class DiamondDetails {

    @SerializedName("$class")
    @Expose
    private String $class;
    @SerializedName("carat")
    @Expose
    private Double carat;
    @SerializedName("clarity")
    @Expose
    private String clarity;
    @SerializedName("Color")
    @Expose
    private String color;
    @SerializedName("cut")
    @Expose
    private String cut;
    @SerializedName("id")
    @Expose
    private String id;

    public String get$class() {
        return $class;
    }

    public void set$class(String $class) {
        this.$class = $class;
    }

    public Double getCarat() {
        return carat;
    }

    public void setCarat(Double carat) {
        this.carat = carat;
    }

    public String getClarity() {
        return clarity;
    }

    public void setClarity(String clarity) {
        this.clarity = clarity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCut() {
        return cut;
    }

    public void setCut(String cut) {
        this.cut = cut;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
