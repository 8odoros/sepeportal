package sepe.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sepe.domain.general.Document;
import sepe.repository.DocumentRepository;

import sepe.domain.general.DocumentV;//new!
import sepe.repository.DocumentVRepository;//new!

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;


@Service
public class DocumentStoredProcedures {
    @Autowired
    EntityManager em;

    @Autowired
    DocumentRepository dr;

    @Autowired
    DocumentVRepository drV;

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentStoredProcedures.class);

    public DocumentStoredProcedures() {
    }

    /*public InputStream getDocument(Long userId, Long docId) throws SQLException {
        StoredProcedureQuery proc = em.createStoredProcedureQuery("GETDATAFILE");
        proc.registerStoredProcedureParameter("USER_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("DOCUMENT_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("DOCUMENT_FILE", BLOB.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("out_errMsg", String.class, ParameterMode.OUT);

        proc.setParameter("USER_ID", userId);
        proc.setParameter("DOCUMENT_ID", docId);


        proc.execute();
        BLOB pdf = null;
        String out_errMsg = (String) proc.getOutputParameterValue("out_errMsg");
        if (out_errMsg == null || out_errMsg.isEmpty()) {
            pdf = (BLOB) proc.getOutputParameterValue("DOCUMENT_FILE");
            return pdf.getBinaryStream();
        } else {//handle error
            return null;
        }
    }*/

   /* public Long setDocument(Long userId, Long docId, byte[] data) throws SQLException {
        StoredProcedureQuery proc = em.createStoredProcedureQuery("SETDATAFILE");
        proc.registerStoredProcedureParameter("USER_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("DOCUMENT_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("DOCUMENT_FILE", BLOB.class, ParameterMode.IN)
                .registerStoredProcedureParameter("DOCUMENT_ID_OUT", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("out_errMsg", String.class, ParameterMode.OUT);

        proc.setParameter("USER_ID", userId);
        proc.setParameter("DOCUMENT_ID", docId);
        Blob blob = new SerialBlob(data);
        proc.setParameter("DOCUMENT_FILE", blob);
        proc.execute();
        Long did = Long.valueOf(-1);
        String out_errMsg = (String) proc.getOutputParameterValue("out_errMsg");
        if (out_errMsg == null || out_errMsg.isEmpty()) {
            did = (long) proc.getOutputParameterValue("DOCUMENT_ID_OUT");
            return did;
        } else {//handle error
            return (long) -1;
        }
    }*/
   public Long setDocument(Long userId, Long dId) throws SQLException {
        Document d=new Document();
        d.setUserId(userId);
        d.setDocId(dId);
        d=dr.save(d);
        return d.getDocumentId();
   }

    public Boolean getDocument(Long userId, Long dId) throws SQLException {
        List<Document> d;
        d=dr.findByDocId(dId);
        /*if(d.size()>0 && d.get(0).getUserId().equals(userId))
            return true;
        else
            return false;*/
        if(d.size()>0)
            for (Document record : d)
                if (record.getUserId().equals(userId)) return true;
        return false;
        //return new ByteArrayInputStream(d.getDatafile());
    }

    public Boolean getDocumentV(Long userId, Long dId) throws SQLException {
        List<DocumentV> d;
        d=drV.findByDocId(dId);
        /*if(d.size()>0 && d.get(0).getUserId().equals(userId))
            return true;
        else
            return false;*/
        if(d.size()>0)
            for (DocumentV record : d)
                if (record.getUserId().equals(userId)) return true;
        return false;
        //return new ByteArrayInputStream(d.getDatafile());
    }

    public String getContentType(String extension)
    {
        if (extension.equalsIgnoreCase("323"))
            return "text/h323";
        else if (extension.equalsIgnoreCase("aaf"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("aca"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("acx"))
            return "application/internet-property-stream";
        else if (extension.equalsIgnoreCase("afm"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("ai"))
            return "application/postscript";
        else if (extension.equalsIgnoreCase("aif"))
            return "audio/x-aiff";
        else if (extension.equalsIgnoreCase("aifc"))
            return "audio/aiff";
        else if (extension.equalsIgnoreCase("aiff"))
            return "audio/aiff";
        else if (extension.equalsIgnoreCase("art"))
            return "image/x-jg";
        else if (extension.equalsIgnoreCase("asd"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("asf"))
            return "video/x-ms-asf";
        else if (extension.equalsIgnoreCase("asi"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("asm"))
            return "text/plain";
        else if (extension.equalsIgnoreCase("asr"))
            return "video/x-ms-asf";
        else if (extension.equalsIgnoreCase("asx"))
            return "video/x-ms-asf";
        else if (extension.equalsIgnoreCase("au"))
            return "audio/basic";
        else if (extension.equalsIgnoreCase("avi"))
            return "video/x-msvideo";
        else if (extension.equalsIgnoreCase("axs"))
            return "application/olescript";
        else if (extension.equalsIgnoreCase("bas"))
            return "text/plain";
        else if (extension.equalsIgnoreCase("bcpio"))
            return "application/x-bcpio";
        else if (extension.equalsIgnoreCase("bin"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("bmp"))
            return "image/bmp";
        else if (extension.equalsIgnoreCase("c"))
            return "text/plain";
        else if (extension.equalsIgnoreCase("cab"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("cat"))
            return "application/vnd.ms-pki.seccat";
        else if (extension.equalsIgnoreCase("cdf"))
            return "application/x-cdf";
        else if (extension.equalsIgnoreCase("chm"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("class"))
            return "application/x-java-applet";
        else if (extension.equalsIgnoreCase("clp"))
            return "application/x-msclip";
        else if (extension.equalsIgnoreCase("cmx"))
            return "image/x-cmx";
        else if (extension.equalsIgnoreCase("cnf"))
            return "text/plain";
        else if (extension.equalsIgnoreCase("cod"))
            return "image/cis-cod";
        else if (extension.equalsIgnoreCase("cpio"))
            return "application/x-cpio";
        else if (extension.equalsIgnoreCase("cpp"))
            return "text/plain";
        else if (extension.equalsIgnoreCase("crd"))
            return "application/x-mscardfile";
        else if (extension.equalsIgnoreCase("crl"))
            return "application/pkix-crl";
        else if (extension.equalsIgnoreCase("crt"))
            return "application/x-x509-ca-cert";
        else if (extension.equalsIgnoreCase("csh"))
            return "application/x-csh";
        else if (extension.equalsIgnoreCase("css"))
            return "text/css";
        else if (extension.equalsIgnoreCase("csv"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("cur"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("dcr"))
            return "application/x-director";
        else if (extension.equalsIgnoreCase("der"))
            return "application/x-x509-ca-cert";
        else if (extension.equalsIgnoreCase("dib"))
            return "image/bmp";
        else if (extension.equalsIgnoreCase("dir"))
            return "application/x-director";
        else if (extension.equalsIgnoreCase("disco"))
            return "text/xml";
        else if (extension.equalsIgnoreCase("dll"))
            return "application/x-msdownload";
        else if (extension.equalsIgnoreCase("dlm"))
            return "text/dlm";
        else if (extension.equalsIgnoreCase("doc"))
            return "application/msword";
        else if (extension.equalsIgnoreCase("dot"))
            return "application/msword";
        else if (extension.equalsIgnoreCase("dsp"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("dtd"))
            return "text/xml";
        else if (extension.equalsIgnoreCase("dvi"))
            return "application/x-dvi";
        else if (extension.equalsIgnoreCase("dwf"))
            return "drawing/x-dwf";
        else if (extension.equalsIgnoreCase("dwp"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("dxr"))
            return "application/x-director";
        else if (extension.equalsIgnoreCase("eml"))
            // return "application/octet-stream";
            // return "multipart/mixed";
            return "message/rfc822";
        else if (extension.equalsIgnoreCase("emz"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("eot"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("eps"))
            return "application/postscript";
        else if (extension.equalsIgnoreCase("etx"))
            return "text/x-setext";
        else if (extension.equalsIgnoreCase("evy"))
            return "application/envoy";
        else if (extension.equalsIgnoreCase("exe"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("fdf"))
            return "application/vnd.fdf";
        else if (extension.equalsIgnoreCase("fif"))
            return "application/fractals";
        else if (extension.equalsIgnoreCase("fla"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("flr"))
            return "x-world/x-vrml";
        else if (extension.equalsIgnoreCase("gif"))
            return "image/gif";
        else if (extension.equalsIgnoreCase("gtar"))
            return "application/x-gtar";
        else if (extension.equalsIgnoreCase("gz"))
            return "application/x-gzip";
        else if (extension.equalsIgnoreCase("h"))
            return "text/plain";
        else if (extension.equalsIgnoreCase("hdf"))
            return "application/x-hdf";
        else if (extension.equalsIgnoreCase("hdml"))
            return "text/x-hdml";
        else if (extension.equalsIgnoreCase("hhc"))
            return "application/x-oleobject";
        else if (extension.equalsIgnoreCase("hhk"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("hhp"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("hlp"))
            return "application/winhlp";
        else if (extension.equalsIgnoreCase("hqx"))
            return "application/mac-binhex40";
        else if (extension.equalsIgnoreCase("hta"))
            return "application/hta";
        else if (extension.equalsIgnoreCase("htc"))
            return "text/x-component";
        else if (extension.equalsIgnoreCase("htm"))
            return "text/html";
        else if (extension.equalsIgnoreCase("html"))
            return "text/html";
        else if (extension.equalsIgnoreCase("htt"))
            return "text/webviewhtml";
        else if (extension.equalsIgnoreCase("hxt"))
            return "text/html";
        else if (extension.equalsIgnoreCase("ico"))
            return "image/x-icon";
        else if (extension.equalsIgnoreCase("ics"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("ief"))
            return "image/ief";
        else if (extension.equalsIgnoreCase("iii"))
            return "application/x-iphone";
        else if (extension.equalsIgnoreCase("inf"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("ins"))
            return "application/x-internet-signup";
        else if (extension.equalsIgnoreCase("isp"))
            return "application/x-internet-signup";
        else if (extension.equalsIgnoreCase("IVF"))
            return "video/x-ivf";
        else if (extension.equalsIgnoreCase("jar"))
            return "application/java-archive";
        else if (extension.equalsIgnoreCase("java"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("jck"))
            return "application/liquidmotion";
        else if (extension.equalsIgnoreCase("jcz"))
            return "application/liquidmotion";
        else if (extension.equalsIgnoreCase("jfif"))
            return "image/pjpeg";
        else if (extension.equalsIgnoreCase("jpb"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("jpe"))
            return "image/jpeg";
        else if (extension.equalsIgnoreCase("jpeg"))
            return "image/jpeg";
        else if (extension.equalsIgnoreCase("jpg"))
            return "image/jpeg";
        else if (extension.equalsIgnoreCase("js"))
            return "application/x-javascript";
        else if (extension.equalsIgnoreCase("latex"))
            return "application/x-latex";
        else if (extension.equalsIgnoreCase("lit"))
            return "application/x-ms-reader";
        else if (extension.equalsIgnoreCase("lpk"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("lsf"))
            return "video/x-la-asf";
        else if (extension.equalsIgnoreCase("lsx"))
            return "video/x-la-asf";
        else if (extension.equalsIgnoreCase("lzh"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("m13"))
            return "application/x-msmediaview";
        else if (extension.equalsIgnoreCase("m14"))
            return "application/x-msmediaview";
        else if (extension.equalsIgnoreCase("m1v"))
            return "video/mpeg";
        else if (extension.equalsIgnoreCase("m3u"))
            return "audio/x-mpegurl";
        else if (extension.equalsIgnoreCase("man"))
            return "application/x-troff-man";
        else if (extension.equalsIgnoreCase("map"))
            return "text/plain";
        else if (extension.equalsIgnoreCase("mdb"))
            return "application/x-msaccess";
        else if (extension.equalsIgnoreCase("mdp"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("me"))
            return "application/x-troff-me";
        else if (extension.equalsIgnoreCase("mht"))
            return "message/rfc822";
        else if (extension.equalsIgnoreCase("mhtml"))
            return "message/rfc822";
        else if (extension.equalsIgnoreCase("mid"))
            return "audio/mid";
        else if (extension.equalsIgnoreCase("midi"))
            return "audio/mid";
        else if (extension.equalsIgnoreCase("mix"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("mmf"))
            return "application/x-smaf";
        else if (extension.equalsIgnoreCase("mno"))
            return "text/xml";
        else if (extension.equalsIgnoreCase("mny"))
            return "application/x-msmoney";
        else if (extension.equalsIgnoreCase("mov"))
            return "video/quicktime";
        else if (extension.equalsIgnoreCase("movie"))
            return "video/x-sgi-movie";
        else if (extension.equalsIgnoreCase("mp2"))
            return "video/mpeg";
        else if (extension.equalsIgnoreCase("mp3"))
            return "audio/mpeg";
        else if (extension.equalsIgnoreCase("mpa"))
            return "video/mpeg";
        else if (extension.equalsIgnoreCase("mpe"))
            return "video/mpeg";
        else if (extension.equalsIgnoreCase("mpeg"))
            return "video/mpeg";
        else if (extension.equalsIgnoreCase("mpg"))
            return "video/mpeg";
        else if (extension.equalsIgnoreCase("mpp"))
            return "application/vnd.ms-project";
        else if (extension.equalsIgnoreCase("mpv2"))
            return "video/mpeg";
        else if (extension.equalsIgnoreCase("ms"))
            return "application/x-troff-ms";
        else if (extension.equalsIgnoreCase("msg"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("msi"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("mvb"))
            return "application/x-msmediaview";
        else if (extension.equalsIgnoreCase("nc"))
            return "application/x-netcdf";
        else if (extension.equalsIgnoreCase("nsc"))
            return "video/x-ms-asf";
        else if (extension.equalsIgnoreCase("nws"))
            return "message/rfc822";
        else if (extension.equalsIgnoreCase("ocx"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("oda"))
            return "application/oda";
        else if (extension.equalsIgnoreCase("ods"))
            return "application/oleobject";
        else if (extension.equalsIgnoreCase("p10"))
            return "application/pkcs10";
        else if (extension.equalsIgnoreCase("p12"))
            return "application/x-pkcs12";
        else if (extension.equalsIgnoreCase("p7b"))
            return "application/x-pkcs7-certificates";
        else if (extension.equalsIgnoreCase("p7c"))
            return "application/pkcs7-mime";
        else if (extension.equalsIgnoreCase("p7m"))
            return "application/pkcs7-mime";
        else if (extension.equalsIgnoreCase("p7r"))
            return "application/x-pkcs7-certreqresp";
        else if (extension.equalsIgnoreCase("p7s"))
            return "application/pkcs7-signature";
        else if (extension.equalsIgnoreCase("pbm"))
            return "image/x-portable-bitmap";
        else if (extension.equalsIgnoreCase("pcx"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("pcz"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("pdf"))
            return "application/pdf";
        else if (extension.equalsIgnoreCase("pfb"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("pfm"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("pfx"))
            return "application/x-pkcs12";
        else if (extension.equalsIgnoreCase("pgm"))
            return "image/x-portable-graymap";
        else if (extension.equalsIgnoreCase("pko"))
            return "application/vnd.ms-pki.pko";
        else if (extension.equalsIgnoreCase("pma"))
            return "application/x-perfmon";
        else if (extension.equalsIgnoreCase("pmc"))
            return "application/x-perfmon";
        else if (extension.equalsIgnoreCase("pml"))
            return "application/x-perfmon";
        else if (extension.equalsIgnoreCase("pmr"))
            return "application/x-perfmon";
        else if (extension.equalsIgnoreCase("pmw"))
            return "application/x-perfmon";
        else if (extension.equalsIgnoreCase("png"))
            return "image/png";
        else if (extension.equalsIgnoreCase("pnm"))
            return "image/x-portable-anymap";
        else if (extension.equalsIgnoreCase("pnz"))
            return "image/png";
        else if (extension.equalsIgnoreCase("pot"))
            return "application/vnd.ms-powerpoint";
        else if (extension.equalsIgnoreCase("ppm"))
            return "image/x-portable-pixmap";
        else if (extension.equalsIgnoreCase("pps"))
            return "application/vnd.ms-powerpoint";
        else if (extension.equalsIgnoreCase("ppt"))
            return "application/vnd.ms-powerpoint";
        else if (extension.equalsIgnoreCase("prf"))
            return "application/pics-rules";
        else if (extension.equalsIgnoreCase("prm"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("prx"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("ps"))
            return "application/postscript";
        else if (extension.equalsIgnoreCase("psd"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("psm"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("psp"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("pub"))
            return "application/x-mspublisher";
        else if (extension.equalsIgnoreCase("qt"))
            return "video/quicktime";
        else if (extension.equalsIgnoreCase("qtl"))
            return "application/x-quicktimeplayer";
        else if (extension.equalsIgnoreCase("qxd"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("ra"))
            return "audio/x-pn-realaudio";
        else if (extension.equalsIgnoreCase("ram"))
            return "audio/x-pn-realaudio";
        else if (extension.equalsIgnoreCase("rar"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("ras"))
            return "image/x-cmu-raster";
        else if (extension.equalsIgnoreCase("rf"))
            return "image/vnd.rn-realflash";
        else if (extension.equalsIgnoreCase("rgb"))
            return "image/x-rgb";
        else if (extension.equalsIgnoreCase("rm"))
            return "application/vnd.rn-realmedia";
        else if (extension.equalsIgnoreCase("rmi"))
            return "audio/mid";
        else if (extension.equalsIgnoreCase("roff"))
            return "application/x-troff";
        else if (extension.equalsIgnoreCase("rpm"))
            return "audio/x-pn-realaudio-plugin";
        else if (extension.equalsIgnoreCase("rtf"))
            return "application/rtf";
        else if (extension.equalsIgnoreCase("rtx"))
            return "text/richtext";
        else if (extension.equalsIgnoreCase("scd"))
            return "application/x-msschedule";
        else if (extension.equalsIgnoreCase("sct"))
            return "text/scriptlet";
        else if (extension.equalsIgnoreCase("sea"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("setpay"))
            return "application/set-payment-initiation";
        else if (extension.equalsIgnoreCase("setreg"))
            return "application/set-registration-initiation";
        else if (extension.equalsIgnoreCase("sgml"))
            return "text/sgml";
        else if (extension.equalsIgnoreCase("sh"))
            return "application/x-sh";
        else if (extension.equalsIgnoreCase("shar"))
            return "application/x-shar";
        else if (extension.equalsIgnoreCase("sit"))
            return "application/x-stuffit";
        else if (extension.equalsIgnoreCase("smd"))
            return "audio/x-smd";
        else if (extension.equalsIgnoreCase("smi"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("smx"))
            return "audio/x-smd";
        else if (extension.equalsIgnoreCase("smz"))
            return "audio/x-smd";
        else if (extension.equalsIgnoreCase("snd"))
            return "audio/basic";
        else if (extension.equalsIgnoreCase("snp"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("spc"))
            return "application/x-pkcs7-certificates";
        else if (extension.equalsIgnoreCase("spl"))
            return "application/futuresplash";
        else if (extension.equalsIgnoreCase("src"))
            return "application/x-wais-source";
        else if (extension.equalsIgnoreCase("ssm"))
            return "application/streamingmedia";
        else if (extension.equalsIgnoreCase("sst"))
            return "application/vnd.ms-pki.certstore";
        else if (extension.equalsIgnoreCase("stl"))
            return "application/vnd.ms-pki.stl";
        else if (extension.equalsIgnoreCase("sv4cpio"))
            return "application/x-sv4cpio";
        else if (extension.equalsIgnoreCase("sv4crc"))
            return "application/x-sv4crc";
        else if (extension.equalsIgnoreCase("swf"))
            return "application/x-shockwave-flash";
        else if (extension.equalsIgnoreCase("t"))
            return "application/x-troff";
        else if (extension.equalsIgnoreCase("tar"))
            return "application/x-tar";
        else if (extension.equalsIgnoreCase("tcl"))
            return "application/x-tcl";
        else if (extension.equalsIgnoreCase("tex"))
            return "application/x-tex";
        else if (extension.equalsIgnoreCase("texi"))
            return "application/x-texinfo";
        else if (extension.equalsIgnoreCase("texinfo"))
            return "application/x-texinfo";
        else if (extension.equalsIgnoreCase("tgz"))
            return "application/x-compressed";
        else if (extension.equalsIgnoreCase("thn"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("tif"))
            return "image/tiff";
        else if (extension.equalsIgnoreCase("tiff"))
            return "image/tiff";
        else if (extension.equalsIgnoreCase("toc"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("tr"))
            return "application/x-troff";
        else if (extension.equalsIgnoreCase("trm"))
            return "application/x-msterminal";
        else if (extension.equalsIgnoreCase("tsv"))
            return "text/tab-separated-values";
        else if (extension.equalsIgnoreCase("ttf"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("txt"))
            return "text/plain";
        else if (extension.equalsIgnoreCase("u32"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("uls"))
            return "text/iuls";
        else if (extension.equalsIgnoreCase("ustar"))
            return "application/x-ustar";
        else if (extension.equalsIgnoreCase("vbs"))
            return "text/vbscript";
        else if (extension.equalsIgnoreCase("vcf"))
            return "text/x-vcard";
        else if (extension.equalsIgnoreCase("vcs"))
            return "text/plain";
        else if (extension.equalsIgnoreCase("vdx"))
            return "application/vnd.visio";
        else if (extension.equalsIgnoreCase("vml"))
            return "text/xml";
        else if (extension.equalsIgnoreCase("vsd"))
            return "application/vnd.visio";
        else if (extension.equalsIgnoreCase("vss"))
            return "application/vnd.visio";
        else if (extension.equalsIgnoreCase("vst"))
            return "application/vnd.visio";
        else if (extension.equalsIgnoreCase("vsw"))
            return "application/vnd.visio";
        else if (extension.equalsIgnoreCase("vsx"))
            return "application/vnd.visio";
        else if (extension.equalsIgnoreCase("vtx"))
            return "application/vnd.visio";
        else if (extension.equalsIgnoreCase("wav"))
            return "audio/wav";
        else if (extension.equalsIgnoreCase("wax"))
            return "audio/x-ms-wax";
        else if (extension.equalsIgnoreCase("wbmp"))
            return "image/vnd.wap.wbmp";
        else if (extension.equalsIgnoreCase("wcm"))
            return "application/vnd.ms-works";
        else if (extension.equalsIgnoreCase("wdb"))
            return "application/vnd.ms-works";
        else if (extension.equalsIgnoreCase("wks"))
            return "application/vnd.ms-works";
        else if (extension.equalsIgnoreCase("wm"))
            return "video/x-ms-wm";
        else if (extension.equalsIgnoreCase("wma"))
            return "audio/x-ms-wma";
        else if (extension.equalsIgnoreCase("wmd"))
            return "application/x-ms-wmd";
        else if (extension.equalsIgnoreCase("wmf"))
            return "application/x-msmetafile";
        else if (extension.equalsIgnoreCase("wml"))
            return "text/vnd.wap.wml";
        else if (extension.equalsIgnoreCase("wmlc"))
            return "application/vnd.wap.wmlc";
        else if (extension.equalsIgnoreCase("wmls"))
            return "text/vnd.wap.wmlscript";
        else if (extension.equalsIgnoreCase("wmlsc"))
            return "application/vnd.wap.wmlscriptc";
        else if (extension.equalsIgnoreCase("wmp"))
            return "video/x-ms-wmp";
        else if (extension.equalsIgnoreCase("wmv"))
            return "video/x-ms-wmv";
        else if (extension.equalsIgnoreCase("wmx"))
            return "video/x-ms-wmx";
        else if (extension.equalsIgnoreCase("wmz"))
            return "application/x-ms-wmz";
        else if (extension.equalsIgnoreCase("wps"))
            return "application/vnd.ms-works";
        else if (extension.equalsIgnoreCase("wri"))
            return "application/x-mswrite";
        else if (extension.equalsIgnoreCase("wrl"))
            return "x-world/x-vrml";
        else if (extension.equalsIgnoreCase("wrz"))
            return "x-world/x-vrml";
        else if (extension.equalsIgnoreCase("wsdl"))
            return "text/xml";
        else if (extension.equalsIgnoreCase("wvx"))
            return "video/x-ms-wvx";
        else if (extension.equalsIgnoreCase("x"))
            return "application/directx";
        else if (extension.equalsIgnoreCase("xaf"))
            return "x-world/x-vrml";
        else if (extension.equalsIgnoreCase("xbm"))
            return "image/x-xbitmap";
        else if (extension.equalsIgnoreCase("xdr"))
            return "text/plain";
        else if (extension.equalsIgnoreCase("xla"))
            return "application/vnd.ms-excel";
        else if (extension.equalsIgnoreCase("xlc"))
            return "application/vnd.ms-excel";
        else if (extension.equalsIgnoreCase("xlm"))
            return "application/vnd.ms-excel";
        else if (extension.equalsIgnoreCase("xls"))
            return "application/vnd.ms-excel";
        else if (extension.equalsIgnoreCase("xlsx"))
            return "application/vnd.ms-excel";
        else if (extension.equalsIgnoreCase("xlt"))
            return "application/vnd.ms-excel";
        else if (extension.equalsIgnoreCase("xlw"))
            return "application/vnd.ms-excel";
        else if (extension.equalsIgnoreCase("xml"))
            return "text/xml";
        else if (extension.equalsIgnoreCase("xof"))
            return "x-world/x-vrml";
        else if (extension.equalsIgnoreCase("xpm"))
            return "image/x-xpixmap";
        else if (extension.equalsIgnoreCase("xsd"))
            return "text/xml";
        else if (extension.equalsIgnoreCase("xsf"))
            return "text/xml";
        else if (extension.equalsIgnoreCase("xsl"))
            return "text/xml";
        else if (extension.equalsIgnoreCase("xslt"))
            return "text/xml";
        else if (extension.equalsIgnoreCase("xsn"))
            return "application/octet-stream";
        else if (extension.equalsIgnoreCase("xwd"))
            return "image/x-xwindowdump";
        else if (extension.equalsIgnoreCase("z"))
            return "application/x-compress";
        else if (extension.equalsIgnoreCase("zip"))
            return "application/x-zip-compressed";
        return "application/octet-stream";
    }
}
