package ru.nsu.fit.mobdev.data.articles;

import java.util.List;

import ru.nsu.fit.mobdev.model.Article;

/**
 * Общий протокол, сккрывающий от UseCase'а
 * конкретную реализацию логики работы с новостными статьями.
 */
public interface IArticlesGateway {
    /**
     * Команда для старта загрузки статей.
     * @param callback Замыкание, в котором мы указываем как поступать,
     *                когда статьи загрузятся или же мы получим ошибку.
     */
    void loadArticles(final boolean shouldUseCache, final ILoadArticlesCallback callback);

    /**
     * Контракт для Gateway, в котором указано: как и что можно вернуть обратно в UseCase.
     */
    interface ILoadArticlesCallback {
        /**
         * В случае успеха нужно вызвать данный метод с загруженными статьями.
         * @param articles Список загруженных статей.
         */
        void didLoad(final List<Article> articles);

        /**
         * В случае ошибки нужно вызвать данный метод.
         * По-хорошему, сюда нужно передать конкретную ошибку, чтобы в дальнейшем решить,
         * какое именно информационное сообщение необходимо показать пользователю.
         */
        void didFailLoad();
    }
}
