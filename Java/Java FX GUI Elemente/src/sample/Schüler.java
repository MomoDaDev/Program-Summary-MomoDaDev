package sample;

public class Schüler {
    public int Katalognummer;
    public String Vorname;
    public String Nachname;
    public String Religion;
    public boolean RK;
    public boolean RE;
    public boolean RISL;
    public boolean ORTH;
    public boolean QUM;
    public boolean ITZJ;
    public boolean UNITY;
    public boolean BSPKU;

    public int getKatalognummer() {
        return Katalognummer;
    }

    public void setKatalognummer(int katalognummer) {
        Katalognummer = katalognummer;
    }

    public String getVorname() {
        return Vorname;
    }

    public void setVorname(String vorname) {
        Vorname = vorname;
    }

    public String getNachname() {
        return Nachname;
    }

    public void setNachname(String nachname) {
        Nachname = nachname;
    }

    public String getReligion() {
        return Religion;
    }

    public void setReligion(String religion) {
        Religion = religion;
    }

    public boolean isRK() {
        return RK;
    }

    public void setRK(boolean RK) {
        this.RK = RK;
    }

    public boolean isRE() {
        return RE;
    }

    public void setRE(boolean RE) {
        this.RE = RE;
    }

    public boolean isRISL() {
        return RISL;
    }

    public void setRISL(boolean RISL) {
        this.RISL = RISL;
    }

    public boolean isORTH() {
        return ORTH;
    }

    public void setORTH(boolean ORTH) {
        this.ORTH = ORTH;
    }

    public boolean isQUM() {
        return QUM;
    }

    public void setQUM(boolean QUM) {
        this.QUM = QUM;
    }

    public boolean isITZJ() {
        return ITZJ;
    }

    public void setITZJ(boolean ITZJ) {
        this.ITZJ = ITZJ;
    }

    public boolean isUNITY() {
        return UNITY;
    }

    public void setUNITY(boolean UNITY) {
        this.UNITY = UNITY;
    }

    public boolean isBSPKU() {
        return BSPKU;
    }

    public void setBSPKU(boolean BSPKU) {
        this.BSPKU = BSPKU;
    }

    @Override
    public String toString() {
        return Katalognummer + Nachname + Vorname + Religion + RK + RE + RISL + ORTH + QUM + ITZJ + UNITY + BSPKU;
    }
}
