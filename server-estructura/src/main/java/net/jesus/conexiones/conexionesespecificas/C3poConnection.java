/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jesus.conexiones.conexionesespecificas;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.sql.Connection;
import net.jesus.conexiones.connectioninterface.ConnectionInterface;
import net.jesus.conexiones.datosestaticos.DatosServer;

/**
 *
 * @author jesus
 */
public class C3poConnection implements ConnectionInterface {

    //Implemento los metodos de la clase ConnectionInterface
    @Override
    public Connection newConnection() throws Exception {

        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setJdbcUrl(DatosServer.url);
        cpds.setUser(DatosServer.user);
        cpds.setPassword(DatosServer.pass);

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void disposeConnection() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
