package servicios;

import dao.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("validarLote")
public class ValidatorMed extends Conexion implements Validator{

    public static boolean valLote(String lote){
        try {
            String sql="SELECT LOTMED FROM MEDICAMENTO WHERE LOTMED = '"+ lote +"'";
            PreparedStatement ps = Conexion.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("error validarLote" + e.getMessage());
        }
        return false;
    }
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String loteMed = (String) value;
        if (valLote(loteMed) == true) {
            FacesMessage msg = new FacesMessage("Este N° de lote ya ha sido registrado");
            throw new ValidatorException(msg);
        }
    }

}
