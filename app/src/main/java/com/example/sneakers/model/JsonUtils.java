package com.example.sneakers.model;

import android.content.Context;
import android.content.res.Resources;

import com.example.sneakers.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class JsonUtils {
    public static JSONArray loadJSONFromRaw(Resources resources) {
        try {
            InputStream inputStream = resources.openRawResource(R.raw.shoes);
            Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
            String json = scanner.hasNext() ? scanner.next() : "";
            scanner.close();
            return new JSONArray(json);
        } catch ( JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
