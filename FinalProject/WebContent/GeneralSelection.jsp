<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Visualizing</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/submission.css" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="./css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="./css/style.css">
</head>
<body>
<div class="header">
  <h1>FEATURE SELECTION</h1>
  <p>Select the main feature and other feature you want to study</p>
</div>
 
<div class="navbar">
  <a href="start.html">Back</a>
</div>
<form action="VisualizeGeneral" method="get">
	<div class="account">
            <div class="container">
                <div class="register">
                        <div class="register-top-grid">
                            <h3>INPUT DATASET</h3>
                            <div class="input">
                                <span>File Name <label style="color:#ffffff;">*</label></span>
                                <input type="text" name="FileName" placeholder="Please input your File" title="Please fill in this field">
                            </div>
                            <div class="input">
                                <span>Column Name <label style="color:#ffffff;">*</label></span>
                                <input type="text" name="ColName" placeholder="Please input your Coulumn using ',' to split" title="Please fill in this field">
                            </div>
                            <div class="input">
                                <span>Main Feature<label style="color:#ffffff;">*</label></span>
                                <input type="text" name="Main" placeholder="Please input your Main Feature index" title="Please fill in this field">
                            </div>
                            <div class="input">
                                <span>Associate Feature<label style="color:#ffffff;">*</label></span>
                                <input type="text" name="Associate" placeholder="Please input your Associate Feature index using ',' to split" title="Please fill in this field">
                            </div>
                            <div class="input">
                                <span>Anomaly detection<label style="color:#ffffff;">*</label></span>
                                <input type="text" name="Anomaly" placeholder="1 represent proceed anomaly detection, 0 represent not to do the anomaly detection" title="Please fill in this field">
                            </div>
                        </div>
                        <div class="register-but text-center">
                           <input type="submit" value="COMPLETE">
                        </div>
                </div>
            </div>
        </div>
</form>
</body>
</html>