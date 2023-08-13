package com.example.sneakers.model;

import android.content.res.Resources;
import android.text.SpannableStringBuilder;
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
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ShoesViewModel extends ViewModel {
    private static final String TAG = "ShoesViewModel";
    private HashMap<String,ShoesModel> itemList = new HashMap<>();;
    private MutableLiveData<List<ShoesModel>> shoesListLiveData = new MutableLiveData<>();

    public LiveData<List<ShoesModel>> getShoesListLiveData(Resources resources) {
        if (shoesListLiveData.getValue() == null) {
            List<ShoesModel> shoesList = parseJsonArray(resources);
            shoesListLiveData.postValue(shoesList);
        }
        return shoesListLiveData;
    }
    private int subTotalPrice=0;
    private int totaltax=0;
  /*  private MutableLiveData<HashMap<String,ShoesModel>> selectedListLiveData = new MutableLiveData<>();

    public LiveData<HashMap<String,ShoesModel>> getSelectedListLiveData() {
        if (shoesListLiveData.getValue() == null) {
            HashMap<String,ShoesModel> shoesMap = new HashMap<>();
            shoesListLiveData.postValue(shoesMap);
        }
        return shoesListLiveData;
    }
    private  HashMap<String,ShoesModel> set*/



    public HashMap<String,ShoesModel> getItemList() {
        return itemList;
    }

    public void addItem(String productId,ShoesModel selectedShoes) {
        itemList.put(productId,selectedShoes);
        addPrice(selectedShoes.getRetailprice(),selectedShoes.getTax());
    }
    public void deleteItem(String productId,ShoesModel shoesModel) {

        itemList.remove(productId);
        deductFromPrice(shoesModel.getRetailprice(),shoesModel.getTax());

    }
    public int itemCount() {
        return this.itemList.size();
    }

    private void addPrice(int price,int tax){
        subTotalPrice= subTotalPrice+price;
        totaltax=totaltax+tax;

    }
    private void deductFromPrice(int price, int tax){
        if(subTotalPrice!=0)
            subTotalPrice= subTotalPrice-price;
        if(totaltax!=0)
            totaltax=totaltax-tax;

    }
    public int getSubTotalPrice(){
        return subTotalPrice;

    }
    public int getTotaltax(){
        return totaltax;

    }
    public int getTotalPrice(){
        return subTotalPrice+totaltax;

    }
    public String appendString(String key, String value){
        SpannableStringBuilder message =new SpannableStringBuilder(key);
        message.append(" : $");
        message.append(value);
        return message.toString();
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
                shoes.setId(jsonObject.getString("id"));
                shoes.setName(jsonObject.getString("name"));
                shoes.setRetailprice(jsonObject.getInt("retailPrice"));
                shoes.setTax(jsonObject.getInt("tax"));
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
