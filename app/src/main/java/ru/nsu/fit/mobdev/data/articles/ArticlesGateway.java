package ru.nsu.fit.mobdev.data.articles;

import java.util.List;

import ru.nsu.fit.mobdev.model.Article;

/**
 * Реализация Gateway'я.
 * Содержит в себе всевозможные dataSource'ы и логику работы с/между ними.
 */
public final class ArticlesGateway implements IArticlesGateway {

    private final IArticlesDataSource mockDataSource;

    public ArticlesGateway(final IArticlesDataSource mockDataSource) {
        this.mockDataSource = mockDataSource;
    }

    @Override
    public void loadArticles(final boolean shouldUseCache, final ILoadArticlesCallback callback) {
        if (shouldUseCache) {
            // Пример: нужно обратиться в базу данных,
            // забрать закешированные новости и прокинуть их в callback.

            // Пока нет слоя с БД, будем возвращашть всегда ошибку.
            callback.didFailLoad();
        } else {
            // Пример: нужно обратиться в сеть,
            // загрузить новые новости и прокинуть их в callback.

            // Пока нет сетевого слоя, будем возвращать замокированные данные.
            mockDataSource.loadArticles(new IArticlesDataSource.ILoadArticlesCallback() {
                @Override
                public void didLoad(final List<Article> articles) {
                    callback.didLoad(articles);
                }

                @Override
                public void didFailLoad() {
                    callback.didFailLoad();
                }
            });
        }
    }
}
