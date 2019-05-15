/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.tt.util;


import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoTimeoutException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class ConexionJavaMongo {
    MongoClient mongoClient;
    DB mongoData;
    DBCollection mongoCollection;
    String base,coleccion;

    public ConexionJavaMongo(String base, String coleccion) {
        this.base = base;
        this.coleccion = coleccion;
    }
    
//
    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public DB getMongoData() {
        return mongoData;
    }

    public DBCollection getMongoCollection() {
        return mongoCollection;
    }
    public void conectarDB() throws UnknownHostException
    {
         mongoClient = new MongoClient(
                 new MongoClientURI(
                         "mongodb://Axel:Wolverine88@cluster0-shard-00-00-bhohc.mongodb.net:27017,"
                                 + "cluster0-shard-00-01-bhohc.mongodb.net:27017"
                                 + ",cluster0-shard-00-02-bhohc.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true"));
         System.out.println("Conexion Establecida con:"+mongoClient.toString());
    }
    public void obtenerBase(String tabla)
    {
        mongoData = mongoClient.getDB(tabla);
        System.out.println("Conexion Exitosa a :"+tabla);
    }
    public void obtenerColeccion(String col)
    {
        mongoCollection=mongoData.getCollection(col);
    }
    public void conectar()
    {
        try {
            this.conectarDB();
            this.obtenerBase(base);
            this.obtenerColeccion(coleccion);
        } catch ( UnknownHostException | MongoTimeoutException ex ) {
            System.out.println("NO SE PUEDE ACCEDER EN ESTE MOMENTO TONTO");
        }
    }
    public void cerrarConexion()
    {
        
        mongoClient.close();
        System.out.println("Conexion Cerrada");
    }
    
    
}
