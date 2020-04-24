<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Visualizing</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/submission.css" rel="stylesheet">
<link href="./css/myButtons.css" rel="stylesheet">
</head>
<body>
<div class="header">
  <h1>FEATURE SELECTION</h1>
  <p>Select the main feature and other feature you want to study</p>
</div>
 
<div class="navbar">
  <a href="map.html">Back</a>
</div>
<form action="HelloServlet" method="get">
	<div class="row">
		<div class = "leftside">
			<p style="margin-left: 400px;"><font size="5" face="arial">Main feature</font></p>
			<div style ="margin-top: 50px;margin-left: 400px;">
				<input type="radio" name="main" value="Precipitation" /> Precipitation<br/>
				<input type="radio" name="main" value="Evaporation" /> Evaporation<br />
				<input type="radio" name="main" value="Humidity" /> Humidity<br />
				<input type="radio" name="main" value="Pressure" /> Pressure<br />
				<input type="radio" name="main" value="Sunlightdur" /> Sunlight duration<br />
				<input type="radio" name="main" value="Temperature" /> Temperature<br />
				<input type="radio" name="main" value="WindSpeed" /> Wind Speed<br />
				<input type="radio" name="main" value="WindDirection" /> Wind Direction<br />
			</div>
		</div>
			
		<div class = "rightside">
			<p style="margin-left: 200px;"><font size="5" face="arial">Other features</font></p>
			<div style ="margin-top: 50px;margin-left: 200px;">
				<input type="checkbox" name="attribute" value="Precipitation" /> Precipitation<br/>
				<input type="checkbox" name="attribute" value="Evaporation" /> Evaporation<br />
				<input type="checkbox" name="attribute" value="Humidity" /> Humidity<br />
				<input type="checkbox" name="attribute" value="Pressure" /> Pressure<br />
				<input type="checkbox" name="attribute" value="Sunlightdur" /> Sunlight duration<br />
				<input type="checkbox" name="attribute" value="Temperature" /> Temperature<br />
				<input type="checkbox" name="attribute" value="WindSpeed" /> Wind Speed<br />
				<input type="checkbox" name="attribute" value="WindDirection" /> Wind Direction<br />
			</div>
		</div>
	</div>
	<div style="margin:50px">
		<input type ="hidden" name="province" value=<%=request.getParameter("province")%>>

		<center><input type="submit" value="Submit" class = "button"/></center>
		<center><p style="color:red">Sorry there is something wrong with your selection</p></center>
	</div>
</form>
</body>
</html>