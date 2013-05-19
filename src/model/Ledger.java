/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;
import java.util.Locale;

/**
 *
 * @author t0m
 */
public class Ledger {

    private int led_id;
    private String led_name;
    private String led_per_name;
    private int led_type;
    private String led_add;
    private String led_contact_no;
    private String led_email;
    private String led_vat_tin;
    private String led_cst_tin;
    private String led_card_loc;
    private float led_open_bal;
    private int led_open_bal_type;
    private float led_pre_bal;
    private int led_pre_bal_type;
    private String led_creation_date;
    private String led_modification_date;

    public Ledger(String led_name, String led_per_name, int led_type, String led_add, String led_contact_no, String led_email, String led_vat_tin, String led_cst_tin, String led_card_loc, float led_open_bal, int led_open_bal_type) {
        this.led_name = led_name;
        this.led_per_name = led_per_name;
        this.led_type = led_type;
        this.led_add = led_add;
        this.led_contact_no = led_contact_no;
        this.led_email = led_email;
        this.led_vat_tin = led_vat_tin;
        this.led_cst_tin = led_cst_tin;
        this.led_card_loc = led_card_loc;
        this.led_open_bal = led_open_bal;
        this.led_open_bal_type = led_open_bal_type;
        Calendar cal = Calendar.getInstance();
        int mon = cal.MONTH+1;
        this.led_creation_date = cal.DATE+"/"+mon+"/"+cal.YEAR;
        this.led_modification_date = led_creation_date;
        
        this.led_pre_bal = led_open_bal;
        this.led_pre_bal_type = led_open_bal_type;
        
    }

    public String getLed_name() {
        return led_name;
    }

    public void setLed_name(String led_name) {
        this.led_name = led_name;
    }

    public String getLed_per_name() {
        return led_per_name;
    }

    public void setLed_per_name(String led_per_name) {
        this.led_per_name = led_per_name;
    }

    public int getLed_type() {
        return led_type;
    }

    public void setLed_type(int led_type) {
        this.led_type = led_type;
    }

    public String getLed_add() {
        return led_add;
    }

    public void setLed_add(String led_add) {
        this.led_add = led_add;
    }

    public String getLed_contact_no() {
        return led_contact_no;
    }

    public void setLed_contact_no(String led_contact_no) {
        this.led_contact_no = led_contact_no;
    }

    public String getLed_email() {
        return led_email;
    }

    public void setLed_email(String led_email) {
        this.led_email = led_email;
    }

    public String getLed_vat_tin() {
        return led_vat_tin;
    }

    public void setLed_vat_tin(String led_vat_tin) {
        this.led_vat_tin = led_vat_tin;
    }

    public String getLed_cst_tin() {
        return led_cst_tin;
    }

    public void setLed_cst_tin(String led_cst_tin) {
        this.led_cst_tin = led_cst_tin;
    }

    public String getLed_card_loc() {
        return led_card_loc;
    }

    public void setLed_card_loc(String led_card_loc) {
        this.led_card_loc = led_card_loc;
    }

    public float getLed_open_bal() {
        return led_open_bal;
    }

    public void setLed_open_bal(float led_open_bal) {
        this.led_open_bal = led_open_bal;
    }

    public int getLed_open_bal_type() {
        return led_open_bal_type;
    }

    public void setLed_open_bal_type(char led_open_bal_type) {
        this.led_open_bal_type = led_open_bal_type;
    }

    public float getLed_pre_bal() {
        return led_pre_bal;
    }

    public void setLed_pre_bal(float led_pre_bal) {
        this.led_pre_bal = led_pre_bal;
    }

    public int getLed_pre_bal_type() {
        return led_pre_bal_type;
    }

    public void setLed_pre_bal_type(char led_pre_bal_type) {
        this.led_pre_bal_type = led_pre_bal_type;
    }

    public String getLed_creation_date() {
        return led_creation_date;
    }

    public void setLed_creation_date(String led_creation_date) {
        this.led_creation_date = led_creation_date;
    }

    public String getLed_modification_date() {
        return led_modification_date;
    }

    public void setLed_modification_date(String led_modification_date) {
        this.led_modification_date = led_modification_date;
    }

    
}
