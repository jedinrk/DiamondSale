package com.logiticks.diamondsale.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Naveen on 05-06-2018.
 */

public class CustomerModelClass {

    @SerializedName("$class")
    @Expose
    private String $class;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("contactDetails")
    @Expose
    private ContactDetails contactDetails;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }

}
