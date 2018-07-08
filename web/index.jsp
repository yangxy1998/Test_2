<%-- JSP文件本质也是一个Servlet，它相当于在HTML中插入Java代码 --%>
<%-- 下面是导入的一些Java包 --%>
<%@ page import="manager.StudentManager" %>
<%@ page import="obj.Student" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/6
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- 下面是导入的一些定制标签 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
  <head>
    <title>学生管理工具</title>
  </head>
  <body>
  <%-- 这一段是JSP脚本，在这里面可以写一些Java代码 --%>
  <%
    request.setAttribute("students",StudentManager.getStudents());
    if(request.getAttribute("update")==null)request.setAttribute("update","");
  %>
  <%--
   下面这一条是一个JSP标准动作，scope表示域。
   这条指令表示从request的作用域域当中找出一个叫做students的Attribute，且指出类型是List<Student>
   这条指令等价于      <% List<student> students=request.getAttribute("students"); %>
   --%>
  <jsp:useBean id="students" type="java.util.List<obj.Student>" scope="request"/>
  <center>
    <form method="post" action="Manager">
      <table border="1">
        <tr>
          <th>学号</th>
          <th>姓名</th>
          <th>性别</th>
          <th>年级</th>
          <th>班级</th>
          <th><input type="submit" name="create->newStudent" value="新增"></th>
        </tr>
        <%-- <% String update=request.getAttribute("update"); %> --%>
        <jsp:useBean id="update" type="java.lang.String" scope="request"/>
        <%--
                      <c:if test=.....></c:if>
         这是一个名为c的定制标签，通过@taglib引入，prefix指出命名空间
         c:if判定test当中的属性是否为真，如果为真则执行标签当中的体
          --%>
        <%--
                       ${object.someAttribute}
        这是一段EL（表达式语言），可以用于访问一些对象
        例如${student.studentNum}就可以访问一个Student对象的studentNum属性，我们在下面会看到
        它会返回一段字符串。
         --%>
        <c:if test="${update.equals(\"newStudent\")}">
          <%-- 这是c:if标签的体 --%>
          <tr>
            <td><input type="text" name="studentNum" placeholder="new student num"></td>
            <td><input type="text" name="studentName" placeholder="new student name"></td>
            <td><input type="text" name="sex" placeholder="new student sex"></td>
            <td><input type="text" name="grade" placeholder="new student grade"></td>
            <td><input type="text" name="classNum" placeholder="new student class num"></td>
            <td><input type="submit" name="commit->newStudent" value="确定"></td>
            <td><input type="submit" name="cancel->newStudent" value="撤销"></td>
          </tr>
          <%-- c:if结束 --%>
        </c:if>
        <%--
                  <c:forEach var=.... items=...></c:forEach>
         顾名思义，它会遍历一个数组或列表。
         在下面的代码中，我们传入的items参数是students，它是一个Student对象列表。
         student就是每一次遍历的元素，也就是一个Student对象。
          --%>
        <c:forEach var="student" items="${students}">
          <%-- foreach开始 --%>
          <c:if test="${update.equals(student.studentNum)}">
            <%-- if开始 --%>
            <tr>
              <td><input type="text" name="studentNum" value="${student.studentNum}"></td>
              <td><input type="text" name="studentName" value="${student.studentName}"></td>
              <td><input type="text" name="sex" value="${student.sex}"></td>
              <td><input type="text" name="grade" value="${student.grade}"></td>
              <td><input type="text" name="classNum" value="${student.classNum}"></td>
              <td><input type="submit" name="commit->${student.studentNum}" value="确定"></td>
              <td><input type="submit" name="cancel->${student.studentNum}" value="撤销"></td>
            </tr>
            <%-- if结束 --%>
          </c:if>
          <c:if test="${!update.equals(student.studentNum)}">
            <%-- if开始 --%>
            <tr>
              <td>${student.studentNum}</td>
              <td>${student.studentName}</td>
              <td>${student.sex}</td>
              <td>${student.grade}</td>
              <td>${student.classNum}</td>
              <td><input type="submit" name="update->${student.studentNum}" value="修改"></td>
              <td><input type="submit" name="remove->${student.studentNum}" value="删除"></td>
            </tr>
            <%-- if结束 --%>
          </c:if>
          <%-- forEach结束 --%>
        </c:forEach>
      </table>
    </form>
  </center>
  </body>
</html>
