<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>${name}'s cabinet</title>
</head>
<body bgcolor="#7fffd4">
 <H3 align="center">Hello, ${name}!</H3>

 <p align="center">Your tasks for current period are:</p>
 <table border="black" bgcolor="#ff7f50" align="center">
     <tr align="center">
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
 <table align="center"><tr><td>
     <table width="320" border="black" bgcolor="#ff8c00" align="center">
         <form action="MainController" method="post">
             <tr><td colspan="2" align="center" bgcolor="#ff4500"><b>Add new Subordinate</b></td></tr>
             <tr align="center"><td>Name</td><td><input type="text" name="name" value=""></td></tr>
             <tr align="center"><td>Lastname</td><td><input type="text" name="lastname" value=""></td></tr>
             <tr align="center"><td>Mobile</td><td><input type="text" name="mobile" value=""></td></tr>
             <tr align="center"><td>Position</td><td><input type="text" name="position" value=""></td></tr>
             <tr align="center"><td>Department</td><td>${department}</td></tr>
             <tr align="center"><td>jobDescription</td><td><input type="text" name="jobDescription" value=""></td></tr>
             <tr align="center"><td>salary</td><td><input type="number" name="salary" value=""></td></tr>
             <tr align="center"><td>Login</td><td><input type="text" name="login" value=""></td></tr>
             <tr align="center"><td>Password</td><td><input type="text" name="password" value=""></td></tr>
             <td colspan="2" align="center"><input type="submit" name="ok" value="addNewEmployee" align="center"></td>
             </tr>
         </form>
         <tr><td colspan="2" align="center">You have to fill all fields correctly.</td></tr>
     </table>
 </td><td>
     <table width="320" border="black" bgcolor="#ff8c00" align="top">
         <form action="MainController" method="post">
             <tr><td colspan="2" align="center" bgcolor="#ff4500"><b>Add new task</b></td></tr>
             <tr align="center"><td>Title</td><td><input type="text" name="title" value=""></td></tr>
             <tr align="center"><td>Level</td><td><input type="text" name="level" value=""></td></tr>
             <tr align="center"><td>Description</td><td><input type="text" name="description" value=""></td></tr>
             <tr align="center"><td>Planned Quantity</td><td><input type="text" name="quanPlan" value=""></td></tr>
             <tr align="center"><td>Start</td><td><input type="date" name="start" value=""></td></tr>
             <tr align="center"><td>End</td><td><input type="date" name="end" value=""></td></tr>
             <tr align="center"><td>Weight</td><td><input type="text" name="weight" value=""></td></tr>
             <tr align="center"><td>Implementor ID</td><td><input type="number" name="implementer" value=""></td></tr>
             <td colspan="2" align="center"><input type="submit" name="ok" value="addNewTask" align="center"></td>
             </tr>
         </form>
         <tr><td colspan="2" align="center">You have to fill all fields correctly.<br></td></tr>

         <table><tr><td width="320" bgcolor="#7fffd4" align="center"><br></td></tr></table>

         </td><td><table width="320" border="black" bgcolor="#ff8c00" align="top">
         <form action="MainController" method="post">
             <tr><td colspan="2" align="center" bgcolor="#ff4500"><b>Add fact task progress</b></td></tr>
             <tr align="center"><td>Task id</td><td><input type="number" name="taskId" value=""></td></tr>
             <tr align="center"><td>Progress</td><td><input type="number" name="quanFact" value=""></td></tr>
             <td colspan="2" align="center"><input type="submit" name="ok" value="addProgress" align="center"></td>
             </tr>
         </form>
     </table></td></tr>
     </table>

 </table>

</body>
</html>
