package servicios;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("sexoConverter")
public class SexoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String seguro ="";
        if(value!=null){
            seguro = (String) value;
            switch(seguro){
                case "1": seguro = "DISTRIBUIDOR"; break;
                case "2": seguro = "LABORATORIO"; break;
            }
        }
        return seguro;
    }
    
}  
