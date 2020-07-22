package sepe.webservicessepe;

import sepe.config.Constants;
import weblogic.security.SSL.TrustManager;
import weblogic.wsee.security.bst.ClientBSTCredentialProvider;
import weblogic.wsee.security.saml.SAMLTrustCredentialProvider;
import weblogic.wsee.security.unt.ClientUNTCredentialProvider;
import weblogic.wsee.security.util.CertUtils;
import weblogic.xml.crypto.wss.WSSecurityContext;
import weblogic.xml.crypto.wss.provider.CredentialProvider;

import java.io.File;
import java.security.cert.X509Certificate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceRef;


// !THE CHANGES MADE TO THIS FILE WILL BE DESTROYED IF REGENERATED!
// This source file is generated by Oracle tools
// Contents may be subject to change
// For reporting problems, use the following
// Version = Oracle WebServices (11.1.1.0.0, build 101221.1153.15811)

public class WebServicesMainMethodPortClient {
    @WebServiceRef
    private static WebServicesMainMethodService webServicesMainMethodService;

    public static String callIkaWS(String username, String password) {
        System.setProperty("https.protocols", "TLSv1,SSLv3,SSLv2Hello");
        //System.setProperty("javax.net.debug", "all");
        String employerId= "";
        try {
            webServicesMainMethodService = new WebServicesMainMethodService();
            WebServicesMainMethod webServicesMainMethod =
                webServicesMainMethodService.getWebServicesMainMethodPort();

            Map<String, Object> requestContext =
                ((BindingProvider)webServicesMainMethod).getRequestContext();
            setPortCredentialProviderList(requestContext);
            File f = new File(Constants.IKA_EWALLET_URL);
            File f2 = new File(Constants.IKA_SERVER_CERT_FILE);
            System.out.println("path: " + f.getAbsolutePath());
            System.out.println("path2: " + f2.getAbsolutePath());
            WebRequestAuthentication aa = new WebRequestAuthentication();
            aa.setPassword(password); // "ισμήνη"
            aa.setUserName(username); // "epoli"
            aa.setYpokUserName("000");

            WebResponseAuthentication bb =
                webServicesMainMethod.retrieveAuthentication(aa);
            System.out.println("EmployerId: " + bb.getEmployerId());
            System.out.println("ResultId: "+ bb.getResultId());
            System.out.println("ResultReason: "+ bb.getResultReason());
            // Add your code to call the desired methods.
            employerId = bb.getEmployerId();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return employerId;
    }

    @Generated("Oracle JDeveloper")
    public static void setPortCredentialProviderList(Map<String, Object> requestContext) throws Exception {
     
     
      String username = "users3p3";
      String password = "p@ssw3pe";
       String clientKeyStore = Constants.IKA_EWALLET_URL; //"C:\\dev\\ewallet.jks";  // \\home\\oracle\\modus\\ewallet.jks
       String clientKeyStorePassword = "manager1";
       String clientKeyAlias = "sepe";
       String clientKeyPassword = "manager1";
       String serverCertFile= Constants.IKA_SERVER_CERT_FILE; //"C:\\dev\\ika.cer"; // \\home\\oracle\\modus\\ika.cer
      
         
         List<CredentialProvider> credList = new ArrayList<CredentialProvider>();



        // Add the necessary credential providers to the list

        credList.add(getUNTCredentialProvider(username, password));

        credList.add(getBSTCredentialProvider(clientKeyStore,
                                              clientKeyStorePassword,
                                              clientKeyAlias,
                                              clientKeyPassword,
                                              serverCertFile, requestContext));

        credList.add(getSAMLTrustCredentialProvider());

        requestContext.put(WSSecurityContext.CREDENTIAL_PROVIDER_LIST,
                           credList);
    }

    @Generated("Oracle JDeveloper")
    public static CredentialProvider getSAMLTrustCredentialProvider() {
        return new SAMLTrustCredentialProvider();
    }

    @Generated("Oracle JDeveloper")
    public static CredentialProvider getUNTCredentialProvider(String username,
                                                              String password) {
        return new ClientUNTCredentialProvider(username.getBytes(),
                                               password.getBytes());
    }

    @Generated("Oracle JDeveloper")
    public static CredentialProvider getBSTCredentialProvider(String clientKeyStore,
                                                              String clientKeyStorePwd,
                                                              String clientKeyAlias,
                                                              String clientKeyPwd,
                                                              String serverCertFile,
                                                              Map<String, Object> requestContext) throws Exception {


        List clientCertList =
            CertUtils.getCertificate(clientKeyStore, clientKeyStorePwd,
                                     clientKeyAlias, "JKS");

        final X509Certificate serverCert =
            (X509Certificate) CertUtils.getCertificate(serverCertFile);
        final X509Certificate clientCert =
            (clientCertList != null && clientCertList.size() > 0) ?
            (X509Certificate)clientCertList.get(0) : null;

        requestContext.put(WSSecurityContext.TRUST_MANAGER,
                           new TrustManager() {
                public boolean certificateCallback(X509Certificate[] chain,
                                                   int validateErr) {
                    boolean result =
                        (chain != null && chain.length > 0) && (chain[0].equals(serverCert) ||
                                                                chain[0].equals(clientCert));
                    return result;
                }
            });

        return new ClientBSTCredentialProvider(clientKeyStore,
                                               clientKeyStorePwd,
                                               clientKeyAlias, clientKeyPwd,
                                               "JKS", serverCert);
    }
}