/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jesus.conexiones.conexionesespecificas;

import java.sql.Connection;
import java.sql.SQLException;
import net.jesus.conexiones.connectioninterface.ConnectionInterface;
import net.jesus.conexiones.datosestaticos.DatosServer;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author jesus
 */
public class DbcpConnection implements ConnectionInterface {

    private BasicDataSource ds = null;
    private Connection connection = null;

    @Override
    public Connection newConnection() throws Exception {
        ds = new BasicDataSource();
        ds.setUrl(DatosServer.url);
        ds.setUsername(DatosServer.user);
        ds.setPassword(DatosServer.pass);
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
        try {
            connection = ds.getConnection();
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return connection;
    }

    @Override
    public void disposeConnection() throws Exception {
        try {
            if (connection != null) {
                connection.close();
            }
            if (ds != null) {
                ds.close();
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

}
