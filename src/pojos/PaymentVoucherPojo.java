/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.util.Date;

/**
 *
 * @author t0m
 */
public class PaymentVoucherPojo {
    private int voucher_no;
    private Date date;
    private String RefNo;

    /**
     * @return the voucher_no
     */
    public int getVoucher_no() {
        return voucher_no;
    }

    /**
     * @param voucher_no the voucher_no to set
     */
    public void setVoucher_no(int voucher_no) {
        this.voucher_no = voucher_no;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the RefNo
     */
    public String getRefNo() {
        return RefNo;
    }

    /**
     * @param RefNo the RefNo to set
     */
    public void setRefNo(String RefNo) {
        this.RefNo = RefNo;
    }
    
    
    
}
