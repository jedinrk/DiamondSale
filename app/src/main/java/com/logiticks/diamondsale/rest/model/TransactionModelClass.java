package com.logiticks.diamondsale.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Naveen on 13-06-2018.
 */

public class TransactionModelClass {

    @SerializedName("$class")
    @Expose
    private String $class;
    @SerializedName("transactionId")
    @Expose
    private String transactionId;
    @SerializedName("transactionType")
    @Expose
    private String transactionType;
    @SerializedName("transactionInvoked")
    @Expose
    private String transactionInvoked;
    @SerializedName("participantInvoking")
    @Expose
    private String participantInvoking;
    @SerializedName("identityUsed")
    @Expose
    private String identityUsed;
    @SerializedName("eventsEmitted")
    @Expose
    private List<Object> eventsEmitted = null;
    @SerializedName("transactionTimestamp")
    @Expose
    private String transactionTimestamp;

    public String get$class() {
        return $class;
    }

    public void set$class(String $class) {
        this.$class = $class;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionType() {
        if(transactionType.contains("UpdateBusinessNetwork")){
            return "Update Business Network";
        }else if(transactionType.contains("PlaceOrder")){
            return "Place Order";
        }else if(transactionType.contains("AddAsset")){
            return "Add Asset";
        }else if(transactionType.contains("ActivateCurrentIdentity")){
            return "Activate Current Identity";
        }else if(transactionType.contains("StartBusinessNetwork")){
            return "Start Business Network";
        }else if(transactionType.contains("IssueIdentity")){
            return "Issue Identity";
        }else if(transactionType.contains("RemoveParticipant")){
            return "Remove Participant";
        }else {
            return "Remove Asset";
        }
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionInvoked() {
        return transactionInvoked;
    }

    public void setTransactionInvoked(String transactionInvoked) {
        this.transactionInvoked = transactionInvoked;
    }

    public String getParticipantInvoking() {
        if(participantInvoking != null && participantInvoking.contains("admin"))
            return "Network Admin";
        else
            return "None";
    }

    public void setParticipantInvoking(String participantInvoking) {
        this.participantInvoking = participantInvoking;
    }

    public String getIdentityUsed() {
        return identityUsed;
    }

    public void setIdentityUsed(String identityUsed) {
        this.identityUsed = identityUsed;
    }

    public List<Object> getEventsEmitted() {
        return eventsEmitted;
    }

    public void setEventsEmitted(List<Object> eventsEmitted) {
        this.eventsEmitted = eventsEmitted;
    }

    public String getTransactionTimestamp() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date newDate = null;
        String date = "";
        try {
            newDate = format.parse(transactionTimestamp);
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            date = format.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        
        return date;
    }

    public void setTransactionTimestamp(String transactionTimestamp) {
        this.transactionTimestamp = transactionTimestamp;
    }
}
