package com.example.newcocktails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

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
        Cocktail currentCocktail = cocktails.get(position);

        String image = currentCocktail.getImageUrl();
        String name = currentCocktail.getName();
        String category = currentCocktail.getCategory();
//        String instructions = currentCocktail.getInstructions();

        holder.nameTextView.setText(name);
        holder.categoryTextView.setText(category);

        Picasso.get().load(image).fit().centerCrop().into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return cocktails.size();
    }

    public class CocktailViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView nameTextView, categoryTextView;

        public CocktailViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            nameTextView = itemView.findViewById(R.id.nameView);
            categoryTextView = itemView.findViewById(R.id.categoryView);

        }
    }
}
