package ru.nsu.fit.mobdev.presentation.storagedemo;

import ru.nsu.fit.mobdev.domain.UseCase;
import ru.nsu.fit.mobdev.domain.storagedemo.StorageDemoDeleteUseCase;
import ru.nsu.fit.mobdev.domain.storagedemo.StorageDemoLoadUseCase;
import ru.nsu.fit.mobdev.domain.storagedemo.StorageDemoSaveUseCase;

public final class StorageDemoPresenter implements StorageDemoModuleContract.Presenter {

    private static final StorageKind STORAGE_KIND = StorageKind.STORAGE_KIND_SHARED_PREFERENCES;

    private final StorageDemoModuleContract.View view;
    private final StorageDemoSaveUseCase saveUseCase;
    private final StorageDemoLoadUseCase loadUseCase;
    private final StorageDemoDeleteUseCase deleteUseCase;

    public StorageDemoPresenter(
            final StorageDemoModuleContract.View view,
            final StorageDemoSaveUseCase saveUseCase,
            final StorageDemoLoadUseCase loadUseCase,
            final StorageDemoDeleteUseCase deleteUseCase
    ) {
        this.view = view;
        this.saveUseCase = saveUseCase;
        this.loadUseCase = loadUseCase;
        this.deleteUseCase = deleteUseCase;
        view.linkPresenter(this);
    }

    @Override
    public void didTapSaveButton(final String title) {
        saveUseCase.setRequestParameters(new StorageDemoSaveUseCase.RequestValues(STORAGE_KIND, title));
        saveUseCase.setCallback(new UseCase.IUseCaseCallback<StorageDemoSaveUseCase.ResponseValues>() {
            @Override
            public void onSuccess(final StorageDemoSaveUseCase.ResponseValues successResponse) {
                view.displayMessage("Новая запись успешно сохранена в: " + STORAGE_KIND);
            }

            @Override
            public void onFailure() {
                view.displayMessage("Произошла ошибка сохранения в: " + STORAGE_KIND);
            }
        });
        saveUseCase.run();
    }

    @Override
    public void didTapLoadButton() {
        loadUseCase.setRequestParameters(new StorageDemoLoadUseCase.RequestValues(STORAGE_KIND));
        loadUseCase.setCallback(new UseCase.IUseCaseCallback<StorageDemoLoadUseCase.ResponseValues>() {
            @Override
            public void onSuccess(final StorageDemoLoadUseCase.ResponseValues successResponse) {
                view.displayArticles(successResponse.getArticles());
                view.displayMessage("Источник загруженных данных: " + STORAGE_KIND);
            }

            @Override
            public void onFailure() {
                view.displayMessage("Произошла ошибка загрузки из: " + STORAGE_KIND);
            }
        });
        loadUseCase.run();
    }

    @Override
    public void didTapDeleteButton() {
        deleteUseCase.setRequestParameters(new StorageDemoDeleteUseCase.RequestValues(STORAGE_KIND));
        deleteUseCase.setCallback(new UseCase.IUseCaseCallback<StorageDemoDeleteUseCase.ResponseValues>() {
            @Override
            public void onSuccess(final StorageDemoDeleteUseCase.ResponseValues successResponse) {
                view.displayMessage("Все данные успешно удалены из: " + STORAGE_KIND);
            }

            @Override
            public void onFailure() {
                view.displayMessage("Произошла удаления данных в: " + STORAGE_KIND);
            }
        });
        deleteUseCase.run();
    }
}
