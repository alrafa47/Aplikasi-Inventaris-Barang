<?php
	require_once 'koneksi.php';

if($_SERVER['REQUEST_METHOD']=='POST'){
	$idBarang = $_POST['idBarang'];

	$sql = "DELETE FROM barang WHERE idBarang = '$idBarang'";

	if(mysqli_query($con,$sql)){
		echo "data berhasil dihapus";
	}else{

		echo "data gagal dihapus";
	}
}else{
	echo 'eror';
}


?>