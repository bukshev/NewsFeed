package ru.nsu.fit.mobdev.presentation.articles;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;

import ru.nsu.fit.mobdev.model.Article;
import ru.nsu.fit.mobdev.R;

public final class ArticlesListItemViewHolder extends RecyclerView.ViewHolder {

    private TextView titleTextView;
    private TextView contentTextView;
    private TextView dateTextView;

    private Article item;

    public ArticlesListItemViewHolder(@NonNull final View itemView) {
        super(itemView);
        titleTextView = itemView.findViewById(R.id.title_text_view);
        contentTextView = itemView.findViewById(R.id.content_text_view);
        dateTextView = itemView.findViewById(R.id.date_text_view);
    }

    void onBind(final Article item) {
        this.item = item;

        titleTextView.setText(item.getTitle());
        contentTextView.setText(item.getContent());
        dateTextView.setText(getDateString(item.getCreatedDate()));
    }

    private String getDateString(final Date date) {
        return SimpleDateFormat.getInstance().format(date);
    }
}
