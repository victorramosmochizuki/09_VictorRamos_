package modelo;

import java.util.Date;
import lombok.Data;

@Data
public class Medicamento {

    int IDMED,STOCMED;
    String PRESMED, GENMED, COMMED,LOTMED,ESTMED,IDPROV;
    Double PRECMED;
    Date FVMED;
    
    Proveedor proveedor = new Proveedor();
    
}
