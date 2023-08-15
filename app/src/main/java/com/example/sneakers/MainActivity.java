package com.example.sneakers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.example.sneakers.databinding.ActivityMainBinding;
import com.example.sneakers.model.ShoesViewModel;
import com.example.sneakers.views.ShoesInventoryFragment;


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
        getSupportFragmentManager().beginTransaction().add(R.id.container, shoesInventoryFragment,
                shoesInventoryFragment.getClass().getName()).commit();
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
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.serach_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });

        return true;
    }*/


    /*
    Except Home Fragment Display back button on other fragments.
    * */
    public void showToolbar() {
        if (binding.toolbar != null) {

            Fragment homeFragment = getSupportFragmentManager().findFragmentById(R.id.container);
            if (homeFragment instanceof ShoesInventoryFragment) {
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