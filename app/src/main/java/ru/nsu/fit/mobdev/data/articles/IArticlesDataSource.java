package ru.nsu.fit.mobdev.data.articles;

import java.util.List;

import ru.nsu.fit.mobdev.model.Article;

public interface IArticlesDataSource {

    /**
     * Тут уже нет никаких дополнительных флагов (по типу shouldUseCache, shouldForceUpdate, etc).
     * Просто команда на загрузку данных.
     */
    void loadArticles(final ILoadArticlesCallback callback);

    /**
     * Контракт может отличаться, но в нашем случае — он такой же, как и в Gateway.
     * Примеры, когда интерфейс может отличаться от Gateway:
     *   1. Можно сделать свой тип ошибки на уровне DataSource и на уровне Gateway.
     *   2. один из DataSource возвразает данные, которые необходимы другому DataSource.
     */
    interface ILoadArticlesCallback {
        void didLoad(final List<Article> articles);
        void didFailLoad();
    }
}
