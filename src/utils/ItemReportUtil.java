/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import pojos.ItemsPharma;
import pojos.SaleBillPharma;

/**
 *
 * @author PIR
 */
public class ItemReportUtil {
    
    private ArrayList<ItemsPharma> itemsSold;
    private ArrayList<String> itemsQntySold;
    private ArrayList<Float> itemsAmt;
    private float totalAmount;
    private Date startDate = null;
    private Date endDate = null;
    private Date reportDate = null;
    
    
    
    //default constructor
    public ItemReportUtil(){   }
    
    public ItemReportUtil(Date sDate, Date eDate){
        startDate = sDate;
        
        //for the single day report
        if(eDate == null)
            endDate = sDate;
        else
            endDate = eDate;
        
        
        reportDate = new Date();
        generateSaleReport();
    }

    
    private void generateSaleReport() {
        //put all the business logic here
        
        EntityManager em = EntityManagerHelper.getInstance().getEm();
        
        List<SaleBillPharma> salebills = em.createQuery(
                "Select s from SaleBillPharma WHERE s.billDate >= :sdate AND s.billDate <= :edate")
                .setParameter("sdate", startDate)
                .setParameter("edate", endDate).getResultList();
                
       for(SaleBillPharma s : salebills){
           System.out.println(s.getFinalAmt());
       }
              
        
        
    }
    
    
}
