package com.logiticks.diamondsale.rest.model;

/**
 * Created by Naveen on 05-06-2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContactDetails {

    @SerializedName("$class")
    @Expose
    private String $class;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("mobilePhone")
    @Expose
    private String mobilePhone;
    @SerializedName("homePhone")
    @Expose
    private String homePhone;
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("id")
    @Expose
    private String id;

    public String get$class() {
        return $class;
    }

    public void set$class(String $class) {
        this.$class = $class;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
