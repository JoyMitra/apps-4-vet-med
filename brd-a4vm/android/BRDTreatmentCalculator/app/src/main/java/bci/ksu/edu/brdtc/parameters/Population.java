package bci.ksu.edu.brdtc.parameters;

public class Population {

    private float morbidity;
    private float cog;  // Cost of Gain
    private float sp;   // Price Received at Sale
    private int pw;     // Arrival Weight
    private int days;   // Days on Feed
    private float ahc;  // ADG Healthy Cattle

    public Population() {

    }

    public float getMorbidity() {
        return morbidity;
    }

    public void setMorbidity(float morbidity) {
        this.morbidity = morbidity;
    }

    public float getCog() {
        return cog;
    }

    public void setCog(float cog) {
        this.cog = cog;
    }

    public float getSp() {
        return sp;
    }

    public void setSp(float sp) {
        this.sp = sp;
    }

    public int getPw() {
        return pw;
    }

    public void setPw(int pw) {
        this.pw = pw;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public float getAhc() {
        return ahc;
    }

    public void setAhc(float ahc) {
        this.ahc = ahc;
    }

    @Override
    public String toString() {
        return "Morbidity: " + this.morbidity
                + "\nCost of Gain: " + this.cog
                + "\nPrice Received at Sale" + this.sp
                + "\nArrival Weight: " + this.sp
                + "\nDays on Feed: " + this.days
                + "\nADG Healthy Cattle: " + this.ahc;
    }
}
