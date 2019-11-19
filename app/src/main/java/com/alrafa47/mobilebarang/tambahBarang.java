package com.alrafa47.mobilebarang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class tambahBarang extends AppCompatActivity {
    private EditText etNama,etJumlah, etHarga;
    private RadioGroup rgStatus;
    private Button btnSave;
    private RadioButton radStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_barang);
        etNama = findViewById(R.id.et_nama);
        etJumlah = findViewById(R.id.et_jumlah);
        rgStatus = findViewById(R.id.radGrup_status);
        etHarga = findViewById(R.id.et_harga);

        btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int data = rgStatus.getCheckedRadioButtonId();
                radStatus = findViewById(data);
                StringRequest postRequest = new StringRequest(Request.Method.POST, "http://192.168.43.15/barang/tambah_barang.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("Respone", "onResponse: " + response);
                                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ERROR", "onErrorResponse: " + error);
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

                    }
                }){
                    @Override
                    protected Map<String, String> getParams(){
                        Map<String, String> param = new HashMap<String, String>();
                        param.put("nama", etNama.getText().toString());
                        param.put("harga", etHarga.getText().toString());
                        param.put("jumlah", etJumlah.getText().toString());
                        param.put("status", radStatus.getText().toString());
                        return param;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(postRequest);
            }
        });
    }
}
