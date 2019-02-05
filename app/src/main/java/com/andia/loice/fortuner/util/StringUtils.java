package com.andia.loice.fortuner.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class StringUtils {

    private final Context context;

    public StringUtils(Context context) {
        this.context = context;
    }

    public String readFile(String fileName) throws IOException {
        BufferedReader reader = null;
        reader = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName), StandardCharsets.UTF_8));

        String content = "";
        String line;
        while ((line = reader.readLine()) != null) {
            content = content + line;
        }

        return content;

    }

//    public Fortune getFortune() {
//        String jsonFileContent;
//        Fortune fortune = null;
//        try {
//            jsonFileContent = readFile("test-data.json");
//            JSONArray jsonArray = null;
//            JSONObject jsonObj = jsonArray.getJSONObject(i);
//            jsonArray = new JSONArray(jsonFileContent);
//            for (int i = 0; i < jsonArray.length(); i++) {
//                List<String> fileFortune = jsonObj.getJSONArray("fortune");
//                fortune = new Fortune(fileFortune);
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return fortune;
//
//    }
}
