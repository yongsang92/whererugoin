package com.whereareyougoing.www.demo.config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import au.com.bytecode.opencsv.CSVReader;

@Component
public class CsvReader{

    String fileName= "C:/Users/yongyong/Documents/";
    
    List<String[]> data = new ArrayList<String[]>();

    public void setfileName(String fileName){
        this.fileName+=fileName;
    }

    public List<String[]> readCsv() {
        try {
            final CSVReader reader = new CSVReader(new FileReader(fileName));
            String[] s;
            while ((s = reader.readNext()) != null) {
                data.add(s);
            }
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }

        return data;
    }

}