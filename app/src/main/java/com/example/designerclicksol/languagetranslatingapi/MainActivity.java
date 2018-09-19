package com.example.designerclicksol.languagetranslatingapi;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.designerclicksol.languagetranslatingapi.ws.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    WebService webService;

    ProgressBar progressBar;

    EditText editTextToTrans;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webService=((AppBase)getApplicationContext()).getWebService();
     progressBar=(ProgressBar)findViewById(R.id.progressBar);
     editTextToTrans=(EditText)findViewById(R.id.etLanguage);
     textView=(TextView)findViewById(R.id.result);


    }
    GoogleTranslateWithHttpReq translator;

    public void translate(View view){
new myAsn().execute();

    }




    public class myAsn extends AsyncTask<Void ,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            translator = new GoogleTranslateWithHttpReq("AIzaSyA3fuYXns09lHGYSGoF6aAueTEY2T1mLFo");

        String tst=    translator.translte(editTextToTrans.getText()+"","en","ur");
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
            String rePonseTxt=translator.successTxtTranslated;
            if(rePonseTxt==null||rePonseTxt.equals("")||rePonseTxt.isEmpty()){
                textView.setText(translator.errorText);
            }else {
                textView.setText(translator.successTxtTranslated);
            }

        }
    }



    public void callWebApiToTranslate(View view){
        try {

            if(TextUtils.isEmpty(editTextToTrans.getText().toString())){
                Toast.makeText(getApplicationContext(),"Enter  language to translate",Toast.LENGTH_SHORT).show();
                return;
            }

            progressBar.setVisibility(View.VISIBLE);
        String apiKey="";
        String textToTranslate="how are you ";


        textToTranslate=editTextToTrans.getText().toString();

        if(TextUtils.isEmpty(textToTranslate)){
            Toast.makeText(getApplicationContext(),"translating field should not be empty",Toast.LENGTH_SHORT).show();
            return;
        }


        String encodedText= URLEncoder.encode(textToTranslate,"UTF-8");

        String translateFrom="en";
        String translateTo="ur";


        Call<ResponseBody> call=webService.getTranslatedString(apiKey,encodedText,translateTo,translateFrom);


        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressBar.setVisibility(View.GONE);
                if(response!=null&&response.isSuccessful()){
                   String responseStr=response.body().toString();

                }else {


                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),"error in translating language"+response.errorBody().toString(),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Log.d("debug",t.getMessage()+"");
            }
        });

        } catch (UnsupportedEncodingException e) {
            progressBar.setVisibility(View.GONE);
            e.printStackTrace();
        }

    }





    public void callWebApiFromCompleteURl(View view) throws UnsupportedEncodingException {
        if(TextUtils.isEmpty(editTextToTrans.getText().toString())){
            Toast.makeText(getApplicationContext(),"Enter  language to translate",Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        String apiKey="AIzaSyA3fuYXns09lHGYSGoF6aAueTEY2T1mLFo";
        String textToTranslate="how are you ";


        textToTranslate=editTextToTrans.getText().toString();

        if(TextUtils.isEmpty(textToTranslate)){
            Toast.makeText(getApplicationContext(),"translating field should not be empty",Toast.LENGTH_SHORT).show();
            return;
        }


        String encodedText= URLEncoder.encode(textToTranslate,"UTF-8");

        String translateFrom="en";
        String translateTo="ur";






        Call<ResponseBody> call=webService.getTranslatedStringThroughCompleteUrl(geterateURl(apiKey,encodedText,translateTo,translateFrom));

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressBar.setVisibility(View.GONE);
                if(response!=null)
                    if(response.isSuccessful()){
                        String responseStr= null;
                        try {
                            responseStr = response.body().string();

                         textView.setText(parseJsonString(responseStr));

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Log.d("debug",responseStr);
                }else {
                   String error= response.errorBody().toString();
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),"error in translating language"+response.message().toString(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressBar.setVisibility(View.GONE);


                Log.d("debug",t.getMessage()+"");
            }
        });
    }




    public String parseJsonString(String strToPArse){
        String sTraslation="";
        try {
            JSONObject jsonObject=new JSONObject(strToPArse);

            JSONArray jsonArray=jsonObject.optJSONArray("translations");
            jsonArray=   jsonObject.getJSONObject("data").getJSONArray("translations");

            sTraslation=jsonArray.getJSONObject(0).getString("translatedText");





        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sTraslation;
    }


    public String geterateURl(String key,String encodedText,String to,String from){
        String urlStr = "https://www.googleapis.com/language/translate/v2?key=" + key + "&q=" + encodedText + "&target=" + to + "&source=" + from;

        return urlStr;
    }
}
