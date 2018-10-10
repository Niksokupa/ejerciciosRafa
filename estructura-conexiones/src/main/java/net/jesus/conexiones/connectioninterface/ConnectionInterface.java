/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jesus.conexiones.connectioninterface;

import java.sql.Connection;

/**
 *
 * @author jesus
 */
public interface ConnectionInterface {
    
    //Creo la interfaz newConnection que devolvera un objeto de tipo Connection 
    //con toda la info de la conexcion
    public Connection newConnection() throws Exception;
    
    //Destruyo la conexion y no devuelvo nada porque es obvio
    public void disposeConnection() throws Exception;
    
}
