<?php
	$con = mysqli_connect("localhost","root","");
	mysqli_select_db($con,"final");
	
	$res = mysqli_query($con,"SELECT id FROM `account` WHERE email= '" . $_GET['email']. "' ");
	if (!$res) {
		printf("Error: %s\n", mysqli_error($con));
		exit();
	}
	if(mysqli_num_rows($res)==1){
		echo 1;
	}else{
		echo 0;
	}
?>