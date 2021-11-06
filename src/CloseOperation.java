public class CloseOperation 
{       
    String theCode,line,temp,optimizedCode;
    String [][]obj_ifStream;
    String []codeLines;
    int counter,codeLineIndex,openingBrace,closingBrace,index;
    
    public CloseOperation(String theCode)
    {
        this.theCode=theCode;
        codeLineIndex=counter=5;
        obj_ifStream=new String[counter][2];
        codeLines=new String[codeLineIndex];
    }
    
    void analyzeCode()
    {   
        line="";
        temp="";
        openingBrace=0;
        closingBrace=0;
        for(index=0;index<theCode.length();index++)
        {
            if(theCode.charAt(index)=='\n')
            {   
                temp=line.trim();
                populateCodeLinesArray(line);
                
                if(startsWithType()==true && temp.charAt(temp.length()-1)=='{')
                {   
                    openingBrace++;
                    analyzeFunction(index);
                    refreshMemory();
                    openingBrace=0;
                    closingBrace=0;
                }
                
                line="";
            }
            else line+=theCode.charAt(index);
        }
    }
    
    void analyzeFunction(int lineIndex)
    {   
        line="";
        int inQuote=1;
        
        for(int i=lineIndex+1;i<theCode.length();i++)
        {
            index++;
            if(theCode.charAt(i)=='"')inQuote*=-1;
            else if(theCode.charAt(i)=='{' && inQuote==1)openingBrace++;
            else if(theCode.charAt(i)=='}' && inQuote==1)
            {
                closingBrace++;
                if(closingBrace==openingBrace)
                {       
                    for(int m=0;m<counter;m++)
                    {
                        if(obj_ifStream[m][0]!=null)
                        {
                            if(obj_ifStream[m][1].equals("f"))populateCodeLinesArray(obj_ifStream[m][0]+".close();");
                        }
                    }
                    populateCodeLinesArray("}");
                    break;
                }
            }
            
            if(theCode.charAt(i)=='\n')
            {
                temp=line.trim();
                populateCodeLinesArray(line);

                if(line.contains(".open("))addfstreamVariableToMemory();
                else if(line.contains(".close("))
                {   
                    String aux="";
                    int pointFound=0;
                    for(int j=0;j<temp.length();j++)
                    {
                        if(temp.charAt(j)=='.')
                        {
                            pointFound=j;
                            break;
                        }
                    }
                    
                    aux=temp.substring(0,pointFound);
                    
                    for(int m=0;m<counter;m++)
                    {
                        for(int j=0;j<2;j++)
                        {   
                            if(obj_ifStream[m][0]!=null && obj_ifStream[m][0].equals(aux))obj_ifStream[m][1]="t";
                        }
                    }
                }
                
                line="";
            }
            else line+=theCode.charAt(i);
        }
    }
    
    void addfstreamVariableToMemory()
    {   
        int endOfStatement=0,pointFound=0;
        String aux="";
        
        if(temp.startsWith("if")||temp.startsWith("else if")||temp.startsWith("for")||temp.startsWith("while"))
        {
            for(int i=temp.length()-1;i>=0;i--)
            {   
                if(temp.charAt(i)==')' && pointFound > 0)
                {
                    endOfStatement=i;
                    break;
                }
                else if(temp.charAt(i)=='.')pointFound=i;
            }
            
            aux=temp.substring(endOfStatement+1,pointFound);
            aux=aux.trim();
            populateobj_ifStreamArray(aux);
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
                if(temp.charAt(i)=='.')
                {
                    pointFound=i;
                    break;
                }
            }
            
            aux=temp.substring(endOfStatement+1,pointFound);
            aux=aux.trim();
            populateobj_ifStreamArray(aux);
        }
        else
        {
            for(int i=0;i<temp.length();i++)
            {
                if(temp.charAt(i)=='.')
                {
                    pointFound=i;
                    break;
                }
            }
            
            aux=temp.substring(0,pointFound);
            aux=aux.trim();
            populateobj_ifStreamArray(aux);
        }
    }
    
    boolean startsWithType()
    {   
        if(temp.startsWith("void") || temp.startsWith("int") || temp.startsWith("double") ||
           temp.startsWith("float") || temp.startsWith("char") || temp.startsWith("string") || 
           temp.startsWith("bool"))return true;
        
        else return false;
    }
    
    void populateobj_ifStreamArray(String x)
    {   
        if(counter==obj_ifStream.length)
        {
            String [][]array=new String[counter+5][2];
            for(int i=0;i<counter;i++)
            {
                for(int j=0;j<2;j++)array[i][j]=obj_ifStream[i][j];
            }
            
            obj_ifStream=array;
        }
        obj_ifStream[counter][0]=x;
        obj_ifStream[counter][1]="f";
        counter++;
    }
    
    void populateCodeLinesArray(String x)
    {
        if(codeLineIndex==codeLines.length)
        {
            String []array=new String[codeLineIndex+5];
            for(int i=0;i<codeLineIndex;i++)array[i]=codeLines[i];
            
            codeLines=array;
        }
        codeLines[codeLineIndex]=x;
        codeLineIndex++;
    }
    
    void refreshMemory()
    {
        obj_ifStream=new String[counter][2];
    }
    
    String returnOptimizedCode()
    {   
        optimizedCode="";
        for(int i=0;i<codeLineIndex;i++)
        {   
            if(codeLines[i]!=null)optimizedCode+=codeLines[i]+'\n';
        }
        
        return optimizedCode;
    }
}