<?php
	require_once 'koneksi.php';

if($_SERVER['REQUEST_METHOD']=='POST'){
	$idBarang = $_POST['idBarang'];
	$nama = $_POST['nama'];
	$jumlah = $_POST['jumlah'];
	$harga = $_POST['harga'];
	$status = $_POST['status'];
	$sql = "UPDATE barang SET nama = '$nama', jumlah = '$jumlah', harga= '$harga',  status = '$status' WHERE idBarang = '$idBarang'";

	if(mysqli_query($con,$sql)){
		echo "data berhasil diganti";
	}else{
		echo "Data gagal disimpan";
	}
}else{
	echo 'Error koneksi database';
}


?>