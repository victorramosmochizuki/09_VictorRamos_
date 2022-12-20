
package dao;

import java.util.List;


public interface ICRUD<Generic> {

    void registrar(Generic modelo) throws Exception;

    void modificar(Generic modelo) throws Exception;

    void eliminar(Generic modelo) throws Exception;

    List<Generic>listar() throws Exception;
}
