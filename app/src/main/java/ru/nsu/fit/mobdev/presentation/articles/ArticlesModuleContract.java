package ru.nsu.fit.mobdev.presentation.articles;

import java.util.List;

import ru.nsu.fit.mobdev.model.Article;
import ru.nsu.fit.mobdev.presentation.IBasePresenter;
import ru.nsu.fit.mobdev.presentation.IBaseView;

public interface ArticlesModuleContract {

    interface View extends IBaseView<Presenter> {

        void displayArticles(final List<Article> articles);
    }

    interface Presenter extends IBasePresenter {

        void onCreate();
    }
}
