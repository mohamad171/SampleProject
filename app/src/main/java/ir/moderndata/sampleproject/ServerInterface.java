package ir.moderndata.sampleproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.util.Map;

public class ServerInterface {
    Context context;
    RequestQueue queue;
    String baseUrl="http://192.168.1.104:8000";
    responseListeneer responselisteneer;

    public ServerInterface(Context context ) {
        this.context = context;
        queue = Volley.newRequestQueue(context);


    }

    public void Get(String resource, responseListeneer responseListeneer){
        responselisteneer = responseListeneer;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, baseUrl+resource,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        responselisteneer.OnResponse(response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "خطایی در ارتباط با سرور رخ داده است", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }
    public void Post(String resource, final Map<String,String> params, responseListeneer responseListeneer){
        responselisteneer = responseListeneer;
        Log.wtf("dataaa",baseUrl+resource);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, baseUrl+resource,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        responselisteneer.OnResponse(response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.wtf("dataaa",error.toString());
                Toast.makeText(context, "خطایی در ارتباط با سرور رخ داده است", Toast.LENGTH_SHORT).show();
            }

        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        queue.add(stringRequest);


    }
    public void PostImage(String resource, final Map<String,String> params,Bitmap bitmap, responseListeneer responseListeneer){
        params.put("image",BitMapToString(bitmap));

        Post(resource,params,responseListeneer);

    }

    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp=Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }
    public interface responseListeneer{
        public void OnResponse(String content);
    }

}
