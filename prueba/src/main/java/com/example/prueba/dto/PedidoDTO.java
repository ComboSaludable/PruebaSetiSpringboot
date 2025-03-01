package com.example.prueba.dto;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "EnvioPedidoRequest")
public class PedidoDTO {
    private String numPedido;
    private int cantidadPedido;
    private String codigoEAN;
    private String nombreProducto;
    private String numDocumento;
    private String direccion;

    // Getters and setters
    public String getNumPedido() { return numPedido; }
    public void setNumPedido(String numPedido) { this.numPedido = numPedido; }
    public int getCantidadPedido() { return cantidadPedido; }
    public void setCantidadPedido(int cantidadPedido) { this.cantidadPedido = cantidadPedido; }
    public String getCodigoEAN() { return codigoEAN; }
    public void setCodigoEAN(String codigoEAN) { this.codigoEAN = codigoEAN; }
    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }
    public String getNumDocumento() { return numDocumento; }
    public void setNumDocumento(String numDocumento) { this.numDocumento = numDocumento; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
}
