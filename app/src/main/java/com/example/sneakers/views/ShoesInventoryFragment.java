package com.example.sneakers.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sneakers.R;
import com.example.sneakers.adapter.ShoesListAdapter;
import com.example.sneakers.databinding.FragmentShoesInventoryBinding;
import com.example.sneakers.model.ShoesModel;
import com.example.sneakers.model.ShoesViewModel;

import java.util.ArrayList;
import java.util.List;

public class ShoesInventoryFragment extends Fragment {
    private static final String TAG = "ShoesInventoryFragment";
    private FragmentShoesInventoryBinding binding;
    private ShoesListAdapter shoesListAdapter;
    private ShoesViewModel shoesViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_shoes_inventory,container,false);
        shoesViewModel = new ViewModelProvider(requireActivity()).get(ShoesViewModel.class);
        binding.rvProductRecycler.setLayoutManager(new GridLayoutManager(requireContext(),2,GridLayoutManager.VERTICAL,false));

        shoesViewModel.getShoesListLiveData(getResources()).observe(getViewLifecycleOwner(), shoesList -> {
            shoesListAdapter =new ShoesListAdapter(requireContext(),shoesList,this::onClickProduct);
            binding.rvProductRecycler.setAdapter(shoesListAdapter);
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.fabButton.setOnClickListener(views -> {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new CartFragment()).commit();
        });
    }

    private void onClickProduct(String productId, ShoesModel selectedShoes){
        shoesViewModel.addItem(productId,selectedShoes);
        binding.tvCount.setText(String.valueOf(shoesViewModel.itemCount()));

    }
}