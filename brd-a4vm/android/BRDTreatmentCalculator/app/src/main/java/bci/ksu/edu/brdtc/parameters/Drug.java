package bci.ksu.edu.brdtc.parameters;

public class Drug {

    private String name;
    private float tfp;  // Treatment Failure Percent
    private float cfr;  // Case Fatality Risk
    private float ct1;  // Cost of Treatment
    private float cp;   // Chronic Percent

    public Drug() {}

    public Drug(Drug drug) {
        this.name = drug.getName();
        this.tfp = drug.getTfp();
        this.cfr = drug.getCfr();
        this.ct1 = drug.getCt1();
        this.cp = drug.getCp();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getTfp() {
        return tfp;
    }

    public void setTfp(float tfp) {
        this.tfp = tfp;
    }

    public float getCfr() {
        return cfr;
    }

    public void setCfr(float cfr) {
        this.cfr = cfr;
    }

    public float getCt1() {
        return ct1;
    }

    public void setCt1(float ct1) {
        this.ct1 = ct1;
    }

    public float getCp() {
        return cp;
    }

    public void setCp(float cp) {
        this.cp = cp;
    }

    @Override
    public String toString() {
        return "Drug Name: " + this.name
                + "\nTreatment Failure Percent: " + this.tfp
                + "\nCase Fatality Risk: " + this.cfr
                + "\nCost of Treatment: " + this.ct1
                + "\nChronic Percent: " + this.cp;
    }
}
