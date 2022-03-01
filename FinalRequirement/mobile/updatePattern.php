<?php
	$con = mysqli_connect("localhost","root","");
	mysqli_select_db($con,"final");
	$res = mysqli_query($con,"UPDATE account SET password = '".$_POST['password']."' WHERE username='". $_POST['userName']."' AND email = '".$_POST['email']."' ");
	
	echo 1;
?>