<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>${name}'s cabinet</title>
</head>
<body bgcolor="#faebd7">
 <H3 align="center">Hello, ${name}!</H3>

<p align="center">Your tasks for current period are:</p>
 <table border="black" bgcolor="aqua" align="center">
     <tr align="center" bgcolor="#00ced1">
         <td>Id</td>
         <td>Task</td>
         <td>Level</td>
         <td>Description</td>
         <td>Planned Quantity</td>
         <td>Real Quantity</td>
         <td>Date of start</td>
         <td>Date of end</td>
         <td>Performance</td>
         <td>Bonus</td>
         <td>Weight</td>
         <td>Ratio</td>
     </tr>
     <c:forEach items="${taskList}" var="t">
        <tr>
            <td>${t.id} </td>
            <td>${t.title} </td>
            <td>${t.level}</td>
            <td>${t.description}</td>
            <td>${t.quanPlan}</td>
            <td>${t.quanFact}</td>
            <td>${t.start}</td>
            <td>${t.end}</td>
            <td>${t.performance}</td>
            <td>${t.bonus}</td>
            <td>${t.weight}</td>
            <td>${t.ratio}</td>
        </tr>
        <p> </p>
    </c:forEach>
 </table>
<br>
 <table width="320" border="black" bgcolor="aqua" align="center">
     <form action="MainController" method="post">
         <tr align="center" ><td colspan="2" align="center" bgcolor="#00ced1"><b>Add fact task progress</b></td></tr>
         <tr align="center"><td>Task id</td><td><input type="number" name="taskId" value=""></td></tr>
         <tr align="center"><td>Progress</td><td><input type="number" name="quanFact" value=""></td></tr>
         <td colspan="2" align="center"><input type="submit" name="ok" value="addProgress" align="center"></td>
         </tr>
     </form>
 </table>
</body>
</html>
