/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package setexpansion.util;

import com.sun.rowset.internal.Row;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * This class is used in preprocessing steps.
 * @author nawshad
 */
public class Tuple  {
    String c1;
    String c2;

    public Tuple(String c1, String c2) {
        this.c1 = c1;
        this.c2 = c2;
    }
    
    public static void sort(String inputFile, String outputFile) throws IOException{
        
         //Sorting smallterm
         Path inputPath = Paths.get(inputFile);  
         List<Tuple> tuple = new ArrayList<Tuple>();
         List<String> lines = Files.readAllLines(inputPath, StandardCharsets.UTF_8);
         String splitter;
         if(inputFile.equals("smallterm.txt")){
             splitter = "\t";
         }else{
             splitter = "\t\t"; 
         }
         for (String line : lines) {
            String[] array = line.split(splitter);
            tuple.add(new Tuple(array[0], array[1]));
        }
         
        
        Collections.sort(tuple, new Comparator<Tuple>() {
             public int compare(Tuple o1, Tuple o2) {
                 return o1.c1.compareTo(o2.c1);
             }
        });
        
        File file1 = new File(outputFile);
    	if(file1.exists()){
    		file1.delete();
    	}
        Path outputPath = Paths.get(outputFile); 
        BufferedWriter writer = Files.newBufferedWriter(outputPath, StandardCharsets.UTF_8);
        PrintWriter outSmallTermSorted = new PrintWriter(writer);
        
        for(Tuple tp : tuple ){
            if(inputFile.equals("smallterm.txt")){
                outSmallTermSorted.println(tp.c1+"\t"+tp.c2);
            }else{
                outSmallTermSorted.println(tp.c1+"\t\t"+tp.c2);
            }
            
            outSmallTermSorted.flush();
        }
    }
    
    public static void main(String[] args) throws IOException{
        Tuple.sort("smallterm.txt", "smallTermSorted.txt");
        Tuple.sort("smalltermPair.txt", "smallTermPairSorted.txt");
    }
    
    
}     
   


