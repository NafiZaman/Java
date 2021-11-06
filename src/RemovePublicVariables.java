
public class RemovePublicVariables 
{
    String theCode;
    boolean inClass,inPublic,inFunction,inPrivate;
    String temp,line,className;
    String []codeLines;
    String [][]privateMemberData;
    static String [][]newFunctions;
    RemoveAndReplace r;
    int lineCounter,openingBrace,closingBrace,privateDataCounter,newFunctionsCounter,lineNumber/*,replacementVariableCounter*/;
    
    public RemovePublicVariables(String code) 
    {
        theCode=code;
        inClass=false;
        inPublic=false;
        inFunction=false;
        temp="";
        line="";
        className="";
        codeLines=new String[5];
        privateMemberData=new String[5][2];
        lineCounter=0;
        openingBrace=0;
        closingBrace=0;
        privateDataCounter=0;
        newFunctionsCounter=0;
        lineNumber=0;
        newFunctions=new String[5][8];
    }
    
    public RemovePublicVariables(String a,int z){}
    
    void analyzeCode()
    {   
        for(int i=0;i<theCode.length();i++)
        {   
            if(theCode.charAt(i)=='\n' && line.length() > 0)
            {   
                temp=line;
                temp=temp.trim();
                
                if(temp.length() > 0 && temp.charAt(0)=='}' && temp.charAt(temp.length()-1)==';')
                {
                    inClass=false;
                    inPublic=false;
                    inPrivate=false;
                    className="";
                    populateCodeLines(line);
                }
                else if(temp.startsWith("private:"))
                {   
                    inPrivate=true;
                    populateCodeLines(temp);
                }
                else if(inClass==true && inPublic==true)
                {   
                    if(inPrivate==false)analyzePublicMemberData();
                    else populateCodeLines(temp);
                }
                else 
                {
                    if(temp.startsWith("class"))
                    {
                        inClass=true;
                        className=temp.substring(6, temp.length()-1);
                    }
                    else if(temp.startsWith("public"))
                    {
                        inPublic=true;
                        if(inPrivate==true)inPrivate=false;
                    }
                    
                    populateCodeLines(line);
                }
                line="";
            }
            else line+=theCode.charAt(i);
        }
        
        reWriteCode();
    }
    
    void analyzePublicMemberData()
    {
        if(inFunction==true)
        {
            for(int i=0;i<temp.length();i++)
            {
                if(temp.charAt(i)=='{')openingBrace++;
                else if(temp.charAt(i)=='}')closingBrace++;
            }
            
            if(openingBrace==closingBrace)
            {
                inFunction=false;
                openingBrace=0;
                closingBrace=0;
            }
            
            populateCodeLines(line);
        }
        else if(startsWithType()==true && (temp.charAt(temp.length()-2)==')' || temp.charAt(temp.length()-3)==')'))
        {   
            if(temp.charAt(temp.length()-1)=='{')
            {
                inFunction=true;
                openingBrace++;
            }
            populateCodeLines(line);
        }
        else
        {   
            if(startsWithType()==true && temp.charAt(temp.length()-1)!='{')temp=analyzeLine(temp);
            else populateCodeLines(line);
            
            if(startsWithType()==true && temp.length() > 0 && temp.charAt(temp.length()-1)!='{')
            {   
                lineNumber++;
                populatePrivateData(temp); 
                populateNewFunctionsData(temp,lineNumber);
                populateCodeLines("change");
            }
        }
    }
    
    String analyzeLine(String x)
    {   
        boolean pointer=false;
        String aux="",aux2="";
        
        for(int i=0;i<x.length();i++)
        {            
            if(x.charAt(i)=='*')
            {
                pointer=true;
                aux2+=x.charAt(i);
            }
            else if(pointer==true && (x.charAt(i)==',' || x.charAt(i)==';'))
            {
                pointer=false;
                aux2+=x.charAt(i);
            }
            
            else if(pointer==true)aux2+=x.charAt(i);
            else aux+=x.charAt(i);
        }
        
        if(aux2.length() > 0)
        {
            if(x.startsWith("int"))aux2="int "+aux2;
            else if(x.startsWith("float"))aux2="float "+aux2;
            else if(x.startsWith("double"))aux2="double "+aux2;
            else if(x.startsWith("string"))aux2="string "+aux2;
            else if(x.startsWith("char"))aux2="char "+aux2;
            else if(x.startsWith("bool"))aux2="bool "+aux2;
            
            if(aux2.charAt(aux2.length()-1)==',')aux2=aux2.substring(0,aux2.length()-1)+';';
            
            populateCodeLines(aux2);
        }
        
        aux=aux.trim();
        if(aux.equals("int") || aux.equals("float") || aux.equals("double") || aux.equals("char") || aux.equals("string") || aux.equals("bool"))aux="";
        else if(aux.length() > 0 && aux.charAt(aux.length()-1)==',')aux=aux.substring(0,aux.length()-1)+';';
        
        return aux;
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
        if(lineCounter==codeLines.length)
        {
            String []arr=new String[lineCounter+5];
            for(int i=0;i<lineCounter;i++)arr[i]=codeLines[i];
            
            codeLines=arr;
        }
        codeLines[lineCounter]=x;
        lineCounter++;
    }
    
    void populatePrivateData(String x)
    {   
        if(privateDataCounter==privateMemberData.length)
        {
            String [][]arr=new String[privateDataCounter+5][2];
            
            for(int i=0;i<privateDataCounter;i++)
            {
                for(int j=0;j<2;j++)
                {
                    arr[i][j]=privateMemberData[i][j];
                }
            }
            
            privateMemberData=arr;
        }
        privateMemberData[privateDataCounter][0]=className;
        privateMemberData[privateDataCounter][1]=x;
        privateDataCounter++;
    }
    
    void populateNewFunctionsData(String x,int ln)
    {   
        String type="",name="";
        int start=0;
        boolean foundInit=false;
        openingBrace=0;
        closingBrace=0;
        
        if(x.startsWith("int"))
        {
            type="int";
            start=3;
        }
        else if(x.startsWith("float"))
        {
            type="float";
            start=5;
        }
        else if(x.startsWith("double"))
        {
            type="double";
            start=6;
        }
        else if(x.startsWith("char"))
        {
            type="char";
            start=4;
        }
        else if(x.startsWith("string"))
        {
            type="string";
            start=6;
        }
        else if(x.startsWith("bool"))
        {
            type="bool";
            start=4;
        }
        
        type=type.trim();

        for(int i=start;i<x.length();i++)
        {
            if(foundInit==true)
            {
                if(x.charAt(i)=='{')openingBrace++;
                else if(x.charAt(i)=='}')closingBrace++;
                else if(x.charAt(i)==',' || x.charAt(i)==';')
                {
                    if(((openingBrace > 0 && closingBrace > 0) && (openingBrace==closingBrace)) || (openingBrace==0 && closingBrace==0))
                    {
                        name=name.trim();
                        populateFunctionInfo(name,type,ln);
                        name="";
                        openingBrace=closingBrace=0;
                        foundInit=false;
                    }
                }
            }
            else if(x.charAt(i)=='=')foundInit=true;
            else if(x.charAt(i)==',' || x.charAt(i)==';')
            {
                name=name.trim();
                populateFunctionInfo(name, type, ln);
                name="";
            }
            else name+=x.charAt(i);
        }
    }
    
    void populateFunctionInfo(String name,String type,int ln)
    {   
        if(newFunctionsCounter==newFunctions.length)
        {
            String [][]arr=new String[newFunctionsCounter+5][8];
            for(int i=0;i<newFunctionsCounter;i++)
            {
                for(int j=0;j<8;j++)arr[i][j]=newFunctions[i][j];
            }
            
            newFunctions=arr;
        }
        
        int braceIndex=0;
        String tempName=name;
        String getFunc="";
        String assignFunc="";
        
        for(int i=0;i<name.length();i++)
        {
            if(name.charAt(i)=='[')if(braceIndex==0)braceIndex=i;
        }
        
        if(braceIndex > 0)name=name.substring(0,braceIndex);
        
        getFunc="get_"+name+"Val(";
        assignFunc="give_"+name+"Val(";
        braceIndex=0;
        for(int i=0;i<tempName.length();i++)
        {
            if(tempName.charAt(i)=='[')
            {   
                braceIndex++;
                getFunc+=type+" index_"+name+Integer.toString(braceIndex)+",";
                assignFunc+=type+" index_"+name+Integer.toString(braceIndex)+",";
            }
        }
        
        if(getFunc.endsWith(","))getFunc=getFunc.substring(0,getFunc.length()-1)+"){ "+'\n'+"return "+name;
        else getFunc+="){ "+'\n'+"return "+name;
        assignFunc+=type+" newVal_"+name+"){ "+'\n'+name;
        
        braceIndex=0;
        
        for(int i=0;i<tempName.length();i++)
        {
            if(tempName.charAt(i)=='[')
            {
                braceIndex++;
                getFunc+="["+"index_"+name+Integer.toString(braceIndex)+"]";
                assignFunc+="["+"index_"+name+Integer.toString(braceIndex)+"]";
            }
        }
        
        getFunc+=";"+'\n'+" }"+'\n';
        assignFunc+="=newVal_"+name+";"+'\n'+" }"+'\n';
        tempName="temp_"+name+"Val";
        
        newFunctions[newFunctionsCounter][0]=type;
        newFunctions[newFunctionsCounter][1]=name;
        newFunctions[newFunctionsCounter][2]=String.valueOf(ln);
        newFunctions[newFunctionsCounter][3]=className;
        newFunctions[newFunctionsCounter][4]=tempName;
        newFunctions[newFunctionsCounter][5]=getFunc;
        newFunctions[newFunctionsCounter][6]=assignFunc;
        newFunctions[newFunctionsCounter][7]="f";
        newFunctionsCounter++;
    }
    
    void reWriteCode()
    {   
        className="";
        boolean foundPrivate=false;
        temp="";
        lineNumber=0;
        theCode="";
        for(int i=0;i<codeLines.length;i++)
        {
            if(codeLines[i]!=null)
            {   
                temp=codeLines[i];
                temp=temp.trim();
                
                if(temp.equals("change"))
                {   
                    codeLines[i]="";
                    lineNumber++;
                    for(int j=0;j<newFunctions.length;j++)
                    {   
                        if(Integer.toString(lineNumber).equals(newFunctions[j][2]))codeLines[i]+=newFunctions[j][0]+" "+newFunctions[j][5]+"void "+newFunctions[j][6]+'\n';
                    }
                    
                    theCode+=codeLines[i]+'\n';
                }
                else if(temp.contains("."))
                {   
                    r=new RemoveAndReplace(temp);
                    r.analyze();
                    temp=r.returnLine();
                    check();
                    theCode+=temp+'\n';
                }
                else if(temp.startsWith("class "))
                {   
                    theCode+='\n'+codeLines[i]+'\n';
                    className=temp.substring(6,temp.length()-1);
                }
                else if(temp.startsWith("private"))
                {
                    theCode+=codeLines[i]+'\n';
                    foundPrivate=true;
                    for(int j=0;j<privateMemberData.length;j++)
                    {
                        if(className.equals(privateMemberData[j][0]))theCode+=privateMemberData[j][1]+'\n';
                    }
                }
                else if(temp.equals("};") || temp.equals("} ;"))
                {   
                    if(foundPrivate==true)foundPrivate=false;
                    else
                    {
                        theCode+="private:"+'\n';
                        for(int j=0;j<privateMemberData.length;j++)
                        {
                            if(className.equals(privateMemberData[j][0]))theCode+=privateMemberData[j][1]+'\n';
                        }
                        
                    }
                    
                    theCode+=codeLines[i]+'\n';
                    className="";
                }
                else theCode+=codeLines[i]+'\n';
            }
        }
    }
    
    String returnOptimizedCode()
    {   
        line="";
        temp="";
        String variableLines="";
        for(int i=0;i<theCode.length();i++)
        {
            if(theCode.charAt(i)=='\n' && line.length() > 0)
            {   
                temp=line;
                temp=temp.trim();
                if(startsWithType()==true || temp.startsWith("class "))
                {   
                    for(int j=0;j<newFunctions.length;j++)
                    {
                        if(newFunctions[j][0]!=null && newFunctions[j][7].equals("t"))variableLines+=newFunctions[j][0]+" "+newFunctions[j][4]+";";
                    }
                    
                    theCode=theCode.substring(0,i-temp.length())+variableLines+'\n'+temp+'\n'+theCode.substring(i+1,theCode.length());
                    break;
                }
                else line="";
            }
            else line+=theCode.charAt(i);
        }
        
        return theCode;
    }
    
    void check()
    {
        for(int i=0;i<newFunctions.length;i++)
        {
            if(temp!=null && newFunctions[i][0]!=null && temp.contains(newFunctions[i][4]))newFunctions[i][7]="t";
        }
    }
}