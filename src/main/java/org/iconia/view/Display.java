package org.iconia.view;

import org.iconia.domain.TwillioHelper;

public class Display {


    //  help with local testing to avoid making api requests





    /// construction for working with api
    TwillioHelper helper;
    Display(TwillioHelper helper) {
        this.helper = helper;
    }

    /// construction for working without api

    Display(){

    }

    void displayText(String from,String to, String text){
        if(helper != null){
            helper.sendMessage(from,to,text);
        }else{
            System.out.println(text);
        }
    }
}
