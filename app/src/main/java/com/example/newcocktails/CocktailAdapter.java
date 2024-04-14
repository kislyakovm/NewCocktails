package com.example.newcocktails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CocktailAdapter extends RecyclerView.Adapter<CocktailAdapter.CocktailViewHolder> {
    Context context;
    private ArrayList<Cocktail> cocktails;

    public CocktailAdapter(Context context, ArrayList<Cocktail> cocktails) {
        this.context = context;
        this.cocktails = cocktails;
    }

    @NonNull
    @Override
    public CocktailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cocktail_item, parent, false);
        return new CocktailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CocktailViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return cocktails.size();
    }

    public class CocktailViewHolder extends RecyclerView.ViewHolder {
        public CocktailViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
