
package modelo;

import java.util.Date;
import java.util.GregorianCalendar; //importa fecha actual


public class Compra {
    
    
    private int IDCOM,IDPROV,IDMED,CANTCOM;
    private Date FECHCOM = GregorianCalendar.getInstance().getTime(); //Pone la fecha actual
    private Double IMPCOM;
    
    private Proveedor proveedor = new Proveedor(); // instanciar
    private Medicamento medicamento = new Medicamento(); 

    public Compra() {
    }

    public Compra(int IDCOM, int IDPROV, int IDMED, int CANTCOM, Date FECHCOM, Double IMPCOM, Proveedor proveedor, Medicamento medicamento) {
        this.IDCOM = IDCOM;
        this.IDPROV = IDPROV;
        this.IDMED = IDMED;
        this.CANTCOM = CANTCOM;
        this.FECHCOM = FECHCOM;
        this.IMPCOM = IMPCOM;
        this.proveedor = proveedor;
        this.medicamento = medicamento;
    }

    public int getIDCOM() {
        return IDCOM;
    }

    public void setIDCOM(int IDCOM) {
        this.IDCOM = IDCOM;
    }

    public int getIDPROV() {
        return IDPROV;
    }

    public void setIDPROV(int IDPROV) {
        this.IDPROV = IDPROV;
    }

    public int getIDMED() {
        return IDMED;
    }

    public void setIDMED(int IDMED) {
        this.IDMED = IDMED;
    }

    public int getCANTCOM() {
        return CANTCOM;
    }

    public void setCANTCOM(int CANTCOM) {
        this.CANTCOM = CANTCOM;
    }

    public Date getFECHCOM() {
        return FECHCOM;
    }

    public void setFECHCOM(Date FECHCOM) {
        this.FECHCOM = FECHCOM;
    }

    public Double getIMPCOM() {
        return IMPCOM;
    }

    public void setIMPCOM(Double IMPCOM) {
        this.IMPCOM = IMPCOM;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    @Override
    public String toString() {
        return "Compra{" + "IDCOM=" + IDCOM + ", IDPROV=" + IDPROV + ", IDMED=" + IDMED + ", CANTCOM=" + CANTCOM + ", FECHCOM=" + FECHCOM + ", IMPCOM=" + IMPCOM + ", proveedor=" + proveedor + ", medicamento=" + medicamento + '}';
    }
    
    
    
    
}
