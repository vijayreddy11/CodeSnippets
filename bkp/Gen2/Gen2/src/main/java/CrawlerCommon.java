package com.modak.spiderweb.common;

public class CrawlerCommon {
    public static final String KAFKA_BOOTSTRAP_SERVERS = "bootstrap_servers";
    public static final String TRUSTORE_FILE_PATH = " truststoreFilePath";
    public static final String PRIORITY_TOPIC_LIST = " priority_topics";
    public static final String GROUP_ID = "group_id";
    public static final String PUBLIC_KEY = "public_key";
    public static final String PRIVATE_KEY = "private_key";
    public static final String CRAWLER_IS_NOT_SUPPLIED_WITH_CONFIGURATIONS_DIRECTORY_PATH = "Crawler is not supplied" +
            " with configurations directory path";
    public static final String SO_COULD_NOT_START_THE_CRAWLER_WORLD_PROCESS = "So could not start thecrawler world" +
            " process !";
    public static final String LOGGER_CONFIG_FILE_IS_FOUND = "logger connection_profiles file is found ";
    public static final String LOGGER_CONFIG_FILE_LOG4J2_XML_IS_NOT_FOUND = "logger connection_profiles file i.e " +
            "log4j2.xml is not found  !";

    public static final String SOURCE_INFO = "source_info";
    public static final String SOURCE_INFO_DETAILS = "source_info_details";
    public static final String METASTORE_INFO = "metastore_info";
    public static final String JDBC_INFO = "jdbc_info";
    public static final String HOST_NAME = "host_name";
    public static final String HIVE_HOST_NAME = "jdbc_url";
    public static final String DATABASE_NAME = "database_name";
    public static final String RUN_ATTRIBUTES = "run_attributes";
    public static final String DATABASE_CONFIG = "database_config";
    public static final String THREADPOOL_SIZE = "thread_pool_size";
    public static final String STAGINGSCHEMAPOOLSIZE = "stagingSchemaPoolSize";
    public static final String KAFKA_CONFIG = "kafka_config";
    public static final String MQTT_CONFIG = "mqtt_config";
    public static final String CONTROL_TOPIC = "control_topic";
    public static final String STAGING_TABLES = "staging_tables";
    public static final String RESOURCES_PATH = "resources_path";
    public static final String SECURITY_PROTOCOL_TYPE = "SASL_SSL";
    public static final String MECHANISM = "GSSAPI";
    public static final String SERVICE_NAME = "kafka";
    public static final String STRINGDESERIALIZER = "org.apache.kafka.common.serialization.String.StringDeserializer";
    public static final String BYTEARRAYDESERIALIZER = "org.apache.kafka.common.serialization.Byte.ByteArrayDeserializer";
    public static final String FALSE = "false";
    public static final String TEMPLATE_CONFIG_DATAPLACE = "template_config_dataplace";
    public static final String TEMPLATE_CONFIG_CLOUD = "template_config_cloud";
    public static final String SOURCE_DATABASE_CONFIG = "source_database_config";
    public static final String IP_ADDRESS = "ip_address";
    public static final String DATAMAP = "dataMap :";
    public static final String RESOURCE_ID_IS = "Resource ID is: ";
    public static final String WAITING_FOR_STAGE_TO_ALLOCATE = "Waiting for stage to allocate task for ";
    public static final String TEMPLATEGROUP = "template_group";
    public static final String TEMPLATENAME = "template_name";
    public static final String INSERT_INFORM_SCHEMA = "insertInformSchema";
    public static final String INACTIVE_SOURCE = "inactivateSource";
    public static final String GET_SOURCES = "getSources";
    public static final String GET_BATCH_SOURCES = "getBatchSources";
    public static final String INSERT_DIRECTORY = "insertDirectory";
    public static final String INACTIVE_DIRECTORY = "inactivateDirectory";
    public static final String GET_CLOUD_SOURCES = "getCloudSources";
    public static final String GET_CREDS = "getCreds";
    public static final String REDIS_URL = "redis_url";
    public static final String REDISURL = "redisURL";
    public static final String JWT_PUBLIC_KEY = "JWTPublicKey";
    public static final String JWT_PRIVATE_KEY = "JWTPrivateKey";
    public static final String AUTH_CLASS = "auth_class";
    public static final String JWTTTL = "JWT_TTL";
    public static final String JWT_HANDLER = "JWTHandler";
    public static final String JWT_TTL = "jwt_ttl";
    public static final String USERID = "userid";
    public static final String USER_ID = "userId";
    public static final String USER_PASS = "userpass";
    public static final String HOST = "host";
    public static final String PORT = "port";
    public static final String ENDPOINT = "endpoint";
    public static final String AUTH_TOKEN_IS_NULL = "AuthToken is null";
    public static final String CREDENTIALS_ARE_NOT_APPROPRIATE = "Credentials :{} are not appropriate";
    public static final String SPECIFIED_PATH_IS_NOT_VALID = "specified path is not valid :";
    public static final String ERROR_FOR_FILE = "error for file : {} error {}";
    public static final String SHARE_IS_NULL = "Share is null : {}";
    public static final String STAGING_INSERT_FORMATS = "stagingInsert_Formats";
    public static final String STAGING_INSERT_ESTIMATED_ROWS = "stagingInsert_EstimatedRows";
    public static final String STAGING_INSERT_FILEMETADATA = "stagingInsert_FileMetadataFromSource";
    public static final String HIVE_MYSQL_JSON = "hive_mysql.json";
    public static final String HIVE_ORACLE_JSON = "hive_oracle.json";
    public static final String HIVE_POSTGRES_JSON = "hive_postgres.json";
    public static final String CREDENTIAL_DETAILS = "credential_details";
    public static final String JDBC_HIVE = "jdbc:hive";
    public static final String JDBC_ORACLE = "jdbc:oracle";
    public static final String JDBC_POSTGRESQL = "jdbc:postgresql";
    public static final String JDBC_MYSQL = "jdbc:mysql";
    public static final String STAGING_INSERT_TABLEMETADATA = "stagingInsert_TableMetadataFromSource";
    public static final String STAGING_INSERT_COLUMNMETADATA = "stagingInsert_ColumnsDataFromSource";
    public static final String MYSQL = "mysql";
    public static final String ATHENA = "athena";
    public static final String HIVE = "hive";
    public static final String FETCH_COLUMN_METADATA = "fetch_column_metadata";
    public static final String FETCHCOLUMNMETADATA = "fetchColumnMetadata";
    public static final String HEADER = "hasHeader";
    public static final String INCORRECT_SOURCE_DETAILS = "CRAWLING OF SOURCE IS DISABLED AS DETAILS OF THE SOURCE WITH DATAPLACE_ID : {} AND DATAPLACE_COMPONENT_TYPE_ID : {} ARE INAPPROPRIATE ";
    public static final String RELATIVE_PATH = "relativePath";
    public static final String ABSOLUTE_PATH = "absolutePath";
    public static final String TRUE = "true";
    public static final String KAFKA_PRODUCER_CONFIG = "kafka_producer_config";
    public static final String BOT_TASKS = "bot_tasks";
    public static final String INPUT_DATA = "input_data";
    public static final String OUTPUT_MESSAGE_TEMPLATES = "output_message_templates";
    public static final String BOOTSTRAP_BROKERS = "bootstrap_brokers";
    public static final String CONNECTED_TO_MQTT = "Connected to MQTTClient";
    public static final String CRAWLERS_SHUTDOWN = "Crawler has been shutdown";
    public static final String CONNECTED_TO_SHARE = "connected to share : {}";
    public static final String SOURCE_CONNECTION_NULL = "Source connection is null";
    public static final String DELIMITER_FILES_COMPLETE_SCAN = "delimiter_files_complete_scan";
    public static final String NUM_OF_ROWS_FOR_DELIMITER_FILES_SCAN = "num_of_rows_for_delimiter_files_scan";
    public static final String TOTAL_TIME_MILLISECONDS = "total time taken for the query: {} in milliseconds is :{}";
    public static final String TOTAL_TIME_MINUTES = "total time taken for the query: {} in minutes is :{}";


    //jdbc
    public static final String JDBC_DRIVER = "jdbc_driver";
    public static final String JDBC_DRIVER_POSTGRES = "jdbc_driver_postgres";
    public static final String JDBC_DRIVER_MYSQL = "jdbc_driver_mysql";
    public static final String CREDENTIALS_OCS = "credentials_alsc_ocs.json";
    public static final String CREDENTIALS_IXX = "credentials_alsc_ixx.json";
    public static final String CREDENTIALS_CSDW = "credentials_alsc_csdw.json";

    //vault
    public static final String VAULT_CONFIG_FILE = "vault_config.json";
    public static final String VAULT_ID = "vault_id";
    public static final String PASSWORD = "password";
    public static final String USER_NAME = "username";
    public static final String ISENCRYPTED = "isEncrypted";
    public static final String KEYS = "keys";
    public static final String JDBC_URL = "jdbc_url";
    public static final String JDBC_URL_2 = "jdbc_url_2";
    public static final String DIRECTORY = "directory";
    public static final String UDIRECTORY = "DIRECTORY";
    public static final String SCHEMANAME = "schemaName";
    public static final String LOCATION_PATH = "location_path";
    public static final String KEYTAB = "keytab";
    public static final String PRINCIPAL = "principal";
    public static final String TRUSTSTORE_PASSWORD = "truststore_password";
    public static final String TRUSTSTORE_PATH = "truststore_path";

    public static final String PRIVATEKEY = "privatekey";
    public static final String QUERY_TYPE = "query_type";
    public static final String TEMPLATE_GROUP = "templateGroup";
    public static final String MIN_CONNECTION_PER_PARTITION = "minConnectionsPerPartition";
    public static final String MAX_CONNECTION_PER_PARTITION = "maxConnectionsPerPartition";
    public static final String LEAK_DETECTION_THRESHOLD = "leakDetectionThreshold";
    public static final String ENCRYPTED_PASSWORD = "encrypted_password";
    public static final String YES = "Y";

    //configs
    public static final String CRAWLER_RULES = "crawlerRules";
    public static final String SOURCEATTRIBUTES = "sourceAttributes";

    //source
    public static final String SOURCE_TYPE = "source_type";
    public static final String COMPONENT_TYPE = "component_type";
    public static final String POSTGRES = "postgres";
    public static final String SQL_SERVER = "sql_server";
    public static final String ORACLE = "oracle";
    public static final String DB2 = "db2";
    public static final String TERADATA = "teradata";

    //query
    public static final String SELECT = "select";
    public static final String UPDATE = "update";
    public static final String SELECT_INSERT = "select_insert";
    public static final String SELECT_QUERY = "select_query";
    public static final String INSERT_QUERY = "insert_query";
    public static final String SOURCE_KOSH = "source_kosh";
    public static final String BATCHUPDATE = "batch_update";
    public static final String ROLLBACK = "rollback";
    public static final String CRAWLING = "crawling";
    public static final String CDC = "cdc";
    public static final String TEMPLATE_MAP = "template_map";
    public static final String DATA = "data";
    public static final String CONNECTION_TYPE = "connection_type";
    public static final String SOURCE = "source";
    public static final String SOURCE_CON = "source_con";
    public static final String DEPENDENT_TEMPLATE = "dependent_template";
    public static final String RESOURCE_ID = "resourceId";
    public static final String TABLE_NAME = "table_name";
    public static final String TABLELIST = "tablelist";
    public static final String CHECKTABLEEXISTS = "checkTableExists";
    public static final String GET_TABLES_LIST = "get_tables_list";

    //files
    public static final String CRAWLER_CONFIG = "crawler_config.json";
    public static final String STAGING_TABLES_FILE = "staging_tables.json";
    public static final String DROPTABLE = "dropTable";

    //logging
    public static final String LOGGING = "logging";
    public static final String LOG4J2 = "log4j2.xml";
    public static final String KAFKA_CONSUMER_SUBSCRIBE_TO_TOPIC = "kafka consumer subscribed to topic :{}";
    public static final String CONNECTING_TO_SOURCE = "connecting to source";
    public static final String MESSAGE_RECEIVED_TOPIC = "Message received topic ={},offset={},partition={},\n " +
            "decryptedMessage={}";
    public static final String CHECK_IF_TABLE_EXISTS_IN_STAGINGSCHEMA = "check if table exists in StagingSchema";
    public static final String CREATING_TABLE_IN_SCHEMA = "Creating Table In Schema";
    public static final String NO_TABLES_TO_DELETE = "No tables to delete";
    public static final String SOURCE_CONNECTION_IS_NULL = "Source connection is null";
    public static final String SOURCE_CONNECTION_OBJECT_ONE_IS_NULL = "Source connection object 1 is null";
    public static final String SOURCE_CONNECTION_OBJECT_TWO_IS_NULL = "Source connection object 2 is null";
    public static final String IS_NULL = " is null";
    public static final String NO_SOURCE_CONNECTION = "Crawling is disabled , So connection to source is not established";
    public static final String JDBC_URL_ERROR = "jdbc URL is not in correct format";
    public static final String STARTED_CRAWLING = "started crawling for the source,datastore_id";
    public static final String ENDED_CRAWLING = "Ended crawling:";
    public static final String CRAWLING_IS_DISABLED = "Crawling is disabled:";
    public static final String SOURCE_CONNECTION_OBJECT_IS_NULL = "Source Connection object is null";
    public static final String STARTED_ROLLBACK = "RESOURCES ALLOCATED :{}, AND STARTED ROLLBACK FOR DATAPLACE_ID:{}, DATAPLACE_COMPONENT_TYPE_ID :{}";
    public static final String ROLLBACK_DISABLED = "RollBack is disabled";
    public static final String RESOURCEID = "resourceId";
    public static final String CLASSNAME = "className";
    public static final String CONNECTION_PROFILES = "connection_profiles";
    public static final String CREDENTIALS = "credentials";
    public static final String ADDITIONAL_INFO = "additional_info";

    //switchs
    public static final String DOCRAWLING = "doCrawling";
    public static final String DOROLLBACK = "doRollBack";
    public static final String DOCDC = "doCDC";
    public static final String STARTED_DELTA_CRAWLING = "Started Delta Crawling";
    public static final String DATABASE_CRAWLING = "get_new_databases";
    public static final String ENDED_DELTA_CRAWLING = "Ended Delta Crawling";
    public static final String CRAWLING_DEPENDENCY = "crawling_dependency";
    public static final String CLIENT_CONFIGS = "clientConfigs";
    public static final String INVALID_QUERY_TYPE = "Invalid Query type";


    public static final String DATAPLACE_ID = "dataplace_id";
    public static final String DATAPLACE_COMPONENT_TYPE_ID = "dataplace_component_type_id";
    public static final String CREDENTIAL_ID = "credential_id";
    public static final String CREDENTIAL_TYPE_ID = "credential_type_id";

    public static final String POSTGRES_JDBC_DRIVER = "postgres_jdbc_driver";
    public static final String MYSQL_JDBC_DRIVER = "mysql_jdbc_driver";
    public static final String ORACLE_JDBC_DRIVER = "oracle_jdbc_driver";
    public static final String HIVE_JDBC_DRIVER = "hive_jdbc_driver";

    public static final String IS_ACTIVE = "is_active";
    public static final String IS_SENSITIVE = "is_sensitive";
    public static final String SCHEMA_NAME = "schema_name";
    public static final String SCHEMA_ID = "schema_id";
    public static final String SCHEMA_LIST = "schema_list";
    public static final String SCHEMA_ID_LIST = "schema_id_list";
    public static final String BATCH_ID = "batch_id";
    public static final String AUTH_CONFIG = "authBot_config";
    public static final String REDIS_CONFIG = "redis_config";
    public static final String JWT_CONFIG = "jwtConfig";
    public static final String USERDETAILS = "userdetails";

    //documentum
    public static final String RUN_CONFIGURATIONS = "run_configurations";
    public static final String WEBTOPURLS = "webtopurls.json";
    public static final String DOCUMENTUMJSON = "documentum.json";
    public static final String SOURCE_NAME = "sourceName";
    public static final String OWNER_NAME = "owner_name";
    public static final String OWNER_PERMIT = "owner_permit";
    public static final String GROUP_NAME = "group_name";
    public static final String GROUP_PERMIT = "group_permit";
    public static final String WORLD_PERMIT = "world_permit";
    public static final String ACL_DOMAIN = "acl_domain";
    public static final String ACL_NAME = "acl_name";
    public static final String R_OBJECT_TYPE = "r_obect_type";
    public static final String R_MODIFY_DATE = "r_modify_type";
    public static final String WEBTOPURL = "webtopurl";
    public static final String DOCBROKER = "docbroker";
    public static final String DOCBASE = "docbase";
    public static final String R_OBJECT_ID = "r_object_id";
    public static final String OBJECT_NAME = "object_name";
    public static final String R_CREATION_DATE = "r_creation_date";
    public static final String I_CHRONICAL_ID = "i_chronical_id";
    public static final String FOLDER_ID = "folder_id";
    public static final String A_CONTENT_TYPE = "a_content_type";
    public static final String R_FOLDER_PATH = "r_folder_path";
    public static final String R_FULL_CONTENT_SIZE = "r_full_content_size";
    public static final String USERNAME = "username";
    public static final String PASSWD = "passwd";
    public static final String ENCRYPTED_PASSWD = "encrypted_passwd";
    public static final String FLAG_YES = "Y";
    public static final String error = "Y";

    //ftp
    public static final String TEMPLATES = "templates";
    public static final String FTPJSON = "ftp_credentials.json";
    public static final String SFTPJSON = "sftp_credentials.json";
    public static final String FTP_ENCRYPTED_PASSWORD = "ftp_encrypted_password";
    public static final String SFTP_ENCRYPTED_PASSWORD = "sftp_encrypted_password";
    public static final String HOST_URL = "host_url";
    public static final String PROXY_ENABLED = "proxy_enabled";
    public static final String PROXY = "proxy";
    public static final String AT_THE_RATE = "@";
    public static final String BATCH_INSERT_FTP = "batch_insert_ftp";
    public static final String FTP = "ftp";
    public static final Character DELIMITER = '$';
    public static final String FORWARD_SLASH = "/";
    public static final String PRIVATE_KEY_FILE_PATH = "private_key_file_path";
    public static final String IS_ENCRYPTED = "isEncrypted";
    public static final String SOURCE_URL = "sourceUrl";
    public static final String PROXY_URL = "proxyUrl";

    //gdrive
    public static final String GDRIVE_JSON = "gdrive_credentials.json";
    public static final String GDRIVE_ENCRYPTED_PASSWD = "gdrive_encrypted_passwd";
    public static final String SYSTEM_NAME = "system_name";
    public static final String S3_URL = "s3_url";
    public static final String GDRIVE = "gdrive";
    public static final String OBJECTSTORE = "objectstore";
    public static final String REGEX = "*";
    public static final String BATCH_INSERT_GDRIVE = "batch_insert_gdrive";
    public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String SFTP = "sftp";
    public static final String ERROR = "errors";
    public static final String INSERT_ERROR = "insert_into_error_table";
    public static final String ERROR_MESSAGE = "error_message";

    //unix
    public static final String UNIX = "unix";
    public static final String FILESHARE = "FileShare";

    //smb
    public static final String SMB = "smb";
    public static final String DOMAIN = "domain";
    public static final String SMB_SCHEME = "smb://";
    public static final String SHARE_NAME = "share_name";

    //sharepoint
    public static final String SHAREPOINT = "sharepoint";
    public static final String FOLDER_GUID = "folder_guid";
    public static final String SITE_URL = "site_url";
    public static final String FILEDIRREF = "FileDirRef";
    public static final String SITE_DOMAIN = "site_domain";
    public static final String UNIQUEID = "UniqueId";
    public static final String SMTOTALFILESSTREAMSIZE = "SMTotalFileStreamSize";
    public static final String METADATA_URI = "__metadata.uri";
    public static final String HTTPS = "https://";
    public static final String FILE_TYPE = "File_x005f_x0020_x005f_Type";
    public static final String CREATED_DATE = "Created_x005f_x0020_x005f_Date";
    public static final String LAST_MODIFIED = "Last_x005f_x0020_x005f_Modified";

    //cifs
    public static final String CIFS = "cifs";
    public static final String FLAG_Y = "Y";
    //errors
    public static final String ERRORS = "errors";

    //rest
    public static final String REST_END_POINT = "rest_end_point";
    public static final String HTTP_RESPONSE = "http_response";
    public static final String REST_CONFIG = "rest_config";

    //vault
    public static final String VAULT_CLASSNAME = "vault_classname";
    public static final String VAULT_CONFIG = "vault_config";

    //loggers
    public static final String ESTABLISH_CONNECTION = "RESOURCE ALLOCATED : {},AND TRYING TO ESTABLISH SOURCE"
            + "CONNECTION FOR DATAPLACE_ID :{} , DATAPLACE_COMPONENT_TYPE_ID :{}";
    public static final String CRAWLING_COMPLETED = "RESOURCE ALLOCATED : {},AND COMPLETED CRAWLING FOR DATAPLACE_ID:" +
            " {}, DATAPLACE_COMPONENT_TYPE_ID :{}";
    public static final String CRAWLING_STARTED = "RESOURCE ALLOCATED : {},AND STARTED CRAWLING FOR DATAPLACE_ID:" +
            " {}, DATAPLACE_COMPONENT_TYPE_ID :{}";
    public static final String CRAWLING_DISABLED = "RESOURCE ALLOCATED :{},DISABLED CRAWLING FOR DATAPLACE_ID:{}," +
            "DATAPLACE_COMPONENT_TYPE_ID: {}";
    public static final String CDC_DISABLED = "RESOURCE ALLOCATED :{},DISABLED CDC FOR DATAPLACE_ID:{}," +
            "DATAPLACE_COMPONENT_TYPE_ID: {}";
    public static final String CDC_STARTED = "RESOURCE ALLOCATED :{},STARTED CDC FOR DATAPLACE_ID:{}," +
            "DATAPLACE_COMPONENT_TYPE_ID: {}";
    public static final String CDC_COMPLETED = "RESOURCE ALLOCATED :{},COMPLETED CDC FOR DATAPLACE_ID:{}," +
            "DATAPLACE_COMPONENT_TYPE_ID: {}";
    public static final String RESOURCE_RELEASED = "COMPLETED PROCESS, RETURNING RESOURCE TO POOL:{}";
    public static final String NULL_CONNECTION = "Source Connection is null";
    public static final String DATABASE_CONFIG_NULL = "database config is null";
    public static final String TEMPLATE_CONFIG_NULL = "template config is null";
    public static final String SOURCE_MAP_NULL = "source map is null";
    public static final String FAILED_TO_FETCH_MESSAGES = "Invalid crawler message";
    public static final String FAILED_TO_CHECK_TABLES_IN_STAGING_SCHEMA = "Failed to check tables in staging_schema";
    public static final String FAILED_START_THE_CRAWLER_WORLD_PROCESS = "Failed start the Crawler World process!";
    public static final String KEY_TEMPLATE_CONFIG = "template_config";
    public static final String TEMPLATE_NAME_TO_SELECT_NEW_DATABASES = "select_new_databases_from_cdr_db_entity";
    public static final String NOT_VALID_LOCATION_PATH = "Location path is not valid :{} ";


    //AWS
    public static final String ACCESS_KEY = "access_id";
    public static final String SECRET_KEY = "secret_access_key";
    public static final String REGION = "region";
    public static final String AWS_CREDENTIAL_PROVIDER_CLASS = "com.simba.athena.amazonaws.auth.SystemPropertiesCredentialsProvider";
    public static final String ACCESS_DENIED = "Access Denied for Bucket: {}";
    public static final String CSV = "csv";
    public static final String TSV = "tsv";
    public static final String AVRO = "avro";
    public static final String PARQUET = "parquet";
    public static final String FILE_SEPARATOR = "/";
    public static final String OBJECT = "object";
    public static final String ORDINAL_POSITION = "ordinal_position";
    public static final String COLUMN_NAME = "column_name";
    public static final String DATA_TYPE = "data_type";


    //GCP
    public static final String BUCKET = "bucket";
    public static final String BUCKET_NAME = "bucket_name";
    public static final String FETCH_STATS_TYPE = "fetch_stats_type";
    public static final String DIRECTORY_NAME = "directory_name";
    public static final String DIRECTORY_ID = "directory_id";
    public static final String DIRECTORY_PATH = "directory_path";
    public static final String PATH = "path";
    public static final String ROOT_LOCATION_PATH = "root_location_path";
    public static final String BLOB = "blob";
    public static final String FILE = "file";
    public static final String PROJECT_ID = "project_id";
    public static final String FILE_NAME = "file_name";
    public static final String FILE_PATH = "path";
    public static final String FILE_FORMAT = "file_format";
    public static final String FILE_LAST_MODIFIED = "lastmodified";
    public static final String FILE_OWNER_ID = "owner_id";
    public static final String FILE_SIZE = "size";
    public static final String COLUMN_FILE_ADDITIONAL_INFO = "additional_info";


    //ADLS1
    public static final String CONTAINER = "container";
    public static final String ACCOUNTFQDN = "accountFQDN";
    public static final String DATA_LAKE = "DATA_LAKE";
    public static final String DATA_LAKE_NAME = "data_lake_name";
    public static final String FOLDER = "FOLDER";
    public static final String AZURE_DATALAKE_STORE_NET = ".azuredatalakestore.net";
    public static final String CLIENTID = "client_id";
    public static final String AUTH_TOKEN_ENDPOINT = "token";
    public static final String CLIENT_KEY = "secret";
    public static final String ROOT_PATH = "/";
    public static final String ADL_PREFIX = "adl://";
    public static final String ADL_DIRECTORY = "DIRECTORY";
    public static final String IS_DIR = "is_dir";


    //Glue
    public static final String CREATE_TABLE_TS = "create_table_ts";
    public static final String MODIFY_TABLE_TS = "modify_table_ts";
    public static final String TABLE_OWNER = "table_owner";
    public static final String TABLE_SIZE = "table_size";
    public static final String TABLE_TYPE = "table_type";
    public static final String ESTIMATED_ROWS = "estimated_rows";
    public static final String IS_COMPRESSED = "is_compressed";
    public static final String COLUMN_COMMENT = "column_comment";
    public static final String RECORD_COUNT = "recordCount";
    public static final String COMPRESSION_TYPE = "compressionType";
    public static final String EXTERNAL_TABLE = "EXTERNAL_TABLE";
    public static final String NONE = "none";
    public static final String TABLE = "T";
    public static final String VIEW = "V";
    public static final char IS_NULLABLE = 'N';
    public static final String GLUE = "glue";

    //ADLS2

    public static final String ACCOUNT_KEY = "accountkey";
    public static final String ACCOUNT_NAME = "accountname";
    public static final String FILE_OWNER_NAME = "file_owner_name";
    public static final String FILE_MODIFIED_TIME = "lastmodified";
    public static final String BLOB_CORE_WINDOWS_NET = ".blob.core.windows.net/";


    //SFTP
    public static final String SFTP_SERVER_CONNECTED_STATUS = "sftp server is : {} and connected status : {} ";

    //Azure Blob
    public static final String CONTAINER_NAME = "container";
    public static final String CONNECTION_STRING = "connection_string";
    public static final String SAS_TOKEN = "SAS_token";
    public static final String BLOB_PREFIX = "wasbs://";
    public static final String AZURE_BLOB_WINDOWS_NET = ".blob.core.windows.net/";
    public static final String ACCOUNTNAME = "accountName";

    //Sharepoint
    public static final String SITE_ONLY="site_url";
    public static final String FILEREF="FileRef";
    public static final String FILELEAFREF="FileLeafRef";
    public static final String CLIENT_ID = "client_id";
    public static final String CLIENT_SECRET = "client_secret";
    public static final String IDENTIFIER = "identifier";

    public static final String STAGING_INSERT_FILESHAREPOINT_METADATA = "stagingInsert_FileSharePointMetadataFromSource";

}
