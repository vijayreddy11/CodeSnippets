//import com.azure.core.http.rest.PagedIterable;
//import com.azure.storage.common.StorageSharedKeyCredential;
//import com.azure.storage.file.datalake.DataLakeFileClient;
//import com.azure.storage.file.datalake.DataLakeFileSystemClient;
//import com.azure.storage.file.datalake.DataLakeServiceClient;
//import com.azure.storage.file.datalake.DataLakeServiceClientBuilder;
//
//import com.modak.vaultfactory.VaultFactory;
//import com.modak.vaultinterface.VaultClient;
//
//import com.modak.vaultutils.JSONUtils;
//import org.apache.commons.io.FileUtils;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//public class test {
//    DataLakeFileSystemClient dataLakeFileSystemClient;
//    DataLakeServiceClient dataLakeServiceClient;
//    private static final String CONTAINER = "container";
//    private static final String FOLDER = "FOLDER";
//    private static final String FILE = "FILE";
//    private static final String ACCOUNT_KEY = "accountKey";
//    private static final String ACCOUNT_NAME = "accountName";
//    private static final String COLUMN_FILE_NAME = "file_name";
//    private static final String COLUMN_FILE_PATH = "path";
//    private static final String COLUMN_FILE_MODIFIED_TIME = "lastmodified";
//    private static final String COLUMN_FILE_OWNER = "owner";
//    private static final String COLUMN_FILE_SIZE = "size";
//    private static final String COLUMN_FILE_ADDITIONAL_INFO = "additional_info";
//    private static final String ROOT_PATH = "/";
//    private static String fetchStatsType = null;
//    HashMap<String, String> configMap;
//    int dataplace_id;
//    int datalayer_component_id;
//    public DataLakeFileSystemClient getDataLakeFileSystemClient(HashMap<String, String> azureDataLakeCredMap, String containerName) {
//        StorageSharedKeyCredential sharedKeyCredential = new StorageSharedKeyCredential
//                ("nabucrawling", azureDataLakeCredMap.get(ACCOUNT_KEY));
//        DataLakeServiceClientBuilder builder = new DataLakeServiceClientBuilder();
//        builder.credential(sharedKeyCredential);
//        builder.endpoint("https://" + "nabucrawling" + ".dfs.core.windows.net");
//        return builder.buildClient().getFileSystemClient(containerName);
//    }
//    public static void main(String[] args) throws Exception{
//        test adls2Crawler=new test();
//        DataLakeFileSystemClient dataLakeFileSystemClient1;
//        DataLakeFileClient dataLakeFileClient1;
//        String ContainerName = "nabucrawling";
//        String jsonString = FileUtils.readFileToString(new File("C:\\Users\\MA0264\\Desktop\\git_crawlers\\crawlers2.0\\crawlers\\src\\main\\resources\\vault_config.json"));
//        Map<String, Object> vaultMap = JSONUtils.jsonToMap(jsonString);
//        VaultClient vault= VaultFactory.getVault(vaultMap);
//        String key=vault.getSecretValue(vaultMap,"key");
//        String name=vault.getSecretValue(vaultMap,"name");
//        HashMap<String, String> azureDataLakeCredMap=new HashMap<>();
//        azureDataLakeCredMap.put("nabucrawling",name);
//        azureDataLakeCredMap.put(ACCOUNT_KEY,key);
//        dataLakeFileSystemClient1=adls2Crawler.getDataLakeFileSystemClient(azureDataLakeCredMap,ContainerName);
//        dataLakeFileClient1=dataLakeFileSystemClient1.getFileClient("userdata5.avro");
//        System.out.println("xyz" + dataLakeFileClient1);
//        System.out.println("connected"+ dataLakeFileSystemClient1);
//    }
//}