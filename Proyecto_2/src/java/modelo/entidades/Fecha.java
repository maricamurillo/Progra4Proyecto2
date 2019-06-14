/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.entidades;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Rodrigo
 */
public class Fecha {
     Date objDate;
     String strDateFormat = "hh: mm: ss a dd-MMM-aaaa";

    public String getStrDateFormat() {
        return strDateFormat;
    }

    public void setStrDateFormat(String strDateFormat) {
        this.strDateFormat = strDateFormat;
    }

    public Fecha() {
        Date objDate = new Date();
        SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-MM-dd");
        strDateFormat=objSDF.format(objDate);
    }
    public Date getObjDate() {
        return objDate;
    }
    
}
