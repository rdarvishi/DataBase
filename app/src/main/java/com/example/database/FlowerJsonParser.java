package com.example.database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FlowerJsonParser {

    public static List<Flower> parsJson (InputStream inputStream){
        StringBuilder sb = new StringBuilder();
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        while (true){
            try {
                if (!(bis.available() != 0)) {
                    sb.append((char) bis.read() );
                }
                bis.close();
                return parsJson(sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
                return parsJson("") ;
            }

        }
    }

    public static List<Flower> parsJson (String jsonString){
        List<Flower> flowers = new ArrayList<>();
        if (jsonString.isEmpty())return flowers ;
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i=0 ; i <jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Flower flower = new Flower();


                flower.setName(jsonObject.getString(Flower.KEY_NAME));
                flower.setCategory(jsonObject.getString(Flower.KEY_CAT));
                flower.setInstructions(jsonObject.getString(Flower.KEY_INSTR));
                flower.setPhoto(jsonObject.getString(Flower.KEY_PHOTO));
                flower.setPrice(jsonObject.getDouble(Flower.KEY_PRICE));
                flower.setProductId(jsonObject.getInt(Flower.KEY_ID));

                flowers.add(flower);
            }

        } catch (JSONException e) {

            e.printStackTrace();
        }
        return flowers;
    }
}
