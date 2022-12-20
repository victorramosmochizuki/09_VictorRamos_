package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import modelo.Compra;
import modelo.Medicamento;
import modelo.Proveedor;
import servicios.UtilToSql;

public class CompraImpl extends Conexion implements ICRUD<Compra> {
    
    DateFormat formato = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    public void registrar(Compra com) throws Exception {
        
        String sql = "INSERT INTO COMPRA (IDPROV, IDMED, CANTCOM, IMPCOM, FECHCOM) VALUES (?,?,?,?,?)"; 
        try {
            System.out.println("ENVIANDO COMPRA1");
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            System.out.println("ENVIANDO COMPRA2");
            ps.setInt(1, com.getProveedor().getIDPROV());
            ps.setInt(2, com.getMedicamento().getIDMED());
            ps.setInt(3, com.getCANTCOM());
            ps.setDouble(4, com.getIMPCOM());
            

            ps.setString(5, formato.format(com.getFECHCOM()));


            

            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            
            System.out.println("Error al INGRESAR COMPRAD:  " + e.getMessage());
        } finally {
            this.cerrar();
        }

    }

    @Override
    public void modificar(Compra com) throws Exception {

        String sql = "UPDATE COMPRA SET IDPROV=?, IDMED=?, CANTCOM=?, "
                + "IMPCOM=?, FECHCOM=? ";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);

            ps.setInt(1, com.getProveedor().getIDPROV());
            ps.setInt(2, com.getMedicamento().getIDMED());
            ps.setInt(3, com.getCANTCOM());
            ps.setDouble(4, com.getIMPCOM());
            ps.setString(5, formato.format(com.getFECHCOM()));
//            ps.setDate(5, new java.sql.Date(com.getFECHCOM().getTime()));

            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en MODIFICAR COMPRAD: " + e.getMessage());
        }

    }

    @Override
    public void eliminar(Compra com) throws Exception {

        String sql = "DELETE FROM COMPRA WHERE IDCOM=? ";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            
            ps.setInt(1, com.getIDCOM());

            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en ELIMINAR COMPRAD" + e.getMessage());
        }

    }

    @Override
    public List<Compra> listar() throws Exception {

        List<Compra> listado = null;
        Compra com;
        String sql = "SELECT * FROM vCompra";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                com = new Compra();
                Proveedor proveedor = new Proveedor();
                Medicamento medicamento = new Medicamento();

                proveedor.setNOMPROV(rs.getString("NOMPROV"));
                medicamento.setGENMED(rs.getString("GENMED"));

                com.setIDCOM(rs.getInt("IDCOM"));
                com.setIDPROV(rs.getInt("IDPROV"));
                com.setIDMED(rs.getInt("IDMED"));
                com.setFECHCOM(rs.getDate("FECHCOM"));
                com.setCANTCOM(rs.getInt("CANTCOM"));
                com.setIMPCOM(rs.getDouble("IMPCOM"));

                com.setMedicamento(medicamento);
                com.setProveedor(proveedor);

                listado.add(com);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en listarTodos CompraD Lista: " + e.getMessage());
        } finally {
            this.cerrar();
        }
        return listado;

    }

//    public List<String> autocompleteUbigeo(String consulta) throws SQLException, Exception {
//        List<String> lista = new ArrayList<>();
//        String sql = "select  CONCAT(CONCAT(CONCAT(u.NOMDIS,', '),CONCAT(u.NOMPRO,', ')),u.NOMDEP) AS UBIGEODESC from UBIGEO u WHERE u.NOMDIS LIKE ? AND ROWNUM <= 5";
//        try {
//            PreparedStatement ps = this.conectar().prepareCall(sql);
//            ps.setString(1, "%" + consulta.toUpperCase() + "%");
//            ResultSet rs = ps.executeQuery();
//
//            lista.add(rs.getString("UBIGEODESC"));
//        }
//    }
//    catch (Exception e
//
//    
//        ) {
//            System.out.println("Error en autocompleteUbigeoDao" + e.getMessage());
//    }
//    return lista ;
//    }
}
