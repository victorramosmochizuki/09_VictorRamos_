
package modelo;

import java.io.Serializable;
import lombok.Data;
@Data
public class detVenta implements Serializable{
    
    //Tabla Venta Detalle
    
    //Tabla Venta Detalle
    String codigoMed,generico, comercial, provAbr, presentacion;
    double precio, subtotal;
    int cantidad;
    
}
