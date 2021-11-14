package ru.nsu.fit.mobdev.data.storagedemo;

import java.util.List;

import ru.nsu.fit.mobdev.model.Article;
import ru.nsu.fit.mobdev.presentation.storagedemo.StorageKind;

public interface IStorageDemoGateway {

    void saveArticle(final StorageKind storageKind, final Article article, final ISaveArticleCallback callback);
    void loadAllArticles(final StorageKind storageKind, final ILoadAllArticlesCallback callback);
    void deleteAllArticles(final StorageKind storageKind, final IDeleteAllArticlesCallback callback);

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
