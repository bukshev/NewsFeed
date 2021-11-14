package ru.nsu.fit.mobdev.presentation.storagedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ru.nsu.fit.mobdev.data.storagedemo.IStorageDemoGateway;
import ru.nsu.fit.mobdev.data.storagedemo.StorageDemoFilesDataSource;
import ru.nsu.fit.mobdev.data.storagedemo.StorageDemoGateway;
import ru.nsu.fit.mobdev.data.storagedemo.StorageDemoSQLiteDataSource;
import ru.nsu.fit.mobdev.data.storagedemo.StorageDemoSharedPreferencesDataSource;
import ru.nsu.fit.mobdev.domain.storagedemo.StorageDemoDeleteUseCase;
import ru.nsu.fit.mobdev.domain.storagedemo.StorageDemoLoadUseCase;
import ru.nsu.fit.mobdev.domain.storagedemo.StorageDemoSaveUseCase;
import ru.nsu.fit.mobdev.model.Article;
import ru.nsu.fit.mobdev.R;

public final class StorageDemoActivity extends AppCompatActivity implements StorageDemoModuleContract.View {

    public static Intent getIntent(final Context context) {
        return new Intent(context, StorageDemoActivity.class);
    }

    private TextView amountTextView;
    private TextView lastElementTextView;
    private EditText titleEditText;

    private StorageDemoModuleContract.Presenter presenter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_demo);

        amountTextView = findViewById(R.id.amount_textView);
        lastElementTextView = findViewById(R.id.last_element_textView);
        titleEditText = findViewById(R.id.title_editText);

        final Button saveButton = findViewById(R.id.save_button);
        final Button loadButton = findViewById(R.id.load_button);
        final Button deleteButton = findViewById(R.id.delete_button);

        saveButton.setOnClickListener(view -> presenter.didTapSaveButton(titleEditText.getText().toString()));
        loadButton.setOnClickListener(view -> presenter.didTapLoadButton());
        deleteButton.setOnClickListener(view -> presenter.didTapDeleteButton());

        // Временно (!) инициализируем здесь весь стек в Activity.
        final IStorageDemoGateway storageDemoGateway = new StorageDemoGateway(
                new StorageDemoFilesDataSource(getApplicationContext()),
                new StorageDemoSharedPreferencesDataSource(getApplicationContext()),
                new StorageDemoSQLiteDataSource()
        );
        final StorageDemoSaveUseCase saveUseCase = new StorageDemoSaveUseCase(storageDemoGateway);
        final StorageDemoLoadUseCase loadUseCase = new StorageDemoLoadUseCase(storageDemoGateway);
        final StorageDemoDeleteUseCase deleteUseCase = new StorageDemoDeleteUseCase(storageDemoGateway);
        StorageDemoModuleContract.Presenter presenter = new StorageDemoPresenter(this, saveUseCase, loadUseCase, deleteUseCase);
    }

    @Override
    public void linkPresenter(final StorageDemoModuleContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void displayArticles(final List<Article> articles) {
        amountTextView.setText("Общее количество записей: " + articles.size());
        if (articles.size() > 0) {
            lastElementTextView.setText("Последний элемент: " + articles.get(articles.size() - 1).getTitle());
        } else {
            lastElementTextView.setText("%нет_значения%");
        }
    }

    @Override
    public void displayMessage(final String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}