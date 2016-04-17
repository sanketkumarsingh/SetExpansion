/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package setexpansion.algorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class is used for preprocessing the data
 * @author nawshad and sanket
 */
public class SumOfTerms {
    public static void sumOfTerms(String inputFile, String outputFile) throws IOException{
    	File file = new File(outputFile);
    	if(file.exists()){
    		file.delete();
    	}
        FileWriter fos = new FileWriter(outputFile);
                PrintWriter dos = new PrintWriter(fos);
        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        try {
            String line = "";
            String prevt1="";
            int curVal = 0;
            int lineno = 0;
            boolean first = true;
            //boolean endAtSameTerm = false;
            while ((line = br.readLine()) != null){
                String[] split = line.split("\t");
                if(split.length != 2){
                	continue;
                }
                if(first){
                	curVal += Integer.parseInt(split[1]);     
                	prevt1 = split[0];
                	first = false;
                	continue;
                }
                if(!split[0].equals(prevt1)){
              	  dos.println(prevt1+"\t"+curVal);
              	  dos.flush();
              	  curVal = Integer.parseInt(split[1]);
              	  prevt1 = split[0];
              	//endAtSameTerm = false;
                }else{
                	curVal += Integer.parseInt(split[1]);     
                	prevt1 = split[0];
                	//endAtSameTerm = true;
                }
                lineno = lineno + 1;
                if(lineno%10000 == 0)
                System.out.println("Done line no:" + lineno);
            }
           // if(endAtSameTerm){
            	dos.println(prevt1+"\t"+curVal);
            	  dos.flush();
            	  System.out.println("Done last line");
          //  }
            
        } finally {
            br.close();
        }
        dos.close();
        fos.close();
        System.out.println("Finished..");
    }
    
}
