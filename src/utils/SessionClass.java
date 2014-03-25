	

    /*
     * To change this template, choose Tools | Templates
     * and open the template in the editor.
     */
    package utils;
     
    /**
     *
     * @author ashutoshsingh
     */
    public class SessionClass {
       
        private String vatNumber = null;
        private String mobileNumber = "9766162186";
     
        public String getMobileNumber() {
            return mobileNumber;
        }
     
        public void setMobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
        }
     
        public String getVatNumber() {
            return vatNumber;
        }
     
        public void setVatNumber(String vatNumber) {
            this.vatNumber = vatNumber;
        }
        private static  SessionClass instance;
       
        private SessionClass(){
           
        }
       
        public static SessionClass getInstance(){
            if ( null == instance)
                instance = new SessionClass();
            return instance;
               
        }
       
       
       
    }

