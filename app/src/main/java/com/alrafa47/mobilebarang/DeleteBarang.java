package com.alrafa47.mobilebarang;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class DeleteBarang {

    public DeleteBarang(final Context context, final String delete) {

        StringRequest post = new StringRequest(Request.Method.POST, "http://192.168.43.15/barang/delete_barang.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, response+"", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(context, MainActivity.class);
                        context.startActivity(i);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error+"", Toast.LENGTH_LONG).show();

            }
        }
        ){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> param = new HashMap<String, String>();
                param.put("idBarang", delete);
                return param;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(post);

    }

}
