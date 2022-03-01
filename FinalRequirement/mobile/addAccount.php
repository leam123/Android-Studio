<?php
	$con = mysqli_connect("localhost","root","");
	mysqli_select_db($con,"final");
	mysqli_query($con,"INSERT INTO account(`firstname`, `lastname`, `email`,`username`,`gender`,`password`) VALUES ('". $_POST['firstName']."' , '".$_POST['lastName']."' , '".$_POST['email']."' , '".$_POST['userName']."' , '".$_POST['gender']."' , '".$_POST['password']."')");
	
	echo mysqli_insert_id($con);
?>