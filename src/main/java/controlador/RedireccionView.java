
package controlador;

import java.io.IOException;
import javax.inject.Named;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;


@Named
@RequestScoped
public class RedireccionView {

    public void onIdle() throws IOException {
      FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8080/FARMAVIC_ODAO/");
    }
    
}
