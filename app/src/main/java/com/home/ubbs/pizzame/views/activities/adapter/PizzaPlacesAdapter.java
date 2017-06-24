package com.home.ubbs.pizzame.views.activities.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.home.ubbs.pizzame.R;
import com.home.ubbs.pizzame.databinding.ItemPizzaPlacesBinding;
import com.home.ubbs.pizzame.model.Result;
import com.home.ubbs.pizzame.viewmodel.ItemPizzaPlacesViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by udyatbhanu-mac on 6/19/17.
 */

public class PizzaPlacesAdapter extends RecyclerView.Adapter<PizzaPlacesAdapter.PizzaPlacesAdapterViewHolder> {

    private List<Result> results = new ArrayList<>();

    public PizzaPlacesAdapter(List<Result> results) {
        this.results = results;
    }

    @Override
    public PizzaPlacesAdapter.PizzaPlacesAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemPizzaPlacesBinding itemPizzaPlacesBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_pizza_places,
                        parent, false);
        return new PizzaPlacesAdapterViewHolder(itemPizzaPlacesBinding);
    }

    @Override
    public void onBindViewHolder(PizzaPlacesAdapter.PizzaPlacesAdapterViewHolder holder, int position) {
        holder.bindResults(results.get(position));
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public static class PizzaPlacesAdapterViewHolder extends RecyclerView.ViewHolder {
        ItemPizzaPlacesBinding itemPizzaPlacesBinding;

        public PizzaPlacesAdapterViewHolder(ItemPizzaPlacesBinding itemPizzaPlacesBinding) {
            super(itemPizzaPlacesBinding.cardView);
            this.itemPizzaPlacesBinding = itemPizzaPlacesBinding;

        }

        void bindResults(Result result) {
            if (itemPizzaPlacesBinding.getItemPizzaPlacesViewModel() == null) {
                ItemPizzaPlacesViewModel viewModel = new ItemPizzaPlacesViewModel(result, itemView.getContext());
                itemPizzaPlacesBinding.setItemPizzaPlacesViewModel(viewModel);
            } else {
                itemPizzaPlacesBinding.getItemPizzaPlacesViewModel().setResult(result);
            }
        }

    }
}
