/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jesus.conexiones.conexionesespecificas;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import net.jesus.conexiones.connectioninterface.ConnectionInterface;
import net.jesus.conexiones.datosestaticos.DatosServer;

/**
 *
 * @author jesus
 */
public class HikariConnection implements ConnectionInterface {

    HikariDataSource oConnectionPool = null;
    Connection connection = null;

    //Implemento los metodos de la clase ConnectionInterface
    @Override
    public Connection newConnection() throws Exception {

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(DatosServer.url);
        config.setUsername(DatosServer.user);
        config.setPassword(DatosServer.pass);

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        try {
            oConnectionPool = new HikariDataSource(config);
            connection = (Connection) oConnectionPool.getConnection();
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
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

}
