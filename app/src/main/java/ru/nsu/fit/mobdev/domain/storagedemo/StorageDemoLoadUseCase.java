package ru.nsu.fit.mobdev.domain.storagedemo;

import java.util.List;

import ru.nsu.fit.mobdev.data.storagedemo.IStorageDemoGateway;
import ru.nsu.fit.mobdev.domain.UseCase;
import ru.nsu.fit.mobdev.model.Article;
import ru.nsu.fit.mobdev.presentation.storagedemo.StorageKind;

public final class StorageDemoLoadUseCase extends UseCase<StorageDemoLoadUseCase.RequestValues, StorageDemoLoadUseCase.ResponseValues> {

    private final IStorageDemoGateway storageDemoGateway;

    public StorageDemoLoadUseCase(final IStorageDemoGateway storageDemoGateway) {
        this.storageDemoGateway = storageDemoGateway;
    }

    @Override
    protected void execute(final RequestValues parameters) {
        storageDemoGateway.loadAllArticles(parameters.storageKind, new IStorageDemoGateway.ILoadAllArticlesCallback() {
            @Override
            public void didLoadAllArticles(final List<Article> articles) {
                getCallback().onSuccess(new ResponseValues(articles));
            }

            @Override
            public void didFailLoadAllArticles() {
                getCallback().onFailure();
            }
        });
    }

    public static final class RequestValues {
        private final StorageKind storageKind;

        public RequestValues(final StorageKind storageKind) {
            this.storageKind = storageKind;
        }
    }

    public static final class ResponseValues {
        private final List<Article> articles;

        public ResponseValues(final List<Article> articles) {
            this.articles = articles;
        }

        public List<Article> getArticles() {
            return articles;
        }
    }
}
