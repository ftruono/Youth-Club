package it.youthclub.beans;

import java.io.Serializable;

public class Utente implements Serializable {

    private String imei;
    private String JSESSION;

    public String getJSESSION() {
        return JSESSION;
    }

    public void setJSESSION(String JSESSION) {
        this.JSESSION = JSESSION;
    }




    public Utente(String imei) {
        this.imei = imei;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }



}
