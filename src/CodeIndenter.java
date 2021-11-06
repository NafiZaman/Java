
public class CodeIndenter
{
    String theCode;
    String []codeLines;
    int lineNumber,tabLenght;
    String line,tabSpace;
    
    public CodeIndenter(String theCode)
    {
        this.theCode=theCode;
        codeLines=new String[5];
        lineNumber=0;
        tabLenght=0;
        tabSpace="";
        line="";
    }
    
    void analyzeCode()
    {   
        for(int i=0;i<theCode.length();i++)
        {
            if(theCode.charAt(i)=='\n' && line!=null)
            {
                line=line.trim();
                
                if(line.equals("}")||line.equals("};"))tabLenght--;
                
                tabSpace="";
                for(int j=0;j<tabLenght;j++)tabSpace+="\t";
                
                if(line.startsWith("class ")|| line.equals("private:")||line.equals("public:"))tabSpace="";
                
                line=tabSpace+line;
                populateCodeLines(line);
                
                if(line.length() > 1 && line.charAt(line.length()-1)=='{')tabLenght++;
                
                line="";
            }
            else line+=theCode.charAt(i);
        }
    }
    
    void populateCodeLines(String x)
    {
        if(lineNumber==codeLines.length)
        {
            String []arr=new String[lineNumber+5];
            
            for(int i=0;i<lineNumber;i++)arr[i]=codeLines[i];
            
            codeLines=arr;
        }
        codeLines[lineNumber]=x;
        lineNumber++;
    }
    
    String returnIndentedCode()
    {   
        theCode="";
        for(int i=0;i<codeLines.length;i++)if(codeLines[i]!=null)theCode+=codeLines[i]+'\n';
        
        return theCode;
    }
}