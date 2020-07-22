package sepe.config;

import java.net.InetAddress;
import java.net.URL;
import java.sql.Timestamp;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpUtils;

/**
 * Created by Evangelos on 23/1/2015.
 */
public class Constants {

    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    public static final String LOGIN_TEMPLATE = "login";
    public static final String REGISTER_TEMPLATE = "register";
    public static final String SEPE_ERROR_PREFIX = "SEPE_ERROR:";
    public static final String EMPLOYEE_PROFILE_TEMPLATE = "employeeProfile";
    public static final String CITIZEN_PROFILE_TEMPLATE = "citizenProfile";
    public static final String COMPANY_PROFILE_TEMPLATE = "companyProfile";
    public static final String COMPANY_USER_PROFILE_TEMPLATE = "companyUserProfile";
    public static final String WORKPLACE_DOCTOR_PROFILE_TEMPLATE = "doctorProfile";
    public static final String SAFETY_TECHNICIAN_PROFILE_TEMPLATE = "technicianProfile";
    public static final String ASSOCIATION_PROFILE_TEMPLATE = "associationProfile";
    public static final String ADMIN_PROFILE_TEMPLATE = "adminProfile";
    public static final String PASSWORD_TEMPLATE = "password";

    public static final String REGISTER_ERROR = "registerError";
    /*
    * REGISTER USING OAUTH FROM GSIS
    * */
    public static final String REGISTER_TEMPLATE2 = "register2";

    // DEV
    public static final String OAUTH_CONSUMER_KEY = "tc97t89";
    public static final String OAUTH_CONSUMER_SECRET = "xr7tgt9AbK3";
    public static final String OAUTH_REQUEST_TOKEN_URI = "https://www1.gsis.gr/gsisapps/gsisdemo/oauth/request_token";
    public static final String OAUTH_CONFIRM_ACCESS_URI = "https://www1.gsis.gr/gsisapps/gsisdemo/oauth/confirm_access";
    public static final String OAUTH_ACCESS_TOKEN_URI = "https://www1.gsis.gr/gsisapps/gsisdemo/oauth/access_token";
    public static final String OAUTH_RESOURCE_ONE_URI = "https://www1.gsis.gr/gsisapps/gsisdemo/gsisdemoservice/resource_one";
    public static final String OAUTH_RESOURCE_ONE_CODE = "Μπραβο!!! Καλέσατε με επιτυχία το web service της ΓΓΠΣ μέσω OAuth";
    public static final String OAUTH_LOGOUT_URI = "https://www1.gsis.gr/gsisapps/gsisdemo/logout.htm?logout_token=";
    public static final String OAUTH_CALLBACK_URI = "http://testapps.sepe.int:7778/portal/register2/oauth/confirmed";
    //public static final String OAUTH_CALLBACK_URI = "http://localhost:7001/portal/register2/oauth/confirmed";
    public static final String OAUTH_CALLBACK_URI_TECHNICIAN = "http://testapps.sepe.int:7778/portal/registerTa/oauth/confirmed";
    //public static final String OAUTH_CALLBACK_URI_TECHNICIAN = "http://localhost:7001/portal/registerTa/oauth/confirmed";
    public static final String IKA_EWALLET_URL = "C:\\dev\\ewallet.jks";
    public static final String IKA_SERVER_CERT_FILE = "C:\\dev\\ika.cer";
    /*PAPYROS FILE UPLOAD URI*/
    public static final String PAPYROS_FILE_UPLOAD_URI = "http://172.18.15.11:5003/GENERAL_WS/rest/ws1/submitDocument"; // dev 5003, prod  7003

    // PROD
    /*public static final String OAUTH_CONSUMER_KEY = "eoJcvIshERjDd739edjh348Shb3";
    public static final String OAUTH_CONSUMER_SECRET = "lajDh37sh38dh39458kjzXXsweKae";
    public static final String OAUTH_REQUEST_TOKEN_URI = "https://www1.gsis.gr/gsisapps/soasgsisws/oauth/request_token";
    public static final String OAUTH_CONFIRM_ACCESS_URI = "https://www1.gsis.gr/gsisapps/soasgsisws/oauth/confirm_access";
    public static final String OAUTH_ACCESS_TOKEN_URI = "https://www1.gsis.gr/gsisapps/soasgsisws/oauth/access_token";

    public static final String OAUTH_CALLBACK_URI = "https://apps.sepenet.gr/portal/register2/oauth/confirmed"; // dev 172.18.15.11:6003, prod 172.18.15.17:7003
    public static final String OAUTH_CALLBACK_URI_TECHNICIAN = "https://apps.sepenet.gr/portal/registerTa/oauth/confirmed";
    public static final String OAUTH_RESOURCE_ONE_URI = "https://www1.gsis.gr/gsisapps/soasgsisws/gsisgemhservice/sepe/loggeduserdetails";
    public static final String OAUTH_RESOURCE_ONE_CODE = "Reserved Element";
    public static final String OAUTH_LOGOUT_URI = "https://www1.gsis.gr/gsisapps/soasgsisws/logout.htm?logout_token=";
    public static final String IKA_EWALLET_URL = File.separator + "home" + File.separator + "oracle" +File.separator +"modus" + File.separator + "ewallet.jks";
    public static final String IKA_SERVER_CERT_FILE = File.separator + "home" + File.separator + "oracle" +File.separator +"modus" + File.separator + "ika.cer";
    *//*PAPYROS FILE UPLOAD URI*//*
    public static final String PAPYROS_FILE_UPLOAD_URI = "http://172.18.15.11:7003/GENERAL_WS/rest/ws1/submitDocument"; // dev 5003, prod  7003*/

    /* ---------------------------------------------------------------------
    PERSON_TYPE
    --------------------------------------------------------------------- */
    public enum PERSON_TYPE {

        UNKNOWN("UNKNOWN", 0),
        NATURAL("Φυσικό Πρόσωπο", 1),
        LEGAL("Νομικό Πρόσωπο", 2);

        private final String name;
        private final int code;

        private PERSON_TYPE(String name, int code) {
            this.name = name;
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public int getCode() {
            return code;
        }

        public static PERSON_TYPE getUserCategory(String value) {
            if (value.equals(PERSON_TYPE.NATURAL.getName())) {
                return PERSON_TYPE.NATURAL;
            } else {
                return PERSON_TYPE.LEGAL;
            }
        }

        public static PERSON_TYPE getUserCategory(int value) {
            if (value == PERSON_TYPE.NATURAL.getCode()) {
                return PERSON_TYPE.NATURAL;
            } else {
                return PERSON_TYPE.LEGAL;
            }
        }

    }

    /* ---------------------------------------------------------------------
    USER_TYPE
     --------------------------------------------------------------------- */
    public enum USER_TYPE {
        ADMIN("ROLE_ADMIN", 0),
        EMPLOYEE("ROLE_EMPLOYEE", 1),
        CITIZEN("ROLE_CITIZEN", 2),
        COMPANY("ROLE_EMPLOYER", 3),
        TECHNICIAN("ROLE_TECHNICIAN", 4),
        DOCTOR("ROLE_DOCTOR", 5),
        ASSOCIATION("ROLE_ASSOCIATION", 6),
        COMPANY_USER("ROLE_COMPANY_USER", 7),
        UNKNOWN("UNKNOWN", 8);


        private final String name;
        private final int code;


        private USER_TYPE(String name, int code) {
            this.name = name;
            this.code = code;

        }

        public String getName() {
            return name;
        }

        public int getCode() {
            return code;
        }

        public Role getRole() {
            return getUserRole(this.code);
        }

        public static USER_TYPE getUserType(String value) {
            if (value.equals(USER_TYPE.CITIZEN.getName()))
                return USER_TYPE.CITIZEN;
            else if (value.equals(USER_TYPE.EMPLOYEE.getName()))
                return USER_TYPE.EMPLOYEE;
            else if (value.equals(USER_TYPE.DOCTOR.getName()))
                return USER_TYPE.DOCTOR;
            else if (value.equals(USER_TYPE.TECHNICIAN.getName()))
                return USER_TYPE.TECHNICIAN;
            else if (value.equals(USER_TYPE.COMPANY.getName()))
                return USER_TYPE.COMPANY;
            else if (value.equals(USER_TYPE.COMPANY_USER.getName()))
                return USER_TYPE.COMPANY_USER;
            else if (value.equals(USER_TYPE.ASSOCIATION.getName()))
                return USER_TYPE.ASSOCIATION;
            else if (value.equals(USER_TYPE.ADMIN.getName()))
                return USER_TYPE.ADMIN;
            else {
                return USER_TYPE.UNKNOWN;
            }
        }

        public static USER_TYPE getUserType(int value) {
            if (value == (USER_TYPE.CITIZEN.getCode()))
                return USER_TYPE.CITIZEN;
            else if (value == (USER_TYPE.EMPLOYEE.getCode()))
                return USER_TYPE.EMPLOYEE;
            else if (value == (USER_TYPE.DOCTOR.getCode()))
                return USER_TYPE.DOCTOR;
            else if (value == (USER_TYPE.TECHNICIAN.getCode()))
                return USER_TYPE.TECHNICIAN;
            else if (value == (USER_TYPE.COMPANY.getCode()))
                return USER_TYPE.COMPANY;
            else if (value == (USER_TYPE.COMPANY_USER.getCode()))
                return USER_TYPE.COMPANY_USER;
            else if (value == (USER_TYPE.ASSOCIATION.getCode()))
                return USER_TYPE.ASSOCIATION;
            else if (value == (USER_TYPE.ADMIN.getCode()))
                return USER_TYPE.ADMIN;

            else {
                return USER_TYPE.UNKNOWN;
            }
        }

        public static Role getUserRole(int value) {
            if (value == (USER_TYPE.CITIZEN.getCode()))
                return new Role(USER_TYPE.CITIZEN.getName(), USER_TYPE.CITIZEN.getCode());
            else if (value == (USER_TYPE.EMPLOYEE.getCode()))
                return new Role(USER_TYPE.EMPLOYEE.getName(), USER_TYPE.EMPLOYEE.getCode());
            else if (value == (USER_TYPE.DOCTOR.getCode()))
                return new Role(USER_TYPE.DOCTOR.getName(), USER_TYPE.DOCTOR.getCode());
            else if (value == (USER_TYPE.TECHNICIAN.getCode()))
                return new Role(USER_TYPE.TECHNICIAN.getName(), USER_TYPE.TECHNICIAN.getCode());
            else if (value == (USER_TYPE.COMPANY.getCode()))
                return new Role(USER_TYPE.COMPANY.getName(), USER_TYPE.COMPANY.getCode());
            else if (value == (USER_TYPE.COMPANY_USER.getCode()))
                return new Role(USER_TYPE.COMPANY_USER.getName(), USER_TYPE.COMPANY_USER.getCode());
            else if (value == (USER_TYPE.ASSOCIATION.getCode()))
                return new Role(USER_TYPE.ASSOCIATION.getName(), USER_TYPE.ASSOCIATION.getCode());
            else if (value == (USER_TYPE.ADMIN.getCode()))
                return new Role(USER_TYPE.ADMIN.getName(), USER_TYPE.ADMIN.getCode());
            else {
                return null;
            }
        }
    }

    /* ---------------------------------------------------------------------
    COMPANY_SERVICE
    --------------------------------------------------------------------- */
    public enum COMPANY_SERVICE {
        /*UNKNOWN("UNKNOWN", 0),
        DAILY_CARD("Ημερήσια Δελτία Προσωπικού", 1),
        ACCIDENT("Εργατικά Ατυχήματα", 2),
        PROJECT_ANNOUNCEMENT("Οικοδομικές Εργασίες", 3),
        ILLNESS("Επαγγελματική Ασθένεια", 4),
        SUNDAY_PERMISSION("Χορήγηση Άδειας Εργασίας Κυριακής", 5),
        JOB_RECRUITMENT_OFFICE("Καταστάσεις Ιδιωτικών Γραφείων Ευρέσεως Εργασίας", 6),
        DISPUTE_NEG("Συμφιλιωτικές Διαδικασίες", 7),
        COMPLAINT("Καταγγελίες", 8),
        GEN_REQUEST("Βεβαιώσεις Αντιγράφων ΣΕΠΕ", 9),
        EXTRA_INFO("Αποστολή Ζητούμενων Πρόσθετων Στοιχείων", 10),
        ASSIGN_NOTIFICATION("Αναγγελίες Ι.Ε/Τ.Α", 11),
        DIARY_VEHICLE_SCHEDULE("Ημερολόγιο Δρομολογίων Αυτοκινήτων", 12),
        EXPLANATION("Εξηγήσεις επιχείρησης μετά από έλεγχο", 13),
        DIARY_SAFETY("Ημερολόγιο Μέτρων Ασφάλειας", 14),
        BOOK_SUGGESTIONS("Βιβλίο Υποδείξεων", 15),
        RISK_ASSESSMENT("Εκτίμηση Επαγγελματικού Κινδύνου", 16),
        DISPUTE_NEG_DISABLED("Εργατικές Διαφορές", 20);*/

        UNKNOWN("UNKNOWN", 0),
        ACCIDENT("Εργατικά Ατυχήματα", 1),
        ILLNESS("Επαγγελματική Ασθένεια", 2),
        SUNDAY_PERMISSION("Αιτήσεις χορήγησης άδειας εργασίας κατά την Κυριακή & ημέρα αργίας", 3),
        JOB_RECRUITMENT_OFFICE("Καταστάσεις συμβάσεων εργασίας ΙΓΕΕ", 4),
        DISPUTE_NEG("Συμφιλιωτικές Διαδικασίες", 5),
        COMPLAINT("Καταγγελίες", 6),
        GEN_REQUEST("Βεβαιώσεις Αντιγράφων ΣΕΠΕ", 7),
        EXTRA_INFO("Πρόσθετα στοιχεία μετά από έλεγχο", 8),
        EXPLANATION("Έγγραφες εξηγήσεις μετά από έλεγχο", 9),
        PROJECT_ANNOUNCEMENT("Οικοδομικές Εργασίες", 10),
        DAILY_CARD("Ημερήσια Δελτία Προσωπικού", 11),
        ASSIGN_NOTIFICATION("Αναγγελίες Ι.Ε/Τ.Α", 12),
        BOOK_SUGGESTIONS("Βιβλίο Υποδείξεων", 13),
        DIARY_SAFETY("Ημερολόγιο Μέτρων Ασφάλειας", 14),
        RISK_ASSESSMENT("Εκτίμηση Επαγγελματικού Κινδύνου", 15),
        DIARY_VEHICLE_SCHEDULE("Ημερολόγια Δρομολογίων Αυτοκινήτων", 16),
        CERTIFICATE("Χορήγηση πιστοποιητικού του παρ.2γ, άρθρου 73 του Ν.4412/2016", 21),
        MILITARY("Αναγγελίες Ι.Ε./Τ.Α. για ΈΝΟΠΛΕΣ ΔΥΝΑΜΕΙΣ", 22);
        //DISPUTE_NEG_DISABLED("Εργατικές Διαφορές", 20);

        private final String name;
        private final int code;

        private COMPANY_SERVICE(String name, int code) {
            this.name = name;
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public int getCode() {
            return code;
        }

        public static COMPANY_SERVICE getCompanyServiceByName(String value) {
            if (value.equals(COMPANY_SERVICE.DAILY_CARD.getName()))
                return COMPANY_SERVICE.DAILY_CARD;
            else if (value.equals(COMPANY_SERVICE.ACCIDENT.getName()))
                return COMPANY_SERVICE.ACCIDENT;
            else if (value.equals(COMPANY_SERVICE.PROJECT_ANNOUNCEMENT.getName()))
                return COMPANY_SERVICE.PROJECT_ANNOUNCEMENT;
            else if (value.equals(COMPANY_SERVICE.ILLNESS.getName()))
                return COMPANY_SERVICE.ILLNESS;
            else if (value.equals(COMPANY_SERVICE.SUNDAY_PERMISSION.getName()))
                return COMPANY_SERVICE.SUNDAY_PERMISSION;
            else if (value.equals(COMPANY_SERVICE.JOB_RECRUITMENT_OFFICE.getName()))
                return COMPANY_SERVICE.JOB_RECRUITMENT_OFFICE;
            else if (value.equals(COMPANY_SERVICE.DISPUTE_NEG.getName()))
                return COMPANY_SERVICE.DISPUTE_NEG;
            else if (value.equals(COMPANY_SERVICE.COMPLAINT.getName()))
                return COMPANY_SERVICE.COMPLAINT;
            else if (value.equals(COMPANY_SERVICE.GEN_REQUEST.getName()))
                return COMPANY_SERVICE.GEN_REQUEST;
            else if (value.equals(COMPANY_SERVICE.CERTIFICATE.getName()))
                return COMPANY_SERVICE.CERTIFICATE;
            else if (value.equals(COMPANY_SERVICE.MILITARY.getName()))
                return COMPANY_SERVICE.MILITARY;
            else if (value.equals(COMPANY_SERVICE.EXTRA_INFO.getName()))
                return COMPANY_SERVICE.EXTRA_INFO;
            else if (value.equals(COMPANY_SERVICE.ASSIGN_NOTIFICATION.getName()))
                return COMPANY_SERVICE.ASSIGN_NOTIFICATION;
            else if (value.equals(COMPANY_SERVICE.DIARY_VEHICLE_SCHEDULE.getName()))
                return COMPANY_SERVICE.DIARY_VEHICLE_SCHEDULE;
            else if (value.equals(COMPANY_SERVICE.EXPLANATION.getName()))
                return COMPANY_SERVICE.EXPLANATION;
            else if (value.equals(COMPANY_SERVICE.DIARY_SAFETY.getName()))
                return COMPANY_SERVICE.DIARY_SAFETY;
            else if (value.equals(COMPANY_SERVICE.BOOK_SUGGESTIONS.getName()))
                return COMPANY_SERVICE.BOOK_SUGGESTIONS;
            else if (value.equals(COMPANY_SERVICE.RISK_ASSESSMENT.getName()))
                return COMPANY_SERVICE.RISK_ASSESSMENT;
            else {
                return COMPANY_SERVICE.UNKNOWN;
            }
        }

        public static COMPANY_SERVICE getCompanyServiceByCode(int value) {
            if (value == (COMPANY_SERVICE.DAILY_CARD.getCode()))
                return COMPANY_SERVICE.DAILY_CARD;
            else if (value == (COMPANY_SERVICE.ACCIDENT.getCode()))
                return COMPANY_SERVICE.ACCIDENT;
            else if (value == (COMPANY_SERVICE.PROJECT_ANNOUNCEMENT.getCode()))
                return COMPANY_SERVICE.PROJECT_ANNOUNCEMENT;
            else if (value == (COMPANY_SERVICE.ILLNESS.getCode()))
                return COMPANY_SERVICE.ILLNESS;
            else if (value == (COMPANY_SERVICE.SUNDAY_PERMISSION.getCode()))
                return COMPANY_SERVICE.SUNDAY_PERMISSION;
            else if (value == (COMPANY_SERVICE.JOB_RECRUITMENT_OFFICE.getCode()))
                return COMPANY_SERVICE.JOB_RECRUITMENT_OFFICE;
            else if (value == (COMPANY_SERVICE.DISPUTE_NEG.getCode()))
                return COMPANY_SERVICE.DISPUTE_NEG;
            else if (value == (COMPANY_SERVICE.COMPLAINT.getCode()))
                return COMPANY_SERVICE.COMPLAINT;
            else if (value == (COMPANY_SERVICE.GEN_REQUEST.getCode()))
                return COMPANY_SERVICE.GEN_REQUEST;
            else if (value == (COMPANY_SERVICE.CERTIFICATE.getCode()))
                return COMPANY_SERVICE.CERTIFICATE;
            else if (value == (COMPANY_SERVICE.MILITARY.getCode()))
                return COMPANY_SERVICE.MILITARY;
            else if (value == (COMPANY_SERVICE.EXTRA_INFO.getCode()))
                return COMPANY_SERVICE.EXTRA_INFO;
            else if (value == (COMPANY_SERVICE.ASSIGN_NOTIFICATION.getCode()))
                return COMPANY_SERVICE.ASSIGN_NOTIFICATION;
            else if (value == (COMPANY_SERVICE.DIARY_VEHICLE_SCHEDULE.getCode()))
                return COMPANY_SERVICE.DIARY_VEHICLE_SCHEDULE;
            else if (value == (COMPANY_SERVICE.EXPLANATION.getCode()))
                return COMPANY_SERVICE.EXPLANATION;
            else if (value == (COMPANY_SERVICE.DIARY_SAFETY.getCode()))
                return COMPANY_SERVICE.DIARY_SAFETY;
            else if (value == (COMPANY_SERVICE.BOOK_SUGGESTIONS.getCode()))
                return COMPANY_SERVICE.BOOK_SUGGESTIONS;
            else if (value == (COMPANY_SERVICE.RISK_ASSESSMENT.getCode()))
                return COMPANY_SERVICE.RISK_ASSESSMENT;
            else {
                return COMPANY_SERVICE.UNKNOWN;
            }
        }
    }

    /* ---------------------------------------------------------------------
    SUBMIT_STATUS
    --------------------------------------------------------------------- */
    public enum SUBMIT_STATUS {
        UNKNOWN("UNKNOWN", 0),
        SAVED("Αποθηκευμένο", 1),
        SUBMITTED("Υποβληθέν", 2);

        private final String name;
        private final int code;

        private SUBMIT_STATUS(String name, int code) {
            this.name = name;
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public int getCode() {
            return code;
        }


        public static SUBMIT_STATUS getSubmitStatus(String value) {
            if (value.equals(SUBMIT_STATUS.SAVED.getName()))
                return SUBMIT_STATUS.SAVED;
            else if (value.equals(SUBMIT_STATUS.SUBMITTED.getName()))
                return SUBMIT_STATUS.SUBMITTED;
            else {
                return SUBMIT_STATUS.UNKNOWN;
            }
        }


        public static SUBMIT_STATUS getSubmitStatus(int value) {
            if (value == (SUBMIT_STATUS.SAVED.getCode()))
                return SUBMIT_STATUS.SAVED;
            else if (value == (SUBMIT_STATUS.SUBMITTED.getCode()))
                return SUBMIT_STATUS.SUBMITTED;
            else {
                return SUBMIT_STATUS.UNKNOWN;
            }
        }
    }

    /* ---------------------------------------------------------------------
    REQUEST_STATUS
    --------------------------------------------------------------------- */
    public enum REQUEST_STATUS {
        UNKNOWN("UNKNOWN", 0),
        INPROGRESS("Σε Εξέλιξη", 1),
        CHARGED("Χρεώθηκε", 2),
        EXAMINATION("Έλεγχος", 3),
        RECORDS("Αρχείο", 4),
        PENDING("Σε Εκκρεμότητα", 5),
        ACCEPTED("Εγκρίθηκε", 6),
        REJECTED("Απορρίφθηκε", 7);

        private final String name;
        private final int code;

        private REQUEST_STATUS(String name, int code) {
            this.name = name;
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public int getCode() {
            return code;
        }

        public static REQUEST_STATUS getRequestStatus(String value) {
            if (value.equals(REQUEST_STATUS.INPROGRESS.getName()))
                return REQUEST_STATUS.INPROGRESS;
            else if (value.equals(REQUEST_STATUS.CHARGED.getName()))
                return REQUEST_STATUS.CHARGED;
            else if (value.equals(REQUEST_STATUS.EXAMINATION.getName()))
                return REQUEST_STATUS.EXAMINATION;
            else if (value.equals(REQUEST_STATUS.RECORDS.getName()))
                return REQUEST_STATUS.RECORDS;
            else if (value.equals(REQUEST_STATUS.PENDING.getName()))
                return REQUEST_STATUS.PENDING;
            else if (value.equals(REQUEST_STATUS.ACCEPTED.getName()))
                return REQUEST_STATUS.ACCEPTED;
            else if (value.equals(REQUEST_STATUS.REJECTED.getName()))
                return REQUEST_STATUS.REJECTED;
            else {
                return REQUEST_STATUS.UNKNOWN;
            }
        }

        public static REQUEST_STATUS getRequestStatus(int value) {
            if (value == (REQUEST_STATUS.INPROGRESS.getCode()))
                return REQUEST_STATUS.INPROGRESS;
            else if (value == (REQUEST_STATUS.CHARGED.getCode()))
                return REQUEST_STATUS.CHARGED;
            else if (value == (REQUEST_STATUS.EXAMINATION.getCode()))
                return REQUEST_STATUS.EXAMINATION;
            else if (value == (REQUEST_STATUS.RECORDS.getCode()))
                return REQUEST_STATUS.RECORDS;
            else if (value == (REQUEST_STATUS.PENDING.getCode()))
                return REQUEST_STATUS.PENDING;
            else if (value == (REQUEST_STATUS.ACCEPTED.getCode()))
                return REQUEST_STATUS.ACCEPTED;
            else if (value == (REQUEST_STATUS.REJECTED.getCode()))
                return REQUEST_STATUS.REJECTED;
            else {
                return REQUEST_STATUS.UNKNOWN;
            }
        }
    }

    /* ---------------------------------------------------------------------
    COMP_ANN_STATUS [status Αναγγελιας TE/IA απο εταιρια]
    --------------------------------------------------------------------- */
    public enum COMP_IE_TA_ANN_STATUS {
        UNKNOWN("UNKNOWN", -2),
        REJECTED("Απορρίφθηκε", -1), /* Rejected by IE/TA */
        ISSUED("Εκδόθηκε", 0), /* Εκδόθηκε απο την εταιρια, ΙΕ/ΤΑ δεν εχει απαντησει */
        ACCEPTED("Εγκρίθηκε", 1), /* Accepted by IE/TA */
        EXPIRED("Έληξε", 2), /* Expired by new announcement */
        PAUSED("Παύση", 3); /* Paused by company */

        private final String name;
        private final int code;

        private COMP_IE_TA_ANN_STATUS(String name, int code) {
            this.name = name;
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public int getCode() {
            return code;
        }


        public static COMP_IE_TA_ANN_STATUS getCompAnnStatus(String value) {
            if (value.equals(COMP_IE_TA_ANN_STATUS.REJECTED.getName()))
                return COMP_IE_TA_ANN_STATUS.REJECTED;
            else if (value.equals(COMP_IE_TA_ANN_STATUS.ISSUED.getName()))
                return COMP_IE_TA_ANN_STATUS.ISSUED;
            else if (value.equals(COMP_IE_TA_ANN_STATUS.ACCEPTED.getName()))
                return COMP_IE_TA_ANN_STATUS.ACCEPTED;
            else if (value.equals(COMP_IE_TA_ANN_STATUS.EXPIRED.getName()))
                return COMP_IE_TA_ANN_STATUS.EXPIRED;
            else if (value.equals(COMP_IE_TA_ANN_STATUS.PAUSED.getName()))
                return COMP_IE_TA_ANN_STATUS.PAUSED;
            else {
                return COMP_IE_TA_ANN_STATUS.UNKNOWN;
            }
        }


        public static COMP_IE_TA_ANN_STATUS getCompAnnStatus(int value) {
            if (value == (COMP_IE_TA_ANN_STATUS.REJECTED.getCode()))
                return COMP_IE_TA_ANN_STATUS.REJECTED;
            else if (value == (COMP_IE_TA_ANN_STATUS.ISSUED.getCode()))
                return COMP_IE_TA_ANN_STATUS.ISSUED;
            else if (value == (COMP_IE_TA_ANN_STATUS.ACCEPTED.getCode()))
                return COMP_IE_TA_ANN_STATUS.ACCEPTED;
            else if (value == (COMP_IE_TA_ANN_STATUS.EXPIRED.getCode()))
                return COMP_IE_TA_ANN_STATUS.EXPIRED;
            else if (value == (COMP_IE_TA_ANN_STATUS.PAUSED.getCode()))
                return COMP_IE_TA_ANN_STATUS.PAUSED;
            else {
                return COMP_IE_TA_ANN_STATUS.UNKNOWN;
            }
        }
    }

    /* ---------------------------------------------------------------------
    EMAIL_NOTIFICATION_TYPE
    --------------------------------------------------------------------- */
    public enum EMAIL_NOTIFICATION_TYPE {

        EMAIL("Μέσω email", 0),
        NONE("Να μην ενημερώνομαι", 1);

        private final String name;
        private final int code;

        private EMAIL_NOTIFICATION_TYPE(String name, int code) {
            this.name = name;
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public int getCode() {
            return code;
        }
    }

    /* ---------------------------------------------------------------------
    EMP_DISP_CATEG
    --------------------------------------------------------------------- */
    public enum EMP_DISP_CATEG {
        UNKNOWN("UNKNOWN", 0),
        EMP_DISP_SALARY("Οφειλή δεδουλευμένων αποδοχών μηνών", 1),
        EMP_DISP_HOLIDAY("Οφειλή αποζημίωσης μη ληφθείσας άδειας έτους", 2),
        EMP_DISP_HOLIDAY_PAY("Οφειλή επιδόματος άδειας", 4),
        EMP_DISP_CHRISTMAS_GIFT("Οφειλή δώρου Χριστουγέννων έτους", 5),
        EMP_DISP_EASTER_GIFT("Οφειλή δώρου Πάσχα έτους", 6),
        EMP_DISP_OVERTIME_WORK("Οφειλή αμοιβής υπέρωριακής απασχόλησης", 7),
        EMP_DISP_HOLIDAY_WORK("Οφειλή προσαύξησης αμοιβής λόγω εργασίας Κυριακών-Αργιών", 8),
        EMP_DISP_NIGHT_WORK("Οφειλή προσαύξησης αμοιβής λόγω νυχτερινής εργασίας", 9),
        EMP_DISP_RESTDAY_WORK("Εργασία κατά τις μέρες της εβδομαδιαίας αναπαύσεως", 10),
        EMP_DISP_ILLNESS("Οφειλή αποδοχών ασθένεις περιόδου", 11),
        EMP_DISP_INDEFINITE_TERM("Καταγγελία σύμβασης εργασίας αορίστου χρόνου", 12),
        EMP_DISP_PREGNANT("Καταγγελία σύμβασης εργασίας εγκύου", 13),
        EMP_DISP_SYNDICALIST("Καταγγελία σύμβασης εργασίας συνδικαλιστή", 14),
        EMP_DISP_MILITARY("Καταγγελία σύμβασης εργασίας στρατευμένου", 15),
        EMP_DISP_FIXED_TERM("Καταγγελία σύμβασης εργασίας ορισμένου  χρόνου", 16),
        EMP_DISP_TERMS("Μονομερής βλαπτική μεταβολή των όρων εργασίας", 17),
        EMP_DISP_EQUAL_RELATIONSHIP("Καταγγελία Ίσης μεταχείρισης", 18),
        EMP_DISP_OTHER("Άλλοι λόγοι", 19);


        private final String name;
        private final int code;

        private EMP_DISP_CATEG(String name, int code) {
            this.name = name;
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public int getCode() {
            return code;
        }


        public static EMP_DISP_CATEG getEmpDispCateg(String value) {

            return EMP_DISP_CATEG.UNKNOWN;
        }


        public static EMP_DISP_CATEG getEmpDispCateg(int value) {

            return EMP_DISP_CATEG.UNKNOWN;
        }
    }

    /* ---------------------------------------------------------------------
    COMPLAINT_TYPE_DESC
    --------------------------------------------------------------------- */
    public enum COMPLAINT_TYPE_DESC {
        NAMED("ΕΠΩΝΥΜΗ", 1),
        ANONYMOUS("ΑΝΩΝΥΜΗ", 2);

        private final String name;
        private final int code;

        private COMPLAINT_TYPE_DESC(String name, int code) {
            this.name = name;
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public int getCode() {
            return code;
        }
    }

    /* ---------------------------------------------------------------------
    COMPLAINT_TE //Texniki Epithewrisi
    --------------------------------------------------------------------- */
    public enum COMPLAINT_TE {
        NO("Όχι", 0),
        YES("Ναι", 1);

        private final String name;
        private final int code;

        private COMPLAINT_TE(String name, int code) {
            this.name = name;
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public int getCode() {
            return code;
        }
    }

    /* ---------------------------------------------------------------------
    COMPLAINT_KE //Koinwniki epithewrisi
    --------------------------------------------------------------------- */
    public enum COMPLAINT_KE {
        NO("Όχι", 0),
        YES("Ναι", 1);

        private final String name;
        private final int code;

        private COMPLAINT_KE(String name, int code) {
            this.name = name;
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public int getCode() {
            return code;
        }
    }

    /* ---------------------------------------------------------------------
    COMPLAINT_UNION //Epixeirisi/Swmateio
    --------------------------------------------------------------------- */
    public enum COMPLAINT_UNION {
        NO("Όχι", 0),
        YES("Ναι", 1);

        private final String name;
        private final int code;

        private COMPLAINT_UNION(String name, int code) {
            this.name = name;
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public int getCode() {
            return code;
        }

    }

    public static boolean StringIsNullOrEmpty(String s) {
        if (s == null) return true;
        if (s.isEmpty()) return true;
        return false;
    }

    public static boolean StringIsNullOrEmptyORContainsEmpty(String s) {
        if (s == null) return true;
        if (s.isEmpty()) return true;
        if (s.contains(" ")) return true;
        return false;
    }

    public static boolean IntegerIsNullOrEmpty(Integer i) {
        if (i == null) return true;
        return false;
    }

    public static boolean LongIsNullOrEmpty(Long i) {
        if (i == null) return true;
        return false;
    }

    public static boolean TimestampIsNullOrEmpty(Timestamp t) {
        if (t == null) return true;
        return false;
    }
}
