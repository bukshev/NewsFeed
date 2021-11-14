package ru.nsu.fit.mobdev.presentation.articles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import ru.nsu.fit.mobdev.model.Article;
import ru.nsu.fit.mobdev.R;
import ru.nsu.fit.mobdev.data.articles.ArticlesMockDataSource;
import ru.nsu.fit.mobdev.data.articles.ArticlesGateway;
import ru.nsu.fit.mobdev.domain.articles.LoadArticlesUseCase;
import ru.nsu.fit.mobdev.presentation.storagedemo.StorageDemoActivity;

public final class ArticlesActivity extends AppCompatActivity implements ArticlesModuleContract.View {

    private static final String LOG_TAG = ArticlesActivity.class.getSimpleName();

    private ArticlesModuleContract.Presenter presenter;
    private ArticlesListAdapter listAdapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        final RecyclerView recyclerView = findViewById(R.id.recycler_view_articles);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new ArticlesListAdapter(new ArrayList<>());
        recyclerView.setAdapter(listAdapter);

        final Button storageDemoButton = findViewById(R.id.storage_demo_button);
        storageDemoButton.setOnClickListener(view -> {
            // Пока без всяких роутеров и т.п.
            final Intent intent = StorageDemoActivity.getIntent(getBaseContext());
            startActivity(intent);
        });

        // Временно (!) инициализируем здесь весь стек в Activity.

        final ArticlesMockDataSource dataSource = new ArticlesMockDataSource();
        final ArticlesGateway repository = new ArticlesGateway(dataSource);
        final LoadArticlesUseCase loadArticlesUseCase = new LoadArticlesUseCase(repository);
        ArticlesModuleContract.Presenter presenter = new ArticlesPresenter(this, loadArticlesUseCase);

        presenter.onCreate();
    }

    @Override
    public void linkPresenter(final ArticlesModuleContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void displayArticles(final List<Article> articles) {
        Log.d(LOG_TAG, String.valueOf(articles));
        listAdapter.replaceData(articles);
    }
}