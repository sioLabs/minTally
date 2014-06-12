/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pojos;

/**
 *
 * @author PIR
 */
public class CustMiniStmtBean {

    public CustMiniStmtBean(int billNo, String billDate, String totalAmount) {
        this.billNo = billNo;
        this.billDate = billDate;
        this.totalAmount = totalAmount;
    }
    
    private int billNo;
    private String billDate;
    private String totalAmount;
            
    
    
    
}
