﻿Para ejecutar el proyecto:
-Abrir en Eclipse en un computador donde hayan JBossTools y wildfly 10.0.0 _En este servido se realizaron las pruebas_

-Ejecutar el proyecto en un servidor wildfly, en  el 10.0.0 fueron realizadas las pruebas.

Ejemplos para los requerimientos, estos, se encuentran en la carpeta docs dentro del proyecto.

Estas pruebas, están en el  tanto en este documento como en pruebas_para_postman_it3.txt que se encuentra en la carpeta docs del proyecto.

La libreria JRE usada, es la "jre.1.8.0_144"
Las librerias  servlet y para json, están en las carpetas de información web.
La librería wildfly deberá estar en el dispositivo a correr la aplicación.

URL´s para pruebas en postman


RF11

Equivalencia ingredientes

POST  http://localhost:8080/RotondAndes/rest/administradorrestaurante/equivalenciaingredientes

 {
        "idIngrediente1": 1,
        "idIngrediente2": 11
    }

RF12
Equivalencia productos

POST http://localhost:8080/RotondAndes/rest/administradorrestaurante/equivalenciaproductos

{
    "idProducto1": 1010,
    "idProducto2": 69
}

POST

RF13
SurtirRestaurante
Desde administrador restaurante se realiza esta acción

PUT http://localhost:8080/RotondAndes/rest/administradorrestaurante/surtirrestaurante/{nombreRestaurante}

RF14
Registrar orden con equivalencias

POST http://localhost:8080/RotondAndes/rest/ordenRestaurante/equivalencias

{ "ordenRestaurante" : {
        "idOrdenRestaurante": 2222,
        
	"fecha": "2017-10-03",
        
	"idMenu": 2,
        
	"idRotonda": 1,
        
	"idCliente": 1090509418,
        
	"servida": true
    
},  "equivalencias" :
[{
    "idProducto1": 1,
    "idProducto2": 6
}]
}

RF15 
Registrar pedido de productos de una mesa

POST http://localhost:8080/RotondAndes/rest/ordenRestaurante/ordenmesa/{idMesa}

Donde {idMesa} representa a la mesa, ejemplos : a1, vip.

JSON
[{
    "idOrdenRestaurante": 1,
        
	"fecha": "2017-10-03",
        
	"idMenu": 6,
        
	"idRotonda": 1,
        
	"idCliente": 2020202020,
	
	"servida": false,
	
	"mesa": "a1"
}, 
{
    "idOrdenRestaurante": 11,
        
	"fecha": "2017-10-03",
        
	"idMenu": 6,
        
	"idRotonda": 1,
        
	"idCliente": 2020202020,
	
	"servida": false,
	
	"mesa": "a1"
},
{
    "idOrdenRestaurante": 21,
        
	"fecha": "2017-10-03",
        
	"idMenu": 6,
        
	"idRotonda": 1,
        
	"idCliente": 2020202020,
	
	"servida": false,
	
	"mesa": "a1"
}]

RF16
Servir ordenes en una mesa

PUT http://localhost:8080/RotondAndes/rest/ordenRestaurante/ordenmesa/{idMesa}

Donde {idMesa} representa a la mesa, ejemplos : a1, vip.

RF17
Cancelar pedido

DELETE http://localhost:8080/RotondAndes/rest/ordenRestaurante/ordenmesa/{idMesa}

Donde {idMesa} representa a la mesa, ejemplos : a1, vip.


RFC7
Obtener la información de pedidos para un cliente.

GET http://localhost:8080/RotondAndes/rest/cliente/consumocliente/{idCliente}

o bien 

GET http://localhost:8080/RotondAndes/rest/administradorrotonda/consumocliente/{idCliente}

Donde {idCliente}, corresponde a un cliente ya existente.

Algunos id de cliente son: 1010054402 y 2020202020.


RFC8

Obetener información ordenes clientes registrados y no registrados, información venta productos.

GET http://localhost:8080/RotondAndes/rest/administradorrotonda/ordenes	

GET  http://localhost:8080/RotondAndes/rest/administradorrotonda/ordenes/productosmenu




