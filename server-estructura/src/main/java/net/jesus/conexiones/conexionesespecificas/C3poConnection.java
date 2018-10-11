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

    private ComboPooledDataSource config = null;
    private Connection conexion = null;

    @Override

    public Connection newConnection() throws Exception {
        try {
            config = new ComboPooledDataSource();
            config.setJdbcUrl(DatosServer.url);
            config.setUser(DatosServer.user);
            config.setPassword(DatosServer.pass);
            config.setMaxStatements(180);
            conexion = config.getConnection();
        } catch (Exception e) {
            throw new Exception(e);
        }
        return conexion;
    }

    @Override
    public void disposeConnection() throws Exception {
        try {
            if (conexion != null) {
                conexion.close();
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

}
