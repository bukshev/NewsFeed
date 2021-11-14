package ru.nsu.fit.mobdev.domain.articles;

import java.util.List;

import ru.nsu.fit.mobdev.domain.UseCase;
import ru.nsu.fit.mobdev.model.Article;
import ru.nsu.fit.mobdev.data.articles.IArticlesGateway;

/**
 * Инкапсулированная бизнес-логика для задачи "Загрузка всех новостных статей".
 */
public final class LoadArticlesUseCase extends UseCase<LoadArticlesUseCase.RequestValues, LoadArticlesUseCase.ResponseValues> {

    private final IArticlesGateway articlesGateway;

    public LoadArticlesUseCase(final IArticlesGateway articlesGateway) {
        this.articlesGateway = articlesGateway;
    }

    @Override
    protected void execute(final RequestValues requestParameters) {
        articlesGateway.loadArticles(false, new IArticlesGateway.ILoadArticlesCallback() {
            @Override
            public void didLoad(final List<Article> articles) {
                // Если с нижних уровней вызвался данный метод, значит удалось успешно получить статьи.
                // Конвертируем их в ResponseValues и отправляем в callback, который был сформирован Presenter'ом.
                final ResponseValues responseValues = new ResponseValues(articles);
                getCallback().onSuccess(responseValues);
            }

            @Override
            public void didFailLoad() {
                // На уровне Gateway/Repository произошла ошибка во время загрузки/получения articles.
                getCallback().onFailure();
            }
        });
    }

    public static final class RequestValues {
        // Входные данные на данном этапе не нужны.
        // Но для реализации UseCase'а нужна хотя бы пустая реализация.
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
