<?php
	$con = mysqli_connect("localhost","root","");
	mysqli_select_db($con,"employee");
	$res = mysqli_query($con,"DELETE FROM `person` WHERE id=" . $_GET['id']);
	if(mysqli_error($con)==""){echo 1;}
?>