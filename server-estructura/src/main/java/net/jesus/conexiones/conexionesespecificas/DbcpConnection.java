/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jesus.conexiones.conexionesespecificas;

import java.sql.Connection;
import net.jesus.conexiones.connectioninterface.ConnectionInterface;

/**
 *
 * @author jesus
 */
public class DbcpConnection implements ConnectionInterface {

    //Aqui llamaria a un metodo que me guarda los datos como la url, el usuario
    //la pass... para usarlo abajo, y asi poder crear la conexion con la base de datos
    public String datosConexion = null;

    //Implemento los metodos de la clase ConnectionInterface
    @Override
    public Connection newConnection() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void disposeConnection() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
