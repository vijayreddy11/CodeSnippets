
public class UtilityCommon {

    //JDBC UTILITY COMMON
    public static final String QUERY_EXECUTION_ERROR = "Failed to execute jdbc query";
    public static final String NULL_CONNECTION = "Null connection";
    public static final String NULL_SQL_STATEMENT = "Null SQL statement";
    public static final String QUERY_TEMPLATE_GROUP = "query_template_group";
    public static final String QUERY_TEMPLATE_NAME = "query_template_name";
    public static final String QUERY_INPUTS = "query_inputs";
    public static final String CHILDS = "childs";
    public static final String QUERY_TEMPLATE_OUTPUT_KEY = "query output key";
    public static final String RELATION_KEYS = "relation_keys";
    public static final String FAILED_TO_EXECUTE_QUERY = "Failed execute query in ParentChildQueryUtil";
    public static final String REDIS_URL = "redis_url";
    ;
    public static final String RSA_ENCRYPTION = "RSA";
    public static final String AES_ENCRYPTION = "AES";
    public static final String FILE_SEPARATOR = "/";

    //EMAIL UTILITY COMMON
    public static final String MAIL_FROM = "from";
    public static final String MAIL_TO = "to";
    public static final String MAIL_CC = "cc";
    public static final String MAIL_BCC = "bcc";
    public static final String MAIL_SUBJECT = "subject";
    public static final String MAIL_CONTEXT = "context";
    public static final String MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";
    public static final String MAIL_SMTP_HOST = "mail.smtp.host";
    public static final String SMTP_HOST = "localhost";
    public static final String SMTP_PROTOCOL = "smtp";
    public static final String EMAIL_CONTENT_TYPE = "Content-Type";


    //BIGQUERY UTILITY COMMON
    public static final String CLIENT_CONFIGS = "clientConfigs";
    public static final String TABLE_NAME = "table_name";
    public static final String PROJECT_ID = "project_id";
    public static final String NO_MODELS_IN_DATASET = "Dataset does not contain any models";
    public static final String NO_DATASETS_IN_PROJECT = "Project does not contain any datasets";

    //SHAREPOINT UTILITY COMMON
    public static final String FOLDER_NAME = "folder_name";
    public static final String FOLDER_ITEMS_URL = "folder_items_url";
    public static final String FOLDER_GUID = "folder_guid";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String ACCESS_TOKEN_EXPIRES_ON = "expires_on";
    public static final String INVALID_APP_CREDENTIALS_EXP_MSG = "Failed to init Invalid app credentials either client cuttt";
    public static final String FAILED_TO_GET_TENANT_ID_EXP_MSG = "Failed to get tenant_id";
    public static final String FAILED_TO_GET_NEW_TOKEN_EXP_MSG = "Failed to get new token:";
    public static final String TENANT_ID_IS_NULL_EXP_MSG = "Tenant_id is null";
    public static final String HEADER_ACCEPT = "accept";
    public static final String HEADER_AUTHORIZATION = "authorization";
    public static final String HEADER_CACHE_CONTROL = "cache-control";
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String HEADER_X_REQUESTED_BY = "X-Requested-By";
    public static final String HEADER_APLLICATION_JSON = "application/json";
    public static final String HEADER_BEARER = "bearer";
    public static final String HEADER_NO_CACHE = "no-cache";
    public static final String KEY_REPEATED = "_REPEATED";
    public static final String CLIENT_ID = "client_id";
    public static final String CLIENT_SECRET = "client_secret";
    public static final String RESOURCE = "resource";
    public static final String HEADER_X_REQUESTED_BY_CONTENT_TYPE = "application/x-www-form-urlencoded";
    public static final String GRANT_TYPE = "grant_type";
    public static final String CLIENT_CREDENTIALS = "client_credentials";
    //HTTP UTILITY COMMON
    public static final String STATUS = "status";
    public static final String HTTP_RESPONSE = "http_response";
    public static final String HTTP_HEADERS = "http_headers";

    //SMB COMMON
    public static final String DOMAIN = "domain";
    public static final String SOURCE_NAME = "sourceName";
    public static final String USERNAME = "username";
    public static final String SMB_SCHEME = "smb://";

    //HDFD COMMON
    public static final String HDFS_OP_EXP = "hdfs op exp";
    public static final String HDFS_OP_STATUS = "hdfs_op_status";
    public static final String DIRECTORY_FILE_PATH = "directory_file_path";
    public static final String KEYTAB_PATH = "keyTabPath";
    public static final String CORE_SITE_XML = "core-site";
    public static final String HDFS_SITE_XML = "hdfs-site";
    public static final String FAILED_TO_DELETE_DIRECTORY = "Failed to delete directory";
    public static final String FAILED_TO_CREATE_DIRECTORY = "Failed to create directory";
    public static final String HADOOP_SECURITY_AUTHENTICATION = "hadoop.security.authentication";
    public static final String KERBEROS = "kerberos";
    public static final String HADOOP_RPC_PROTECTION = "hadoop.rpc.protection";
    public static final String PRIVACY = "privacy";
    public static final String FS_HDFS_IMPL = "fs.hdfs.impl";
    public static final String FS_FILE_IMPL = "fs.file.impl";
    public static final String USERID = "userId";
    public static final String SOURCE_FILE = "source_file";
    public static final String DESTINATION_FILE = "destination_file";
    public static final String STATUS_ERROR = "status_error";
    public static final String ERROR_WHILE_COPYING_HDFS_FILE = "error while copying hdfs file";
    public static final String INVALID_FILE_LIST = "Invalid file_list";
    public static final String EXCEPTION_WHILE_COPYING_FILES = "Exception while copying files";
    public static final String INVALID_SOURCE_HDFS_CONFIG = "invalid source hdfs config";
    public static final String INAVLID_DESTINATION_HDFS_CONFIG = "invalid destination hdfs config";
    public static final String INVALID_FILESINFO = "Invalid filesInfo";
    public static final String FILES_LIST = "files_list";
    public static final String MAPRED_SITE = "mapred-site";
    public static final String YARN_SITE = "yarn-site";
    public static final int ARGUMENT_SIZE = 6;
    public static final String ARCHIVE_NAME = "archiveName";
    public static final String HAR_EXTENSION = ".har";
    public static final String PARENT_DIRECTORY = "-p";

    //FTP AUTH UTILITY
    public static final String AT_THE_RATE = "@";
    public static final String STRICTHOSTKEYCHECKING = "StrictHostKeyChecking";
    public static final String NO = "no";
    public static final String SFTP = "sftp";
    public static final String S3_URL = "s3_url";
    public static final String API_URL = "api_url";
    public static final String PASSWORD = "password";
    public static final String USER_NAME = "username";
    public static final String IS_NULL = " is null";
    public static final String FLAG_YES = "Y";
    public static final String IS_Encrypted = "IsEncrypted";
    public static final String PRIVATE_KEY_FILE_PATH = "private_key_file_path";
    public static final String SOURCE_URL = "sourceUrl";
    public static final String PROXY_URL = "proxyUrl";
    public static final String IDENTIFIER = "identifier";
    public static final String SITE_DOMAIN = "site_domain";
    public static final String SITE_URL = "site_url";
    public static final String RESPONSE_FAILED = "Failed to get response";
    public static final String EXP_PARENT_MAPS_NOT_FOUND = "Parent maps not found";
    public static final String EXP_FAILED_GET = "Parent maps not found";
    public static final String EXP_KEY_ARGUMENT_NULL = "Key argument is null";
    public static final String EXP_SOURCE_MAP_ARGUMENT_NULL = "Key argument is null";
    public static final String FAILED_CONVERT_JSON = "Failed to parse json";
    public static final String HDFS_PATH_NOT_FOUND = "HDFS Path not found";
    public static final String FAILED_TO_CREATE_HAR = "Har error for";
    public static final String INPUT_FILE_NOT_FOUND = "Input file not found";
    public static final String MISSING_CONFIG_FILES_NOT_FOUND = "Missing required config files in map";
    public static final String FAILED_TO_SEND_EMAIL = "Failed to send email";
    public static final char ST_DELIMITER = '$';
    public static final String TEMPLATE_NOT_DEFINED = "Template not defined";
    public static final String KEY_DATA = "data";
    public static final String EMPTY_OR_NULL_PASSWORD = "Empty or null password";
    public static final String EXP_INIT_BIGQUERY_CLIENT = "Failed to initialize BigQuery client";
    public static final String EXP_TABLE_EMPTY = "Table name is null or empty in dataset";
    public static final String EXP_NULL_SCHEMA = "null schema";
    public static final String EXP_FAILED_GET_METADATA = "Failed to get table metadata";
    public static final String EXP_TABLE_NOT_FOUND = "Table not found";
    public static final String EXP_FAILED_TO_LIST_TABLES = "Failed to list tables";
    public static final String EXP_DATASET_NOT_FOUND = "Dataset not found";
    public static final String EXP_FAILED_GET_COUNTS = "Failed to get counts";
    public static final String EXP_DISKSHARE_NOT_INITIALIZED = "DiskShare is not initialized";
    public static final String EXP_EMPTY_OR_NULL_REQUIRED_VALUES = "Empty or null required values";
    public static final String EXP_INVALID_FILE_PATH = "Null or empty file path";
    public static final String EXP_CLOSING_CONNECTION = "Error while closing connection:";
    public static final String EXP_CREATING_DOWNLOAD_SERVICE = "Failed to create download service";
    public static final String DEFAULT_CHARSET = "UTF-8";
    public static final String HEADER_ACCEPT_JSON_VERB = "application/json;odata=verbose";
    public static final String HEADER_BEARER_SPC = "Bearer ";

    //SharePoint
    public static final String SP_FILE_STREAM_URL = "/_api/web/GetFolderByServerRelativeUrl";
    public static final String SP_DEFERRED_URI = "FieldValuesAsText.__deferred.uri";
    public static final String SP_KEY_NEXT = "d.__next";
    public static final String SP_KEY_D_RESULTS = "d.results";
    public static final String SP_WWW_AUTHENTICATE = "WWW-Authenticate";
    public static final String SP_TENANT_ID_URL = "/_vti_bin/client.svc/";
    public static final String SP_ACCESS_TOKEN_URL_PREFIX = "https://accounts.accesscontrol.windows.net/";
    public static final String SP_ACCESS_TOKEN_URL_SUFFIX = "/tokens/OAuth/2";
    public static final String SP_LIST_FOLDER_URL = "/_api/Web/Lists";
    public static final String SP_KEY_BASE_TEMPLATE = "BaseTemplate";
    public static final String SP_KEY_ID = "Id";
    public static final String SP_KEY_Title = "Title";
    public static final String SP_ITEMS_DEFERRED_URI = "Items.__deferred.uri";
    public static final String DP_KEY_D = "d";
    public static final String SP_D_NEXT = "d.__next";
    public static final String SP_FILEDVALUESASTEXT = "FieldValuesAsText.__deferred.uri";
    public static final String SP_KEY_FILEREF = "FileRef";


    //kafka
    public static final String TOPIC_LIST = "topics";
    public static final String TOPIC_CONTAINS_PATTERN = "topic_contains_pattern";
    public static final String KAFKA_BOOTSTRAP_SERVERS = "bootstrap_servers";
    public static final String TRUSTORE_FILE_PATH = "truststoreFilePath";
    public static final String SECURITY_PROTOCOL_TYPE = "SASL_SSL";
    public static final String MECHANISM = "GSSAPI";
    public static final String SERVICE_NAME = "kafka";
    public static final String SESSION_TIMEOUT_MS_CONFIG = "60000";
    public static final String ENABLE_AUTO_COMMIT_CONFIG = "false";
    public static final Integer MAX_POLL_RECORDS_CONFIG = 1;
    public static final Integer MAX_POLL_INTERVAL_MS_CONFIG = 172800000; //2days 172800000
    public static final Integer REQUEST_TIMEOUT_MS_CONFIG = 172830000; //2days 5sec 172830000
    public static final String GROUP_ID = "group_id";
    public static final String CONSUMER_CLIENT_ID = "client_id";
    public static final String ACKS_CONFIG = "1";
    public static final String EXP_NULL_OR_EMPTY_VALUE = "null or empty key value";
    public static final String HDFS_FILE_PATH = "hdfs_file_path";
    public static final String HDFS_FILE_CONTENT = "hdfs_file_content";
    public static final String SUCCESS = "success";
    public static final String FAILED = "failed";
    public static final String SUCCESS_COUNT = "success_count";
    public static final String FAILED_COUNT = "failed_count";
    public static final String PARENT_PATH = "parent_path";
    public static final String DESTINATION_PATH = "destination_path";
    public static final String HAR_NAME = "har_name";
    public static final String HAR_DIR_NAME = "har_dir_name";
    public static final String JAVAX_NET_SSL_TRUSTSTORE = "javax.net.ssl.trustStore";
    public static final String TRUST_STORE = "truststore";
    public static final String KEY_TAB_PATH = "keyTabPath";
    public static final String USER_ID = "userId";
    public static final String HDFS_SITE = "hdfs-site";
    public static final String CORE_SITE = "core-site";
    public static final String INSTANCE = "INSTANCE";
    public static final String PERMS = "perms";
    public static final String DEFAULTPOLICY = "defaultPolicy";
    public static final String MODIFIERS = "modifiers";
    public static final String IS_RESTRICTED = "isRestricted";
    public static final String JAVAX_CRYPTO_CRYPTOALLPERMISSION = "javax.crypto.CryptoAllPermissions";
    public static final String JAVAX_CRYPTO_CRYPTOPERMISSIONS = "javax.crypto.CryptoPermissions";
    public static final String JAVAX_CRYPTO_JCESECURITY = "javax.crypto.JceSecurity";
    public static final String FAILED_TO_REMOVE_CRYPTOGRAPHY_RESTRICTIONS = "Failed to remove cryptography restrictions";

    public static final String NOT_A_DATA_FILE = "Not a data File";
    public static final String BLOCK_SIZE_INVALID_OR_TOO_LONG_FOR_THIS = "Block size invalid or too large for this";
    public static final String IMPLEMENTATION = "implementation";
    public static final String INVALID_SYNC = "Inavlid sync!";
    public static final String BOOTSTRAP_BROKERS = "bootstrap_brokers";

    //Documentum
    public static final String PASSWD = "passwd";
    public static final String ENCRYPTED_PASSWD = "encrypted_passwd";
    public static final String IS_ENCRYPTED = "Y";
    public static final String PRIVATE_KEY = "private_key";

    public static final String DOCUMNETUM_PATH = "documentum_path";
    public static final String DOC_BASE = "doc_base";
    public static final String R_OBJECT_ID = "r_object_id";
    public static final String BUCKET_NAME = "bucketName";
    public static final String BUCKET_KEY = "key";
    public static final String newLineCharacter = "\n";
    public static final String EXP_NULL_HIKARI_DATASOURCE = "null hikari datasource";
    public static final String LOG_TYPE_FILE = "FILE";
    public static final String LOG_TYPE_DB = "DATABASE";
    public static final String LOG_FILE_LOCATION = "log_file_location";
    public static final String TEMPLATE_GROUP = "template_group";
    public static final String TEMPLATE_NAME = "template_name";
    public static final String DATA = "data";
    public static final String CURRENT_TIMESTAMP = "current_timestamp";
    public static final String LOG_TYPE = "log_type";
    public static final String FAILED_TO_COPY_FILE = "failed_to_copy_file";
    public static final String VAULTURL = "vaultURL";
    public static final String YES = "Y";
    public static final String ENCRYPTED_PASSWORD = "encrypted_password";
    public static final String CONFIG_NULL = "Config Map is null";
    public static final String TOKEN_NULL = "Failed to get token";
    public static final String FAILED_TO_CONNECT_VAULT_SERVER = "Failed to connect to vault server";
    public static final String VAULT_NULL_ERROR = "Vault Connection is null";
    public static final String VAULTMOUNTPATH = "vaultMountPath";

    public static final String KEY_REST_ST_GROUPID = "groupId";
    public static final String KEY_TEMPLATE_RENDER_TYPE = "template_renderer_type";
    public static final String KEY_GROUP_FILE_RENDERER = "STGroupFileRenderer";
    public static final String KEY_ENDPOINT_RENDERER = "STRestEndPointRenderer";
    public static final String KEY_REST_END_POINT = "rest_end_point";
    public static final String KEY_TEMPLATE_CONFIG = "template_config";

    public static final String VAULT_CONFIG_FILE = "vault_config";
    public static final String VAULT_ID = "vault_id";

    public static final String VAULT_CLASSNAME = "vault_classname";

    public static final String BUCKET = "bucket";
    public static final String BLOB = "blob";
    public static final String FILE = "file";
    public static final String FILE_NAME = "file_name";
    public static final String FILE_FORMAT = "file_format";
    public static final String RELATIVE_PATH = "relativePath";
    public static final String ABSOLUTE_PATH = "absolutePath";
    public static final String FILE_MODIFIED_TIME = "lastmodified";
    public static final String FILE_OWNER = "owner_id";
    public static final String FILE_SIZE = "size";
    public static final String ADDITIONAL_INFO = "additional_info";
    public static final String SOURCE = "source";
    public static final String BIGQUERY = "bigquery";
    public static final String TABLE="TABLE";
    public static final String EXTERNAL="EXTERNAL";
    public static final String DATAPLACE_ID="dataplace_id";
    public static final String DATAPLACE_COMPONENT_TYPE_ID="dataplace_component_type_id";
    public static final String SCHEMA_NAME="schema_name";
    public static final String SCHEMA_ID="schema_id";
    public static final String TSV="tsv";
    public static final String CSV="csv";


    //ADLS2
    public static final String ACCOUNT_KEY="accountkey";
    public static final String ACCOUNT_NAME="accountname";
    public static final String DFS_CORE_WINDOWS_NET=".dfs.core.windows.net";
    public static final String HTTPS="https://";

    //Sharepoint
    public static final String HEADER_APPLICATION_JSON = "application/json";
    public static final String SP_TENANT_ID_URl ="/_vti_bin/client.svc/";
    public static final String SP_FIELDVALUESASTEXT="FieldValuesAsText.__deferred.uri";
    public static final String SP_KEY_D="d";


}
