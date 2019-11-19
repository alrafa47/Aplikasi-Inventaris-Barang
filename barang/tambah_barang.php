<?php
	require_once 'koneksi.php';

if($_SERVER['REQUEST_METHOD']=='POST'){
	$nama = $_POST['nama'];
	$jumlah = $_POST['jumlah'];
	$harga = $_POST['harga'];
	$status = $_POST['status'];

	$sql = "INSERT INTO barang (nama, jumlah, harga, status) VALUES ('$nama', '$jumlah', '$harga', '$status')";

	if(mysqli_query($con,$sql)){
		echo "data berhasil disimpan";
	}else{

		echo "data gagal disimpan";
	}
}else{
	echo 'eror koneksi';
}


?>