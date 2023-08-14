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
import android.view.MenuItem;

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
        setSupportActionBar(binding.toolbar);
        shoesViewModel = new ViewModelProvider(this).get(ShoesViewModel.class);
        Fragment shoesInventoryFragment = new ShoesInventoryFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, shoesInventoryFragment,shoesInventoryFragment.getClass().getName()).commit();
    }
    /*
       Handle back button press on the toolbar
    * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
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

    public void showToolbar(){
        if (binding.toolbar != null) {

            Fragment homeFrag = getSupportFragmentManager().findFragmentById(R.id.container);
            if(homeFrag instanceof ShoesInventoryFragment){
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                getSupportActionBar().setDisplayShowHomeEnabled(false);
            } else {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
            }
        }
    }

    public ShoesViewModel getShoesViewModel() {
        return shoesViewModel;
    }


}