package com.example.aws.elasticsearch.demo.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.util.List;
import java.util.Map;

public class CSV2JSON {

    public static void main(String[] args) throws Exception {

        long freeMemory = Runtime.getRuntime().freeMemory();
        long totalMemory = Runtime.getRuntime().totalMemory();
        long maxMemory = Runtime.getRuntime().maxMemory();

        System.out.println("JVM freeMemory: " + freeMemory);
        System.out.println("JVM totalMemory also equals to initial heap size of JVM : "
                + totalMemory);
        System.out.println("JVM maxMemory also equals to maximum heap size of JVM: "
                + maxMemory);
        try {
            File input = new File("/Users/gauravtyagi/Downloads/F_5500_2017_Latest/f_5500_2017_latest.csv");
            File output = new File("/Users/gauravtyagi/Downloads/F_5500_2017_Latest/f_5500_2017_latest.json");

            CsvSchema csvSchema = CsvSchema.builder().setUseHeader(true).build();
            CsvMapper csvMapper = new CsvMapper();

            // Read data from CSV file
            List<Object> readAll = csvMapper.readerFor(Map.class).with(csvSchema).readValues(input).readAll();

            ObjectMapper mapper = new ObjectMapper();

            // Write JSON formated data to output.json file
            mapper.writerWithDefaultPrettyPrinter().writeValue(output, readAll);

            // Write JSON formated data to stdout
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(readAll));
        } finally {
            freeMemory = Runtime.getRuntime().freeMemory();
            totalMemory = Runtime.getRuntime().totalMemory();
            maxMemory = Runtime.getRuntime().maxMemory();

            System.out.println("Used Memory in JVM: " + (maxMemory - freeMemory));
            System.out.println("freeMemory in JVM: " + freeMemory);
            System.out.println("totalMemory in JVM shows current size of java heap : "
                    + totalMemory);
            System.out.println("maxMemory in JVM: " + maxMemory);
        }
    }
}