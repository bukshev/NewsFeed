package ru.nsu.fit.mobdev.domain.storagedemo;

import java.util.Date;
import java.util.UUID;

import ru.nsu.fit.mobdev.data.storagedemo.IStorageDemoGateway;
import ru.nsu.fit.mobdev.domain.UseCase;
import ru.nsu.fit.mobdev.model.Article;
import ru.nsu.fit.mobdev.presentation.storagedemo.StorageKind;

public final class StorageDemoSaveUseCase extends UseCase<StorageDemoSaveUseCase.RequestValues, StorageDemoSaveUseCase.ResponseValues> {

    private final IStorageDemoGateway storageDemoGateway;

    public StorageDemoSaveUseCase(final IStorageDemoGateway storageDemoGateway) {
        this.storageDemoGateway = storageDemoGateway;
    }

    @Override
    protected void execute(final RequestValues parameters) {
        final Article article = new Article(UUID.randomUUID(), parameters.articleTitle, new Date(), "");
        storageDemoGateway.saveArticle(parameters.storageKind, article, new IStorageDemoGateway.ISaveArticleCallback() {
            @Override
            public void didSaveArticle() {
                getCallback().onSuccess(new ResponseValues());
            }

            @Override
            public void didFailSaveArticle() {
                getCallback().onFailure();
            }
        });
    }

    public static final class RequestValues {
        private final StorageKind storageKind;
        private final String articleTitle;

        public RequestValues(final StorageKind storageKind, final String articleTitle) {
            this.storageKind = storageKind;
            this.articleTitle = articleTitle;
        }
    }

    public static final class ResponseValues {
        // Nothing.
    }
}
