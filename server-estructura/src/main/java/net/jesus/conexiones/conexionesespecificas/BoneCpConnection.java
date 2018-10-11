/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jesus.conexiones.conexionesespecificas;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import java.sql.Connection;
import net.jesus.conexiones.connectioninterface.ConnectionInterface;
import net.jesus.conexiones.datosestaticos.DatosServer;

/**
 *
 * @author jesus
 */
public class BoneCpConnection implements ConnectionInterface {

    private BoneCP connectionPool = null;
    private Connection connection = null;   

    @Override
    public Connection newConnection() throws Exception {

        try {
            // setup the connection pool
            BoneCPConfig config = new BoneCPConfig();
            config.setJdbcUrl(DatosServer.url); // jdbc url specific to your database, eg jdbc:mysql://127.0.0.1/yourdb
            config.setUsername(DatosServer.user);
            config.setPassword(DatosServer.pass);
            config.setMinConnectionsPerPartition(5);
            config.setMaxConnectionsPerPartition(10);
            config.setPartitionCount(1);
            connectionPool = new BoneCP(config); 

            connection = connectionPool.getConnection();
            System.out.print(connection);
        } catch (Exception e) {
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
