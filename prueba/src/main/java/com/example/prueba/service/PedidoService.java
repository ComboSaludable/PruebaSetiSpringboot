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
        JSONObject jsonObject = XML.toJSONObject(xmlResponse);
        return jsonObject.toString(4);
    }
    private final RestTemplate restTemplate;

    public PedidoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

}