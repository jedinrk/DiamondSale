package com.logiticks.diamondsale.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Naveen on 12-06-2018.
 */

public class MerchantModelClass {

    @SerializedName("$class")
    @Expose
    private String $class = "com.logiticks.diamond.lifecycle.merchant.Merchant";
    @SerializedName("companyId")
    @Expose
    private String companyId;
    @SerializedName("companyName")
    @Expose
    private String companyName;

    /*public String get$class() {
        return $class;
    }

    public void set$class(String $class) {
        this.$class = $class;
    }*/

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
