package com.ef.parser;

import com.ef.parser.model.AccessLog;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

/**
 *
 * @author Rogerio J. Gentil
 */
public class DefaultProcessor implements Processor {

    private Map<String, Integer> ips;
    private static final String DATE_FORMAT_FILE = "yyyy-MM-dd HH:mm:ss.SSS";

    @Override
    public Collection<AccessLog> read(File file) throws IOException {
        AccessLog log;
        Collection<AccessLog> accessLogs = new ArrayList<>();

        LineIterator lineIterator = FileUtils.lineIterator(file);

        while (lineIterator.hasNext()) {
            String[] line = lineIterator.nextLine().split("\\|"); // The delimiter of the log file is pipe (|)

            // new method?
            String dateTxt = line[0];
            String ip = line[1];
            String request = line[2];
            String statusCode = line[3];
            String userAgent = line[4];

            LocalDateTime dateOfTheLine = LocalDateTime.parse(dateTxt, DateTimeFormatter.ofPattern(DATE_FORMAT_FILE));

            log = new AccessLog(dateOfTheLine, ip, request, statusCode, userAgent);
            accessLogs.add(log);
        }

        return accessLogs;
    }
}
