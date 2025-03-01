package com.example.prueba.service;
import com.example.prueba.config.SoapRequestWrapper;
import com.example.prueba.dto.PedidoDTO;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import jakarta.xml.bind.*;


import java.io.StringWriter;
import java.util.logging.Logger;

@Service
public class PedidoService {

    /*
    * Lectura del mocky url nuevo generado dado la no disponibilidad del proporcionado en el documento
    * */
    @Value("${soap.service.url}")
    private String SOAP_ENDPOINT;

    private static final Logger LOGGER = Logger.getLogger(PedidoService.class.getName());


    public String processOrder(PedidoDTO request) {
        try {
            String xmlRequest = convertToXml(request);
            LOGGER.info("XML Request Sent:\n" + xmlRequest);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_XML);
            HttpEntity<String> entity = new HttpEntity<>(xmlRequest, headers);

            ResponseEntity<String> response = restTemplate.exchange(SOAP_ENDPOINT, HttpMethod.POST, entity, String.class);

            if (response.getBody() == null) {
                LOGGER.warning("SOAP Response is null");
                return "Error: Received null response from SOAP service";
            }

            LOGGER.info("XML Response Received:\n" + response.getBody());

            return convertXmlToJson(response.getBody());
        } catch (Exception e) {
            LOGGER.severe("Exception processing order: " + e.getMessage());
            return "Error processing order: " + e.getMessage();
        }
    }


    private String convertToXml(PedidoDTO request) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(SoapRequestWrapper.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        StringWriter writer = new StringWriter();
        marshaller.marshal(new SoapRequestWrapper(request), writer);
        return writer.toString();
    }


    private String convertXmlToJson(String xmlResponse) {
        // Convertir XML a JSONObject
        JSONObject jsonObject = XML.toJSONObject(xmlResponse);

        // Extraer los datos necesarios navegando en la estructura JSON
        JSONObject body = jsonObject.getJSONObject("soapenv:Envelope")
                .getJSONObject("soapenv:Body")
                .getJSONObject("env:EnvioPedidoAcmeResponse")
                .getJSONObject("EnvioPedidoResponse");

        // Construir la respuesta final con el formato correcto
        JSONObject formattedResponse = new JSONObject();
        JSONObject enviarPedidoRespuesta = new JSONObject();

        enviarPedidoRespuesta.put("codigoEnvio", body.getInt("Codigo"));
        enviarPedidoRespuesta.put("estado", body.getString("Mensaje"));

        formattedResponse.put("enviarPedidoRespuesta", enviarPedidoRespuesta);

        return formattedResponse.toString(4); // Indentación para que sea más legible
    }
    private final RestTemplate restTemplate;

    public PedidoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

}