<?php
	$con = mysqli_connect("localhost","root","");
	mysqli_select_db($con,"employee");
	$res = mysqli_query($con,"UPDATE person SET name = '".$_POST['name']."' , address = '".$_POST['address']."', salary = '".$_POST['salary']."' WHERE id =". $_GET['id']);
	
	if(mysqli_error($con)==""){
		echo 1;
	}
?>