package com.meli.bootcamp.shipment.service;

import com.meli.bootcamp.shipment.dto.request.CalculateShipmentRequest;
import com.meli.bootcamp.shipment.dto.request.ProductRequest;
import com.meli.bootcamp.shipment.dto.request.PurchaseRequest;
import com.meli.bootcamp.shipment.dto.response.EnderecoResponse;
import com.meli.bootcamp.shipment.dto.response.ProductResponse;
import com.meli.bootcamp.shipment.entity.Box;
import com.meli.bootcamp.shipment.dto.response.CalculateShipmentResponse;
import com.meli.bootcamp.shipment.entity.Buyer;
import com.meli.bootcamp.shipment.exception.BusinessException;
import com.meli.bootcamp.shipment.exception.NotFoundException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Objects;
import java.util.Properties;

public class ExternalAPIHelper {

    public static Box calculateShipment(Box box, PurchaseRequest purchaseRequest, Buyer buyer) {
        CalculateShipmentRequest request = CalculateShipmentRequest.builder()
                .servico(purchaseRequest.getShippingService())
                .cepOrigem(box.getRemetente().getEndereco().getCep().replace("-", ""))
                .cepDestino(buyer.getEndereco().getCep().replace("-", ""))
                .peso(box.getPeso())
                .diametro(box.getDiametro() > 5 ? box.getDiametro() : box.getDiametro() + 5)
                .largura(box.getLargura() > 10 ? box.getLargura() : box.getLargura() + 10)
                .altura(box.getAltura() > 1 ? box.getAltura() : box.getAltura() + 1)
                .comprimento(box.getComprimento() > 15 ? box.getComprimento() : box.getComprimento() + 15)
                .formato("1")
                .build();

        CalculateShipmentResponse response = new CalculateShipmentResponse();

        //Atributos
        String nCdEmpresa = "";
        String sDsSenha = "";
        String nCdServico = request.getServico();
        String sCepOrigem = request.getCepOrigem();
        String sCepDestino = request.getCepDestino();
        String nVlPeso = Float.toString(request.getPeso());
        String nCdFormato = request.getFormato(); //Pacote ou caixa - 1
        String nVlComprimento = Float.toString(request.getComprimento());
        String nVlAltura = Float.toString(request.getAltura());
        String nVlLargura = Float.toString(request.getLargura());
        String nVlDiametro = "0";
        String sCdMaoPropria = "n";
        String nVlValorDeclarado = "0";
        String sCdAvisoRecebimento = "n";
        String StrRetorno = "xml";

        //URL do webservice Correio
        String urlString = "http://ws.correios.com.br/calculador/CalcPrecoPrazo.aspx";

        //Parâmetros
        Properties parameters = new Properties();

        parameters.setProperty("nCdEmpresa", nCdEmpresa);
        parameters.setProperty("sDsSenha", sDsSenha);
        parameters.setProperty("nCdServico", nCdServico);
        parameters.setProperty("sCepOrigem", sCepOrigem);
        parameters.setProperty("sCepDestino", sCepDestino);
        parameters.setProperty("nVlPeso", nVlPeso);
        parameters.setProperty("nCdFormato", nCdFormato);
        parameters.setProperty("nVlComprimento", nVlComprimento);
        parameters.setProperty("nVlAltura", nVlAltura);
        parameters.setProperty("nVlLargura", nVlLargura);
        parameters.setProperty("nVlDiametro", nVlDiametro);
        parameters.setProperty("sCdMaoPropria", sCdMaoPropria);
        parameters.setProperty("nVlValorDeclarado", nVlValorDeclarado);
        parameters.setProperty("sCdAvisoRecebimento", sCdAvisoRecebimento);
        parameters.setProperty("StrRetorno", StrRetorno);

        //Iterador, para criar a URL
        Iterator i = parameters.keySet().iterator();

        //Contador
        int counter = 0;

        //Percorrer parâmetros
        while (i.hasNext()) {

            //Nome
            String name = (String) i.next();

            //Valor
            String value = parameters.getProperty(name);

            //Adicionar um conector (? ou &)
            urlString += (++counter == 1 ? "?" : "&") + name + "=" + value;

        }

        try {
            //Cria objeto URL
            URL url = new URL(urlString);

            //Cria objeto httpurlconnection
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();

            //Method set
            connection.setRequestProperty("Request-Method", "GET");

            //Prepara a variável para ler o resultado
            connection.setDoInput(true);
            connection.setDoOutput(false);

            //Conecta a url destino
            connection.connect();

            //Abre a conexão pra input
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));

            //Lê
            StringBuffer newData = new StringBuffer();
            String s = "";
            while (null != ((s = br.readLine()))) {
                newData.append(s);
            }
            br.close();

            //Prepara o XML que está em string para executar leitura por nodes
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(newData.toString()));
            Document doc = db.parse(is);
            NodeList nodes = doc.getElementsByTagName("cServico");

            //Faz a leitura dos nodes
            for (int j = 0; j < nodes.getLength(); j++) {
                Element element = (Element) nodes.item(j);

                NodeList valor = element.getElementsByTagName("Valor");
                NodeList prazoEntrega = element.getElementsByTagName("PrazoEntrega");

                Element line = (Element) valor.item(0);
                Element prazo = (Element) prazoEntrega.item(0);

                response.setQtdeDias(getCharacterDataFromElement(prazo));
                response.setValor(getCharacterDataFromElement(line));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }

        box.setPrevisaoEntrega(LocalDate.now().plusDays(Long.parseLong(response.getQtdeDias())));
        box.setValorFrete(new BigDecimal(response.getValor().replace(",", ".")));
        return box;
    }

    public static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "";
    }


    public static EnderecoResponse searchAddress(String cep) {
        try {
            String url = "https://viacep.com.br/ws/" + cep + "/json/";
            RestTemplate restTemplate = new RestTemplate();
            EnderecoResponse enderecoResponse = restTemplate.getForObject(url, EnderecoResponse.class);

            if(enderecoResponse.getCep() == null)
                throw new NotFoundException(String.format("Address %s not found", cep));

            return enderecoResponse;
        } catch (HttpClientErrorException e) {
        throw e;
        }
    }


    public static ProductResponse searchProduct(ProductRequest productRequest) throws IOException {
        try {
            String url = "http://localhost:8081/api/v1/fresh-products/products/" + productRequest.getId();
            RestTemplate restTemplate = new RestTemplate();

            ProductResponse productResponse = restTemplate.getForObject(url, ProductResponse.class);


            if (productRequest.getQuantity() > productResponse.getQuantity())
                throw new BusinessException("Quantity is greater we have in our stock");


            Objects.requireNonNull(productResponse).getWarehouse().setEndereco(searchAddress(productResponse.getWarehouse().getCep()));
            productResponse.setQuantity(productRequest.getQuantity());

            return productResponse;
        } catch (HttpClientErrorException e) {
            throw e;
        }
    }
}
