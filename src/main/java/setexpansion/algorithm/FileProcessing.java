/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package setexpansion.algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author nawshad
 */
public class FileProcessing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
    SumOfTerms.sumOfTerms("smallTermSorted.txt", "smalltermCount.txt");
    SumOfPairs.sumOfPairs("smallTermPairSorted.txt", "smallTermPairCount.txt");
//    	File file = new File("smalltermCount.txt");
//    	if(file.exists()){
//    		file.delete();
//    	}
//    	PrintWriter outFile1 = new PrintWriter(new FileWriter("smalltermCount.txt", true));
//    	outFile1.println("Hi Sanket");
//    	outFile1.flush();
//    	outFile1.close();
//    	int i =1;
//    	int j = 10;
//    	double x = (double)i/(double)j;
//    	System.out.println(x);
    }
        
}
    

