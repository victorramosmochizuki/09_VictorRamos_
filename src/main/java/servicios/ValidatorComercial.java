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

@FacesValidator("validarComercial")
public class ValidatorComercial extends Conexion implements Validator{

    public static boolean valLote(String comercial){
        try {
            String sql="SELECT COMMED FROM MEDICAMENTO WHERE COMMED = '"+ comercial +"'";
            PreparedStatement ps = Conexion.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("error validarComercial" + e.getMessage());
        }
        return false;
    }
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String comercialMed = (String) value;
        if (valLote(comercialMed) == true) {
            FacesMessage msg = new FacesMessage("Este medicamento comercial ya se ha registrado");
            throw new ValidatorException(msg);
        }
    }

}
