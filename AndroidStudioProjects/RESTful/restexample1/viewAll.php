<?php
	$con = mysqli_connect("localhost","root","");
	mysqli_select_db($con,"employee");
	
	$res = mysqli_query($con,"SELECT * FROM person");
	$t = "                                                           ";
	$s = "";
	while($rec = mysqli_fetch_assoc($res)){
		$s .= $rec['id'] . "  :  " . $rec['name'] . "  :  " . $rec['address'] . "  :  " . $rec['salary'] . "$t";
	}
	echo $s;
?>