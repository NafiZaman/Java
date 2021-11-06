
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
public class CodeInitializer {
    public static String originalCode, optimizedCode;
    
    CodeHeaders CH;
    CodeGlobalVariables CGV;
    CodeGlobalFunctions CGF;
    CodeClass CC;
    int bracketCounter;
    boolean notafunctioncheck = false;
    boolean firstbraccheck = false;
    boolean arrinitbraccheck = false;
    
    CodeInitializer() throws IOException{
        CH = new CodeHeaders();
        CGV = new CodeGlobalVariables();
        CGF = new CodeGlobalFunctions();
        CC = new CodeClass();
    }

    String init(String code) throws FileNotFoundException, IOException{
        originalCode = code;
        
        codeLoader(originalCode);
        CH.includeAsset();
        
        for(int i=0;i<CGF.gFunctionCounter;i++){
            CGF.functionVerifier(i);
        }
        CGF.emptyfunc();
        CGF.updateFuncUsage();
        compile();
        return optimizedCode;
    }

    void codeLoader(String line){

        line = line.replaceAll("\\s+"," ");
        for(int i=0;i<line.length();i++){
           
            if(line.charAt(i)=='#'){ //header Extractor
               int j = i+1;
               while(true){
                   if(line.charAt(j)=='>'){
                       CH.includeHeader(line.substring(i, j+1));
                       break;
                   }
                   j++;
               }
               i = j;
           }
            
           if(line.charAt(i)=='c'){
               if((line.substring(i,i+5)).equals("class")){
                    int j=i+1;
                    while(true){
                        if(line.substring(j,j+2).equals("};")){
                            CC.includeClass(line.substring(i, j+2));
                            break;
                        }
                        if(line.substring(j,j+3).equals("} ;")){
                            CC.includeClass(line.substring(i, j+3));
                            break;
                        }
                        j++;
                    }
                    i=j;
               }
           }
            
           if(line.charAt(i)=='i'){ //int variable/function Extractor
               if((line.substring(i,i+3)).equals("int")){
                   i =i+3;
                   int j = i;
                   while(true){
                       if(line.charAt(j)=='(') firstbraccheck = true;
                       if(line.charAt(j)==')') firstbraccheck = false;
                       if(line.charAt(j)=='{') arrinitbraccheck = true;
                       if(line.charAt(j)=='=') notafunctioncheck = true;
                       if(line.charAt(j)=='{'&&notafunctioncheck==false){
                           bracketCounter=0;
                           j = j+1;
                           while(true){
                               if(line.charAt(j)=='{') bracketCounter++;
                               if(line.charAt(j)=='}'){
                                   if(bracketCounter==0){
                                       CGF.includegFunction("int" + line.substring(i, j+1));
                                       break;
                                   }
                                   else bracketCounter--;
                               }
                               j++;
                           }
                        break;
                       }
                       if(line.charAt(j)==','&& firstbraccheck == false){
                           if(arrinitbraccheck == true){
                               while(true){
                                   if(line.charAt(j)=='}'){
                                       if(line.charAt(i)==' '){
                                          CGV.includegVariable("int"+line.substring(i,j+1)+";");
                                          i=j+1;
                                          arrinitbraccheck = false;
                                          break;  
                                       }
                                       else{
                                          CGV.includegVariable("int "+line.substring(i,j+1)+";");
                                          i=j+1;
                                          arrinitbraccheck = false;
                                          break; 
                                       }
                                   }
                                   j++;
                               }
                           }
                           else{
                                if(line.charAt(i)==' '){
                                    CGV.includegVariable("int"+line.substring(i,j)+";");
                                    i=j+1;
                                }
                                else{
                                    CGV.includegVariable("int "+line.substring(i,j)+";");
                                    i=j+1;
                                }
                           }
                       }
                       if(line.charAt(j)==';'){
                            if(line.charAt(i)==' '){
                                CGV.includegVariable("int"+line.substring(i,j)+";");
                                i=j+1;
                                break;
                            }
                            else{
                                CGV.includegVariable("int "+line.substring(i,j)+";");
                                i=j+1;
                                break;
                            }
                       }
                       j++;
                   }
                   i = j;
                   notafunctioncheck = false;
                   firstbraccheck = false;
                   arrinitbraccheck = false;
               }
           }
           
           if(line.charAt(i)=='s'){ //int variable/function Extractor
               if((line.substring(i,i+6)).equals("string")){
                   i =i+6;
                   int j = i;
                   while(true){
                       if(line.charAt(j)=='(') firstbraccheck = true;
                       if(line.charAt(j)==')') firstbraccheck = false;
                       if(line.charAt(j)=='{') arrinitbraccheck = true;
                       if(line.charAt(j)=='=') notafunctioncheck = true;
                       if(line.charAt(j)=='{'&&notafunctioncheck==false){
                           bracketCounter=0;
                           j = j+1;
                           while(true){
                               if(line.charAt(j)=='{') bracketCounter++;
                               if(line.charAt(j)=='}'){
                                   if(bracketCounter==0){
                                       CGF.includegFunction("string" + line.substring(i, j+1));
                                       break;
                                   }
                                   else bracketCounter--;
                               }
                               j++;
                           }
                        break;
                       }
                       if(line.charAt(j)==','&& firstbraccheck == false){
                           if(arrinitbraccheck == true){
                               while(true){
                                   if(line.charAt(j)=='}'){
                                       if(line.charAt(i)==' '){
                                          CGV.includegVariable("string"+line.substring(i,j+1)+";");
                                          i=j+1;
                                          arrinitbraccheck = false;
                                          break;  
                                       }
                                       else{
                                          CGV.includegVariable("string "+line.substring(i,j+1)+";");
                                          i=j+1;
                                          arrinitbraccheck = false;
                                          break; 
                                       }
                                   }
                                   j++;
                               }
                           }
                           else{
                                if(line.charAt(i)==' '){
                                    CGV.includegVariable("string"+line.substring(i,j)+";");
                                    i=j+1;
                                }
                                else{
                                    CGV.includegVariable("string "+line.substring(i,j)+";");
                                    i=j+1;
                                }
                           }
                       }
                       if(line.charAt(j)==';'){
                            if(line.charAt(i)==' '){
                                CGV.includegVariable("string"+line.substring(i,j)+";");
                                i=j+1;
                                break;
                            }
                            else{
                                CGV.includegVariable("string "+line.substring(i,j)+";");
                                i=j+1;
                                break;
                            }
                       }
                       j++;
                   }
                   i = j;
                   notafunctioncheck = false;
                   firstbraccheck = false;
                   arrinitbraccheck = false;
               }
           }
           
           if(line.charAt(i)=='f'){ //float variable/function Extractor
               if((line.substring(i,i+5)).equals("float")){
                   i =i+5;
                   int j = i;
                   while(true){
                       if(line.charAt(j)=='(') firstbraccheck = true;
                       if(line.charAt(j)==')') firstbraccheck = false;
                       if(line.charAt(j)=='{') arrinitbraccheck = true;
                       if(line.charAt(j)=='=') notafunctioncheck = true;
                       if(line.charAt(j)=='{'&&notafunctioncheck==false){
                           bracketCounter=0;
                           j = j+1;
                           while(true){
                               if(line.charAt(j)=='{') bracketCounter++;
                               if(line.charAt(j)=='}'){
                                   if(bracketCounter==0){
                                       CGF.includegFunction("float" + line.substring(i, j+1));
                                       break;
                                   }
                                   else bracketCounter--;
                               }
                               j++;
                           }
                        break;
                       }
                       if(line.charAt(j)==','&& firstbraccheck == false){
                           if(arrinitbraccheck == true){
                               while(true){
                                   if(line.charAt(j)=='}'){
                                       if(line.charAt(i)==' '){
                                          CGV.includegVariable("float"+line.substring(i,j+1)+";");
                                          i=j+1;
                                          arrinitbraccheck = false;
                                          break;  
                                       }
                                       else{
                                          CGV.includegVariable("float "+line.substring(i,j+1)+";");
                                          i=j+1;
                                          arrinitbraccheck = false;
                                          break; 
                                       }
                                   }
                                   j++;
                               }
                           }
                           else{
                                if(line.charAt(i)==' '){
                                    CGV.includegVariable("float"+line.substring(i,j)+";");
                                    i=j+1;
                                }
                                else{
                                    CGV.includegVariable("float "+line.substring(i,j)+";");
                                    i=j+1;
                                }
                           }
                       }
                       if(line.charAt(j)==';'){
                            if(line.charAt(i)==' '){
                                CGV.includegVariable("float"+line.substring(i,j)+";");
                                i=j+1;
                                break;
                            }
                            else{
                                CGV.includegVariable("float "+line.substring(i,j)+";");
                                i=j+1;
                                break;
                            }
                       }
                       j++;
                   }
                   i = j;
                   notafunctioncheck = false;
                   firstbraccheck = false;
                   arrinitbraccheck = false;
               }
           }
           
           if(line.charAt(i)=='b'){ //bool variable/function Extractor
               if((line.substring(i,i+4)).equals("bool")){
                   i =i+4;
                   int j = i;
                   while(true){
                       if(line.charAt(j)=='(') firstbraccheck = true;
                       if(line.charAt(j)==')') firstbraccheck = false;
                       if(line.charAt(j)=='{') arrinitbraccheck = true;
                       if(line.charAt(j)=='=') notafunctioncheck = true;
                       if(line.charAt(j)=='{'&&notafunctioncheck==false){
                           bracketCounter=0;
                           j = j+1;
                           while(true){
                               if(line.charAt(j)=='{') bracketCounter++;
                               if(line.charAt(j)=='}'){
                                   if(bracketCounter==0){
                                       CGF.includegFunction("bool" + line.substring(i, j+1));
                                       break;
                                   }
                                   else bracketCounter--;
                               }
                               j++;
                           }
                        break;
                       }
                       if(line.charAt(j)==','&& firstbraccheck == false){
                           if(arrinitbraccheck == true){
                               while(true){
                                   if(line.charAt(j)=='}'){
                                       if(line.charAt(i)==' '){
                                          CGV.includegVariable("bool"+line.substring(i,j+1)+";");
                                          i=j+1;
                                          arrinitbraccheck = false;
                                          break;  
                                       }
                                       else{
                                          CGV.includegVariable("bool "+line.substring(i,j+1)+";");
                                          i=j+1;
                                          arrinitbraccheck = false;
                                          break; 
                                       }
                                   }
                                   j++;
                               }
                           }
                           else{
                                if(line.charAt(i)==' '){
                                    CGV.includegVariable("bool"+line.substring(i,j)+";");
                                    i=j+1;
                                }
                                else{
                                    CGV.includegVariable("bool "+line.substring(i,j)+";");
                                    i=j+1;
                                }
                           }
                       }
                       if(line.charAt(j)==';'){
                            if(line.charAt(i)==' '){
                                CGV.includegVariable("bool"+line.substring(i,j)+";");
                                i=j+1;
                                break;
                            }
                            else{
                                CGV.includegVariable("bool "+line.substring(i,j)+";");
                                i=j+1;
                                break;
                            }
                       }
                       j++;
                   }
                   i = j;
                   notafunctioncheck = false;
                   firstbraccheck = false;
                   arrinitbraccheck = false;
               }
           }
           
           if(line.charAt(i)=='c'){ //char variable/function Extractor
               if((line.substring(i,i+4)).equals("char")){
                   i =i+4;
                   int j = i;
                   while(true){
                       if(line.charAt(j)=='(') firstbraccheck = true;
                       if(line.charAt(j)==')') firstbraccheck = false;
                       if(line.charAt(j)=='{') arrinitbraccheck = true;
                       if(line.charAt(j)=='=') notafunctioncheck = true;
                       if(line.charAt(j)=='{'&&notafunctioncheck==false){
                           bracketCounter=0;
                           j = j+1;
                           while(true){
                               if(line.charAt(j)=='{') bracketCounter++;
                               if(line.charAt(j)=='}'){
                                   if(bracketCounter==0){
                                       CGF.includegFunction("char" + line.substring(i, j+1));
                                       break;
                                   }
                                   else bracketCounter--;
                               }
                               j++;
                           }
                        break;
                       }
                       if(line.charAt(j)==','&& firstbraccheck == false){
                           if(arrinitbraccheck == true){
                               while(true){
                                   if(line.charAt(j)=='}'){
                                       if(line.charAt(i)==' '){
                                          CGV.includegVariable("char"+line.substring(i,j+1)+";");
                                          i=j+1;
                                          arrinitbraccheck = false;
                                          break;  
                                       }
                                       else{
                                          CGV.includegVariable("char "+line.substring(i,j+1)+";");
                                          i=j+1;
                                          arrinitbraccheck = false;
                                          break; 
                                       }
                                   }
                                   j++;
                               }
                           }
                           else{
                                if(line.charAt(i)==' '){
                                    CGV.includegVariable("char"+line.substring(i,j)+";");
                                    i=j+1;
                                }
                                else{
                                    CGV.includegVariable("char "+line.substring(i,j)+";");
                                    i=j+1;
                                }
                           }
                       }
                       if(line.charAt(j)==';'){
                            if(line.charAt(i)==' '){
                                CGV.includegVariable("char"+line.substring(i,j)+";");
                                i=j+1;
                                break;
                            }
                            else{
                                CGV.includegVariable("char "+line.substring(i,j)+";");
                                i=j+1;
                                break;
                            }
                       }
                       j++;
                   }
                   i = j;
                   notafunctioncheck = false;
                   firstbraccheck = false;
                   arrinitbraccheck = false;
               }
           }
           
           if(line.charAt(i)=='d'){ //double variable/function Extractor
               if((line.substring(i,i+6)).equals("double")){
                   i =i+6;
                   int j = i;
                   while(true){
                       if(line.charAt(j)=='(') firstbraccheck = true;
                       if(line.charAt(j)==')') firstbraccheck = false;
                       if(line.charAt(j)=='{') arrinitbraccheck = true;
                       if(line.charAt(j)=='=') notafunctioncheck = true;
                       if(line.charAt(j)=='{'&&notafunctioncheck==false){
                           bracketCounter=0;
                           j = j+1;
                           while(true){
                               if(line.charAt(j)=='{') bracketCounter++;
                               if(line.charAt(j)=='}'){
                                   if(bracketCounter==0){
                                       CGF.includegFunction("double" + line.substring(i, j+1));
                                       break;
                                   }
                                   else bracketCounter--;
                               }
                               j++;
                           }
                        break;
                       }
                       if(line.charAt(j)==','&& firstbraccheck == false){
                           if(arrinitbraccheck == true){
                               while(true){
                                   if(line.charAt(j)=='}'){
                                       if(line.charAt(i)==' '){
                                          CGV.includegVariable("double"+line.substring(i,j+1)+";");
                                          i=j+1;
                                          arrinitbraccheck = false;
                                          break;  
                                       }
                                       else{
                                          CGV.includegVariable("double "+line.substring(i,j+1)+";");
                                          i=j+1;
                                          arrinitbraccheck = false;
                                          break; 
                                       }
                                   }
                                   j++;
                               }
                           }
                           else{
                                if(line.charAt(i)==' '){
                                    CGV.includegVariable("double"+line.substring(i,j)+";");
                                    i=j+1;
                                }
                                else{
                                    CGV.includegVariable("double "+line.substring(i,j)+";");
                                    i=j+1;
                                }
                           }
                       }
                       if(line.charAt(j)==';'){
                            if(line.charAt(i)==' '){
                                CGV.includegVariable("double"+line.substring(i,j)+";");
                                i=j+1;
                                break;
                            }
                            else{
                                CGV.includegVariable("double "+line.substring(i,j)+";");
                                i=j+1;
                                break;
                            }
                       }
                       j++;
                   }
                   i = j;
                   notafunctioncheck = false;
                   firstbraccheck = false;
                   arrinitbraccheck = false;
               }
           }
           
           if(line.charAt(i)=='v'){ //void variable/function Extractor
               if((line.substring(i,i+4)).equals("void")){
                   i =i+4;
                   int j = i;
                   while(true){
                       if(line.charAt(j)=='{'){
                           bracketCounter=0;
                           j = j+1;
                           while(true){
                               if(line.charAt(j)=='{') bracketCounter++;
                               if(line.charAt(j)=='}'){
                                   if(bracketCounter==0){
                                       CGF.includegFunction("void" + line.substring(i, j+1));
                                       break;
                                   }
                                   else bracketCounter--;
                               }
                               j++;
                           }
                           break;
                       }
                       j++;
                   }
                   i = j;
               }
           }
        }
    }
    
    String compile(){
        optimizedCode = "";
        for(int i=0;i<CH.counter;i++){
            if(CH.headerCodeLines[i][0].equals("t")){
                optimizedCode += CH.headerCodeLines[i][1].trim() +"\n";
            }
        }
        optimizedCode += "\nusing namespace std;\n\n";
        for(int i=0;i<CGV.gVariableCounter;i++){
            if(CGV.gVariableArray[i][0].equals("t")){
                optimizedCode += CGV.gVariableArray[i][1].trim() +"\n";
            }
        }
        
        for(int i=0;i<CC.CCcounter;i++){
            optimizedCode+="\n";
            String line = CC.CCLines[i];
            int index = 0;
                for(int j=0;j<line.length();j++){
                    if(line.charAt(j)==':') {
                        optimizedCode += line.substring(index,j+1).trim() + "\n";
                        index = j+1;
                    }
                    if(line.charAt(j)=='f'){
                        if(line.substring(j,j+3).equals("for")){
                            int k=j+1;
                            int semicolon = 0;
                            while(line.charAt(k)!='{'){
                                if(line.charAt(k)==';')semicolon++;
                                if(semicolon==3)break;
                                k++;
                            }
                            optimizedCode += line.substring(j,k+1).trim() + "\n";
                            j = k+1;
                            index = j;
                        }
                    }
                    if(line.charAt(j)==';') {
                        optimizedCode += line.substring(index,j+1).trim() + "\n";
                        index = j+1;
                    }
                    if(line.charAt(j)=='{') {
                        optimizedCode += line.substring(index,j+1).trim() + "\n";
                        index = j+1;
                    }
                    if(line.charAt(j)=='}') {
                        if(line.substring(j,j+2).equals("};")){
                            optimizedCode += line.substring(index,j+2).trim() + "\n";
                            index = j+2;
                        }
                        else if(line.substring(j,j+3).equals("} ;")){
                            optimizedCode += line.substring(index,j+3).trim() + "\n";
                            index = j+3;
                        }
                        else{
                            optimizedCode += line.substring(index,j+1).trim() + "\n";
                            index = j+1;
                        }
                    }
                }
        }
        
        for(int i=0;i<CGF.gFunctionCounter;i++){
            if(CGF.gFunctionsArray[i][0].equals("t")&&i!=CGF.mainFunctionIndex){
                optimizedCode+="\n";
                optimizedCode += CGF.gFunctionsArray[i][1].substring(0,CGF.gFunctionsArray[i][1].length()-1).trim()+";"+"\n";
            }
        }//PROTOTYPING
        
        for(int i=0;i<CGF.gFunctionCounter;i++){
            if(CGF.gFunctionsArray[i][0].equals("t")){
                optimizedCode+="\n";
                optimizedCode += CGF.gFunctionsArray[i][1].trim() +"\n";
                String line = CGF.gFunctionsArray[i][2];
                int index = 0;
                for(int j=0;j<line.length();j++){
                    if(line.charAt(j)=='f'){
                        if(line.substring(j,j+3).equals("for")){
                            int k=j+1;
                            int semicolon = 0;
                            while(line.charAt(k)!='{'){
                                if(line.charAt(k)==';')semicolon++;
                                if(semicolon==3)break;
                                k++;
                            }
                            optimizedCode += line.substring(j,k+1).trim() + "\n";
                            j = k+1;
                            index = j;
                        }
                    }
                    if(line.charAt(j)==';') {
                        optimizedCode += line.substring(index,j+1).trim() + "\n";
                        index = j+1;
                    }
                    if(line.charAt(j)=='{') {
                        optimizedCode += line.substring(index,j+1).trim() + "\n";
                        index = j+1;
                    }
                    if(line.charAt(j)=='}') {
                        optimizedCode += line.substring(index,j+1).trim() + "\n";
                        index = j+1;
                    }
                }
            }
        }
        
        return optimizedCode;
    }
}