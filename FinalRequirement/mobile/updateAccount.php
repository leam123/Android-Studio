<?php
	$con = mysqli_connect("localhost","root","");
	mysqli_select_db($con,"final");
	$res = mysqli_query($con,"UPDATE account SET firstname = '".$_POST['firstName']."' , lastname = '".$_POST['lastName']."', email = '".$_POST['email']."', username = '".$_POST['userName']."', gender = '".$_POST['gender']."' WHERE username='". $_POST['prev']."' ");
	
	echo 1;
?>