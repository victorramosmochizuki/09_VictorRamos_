package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CharUsuarioImpl extends Conexion {

    public List<Number> graficoPer() throws Exception {
        this.conectar();
        List<Number> lista = new ArrayList();
        try {
            String sql = "SELECT COUNT(CASE SEXCLI WHEN 'M' THEN 'M' END) AS CANTHOM , COUNT(CASE SEXCLI WHEN 'F' THEN 'F' END) AS CANTMUJ FROM CLIENTE";
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.println("Existen datos");
                lista.add(rs.getInt("CANTHOM"));
                lista.add(rs.getInt("CANTMUJ"));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("Error en la lista Persona dao " + e);
        } finally {
            this.cerrar();
        }
        return lista;
    }
}
