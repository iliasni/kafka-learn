import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.Date;

public class StellarObject {
    @JsonProperty("designation")
    private String designation;

    @JsonProperty("discovery_date")
    private Date discoveryDate;

    @JsonProperty("h_mag")
    private float hMag;

    @JsonProperty("moid_au")
    private float moidAu;

    @JsonProperty("q_au_1")
    private float qAu1;

    @JsonProperty("q_au_2")
    private float qAu2;

    @JsonProperty("period_yr")
    private float periodYr;

    @JsonProperty("i_deg")
    private float iDeg;

    @JsonProperty("pha")
    private Boolean pha;

    @JsonProperty("orbit_class")
    private String orbitClass;

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Date getDiscoveryDate() {
        return discoveryDate;
    }

    public void setDiscoveryDate(Date discoveryDate) {
        this.discoveryDate = discoveryDate;
    }

    public float gethMag() {
        return hMag;
    }

    public void sethMag(float hMag) {
        this.hMag = hMag;
    }

    public float getMoidAu() {
        return moidAu;
    }

    public void setMoidAu(float moidAu) {
        this.moidAu = moidAu;
    }

    public float getqAu1() {
        return qAu1;
    }

    public void setqAu1(float qAu1) {
        this.qAu1 = qAu1;
    }

    public float getqAu2() {
        return qAu2;
    }

    public void setqAu2(float qAu2) {
        this.qAu2 = qAu2;
    }

    public float getPeriodYr() {
        return periodYr;
    }

    public void setPeriodYr(float periodYr) {
        this.periodYr = periodYr;
    }

    public float getiDeg() {
        return iDeg;
    }

    public void setiDeg(float iDeg) {
        this.iDeg = iDeg;
    }

    public Boolean isPha() {
        return pha;
    }

    public void setPha(String pha) {
        if ("Y".equals(pha)) {
            this.pha = true;
        } else if ("N".equals(pha)) {
            this.pha = false;
        } else {
            this.pha = null;
        }
    }

    public String getOrbitClass() {
        return orbitClass;
    }

    public void setOrbitClass(String orbitClass) {
        this.orbitClass = orbitClass;
    }

    @Override
    public String toString() {
        return "StellarObject{" +
                "designation='" + designation + '\'' +
                ", discovery_date=" + discoveryDate +
                ", h_mag=" + hMag +
                ", moid_au=" + moidAu +
                ", q_au_1=" + qAu1 +
                ", q_au_2=" + qAu2 +
                ", period_yr=" + periodYr +
                ", i_deg=" + iDeg +
                ", pha=" + pha +
                ", orbit_class='" + orbitClass + '\'' +
                '}';
    }

}
