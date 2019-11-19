<?php 

require_once 'koneksi.php';
$sql = "select * from barang";
$result = mysqli_query($con,$sql);

$number_of_rows = mysqli_num_rows($result);
$temp_array = array();

if($number_of_rows > 0){
	while ($row = mysqli_fetch_assoc($result)){
		array_push($temp_array, array(
			"idBarang"=>$row['idBarang'],
			"nama"=>$row['nama'],
			"jumlah"=>$row['jumlah'],
			"harga"=>$row['harga'],
			"status"=>$row['status']
		));
	}
}else{
	echo "tidak ada data";
}
echo json_encode($temp_array);
?>