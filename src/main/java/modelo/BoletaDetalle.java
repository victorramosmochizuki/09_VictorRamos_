package modelo;

import lombok.Data;

@Data
public class BoletaDetalle {

    private Integer IDBODE, CANTBODE, IDBOLE, IDMED;
    private Double SUBTOT;
    private String NOMCLI;
    
    private Boleta boleta = new Boleta();
    private Medicamento medicamento = new Medicamento();

    public BoletaDetalle() {
    }

    public BoletaDetalle(Integer IDBODE, Integer CANTBODE, Integer IDBOLE, Integer IDMED, Double SUBTOT, String ESTBODE, String NOMCLI) {
        this.IDBODE = IDBODE;
        this.CANTBODE = CANTBODE;
        this.IDBOLE = IDBOLE;
        this.IDMED = IDMED;
        this.SUBTOT = SUBTOT;
        this.NOMCLI = NOMCLI;
    }
    
    
    

    @Override
    public String toString() {
        return "BoletaDetalle{" + "IDBODE=" + IDBODE + ", CANTBODE=" + CANTBODE + ", IDBOLE=" + IDBOLE + ", IDMED=" + IDMED + ", SUBTOT=" + SUBTOT + ", NOMCLI=" + NOMCLI + ", boleta=" + boleta + ", medicamento=" + medicamento + '}';
    }

    
    
    public String getNOMCLI() {
        return NOMCLI;
    }

    public void setNOMCLI(String NOMCLI) {
        this.NOMCLI = NOMCLI;
    }


    public Integer getIDBODE() {
        return IDBODE;
    }

    public void setIDBODE(Integer IDBODE) {
        this.IDBODE = IDBODE;
    }

    public Integer getCANTBODE() {
        return CANTBODE;
    }

    public void setCANTBODE(Integer CANTBODE) {
        this.CANTBODE = CANTBODE;
    }

    public Integer getIDBOLE() {
        return IDBOLE;
    }

    public void setIDBOLE(Integer IDBOLE) {
        this.IDBOLE = IDBOLE;
    }

    public Integer getIDMED() {
        return IDMED;
    }

    public void setIDMED(Integer IDMED) {
        this.IDMED = IDMED;
    }

    public Double getSUBTOT() {
        return SUBTOT;
    }

    public void setSUBTOT(Double SUBTOT) {
        this.SUBTOT = SUBTOT;
    }

    public void setBoleta(Boleta boleta) {
        this.boleta = boleta;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }
  
}
