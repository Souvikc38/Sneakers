package com.example.sneakers.views;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.example.sneakers.MainActivity;
import com.example.sneakers.R;
import com.example.sneakers.adapter.CartListAdapter;
import com.example.sneakers.adapter.ShoesListAdapter;
import com.example.sneakers.databinding.FragmentCartBinding;
import com.example.sneakers.model.ShoesModel;
import com.example.sneakers.model.ShoesViewModel;

import java.util.ArrayList;
import java.util.List;


public class CartFragment extends Fragment {
    private static final String TAG = "CartFragment";
    private FragmentCartBinding binding;
    private CartListAdapter cartListAdapter;
    private ShoesViewModel shoesViewModel;
    private  List<ShoesModel> shoesModelList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_cart, container, false);
        shoesViewModel =((MainActivity)requireActivity()).getShoesViewModel();
        binding.rvCartRecycler.setLayoutManager(new GridLayoutManager(requireContext(),1,GridLayoutManager.VERTICAL,false));
        shoesModelList = new ArrayList<ShoesModel>(shoesViewModel.getItemList().values());
        cartListAdapter =new CartListAdapter(requireContext(),shoesModelList,this::onRemoveCart);
        binding.rvCartRecycler.setAdapter(cartListAdapter);
        isRecyclerReady();
        return binding.getRoot();
    }

    private void onRemoveCart(String productId, int position,ShoesModel shoesModel){
       shoesViewModel.deleteItem(productId,shoesModel);
       removeItem(position);

    }
    private void removeItem(int position) {
        if (position >= 0 && position < shoesModelList.size()) {
            shoesModelList.remove(position);
            cartListAdapter.notifyItemRemoved(position);
            setDetails();

        }
    }
    private void isRecyclerReady(){
        binding.rvCartRecycler.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                setDetails();
            }
        });
    }
    private void setDetails(){
        binding.tvSubtotal.setText(shoesViewModel.appendString(getResources().getString(R.string.sub_total),
                String.valueOf(shoesViewModel.getSubTotalPrice())));
        binding.tvTaxes.setText(shoesViewModel.appendString(getResources().getString(R.string.taxes_and_charges),
                String.valueOf(shoesViewModel.getTotaltax())));
        binding.tvTotalPrice.setText(shoesViewModel.appendString(getResources().getString(R.string.total),
                String.valueOf(shoesViewModel.getTotalPrice())));
    }


}