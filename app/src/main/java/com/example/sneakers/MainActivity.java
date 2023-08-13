package com.example.sneakers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.example.sneakers.databinding.ActivityMainBinding;
import com.example.sneakers.model.JsonUtils;
import com.example.sneakers.model.ShoesModel;
import com.example.sneakers.model.ShoesViewModel;
import com.example.sneakers.views.ShoesInventoryFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static ActivityMainBinding binding;
    private ShoesViewModel shoesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        shoesViewModel = new ViewModelProvider(this).get(ShoesViewModel.class);
        parseJsonToDataModel();
    }
    @Override
    public void onBackPressed ()
    {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    private void parseJsonToDataModel(){
        shoesViewModel.getShoesListLiveData(getResources())
                .observe(this, new Observer<List<ShoesModel>>() {
                    @Override
                    public void onChanged(List<ShoesModel> userList) {
                        Fragment shoesInventoryFragment = new ShoesInventoryFragment();
                        getSupportFragmentManager().beginTransaction().add(R.id.container, shoesInventoryFragment).commit();
                    }
                });
    }


    public ShoesViewModel getShoesViewModel() {
        return shoesViewModel;
    }


}