
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Finn
 */
public class CodeHeaders {
    
    public static int hCLsize, hAsize, counter;
    public static String[][] headerCodeLines;
    public static String[][] headerAssets;
    
    CodeHeaders() throws IOException{
        counter = 0;
        hCLsize = hAsize = 5;
        headerCodeLines = new String[hCLsize][2];
        headerAssets = new String[hAsize][hAsize];
    }
    
    void includeHeader(String line){
        headerCodeLines[counter][0] = "f";
        headerCodeLines[counter][1] = line;
        counter++;
        if(counter==hCLsize) sizeIncrease();
    }
    
    static void updateHeaderUsage(String line){
        for(int i=0;i<counter;i++){
            if(headerCodeLines[i][0].equals("f")){
                for(int j=0;j<hAsize;j++){
                    if(line.equals(headerAssets[i][j])){
                        headerCodeLines[i][0] = "t";
                        break;
                    }
                }
            }
        }
    }
    
    void sizeIncrease(){
        String[][] auxcodeLines = new String[hCLsize*2][2];
        
        for(int i=0;i<hCLsize;i++){
            for(int j=0;j<2;j++){
                auxcodeLines[i][j] = headerCodeLines[i][j];
            }
        }
        hCLsize*=2;
        headerCodeLines = auxcodeLines;
    }
    
    String sendHeaderName(int i){
        String aux = null;
        int index = 0, j;
        for(j=0;j<headerCodeLines[i][1].length();j++){
            if(headerCodeLines[i][1].charAt(j)=='<'){
                index = j+1;
                while(true){
                    if(headerCodeLines[i][1].charAt(index)=='>'){
                        aux = headerCodeLines[i][1].substring(j+1, index);
                        break;
                    }
                    index++;
                }
            }
        }
        return aux;
    }
    
    void includeAsset() throws FileNotFoundException, IOException{  
        String line,aux;
        boolean trip = false;
        for(int i = 0; i<counter; i++){
            FileReader fileReader = new FileReader("headerAssets.txt"); //filereader loads the file into the program
            BufferedReader bufferedReader =  new BufferedReader(fileReader); //bufferedreader reads it
             while(true){
                int startIndex = 0,assetCounter = 0;
                if((line = bufferedReader.readLine()) != null) {
                    for(int j=0;j < line.length(); j++){
                        if(line.charAt(j)==' '){
                        aux = line.substring(startIndex, j);
                        if(aux.equals(sendHeaderName(i))==false){
                            break;
                        }
                        startIndex = j+1;
                        }
                        else if(line.charAt(j)==','){
                           if(assetCounter==hAsize) assetIncrease();
                           aux = line.substring(startIndex, j);
                           headerAssets[i][assetCounter] = aux;
                           startIndex = j+1;
                           assetCounter++;
                        }
                        else if(j+1==line.length()){
                           if(assetCounter==hAsize) assetIncrease();
                           aux = line.substring(startIndex, j+1);
                           headerAssets[i][assetCounter] = aux;
                           break;
                        }
                    }
                }
                else break;
            }
        }
    }
    
    void assetIncrease(){
        String[][] aux = new String[hAsize*2][hAsize*2];
        
        for(int i = 0;i<hAsize;i++){
            for(int j = 0;j<hAsize;j++){
            aux[i][j] = headerAssets[i][j];
            }
        }
        hAsize*=2;
        headerAssets = aux;
    }
    
    static void display(){
        for(int i = 0;i<hAsize;i++){
            System.out.print(headerCodeLines[i][0]+" "+headerCodeLines[i][1]);
            for(int j = 0;j<hAsize;j++){
                System.out.print(headerAssets[i][j]);
            }
            System.out.println();
        }
    }
}