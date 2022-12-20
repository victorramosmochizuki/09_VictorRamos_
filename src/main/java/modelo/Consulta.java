
package modelo;

import java.util.Date;


public class Consulta {
    
    private int IDBOLE, IDCLI, IDVEN, IDBODE, CANTBODE, IDMED;
    private Date FECHEMBOLE;
    private Double IMPBOLE, SUBTOT;
    private String ESTBODE, ESTBOLE;
    
    private Cliente cliente = new Cliente();
    private Medicamento medicamento = new Medicamento();

   
    public Consulta() {
    }

    public Consulta(int IDBOLE, int IDCLI, int IDVEN, int IDBODE, int CANTBODE, int IDMED, Date FECHEMBOLE, Double IMPBOLE, Double SUBTOT, String ESTBODE, String ESTBOLE) {
        this.IDBOLE = IDBOLE;
        this.IDCLI = IDCLI;
        this.IDVEN = IDVEN;
        this.IDBODE = IDBODE;
        this.CANTBODE = CANTBODE;
        this.IDMED = IDMED;
        this.FECHEMBOLE = FECHEMBOLE;
        this.IMPBOLE = IMPBOLE;
        this.SUBTOT = SUBTOT;
        this.ESTBODE = ESTBODE;
        this.ESTBOLE = ESTBOLE;
    }

    public int getIDBOLE() {
        return IDBOLE;
    }

    public void setIDBOLE(int IDBOLE) {
        this.IDBOLE = IDBOLE;
    }

    public int getIDCLI() {
        return IDCLI;
    }

    public void setIDCLI(int IDCLI) {
        this.IDCLI = IDCLI;
    }

    public int getIDVEN() {
        return IDVEN;
    }

    public void setIDVEN(int IDVEN) {
        this.IDVEN = IDVEN;
    }

    public int getIDBODE() {
        return IDBODE;
    }

    public void setIDBODE(int IDBODE) {
        this.IDBODE = IDBODE;
    }

    public int getCANTBODE() {
        return CANTBODE;
    }

    public void setCANTBODE(int CANTBODE) {
        this.CANTBODE = CANTBODE;
    }

    public int getIDMED() {
        return IDMED;
    }

    public void setIDMED(int IDMED) {
        this.IDMED = IDMED;
    }

    public Date getFECHEMBOLE() {
        return FECHEMBOLE;
    }

    public void setFECHEMBOLE(Date FECHEMBOLE) {
        this.FECHEMBOLE = FECHEMBOLE;
    }

    public Double getIMPBOLE() {
        return IMPBOLE;
    }

    public void setIMPBOLE(Double IMPBOLE) {
        this.IMPBOLE = IMPBOLE;
    }

    public Double getSUBTOT() {
        return SUBTOT;
    }

    public void setSUBTOT(Double SUBTOT) {
        this.SUBTOT = SUBTOT;
    }

    public String getESTBODE() {
        return ESTBODE;
    }

    public void setESTBODE(String ESTBODE) {
        this.ESTBODE = ESTBODE;
    }

    public String getESTBOLE() {
        return ESTBOLE;
    }

    public void setESTBOLE(String ESTBOLE) {
        this.ESTBOLE = ESTBOLE;
    }

    @Override
    public String toString() {
        return "Consulta{" + "IDBOLE=" + IDBOLE + ", IDCLI=" + IDCLI + ", IDVEN=" + IDVEN + ", IDBODE=" + IDBODE + ", CANTBODE=" + CANTBODE + ", IDMED=" + IDMED + ", FECHEMBOLE=" + FECHEMBOLE + ", IMPBOLE=" + IMPBOLE + ", SUBTOT=" + SUBTOT + ", ESTBODE=" + ESTBODE + ", ESTBOLE=" + ESTBOLE + '}';
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    
    
    
    
}
