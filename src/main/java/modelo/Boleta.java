package modelo;

import java.util.Date;
import java.util.GregorianCalendar;
import lombok.Data;

@Data
public class Boleta {
    private int IDBOLE,IDCLI,IDVEN;
    private Date FECHEMBOLE = GregorianCalendar.getInstance().getTime();
    private Double IMPBOLE;
    private Cliente cliente = new Cliente();
    private Vendedor vendedor = new Vendedor();
    
    public Boleta() {
    }

    public Boleta(int IDBOLE, int IDCLI, int IDVEN, Date FECHEMBOLE, Double IMPBOLE, String ESTBOLE) {
        this.IDBOLE = IDBOLE;
        this.IDCLI = IDCLI;
        this.IDVEN = IDVEN;
        this.FECHEMBOLE = FECHEMBOLE;
        this.IMPBOLE = IMPBOLE;
    }

    @Override
    public String toString() {
        return "Boleta{" + "IDBOLE=" + IDBOLE + ", IDCLI=" + IDCLI + ", IDVEN=" + IDVEN + ", FECHEMBOLE=" + FECHEMBOLE + ", IMPBOLE=" + IMPBOLE + ", cliente=" + cliente + ", vendedor=" + vendedor + '}';
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
  
}
