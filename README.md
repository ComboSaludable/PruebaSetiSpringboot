# Prueba Técnica Spring Boot - Consultor Backend

## 📌 Descripción

Este proyecto es una API REST desarrollada con **Spring Boot**, que convierte solicitudes en formato **JSON** a **XML** para ser enviadas a un servicio SOAP. Luego, convierte la respuesta de **XML** a **JSON** para devolverla al cliente. Además, la aplicación está **dockerizada** para facilitar su despliegue y ejecución.

## 🚀 Tecnologías Utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Web Services (SOAP)**
- **RestTemplate**
- **JAXB (para conversión JSON ↔ XML)**
- **Docker & Docker Compose**
- **GitHub**

## ⚙️ Requisitos Previos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

- [Java 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Docker](https://www.docker.com/)

## Detalles a tener en cuenta

El url proporcionado en el documento de la prueba no ha funcionando de la forma esperada por lo que se ha creado una nueva instancia url: https://run.mocky.io/v3/580b75d9-5348-4f74-a454-d5225fca3dec

## 🔧 Instalación y Ejecución Local

### 1️⃣ Clonar el Repositorio

```sh
git clone https://github.com/combosaludable/prueba-app.git
cd prueba-app
```

### 2️⃣ Compilar el Proyecto

```sh
mvn clean package
```

### 3️⃣ Ejecutar con Java

```sh
java -jar target/prueba-0.0.1-SNAPSHOT.jar
```

La API estará disponible en: [**http://localhost:8080/api/orders/process**](http://localhost:8080/api/orders/process)

## 🐳 Ejecución con Docker

### 1️⃣ Construir la Imagen Docker

```sh
docker build -t combosaludable/prueba-app .
```

### 2️⃣ Ejecutar el Contenedor

```sh
docker run -p 8080:8080 combosaludable/prueba-app
```

### 3️⃣ Verificar que el Contenedor Está Corriendo

```sh
docker ps
```

## 📦 Despliegue con Docker Hub

Este proyecto está disponible en Docker Hub y puede ejecutarse directamente con:

```sh
docker pull combosaludable/prueba-app

docker run -p 8080:8080 combosaludable/prueba-app
```

## 🔥 Uso de la API

### 🔹 Endpoint: ``

#### 📨 JSON Request:

```json
{
  "numPedido": "75630275",
  "cantidadPedido": 1,
  "codigoEAN": "00110000765191002104587",
  "nombreProducto": "Armario INVAL",
  "numDocumento": "1113987400",
  "direccion": "CR 72B 45 12 APT 301"
}
```

#### 📩 XML Request (convertido internamente):

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
   <soapenv:Header/>
   <soapenv:Body>
      <EnvioPedidoRequest>
         <pedido>75630275</pedido>
         <Cantidad>1</Cantidad>
         <EAN>00110000765191002104587</EAN>
         <Producto>Armario INVAL</Producto>
         <Cedula>1113987400</Cedula>
         <Direccion>CR 72B 45 12 APT 301</Direccion>
      </EnvioPedidoRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

#### 📥 JSON Response:

```json
{
  "codigoEnvio": "80375472",
  "estado": "Entregado exitosamente al cliente"
}
```

## 🛠 Docker Compose

Si deseas levantar el servicio con **Docker Compose**, usa:

```sh
docker-compose up --build
```

Esto iniciará el servicio en el puerto **8080**.
