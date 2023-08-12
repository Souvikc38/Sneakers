package com.example.sneakers.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sneakers.R;
import com.example.sneakers.databinding.ShoesListItemBinding;
import com.example.sneakers.model.ShoesModel;
import com.example.sneakers.model.ShoesViewModel;

import java.util.ArrayList;
import java.util.List;

public class ShoesListAdapter extends RecyclerView.Adapter<ShoesListAdapter.ShoesViewHolder> {
    private static final String TAG = "ShoesListAdapter";
private ShoesListItemBinding viewBinding;
private List<ShoesModel> shoesModelList;
private List<ShoesModel> shoesSelectedList= new ArrayList<>();
private ProductClickListner productClickListner;
private Context mContext;

    public ShoesListAdapter(Context mContext, @NonNull List<ShoesModel> shoesModelList, ProductClickListner productClickListner) {
       this.mContext=mContext;
       this.shoesModelList=shoesModelList;
       this.productClickListner = productClickListner;
    }

    @NonNull
    @Override
    public ShoesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.shoes_list_item,parent,false);
        return  new ShoesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoesViewHolder holder, int position) {
        final ShoesModel shoesModel = this.shoesModelList.get(position);
        viewBinding.tvName.setText(shoesModel.getName());
        Log.e(TAG, "onBindViewHolder: "+shoesModel.getMedia().getImageurl());
        Glide.with(mContext).load(shoesModel.getMedia().getImageurl()).into(viewBinding.ivProductImg);

    }

    @Override
    public int getItemCount() {
        return shoesModelList.size();
    }

    class ShoesViewHolder extends RecyclerView.ViewHolder{

        public ShoesViewHolder(@NonNull View itemView) {
            super(itemView);
            viewBinding= DataBindingUtil.bind(itemView);
        }
    }
    public interface ProductClickListner{
        void onProductClick(ArrayList<ShoesModel> shoesModelArrayList);
    }
}