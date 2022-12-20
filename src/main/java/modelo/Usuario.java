
package modelo;

import lombok.Data;

@Data
public class Usuario {
    String NOMUSU;
    String PWUSU;
    int NIVUSU;
    String EMAUSU;
    
    Proveedor proveedor = new Proveedor(); 

//    public void getEMAUSU(String victorramosvallegrandeedupe) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
