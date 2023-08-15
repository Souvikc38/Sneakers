package com.example.sneakers.views;

import static com.example.sneakers.utils.Constants.SELECTED_SHOES;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.sneakers.MainActivity;
import com.example.sneakers.R;
import com.example.sneakers.databinding.FragmentShoesDetailsBinding;
import com.example.sneakers.model.ShoesModel;
import com.example.sneakers.model.ShoesViewModel;


public class ShoesDetailsFragment extends Fragment {

private FragmentShoesDetailsBinding binding;
private ShoesModel shoesModel;

   public static ShoesDetailsFragment getInstance(Bundle bundle){
       ShoesDetailsFragment fragment= new ShoesDetailsFragment();
       fragment.setArguments(bundle);
       return fragment;
   }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() == null){
            return;
        }
        shoesModel= (ShoesModel) getArguments().getSerializable(SELECTED_SHOES);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_shoes_details, container, false);
        ((MainActivity) getActivity()).showToolbar();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Glide.with(requireContext()).load(shoesModel.getMedia().getImageurl()).into(binding.ivProductImg);
        binding.tvName.setText(shoesModel.getName());
        binding.tvPrice.setText(appendString("$","",String.valueOf(shoesModel.getRetailprice())));
        binding.tvBrand.setText(appendString(getResources().getString(R.string.brand)," : ",
                shoesModel.getBrand()));
        binding.tvGender.setText(appendString(getResources().getString(R.string.gender)," : ",
                shoesModel.getGender()));
        binding.tvReleaseDate.setText(appendString(getResources().getString(R.string.year_of_release)," : ",
                shoesModel.getReleasedate()));

        binding.bvCheckout.setOnClickListener(v ->{showChildFragment();} );
    }
    private void showChildFragment(){
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,
                new CartFragment(), CartFragment.class.getName()).addToBackStack(null).commit();
    }

    public String appendString(String key,String middleValue, String value){
        SpannableStringBuilder message =new SpannableStringBuilder(key);
        if(!middleValue.isEmpty()) {
            message.append(middleValue);
        }
        message.append(value);
        return message.toString();
    }
}