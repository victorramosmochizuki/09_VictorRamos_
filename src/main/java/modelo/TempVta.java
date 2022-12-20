package modelo;

import java.io.Serializable;
import lombok.Data;

@Data
public class TempVta implements Serializable {
    int IDMED, CANTBODE, STOCKMED;
    double precio, subTotal;
    String presentacion, comercial, generico, proveedor;    
}
