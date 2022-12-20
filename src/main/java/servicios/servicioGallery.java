package servicios;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;



public class servicioGallery implements Serializable{
    public static int numero;
    public static int Randon() {
        
        numero = (int) (Math.random()*1000 + 1000);
        System.out.println("El número aleatorio es:" + numero );
        return numero;
    } 
    
    
    
}
