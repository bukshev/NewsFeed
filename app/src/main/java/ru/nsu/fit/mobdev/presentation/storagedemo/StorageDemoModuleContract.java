package ru.nsu.fit.mobdev.presentation.storagedemo;

import java.util.List;

import ru.nsu.fit.mobdev.model.Article;
import ru.nsu.fit.mobdev.presentation.IBasePresenter;
import ru.nsu.fit.mobdev.presentation.IBaseView;

public interface StorageDemoModuleContract {

    interface View extends IBaseView<StorageDemoModuleContract.Presenter> {
        void displayArticles(final List<Article> articles);
        void displayMessage(final String message);
    }

    interface Presenter extends IBasePresenter {
        void didTapSaveButton(final String title);
        void didTapLoadButton();
        void didTapDeleteButton();
    }
}
