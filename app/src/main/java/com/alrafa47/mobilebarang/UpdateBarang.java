package com.alrafa47.mobilebarang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class UpdateBarang extends AppCompatActivity {
    private EditText etUpdateNama, etUpdateHarga, etUpdateJumlah;
    private RadioGroup radioGroup;
    private RadioButton rbAda, rbHabis;
    private TextView tvidBarang;
    private Button btnUpdate;
    String UpdateStatus = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_barang);
        etUpdateHarga = findViewById(R.id.et_update_harga);
        etUpdateJumlah = findViewById(R.id.et_update_jumlah);
        etUpdateNama = findViewById(R.id.et_update_nama);
        radioGroup = findViewById(R.id.radGrup_update_status);
        tvidBarang = findViewById(R.id.tv_update_idbarang);
        rbAda = findViewById(R.id.rb_update_ada);
        rbHabis = findViewById(R.id.rb_update_habis);

        tvidBarang.setText(getIntent().getExtras().getString("idBarang"));
        etUpdateHarga.setText(getIntent().getExtras().getString("harga"));
        etUpdateNama.setText(getIntent().getExtras().getString("nama"));
        etUpdateJumlah.setText(getIntent().getExtras().getString("jumlah"));
        String status = getIntent().getExtras().getString("status");

        if (status.equalsIgnoreCase("Layak")){
            rbAda.setChecked(true);
        }else{
            rbHabis.setChecked(true);
        }

                btnUpdate = findViewById(R.id.btn_update);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int data = radioGroup.getCheckedRadioButtonId();
                switch (data){
                    case R.id.rb_update_ada :
                        UpdateStatus = "Layak";
                        break;
                    case R.id.rb_update_habis:
                        UpdateStatus = "Rusak";
                        break;

                }
                StringRequest postRequest = new StringRequest(Request.Method.POST, "http://192.168.43.15/barang/update_barang.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                Toast.makeText(getApplicationContext(), response+"", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(UpdateBarang.this, MainActivity.class);
                                startActivity(i);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error+"", Toast.LENGTH_LONG).show();

                    }
                }){
                    @Override
                    protected Map<String, String> getParams(){
                        Map<String, String> param = new HashMap<String, String>();
                        param.put("idBarang", tvidBarang.getText().toString());
                        param.put("nama", etUpdateNama.getText().toString());
                        param.put("harga", etUpdateHarga.getText().toString());
                        param.put("jumlah", etUpdateJumlah.getText().toString());
                        param.put("status", UpdateStatus);

                        return param;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(postRequest);
            }
        });


    }
}
