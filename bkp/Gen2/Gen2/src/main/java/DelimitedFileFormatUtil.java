import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

import com.univocity.parsers.csv.CsvFormat;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.tsv.TsvFormat;
import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;


public class DelimitedFileFormatUtil {
    public String getTSVFormat(InputStream inputStream) {
        TsvParserSettings settings = new TsvParserSettings();
        settings.setLineSeparatorDetectionEnabled(true);
        TsvParser parser = new TsvParser(settings);
        parser.beginParsing(inputStream);
        // Get the first row from the TSV file and check if there is header using heuristic method
        String[] row = parser.parseNext();
        parser.stopParsing();
        boolean hasHeader = isHeader(row);
        TsvFormat format = settings.getFormat();

        // store the below in JSON
        HashMap<String, Object> map = new HashMap<>();
        map.put("isTsvFile", true);
        map.put("delimiter", String.valueOf('\t'));
        map.put("escapeChar", String.valueOf(format.getEscapeChar()));
        map.put("escapedTabChar", String.valueOf(format.getEscapedTabChar()));
        map.put("lineSeparator", format.getLineSeparatorString());
        map.put("hasHeader", hasHeader);

        if (hasHeader) {
            map.put("headerColumnNames", Arrays.asList(row));
        }
        return JSONUtils.map2JsonString(map);
    }

    public String getCSVFormat(InputStream inputStream) {
        CsvParserSettings settings = new CsvParserSettings();
        settings.detectFormatAutomatically();
        settings.setMaxColumns(4096);
        settings.setKeepEscapeSequences(true);
        CsvParser parser = new CsvParser(settings);
        parser.beginParsing(inputStream);
        CsvFormat format = parser.getDetectedFormat();
        HashMap<String, Object> map = new HashMap<>();
        map.put("delimiter", format.getDelimiterString());
        map.put("quoteChar", String.valueOf(format.getQuote()));
        map.put("quoteEscapeChar", String.valueOf(format.getQuoteEscape()));
        map.put("lineSeparator", format.getLineSeparatorString());
        map.put("charToEscapeQuoteEscaping",String.valueOf(format.getCharToEscapeQuoteEscaping()));

        // Get the first row from the CSV file and check if there is header using heuristic method
        String[] row = parser.parseNext();
        parser.stopParsing();
        boolean hasHeader = isHeader(row);
        map.put("hasHeader", hasHeader);
        if (hasHeader) {
            map.put("headerColumnNames", Arrays.asList(row));
        }
        return JSONUtils.map2JsonString(map);
    }




    public static boolean isHeader(String[] row) {
        if (row != null) {
            for (String column : row) {
                if (column != null) {
                    column = column.trim();
                    if (column.length() == 0) {
                        return false;
                    }
                    String columnOnlyOtherChars = column.replaceAll("[a-zA-Z_-]+", "");
                    if (columnOnlyOtherChars.length() > 0) {
                        double ratio = columnOnlyOtherChars.length() * 1.0 / column.length();
                        if (ratio > .25) {
                            // if more numbers or non-alpha characters than 25%
                            // then it is assumed to be not a column name
                            return false;
                        }
                        if (!column.matches("^[a-zA-Z_-]{3,}\\d+$")) {
                            // if the column doesn't start with atleast 3 alpha
                            // then it is assumed to be not a column name
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static Reader getReader(String relativePath) {
        try {
            return new InputStreamReader(new FileInputStream(relativePath), "UTF-8");
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            throw new IllegalStateException("Unable to read input", e);
        }
    }

    public static Reader getReader(InputStream inputStream) {
        try {
            return new InputStreamReader(inputStream, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Unable to read input", e);
        }
    }

}