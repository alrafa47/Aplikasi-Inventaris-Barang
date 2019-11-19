package com.alrafa47.mobilebarang;

import android.content.Intent;
import android.os.Bundle;

import com.alrafa47.mobilebarang.Adapter.AdapterBarang;
import com.alrafa47.mobilebarang.model.ModelBarang;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<ModelBarang> list;
    RecyclerView RvBarang;
    AdapterBarang adapterBarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        RvBarang = (RecyclerView) findViewById(R.id.rv_Item);
        RvBarang.setHasFixedSize(true);
        RvBarang.setLayoutManager(layoutManager);
            list = new ArrayList<>();
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, tambahBarang.class);
                startActivity(i);
            }
        });
        getData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getData(){
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "http://192.168.43.15/barang/list_barang.php",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            for (int a = 0; a < response.length(); a++){
                                JSONObject jsonObject = response.getJSONObject(a);
                                ModelBarang barang = new ModelBarang(
                                        jsonObject.getString("idBarang"),
                                        jsonObject.getString("nama"),
                                        jsonObject.getString("jumlah"),
                                        jsonObject.getString("harga"),
                                        jsonObject.getString("status")
                                );
                                list.add(barang);
                            }
                            System.out.println("" +response);
                            adapterBarang = new AdapterBarang(getApplicationContext(), list);
                            RvBarang.setAdapter(adapterBarang);
                        }catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), "Error Json : "+e,Toast.LENGTH_LONG).show();
                            System.out.println("" +e);

                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error Response : "+error,Toast.LENGTH_LONG).show();
                System.out.println("" +error);

            }
        });
        Volley.newRequestQueue(this).add(jsonArrayRequest);
    }



}
