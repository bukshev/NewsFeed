package ru.nsu.fit.mobdev.data.storagedemo;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import ru.nsu.fit.mobdev.model.Article;

public final class StorageDemoSharedPreferencesDataSource implements IStorageDemoDataSource {

    public static final String APP_PREFERENCES = "my_preferences";
    public static final String UUID_KEY = "uuid";
    public static final String SINGLE_TITLE_KEY = "single_title";
    public static final String ARRAY_TITLES_KEY = "array_titles";

    private SharedPreferences preferences;

    public StorageDemoSharedPreferencesDataSource(final Context context) {
        preferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    @Override
    public void saveArticle(final Article article, final ISaveArticleCallback callback) {
        final SharedPreferences.Editor editor = preferences.edit();

        // Не стоит хранить мутабельные коллекции данных в SharedPreferences. Это исключительно пример возможностей.
        final Set<String> articleTitles = preferences.getStringSet(ARRAY_TITLES_KEY, new HashSet<>());
        articleTitles.add(article.getTitle());

//        editor.putString(SINGLE_TITLE_KEY, article.getTitle());
        editor.putString(UUID_KEY, article.getUuid().toString());
        editor.putStringSet(ARRAY_TITLES_KEY, articleTitles);
        editor.apply();
    }

    @Override
    public void loadAllArticles(final ILoadAllArticlesCallback callback) {
//        final String title = preferences.getString(SINGLE_TITLE_KEY, "Default Title");
//        final String uuidString = preferences.getString(UUID_KEY, UUID.randomUUID().toString());
//        final List<Article> articles = new ArrayList<>();

        final Set<String> articleTitles = preferences.getStringSet(ARRAY_TITLES_KEY, new HashSet<>());
        final List<Article> articles = articleTitles.stream()
                .map(title -> new Article(UUID.randomUUID(), title, new Date(), ""))
                .collect(Collectors.toList());

        callback.didLoadAllArticles(articles);
    }

    @Override
    public void deleteAllArticles(final IDeleteAllArticlesCallback callback) {
        final SharedPreferences.Editor editor = preferences.edit();
        editor.remove(UUID_KEY);
        editor.remove(SINGLE_TITLE_KEY);
        editor.remove(ARRAY_TITLES_KEY);
        editor.apply();
    }
}
