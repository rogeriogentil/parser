package com.ef.parser;

import java.io.File;
import java.io.IOException;
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
    
    // TODO: Does Stream could be better than File to avoid IOException? Verify.
    @Override
    public Map<String, Integer> findIps(File file, String startDate, String duration, String threshold) throws IOException {
        ips = new HashMap<>();
        
        LineIterator lineIterator = FileUtils.lineIterator(file);
        
        while (lineIterator.hasNext()) {
            String[] line = lineIterator.nextLine().split("\\|");
            
            // new method
            String date = line[0];
            String ip = line[1];
            String request = line[2];
            String statusCode = line[3];
            String userAgent = line[4];
            
            if (ips.get(ip) != null) {
                Integer count = ips.get(ip);
                ips.put(ip, ++count);
            } else {
                ips.put(ip, 1);
            }
        }
        
        int limit = Integer.parseInt(threshold);
        
        return ips.entrySet().stream()
                .filter(map -> map.getValue() >= limit)
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
    }

}
