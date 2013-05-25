/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ashutoshsingh
 */
public class EntityManagerHelper {
    
    private static EntityManagerHelper instance;
    private EntityManager em;
    
    private EntityManagerHelper(){
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("InnuInfocommPU");
        em  = emf.createEntityManager();
    }
    
    public static EntityManagerHelper getInstance(){
    
        if(instance==null){
                instance = new EntityManagerHelper();
                }
       
        return instance;
        
    }
    
    public EntityManager getEm(){
        return em;
    }
    
}
