/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Finn
 */
public class CodeGlobalVariables {
    public static int gVariableSize, gVariableCounter;
    public static String[][] gVariableArray;
    CodeGlobalVariables(){
        gVariableSize = 10; 
        gVariableCounter = 0;
                
        gVariableArray = new String[gVariableSize][2];
    }
    
    void includegVariable(String line){
        gVariableArray[gVariableCounter][0] = "f";
        gVariableArray[gVariableCounter][1] = line;
        gVariableCounter++;
        if(gVariableCounter==gVariableSize) sizeIncrease();
    }
    
    void sizeIncrease(){
        String[][] auxVariableArr = new String[gVariableSize*2][2];
        
        for(int i=0;i<gVariableSize;i++){
            for(int j=0;j<2;j++){
                auxVariableArr[i][j] = gVariableArray[i][j];
            }
        }
        gVariableSize*=2;
        gVariableArray = auxVariableArr;
    }
    
    void display(){
        for(int i=0;i<gVariableSize;i++){
            for(int j=0;j<2;j++){
                System.out.print(gVariableArray[i][j]);
            }
            System.out.println();
        }
    }
    
    static void updateGlobalVar(String s){
        int size = s.length();
        if(size==1&&Character.isDigit(s.charAt(0))){return;}
        if(s.equals("int")||s.equals("bool")||s.equals("char")||s.equals("float")||s.equals("double")||s.equals("string")){
        return;
        }
        int index = 0;
        while(index<gVariableCounter){
            if(gVariableArray[index][0].equals("t")==false){
                String line = gVariableArray[index][1];
                for(int i=0;i<line.length();i++){
                    if(i+size<line.length()&&(line.substring(i,i+size)).equals(s)){
                        if(i-1>0&&(Character.isLetterOrDigit(line.charAt(i-1)))==false&&(Character.isLetterOrDigit(line.charAt(i+size)))==false){
                            if(gVariableArray[index][0].equals("f")){
                                gVariableArray[index][0] = "t";
                            }
                        }
                    }
                }
            }
            index++;
        }
    }
}