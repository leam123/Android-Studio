<?php
	mysqli_connect("localhost","root","");
	
	mysqli_select_db("employee");
	
	$res = mysqli_query("SELECT * FROM person");
	$s = "";
	while($rec = mysqli_fetch_assoc($res)){
		$s .= $rec['id'] . ":" . $rec['name'] . ":" . $rec['address'] . ":" . $rec['salary'] . ";";
	}
	echo $s;
?>