/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Finn
 */
public class CodeClass {
    public static int CCsize, CCcounter;
    public static String[] CCLines;
    
    CodeClass(){
        CCcounter = 0;
        CCsize = 5;
        CCLines = new String[CCsize]; 
    }
    
    void includeClass(String line){
        CCLines[CCcounter] = line;
        CCcounter++;
        if(CCcounter==CCsize) sizeIncrease();
    }
    
    void sizeIncrease(){
        String[] auxcodeLines = new String[CCsize*2];
        
        for(int i=0;i<CCsize;i++){
            auxcodeLines[i] = CCLines[i];
        }
        CCsize*=2;
        CCLines =  auxcodeLines;
    }
    
    void display(){
        for(int i=0;i<CCsize;i++){
            System.out.println(CCLines[i]);
        }
    }
}