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
import com.example.sneakers.databinding.CartListItemBinding;
import com.example.sneakers.model.ShoesModel;
import java.util.List;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.CartViewHolder>{
    private CartListItemBinding viewBinding;
    private Context mContext;
    private List<ShoesModel> cartItemList;
    private CartClickListner onRemoveClick;


    public CartListAdapter(Context mContext, List<ShoesModel> cartItemList, CartClickListner onRemoveClick) {
        this.mContext=mContext;
        this.cartItemList= cartItemList;
        this.onRemoveClick=onRemoveClick;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list_item,parent,false);
        return  new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartViewHolder holder, final int position) {
        final ShoesModel shoesModel= cartItemList.get(position);
        Glide.with(mContext).load(shoesModel.getMedia().getImageurl()).into(viewBinding.ivProductImg);
        viewBinding.tvName.setText(shoesModel.getName());
        viewBinding.tvPrice.setText(appendString(String.valueOf(shoesModel.getRetailprice())));
        viewBinding.ivRemoveIcon.setOnClickListener(view -> {
            onRemoveClick.onItemRemoveClick(shoesModel.getId(),position,shoesModel);
        });

    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    class CartViewHolder extends RecyclerView.ViewHolder{

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            viewBinding= DataBindingUtil.bind(itemView);
        }
    }
    public String appendString( String value){
        SpannableStringBuilder message =new SpannableStringBuilder("$");
        message.append(value);
        return message.toString();
    }
    public interface CartClickListner{
        void onItemRemoveClick(String itemId,int position,ShoesModel shoesModel);
    }
}
