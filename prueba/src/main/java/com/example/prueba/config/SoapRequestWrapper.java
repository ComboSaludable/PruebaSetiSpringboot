package com.example.prueba.config;

import com.example.prueba.dto.PedidoDTO;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "soapenv:Envelope")
public class SoapRequestWrapper {

    @XmlElement(name = "soapenv:Body")
    private SoapBody body;

    public SoapRequestWrapper() {
        this.body = new SoapBody();
    }

    public SoapRequestWrapper(PedidoDTO pedido) {
        this.body = new SoapBody(pedido);
    }
}

class SoapBody {
    @XmlElement(name = "env:EnvioPedidoAcme")
    private PedidoDTO pedido;

    public SoapBody() {}

    public SoapBody(PedidoDTO pedido) {
        this.pedido = pedido;
    }
}
