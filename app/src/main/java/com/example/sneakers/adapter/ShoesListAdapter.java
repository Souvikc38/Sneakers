package com.example.sneakers.adapter;

import android.content.Context;
import android.text.SpannableStringBuilder;
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
import java.util.List;

public class ShoesListAdapter extends RecyclerView.Adapter<ShoesListAdapter.ShoesViewHolder> {
    private static final String TAG = "ShoesListAdapter";
private ShoesListItemBinding viewBinding;
private List<ShoesModel> shoesModelList;
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
         ShoesModel shoesModels = shoesModelList.get(position);
        Glide.with(mContext).load(shoesModels.getMedia().getImageurl()).into(viewBinding.ivProductImg);
        viewBinding.tvName.setText(shoesModels.getName());
        viewBinding.tvPrice.setText(appendString(String.valueOf(shoesModels.getRetailprice())));
        viewBinding.ivAddIcon.setOnClickListener(view -> productClickListner.
                onProductAddClick(shoesModels.getId(),shoesModels));
        viewBinding.cardView.setOnClickListener(view -> productClickListner.onProductItemClick(shoesModels));

    }

    @Override
    public int getItemCount() {
        return shoesModelList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ShoesViewHolder extends RecyclerView.ViewHolder{

        public ShoesViewHolder(@NonNull View itemView) {
            super(itemView);
            viewBinding= DataBindingUtil.bind(itemView);
        }
    }
    public String appendString( String value){
        SpannableStringBuilder message =new SpannableStringBuilder("$");
        message.append(value);
        return message.toString();
    }
    public interface ProductClickListner{
        void onProductAddClick(String itemId, ShoesModel shoesModel);
        void onProductItemClick( ShoesModel shoesModel);
    }
}
