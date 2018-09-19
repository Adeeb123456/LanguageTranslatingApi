package com.example.designerclicksol.languagetranslatingapi;

import android.app.Application;

import com.example.designerclicksol.languagetranslatingapi.ws.ServiceGenerator;
import com.example.designerclicksol.languagetranslatingapi.ws.WebService;

public class AppBase extends Application {
    WebService webService;


    @Override
    public void onCreate() {
        super.onCreate();

        webService=ServiceGenerator.createService(WebService.class);
    }


    public WebService getWebService(){

        if(webService==null){
            webService=ServiceGenerator.createService(WebService.class);
        }
        return webService;
    }
}
