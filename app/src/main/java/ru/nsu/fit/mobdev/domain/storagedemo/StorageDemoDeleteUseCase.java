package ru.nsu.fit.mobdev.domain.storagedemo;

import ru.nsu.fit.mobdev.data.storagedemo.IStorageDemoGateway;
import ru.nsu.fit.mobdev.domain.UseCase;
import ru.nsu.fit.mobdev.presentation.storagedemo.StorageKind;

public final class StorageDemoDeleteUseCase extends UseCase<StorageDemoDeleteUseCase.RequestValues, StorageDemoDeleteUseCase.ResponseValues> {

    private final IStorageDemoGateway storageDemoGateway;

    public StorageDemoDeleteUseCase(final IStorageDemoGateway storageDemoGateway) {
        this.storageDemoGateway = storageDemoGateway;
    }

    @Override
    protected void execute(final RequestValues parameters) {
        storageDemoGateway.deleteAllArticles(parameters.storageKind, new IStorageDemoGateway.IDeleteAllArticlesCallback() {
            @Override
            public void didDeleteAllArticles() {
                getCallback().onSuccess(new ResponseValues());
            }

            @Override
            public void didFailDeleteAllArticles() {
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
        // Nothing.
    }
}
