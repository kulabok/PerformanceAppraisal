<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Welcome!</title>
  </head>
  <body>
<table width="800" border="black" bgcolor="yellow" align="center">
  <tr><td align="center"><H3>Hello newcomer!</H3>

  <p>This is a performance appraisal system of our company.</p></td></tr>
  <tr><td align="center"><p>To get in you have to:</p>
    <form action="MainController" method="post">
      <input type="text" name="login" value=""><br>
      <input type="text" name="password" value=""><br>
      <input type="submit" name="ok" value="Login">
    </form>
  </td></tr>
  <tr><td align="center"><p>Or if you have no login and password you can contact our admin to get it.</p></td></tr>
</table>
  </body>
</html>
