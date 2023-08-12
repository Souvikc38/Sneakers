package com.example.sneakers.model;

import android.content.res.Resources;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sneakers.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShoesViewModel extends ViewModel {
    private static final String TAG = "ShoesViewModel";

    private MutableLiveData<List<ShoesModel>> shoesListLiveData = new MutableLiveData<>();

    public LiveData<List<ShoesModel>> getShoesListLiveData(Resources resources) {
        if (shoesListLiveData.getValue() == null) {
            List<ShoesModel> userList = parseJsonArray(resources);
            shoesListLiveData.postValue(userList);
        }
        return shoesListLiveData;
    }

    private List<ShoesModel> parseJsonArray(Resources resources) {
        List<ShoesModel> shoesList = new ArrayList<>();
        try {
            InputStream inputStream = resources.openRawResource(R.raw.shoes);
            Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
            String json = scanner.hasNext() ? scanner.next() : "";
            scanner.close();
            JSONArray shoesJsonArray = new JSONArray(json);

            for (int i = 0; i < shoesJsonArray.length(); i++) {
                JSONObject jsonObject = shoesJsonArray.getJSONObject(i);
                ShoesModel shoes = new ShoesModel();
                shoes.setName(jsonObject.getString("name"));
                shoes.setRetailprice(jsonObject.getInt("retailPrice"));
                JSONObject mediaObject = jsonObject.getJSONObject("media");
                ShoesModel.Media media = new ShoesModel.Media();
                media.setImageurl(mediaObject.getString("imageUrl"));
                shoes.setMedia(media);
                shoesList.add(shoes);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return shoesList;
    }
}
