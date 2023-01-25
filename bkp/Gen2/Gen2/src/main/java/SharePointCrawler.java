import com.modak.spiderweb.common.CrawlerCommon;
//import com.modak.utility.DelimitedFileFormatUtil;
//import com.modak.utility.HashMapUtility;
//import com.modak.utility.SemiStructuredUtils;
//import com.modak.utility.TimeStampUtils;
//import com.modak.utility.connector.SharePointConnector;
import com.modak.utils.JSONUtils;
//import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
//import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * SharePointCrawler extends BaseCrawler and implements the abstract methods of BaseCrawler.
 * It connects to the source, establishes the connection, gets the metadata of files
 * and then populates the metadata in the tables related to metadata in kosh database.
 */
public class SharePointCrawler {
    protected static DelimitedFileFormatUtil delimitedFileFormatUtil;
    private final int BATCH_SIZE = 1000;
    private static String fetchCrawlType = null;
    private static boolean csv = false;
    private static boolean tsv = false;
    private static boolean avro = false;
    private static boolean delimiterFilesCompleteScan = false;
    private static int numOfRowsForDelimiterFilesScan = 100;
    private static List<Object[]> list_of_file_objects_array = new ArrayList<>();
    private static List<Object[]> list_of_column_objects_array = new ArrayList<>();
    private static final SharePointConnector sharePointConnector = new SharePointConnector();
    protected static HashMap<String, Object> sourceAttributes = new HashMap<>();
    protected HashMap<String, Object> crawlerRulesMap;

    //  private static final Logger logger = LogManager.getLogger(SharePointCrawler.class);

    /**
     * This method is used to establish source connection by getting the information regarding SharePoint account
     */
    public static void main(String[] args) throws Exception {
        SharePointCrawler sharePointCrawler = new SharePointCrawler();
        SemiStructuredUtils semiStructuredUtils =new SemiStructuredUtils();
        //
        String app_client_id = "d9c24804-0e49-4bc7-b782-1a0f0cc8d2c3";
        String app_client_secret = "ciDhZeCezMt1RCdd/EClniSxyoukRNArzotcFeC/pho=";
        String identifier = "00000003-0000-0ff1-ce00-000000000000";
        HashMap<String, Object> sourceInfo = HashMapUtility.getMap(sourceAttributes, CrawlerCommon.SOURCE_INFO_DETAILS);
        String site_url = "https://modakanalytics0.sharepoint.com/sites/nabu_crawling";
        String site_domain = "modakanalytics0.sharepoint.com";
        sourceAttributes.put(CrawlerCommon.PATH, site_url);
        sourceAttributes.put(CrawlerCommon.SITE_DOMAIN, site_domain);
        sharePointConnector.init(app_client_id, app_client_secret, identifier, site_url, site_domain);
        csv = true;
        tsv = true;
        avro = true;
        delimiterFilesCompleteScan = true;
        numOfRowsForDelimiterFilesScan = 100;
        //
        fetchCrawlType = "stagingInsert_FileMetadataFromSource22";


        //
        CloseableHttpClient httpclient = HttpClients.createDefault();
        InputStream inputStream;
        String dirPath = "/sites/nabu_crawling/Shared Documents/csvFiles/";
        String nameOfFile = "SpeedDating.csv";
        String fileFormat = "CSV";
        delimitedFileFormatUtil = new DelimitedFileFormatUtil();
        String delimiterFileFormat = null;
        if (fileFormat.equalsIgnoreCase(CrawlerCommon.CSV)) {
            System.out.println("File: " + nameOfFile);
            System.out.println("DIR: " + dirPath);
            inputStream = sharePointConnector.getFileAsStream(httpclient, dirPath, nameOfFile);
//            FileUtils.copyInputStreamToFile(inputStream, new File("src/main/resources/out.txt"));
//            inputStream=new FileInputStream(new File("src/main/resources/out.txt"));
//            List<HashMap<String, Object>> mapList = semiStructuredUtils.getCSVColMetadata_commonsCSV(inputStream,"csv");
//            System.out.println(mapList);

//            List<HashMap<String, Object>> listMap = semiStructuredUtils.getCSVAndTSVColMetadata(inputStream,"csv",true,10);
//            System.out.println(listMap);
//            delimiterFileFormat = delimitedFileFormatUtil.getCSVFormat(inputStream);
//            System.out.println(delimiterFileFormat);
            //

            System.out.println(semiStructuredUtils.getCSVAndTSVColMetadata(inputStream,"csv",true,10));
            sharePointCrawler.processDirectory();
        }
    }

    public void initSourceConnection() {
        System.out.println("initSourceConnection");
        try {
//                HashMap<String, Object> credentials = JSONUtils.jsonToMap(creds);
            String app_client_id = "d9c24804-0e49-4bc7-b782-1a0f0cc8d2c3";
            String app_client_secret = "ciDhZeCezMt1RCdd/EClniSxyoukRNArzotcFeC/pho=";
            String identifier = "00000003-0000-0ff1-ce00-000000000000";
            HashMap<String, Object> sourceInfo = HashMapUtility.getMap(sourceAttributes, CrawlerCommon.SOURCE_INFO_DETAILS);
            String site_url = "https://modakanalytics0.sharepoint.com/sites/nabu_crawling";
            String site_domain = "modakanalytics0.sharepoint.com";
            sourceAttributes.put(CrawlerCommon.PATH, site_url);
            sourceAttributes.put(CrawlerCommon.SITE_DOMAIN, site_domain);
            sharePointConnector.init(app_client_id, app_client_secret, identifier, site_url, site_domain);
            HashMap<String, Object> fetchColumnMetadata = HashMapUtility.getMap(sourceInfo, CrawlerCommon.FETCH_COLUMN_METADATA);
            csv = HashMapUtility.getBoolean(fetchColumnMetadata, CrawlerCommon.CSV);
            tsv = HashMapUtility.getBoolean(fetchColumnMetadata, CrawlerCommon.TSV);
            avro = HashMapUtility.getBoolean(fetchColumnMetadata, CrawlerCommon.AVRO);
            delimiterFilesCompleteScan = HashMapUtility.getBoolean(fetchColumnMetadata, CrawlerCommon.DELIMITER_FILES_COMPLETE_SCAN);
            numOfRowsForDelimiterFilesScan = HashMapUtility.getInteger(fetchColumnMetadata, CrawlerCommon.NUM_OF_ROWS_FOR_DELIMITER_FILES_SCAN);
//            } else {
////                logger.info(CrawlerCommon.NO_SOURCE_CONNECTION);
//            }
        } catch (Exception e) {
//            logger.error(ExceptionUtils.getStackTrace(e));
//            insertError(e);
        }
    }

    /**
     * This method is used to store the root location path in the kosh database
     */
//    @Override
//    public void connectToSource() {
//        this.getDirectories();
//    }

    /**
     * This method is used to get the metadata related to all the files in the source
     */

    public void doCrawling() {
        System.out.println("doCrawling");
        try {
//            Boolean doCrawling = (Boolean) HashMapUtility.get(runAttributes, CrawlerCommon.DOCRAWLING);
//            if (doCrawling && !isErrored) {
//                logger.info(CrawlerCommon.CRAWLING_STARTED, HashMapUtility.get(dataMap, CrawlerCommon.RESOURCE_ID),
//                        HashMapUtility.get(sourceAttributes, CrawlerCommon.DATAPLACE_ID),
//                        HashMapUtility.get(sourceAttributes, CrawlerCommon.DATAPLACE_COMPONENT_TYPE_ID));
            HashMap<String, Object> crawlingTemplateMap = HashMapUtility.getMap(crawlerRulesMap, CrawlerCommon.CRAWLING);
            String templateGroup = HashMapUtility.getString(crawlingTemplateMap, CrawlerCommon.TEMPLATE_GROUP);
            HashMap<String, Object> templateMap = HashMapUtility.getMap(crawlingTemplateMap, CrawlerCommon.TEMPLATE_MAP);
            for (String templateName : templateMap.keySet()) {
//                    String query = templateRenderer.renderTemplate(templateGroup, templateName, CrawlerCommon.DATA, dataMap);
                HashMap<String, Object> dependentMap = HashMapUtility.getMap(templateMap, templateName);
                String queryType = HashMapUtility.getString(dependentMap, CrawlerCommon.QUERY_TYPE);
                if (queryType.equalsIgnoreCase(CrawlerCommon.SELECT)) {
//                        List<Map<String, Object>> list_of_map = jdbcUtility.executeSelectQuery(query, dataSource.getConnection(), true, new MapListHandler());
//                        dataMap.put(templateName, list_of_map);
                } else if (queryType.equalsIgnoreCase(CrawlerCommon.UPDATE)) {
//                        jdbcUtility.executeUpdateQuery(query, dataSource.getConnection(), true);
                } else {
                    if (templateName.equals(CrawlerCommon.STAGING_INSERT_FILESHAREPOINT_METADATA)) {
                        fetchCrawlType = CrawlerCommon.STAGING_INSERT_FILEMETADATA;
                        processDirectory();
                        if (list_of_file_objects_array.size() > 0) {
//                                jdbcUtility.executeBatchUpdateQuery(list_of_file_objects_array, query, dataSource.getConnection(), true);
                            list_of_file_objects_array.clear();
                        }
                    } else {
                        fetchCrawlType = CrawlerCommon.STAGING_INSERT_COLUMNMETADATA;
                        processDirectory();
                        if (list_of_column_objects_array.size() > 0) {
//                                jdbcUtility.executeBatchUpdateQuery(list_of_column_objects_array, query, dataSource.getConnection(), true);
                            list_of_column_objects_array.clear();
                        }
                    }
                }
            }
//                logger.info(CrawlerCommon.CRAWLING_COMPLETED, HashMapUtility.get(dataMap, CrawlerCommon.RESOURCE_ID),
//                        HashMapUtility.get(sourceAttributes, CrawlerCommon.DATAPLACE_ID), HashMapUtility.get(sourceAttributes, CrawlerCommon.DATAPLACE_COMPONENT_TYPE_ID));
//            } else {
//                logger.info(CrawlerCommon.CRAWLING_DISABLED, HashMapUtility.get(dataMap, CrawlerCommon.RESOURCE_ID),
//                        HashMapUtility.get(sourceAttributes, CrawlerCommon.DATAPLACE_ID),
//                        HashMapUtility.get(sourceAttributes, CrawlerCommon.DATAPLACE_COMPONENT_TYPE_ID));
//            }
        } catch (Exception e) {
//            logger.error(ExceptionUtils.getStackTrace(e));
//            insertError(e);
        }
    }


    /**
     * This method is used to list all files within each folder
     *
     * @throws Exception if it fails to iterate files
     */
    public void processDirectory() throws Exception {
        System.out.println("processDirectory");
        try {
            for (HashMap<String, Object> folderDetails : sharePointConnector.listAllFolders()) {
                System.out.println("FOLDER_DETAILS: " + folderDetails);
                String guid = HashMapUtility.getString(folderDetails, CrawlerCommon.FOLDER_GUID);
                ArrayList<String> filesUrl = sharePointConnector.getFilesUrlOfAFolder(guid);
                if (filesUrl != null) {
                    for (String fileUrl : filesUrl) {
                        System.out.println("FILE_DETAILS: " + fileUrl);
                        HashMap<String, Object> fileDetailsMap = sharePointConnector.getFileDetails(fileUrl);
                        System.out.println("HELL" + fileDetailsMap);
                        System.out.println(("TEDT" + HashMapUtility.get(fileDetailsMap, CrawlerCommon.FILE_TYPE)));
                        if (HashMapUtility.get(fileDetailsMap, CrawlerCommon.FILE_TYPE) != "") {
                            System.out.println("TEST24");
                            if (fetchCrawlType.equals(CrawlerCommon.STAGING_INSERT_FILEMETADATA)) {
                                System.out.println("TEST");
                                Object[] obj = processFile(fileDetailsMap);
                                if (obj != null) {
                                    list_of_file_objects_array.add(obj);
                                    if (list_of_file_objects_array.size() >= BATCH_SIZE) {
//                                        jdbcUtility.executeBatchUpdateQuery(list_of_file_objects_array, query, dataSource.getConnection(), true);
                                        list_of_file_objects_array.clear();
                                    }
                                }
                            } else {
                                String fileFormat = HashMapUtility.getString(fileDetailsMap, CrawlerCommon.FILE_TYPE);
                                if (fileFormat.equalsIgnoreCase(CrawlerCommon.TSV) || fileFormat.equalsIgnoreCase(CrawlerCommon.CSV) || fileFormat.equalsIgnoreCase(CrawlerCommon.AVRO)) {
                                    processColumnMetadata(fileDetailsMap);
                                }
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
//            logger.error(ExceptionUtils.getStackTrace(e));
//            insertError(e);
            throw new Exception(e);
        }
    }

    /**
     * This method is used to get file metadata
     *
     * @param fileDetailsMap consists of the file related information
     * @return file metadata as object array
     * @throws Exception if it fails to get file metadata
     */
    public Object[] processFile(HashMap<String, Object> fileDetailsMap) throws Exception {
        System.out.println("processFile");
        CloseableHttpClient httpclient = HttpClients.createDefault();
        InputStream inputStream;
        try {
            String dirPath = HashMapUtility.getString(fileDetailsMap, CrawlerCommon.FILEDIRREF);
            String nameOfFile = HashMapUtility.getString(fileDetailsMap, CrawlerCommon.FILELEAFREF);
            String fileFormat = HashMapUtility.getString(fileDetailsMap, CrawlerCommon.FILE_TYPE);
            delimitedFileFormatUtil = new DelimitedFileFormatUtil();
            String delimiterFileFormat = null;
            if (fileFormat.equalsIgnoreCase(CrawlerCommon.CSV)) {
                System.out.println("File: " + nameOfFile);
                System.out.println("DIR: " + dirPath);
                inputStream = sharePointConnector.getFileAsStream(httpclient, dirPath, nameOfFile);
                delimiterFileFormat = delimitedFileFormatUtil.getCSVFormat(inputStream);

            } else if (fileFormat.equalsIgnoreCase(CrawlerCommon.TSV)) {
                inputStream = sharePointConnector.getFileAsStream(httpclient, dirPath, nameOfFile);
                delimiterFileFormat = delimitedFileFormatUtil.getTSVFormat(inputStream);
            }

            System.out.println("FILEREF: " + HashMapUtility.get(fileDetailsMap, CrawlerCommon.FILEREF));
            return new Object[]{
//                    sourceAttributes.get(CrawlerCommon.DATAPLACE_ID),
//                    sourceAttributes.get(CrawlerCommon.DATAPLACE_COMPONENT_TYPE_ID),
//                    sourceAttributes.get(CrawlerCommon.DIRECTORY_ID),
                    CrawlerCommon.SHAREPOINT,
                    HashMapUtility.get(fileDetailsMap, CrawlerCommon.FILEDIRREF),
                    HashMapUtility.get(fileDetailsMap, CrawlerCommon.FILEREF),
                    HashMapUtility.get(fileDetailsMap, CrawlerCommon.FILELEAFREF),
                    CrawlerCommon.HTTPS + sourceAttributes.get(CrawlerCommon.SITE_DOMAIN) + HashMapUtility.get(fileDetailsMap, CrawlerCommon.FILEREF),
                    UUID.fromString(HashMapUtility.get(fileDetailsMap, CrawlerCommon.UNIQUEID).toString()),
                    HashMapUtility.get(fileDetailsMap, CrawlerCommon.FILE_TYPE),
                    Integer.parseInt(HashMapUtility.get(fileDetailsMap, CrawlerCommon.SMTOTALFILESSTREAMSIZE).toString()),
                    HashMapUtility.get(fileDetailsMap, CrawlerCommon.METADATA_URI),
                    TimeStampUtils.getTimestamp(HashMapUtility.get(fileDetailsMap, CrawlerCommon.CREATED_DATE).toString()),
                    TimeStampUtils.getTimestamp(HashMapUtility.get(fileDetailsMap, CrawlerCommon.LAST_MODIFIED).toString()),
                    delimiterFileFormat
            };

        } catch (Exception e) {
//            logger.error(ExceptionUtils.getStackTrace(e));
//            insertError(e);
            throw new Exception(e);
        }
    }

    /**
     * This method is used to get column metadata of file
     *
     * @param fileDetailsMap consists of the file related information
     * @throws Exception if it fails to get column metadata
     */
    private void processColumnMetadata(HashMap<String, Object> fileDetailsMap) throws Exception {
        System.out.println("processColumnMetadata");
        System.out.println("File Name: "+HashMapUtility.getString(fileDetailsMap, CrawlerCommon.FILELEAFREF));
        CloseableHttpClient httpclient = HttpClients.createDefault();
        InputStream inputStream;
        try {
            String fileFormat = HashMapUtility.getString(fileDetailsMap, CrawlerCommon.FILE_TYPE);
            String dirPath = HashMapUtility.getString(fileDetailsMap, CrawlerCommon.FILEDIRREF);
            String nameOfFile = HashMapUtility.getString(fileDetailsMap, CrawlerCommon.FILELEAFREF);
            String absolutePath = CrawlerCommon.HTTPS + sourceAttributes.get(CrawlerCommon.SITE_DOMAIN) + HashMapUtility.get(fileDetailsMap, CrawlerCommon.FILEREF);
            String delimiterFileFormat;
            if (fileFormat.equalsIgnoreCase(CrawlerCommon.CSV) && csv) {
                inputStream = sharePointConnector.getFileAsStream(httpclient, dirPath, nameOfFile);
                delimiterFileFormat = delimitedFileFormatUtil.getCSVFormat(inputStream);
                HashMap<String, Object> delimiterFileFormatMap = JSONUtils.jsonToMap(delimiterFileFormat);
                boolean header = (boolean) HashMapUtility.get(delimiterFileFormatMap, CrawlerCommon.HEADER);
                if (header) {
                    System.out.println(getCSVAndTSVMetadata(dirPath, nameOfFile, absolutePath));
                }
            } else if (fileFormat.equalsIgnoreCase(CrawlerCommon.TSV) && tsv) {
                inputStream = sharePointConnector.getFileAsStream(httpclient, dirPath, nameOfFile);
                delimiterFileFormat = delimitedFileFormatUtil.getTSVFormat(inputStream);
                HashMap<String, Object> delimiterFileFormatMap = JSONUtils.jsonToMap(delimiterFileFormat);
                boolean header = (boolean) HashMapUtility.get(delimiterFileFormatMap, CrawlerCommon.HEADER);
                if (header) {
                    list_of_column_objects_array.addAll(getCSVAndTSVMetadata(dirPath, nameOfFile, absolutePath));
                }
            } else if (fileFormat.equalsIgnoreCase(CrawlerCommon.AVRO) && avro) {
                list_of_column_objects_array.addAll(getAvroMetadata(dirPath, nameOfFile, absolutePath));
            }
            if (list_of_column_objects_array.size() >= BATCH_SIZE) {
//                jdbcUtility.executeBatchUpdateQuery(list_of_column_objects_array, query, dataSource.getConnection(), true);
                list_of_column_objects_array.clear();
            }
        } catch (Exception e) {
//            logger.error(ExceptionUtils.getStackTrace(e));
//            insertError(e);
            throw new Exception(e);
        }
    }

    /**
     * This method is used to get column metadata of avro file
     *
     * @param nameOfFile   consists of the name of avro file
     * @param dirPath      consists of path to the directory in which the file is present
     * @param absolutePath consists of absolute file path
     * @return column metadata as list of object array
     * @throws Exception if it fails to get column metadata of avro file
     */
    private List<Object[]> getAvroMetadata(String dirPath, String nameOfFile, String absolutePath) throws Exception {
        InputStream inputStream = null;
        List<Object[]> list_of_file_objects_array = new ArrayList<>();
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            SemiStructuredUtils semiStructuredUtils = new SemiStructuredUtils();
            inputStream = sharePointConnector.getFileAsStream(httpclient, dirPath, nameOfFile);
            List<HashMap<String, Object>> listMap = semiStructuredUtils.getAvroColMetadata(inputStream);
            for (HashMap<String, Object> map : listMap) {
                Object[] obj = {
//                        sourceAttributes.get(CrawlerCommon.DATAPLACE_ID),
//                        sourceAttributes.get(CrawlerCommon.DATAPLACE_COMPONENT_TYPE_ID),
//                        sourceAttributes.get(CrawlerCommon.DIRECTORY_ID),
                        nameOfFile,
                        map.get(CrawlerCommon.COLUMN_NAME),
                        map.get(CrawlerCommon.DATA_TYPE),
                        absolutePath,
                        null,
                        null,
                        null,
                        null,
                        map.get(CrawlerCommon.ORDINAL_POSITION),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                };
                list_of_file_objects_array.add(obj);
            }
            return list_of_file_objects_array;
        } catch (Exception e) {
//            logger.error(ExceptionUtils.getStackTrace(e));
//            insertError(e);
            throw new Exception(e);
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException ex) {
//                logger.error(ExceptionUtils.getStackTrace(ex));
            }
        }
    }

    /**
     * This method is used to get column metadata of delimiter file
     *
     * @param nameOfFile   consists of the name of delimiter file
     * @param dirPath      consists of path to the directory in which file present
     * @param absolutePath consists of absolute file path
     * @return column metadata as list of object array
     * @throws Exception if it fails to get column metadata
     */
    private List<Object[]> getCSVAndTSVMetadata(String dirPath, String nameOfFile, String absolutePath) throws Exception {
        InputStream inputStream = null;
        try {
            SemiStructuredUtils semiStructuredUtils = new SemiStructuredUtils();
            List<Object[]> list_of_file_objects_array = new ArrayList<>();
            CloseableHttpClient httpclient = HttpClients.createDefault();
            String fileFormat = FilenameUtils.getBaseName(nameOfFile);
            inputStream = sharePointConnector.getFileAsStream(httpclient, dirPath, nameOfFile);
            List<HashMap<String, Object>> listMap = semiStructuredUtils.getCSVAndTSVColMetadata(inputStream, fileFormat, delimiterFilesCompleteScan, numOfRowsForDelimiterFilesScan);
            for (HashMap<String, Object> map : listMap) {
                Object[] obj = {
//                        sourceAttributes.get(CrawlerCommon.DATAPLACE_ID),
//                        sourceAttributes.get(CrawlerCommon.DATAPLACE_COMPONENT_TYPE_ID),
//                        sourceAttributes.get(CrawlerCommon.DIRECTORY_ID),
                        nameOfFile,
                        map.get(CrawlerCommon.COLUMN_NAME),
                        map.get(CrawlerCommon.DATA_TYPE),
                        absolutePath,
                        null,
                        null,
                        null,
                        null,
                        map.get(CrawlerCommon.ORDINAL_POSITION),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                };
                list_of_file_objects_array.add(obj);
            }
            return list_of_file_objects_array;
        } catch (Exception e) {
//            logger.error(ExceptionUtils.getStackTrace(e));
//            insertError(e);
            throw new Exception(e);
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException ex) {
//                logger.error(ExceptionUtils.getStackTrace(ex));
            }
        }
    }

    /**
     * This method is used to close source connection
     */

    public void closeResources() {
    }
}
