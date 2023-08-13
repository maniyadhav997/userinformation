import javax.servlet.*;
import java.util.*;
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
public class StudentDetails implements Servlet
{
private DocumentBuilderFactory fact;
private DocumentBuilder builder;
private Document doc;
private NodeList list,childs;
private Node node,parent,child;
private String str;
private String hallTicket;
private ServletConfig sc;
public void init(ServletConfig sc)
{
try
{
this.sc=sc;
str="C:\Program Files\Apache Software Foundation\Tomcat 5.0\webapps\StudentDetails\WEBINF\classes\Details.xml";
fact=DocumentBuilderFactory.newInstance();
builder =fact.newDocumentBuilder();
doc=builder.parse(str);
System.out.println("In the Init Method");
}
catch(Exception e)
{
System.out.println("Error in the Init Method"+e.getMessage());
}
}
public void service(ServletRequest req, ServletResponse res)throws 
ServletException,IOException
{
hallTicket=req.getParameter("hall");
res.setContentType("text/html");
PrintWriter pw=res.getWriter();
list=doc.getElementsByTagName("HallTicketNo");
pw.print("<center><h1>Welcome To Student Details</center></h1>");
for(int i=0;i<list.getLength();i++)
{
node=list.item(i);
if(node.getTextContent().equals(hallTicket))
{
parent=node.getParentNode();
childs=parent.getChildNodes();
for(int j=1;j<childs.getLength()-1;j=j+2)
{
child=childs.item(j);
pw.print("<center>"+child.getNodeName()+" "+child.getTextContent());
}
break;
}//if
}//for
}//service
public ServletConfig getServletConfig()
{
return sc;
}
public String getServletInfo()
{
return "Developed By Khaja HabeebUddin";
}
public void destroy()
{
}
}