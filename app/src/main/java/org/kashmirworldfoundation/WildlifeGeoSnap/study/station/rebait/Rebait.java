//package org.kashmirworldfoundation.snowleopardapp;
//
//import com.google.firebase.database.Exclude;
//
//class Rebait {
//    private String stationId;
//    private String totalPics;
//    private String signsInput;
//    private String sdNum;
//    private String lureType;
//    private String camWorks;
//    //private Date currentTime;
//
//    public Rebait() {
//
//    }
//    public Rebait(String totalPics, String signsInput, String sdNum, String lureType, String camWorks){
//        this.totalPics = totalPics;
//        this.signsInput = signsInput;
//        this.sdNum = sdNum;
//        this.lureType = lureType;
//        this.camWorks = camWorks;
//    }
//
//    @Exclude
//    public String getStationId() {
//        return stationId;
//    }
//
//    public void setStationId(String stationId) {
//        this.stationId = stationId;
//    }
//
//    public String getTotalPics(){ return totalPics;}
//    public String getSignsInput(){ return signsInput;}
//    public String getSdNum(){ return sdNum;}
//    public String getLureType() { return lureType;}
//    public String getCamWorks() { return camWorks; }
//}
package org.kashmirworldfoundation.WildlifeGeoSnap.study.station.rebait;

import com.google.firebase.database.Exclude;
import java.util.Date;

public class Rebait {
    private String sdID;
    private String totalPics;
    private String date;

    // Constructor
    public Rebait(String sdId, String totalPics, String date){
        this.date = date;
        this.totalPics = totalPics;
        this.sdID= sdId;
    }

    // Getters
    public String getsdID() {
        return sdID;
    }

    public String getTotalPics() {
        return totalPics;
    }

    public String getDate() {
        return date;
    }
}
