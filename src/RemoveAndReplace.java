public class RemoveAndReplace
{   
    String str,theLine,name,obj,val,getFunc,setFunc;
    int start,end;
    boolean foundInit,foundBrace,foundSymbol;
    RemovePublicVariables rpv;
    
    public RemoveAndReplace(String a)
    {   
        theLine=a;
        theLine=theLine.trim();
        rpv=new RemovePublicVariables("",0);
    }
    
    void analyze()
    {   
        if(theLine.startsWith("if")||theLine.startsWith("else")||theLine.startsWith("cout")||theLine.startsWith("for")||theLine.startsWith("while")||theLine.startsWith("switch"))theLine=correctChecker(theLine);
        else theLine=correctInitialization(theLine);
    }
    
    String correctInitialization(String line)
    {   
        foundInit=false;
        String aux="";
        str=line;
        str=str.replaceAll("\\s", "");
        foundSymbol=false;
        
        for(int i=0;i<str.length();i++)
        {   
            if(str.charAt(i)=='=')foundInit=true;            
            if(str.charAt(i)=='='||str.charAt(i)=='+'||str.charAt(i)=='*'||str.charAt(i)=='/'||str.charAt(i)=='%'||
               str.charAt(i)=='-'||str.charAt(i)==';'||str.charAt(i)=='>')
            {   
                if(str.charAt(i)!='=')foundSymbol=true;
                if(aux.contains(".") && aux.contains("["))
                {   
                    name="";
                    obj="";
                    val="";
                    getFunc="";
                    setFunc="";
                    foundBrace=false;
                    
                    obj=aux.substring(0,aux.indexOf("."));
                    name=aux.substring(aux.indexOf(".")+1,aux.indexOf("["));
                    
                    for(int j=0;j<aux.length();j++)
                    {   
                        if(aux.charAt(j)==']')val+=',';
                        else if(aux.charAt(j)=='[')foundBrace=true;
                        else if(foundBrace==true)val+=aux.charAt(j);
                    }
                    
                    val=val.trim();
                    val=val.substring(0,val.length()-1);
                    getFunc=obj+"."+"get_"+name+"Val("+val+")";
                    setFunc=obj+"."+"give_"+name+"Val("+val+",temp_"+name+"Val)";
                    
                    if(validVariable()==true)
                    {
                        if(line.contains(aux) && str.contains("=")!=true)line=replaceOccurence(line,getFunc,aux);
                        else if(foundInit==false || (foundInit==true && foundSymbol==false))
                        {   
                            if(line.contains(aux))line=replaceOccurence(line,"temp_"+name+"Val",aux);
                        
                            line="temp_"+name+"Val"+"="+getFunc+";"+'\n'+line+'\n'+setFunc+";"+'\n';
                        }
                        else if(line.contains(aux))line=replaceOccurence(line,getFunc,aux);   
                    }
                }
                else if(aux.contains("."))
                {   
                    name="";
                    obj="";
                    val="";
                    getFunc="";
                    setFunc="";
                    
                    obj=aux.substring(0,aux.indexOf("."));
                    name=aux.substring(aux.indexOf(".")+1,aux.length());
                    
                    getFunc=obj+"."+"get_"+name+"Val()";
                    setFunc=obj+"."+"give_"+name+"Val(temp_"+name+"Val)";
                    
                    if(validVariable()==true)
                    {   
                        if(line.contains(aux) && str.contains("=")!=true)line=replaceOccurence(line,getFunc,aux);
                        else if(foundInit==false || (foundInit==true && foundSymbol==false))
                        {   
                            if(line.contains(aux))line=replaceOccurence(line,"temp_"+name+"Val",aux);
                            
                            line="temp_"+name+"Val"+"="+getFunc+";"+'\n'+line+'\n'+setFunc+";"+'\n';
                        }
                        else if(line.contains(aux))line=replaceOccurence(line,getFunc,aux); 
                    }
                }
                aux="";
            }
            else aux+=str.charAt(i);
        }
        
        return line;
    }
    
    String correctChecker(String line)
    {   
        String aux="";
        String aux2="";
        String temp_aux="";
        str=line;
        str=str.trim();

        if(str.startsWith("if")||str.startsWith("for")||str.startsWith("while")||str.startsWith("else if")||str.startsWith("switch"))
        {
            aux=str.substring(0,str.lastIndexOf(")")+1);
            aux2=str.substring(str.lastIndexOf(")")+1,str.length());
            aux2=aux2.trim();
        }
        else if(str.startsWith("else "))
        {
            aux=str.substring(0,str.indexOf(" ")+1);
            aux2=str.substring(str.indexOf(" ")+1,str.length());
            aux2=aux2.trim();
        }
        else if(str.startsWith("cout"))
        {
            aux=str.substring(0,str.lastIndexOf("<")+1);
            aux2=str.substring(str.lastIndexOf("<")+1,str.length());
            aux2=aux2.trim();
        }
        
        
        for(int i=0;i<aux.length();i++)
        {
            if(str.charAt(i)=='+'||str.charAt(i)=='-'||str.charAt(i)=='*'||str.charAt(i)=='/'||str.charAt(i)=='%'||
               str.charAt(i)=='('||str.charAt(i)==')'||str.charAt(i)=='&'||str.charAt(i)=='|'||str.charAt(i)=='!'||
               str.charAt(i)=='='||str.charAt(i)=='<'||str.charAt(i)=='>')
            {   
                temp_aux=temp_aux.trim();
                temp_aux=temp_aux.replace("\\s", "");
                if(temp_aux.contains(".") && temp_aux.contains("["))
                {   
                    name="";
                    obj="";
                    val="";
                    getFunc="";
                    foundBrace=false;
                    
                    obj=temp_aux.substring(0,temp_aux.indexOf("."));
                    name=temp_aux.substring(temp_aux.indexOf(".")+1,temp_aux.indexOf("["));
                 
                    
                    for(int j=0;j<temp_aux.length();j++)
                    {   
                        if(temp_aux.charAt(j)==']')val+=',';
                        else if(temp_aux.charAt(j)=='[')foundBrace=true;
                        else if(foundBrace==true)val+=temp_aux.charAt(j);
                    }
                    
                    val=val.trim();
                    val=val.substring(0,val.length()-1);
                    getFunc=obj+"."+"get_"+name+"Val("+val+")";
                    
                    if(validVariable()==true && line.contains(temp_aux))line=replaceOccurence(line,getFunc,temp_aux);
                }
                else if(temp_aux.contains("."))
                {   
                    name="";
                    obj="";
                    getFunc="";
                    
                    obj=temp_aux.substring(0,temp_aux.indexOf("."));
                    name=temp_aux.substring(temp_aux.indexOf(".")+1,temp_aux.length());
                    getFunc=obj+"."+"get_"+name+"Val()";
                    
                    if(validVariable()==true && line.contains(temp_aux))line=replaceOccurence(line,getFunc,temp_aux);
                }
                temp_aux="";
            }
            else temp_aux+=aux.charAt(i);
        }
        aux=line=line.substring(0,line.length()-aux2.length());
        temp_aux=aux2;
        
        if(aux2.startsWith("cout")||aux2.startsWith("cin"))temp_aux=correctChecker(aux2);
        else temp_aux=correctInitialization(temp_aux);
        
        int endl=0;
        for(int i=0;i<temp_aux.length();i++)if(temp_aux.charAt(i)=='\n')endl++;
        
        if(endl>1)temp_aux="{"+'\n'+temp_aux+"}"+'\n';
        
        aux2=temp_aux;
        line=aux+aux2;
        return line;
    }
    
    String returnLine()
    {   
        return theLine;
    }
    
    String replaceOccurence(String line,String replaceMent,String aux)
    {   
        
        String temp="";
        String ol=line;
        for(int i=0;i<ol.length();i++)
        {
            if(nextCharIsSymbol(line.charAt(i))==true)
            {
                if(temp.equals(aux))line=line.substring(0,i-aux.length())+replaceMent+line.charAt(i)+line.substring(i+1,line.length());
                
                temp="";
            }
            else temp+=ol.charAt(i);
        }
        
        return line;
    }
    
    boolean nextCharIsSymbol(char x)
    {  
        if(x=='+'||x=='-'||x=='*'||x=='/'||x=='%'||
           x=='('||x==')'||x=='&'||x=='|'||x=='!'||
           x=='='||x=='<'||x=='>'||x==' '||x==';')return true;
        
        else return false;
    }
    
    boolean validVariable()
    {
        for(int i=0;i<rpv.newFunctions.length;i++)
        {
            if(rpv.newFunctions[i][0]!=null && name.equals(rpv.newFunctions[i][1]))return true;
        }
        
        return false;
    }
}