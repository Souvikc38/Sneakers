package com.example.sneakers.views;

import static com.example.sneakers.utils.Constants.SELECTED_SHOES;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sneakers.MainActivity;
import com.example.sneakers.R;
import com.example.sneakers.adapter.ShoesListAdapter;
import com.example.sneakers.databinding.FragmentShoesInventoryBinding;
import com.example.sneakers.model.ShoesModel;
import com.example.sneakers.model.ShoesViewModel;

public class ShoesInventoryFragment extends Fragment implements ShoesListAdapter.ProductClickListner {
    private static final String TAG = "ShoesInventoryFragment";
    private FragmentShoesInventoryBinding binding;
    private ShoesListAdapter shoesListAdapter;
    private ShoesViewModel shoesViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoes_inventory, container, false);
        ((MainActivity) getActivity()).showToolbar();
        binding.rvProductRecycler.setLayoutManager(new GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false));
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        shoesViewModel = ((MainActivity) requireActivity()).getShoesViewModel();
        shoesViewModel.getShoesListLiveData(getResources()).observe(getViewLifecycleOwner(), shoesList -> {
            shoesListAdapter = new ShoesListAdapter(requireContext(), shoesList, this);
            binding.rvProductRecycler.setAdapter(shoesListAdapter);
        });
        binding.fabButton.setOnClickListener(views -> {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,
                    new CartFragment(), CartFragment.class.getName()).addToBackStack(null).commit();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (shoesViewModel.getItemList() != null) {
            if(shoesViewModel.itemCount()!=0)
            binding.tvCount.setText(String.valueOf(shoesViewModel.itemCount()));
        }
    }


    @Override
    public void onProductAddClick(String selectedItemId, ShoesModel selectedShoes) {
        shoesViewModel.addItem(selectedItemId, selectedShoes);
        if(shoesViewModel.itemCount()!=0)
        binding.tvCount.setText(String.valueOf(shoesViewModel.itemCount()));
    }

    @Override
    public void onProductItemClick(ShoesModel shoesModel) {
        Bundle bundle= new Bundle();
        bundle.putSerializable(SELECTED_SHOES,shoesModel);
        Fragment shoesDetailsFragment = new ShoesDetailsFragment();
        shoesDetailsFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,
                shoesDetailsFragment,ShoesDetailsFragment.class.getName()).addToBackStack(null).commit();
    }
}