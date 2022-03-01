<?php
	$con = mysqli_connect("localhost","root","");
	mysqli_select_db($con,"employee");
	
	$res = mysqli_query($con,"SELECT * FROM `person` WHERE id=" . $_GET['id']);
	if (!$res) {
		printf("Error: %s\n", mysqli_error($con));
		exit();
	}
	
	$s = "";
	if($rec = mysqli_fetch_array($res)){
		$s .= $rec['id'] . ":" . $rec['name'] . ":" . $rec['address'] . ":" . $rec['salary'] . ";";
	}
	echo $s;
?>