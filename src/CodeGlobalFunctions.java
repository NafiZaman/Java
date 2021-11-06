/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Finn
 */
public class CodeGlobalFunctions {
    public static int gFunctionSize, gFunctionCounter;
    public static String[][] gFunctionsArray;
    int mainFunctionIndex;
    
    CodeGlobalFunctions(){
        gFunctionSize = 10; 
        gFunctionCounter = 0;
        gFunctionsArray = new String[gFunctionSize][3]; // 2nd column is fn name and 3rd is definition
    }
    
    void includegFunction(String line){
        if(line.substring(0,10).equals("int main()")||line.substring(0,11).equals("int main( )")){
            gFunctionsArray[gFunctionCounter][0] = "t";
            mainFunctionIndex = gFunctionCounter;
        }
        else gFunctionsArray[gFunctionCounter][0] = "f";
 
        for(int i=0;i<line.length();i++){
            if(line.charAt(i)=='{'){
                gFunctionsArray[gFunctionCounter][1] = line.substring(0, i+1);
                gFunctionsArray[gFunctionCounter][2] = line.substring(i+1, line.length());
                break;
            }
        }
        gFunctionCounter++;
        if(gFunctionCounter==gFunctionSize) sizeIncrease();
    }
    
    void sizeIncrease(){
        String[][] auxFunctionArr = new String[gFunctionSize*2][3];
        
        for(int i=0;i<gFunctionSize;i++){
            for(int j=0;j<3;j++){
                auxFunctionArr[i][j] = gFunctionsArray[i][j];
            }
        }
        gFunctionSize*=2;
        gFunctionsArray = auxFunctionArr;
    }
    
    void display(){
        for(int i=0;i<gFunctionSize;i++){
            for(int j=0;j<3;j++){
                System.out.print(gFunctionsArray[i][j]);
            }
            System.out.println();
        }
    }
    
    void functionVerifier(int Index){
        boolean arrinitbrac = false;
        boolean commafound = false;
        boolean exist;
        boolean endofline=false;
        String aux="";
        String line = gFunctionsArray[Index][2];
        for(int i=0;i<line.length();i++){
           if((Character.isLetterOrDigit(line.charAt(i)))){
               if(i+3<line.length()&&(line.substring(i,i+3)).equals("int")){
                   i = i+3;
                   int j = i+1;
                   while(Character.isLetterOrDigit(line.charAt(j))==false)j++;
                   int k=j+1;
                   while(true){
                       if((Character.isLetterOrDigit(line.charAt(k)))==false){
                           exist = searchDefinition(line.substring(j,k),k,Index);
                           if(exist==true){
                               while(true){
                                   if(line.charAt(k)=='{') arrinitbrac = true;
                                   if(line.charAt(k)=='}') arrinitbrac = false;
                                   if(line.charAt(k)==',' && arrinitbrac==false && commafound ==false){
                                       commafound = true;
                                       if(line.charAt(i)!=' ') aux+="int "+line.substring(i,k);
                                       else aux+="int"+line.substring(i,k);
                                       i=k+1;
                                       j=k+1;
                                       break;
                                   }
                                   if(line.charAt(k)==',' && arrinitbrac==false && commafound ==true){
                                       aux+=", "+line.substring(i,k);
                                       i=k+1;
                                       j=k+1;
                                       break;
                                   }
                                   if(line.charAt(k)==';'&& commafound ==true){
                                       commafound =false;
                                       endofline = true;
                                       aux+=", "+line.substring(i,k)+";";
                                       i=k+1;
                                       j=k+1;
                                       break;
                                   }
                                   if(line.charAt(k)==';'&& commafound ==false){
                                       endofline = true;
                                       if(line.charAt(i)!=' ') aux+="int "+line.substring(i,k)+";";
                                       else aux+="int"+line.substring(i,k)+";";
                                       i=k+1;
                                       j=k+1;
                                       break;
                                   }
                                   k++;
                               }
                           }
                           else{
                               boolean arrbrac = false;
                               while(true){
                                   if(line.charAt(k)=='{'){
                                       arrbrac = true;
                                   }
                                   if(line.charAt(k)=='}'){
                                       arrbrac = false;
                                   }
                                   if(line.charAt(k)==','&&arrbrac==false){
                                       i=k+1;
                                       j=k+1;
                                       break;
                                   }
                                   if(line.charAt(k)==';'){
                                       if(aux.length()>1){
                                           if(line.charAt(aux.length()-1)==','){
                                           aux = aux.substring(0,aux.length()-1);
                                           aux+=";";
                                           }
                                           else if(line.charAt(aux.length()-2)==','){
                                               aux = aux.substring(0,aux.length()-2);
                                               aux+=";";
                                           }
                                           else aux+=";";
                                       }
                                       endofline = true;
                                       i=k+1;
                                       j=k+1;
                                       break;
                                   }
                                   k++;
                               }
                           }
                           if(endofline == true){
                               endofline = false;
                               break;
                           }
                       }
                   k++;
                   }
                   i=k;
                   arrinitbrac = false;
                   commafound = false;
               }
               if(i+6<line.length()&&(line.substring(i,i+6)).equals("string")){
                   i = i+6;
                   int j = i+1;
                   while(Character.isLetterOrDigit(line.charAt(j))==false)j++;
                   int k=j+1;
                   while(true){
                       if((Character.isLetterOrDigit(line.charAt(k)))==false){
                           exist = searchDefinition(line.substring(j,k),k,Index);
                           if(exist==true){
                               while(true){
                                   if(line.charAt(k)=='{') arrinitbrac = true;
                                   if(line.charAt(k)=='}') arrinitbrac = false;
                                   if(line.charAt(k)==',' && arrinitbrac==false && commafound ==false){
                                       commafound = true;
                                       if(line.charAt(i)!=' ') aux+="string "+line.substring(i,k);
                                       else aux+="string"+line.substring(i,k);
                                       i=k+1;
                                       j=k+1;
                                       break;
                                   }
                                   if(line.charAt(k)==',' && arrinitbrac==false && commafound ==true){
                                       aux+=", "+line.substring(i,k);
                                       i=k+1;
                                       j=k+1;
                                       break;
                                   }
                                   if(line.charAt(k)==';'&& commafound ==true){
                                       commafound =false;
                                       endofline = true;
                                       aux+=", "+line.substring(i,k)+";";
                                       i=k+1;
                                       j=k+1;
                                       break;
                                   }
                                   if(line.charAt(k)==';'&& commafound ==false){
                                       endofline = true;
                                       if(line.charAt(i)!=' ') aux+="string "+line.substring(i,k)+";";
                                       else aux+="string"+line.substring(i,k)+";";
                                       i=k+1;
                                       j=k+1;
                                       break;
                                   }
                                   k++;
                               }
                           }
                           else{
                               boolean arrbrac = false;
                               while(true){
                                   if(line.charAt(k)=='{'){
                                       arrbrac = true;
                                   }
                                   if(line.charAt(k)=='}'){
                                       arrbrac = false;
                                   }
                                   if(line.charAt(k)==','&&arrbrac==false){
                                       i=k+1;
                                       j=k+1;
                                       break;
                                   }
                                   if(line.charAt(k)==';'){
                                       if(aux.length()>1){
                                           if(line.charAt(aux.length()-1)==','){
                                           aux = aux.substring(0,aux.length()-1);
                                           aux+=";";
                                           }
                                           else if(line.charAt(aux.length()-2)==','){
                                               aux = aux.substring(0,aux.length()-2);
                                               aux+=";";
                                           }
                                           else aux+=";";
                                       }
                                       endofline = true;
                                       i=k+1;
                                       j=k+1;
                                       break;
                                   }
                                   k++;
                               }
                           }
                           if(endofline == true){
                               endofline = false;
                               break;
                           }
                       }
                   k++;
                   }
                   i=k;
                   arrinitbrac = false;
                   commafound = false;
               }
               else if(i+5<line.length()&&(line.substring(i,i+5)).equals("float")){
                   i = i+5;
                   int j = i+1;
                   while(Character.isLetterOrDigit(line.charAt(j))==false)j++;
                   int k=j+1;
                   while(true){
                       if((Character.isLetterOrDigit(line.charAt(k)))==false){
                           exist = searchDefinition(line.substring(j,k),k,Index);
                           if(exist==true){
                               while(true){
                                   if(line.charAt(k)=='{') arrinitbrac = true;
                                   if(line.charAt(k)=='}') arrinitbrac = false;
                                   if(line.charAt(k)==',' && arrinitbrac==false && commafound ==false){
                                       commafound = true;
                                       if(line.charAt(i)!=' ') aux+="float "+line.substring(i,k);
                                       else aux+="float"+line.substring(i,k);
                                       i=k+1;
                                       j=k+1;
                                       break;
                                   }
                                   if(line.charAt(k)==',' && arrinitbrac==false && commafound ==true){
                                       aux+=", "+line.substring(i,k);
                                       i=k+1;
                                       j=k+1;
                                       break;
                                   }
                                   if(line.charAt(k)==';'&& commafound ==true){
                                       commafound =false;
                                       endofline = true;
                                       aux+=", "+line.substring(i,k)+";";
                                       i=k+1;
                                       j=k+1;
                                       break;
                                   }
                                   if(line.charAt(k)==';'&& commafound ==false){
                                       endofline = true;
                                       if(line.charAt(i)!=' ') aux+="float "+line.substring(i,k)+";";
                                       else aux+="float"+line.substring(i,k)+";";
                                       i=k+1;
                                       j=k+1;
                                       break;
                                   }
                                   k++;
                               }
                           }
                           else{
                               boolean arrbrac = false;
                               while(true){
                                   if(line.charAt(k)=='{'){
                                       arrbrac = true;
                                   }
                                   if(line.charAt(k)=='}'){
                                       arrbrac = false;
                                   }
                                   if(line.charAt(k)==','&&arrbrac==false){
                                       i=k+1;
                                       j=k+1;
                                       break;
                                   }
                                   if(line.charAt(k)==';'){
                                       if(aux.length()>1){
                                           if(line.charAt(aux.length()-1)==','){
                                           aux = aux.substring(0,aux.length()-1);
                                           aux+=";";
                                           }
                                           else if(line.charAt(aux.length()-2)==','){
                                               aux = aux.substring(0,aux.length()-2);
                                               aux+=";";
                                           }
                                           else aux+=";";
                                       }
                                       endofline = true;
                                       i=k+1;
                                       j=k+1;
                                       break;
                                   }
                                   k++;
                               }
                           }
                           if(endofline == true){
                               endofline = false;
                               break;
                           }
                       }
                   k++;
                   }
                   i=k;
                   arrinitbrac = false;
                   commafound = false;
               }
               else if(i+4<line.length()&&(line.substring(i,i+4)).equals("bool")){
                   i = i+4;
                   int j = i+1;
                   while(Character.isLetterOrDigit(line.charAt(j))==false)j++;
                   int k=j+1;
                   while(true){
                       if((Character.isLetterOrDigit(line.charAt(k)))==false){
                           exist = searchDefinition(line.substring(j,k),k,Index);
                           if(exist==true){
                               while(true){
                                   if(line.charAt(k)=='{') arrinitbrac = true;
                                   if(line.charAt(k)=='}') arrinitbrac = false;
                                   if(line.charAt(k)==',' && arrinitbrac==false && commafound ==false){
                                       commafound = true;
                                       if(line.charAt(i)!=' ') aux+="bool "+line.substring(i,k);
                                       else aux+="bool"+line.substring(i,k);
                                       i=k+1;
                                       j=k+1;
                                       break;
                                   }
                                   if(line.charAt(k)==',' && arrinitbrac==false && commafound ==true){
                                       aux+=", "+line.substring(i,k);
                                       i=k+1;
                                       j=k+1;
                                       break;
                                   }
                                   if(line.charAt(k)==';'&& commafound ==true){
                                       commafound =false;
                                       endofline = true;
                                       aux+=", "+line.substring(i,k)+";";
                                       i=k+1;
                                       j=k+1;
                                       break;
                                   }
                                   if(line.charAt(k)==';'&& commafound ==false){
                                       endofline = true;
                                       if(line.charAt(i)!=' ') aux+="bool "+line.substring(i,k)+";";
                                       else aux+="bool"+line.substring(i,k)+";";
                                       i=k+1;
                                       j=k+1;
                                       break;
                                   }
                                   k++;
                               }
                           }
                           else{
                               boolean arrbrac = false;
                               while(true){
                                   if(line.charAt(k)=='{'){
                                       arrbrac = true;
                                   }
                                   if(line.charAt(k)=='}'){
                                       arrbrac = false;
                                   }
                                   if(line.charAt(k)==','&&arrbrac==false){
                                       i=k+1;
                                       j=k+1;
                                       break;
                                   }
                                   if(line.charAt(k)==';'){
                                       if(aux.length()>1){
                                           if(line.charAt(aux.length()-1)==','){
                                           aux = aux.substring(0,aux.length()-1);
                                           aux+=";";
                                           }
                                           else if(line.charAt(aux.length()-2)==','){
                                               aux = aux.substring(0,aux.length()-2);
                                               aux+=";";
                                           }
                                           else aux+=";";
                                       }
                                       endofline = true;
                                       i=k+1;
                                       j=k+1;
                                       break;
                                   }
                                   k++;
                               }
                           }
                           if(endofline == true){
                               endofline = false;
                               break;
                           }
                       }
                   k++;
                   }
                   i=k;
                   arrinitbrac = false;
                   commafound = false;
               }
               else if(i+4<line.length()&&(line.substring(i,i+4)).equals("char")){
                   i = i+4;
                   int j = i+1;
                   while(Character.isLetterOrDigit(line.charAt(j))==false)j++;
                   int k=j+1;
                   while(true){
                       if((Character.isLetterOrDigit(line.charAt(k)))==false){
                           exist = searchDefinition(line.substring(j,k),k,Index);
                           if(exist==true){
                               while(true){
                                   if(line.charAt(k)=='{') arrinitbrac = true;
                                   if(line.charAt(k)=='}') arrinitbrac = false;
                                   if(line.charAt(k)==',' && arrinitbrac==false && commafound ==false){
                                       commafound = true;
                                       if(line.charAt(i)!=' ') aux+="char "+line.substring(i,k);
                                       else aux+="char"+line.substring(i,k);
                                       i=k+1;
                                       j=k+1;
                                       break;
                                   }
                                   if(line.charAt(k)==',' && arrinitbrac==false && commafound ==true){
                                       aux+=", "+line.substring(i,k);
                                       i=k+1;
                                       j=k+1;
                                       break;
                                   }
                                   if(line.charAt(k)==';'&& commafound ==true){
                                       commafound =false;
                                       endofline = true;
                                       aux+=", "+line.substring(i,k)+";";
                                       i=k+1;
                                       j=k+1;
                                       break;
                                   }
                                   if(line.charAt(k)==';'&& commafound ==false){
                                       endofline = true;
                                       if(line.charAt(i)!=' ') aux+="char "+line.substring(i,k)+";";
                                       else aux+="char"+line.substring(i,k)+";";
                                       i=k+1;
                                       j=k+1;
                                       break;
                                   }
                                   k++;
                               }
                           }
                           else{
                               boolean arrbrac = false;
                               while(true){
                                   if(line.charAt(k)=='{'){
                                       arrbrac = true;
                                   }
                                   if(line.charAt(k)=='}'){
                                       arrbrac = false;
                                   }
                                   if(line.charAt(k)==','&&arrbrac==false){
                                       i=k+1;
                                       j=k+1;
                                       break;
                                   }
                                   if(line.charAt(k)==';'){
                                       if(aux.length()>1){
                                           if(line.charAt(aux.length()-1)==','){
                                           aux = aux.substring(0,aux.length()-1);
                                           aux+=";";
                                           }
                                           else if(line.charAt(aux.length()-2)==','){
                                               aux = aux.substring(0,aux.length()-2);
                                               aux+=";";
                                           }
                                           else aux+=";";
                                       }
                                       endofline = true;
                                       i=k+1;
                                       j=k+1;
                                       break;
                                   }
                                   k++;
                               }
                           }
                           if(endofline == true){
                               endofline = false;
                               break;
                           }
                       }
                   k++;
                   }
                   i=k;
                   arrinitbrac = false;
                   commafound = false;
               }
               else if(i+6<line.length()&&(line.substring(i,i+6)).equals("double")){
                   i = i+6;
                   int j = i+1;
                   while(Character.isLetterOrDigit(line.charAt(j))==false)j++;
                   int k=j+1;
                   while(true){
                       if((Character.isLetterOrDigit(line.charAt(k)))==false){
                           exist = searchDefinition(line.substring(j,k),k,Index);
                           if(exist==true){
                               while(true){
                                   if(line.charAt(k)=='{') arrinitbrac = true;
                                   if(line.charAt(k)=='}') arrinitbrac = false;
                                   if(line.charAt(k)==',' && arrinitbrac==false && commafound ==false){
                                       commafound = true;
                                       if(line.charAt(i)!=' ') aux+="double "+line.substring(i,k);
                                       else aux+="double"+line.substring(i,k);
                                       i=k+1;
                                       j=k+1;
                                       break;
                                   }
                                   if(line.charAt(k)==',' && arrinitbrac==false && commafound ==true){
                                       aux+=", "+line.substring(i,k);
                                       i=k+1;
                                       j=k+1;
                                       break;
                                   }
                                   if(line.charAt(k)==';'&& commafound ==true){
                                       commafound =false;
                                       endofline = true;
                                       aux+=", "+line.substring(i,k)+";";
                                       i=k+1;
                                       j=k+1;
                                       break;
                                   }
                                   if(line.charAt(k)==';'&& commafound ==false){
                                       endofline = true;
                                       if(line.charAt(i)!=' ') aux+="double "+line.substring(i,k)+";";
                                       else aux+="double"+line.substring(i,k)+";";
                                       i=k+1;
                                       j=k+1;
                                       break;
                                   }
                                   k++;
                               }
                           }
                           else{
                               boolean arrbrac = false;
                               while(true){
                                   if(line.charAt(k)=='{'){
                                       arrbrac = true;
                                   }
                                   if(line.charAt(k)=='}'){
                                       arrbrac = false;
                                   }
                                   if(line.charAt(k)==','&&arrbrac==false){
                                       i=k+1;
                                       j=k+1;
                                       break;
                                   }
                                   if(line.charAt(k)==';'){
                                       if(aux.length()>1){
                                           if(line.charAt(aux.length()-1)==','){
                                           aux = aux.substring(0,aux.length()-1);
                                           aux+=";";
                                           }
                                           else if(line.charAt(aux.length()-2)==','){
                                               aux = aux.substring(0,aux.length()-2);
                                               aux+=";";
                                           }
                                           else aux+=";";
                                       }
                                       endofline = true;
                                       i=k+1;
                                       j=k+1;
                                       break;
                                   }
                                   k++;
                               }
                           }
                           if(endofline == true){
                               endofline = false;
                               break;
                           }
                       }
                   k++;
                   }
                   i=k;
                   arrinitbrac = false;
                   commafound = false;
               }
               
               else if(i+2<line.length()&&(line.substring(i,i+2)).equals("if")){
                   int semicolcounter=0,braccounter=0,j;
                   boolean check=false;
                   boolean onelinecheck = false;
                   j = i+1;
                   while(true){
                       if(line.charAt(j)==';'){
                           onelinecheck = true;
                           break;
                       }
                       if(line.charAt(j)=='{') {
                           braccounter++;
                           break;
                       }
                       j++;
                   }
                   j++;
                   while(onelinecheck==false){
                       if(semicolcounter>1){
                           check = true;
                       }
                       if(braccounter>1){
                           check = true;
                       }
                       if(braccounter==0){
                           break;
                       }
                       if(line.charAt(j)=='{') braccounter++;
                       if(line.charAt(j)==';')semicolcounter++;
                       if(line.charAt(j)=='}') braccounter--;
                       j++;
                   }
                   if(onelinecheck==true||check==true){
                       aux+=line.substring(i,j);
                       i=j;
                   }
                   else{
                       if(semicolcounter==0){
                           i=j;
                       }
                       else{
                            int k=i+1;
                            while(true){
                                if(line.charAt(k)=='{') {
                                    aux+=line.substring(i,k);
                                    i=k+1;
                                    aux+=line.substring(i,j-1);
                                    i=j;
                                    break;
                                }
                                k++;
                            }
                       }
                   }
               }
               
               else if(i+7<line.length()&&(line.substring(i,i+7)).equals("else if")){
                   int semicolcounter=0,braccounter=0,j;
                   boolean check=false;
                   boolean onelinecheck = false;
                   j = i+1;
                   while(true){
                       if(line.charAt(j)==';'){
                           onelinecheck = true;
                           break;
                       }
                       if(line.charAt(j)=='{') {
                           braccounter++;
                           break;
                       }
                       j++;
                   }
                   j++;
                   while(onelinecheck==false){
                       if(semicolcounter>1){
                           check = true;
                       }
                       if(braccounter>1){
                           check = true;
                       }
                       if(braccounter==0){
                           break;
                       }
                       if(line.charAt(j)=='{') braccounter++;
                       if(line.charAt(j)==';')semicolcounter++;
                       if(line.charAt(j)=='}') braccounter--;
                       j++;
                   }
                   if(onelinecheck==true||check==true){
                       aux+=line.substring(i,j);
                       i=j;
                   }
                   else{
                       if(semicolcounter==0){
                           i=j;
                       }
                       else{
                            int k=i+1;
                            while(true){
                                if(line.charAt(k)=='{') {
                                    aux+=line.substring(i,k);
                                    i=k+1;
                                    aux+=line.substring(i,j-1);
                                    i=j;
                                    break;
                                }
                                k++;
                            }
                       }
                   }
               }
               
               else if(i+4<line.length()&&(line.substring(i,i+4)).equals("else")){
                   int semicolcounter=0,braccounter=0,j;
                   boolean check=false;
                   boolean onelinecheck = false;
                   j = i+1;
                   while(true){
                       if(line.charAt(j)==';'){
                           onelinecheck = true;
                           break;
                       }
                       if(line.charAt(j)=='{') {
                           braccounter++;
                           break;
                       }
                       j++;
                   }
                   j++;
                   while(onelinecheck==false){
                       if(semicolcounter>1){
                           check = true;
                       }
                       if(braccounter>1){
                           check = true;
                       }
                       if(braccounter==0){
                           break;
                       }
                       if(line.charAt(j)=='{') braccounter++;
                       if(line.charAt(j)==';')semicolcounter++;
                       if(line.charAt(j)=='}') braccounter--;
                       j++;
                   }
                   if(onelinecheck==true||check==true){
                       aux+=line.substring(i,j);
                       i=j;
                   }
                   else{
                       if(semicolcounter==0){
                           i=j;
                       }
                       else{
                            int k=i+1;
                            while(true){
                                if(line.charAt(k)=='{') {
                                    aux+=line.substring(i,k);
                                    i=k+1;
                                    aux+=line.substring(i,j-1);
                                    i=j;
                                    break;
                                }
                                k++;
                            }
                       }
                   }
               }
               
               else if(i+5<line.length()&&(line.substring(i,i+5)).equals("while")){
                   int semicolcounter=0,braccounter=0,j;
                   boolean check=false;
                   boolean onelinecheck = false;
                   j = i+1;
                   while(true){
                       if(line.charAt(j)==';'){
                           onelinecheck = true;
                           break;
                       }
                       if(line.charAt(j)=='{') {
                           braccounter++;
                           break;
                       }
                       j++;
                   }
                   j++;
                   while(onelinecheck==false){
                       if(semicolcounter>1){
                           check = true;
                       }
                       if(braccounter>1){
                           check = true;
                       }
                       if(braccounter==0){
                           break;
                       }
                       if(line.charAt(j)=='{') braccounter++;
                       if(line.charAt(j)==';')semicolcounter++;
                       if(line.charAt(j)=='}') braccounter--;
                       j++;
                   }
                   if(onelinecheck==true||check==true){
                       aux+=line.substring(i,j);
                       i=j;
                   }
                   else{
                       if(semicolcounter==0){
                           i=j;
                       }
                       else{
                            int k=i+1;
                            while(true){
                                if(line.charAt(k)=='{') {
                                    aux+=line.substring(i,k);
                                    i=k+1;
                                    aux+=line.substring(i,j-1);
                                    i=j;
                                    break;
                                }
                                k++;
                            }
                       }
                   }
               }
               
               else if(i+3<line.length()&&(line.substring(i,i+3)).equals("for")){
                   int semicolcounter=2,braccounter=0,j;
                   boolean check=false;
                   boolean onelinecheck = false;
                   j = i+1;
                   while(true){
                       if(line.charAt(j)==';'&&semicolcounter>0){
                           semicolcounter--;
                       }
                       else if(line.charAt(j)==';'&&semicolcounter==0){
                           onelinecheck = true;
                           break;
                       }
                       if(line.charAt(j)=='{') {
                           braccounter++;
                           break;
                       }
                       j++;
                   }
                   j++;
                   while(onelinecheck==false){
                       if(semicolcounter>1){
                           check = true;
                       }
                       if(braccounter>1){
                           check = true;
                       }
                       if(braccounter==0){
                           break;
                       }
                       if(line.charAt(j)=='{') braccounter++;
                       if(line.charAt(j)==';')semicolcounter++;
                       if(line.charAt(j)=='}') braccounter--;
                       j++;
                   }
                   if(onelinecheck==true||check==true){
                       aux+=line.substring(i,j);
                       i=j;
                   }
                   else{
                       if(semicolcounter==0){
                           i=j;
                       }
                       else{
                            int k=i+1;
                            while(true){
                                if(line.charAt(k)=='{') {
                                    aux+=line.substring(i,k);
                                    i=k+1;
                                    aux+=line.substring(i,j-1);
                                    i=j;
                                    break;
                                }
                                k++;
                            }
                       }
                   }
               }
               
               else if((Character.isLetterOrDigit(line.charAt(i)))){
                   int j = i;
                    while(true){
                        if(line.charAt(j)==';'){
                            aux+=line.substring(i,j)+";";
                            break;
                        }
                        j++;
                    }
                    i=j;
               }
           }
       }
       gFunctionsArray[Index][2] = aux+="}";
    }
    
    boolean searchDefinition(String s, int index, int funcindex){
        int size = s.length();
        if(size==1&&Character.isDigit(s.charAt(0))){return true;}
        if(s.equals("int")||s.equals("bool")||s.equals("char")||s.equals("float")||s.equals("double")||s.equals("string")){
        return true;
        }

        String line = gFunctionsArray[funcindex][2];
        for(int i=index;i<line.length();i++){
            if(i+size<line.length()&&(line.substring(i,i+size)).equals(s)){
                if(i-1>0&&(Character.isLetterOrDigit(line.charAt(i-1)))==false&&(Character.isLetterOrDigit(line.charAt(i+size)))==false) {
                    return true;
                }
            }
        }
        return false;
    }
    
    boolean searchProto(String s){
        int size = s.length();
        
        if(size==1&&Character.isDigit(s.charAt(0))){return true;}
        if(s.equals("int")||s.equals("bool")||s.equals("char")||s.equals("float")||s.equals("double")||s.equals("string")){
        return true;
        }
        
        int j=0;
        while(j<gFunctionCounter){
            if(gFunctionsArray[j][0].equals("t")==false){
                String line = gFunctionsArray[j][1];
                for(int i=0;i<line.length();i++){
                    if(i+size<line.length()&&(line.substring(i,i+size)).equals(s)){
                        if(i-1>0&&(Character.isLetterOrDigit(line.charAt(i-1)))==false&&(Character.isLetterOrDigit(line.charAt(i+size)))==false){
                            if(gFunctionsArray[j][0].equals("f")){
                                gFunctionsArray[j][0] = "t";
                                return true;
                            }
                            else if(gFunctionsArray[j][0].equals("e")){
                                return false;
                            }
                        }
                    }
                }
            }
            j++;
        }
        return true;
    }
    
    void emptyfunc(){
        for(int i=0;i<gFunctionCounter;i++){
            if(gFunctionsArray[i][2].equals("}")){
               gFunctionsArray[i][0] = "e";
            }
        }
    }
    
    void updateFuncUsage(){
        boolean stringremoved = false;
        boolean check = false;
        int linefirst=0;
        String aux="";
        String line = gFunctionsArray[mainFunctionIndex][2];
        for(int i=0;i<line.length();i++){
            if((Character.isLetterOrDigit(line.charAt(i)))){
                if(check==false){
                    linefirst=i;
                    check=true;
                }
               int j = i+1;
               while(true){
                   if((Character.isLetterOrDigit(line.charAt(j)))==false){
                       if(line.substring(i,j).equals("int")==false||line.substring(i,j).equals("bool")==false||line.substring(i,j).equals("char")==false||line.substring(i,j).equals("float")==false||line.substring(i,j).equals("double")==false||line.substring(i,j).equals("string")==false){
                           CodeHeaders.updateHeaderUsage(line.substring(i,j));
                           CodeGlobalVariables.updateGlobalVar(line.substring(i,j));
                           if(searchProto(line.substring(i,j))==false){
                           while(true){
                               if(line.charAt(j)==';'){
                                   aux+=line.substring(0,linefirst);
                                   aux+=line.substring(j+1,line.length());
                                   line = aux;
                                   check=false;
                                   stringremoved = true;
                                   break;
                               }
                               j++;
                           }
                       }
                       break;
                       }
                   }
                   j++;
               }
               i=j;
            }
            if(i<line.length()&&line.charAt(i)==';'){
                check=false;
            }
        }
        gFunctionsArray[mainFunctionIndex][2] = line;
        if(stringremoved == true){
            emptyfunc();
            updateFuncUsage();
        }
        
        check = false;
        linefirst=0;
        stringremoved = false;
        
        int index = 0;
        while(index<gFunctionCounter){
            if(gFunctionsArray[index][0].equals("t")){
                aux="";
                line = gFunctionsArray[index][2];
                for(int i=0;i<line.length();i++){
                    if((Character.isLetterOrDigit(line.charAt(i)))){
                       if(check==false){
                           linefirst=i;
                           check=true;
                       }
                       int j = i+1;
                       while(true){
                           if((Character.isLetterOrDigit(line.charAt(j)))==false){
                               if(line.substring(i,j).equals("int")==false||line.substring(i,j).equals("bool")==false||line.substring(i,j).equals("char")==false||line.substring(i,j).equals("float")==false||line.substring(i,j).equals("double")==false||line.substring(i,j).equals("string")==false){
                                   CodeHeaders.updateHeaderUsage(line.substring(i,j));
                                   CodeGlobalVariables.updateGlobalVar(line.substring(i,j));
                                   if(searchProto(line.substring(i,j))==false){
                                        while(true){
                                            if(line.charAt(j)==';'){
                                                aux+=line.substring(0,linefirst);
                                                aux+=line.substring(j+1,line.length());
                                                line = aux;
                                                check=false;
                                                stringremoved = true;
                                                break;
                                            }
                                            j++;
                                        }
                                   }
                               break;
                               }
                           }
                           j++;
                       }
                       i=j;
                    }
                    if(i<line.length()&&line.charAt(i)==';'){
                           check=false;
                    }
                }
                gFunctionsArray[index][2] = line;
                if(stringremoved == true){
                    emptyfunc();
                    updateFuncUsage();
                }
            }
            index++;
        }
    }
}