package sepe.service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.multipart.BodyPart;
import com.sun.jersey.multipart.BodyPartEntity;
import com.sun.jersey.multipart.MultiPart;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sepe.config.Constants;

import javax.ws.rs.core.MediaType;
import java.io.InputStream;
import java.util.Iterator;


@Service
public class DocumentStoreRest {


    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentStoreRest.class);

    public DocumentStoreRest() {
    }

    public Long setDocument(Long docId, String fileName, byte[] data) throws JSONException {
        String urlString = Constants.PAPYROS_FILE_UPLOAD_URI;

        JSONObject inParams = new JSONObject();
        /* //Replace not working -- Adding new
        if(docId>0) {
            inParams.put("DOC_ID", docId);
        }
        else*/
        inParams.put("INSERT_DOC", " 1");
        inParams.put("FILENAME", fileName);
        inParams.put("USER_ID", 1000000);
        inParams.put("PASSWORD", "sepeWS");

        final ClientConfig config = new DefaultClientConfig();
        final Client client = Client.create(config);
        final WebResource resource = client.resource(urlString);
        MultiPart multiPart = new MultiPart();
        multiPart.bodyPart(new BodyPart(inParams.toString(), MediaType.TEXT_PLAIN_TYPE));
        multiPart.bodyPart(data, MediaType.MULTIPART_FORM_DATA_TYPE);

        // Construct a MultiPart
        // POST the request
        JSONObject response = new JSONObject();
        MultiPart response2 = new MultiPart();
        Long responseId = new Long(-1);
        final ClientResponse clientResp = resource.type("multipart/form-data").post(ClientResponse.class, multiPart);
        /* //Replace not working -- Adding new
        if(docId>0) {
            //response2 = clientResp.getEntity(MultiPart.class);
            responseId = docId;
        }
        else{*/
        response = clientResp.getEntity(JSONObject.class);
        responseId = response.getLong("DocID");
        //}
        client.destroy();

        return responseId;
    }

    public InputStream getDocument(Long userId, Long docId) throws JSONException {

        String urlString = Constants.PAPYROS_FILE_UPLOAD_URI;

        JSONObject inParams = new JSONObject();
        inParams.put("DOC_ID", docId);
        inParams.put("USER_ID", 1000000);
        inParams.put("PASSWORD", "sepeWS");


        final ClientConfig config = new DefaultClientConfig();
        final Client client = Client.create(config);
        final WebResource resource = client.resource(urlString);
        // Construct a MultiPart
        MultiPart multiPart = new MultiPart();
        multiPart.bodyPart(new BodyPart(inParams.toString(), javax.ws.rs.core.MediaType.TEXT_PLAIN_TYPE));

        // POST the request
        final ClientResponse clientResp = resource.type("multipart/form-data").post(ClientResponse.class, multiPart);

        // Get the response
        multiPart = clientResp.getEntity(MultiPart.class);

        // Create iterator for multipart parts
        Iterator itr = multiPart.getBodyParts().iterator();

        // First part contains a JSONObject with the OUT parameters
        JSONObject outParams = null;
        if (itr.hasNext()) {
            String responseJSON = multiPart.getBodyParts().get(1).getEntityAs(String.class);
            outParams = new JSONObject(responseJSON);
            itr.next();
        }
        client.destroy();
        if (outParams.has("error")) {
            return null;
        } else {
            // Second part contains the file as InputStream
            if (itr.hasNext()) {
                BodyPartEntity bpe = (BodyPartEntity) multiPart.getBodyParts().get(2).getEntity();
                InputStream uploadedInputStream = bpe.getInputStream();

                return uploadedInputStream;
            }
        }

        return null;
    }

    public JSONObject getDocumentParams(Long userId, Long docId) throws JSONException {

        String urlString = Constants.PAPYROS_FILE_UPLOAD_URI;

        JSONObject inParams = new JSONObject();
        inParams.put("DOC_ID", docId);
        inParams.put("USER_ID", 1000000);
        inParams.put("PASSWORD", "sepeWS");


        final ClientConfig config = new DefaultClientConfig();
        final Client client = Client.create(config);
        final WebResource resource = client.resource(urlString);
        // Construct a MultiPart
        MultiPart multiPart = new MultiPart();
        multiPart.bodyPart(new BodyPart(inParams.toString(), javax.ws.rs.core.MediaType.TEXT_PLAIN_TYPE));

        // POST the request
        final ClientResponse clientResp = resource.type("multipart/form-data").post(ClientResponse.class, multiPart);

        // Get the response
        multiPart = clientResp.getEntity(MultiPart.class);

        // Create iterator for multipart parts
        Iterator itr = multiPart.getBodyParts().iterator();

        // First part contains a JSONObject with the OUT parameters
        JSONObject outParams = null;
        if (itr.hasNext()) {
            String responseJSON = multiPart.getBodyParts().get(1).getEntityAs(String.class);
            outParams = new JSONObject(responseJSON);
            itr.next();
        }
        client.destroy();

        return outParams;
    }


    public Long versionDocument(Long docId, String protNum, String fileName, byte[] data) throws JSONException {
        String urlString = Constants.PAPYROS_FILE_UPLOAD_URI;

        JSONObject inParams = new JSONObject();
        /* //Replace not working -- Adding new
        if(docId>0) {
            inParams.put("DOC_ID", docId);
        }
        else*/
        inParams.put("DOC_ID", docId);
        inParams.put("PROT_NUM", protNum);
        inParams.put("FILENAME", fileName);
        inParams.put("USER_ID", 1000000);
        inParams.put("PASSWORD", "sepeWS");

        final ClientConfig config = new DefaultClientConfig();
        final Client client = Client.create(config);
        final WebResource resource = client.resource(urlString);
        MultiPart multiPart = new MultiPart();
        multiPart.bodyPart(new BodyPart(inParams.toString(), MediaType.TEXT_PLAIN_TYPE));
        multiPart.bodyPart(data, MediaType.MULTIPART_FORM_DATA_TYPE);

        // Construct a MultiPart
        // POST the request
        JSONObject response = new JSONObject();
        MultiPart response2 = new MultiPart();
        Long responseId = new Long(-1);
        final ClientResponse clientResp = resource.type("multipart/form-data").post(ClientResponse.class, multiPart);
        /* //Replace not working -- Adding new
        if(docId>0) {
            //response2 = clientResp.getEntity(MultiPart.class);
            responseId = docId;
        }
        else{*/
        response = clientResp.getEntity(JSONObject.class);
        responseId = response.getLong("DocID");
        //}
        client.destroy();

        return responseId;
    }


}

//RestTemplate no full support test
//setdocument
/*final String uri = "http://172.18.15.11:5003/GENERAL_WS/rest/ws1/submitDocument";

        RestTemplate restTemplate = new RestTemplate();

        JsonObject inParams = Json.createObjectBuilder()
                .add("INSERT_DOC", "1")
                .add("FILENAME", "SEPE_file.pdf")
                .add("USER_ID", 1000000)
                .add("PASSWORD", "sepeWS")
                .build();

        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
        parts.add("info", inParams.toString());
        parts.add("file", new ByteArrayResource(data));
        HttpHeaders headers = new HttpHeaders();
        //headers.setAccept(Collections.singletonList(new MediaType("application", "json")));
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(parts, headers);
        //ResponseEntity<SetDocumentResponseObj> response = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, SetDocumentResponseObj.class);
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, String.class);

        JsonReader jsonReader = Json.createReader(new StringReader(response.getBody()));
        JsonObject resp = jsonReader.readObject();
        jsonReader.close();

        return new Long(resp.getInt("DocID"));
       // return (new Long (15));*/

//get document
/*RestTemplate restTemplate = new RestTemplate();
MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
parts.add("info", inParams.toString());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);


        Charset charset1 = Charset.forName("UTF-8");
        List<Charset> charsets = new ArrayList<Charset>(1);
        charsets.add(charset1);
        headers.setAcceptCharset(charsets);
        //headers.setAcceptCharset();

        FormHttpMessageConverter formConverter = new FormHttpMessageConverter();
        *//*FormHttpMessageConverter formConverter = new FormHttpMessageConverter() {
            @Override
            public boolean canRead(Class<?> clazz, MediaType mediaType) {
                if (clazz == MyMultiValueMap.class) {
                    return true;
                }
                return super.canRead(clazz, mediaType);
            }
        };
*//*
        //MediaType mediaType = new MediaType("application","x-www-form-urlencoded", Charset.forName("ISO-8859-1"));
        MediaType mediaType = new MediaType("multipart","mixed", Charset.forName("UTF-8"));
        formConverter.setSupportedMediaTypes(Arrays.asList(mediaType));
        formConverter.addPartConverter(new ResourceHttpMessageConverter());
        formConverter.addPartConverter(new MappingJackson2HttpMessageConverter());
        formConverter.addPartConverter(new StringHttpMessageConverter());

        restTemplate.getMessageConverters().add(formConverter);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(parts, headers);*/
//ResponseEntity<MultiPart> response2 = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, MultiPart.class);

        /*int inputStart = response2.getBody().indexOf("%PDF");
        String pdf = response2.getBody().substring(inputStart);
        String pdf2 = pdf.substring(0, pdf.indexOf("--Boundary")-2);
        System.out.println(response2.getBody());
        InputStream stream = new ByteArrayInputStream(pdf2.getBytes());*/
//System.out.println(response2.getBody());
//MyMultiValueMap map = restTemplate.postForObject(urlString, requestEntity, MyMultiValueMap.class);
//new LinkedMultiValueMap<String, Object>(map);
//ResponseEntity<MyMultiValueMap> response = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, MyMultiValueMap.class);
//restTemplate.postForObject(urlString, requestEntity, );
//Multipart<String,Object> body= response.getBody();
//response.getBody();
