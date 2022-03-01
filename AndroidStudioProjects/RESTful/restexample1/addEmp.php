<?php
	$con = mysqli_connect("localhost","root","");
	mysqli_select_db($con,"employee");
	//mysqli_query($con,"INSERT INTO person VALUES(NULL,'" . $_POST['name'] . "','".$_POST['address']."',NULL)");
	mysqli_query($con,"INSERT INTO `person`(`name`, `address`, `salary`) VALUES ('" . $_POST['name'] . "','".$_POST['address']."','".$_POST['salary']."')");
	
	echo mysqli_insert_id($con);
?>