package ru.nsu.fit.mobdev.data.storagedemo;

import androidx.annotation.Nullable;

import java.util.List;

import ru.nsu.fit.mobdev.model.Article;
import ru.nsu.fit.mobdev.presentation.storagedemo.StorageKind;

public final class StorageDemoGateway implements IStorageDemoGateway {

    private final IStorageDemoDataSource filesDataSource;
    private final IStorageDemoDataSource sharedPreferencesDataSource;
    private final IStorageDemoDataSource sqliteDataSource;

    public StorageDemoGateway(
            final IStorageDemoDataSource filesDataSource,
            final IStorageDemoDataSource sharedPreferencesDataSource,
            final IStorageDemoDataSource sqliteDataSource
    ) {
        this.filesDataSource = filesDataSource;
        this.sharedPreferencesDataSource = sharedPreferencesDataSource;
        this.sqliteDataSource = sqliteDataSource;
    }

    @Override
    public void saveArticle(final StorageKind storageKind, final Article article, final ISaveArticleCallback callback) {
        final IStorageDemoDataSource dataSource = getDataSource(storageKind);
        if (null == dataSource) {
            callback.didFailSaveArticle();
            return;
        }
        dataSource.saveArticle(article, new IStorageDemoDataSource.ISaveArticleCallback() {
            @Override
            public void didSaveArticle() {
                callback.didSaveArticle();
            }

            @Override
            public void didFailSaveArticle() {
                callback.didFailSaveArticle();
            }
        });
    }

    @Override
    public void loadAllArticles(final StorageKind storageKind, final ILoadAllArticlesCallback callback) {
        final IStorageDemoDataSource dataSource = getDataSource(storageKind);
        if (null == dataSource) {
            callback.didFailLoadAllArticles();
            return;
        }
        dataSource.loadAllArticles(new IStorageDemoDataSource.ILoadAllArticlesCallback() {
            @Override
            public void didLoadAllArticles(final List<Article> articles) {
                callback.didLoadAllArticles(articles);
            }

            @Override
            public void didFailLoadAllArticles() {
                callback.didFailLoadAllArticles();
            }
        });
    }

    @Override
    public void deleteAllArticles(final StorageKind storageKind, final IDeleteAllArticlesCallback callback) {
        final IStorageDemoDataSource dataSource = getDataSource(storageKind);
        if (null == dataSource) {
            callback.didFailDeleteAllArticles();
            return;
        }
        dataSource.deleteAllArticles(new IStorageDemoDataSource.IDeleteAllArticlesCallback() {
            @Override
            public void didDeleteAllArticles() {
                callback.didDeleteAllArticles();
            }

            @Override
            public void didFailDeleteAllArticles() {
                callback.didFailDeleteAllArticles();
            }
        });
    }

    @Nullable
    private IStorageDemoDataSource getDataSource(final StorageKind storageKind) {
        switch (storageKind) {
            case STORAGE_KIND_FILE:
                return filesDataSource;
            case STORAGE_KIND_SHARED_PREFERENCES:
                return sharedPreferencesDataSource;
            case STORAGE_KIND_SQLITE:
                return sqliteDataSource;
            default:
                return null;
        }
    }
}
