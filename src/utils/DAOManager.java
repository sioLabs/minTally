/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author t0m
 */
public class DAOManager {
    
    public static DAOManager instance = null;
    
    private DAOManager(){};
    
    public static DAOManager getInstance(){
        if(instance == null)
            instance = new DAOManager();
        
        return instance;
    }
    
    
    
}
