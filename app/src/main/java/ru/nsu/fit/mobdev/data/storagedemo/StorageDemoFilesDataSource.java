package ru.nsu.fit.mobdev.data.storagedemo;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import ru.nsu.fit.mobdev.model.Article;

public final class StorageDemoFilesDataSource implements IStorageDemoDataSource {

    private static final String LOG_TAG = StorageDemoFilesDataSource.class.getCanonicalName();
    private static final String FILE_NAME = "StorageDemoFilesDataSource";

    private final Context context;

    public StorageDemoFilesDataSource(final Context context) {
        this.context = context;
    }

    @Override
    public void saveArticle(final Article article, final ISaveArticleCallback callback) {
        try {
            Log.d(LOG_TAG, "1. Открываем поток для записи.");
            final OutputStream outputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            final BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            Log.d(LOG_TAG, "2. Пишем данные в файл.");
            bufferedWriter.write(article.getTitle());

            Log.d(LOG_TAG, "3. Закрывам поток записи.");
            bufferedWriter.close();

            callback.didSaveArticle();

        } catch (final IOException exception) {
            exception.printStackTrace();
            callback.didFailSaveArticle();
        }
    }

    @Override
    public void loadAllArticles(final ILoadAllArticlesCallback callback) {
        final StringBuilder content = new StringBuilder();

        try {
            Log.d(LOG_TAG, "1. Открываем поток для чтения.");
            final InputStream inputStream = context.openFileInput(FILE_NAME);
            final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            Log.d(LOG_TAG, "2. Читаем содержимое из файла.");
            String line = "";
            while (null != (line = bufferedReader.readLine())) {
                content.append(line);
                content.append('\n');
            }

            Log.d(LOG_TAG, "3. Закрывам поток чтения.");
            bufferedReader.close();

            final List<Article> articles = new ArrayList<>();
            articles.add(new Article(UUID.randomUUID(), content.toString(), new Date(), ""));

            callback.didLoadAllArticles(articles);

        } catch (final IOException exception) {
            exception.printStackTrace();
            callback.didFailLoadAllArticles();
        }
    }

    @Override
    public void deleteAllArticles(final IDeleteAllArticlesCallback callback) {

    }
}
