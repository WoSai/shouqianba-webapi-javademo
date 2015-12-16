<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.wosai.sqb.storemap.*" %>
<%@ page import="java.util.*" %>
<%@ page import="jxl.*"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="jquery-1.8.3.js"></script>
</head>
<body>
<%
String name=request.getParameter("n");
String password=request.getParameter("p");
String city=request.getParameter("city");
ReadMessage rm=new ReadMessage();
if(name!=null&&password!=null){
    boolean flag=rm.isUserExist(name,password,city);
    if(flag==true) {
    session.setAttribute("username",name);


    request.getRequestDispatcher("/map.jsp?city="+city).forward(request, response);
    }else{%>
    <script> alert("用户名或密码输入错误");</script>
  <%  }
}
%>
用户名:<input id="username" type="text"><br>
密&nbsp&nbsp&nbsp码:<input id="passid" type="password">

<select id="flag">
   <option value="SH">上海</option>
   <option value="BJ">北京</option>
   <option value="GZ">广州</option>
   <option value="SZ">深圳</option>
</select><br>
<input id="ok" type="button" value='登录' style="width:150px"  onclick="checkUser()">

<script>
function checkUser(){
      var result = document.getElementById("username").value;
      var password = document.getElementById("passid").value;
      var city=document.getElementById("flag").value;
      if(result == ""  ){
        alert("用户名不能为空");
        return false;
      }
      if(password == ""  ){
       alert("密码不能为空");
        return false;
      }else{
      document.location.href = "hello.jsp?n="+result+"&p="+password+"&city="+city;

      }
}
</script>
</body>
</html>