package modelo;

import java.io.Serializable;

public class VerChart implements Serializable{

    private int CANTHOM;
    private int CANTMUJ;

    public int getCANTHOM() {
        return CANTHOM;
    }

    public void setCANTHOM(int CANTHOM) {
        this.CANTHOM = CANTHOM;
    }

    public int getCANTMUJ() {
        return CANTMUJ;
    }

    public void setCANTMUJ(int CANTMUJ) {
        this.CANTMUJ = CANTMUJ;
    }

}
