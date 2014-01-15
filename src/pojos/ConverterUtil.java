/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

/**
 *
 * @author t0m
 * The class used to convert the core data pojos for the javafx data.
 */
public class ConverterUtil {
    
    
    public SalebillItem Items2SalebillItem(Items input){
        SalebillItem sbItem = new SalebillItem();
        
        sbItem.setItemId(input.getItemId());
        sbItem.setItemName(input.getItemName());
        sbItem.setItemQnty(1.0);
        sbItem.setItemRate(input.getItemRate());
        sbItem.setItemUnit(input.getItemFirstUnit().getId());
        sbItem.setItemUnitName(input.getItemFirstUnit().getUnitName());
        sbItem.setItemVatPerc(input.getItemVatPerc());
        //sbItem.set
        
        
        return sbItem;
    }
    
}
