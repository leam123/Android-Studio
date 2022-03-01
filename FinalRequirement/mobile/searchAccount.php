<?php
	$con = mysqli_connect("localhost","root","");
	mysqli_select_db($con,"final");
	
	$res = mysqli_query($con,"SELECT * FROM `account` WHERE username= '" . $_GET['username']. "' AND  password= '" . $_GET['password']. "' ");
	if (!$res) {
		printf("Error: %s\n", mysqli_error($con));
		exit();
	}
	
	$s = "";
	if($rec = mysqli_fetch_array($res)){
		$s .= $rec['firstname'] . ":" . $rec['lastname'] . ":" . $rec['email'] . ":" . $rec['username'] .  ":" . $rec['gender'];
	}
	echo $s;
?>