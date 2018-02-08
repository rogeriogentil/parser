package com.ef.parser;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

/**
 *
 * @author Rogerio J. Gentil
 */
public class DefaultProcessor implements Processor {

    private Map<String, Integer> ips;
    
    private static final String DATE_FORMAT_FILE = "yyyy-MM-dd HH:mm:ss.SSS";

    // TODO: Does Stream could be better than File to avoid IOException? Verify.
    @Override
    public Map<String, Integer> findIps(File file, LocalDateTime startDate, DurationArg duration, int threshold) throws IOException {
        ips = new HashMap<>();
                
        LocalDateTime endDate;        
        switch (duration) {
            case HOURLY:
                endDate = LocalDateTime.from(startDate).plusHours(1);
                break;
            case DAILY:
                endDate = LocalDateTime.from(startDate).plusDays(1);
                break;
            default:
                throw new IllegalArgumentException("");
        }

        LineIterator lineIterator = FileUtils.lineIterator(file);

        while (lineIterator.hasNext()) {
            String[] line = lineIterator.nextLine().split("\\|"); // The delimiter of the log file is pipe (|)

            // new method?
            String dateTxt = line[0];
            String ip = line[1];
            String request = line[2];
            String statusCode = line[3];
            String userAgent = line[4];

            // new method?
            LocalDateTime dateOfTheLine = LocalDateTime.parse(dateTxt, DateTimeFormatter.ofPattern(DATE_FORMAT_FILE));

            if (dateOfTheLine.isBefore(startDate)) {
                continue;
            }

            if (dateOfTheLine.isAfter(endDate)) {
                break;
            }

            if (ips.get(ip) != null) {
                Integer count = ips.get(ip);
                ips.put(ip, ++count);
            } else {
                ips.put(ip, 1);
            }
        }

        return ips.entrySet().stream()
                .filter(map -> map.getValue() >= threshold)
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
    }

}
