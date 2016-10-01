package com.example.xiterss.restservicioweb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
public class MainActivity extends AppCompatActivity implements Button.OnClickListener {

    Button llamada;
    TextView txt;
    String resultado;
    Boolean res = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt=(TextView) findViewById(R.id.txt);
        llamada=(Button) findViewById(R.id.btnLlamada);
        llamada.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        new consumirAsyc().execute();

    }

    public boolean getRespuesta(){
        Boolean resul  =false;

        HttpClient httpClient = new DefaultHttpClient();

        HttpGet url = new HttpGet ("http://services.groupkt.com/country/get/all");

        url.setHeader ("content-type","application/json");

        try{
            HttpResponse respuesta = httpClient.execute(url);
            resultado = EntityUtils.toString(respuesta.getEntity());
            resul = true;
        }catch(Exception e){
            Log.e("Error!", e.getMessage());

        }

        return resul;
    }

    private class consumirAsyc extends  android.os.AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            res=getRespuesta();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if(res){
                txt.setText(resultado);
            }

        }

        @Override
        protected void onPreExecute() {

        }

    }


}
