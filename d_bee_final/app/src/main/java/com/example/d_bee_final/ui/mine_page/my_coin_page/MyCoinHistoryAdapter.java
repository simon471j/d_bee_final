package com.example.d_bee_final.ui.mine_page.my_coin_page;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.d_bee_final.R;
import com.example.d_bee_final.databinding.CoinHistoryItemBinding;
import com.example.d_bee_final.logic.bean.ReceiveCoinBean;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyCoinHistoryAdapter extends RecyclerView.Adapter<MyCoinHistoryAdapter.BindingHolder> {
    List<ReceiveCoinBean.Data.Datas> receiveCoinBeanList;

    public MyCoinHistoryAdapter(List<ReceiveCoinBean.Data.Datas> receiveCoinBeanList) {
        this.receiveCoinBeanList = receiveCoinBeanList;
    }

    @NonNull
    @NotNull
    @Override
    public BindingHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        CoinHistoryItemBinding coinHistoryItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.coin_history_item, parent, false);
        return new BindingHolder(coinHistoryItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyCoinHistoryAdapter.BindingHolder holder, int position) {
        holder.coinHistoryItemBinding.setReceiveHistory(receiveCoinBeanList.get(position));
        holder.coinHistoryItemBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        if (receiveCoinBeanList == null)
            return 0;
        else
            return receiveCoinBeanList.size();
    }

    public class BindingHolder extends RecyclerView.ViewHolder {
        private final CoinHistoryItemBinding coinHistoryItemBinding;

        public BindingHolder(@NonNull @NotNull CoinHistoryItemBinding coinHistoryItemBinding) {
            super(coinHistoryItemBinding.getRoot());
            this.coinHistoryItemBinding = coinHistoryItemBinding;
        }
    }

    public void setReceiveCoinBeanList(List<ReceiveCoinBean.Data.Datas> receiveCoinBeanList) {
        this.receiveCoinBeanList = receiveCoinBeanList;
    }
}
