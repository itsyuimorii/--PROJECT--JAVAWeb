####  1.Servlet

#### a) What is Servlet?

- Servlet is an application running on the web server side, written in Java language
- Servlet object mainly encapsulates the processing of HTTP requests, its operation requires the support of the Servlet container (such as Tomcat)
- Servlet is managed by the Servlet container, Servlet container will be dynamically loaded on the server, and HTTP protocol-related Servlet use HTTP requests and HTTP responses to interact with the client

> As shown below, Servlet requests will first be received by HTTP servers (such as Apache), HTTP servers are only responsible for static HTML interface parsing, while Servlet requests are forwarded to the Servlet container, which will call the corresponding Servlet according to the request path and the mapping relationship between Servlets, which will return the processing results to the Servlet container and transmit the response to the client through the HTTP server

#### b) Manual implementation of Servlet program

1.  Write a class to implement the Servlet interface
2.  Implement service methods, process requests, and respond to data
3.  Go to web.xml to configure the servlet program's access address.

### 2. Servlet interface

>  Servlet is a class that implements the Servlet interface, which is created and called by the Web server to receive and respond to user requests, with five abstract methods defined in the Servlet interface

| Method                                           | description                                                  |
| ------------------------------------------------ | ------------------------------------------------------------ |
| void init(ServletConfig config)                  | After the Servlet is instantiated, the Servlet container calls the method to complete the initialization of the Servlet |
| ServletConfig getServletConfig()                 | Get the configuration information of the Servlet object      |
| String getServletInfo()                          | Returns a string containing Servlet information, such as author, copyright, etc. |
| void service(ServletRequest req,ServletResponse) | Responsible for responding to user requests, when the container receives a request from the client to access the Servlet object will call this method. The **container will construct a ServletRequest object that represents the client request information and a ServletResponse object that is used to respond to the client as parameters passed to the service() method**, in the service() method you can get the relevant information about the client and the request information through the ServletRequest object, after After the request is processed, the method of ServletResponse object is called to set the corresponding information |
| void destroy()                                   | Responsible for releasing the resources occupied by the Servlet object, when the server is closed or the Servlet object When the server is shut down or the Servlet object is removed, the Servlet object will be destroyed, and this method will be called. |

Servlet class must be written to implement the Servlet interface or inherit the Servlet interface implementation class, such as **GenericServlet** and **HttpServlet**, both of which are **abstract** **classes** and HttpServlet inherits **GenericServlet**, which GenericServlet has an abstract method service (), and HttpServlet no abstract method

```java
public class ServletDemo02 extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req,
HttpServletResponse resp) throws ServletException, IOException {
System.out.println("method executed");
}
@Override
protected void doPost(HttpServletRequest req,
HttpServletResponse resp) throws ServletException, IOException {
doGet(req,resp);
}
}
```
> Servlet thread safety problem: In the implementation of the Servlet class if there is a custom member variable, and in the service () method to manipulate this variable may be a thread safety problem, the solution is to change the member variable to a local variable defined inside the method, or use sychronized lock

Summarize the three ways to write Servlet class

1. implement the **Servlet interface**, you need to override all the methods in the Servlet interface
2. inherit **GenericServlet abstract class**, you must override the service () method, other methods can also be overridden
3. Inherit **HttpServlet abstract class**, do not need to override service() method, because HttpServlet in service() method calls another overloaded service() method, this overloaded method will be selected according to the parameters of the corresponding method, such as doGet () method or doPost () method, so we We can override doGet() and doPost() methods to achieve the desired function.
4. usually use inheritance of HttpServlet abstract class way to write Servlet


> HelloServlet.java

```java
public class HelloServlet implements Servlet {
/**
* The service method is dedicated to handling requests and responses
* @param servletRequest
* @param servletResponse
* @throws ServletException
* @throws IOException
*/
@Override
public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws
ServletException, IOException {
System.out.println("Hello Servlet 被访问了");
}
}
```

> web.xml 配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
version="4.0">
<! -- servlet tag to configure a servlet program for Tomcat -->
<servlet>
<! --servlet-name tag Servlet program with an alias (usually a class name) -->
<servlet-name>HelloServlet</servlet-name>
<! --servlet-class is the full class name of the Servlet program -->

<servlet-class>com.yuimorii.servlet.HelloServlet</servlet-class>
</servlet>
<! The --servlet-mapping tag configures access addresses for servlet programs -->

<servlet-mapping>
<! The purpose of the --servlet-name tag is to tell the server which Servlet application I am currently configuring to use the address -->
<servlet-name>HelloServlet</servlet-name>

<url-pattern>/hello</url-pattern>
</servlet-mapping>
</web-app>
```

##### c) url address to the Servlet program access

![- image-20221225150214855](/Users/yuimorii/Documents/GitHub/Javaweb/img/- image-20221225150214855.png)

### 3. The life cycle of Servlet

[the entire life cycle of a servlet]: https://www.geeksforgeeks.org/life-cycle-of-a-servlet/

**Stages of the Servlet Life Cycle**: The Servlet life cycle mainly goes through four stages,

- Loading a Servlet.

- Initializing the Servlet.

- Request handling.

- Destroying the Servlet.

- Initialization phase:


 ![Frame 6](/Users/yuimorii/Documents/GitHub/Javaweb/img/Frame 6.png)

- When the client sends an HTTP request to the Servlet container to access the Servlet, the Servlet container first parses the request and checks whether the Servlet object is already in memory, if so, the Servlet object is used directly; if not, the Servlet instance object is created, and then by calling the init() method to complete the Servlet initialization. Note that in the entire life cycle of the Servlet, **its init() method is called only once. **

- Run phase:
  - The Servlet container creates the ServletRequest object representing the HTTP request and the ServletResponse object representing the HTTP response for the client request, and then passes them as parameters to the service() method of the Servlet. service() method from the ServletRequest object to get the client request and processing the request, through the ServletResponse object to generate the response. In the whole life cycle of the Servlet, for each access request of the Servlet, the Servlet container will call the service() method of the Servlet once and create new ServletRequest and ServletResponse objects. **That is, the service () method will be called several times throughout the life of the Servlet **
  
- Destruction phase
  - when the server is closed or the Web application is removed from the container, the Servlet with the destruction of the Web application and destroyed. Before destroying the Servlet, the Servlet container will call the Servlet destroy() method in order to allow the Servlet object to release the resources it occupies. **The destroy() method is also called only once during the entire life cycle of the Servlet. **Note that once created, the Servlet object resides in memory waiting for access from the client until the server is shut down or the Web application is removed from the container when the Servlet object is destroyed

##### e) The service method is dedicated to handling requests and responses

```java
public class HelloServlet implements Servlet {
/**
* service method is dedicated to handling requests and responses
* @param servletRequest
* @param servletResponse
* @throws ServletException
* @throws IOException
*/
@Override
public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws
ServletException, IOException {
System.out.println("3 service === Hello Servlet was visited");
// Type conversion (because it has getMethod() method)
HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
// The way to get the request
String method = httpServletRequest.getMethod();
if ("GET".equals(method)) {
doGet();
} else if ("POST".equals(method)) {
doPost();
}
}
/**
* get operation
*/
public void doGet(){
System.out.println("get request");
}
/**
* post operation
*/
public void doPost(){
System.out.println("post request");
}
}
```

### 4. Implementing Servlet programs by inheriting HttpServlet

Generally in the actual project development, using the inheritance of the HttpServlet class to implement the Servlet program.

1. write a class to inherit the HttpServlet class
2. according to business needs to rewrite the doGet or doPost method
3. to the web.xml in the configuration of the Servlet program access address
4. 

| description                                   | method                                                       |
| --------------------------------------------- | ------------------------------------------------------------ |
| User processing of HTTP requests of type GETl | protected void doGet(HttpServletRequestreq,HttpServletResponse resp) |
| For handling POST type HTTP requests          | protected void doPost(HttpServletRequestreq,HttpServletResponse resp) |
| For handling PUT type HTTP requests           | protected void doPut(HttpServletRequestreq,HttpServletResponse resp) |
|                                               |                                                              |
|                                               | ![(/Users/yuimorii/Documents/GitHub/Javaweb/img/Screen Shot 2022-12-25 at 8.55.09 PM.png)![Screen Shot 2022-12-25 at 9.00.55 PM](/Users/yuimorii/Documents/GitHub/Javaweb/img/Screen Shot 2022-12-25 at 9.00.55 PM.png) |

```java
//HelloServlet2
package com.yuimorii.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class HelloServlet2 extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        //The init method must be called
        super.init(config);
        System.out.println("Rewrote the init initialization method");
    }

    /**
     * doGet（）=> Called on a get request
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet2 doGet method");
        ServletConfig servletConfig = getServletConfig();
        System.out.println(servletConfig);

        //2. Get the initialization parameter init-param
        System.out.println("The value of the initialization parameter username is;" + servletConfig.getInitParameter("username"));
        System.out.println("The value of the initialization parameter url is;" + servletConfig.getInitParameter("url"));
    }
    /**
     * doPost（）=> Called on a get request
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet2 doPost method");
    }
}
```

```java
//form.html
<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form action="http://localhost:8080/06_Servlet/hello2" method="post">
    <input type="hidden" name="action" value="login" />
    <input type="hidden" name="username" value="root" />
    <input type="submit">
</form>
</body>
</html>
```

```xml
//web.xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
<servlet>
    <servlet-name>HelloServlet2</servlet-name>
    <servlet-class>com.yuimorii.servlet.HelloServlet2</servlet-class>
</servlet>

 <servlet-mapping>
      <servlet-name>HelloServlet2</servlet-name>
      <url-pattern>/hello2</url-pattern>
 </servlet-mapping>
</web-app>
```

##### g) Use IDEA to create a Servlet program

```java
package com.yuimorii.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class HelloServlet3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet3 doPost");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet3 doGet");
    }
}
```

##### h) Servlet class inheritance system![servlet idea ](/Users/yuimorii/Documents/GitHub/Javaweb/img/servlet idea .jpg)

### 5. Servlet creation timing

##### Created on the first visit
> Advantage: reduce the waste of server memory
> Disadvantage: If there are some initialization operations that need to be done when the application is loaded, they cannot be completed

##### Created when the server is loaded

> Advantage: create the object in advance to improve the efficiency of the first execution, you can complete some of the initialization work to be done when the application is loaded
> Disadvantages: more memory consumption of the server, affecting the efficiency of the server startup

Modify the timing of Servlet creation: add <load-on-startup></load-on-startup> tag to the <servlet> tag in the web.xml file, the middle of the tag is filled with an integer, positive integer means created when the server is loaded, the smaller the value the higher the priority, negative integer or not filled means created on the first visit


6. Default Servlet
- The default servlet is a servlet provided by the server, which is configured in the web.xml file in the conf directory of Tomcat.
- Its mapping path is <url-pattern>/</url-pattern>, and when receiving a request, it will first be set in the project's

# 2.ServletConfig

### Overview

- ServletConfig is an interface

- When Tomcat initializes a Servlet, it encapsulates the Servlet's configuration information into a ServletConfig object, and passes the ServletConfig object to the Servlet by calling the init(ServletConfig config) method. life cycle is the same as Servlet

- The configuration information encapsulated in ServletConfig is in the form of key-value pairsa) ServletConfig class three major roles

### ServletConfig configuration

| Method                               | description                                                  |
| ------------------------------------ | ------------------------------------------------------------ |
| String getInitParameter(String name) | Returns the corresponding parameter value according to the parameter name |
| Enumeration getInitParameterNames()  | Returns an Enumeration object with all parameter names       |
| ServletContext getServletContext()   | Returns the ServletContext object representing the current web application |
| String getServletName()              | Return the name of the Servlet                               |

- The configuration of ServletConfig is also done in the web.xml file under the <servlet></servlet> tag, which requires the <init-param></init-param> tag to wrap the parameter name and parameter value to represent a key-value pair, where the <paramname></para- name> for the parameter name, <param-value></param-value> for the parameter value

-  <init-param></init-param> for only one key-value pair

```java
<servlet>
<servlet-name>ServletConfigDemo</servlet-name>
<servlet-class>com.liaoxiangqian.ServletConfigDemo</servlet-class>
<! -- Configure the encoding parameter, using an init-param tag->
<init-param>
<param-name>encoding</param-name>
<param-value>UTF-8</param-value>
</init-param>
<! -- Configure the desc parameter, using an init-param tag -->
<init-param>
<param-name>desc</param-name>
<param-value>this is ServletConfigDemo</param-value>
</init-param>
</servlet>
<servlet-mapping>
<servlet-name>ServletConfigDemo</servlet-name>
<url-pattern>/ServletConfigDemo</url-pattern>
</servlet-mapping>
```

```java
 public void init(ServletConfig config) throws ServletException {
   System.out.println("2 init initialization method");
        // 1. you can get the value of servlet-name alias of Servlet program
        System.out.println("The alias of HelloServlet program is:" + servletConfig.getServletName());
        // 2. get the initialization parameter init-param
        System.out.println("The value of initialization parameter The value of username is;" + servletConfig.getInitParameter("username"));
        System.out.println("The value of the initialization parameter url is;" + servletConfig.getInitParameter("url"));
        // 3, get the ServletContext object
        System.out.println( servletConfig.getServletContext());}

```

> Override the init method inside must call the parent class init (ServiceConfig) operation

```java
public class HelloServlet2 extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        //The init method must be called
        super.init(config);
        System.out.println("Rewrote the init initialization method");
    }
```

# 3.ServletContext

#### a) What is ServletContext?
- Servlet container will create a unique ServletContext object for each Web application to represent the current Web application, ServletContext object encapsulates all the information of the current Web application

  ServletContext can be configured and get the global initialization parameters of the application, and can achieve data sharing between multiple Servlets

  life cycle: the application is loaded to create, the application stops to destroy

  

#### b) ServletContext class of the four roles
- to get the web.xml configuration of the context parameters context-param
- to get the current project path, the format: / project path
- to get the absolute path on the server hard disk after deployment of the project
- Access data like Map

## 2. ServletContext configuration
- The <web-app> tag in the web.xml file is configured by the <context-param> tag, which has two sub-tags
- Sub-tag <param-name> indicates the key of the global initialization parameter
- The sub-tag <param-value> represents the value of the global initialization parameter

```java
<context-param>
<param-name>globalEncoding</param-name>
<param-value>UTF-8</param-value>
</context-param>
<context-param>
<param-name>globaleDesc</param-name>
<param-value>This is SevletContext</param-value>
</context-param>
```
## 3. ServletContext configuration
Get the initialization parameters of the Web application
1. the Enumeration getInitParameterName() method is used to return the Enumeration object containing all the parameter names
2. String getInitParameter(String name) method is used to get the parameter value according to the parameter name to achieve data sharing between multiple Servlets

| method                                    | description                                                  |
| ----------------------------------------- | ------------------------------------------------------------ |
| Enumeration getAttributeNames()           | Returns an Enumeration object containing the names of all domain attributes |
| Object getAttribute(String name)          | Returns the domain attribute value based on the domain attribute name |
| void removeAttribute(String name)         | Remove the corresponding domain attribute from the ServletContext according to the domain attribute name |
| void setAttribute(String name,Object obj) | Set the domain attribute of the ServletContext, where name is the domain attribute name and obj is the domain attribute value |

> Note: The domain attribute refers to the data that can be shared, that is, can be accessed by multiple Servlets

- Read the resource files under the Web application
	- Actual development sometimes need to read some resource files in the Web application, for this reason ServletContext interface defines a number of methods to read Web resources, these methods rely on the Servlet container to achieve, according to the path of the resource file relative to the Web application, return the IO stream associated with the resource file, the absolute path of the resource file, etc.
| method                            | description                                                  |
| --------------------------------- | ------------------------------------------------------------ |
| Set getResourcePaths(String path) | return a Set collection, the set contains the path specified by path subdirectory names and file names but does not contain subdirectory file names, path with a slash / to indicate the root directory of the Web application |
|  String getRealPath(String path)    |   Return the real path of the resource file on the server file system, the parameter path indicates the path of the resource file relative to the web application, starting with a slash / indicates the root directory of the web application, if the virtual path cannot be converted to real path then return null  |
|URL getResource(String path | Returns a URL object mapped to a resource file, the parameter must start with a slash / to indicate the root directory of the web application |
| InputStream getResourceAsStream(String path) |Returns the InputStream object mapped to a resource file, with the same rules for passing path as getResource()|

> Summary: The path in these four methods is actually the path relative to the web application, starting with a slash

# 4. HttpServletRequest

### Overview

- HttpServletRequest interface inherits from ServletRequest interface

- HttpServletRequest is specifically used to encapsulate HTTP request messages

- HttpServletRequest provides a number of methods for accessing the request message, such as getting the request line, getting the request header, etc.