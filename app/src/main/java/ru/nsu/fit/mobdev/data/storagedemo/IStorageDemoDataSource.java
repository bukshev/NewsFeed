package ru.nsu.fit.mobdev.data.storagedemo;

import java.util.List;

import ru.nsu.fit.mobdev.model.Article;
import ru.nsu.fit.mobdev.presentation.storagedemo.StorageKind;

public interface IStorageDemoDataSource {

    void saveArticle(final Article article, final ISaveArticleCallback callback);
    void loadAllArticles(final ILoadAllArticlesCallback callback);
    void deleteAllArticles(final IDeleteAllArticlesCallback callback);

    interface ISaveArticleCallback {
        void didSaveArticle();
        void didFailSaveArticle();
    }

    interface ILoadAllArticlesCallback {
        void didLoadAllArticles(final List<Article> articles);
        void didFailLoadAllArticles();
    }

    interface IDeleteAllArticlesCallback {
        void didDeleteAllArticles();
        void didFailDeleteAllArticles();
    }
}
