package ru.nsu.fit.mobdev.data.articles;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import ru.nsu.fit.mobdev.model.Article;

/**
 * Реализация Gateway с замоканными данными
 * — отлично подходит для локального тестирования во время разработки.
 */
public final class ArticlesMockDataSource implements IArticlesDataSource {

    @Override
    public void loadArticles(final ILoadArticlesCallback callback) {
        final List<Article> articles = new ArrayList<>();

        articles.add(new Article(UUID.randomUUID(), "1", new Date(), "2"));
        articles.add(new Article(UUID.randomUUID(), "3", new Date(), "4"));

        // Считаем, что успешно загрузили статьи.
        callback.didLoad(articles);
    }
}
