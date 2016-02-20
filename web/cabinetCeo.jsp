<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>${name}'s cabinet</title>
</head>
<body bgcolor="#a9a9a9">
 <h3 align="center">Hello, ${name}!</H3>
 <p align="center">Your tasks for current period are:</p>
 <table border="black" bgcolor="#ff8c00" align="center">
     <tr align="center">
         <td bgcolor="#ff4500">Id</td>
         <td bgcolor="#ff4500">Task</td>
         <td bgcolor="#ff4500">Level</td>
         <td bgcolor="#ff4500">Description</td>
         <td bgcolor="#ff4500">Planned Quantity</td>
         <td bgcolor="#ff4500">Real Quantity</td>
         <td bgcolor="#ff4500">Date of start</td>
         <td bgcolor="#ff4500">Date of end</td>
         <td bgcolor="#ff4500">Performance</td>
         <td bgcolor="#ff4500">Bonus</td>
         <td bgcolor="#ff4500">Weight</td>
         <td bgcolor="#ff4500">Ratio</td>
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

 <table border="black" bgcolor="#ff8c00" align="center">
     <tr align="center">
         <td bgcolor="#ff4500">Id</td>
         <td bgcolor="#ff4500">Task</td>
         <td bgcolor="#ff4500">Level</td>
         <td bgcolor="#ff4500">Description</td>
         <td bgcolor="#ff4500">Planned Quantity</td>
         <td bgcolor="#ff4500">Real Quantity</td>
         <td bgcolor="#ff4500">Date of start</td>
         <td bgcolor="#ff4500">Date of end</td>
         <td bgcolor="#ff4500">Performance</td>
         <td bgcolor="#ff4500">Bonus</td>
         <td bgcolor="#ff4500">Weight</td>
         <td bgcolor="#ff4500">Ratio</td>
     </tr>
     <c:forEach items="${tasksToArchive}" var="t">
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
    <tr><td colspan="2" align="center" bgcolor="#ff4500"><b>Add new Superior</b></td></tr>
    <tr align="center"><td>Name</td><td><input type="text" name="name" value=""></td></tr>
    <tr align="center"><td>Lastname</td><td><input type="text" name="lastname" value=""></td></tr>
    <tr align="center"><td>Mobile</td><td><input type="text" name="mobile" value=""></td></tr>
    <tr align="center"><td>Position</td><td><input type="text" name="position" value=""></td></tr>
    <tr align="center"><td>Department</td><td><input type="text" name="department" value=""></td></tr>
    <tr align="center"><td>jobDescription</td><td><input type="text" name="jobDescription" value=""></td></tr>
    <tr align="center"><td>salary</td><td><input type="number" name="salary" value=""></td></tr>
    <tr align="center"><td>Login</td><td><input type="text" name="login" value=""></td></tr>
    <tr align="center"><td>Password</td><td><input type="text" name="password" value=""></td></tr>
    <td colspan="2" align="center"><input type="submit" name="ok" value="addNewSuperior" align="center"></td>
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
         <tr align="center"><td>Start</td><td><input type="date" lang="english" name="start" value=""></td></tr>
         <tr align="center"><td>End</td><td><input type="date" lang="Eng" name="end" value=""></td></tr>
         <tr align="center"><td>Weight</td><td><input type="text" name="weight" value=""></td></tr>
         <tr align="center"><td>Implementer ID</td><td><input type="number" name="implementer" value=""></td></tr>
         <td colspan="2" align="center"><input type="submit" name="ok" value="addNewTask" align="center"></td>
         </tr>
     </form>
     <tr><td colspan="2" align="center">You have to fill all fields correctly.<br></td></tr>

     <table><tr><td width="320" bgcolor="#a9a9a9" align="center"><br></td></tr></table>

     <table width="320" border="black" bgcolor="#ff8c00">
         <form action="MainController" method="post">
             <tr><td colspan="2" align="center" bgcolor="#ff4500"><b>Add bonus min percentage.</b></td></tr>
             <tr align="center"><td>Ceo</td><td><input type="number" name="rateCeo" value=""></td></tr>
             <tr align="center"><td>Superior</td><td><input type="number" name="rateSup" value=""></td></tr>
             <tr align="center"><td>Employees</td><td><input type="number" name="rateEmp" value=""></td></tr>
             <td colspan="2" align="center"><input type="submit" name="ok" value="addRates" align="center"></td>
             </tr>
         </form>
         <tr><td colspan="2" align="center">This is the % of plan/fact ratio<br>below which there wil be no bonus.<br></td></tr>
     </table>
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
