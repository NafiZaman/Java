
public class Try_Catch_Block 
{   
    String theCode,line,temp,optimizedCode;
    String []lineInfo;
    int codeLineIndex=0,openingBraceCounter,closingBraceCounter;
    int lines=0,theCodeIndex=0;
    
    public Try_Catch_Block(String code)
    {   
        theCode=code;
        lineInfo=new String[5];
    }
    
    void analyzeCode()
    {   
        line="";
        temp="";
        openingBraceCounter=0;
        closingBraceCounter=0;
        
        for(theCodeIndex=0;theCodeIndex<theCode.length();theCodeIndex++)
        {
            if(theCode.charAt(theCodeIndex)=='\n')
            {
                temp=line.trim();
                
                if(startsWithType()==true && temp.charAt(temp.length()-1)=='{')
                {   
                    openingBraceCounter++;
                    populateCodeLines(line);
                    analyzeFunction(theCodeIndex);

                    line="";
                    openingBraceCounter=0;
                    closingBraceCounter=0;
                }
                else populateCodeLines(line);
                
                line="";
            }
            else line+=theCode.charAt(theCodeIndex);
        }
    }
    
    void analyzeFunction(int lineNumber)
    {   
        boolean tryFound=false;
        line="";
        int inQuote=1;
        
        for(int j=lineNumber+1;j<theCode.length();j++)
        {   
            theCodeIndex++;
            if(theCode.charAt(j)=='"')inQuote*=-1;
            else if(theCode.charAt(j)=='{' && inQuote==1)openingBraceCounter++;
            else if(theCode.charAt(j)=='}' && inQuote==1)
            {
                closingBraceCounter++;
                if(openingBraceCounter==closingBraceCounter)
                {   
                    populateCodeLines("}");
                    break;
                }
            }
            
            if(theCode.charAt(j)=='\n')
            {   
                temp=line.trim();
                
                if(tryFound==true && temp.startsWith("catch"))
                {   
                    populateCodeLines(line);
                    tryFound=false;
                }
                else if(tryFound==true)populateCodeLines(line);
                else if(temp.startsWith("try"))
                {   
                    populateCodeLines(line);
                    tryFound=true;
                }
                else if(temp.contains("new ") && temp.charAt(temp.length()-2)==']' && tryFound==false)
                {   
                    addTryCatchBlock();
                    populateCodeLines(line);
                }
                
                else populateCodeLines(line);
                
                line="";
            }
            else line+=theCode.charAt(j);
        }
    }
    
    void addTryCatchBlock()
    {   
        int endOfStatement=0,starAt=0,equalAt=0;
        boolean foundInitialization=false,foundEqualSign=false;
        
        if(temp.startsWith("if")||temp.startsWith("else if")||temp.startsWith("for")||temp.startsWith("while"))
        {
            for(int i=0;i<temp.length();i++)
            {
                if(temp.charAt(i)==')')endOfStatement=i;
            }
            
            for(int i=0;i<temp.length();i++)
            {   
                if(temp.charAt(i)=='=' && i > endOfStatement)
                {
                    foundEqualSign=true;
                    equalAt=i;
                }
                else if(temp.charAt(i)=='*' && i > endOfStatement && foundEqualSign==false)
                {
                    foundInitialization=true;
                    starAt=i;
                }
            }
            
            if(foundInitialization==true)
            {
                line=temp.substring(0,endOfStatement+1)+"{"+'\n';
                line+=temp.substring(endOfStatement+1,equalAt)+";"+'\n';
                line+="try{"+'\n';
                line+=temp.substring(starAt+1,temp.length())+'\n';
                line+="}"+'\n'+"catch(exception& e){"+'\n';
                line+="cout << e.what() << endl;"+'\n'+"}"+'\n'+"}"+'\n';
            }
            else 
            {
                line=temp.substring(0,endOfStatement+1)+"{"+'\n';
                line+="try{"+'\n';
                line+=temp.substring(endOfStatement+1,temp.length())+'\n';
                line+="}"+'\n'+"catch(exception& e){"+'\n';
                line+="cout << e.what() << endl;"+'\n'+"}"+'\n'+"}"+'\n';
            }
        }
        
        else if(temp.startsWith("else"))
        {
            for(int i=0;i<temp.length();i++)
            {
                if(temp.charAt(i)==' ')
                {
                    endOfStatement=i;
                    break;
                }
            }
            
            for(int i=0;i<temp.length();i++)
            {   
                if(temp.charAt(i)=='=' && i > endOfStatement)
                {
                    foundEqualSign=true;
                    equalAt=i;
                }
                else if(temp.charAt(i)=='*' && i > endOfStatement && foundEqualSign==false)
                {
                    foundInitialization=true;
                    starAt=i;
                }
            }
            
            if(foundInitialization==true)
            {
                line=temp.substring(0,endOfStatement+1)+"{"+'\n';
                line+=temp.substring(endOfStatement+1,equalAt)+";"+'\n';
                line+="try{"+'\n';
                line+=temp.substring(starAt+1,temp.length())+'\n';
                line+="}"+'\n'+"catch(exception& e){"+'\n';
                line+="cout << e.what() << endl;"+'\n'+"}"+'\n'+"}"+'\n';
            }
            else 
            {
                line=temp.substring(0,endOfStatement+1)+"{"+'\n';
                line+="try{"+'\n';
                line+=temp.substring(endOfStatement+1,temp.length())+'\n';
                line+="}"+'\n'+"catch(exception& e){"+'\n';
                line+="cout << e.what() << endl;"+'\n'+"}"+'\n'+"}"+'\n';
            }
        }
        
        else
        {
            for(int i=0;i<temp.length();i++)
            {
                if(temp.charAt(i)=='=')
                {
                    equalAt=i;
                    break;
                }
                else if(temp.charAt(i)=='*')starAt=i;
            }
            
            if(starAt > 0)
            {
                line=temp.substring(0,equalAt)+";"+'\n';
                line+="try{"+'\n'+temp.substring(starAt+1,temp.length())+'\n';
                line+="}"+'\n'+"catch(exception& e){"+'\n';
                line+="cout << e.what() << endl;"+'\n'+"}"+'\n';
            }
            else
            {
                line="try{"+'\n';
                line+="     "+temp+'\n';
                line+="}"+'\n'+"catch(exception& e){"+'\n';
                line+="cout << e.what() << endl;"+'\n'+"}"+'\n';
            }
        }
    }
    
    boolean startsWithType()
    {   
        if(temp.startsWith("void") || temp.startsWith("int") || temp.startsWith("double") ||
           temp.startsWith("float") || temp.startsWith("char") || temp.startsWith("string") || 
           temp.startsWith("bool"))return true;
        
        else return false;
    }
    
    void populateCodeLines(String x)
    {           
        if(codeLineIndex==lineInfo.length)
        {   
            String []array=new String[codeLineIndex+5];
            for(int i=0;i<codeLineIndex;i++)array[i]=lineInfo[i];
            lineInfo=array;
        }
        lineInfo[codeLineIndex]=x;
        codeLineIndex++;
    }
    
    String returnOptimizedCode()
    {   
        optimizedCode="";
        for(int i=0;i<codeLineIndex;i++)
        {   
            if(lineInfo[i]!=null)optimizedCode+=lineInfo[i]+'\n';
        }
        
        return optimizedCode;
    }
}