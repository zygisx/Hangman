/*
 * 
 * GetWord.java
 * 
 * class GetWord
 * 
 * GetWord: zaidejui grazina zodi is failo kurio pavadinimas nurodomas metodui gaukZodi kaip parametras
 * 
 * Rima Žurauskaitė ir Jovita 
 * 
 * el. paštas: 
 */

import java.io.*;
import java.util.*;
import java.util.Random;

public class GetWord {

	static String eiluteBeTarpu (String zodis) {
		String naujasZodis = zodis.replaceAll(" ", "");	
		return naujasZodis;
	}
	
  static String gaukZodi(String fileName) {
	
		//String zodis;
		String strLine = "";
		int zodziuSkaicius = 0;
		List<String> zodziai = new ArrayList<String>();				// naudoju kolekcijas
    
    try {	
    FileInputStream fstream = new FileInputStream(fileName);
    DataInputStream in = new DataInputStream(fstream);
    BufferedReader br = new BufferedReader(new InputStreamReader(in));
		
    
		
    
    while ((strLine = br.readLine()) != null) {            
      zodziai.add(strLine);                         // ieskau reikiamo atsitiktinio zodzio
      zodziuSkaicius++;
		}
		
    //uzdarom faila
    in.close();
    
    }catch (Exception e){											//Iesko klaidu
      System.err.println("Error: " + e.getMessage());
    }
    // generuoju atsitiktini skaiciu
    Random atsitiktiniai = new Random();
		int skaicius = atsitiktiniai.nextInt(zodziuSkaicius);
		
		// gaunu atsitiktini skaiciu atitinkanti zodi
		strLine = zodziai.get(skaicius);
    strLine = eiluteBeTarpu(strLine);
    strLine =strLine.toUpperCase();
    
    return(strLine);
  }
}
