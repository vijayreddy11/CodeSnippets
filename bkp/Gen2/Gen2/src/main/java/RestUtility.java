import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.*;

public class RestUtility {
    private static final Logger logger = LoggerFactory.getLogger(RestUtility.class);

    public HashMap<String, Object> getResponseWithBasicAuth(String url, String userName, String password,
                                                            HashMap<String, String> optionalHeaders) throws Exception {
        return executeGetRequest(url, buildClientWithBasicAuth(userName, password), optionalHeaders);
    }

    public HashMap<String, Object> getRequest(String url, HashMap<String, String> optionalHeaders)
            throws Exception {
        return executeGetRequest(url, buildClient(), optionalHeaders);
    }

    public HashMap<String, Object> postRequest(String url, String postEntity, HashMap<String, String> optionalHeaders)
            throws Exception {
        return executePostRequest(url, buildClient(), postEntity, optionalHeaders);
    }

    public HashMap<String, Object> postRequest(String url, List<NameValuePair> requestParams,
                                               HashMap<String, String> optionalHeaders) throws Exception {
        try (CloseableHttpClient httpClient = buildClient()) {
            HttpPost httpPost = new HttpPost(url);
            logger.trace("Executing request : " + httpPost.getRequestLine());
            if (requestParams != null) {
                httpPost.setEntity(new UrlEncodedFormEntity(requestParams, "UTF-8"));
            }
            HashMap<String, String> headersMap = Optional.ofNullable(optionalHeaders).orElse(defaultHeaders());
            for (Map.Entry<String, String> entry : headersMap.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity entity = httpResponse.getEntity();
            logger.trace("HTTP Response {} ", httpResponse);
            String response = entity != null ? EntityUtils.toString(entity) : null;
            HashMap<String, Object> outputMap = new HashMap();
            outputMap.put(UtilityCommon.HTTP_RESPONSE, response);
            outputMap.put(UtilityCommon.STATUS, httpResponse.getStatusLine().getStatusCode());
            outputMap.put(UtilityCommon.HTTP_HEADERS, Arrays.asList(httpResponse.getAllHeaders()));
            return outputMap;
        }
    }

    public HashMap<String, Object> postRequestWithBasicAuth(String url, String userName,
                                                            String password, String postEntity,
                                                            HashMap<String, String> optionalHeaders) throws Exception {
        return executePostRequest(url, buildClientWithBasicAuth(userName, password), postEntity, optionalHeaders);
    }

    public HashMap<String, Object> putRequestWithBasicAuth(String url, String userName,
                                                           String password, String putEntity,
                                                           HashMap<String, String> optionalHeaders) throws Exception {
        return executePutRequest(url, buildClientWithBasicAuth(userName, password), putEntity, optionalHeaders);
    }

    public HashMap<String, Object> putRequest(String url, String putEntity, HashMap<String, String> optionalHeaders)
            throws Exception {
        return executePutRequest(url, buildClient(), putEntity, optionalHeaders);
    }

    public HashMap<String, Object> deleteRequestWithBasicAuth(String url, String userName, String password,
                                                              HashMap<String, String> optionalHeaders) throws Exception {
        return executeDeleteRequest(url, buildClientWithBasicAuth(userName, password), optionalHeaders);
    }

    public HashMap<String, Object> deleteRequest(String url, HashMap<String, String> optionalHeaders) throws Exception {
        return executeDeleteRequest(url, buildClient(), optionalHeaders);
    }

    private HashMap<String, Object> executeGetRequest(String url, CloseableHttpClient closeableHttpClient,
                                                      HashMap<String, String> optionalHeaders) throws Exception {
        try (CloseableHttpClient httpClient = closeableHttpClient) {
            HttpGet getRequest = new HttpGet(url);
            logger.trace("Executing request : " + getRequest.getRequestLine());
            HashMap<String, String> headersMap = Optional.ofNullable(optionalHeaders).orElse(defaultHeaders());
            for (Map.Entry<String, String> entry : headersMap.entrySet()) {
                getRequest.setHeader(entry.getKey(), entry.getValue());
            }
            HttpResponse httpResponse = httpClient.execute(getRequest);
            HttpEntity entity = httpResponse.getEntity();
            logger.trace("HTTP Response {}", httpResponse);
            String response = entity != null ? EntityUtils.toString(entity) : null;
            HashMap<String, Object> outputMap = new HashMap<String, Object>();
            outputMap.put(UtilityCommon.HTTP_RESPONSE, response);
            outputMap.put(UtilityCommon.STATUS, httpResponse.getStatusLine().getStatusCode());
            outputMap.put(UtilityCommon.HTTP_HEADERS, Arrays.asList(httpResponse.getAllHeaders()));
            return outputMap;
        }
    }

    private HashMap<String, Object> executePostRequest(String url, CloseableHttpClient closeableHttpClient, String postEntity, HashMap<String, String> optionalHeaders) throws Exception {
        try (CloseableHttpClient httpClient = closeableHttpClient) {
            HttpPost httpPost = new HttpPost((url));
            logger.trace("Executing request: " + httpPost.getRequestLine());
            if (postEntity != null) {
                httpPost.setEntity(new StringEntity(postEntity));
            }
            HashMap<String, String> headersMap = Optional.ofNullable(optionalHeaders).orElse(defaultHeaders());
            for (Map.Entry<String, String> entry : headersMap.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity entity = httpResponse.getEntity();
            logger.trace("HTTP Response {}", httpResponse);
            String response = entity != null ? EntityUtils.toString(entity) : null;
            HashMap<String, Object> outputMap = new HashMap<String, Object>();
            outputMap.put(UtilityCommon.HTTP_RESPONSE, response);
            outputMap.put(UtilityCommon.STATUS, httpResponse.getStatusLine().getStatusCode());
            outputMap.put(UtilityCommon.HTTP_HEADERS, Arrays.asList(httpResponse.getAllHeaders()));
            return outputMap;
        }
    }

    private HashMap<String, Object> executePutRequest(String url, CloseableHttpClient closeableHttpClient, String putEntity, HashMap<String, String> optionalHeaders) throws Exception {
        try (CloseableHttpClient httpClient = closeableHttpClient) {
            HttpPut httpPut = new HttpPut((url));
            logger.trace("Executing request: " + httpPut.getRequestLine());
            if (putEntity != null) {
                httpPut.setEntity(new StringEntity(putEntity));
            }
            HashMap<String, String> headersMap = Optional.ofNullable(optionalHeaders).orElse(defaultHeaders());
            for (Map.Entry<String, String> entry : headersMap.entrySet()) {
                httpPut.setHeader(entry.getKey(), entry.getValue());
            }
            HttpResponse httpResponse = httpClient.execute(httpPut);
            HttpEntity entity = httpResponse.getEntity();
            logger.trace("HTTP Response {}", httpResponse);
            String response = entity != null ? EntityUtils.toString(entity) : null;
            HashMap<String, Object> outputMap = new HashMap<String, Object>();
            outputMap.put(UtilityCommon.HTTP_RESPONSE, response);
            outputMap.put(UtilityCommon.STATUS, httpResponse.getStatusLine().getStatusCode());
            outputMap.put(UtilityCommon.HTTP_HEADERS, Arrays.asList(httpResponse.getAllHeaders()));
            return outputMap;
        }
    }

    private HashMap<String, Object> executeDeleteRequest(String url, CloseableHttpClient closeableHttpClient, HashMap<String, String> optionalHeaders) throws Exception {
        try (CloseableHttpClient httpClient = closeableHttpClient) {
            HttpDelete httpDelete = new HttpDelete((url));
            logger.trace("Executing request: " + httpDelete.getRequestLine());
            HashMap<String, String> headersMap = Optional.ofNullable(optionalHeaders).orElse(defaultHeaders());
            for (Map.Entry<String, String> entry : headersMap.entrySet()) {
                httpDelete.setHeader(entry.getKey(), entry.getValue());
            }
            HttpResponse httpResponse = httpClient.execute(httpDelete);
            HttpEntity entity = httpResponse.getEntity();
            logger.trace("HTTP Response {}", httpResponse);
            String response = entity != null ? EntityUtils.toString(entity) : null;
            HashMap<String, Object> outputMap = new HashMap<String, Object>();
            outputMap.put(UtilityCommon.HTTP_RESPONSE, response);
            outputMap.put(UtilityCommon.STATUS, httpResponse.getStatusLine().getStatusCode());
            outputMap.put(UtilityCommon.HTTP_HEADERS, Arrays.asList(httpResponse.getAllHeaders()));
            return outputMap;
        }
    }

    public CloseableHttpClient buildClientWithBasicAuth(String userName, String password) {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(userName, password));
        return HttpClientBuilder.create().setDefaultCredentialsProvider(credentialsProvider).build();
    }

    public CloseableHttpClient buildClient() {
        return HttpClients.createDefault();
    }

    private HashMap<String, String> defaultHeaders() {
        HashMap<String, String> defaultHeaders = new HashMap<String, String>();
        defaultHeaders.put(UtilityCommon.HEADER_ACCEPT, UtilityCommon.HEADER_APLLICATION_JSON);
        defaultHeaders.put(UtilityCommon.HEADER_CONTENT_TYPE, UtilityCommon.HEADER_APLLICATION_JSON);
        defaultHeaders.put(UtilityCommon.HEADER_X_REQUESTED_BY, RestUtility.class.getName());
        return defaultHeaders;
    }

    public int extractStatusCode(HashMap<String, Object> httpResponseMap) {
        Object statusCodeObj = httpResponseMap.get(UtilityCommon.STATUS);
        return (statusCodeObj != null) ? Integer.parseInt(statusCodeObj.toString()) : 0;
    }

    public String extractResponseString(HashMap<String, Object> httpResponseMap) {
        Object statusObj = httpResponseMap.get(UtilityCommon.HTTP_RESPONSE);
        return (statusObj != null) ? statusObj.toString() : null;
    }

    public List<Header> extractHeadersFromResponse(HashMap<String, Object> httpResponseMap) {
        Object statusObj = httpResponseMap.get(UtilityCommon.HTTP_HEADERS);
        return (List<Header>) statusObj;
    }

    public String extractHeadersFromResponse(HashMap<String, Object> httpResponseMap, String headerName) {
        List<Header> httpHeaders = extractHeadersFromResponse(httpResponseMap);
        String headerValue = null;
        for (Header header : httpHeaders) {
            String name = header.getName();
            if (headerName.equalsIgnoreCase(name)) {
                headerValue = header.getValue();
            }
        }
        return headerValue;
    }

    public InputStream getInputStream(String url, CloseableHttpClient closeableHttpClient, HashMap<String, String> optionalHeaders) throws Exception {
        CloseableHttpClient httpClient = closeableHttpClient;
        HttpGet getRequest = new HttpGet(url);
        logger.trace("Executing request :" + getRequest.getRequestLine());
        HashMap<String, String> headersMap = Optional.ofNullable(optionalHeaders).orElse(defaultHeaders());
        for (Map.Entry<String, String> entry : headersMap.entrySet()) {
            getRequest.setHeader(entry.getKey(), entry.getValue());
        }
        HttpResponse httpResponse = httpClient.execute(getRequest);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            throw new Exception(UtilityCommon.RESPONSE_FAILED + httpResponse);
        }
        return httpResponse.getEntity().getContent();
    }
}