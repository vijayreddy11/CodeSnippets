

//import com.modak.utility.HashMapUtility;
//import com.modak.utility.RestUtility;
//import com.modak.utility.UtilityCommon;
//import com.modak.utility.json.JSONUtility;
import org.apache.http.NameValuePair;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class SharePointConnector {
    private String app_client_id = null;
    private String app_client_secret = null;
    private String identifier = null;
    private String site_url = null;
    private String site_domain = null;
    private String tenant_id = null;
    private Long tokenExpiryTime = null;
    private String accessToken = null;
//    private static final Logger logger = LogManager.getLogger(SharePointConnector.class);

    /**
     * Initializes utility code with credentials
     *
     * @param app_client_id     app client id
     * @param app_client_secret app client secret
     * @param identifier        app identifier
     * @param site_url          sharePoint site url
     * @param site_domain       sharePoint site domain
     * @throws Exception fails to int with credentials
     */
    public void init(String app_client_id, String app_client_secret, String identifier, String site_url, String site_domain) throws Exception {
        this.app_client_id = app_client_id;
        this.app_client_secret = app_client_secret;
        this.identifier = identifier;
        this.site_url = site_url;
        this.site_domain = site_domain;
        if (app_client_id == null || app_client_secret == null || site_url == null || site_domain == null) {
            throw new Exception(UtilityCommon.INVALID_APP_CREDENTIALS_EXP_MSG);
        }
        this.tenant_id = getTenantId();
        generateAndSetNewAccessToken();
    }

    /**
     * Generates tenantId for sharePoint
     *
     * @return tenantId
     * @throws Exception when unable to get tenantId
     */
    private String getTenantId() throws Exception {
        String tenant_id_request_url = site_url + UtilityCommon.SP_TENANT_ID_URl;
        String tenantId = null;
        RestUtility restUtilities = new RestUtility();
        HashMap<String, String> headers = new HashMap<>();
        headers.put(UtilityCommon.HEADER_AUTHORIZATION, UtilityCommon.HEADER_BEARER);
        headers.put(UtilityCommon.HEADER_CACHE_CONTROL, UtilityCommon.HEADER_NO_CACHE);
        headers.put(UtilityCommon.HEADER_CONTENT_TYPE, UtilityCommon.HEADER_APPLICATION_JSON);
        try {
            HashMap<String, Object> responseMap = restUtilities.getRequest(tenant_id_request_url, headers);
            int responseCode = restUtilities.extractStatusCode(responseMap);
            if (responseCode == 401) {
                String headerResponse = restUtilities.extractHeadersFromResponse(responseMap, UtilityCommon.SP_WWW_AUTHENTICATE);
                String realm = headerResponse.split(",")[0].split("=")[1];
                tenantId = realm.substring(1, realm.length() - 1);
            }
        } catch (Exception e) {
   //         logger.error(UtilityCommon.FAILED_TO_GET_TENANT_ID_EXP_MSG, e);
            throw new Exception(UtilityCommon.FAILED_TO_GET_TENANT_ID_EXP_MSG + e);
        }
        return tenantId;
    }

    /**
     * Gets access token when the token is expired
     *
     * @return access token
     * @throws Exception when unable to get access token
     */
    private String getAccessToken() throws Exception {
        if (accessToken == null || tokenExpiryTime == null || System.currentTimeMillis() < tokenExpiryTime) {
            generateAndSetNewAccessToken();
        }
        return accessToken;
    }

    /**
     * Generates new access token
     *
     * @throws Exception when unable to generate new access token.
     */
    private void generateAndSetNewAccessToken() throws Exception {
        if (tenant_id != null) {
            String accessTokenPost_Url = UtilityCommon.SP_ACCESS_TOKEN_URL_PREFIX + tenant_id + UtilityCommon.SP_ACCESS_TOKEN_URL_SUFFIX;
            RestUtility restUtilities = new RestUtility();
            HashMap<String, String> headers = new HashMap<>();
            headers.put(UtilityCommon.HEADER_AUTHORIZATION, UtilityCommon.HEADER_BEARER);
            headers.put(UtilityCommon.HEADER_CACHE_CONTROL, UtilityCommon.HEADER_NO_CACHE);
            headers.put(UtilityCommon.HEADER_CONTENT_TYPE, UtilityCommon.HEADER_X_REQUESTED_BY_CONTENT_TYPE);
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair(UtilityCommon.GRANT_TYPE, UtilityCommon.CLIENT_CREDENTIALS));
            params.add(new BasicNameValuePair(UtilityCommon.CLIENT_ID, app_client_id + "@" + tenant_id)); //ClientID@TenantID
            params.add(new BasicNameValuePair(UtilityCommon.CLIENT_SECRET, app_client_secret));
            params.add(new BasicNameValuePair(UtilityCommon.RESOURCE, identifier + "/" + site_domain + "@" + tenant_id)); //resource/SiteDomain@TenantID
            HashMap<String, Object> responseMap = restUtilities.postRequest(accessTokenPost_Url, params, headers);
            int statusCode = restUtilities.extractStatusCode(responseMap);
            if (statusCode != 200) {
                throw new Exception(UtilityCommon.FAILED_TO_GET_NEW_TOKEN_EXP_MSG + responseMap);
            }
            String responseAsJsonString = restUtilities.extractResponseString(responseMap);
            HashMap<String, Object> responseAsDataMap = JSONUtility.jsonToMap(responseAsJsonString);
            accessToken = responseAsDataMap.get(UtilityCommon.ACCESS_TOKEN).toString();
            String expiryTimeAsString = responseAsDataMap.get(UtilityCommon.ACCESS_TOKEN_EXPIRES_ON).toString();
            tokenExpiryTime = Long.valueOf(expiryTimeAsString) - TimeUnit.MINUTES.toMillis(5L); // max time - 5min as expiry time
        } else {
            throw new Exception(UtilityCommon.FAILED_TO_GET_NEW_TOKEN_EXP_MSG + UtilityCommon.TENANT_ID_IS_NULL_EXP_MSG);
        }
    }

    /**
     * @return all folders for a given site
     * @throws Exception failed to retrieve folder list
     */
    public ArrayList<HashMap<String, Object>> listAllFolders() throws Exception {
        ArrayList<HashMap<String, Object>> foldersDetailsMap = new ArrayList<>();
        String allFoldersLists = site_url + UtilityCommon.SP_LIST_FOLDER_URL;
        RestUtility restUtilities = new RestUtility();
        HashMap<String, String> headers = getCommonGetRequestHeaders();
        HashMap<String, Object> responseMap = restUtilities.getRequest(allFoldersLists, headers);
        int statusCode = restUtilities.extractStatusCode(responseMap);
        if (statusCode != 200) {
            throw new Exception(UtilityCommon.RESPONSE_FAILED + responseMap);
        }
        String responseAsJsonString = restUtilities.extractResponseString(responseMap);
        HashMap<String, Object> responseDataMap = JSONUtility.jsonToMap(responseAsJsonString);
        if (responseDataMap != null) {
            ArrayList<HashMap<String, Object>> foldersListMap = HashMapUtility.getArrayList(responseDataMap, UtilityCommon.SP_KEY_D_RESULTS);
            for (HashMap<String, Object> folderMap : foldersListMap) {
                HashMap<String, Object> folderDetailMap = new HashMap<>();
                Integer folderCode = (Integer) HashMapUtility.get(folderMap, UtilityCommon.SP_KEY_BASE_TEMPLATE);
                if (folderCode == 101) {
                    String folderName = HashMapUtility.getString(folderMap, UtilityCommon.SP_KEY_Title);
                    String folderGUID = HashMapUtility.getString(folderMap, UtilityCommon.SP_KEY_ID);
                    String folderUrl = HashMapUtility.getString(folderMap, UtilityCommon.SP_ITEMS_DEFERRED_URI);
                    folderDetailMap.put(UtilityCommon.FOLDER_NAME, folderName);
                    folderDetailMap.put(UtilityCommon.FOLDER_ITEMS_URL, folderUrl);
                    folderDetailMap.put(UtilityCommon.FOLDER_GUID, folderGUID);
                    foldersDetailsMap.add(folderDetailMap);
                }
            }
        }
        return foldersDetailsMap;
    }


    /**
     * Get all the files ref urls for folder in sharePoint
     *
     * @param folderGuid folder unique id used to get all files under folder
     * @return all file url for a given folder
     * @throws Exception fails to retrieve
     */
    public ArrayList<String> getFilesUrlOfAFolder(String folderGuid) throws Exception {
        ArrayList<String> filesInfoUrl = new ArrayList<>();
        String listsFileUrl = site_url + UtilityCommon.SP_LIST_FOLDER_URL + "(guid'" + folderGuid + "')/Items";
        RestUtility restUtilities = new RestUtility();
        HashMap<String, String> headers = getCommonGetRequestHeaders();
        HashMap<String, Object> responseMap = restUtilities.getRequest(listsFileUrl, headers);
        int statusCode = restUtilities.extractStatusCode(responseMap);
        if (statusCode != 200) {
            throw new Exception(UtilityCommon.RESPONSE_FAILED + responseMap);
        }
        String responseAsJsonString = restUtilities.extractResponseString(responseMap);
        HashMap<String, Object> responseDataMap = JSONUtility.jsonToMap(responseAsJsonString);
        if (responseDataMap != null) {
            ArrayList<HashMap<String, Object>> resultsArray = HashMapUtility.getArrayList(responseDataMap, UtilityCommon.SP_KEY_D_RESULTS);
            if (resultsArray != null) {
                for (HashMap<String, Object> resultArray : resultsArray) {
                    String fileInfoUrl = HashMapUtility.getString(resultArray, UtilityCommon.SP_FIELDVALUESASTEXT);
                    filesInfoUrl.add(fileInfoUrl);
                }
            }
            String nextUrl = HashMapUtility.getString(responseDataMap, UtilityCommon.SP_D_NEXT);
            if (nextUrl != null) {
                ArrayList<String> newFilesList = getFilesUrlOfNextPage(nextUrl);
                filesInfoUrl.addAll(newFilesList);
            }
        }
        return filesInfoUrl;
    }

    /**
     * Get next set urls in folder of next page
     *
     * @param nextPageUrl next page urls in folder files to be retrieved
     * @return next set urls in folder of next page
     * @throws Exception fails to retrieve
     */
    public ArrayList<String> getFilesUrlOfNextPage(String nextPageUrl) throws Exception {
        ArrayList<String> filesInfoUrl = new ArrayList<>();
        RestUtility restUtilities = new RestUtility();
        HashMap<String, String> headers = getCommonGetRequestHeaders();
        HashMap<String, Object> responseMap = restUtilities.getRequest(nextPageUrl, headers);
        int statusCode = restUtilities.extractStatusCode(responseMap);
        if (statusCode != 200) {
            throw new Exception(UtilityCommon.RESPONSE_FAILED + responseMap);
        }
        String responseAsJsonString = restUtilities.extractResponseString(responseMap);
        HashMap<String, Object> responseDataMap = JSONUtility.jsonToMap(responseAsJsonString);
        if (responseDataMap != null) {
            ArrayList<HashMap<String, Object>> resultsArray = HashMapUtility.getArrayList(responseDataMap, UtilityCommon.SP_KEY_D_RESULTS);
            if (resultsArray != null) {
                for (HashMap<String, Object> resultArray : resultsArray) {
                    String fileInfoUrl = HashMapUtility.getString(resultArray, UtilityCommon.SP_DEFERRED_URI);
                    filesInfoUrl.add(fileInfoUrl);
                }
            }
        }
        String nextUrl = HashMapUtility.getString(responseDataMap, UtilityCommon.SP_KEY_NEXT);
        if (nextUrl != null) {
            ArrayList<String> newFilesList = getFilesUrlOfNextPage(nextUrl);
            filesInfoUrl.addAll(newFilesList);
        }
        return filesInfoUrl;
    }

    /**
     * Get file metadata for a given file
     *
     * @param fileInfoUrl sharePoint file url
     * @return fileMetadata as hashMap
     * @throws Exception failed to retrieve
     */
    public HashMap<String, Object> getFileDetails(String fileInfoUrl) throws Exception {
        if (fileInfoUrl != null) {
            RestUtility restUtilities = new RestUtility();
            HashMap<String, String> headers = getCommonGetRequestHeaders();
            HashMap<String, Object> responseMap = restUtilities.getRequest(fileInfoUrl, headers);
            int statusCode = restUtilities.extractStatusCode(responseMap);
            if (statusCode != 200) {
                throw new Exception(UtilityCommon.RESPONSE_FAILED + responseMap);
            }
            String responseAsJsonString = restUtilities.extractResponseString(responseMap);
            HashMap<String, Object> fileDetailsMapResponse = JSONUtility.jsonToMap(responseAsJsonString);
            HashMap<String, Object> fileDetailsInfoMap = HashMapUtility.getMap(fileDetailsMapResponse, UtilityCommon.SP_KEY_D);
            return fileDetailsInfoMap;
        }
        return null;
    }

    /**
     * @return common request headers used for rest request for sharePoint urls
     * @throws Exception failed to configure headers
     */
    private HashMap<String, String> getCommonGetRequestHeaders() throws Exception {
        HashMap<String, String> headers = new HashMap<>();
        headers.put(UtilityCommon.HEADER_ACCEPT, UtilityCommon.HEADER_ACCEPT_JSON_VERB);
        headers.put(UtilityCommon.HEADER_AUTHORIZATION, UtilityCommon.HEADER_BEARER_SPC + getAccessToken());
        headers.put(UtilityCommon.HEADER_CACHE_CONTROL, UtilityCommon.HEADER_NO_CACHE);
        return headers;
    }

    /**
     * This method is used to retrieve the sharePoint file as fetcher
     *
     * @param client   CloseableHttpClient user for rest request to be closed after completing task
     * @param dir      sharePoint file dir
     * @param fileName sharePint fileName
     * @return file as input fetcher
     * @throws Exception fails to retrieve file as fetcher
     */
    public InputStream getFileAsStream(CloseableHttpClient client, String dir, String fileName) throws Exception {
        RestUtility restUtilities = new RestUtility();
        String url2 = site_url + UtilityCommon.SP_FILE_STREAM_URL + "('" + dir + "')/Files('" + fileName + "')/$value";
        URL url = new URL(url2);
        URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), URLDecoder.decode(url.getPath(), UtilityCommon.DEFAULT_CHARSET), "", url.getRef());
        String cleanedUrl = uri.toURL().toString();
        return restUtilities.getInputStream(cleanedUrl, client, getCommonGetRequestHeaders());
    }
}
