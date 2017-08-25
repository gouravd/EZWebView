package com.hkm.ezwebview.models;

import java.io.Serializable;

/**
 * Created by hesk on 25/8/2017.
 */

public class CardInfo implements Serializable {
    protected String bill_to_city;
    protected String bill_to_country_code;
    protected String bill_to_street;
    protected String bill_to_email_address;
    protected String bill_to_first_name;
    protected String bill_to_last_name;
    protected String pay_type;
    protected String currency;
    protected String amount;
    protected String return_url;
    protected String locale;
    protected String card_num_prefix;
    protected String access_id;
    protected String term_id;
    protected String merch_id;
    protected String merch_order_id;
    protected String action;
    protected String version;

    public String getBill_to_city() {
        return bill_to_city;
    }

    public void setBill_to_city(String bill_to_city) {
        this.bill_to_city = bill_to_city;
    }

    public String getBill_to_country_code() {
        return bill_to_country_code;
    }

    public void setBill_to_country_code(String bill_to_country_code) {
        this.bill_to_country_code = bill_to_country_code;
    }

    public String getBill_to_street() {
        return bill_to_street;
    }

    public void setBill_to_street(String bill_to_street) {
        this.bill_to_street = bill_to_street;
    }

    public String getBill_to_email_address() {
        return bill_to_email_address;
    }

    public void setBill_to_email_address(String bill_to_email_address) {
        this.bill_to_email_address = bill_to_email_address;
    }

    public String getBill_to_first_name() {
        return bill_to_first_name;
    }

    public void setBill_to_first_name(String bill_to_first_name) {
        this.bill_to_first_name = bill_to_first_name;
    }

    public String getBill_to_last_name() {
        return bill_to_last_name;
    }

    public void setBill_to_last_name(String bill_to_last_name) {
        this.bill_to_last_name = bill_to_last_name;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getReturn_url() {
        return return_url;
    }

    public void setReturn_url(String return_url) {
        this.return_url = return_url;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getCard_num_prefix() {
        return card_num_prefix;
    }

    public void setCard_num_prefix(String card_num_prefix) {
        this.card_num_prefix = card_num_prefix;
    }

    public String getAccess_id() {
        return access_id;
    }

    public void setAccess_id(String access_id) {
        this.access_id = access_id;
    }

    public String getTerm_id() {
        return term_id;
    }

    public void setTerm_id(String term_id) {
        this.term_id = term_id;
    }

    public String getMerch_id() {
        return merch_id;
    }

    public void setMerch_id(String merch_id) {
        this.merch_id = merch_id;
    }

    public String getMerch_order_id() {
        return merch_order_id;
    }

    public void setMerch_order_id(String merch_order_id) {
        this.merch_order_id = merch_order_id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
