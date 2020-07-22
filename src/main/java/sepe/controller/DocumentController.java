package sepe.controller;

import javafx.util.Pair;
import org.apache.commons.io.IOUtils;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sepe.config.CurrentUserControllerAdvice;
import sepe.config.SpringUserDetails;
import sepe.domain.general.SpPtlVwDocAppTypeEntity;
import sepe.dto.CompanyDTO;
import sepe.dto.UserDTO;
import sepe.repository.general.DocumentTypeRepository;
import sepe.service.DocumentStoreRest;
import sepe.service.DocumentStoredProcedures;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(propagation = Propagation.REQUIRES_NEW)
@RestController
public class DocumentController {

    @Autowired
    private DocumentStoredProcedures documentStoredProcedures;

    @Autowired
    private DocumentStoreRest documentStoreRest;


    @Autowired
    CurrentUserControllerAdvice currentAuthenticatedUser;

    @Autowired
    DocumentTypeRepository documentTypeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    //http://localhost:7001/getDocument?docId=4
    @RequestMapping(value = "/getDocument")
    public void getDocument(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "docId", defaultValue = "-1") Long docId) throws Exception {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        JSONObject outParams = null;
        SpringUserDetails springUserDetails = currentAuthenticatedUser.getCurrentUser();
        //todo add access control of authenticated user to docId

        // docId = -XXXXX when a contract or a contract termination needs to be viewed by the technician or exypp
        if (docId < -1)
        {
            docId = - docId;
            List techniciansPause;
            List exyppPause;
            techniciansPause = entityManager.createNativeQuery("select * from sp_ptl_comp_ta_ann a inner join sp_ptl_comp_ta_ann_ta_entry b on a.id = b.comp_ta_ann_id where b.technician_regrequest_user_id = "+springUserDetails.getUserId()+" and (a.attached_doc_id_pause = "+docId+" or a.attached_doc_id = "+docId+")").getResultList();
            exyppPause = entityManager.createNativeQuery("select * from sp_ptl_comp_ta_ann where exypp_basic = "+springUserDetails.getUserId()+" and (attached_doc_id_pause = "+docId+" or attached_doc_id = "+docId+")").getResultList();
            if (!techniciansPause.isEmpty() || !exyppPause.isEmpty())
                inputStream = documentStoreRest.getDocument(springUserDetails.getUserId(), docId);
        }

        List genRequestIds = entityManager.createNativeQuery("select * from SN_SP_RT_GEN_REQUEST where SP_GREQ_TEMPL_DOC_ID = " + docId + " OR SP_GREQ_HELP_DOC_ID = " + docId).getResultList();

        if (docId == 26444 || docId == 26394 || docId == 34354 || docId == 34689 || genRequestIds.size()> 0 || documentStoredProcedures.getDocumentV(springUserDetails.getUserId(), docId))
            inputStream = documentStoreRest.getDocument(springUserDetails.getUserId(), docId);
        outParams = documentStoreRest.getDocumentParams(springUserDetails.getUserId(), docId);
        if (inputStream == null || !outParams.has("fileName")) {
            response.setContentType("text/html");
            byte[] ba = "Document Not Found/Or Access Denied".getBytes();
            inputStream = new ByteArrayInputStream(ba);
        } else {
            String fileName = outParams.get("fileName").toString();
            String extension[] = fileName.split("\\.");
            String contentType = documentStoredProcedures.getContentType(extension[extension.length - 1]);
            response.setContentType(contentType);
            String composeFilename = java.net.URLEncoder.encode(fileName, "UTF-8");
            if (extension[extension.length - 1].equalsIgnoreCase("pdf")) {
                response.setHeader("Content-Disposition", "inline; filename=\"" + composeFilename + "\"");
            } else {
                response.setHeader("Content-Disposition", "attachment; filename=\"" + composeFilename + "\"");
            }
        }
        outputStream = response.getOutputStream();
        IOUtils.copy(inputStream, outputStream);
        IOUtils.closeQuietly(inputStream);
        IOUtils.closeQuietly(outputStream);
    }

/*    @RequestMapping(value = "/getDocumentMulti", method = RequestMethod.GET)
    public void getDocumentMulti(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "docId", defaultValue = "-1") Long docId) throws Exception {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        SpringUserDetails springUserDetails = currentAuthenticatedUser.getCurrentUser();
        //todo add access control of authenticated user to docId
        inputStream = documentStoredProcedures.getDocument(springUserDetails.getUserId(), docId);
        if (inputStream == null) {
            response.setContentType("text/html");
            byte[] ba = "Document Not Found/Or Access Denied".getBytes();
            inputStream = new ByteArrayInputStream(ba);
        } else {
            response.setContentType(response.getContentType());
        }
        outputStream = response.getOutputStream();
        IOUtils.copy(inputStream, outputStream);
        IOUtils.closeQuietly(inputStream);
        IOUtils.closeQuietly(outputStream);
    }*/

    @RequestMapping(value = "/setDocument", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object>
    setDocument(@RequestParam("file") MultipartFile file, @RequestParam(value = "docId", defaultValue = "-1") Long docId, HttpServletRequest request, HttpServletResponse response, @RequestHeader(value = "Accept") String accept) {
        try {
            if (accept.indexOf("application/json") != -1) {
                response.setContentType("application/json; charset=UTF-8");
            } else {
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.setHeader("Content-Type", "text/plain; charset=UTF-8");
            }
            InputStream inputStream = null;
            if (file.getSize() > 0 && file.getSize() < 25000000) {
                inputStream = file.getInputStream();
                SpringUserDetails springUserDetails = currentAuthenticatedUser.getCurrentUser();
                Long dID = documentStoreRest.setDocument(docId, file.getOriginalFilename(), IOUtils.toByteArray(inputStream));
                //if(docId<0) //Replace is not working -- Addidng new
                documentStoredProcedures.setDocument(springUserDetails != null ? springUserDetails.getUserId() : -1, dID);
                inputStream.close();
                Map<String, Object> jsonResult = new HashMap<String, Object>();
                if (dID < 0) {
                    jsonResult.put("success", Boolean.FALSE);
                    jsonResult.put("error", "Προέκυψε κάποιο σφάλμα κατά την εισαγωγή του αρχείου.");
                } else {
                    jsonResult.put("success", Boolean.TRUE);
                    jsonResult.put("fileId", dID.toString());
                }
                return jsonResult;
            } else {
                Map<String, Object> jsonResult = new HashMap<String, Object>();
                jsonResult.put("success", Boolean.FALSE);
                if (file.getSize() > 25000000) {
                    jsonResult.put("error", "Το μέγεθος του αρχείου υπερβαίνει το επιτρεπόμενο μέγεθος.");
                }
                if (file.getSize() == 0) {
                    jsonResult.put("error", "Το αρχείο είναι κενό.");
                }
                /*if (file.getContentType() != "application/pdf") {
                    jsonResult.put("error", "Το αρχείο πρέπει να είναι pdf.");
                }*/
                return jsonResult;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> jsonResult = new HashMap<String, Object>();
        jsonResult.put("success", Boolean.FALSE);
        jsonResult.put("fileId", "-1");
        return jsonResult;
    }

/*    @RequestMapping(value = "/setDocumentMulti", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object>
    setDocumentMulti(@RequestParam("file") MultipartFile file, @RequestParam(value = "docId", defaultValue = "-1") Long docId) {
        try {
            InputStream inputStream = null;
            if (file.getSize() > 0 && file.getSize() < 25000000 && (file.getContentType().equals("application/pdf")
                                                                    || file.getContentType().equals("application/msword")
                                                                    || file.getContentType().equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")
                                                                    || file.getContentType().equals("image/gif")
                                                                    || file.getContentType().equals("image/png")
                                                                    || file.getContentType().equals("image/jpeg")
                                                                    || file.getContentType().equals("application/vnd.ms-powerpointtd")
                                                                    || file.getContentType().equals("application/vnd.openxmlformats-officedocument.presentationml.presentation")
                                                                    || file.getContentType().equals("application/postscript")
                                                                    || file.getContentType().equals("text/plain")
                                                                    || file.getContentType().equals("application/vnd.ms-excel")
                                                                    || file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))) {
                inputStream = file.getInputStream();
                SpringUserDetails springUserDetails = currentAuthenticatedUser.getCurrentUser();

                Long dID = documentStoredProcedures.setDocument(springUserDetails.getUserId(), docId, IOUtils.toByteArray(inputStream));
                inputStream.close();
                Map<String, Object> jsonResult = new HashMap<String, Object>();
                jsonResult.put("success", Boolean.TRUE);
                jsonResult.put("fileId", dID.toString());
                return jsonResult;
            } else {
                Map<String, Object> jsonResult = new HashMap<String, Object>();
                jsonResult.put("success", Boolean.FALSE);
                if (file.getSize() > 25000000) {
                    jsonResult.put("error", "Το μέγεθος του αρχείου υπερβαίνει το επιτρεπόμενο μέγεθος.");
                }
                if (file.getSize() == 0) {
                    jsonResult.put("error", "Το αρχείο είναι κενό.");
                }
                if (file.getContentType().equals("application/pdf")
                        || file.getContentType().equals("application/msword")
                        || file.getContentType().equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")
                        || file.getContentType().equals("image/gif")
                        || file.getContentType().equals("image/png")
                        || file.getContentType().equals("image/jpeg")
                        || file.getContentType().equals("application/vnd.ms-powerpointtd")
                        || file.getContentType().equals("application/vnd.openxmlformats-officedocument.presentationml.presentation")
                        || file.getContentType().equals("application/postscript")
                        || file.getContentType().equals("text/plain")
                        || file.getContentType().equals("application/vnd.ms-excel")
                        || file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
                    jsonResult.put("error", "Το αρχείο πρέπει να είναι pdf, κείμενο, εικόνα, παρουσίαση ή υπολογιστικό φύλλο.");
                }
                return jsonResult;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> jsonResult = new HashMap<String, Object>();
        jsonResult.put("success", Boolean.FALSE);
        jsonResult.put("fileId", "-1");
        return jsonResult;
    }*/

    //http://localhost:7001/getDocumentType?docId=283
    @RequestMapping(value = "/getDocumentType", produces = "application/json")
    public Pair<Integer, Integer> getDocumentType(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "docId", defaultValue = "-1") Integer docId) throws Exception {
        SpringUserDetails springUserDetails = currentAuthenticatedUser.getCurrentUser();
        //todo add access control of authenticated user to docId
        SpPtlVwDocAppTypeEntity r = documentTypeRepository.findOne(docId);
        if (r == null)
            return new Pair<>(-1, -1);
        else
            return new Pair<>(r.getApplicationType(), r.getApplicationId());
    }

}