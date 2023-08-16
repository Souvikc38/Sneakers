package com.example.sneakers.model;

import android.content.res.Resources;
import android.text.SpannableStringBuilder;

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
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ShoesViewModel extends ViewModel {
    private static final String TAG = "ShoesViewModel";
    private HashMap<String, ShoesModel> itemMap = new HashMap<>();
    private MutableLiveData<List<ShoesModel>> shoesListLiveData = new MutableLiveData<>();

    public LiveData<List<ShoesModel>> getShoesListLiveData(Resources resources) {
        if (shoesListLiveData.getValue() == null) {
            List<ShoesModel> shoesList = parseJsonArray(resources);
            shoesListLiveData.postValue(shoesList);
        }
        return shoesListLiveData;
    }
    private int subTotalPrice = 0;
    private int totalTax = 0;

    public HashMap<String, ShoesModel> getItemList() {
        return itemMap;
    }

    /*
      * Add Item to cart logic
      * */
    public void addItem(String productId, ShoesModel selectedShoes) {
        if (!itemMap.containsKey(productId)) {
            itemMap.put(productId, selectedShoes);
            addPrice(selectedShoes.getRetailprice(), selectedShoes.getTax());
        }
    }

    /*
      * Remove Item from cart logic
   * */
    public void deleteItem(String productId, ShoesModel shoesModel) {
        itemMap.remove(productId);
        deductFromPrice(shoesModel.getRetailprice(), shoesModel.getTax());
    }

    public int itemCount() {
        return this.itemMap.size();
    }

    private void addPrice(int price, int tax) {
        subTotalPrice = subTotalPrice + price;
        totalTax = totalTax + tax;

    }

    private void deductFromPrice(int price, int tax) {
        if (subTotalPrice != 0)
            subTotalPrice = subTotalPrice - price;
        if (totalTax != 0)
            totalTax = totalTax - tax;

    }

    public int getSubTotalPrice() {
        return subTotalPrice;

    }

    public int getTotaltax() {
        return totalTax;

    }

    public int getTotalPrice() {
        return subTotalPrice + totalTax;

    }

    public String appendString(String key, String value) {
        SpannableStringBuilder message = new SpannableStringBuilder(key);
        message.append(" : $");
        message.append(value);
        return message.toString();
    }

    /*
      * Parsing json data into Data Model.
      * */
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
                shoes.setId(jsonObject.getString("id"));
                shoes.setName(jsonObject.getString("name"));
                shoes.setRetailprice(jsonObject.getInt("retailPrice"));
                shoes.setTax(jsonObject.getInt("tax"));
                shoes.setBrand(jsonObject.getString("brand"));
                shoes.setGender(jsonObject.getString("gender"));
                shoes.setReleasedate(jsonObject.getString("releaseDate"));
                JSONObject mediaObject = jsonObject.getJSONObject("media");
                Media media = new Media();
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
