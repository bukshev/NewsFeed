package ru.nsu.fit.mobdev.presentation.articles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.nsu.fit.mobdev.model.Article;
import ru.nsu.fit.mobdev.R;

public final class ArticlesListAdapter extends RecyclerView.Adapter<ArticlesListItemViewHolder> {

    // TODO: Create items
    private List<Article> items;

    public ArticlesListAdapter(final List<Article> items) {
        this.items = items;
    }

    public void replaceData(final List<Article> items) {
        if (null != items) {
            this.items = items;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ArticlesListItemViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final Context context = parent.getContext();

        final View view = LayoutInflater
                .from(context)
                .inflate(R.layout.item_note, parent, false);

        return new ArticlesListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ArticlesListItemViewHolder holder, final int position) {
        final Article item = items.get(position);
        holder.onBind(item);
    }

    @Override
    public int getItemCount() {
        return (null != items) ? items.size() : 0;
    }
}
