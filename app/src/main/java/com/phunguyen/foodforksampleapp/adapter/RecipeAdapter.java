package com.phunguyen.foodforksampleapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.phunguyen.foodforksampleapp.model.Recipe;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder>{

    private List<Recipe> items;
    private RecipeAdapterListener listener;

    public RecipeAdapter(List<Recipe> items, RecipeAdapterListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem_recipe, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       holder.bind(items.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    List<Recipe> getItems(){
        return items;
    }

    void removeItem(Recipe item){
        items.remove(item);
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private View itemView;
        ImageView recipeImage;
        TextView recipeTitle;
        ImageView fvImage;

        ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            recipeImage = itemView.findViewById(R.id.imageView);
            recipeTitle = itemView.findViewById(R.id.title);
            fvImage = itemView.findViewById(R.id.favButton);
        }

        void bind(final Recipe recipe,final RecipeAdapterListener listener){
            Glide.with(itemView).load(recipe.getImageUrl()).into(recipeImage);
            recipeTitle.setText(recipe.getTitle());
            if (recipe.isFavorited()) {
                fvImage.setImageResource(R.drawable.ic_favorite_24dp);
            } else {
                fvImage.setImageResource(R.drawable.ic_favorite_border_24dp);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClickItem(recipe);
                }
            });

            fvImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recipe.isFavorited()) {
                        listener.onRemoveFavorite(recipe);
                    } else {
                        listener.onAddFavorite(recipe);
                    }
                }
            });
        }

    }

    public interface RecipeAdapterListener {
        void onClickItem(Recipe item);
        void onAddFavorite(Recipe item);
        void onRemoveFavorite(Recipe item);
    }

}
