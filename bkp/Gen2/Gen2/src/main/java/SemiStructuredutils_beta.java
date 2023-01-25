import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvFormat;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileStream;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.routines.BigIntegerValidator;
import org.apache.parquet.hadoop.ParquetFileReader;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.Type;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class SemiStructuredutils_beta {
    private static final String COLUMN_NAME = "column_name";
    private static final String DATA_TYPE = "data_type";
    private static final String ORDINAL_POSITION = "ordinal_position";

    public List<LinkedHashMap<String, Object>> getParquetColMetadata(ParquetFileReader reader) {
        MessageType schema = reader.getFooter().getFileMetaData().getSchema();
        List<LinkedHashMap<String, Object>> columnMetainfo = new ArrayList<>();
        LinkedHashMap<String, Object> fieldsAndTypesOfParquet;
        for (Type field : schema.getFields()) {
            fieldsAndTypesOfParquet = new LinkedHashMap<>();
            fieldsAndTypesOfParquet.put(COLUMN_NAME, field.getName());
            if (field.isPrimitive()) {
                fieldsAndTypesOfParquet.put(DATA_TYPE, field.asPrimitiveType().getPrimitiveTypeName().toString());
            } else {
                fieldsAndTypesOfParquet.put(DATA_TYPE, field.getOriginalType().toString());
            }
            columnMetainfo.add(fieldsAndTypesOfParquet);
        }
        return columnMetainfo;
    }

    public List<HashMap<String, Object>> getAvroColMetadata(InputStream inputStream) throws IOException {
        List<HashMap<String, Object>> columnMetainfo = new ArrayList<>();
        HashMap<String, Object> fieldsAndTypesOfAvro;
        DatumReader<GenericRecord> datumReader = new GenericDatumReader<>();
        DataFileStream<GenericRecord> dataFileStream = new DataFileStream<>(inputStream, datumReader);
        Schema schema = dataFileStream.getSchema();
        for (Schema.Field field : schema.getFields()) {
            fieldsAndTypesOfAvro = new HashMap<>();
            fieldsAndTypesOfAvro.put(COLUMN_NAME, field.name());
            fieldsAndTypesOfAvro.put(DATA_TYPE, field.schema().getType().toString());
            fieldsAndTypesOfAvro.put(ORDINAL_POSITION, field.pos());
            columnMetainfo.add(fieldsAndTypesOfAvro);
        }
        return columnMetainfo;
    }

    public List<HashMap<String, Object>> getCSVAndTSVColMetadata(InputStream inputStream, String fileFormat, boolean delimiterFilesCompleteScan, int numOfRowsForDelimiterFilesScan) {
        List<Record> allRecords;
        if (fileFormat.equalsIgnoreCase(UtilityCommon.TSV)) {
            TsvParserSettings settings = new TsvParserSettings();
            settings.setLineSeparatorDetectionEnabled(true);
            TsvParser parser = new TsvParser(settings);
            allRecords = parser.parseAllRecords(inputStream);
            parser.stopParsing();
        } else {
            CsvParserSettings settings = new CsvParserSettings();
            settings.detectFormatAutomatically();
//            settings.setLineSeparatorDetectionEnabled(false);
//            settings.getFormat().setLineSeparator("\n");
            CsvParser parser = new CsvParser(settings);
            allRecords = parser.parseAllRecords(inputStream);
          //  parser.stopParsing();
        }
        List<HashMap<String, Object>> columnMetainfo = new ArrayList<>();
        Record header = allRecords.get(0);
        allRecords.remove(0);
        HashMap<String, Object> fieldsAndTypesOfCSV;
        HashMap<Integer, String> fieldStore = new HashMap<>();
        if (delimiterFilesCompleteScan) {
            for (Record record : allRecords) {
                Map<Integer, String> columns = record.toIndexMap();
                for (Integer i : columns.keySet()) {
                    if (columns.get(i) != null && !columns.get(i).trim().isEmpty()) {
                        if (GenericValidator.isByte(columns.get(i)))
                            fieldStore.put(i, getNumType(fieldStore, i, "byte"));
                        else if (GenericValidator.isShort(columns.get(i)))
                            fieldStore.put(i, getNumType(fieldStore, i, "short"));
                        else if (GenericValidator.isInt(columns.get(i)))
                            fieldStore.put(i, getNumType(fieldStore, i, "int"));
                        else if (GenericValidator.isLong(columns.get(i)))
                            fieldStore.put(i, getNumType(fieldStore, i, "long"));
                        else if (BigIntegerValidator.getInstance().isValid(columns.get(i)))
                            fieldStore.put(i, getNumType(fieldStore, i, "bigint"));
                        else if (GenericValidator.isFloat(columns.get(i)))
                            fieldStore.put(i, getNumType(fieldStore, i, "float"));
                        else if (GenericValidator.isDouble(columns.get(i)))
                            fieldStore.put(i, getNumType(fieldStore, i, "double"));
                        else if (GenericValidator.isEmail(columns.get(i)))
                            fieldStore.put(i, getValidType(fieldStore, i, "email"));
                        else if (columns.get(i).equalsIgnoreCase("true") || columns.get(i).equalsIgnoreCase("false"))
                            fieldStore.put(i, getValidType(fieldStore, i, "boolean"));
                        else if (DateUtil.isValidDate(columns.get(i)))
                            fieldStore.put(i, getValidType(fieldStore, i, "date"));
                        else fieldStore.put(i, "text");
                    }
                    else{
                        fieldStore.put(i, getNumType(fieldStore, i, "null"));
                    }
                }
            }
        } else {
            for (int j = 0; j < numOfRowsForDelimiterFilesScan && j < allRecords.size(); j++) {
                Record record = allRecords.get(j);
                Map<Integer, String> columns = record.toIndexMap();
                for (Integer i : columns.keySet()) {
                    if (columns.get(i) != null && !columns.get(i).trim().isEmpty()) {
                        if (GenericValidator.isByte(columns.get(i)))
                            fieldStore.put(i, getNumType(fieldStore, i, "byte"));
                        else if (GenericValidator.isShort(columns.get(i)))
                            fieldStore.put(i, getNumType(fieldStore, i, "short"));
                        else if (GenericValidator.isInt(columns.get(i)))
                            fieldStore.put(i, getNumType(fieldStore, i, "int"));
                        else if (GenericValidator.isLong(columns.get(i)))
                            fieldStore.put(i, getNumType(fieldStore, i, "long"));
                        else if (BigIntegerValidator.getInstance().isValid(columns.get(i)))
                            fieldStore.put(i, getNumType(fieldStore, i, "bigint"));
                        else if (GenericValidator.isFloat(columns.get(i)))
                            fieldStore.put(i, getNumType(fieldStore, i, "float"));
                        else if (GenericValidator.isDouble(columns.get(i)))
                            fieldStore.put(i, getNumType(fieldStore, i, "double"));
                        else if (GenericValidator.isEmail(columns.get(i)))
                            fieldStore.put(i, getValidType(fieldStore, i, "email"));
                        else if (columns.get(i).equalsIgnoreCase("true") || columns.get(i).equalsIgnoreCase("false"))
                            fieldStore.put(i, getValidType(fieldStore, i, "boolean"));
                        else if (DateUtil.isValidDate(columns.get(i)))
                            fieldStore.put(i, getValidType(fieldStore, i, "date"));
                        else fieldStore.put(i, "text");
                    }
                    else{
                        System.out.println(fieldStore.get(i));
                        fieldStore.put(i, getNumType(fieldStore, i, "null"));
                    }
                }
            }
        }
        allRecords.clear();
        for (int key : fieldStore.keySet()) {
            fieldsAndTypesOfCSV = new HashMap<>();
            fieldsAndTypesOfCSV.put(COLUMN_NAME, header.getString(key));
            fieldsAndTypesOfCSV.put(DATA_TYPE, fieldStore.get(key));
            fieldsAndTypesOfCSV.put(ORDINAL_POSITION, key);
            columnMetainfo.add(fieldsAndTypesOfCSV);

        }
        fieldStore.clear();
        return columnMetainfo;
    }

    public String getValidType(HashMap<Integer, String> fieldStore, int i, String type) {
        if (fieldStore.containsKey(i))
            if (!fieldStore.get(i).equals(type))
                return "text";
        return type;
    }

    public String getNumType(HashMap<Integer, String> fieldStore, int i, String type) {
        HashMap<String, Integer> priorityMap = new HashMap<>();
        priorityMap.put("null",0);
        priorityMap.put("byte", 1);
        priorityMap.put("short", 2);
        priorityMap.put("int", 3);
        priorityMap.put("long", 4);
        priorityMap.put("bigint", 5);
        priorityMap.put("float", 6);
        priorityMap.put("double", 7);
        priorityMap.put("text", 8);
        priorityMap.put("boolean", 8);
        priorityMap.put("email", 8);
        priorityMap.put("date", 8);
        if (fieldStore.containsKey(i)) {
            if (priorityMap.get(fieldStore.get(i)) < priorityMap.get(type))
                return type;
            else
                return fieldStore.get(i);
        }
        return type;
    }
}