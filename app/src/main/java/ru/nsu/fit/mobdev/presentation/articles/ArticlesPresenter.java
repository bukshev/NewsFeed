package ru.nsu.fit.mobdev.presentation.articles;

import java.util.List;

import ru.nsu.fit.mobdev.model.Article;
import ru.nsu.fit.mobdev.domain.articles.LoadArticlesUseCase;
import ru.nsu.fit.mobdev.domain.UseCase;

public class ArticlesPresenter implements ArticlesModuleContract.Presenter {

    private ArticlesModuleContract.View view;
    private final LoadArticlesUseCase loadArticlesUseCase;

    public ArticlesPresenter(final ArticlesModuleContract.View view, final LoadArticlesUseCase loadArticlesUseCase) {
        this.view = view;
        this.loadArticlesUseCase = loadArticlesUseCase;
        view.linkPresenter(this);
    }

    @Override
    public void onCreate() {
        loadArticlesUseCase.setRequestParameters(new LoadArticlesUseCase.RequestValues());
        loadArticlesUseCase.setCallback(new UseCase.IUseCaseCallback<LoadArticlesUseCase.ResponseValues>() {
            @Override
            public void onSuccess(LoadArticlesUseCase.ResponseValues response) {
                final List<Article> articles = response.getArticles();
                view.displayArticles(articles);
            }

            @Override
            public void onFailure() {
                // TODO: Process error.
            }
        });
        loadArticlesUseCase.run();
    }
}
