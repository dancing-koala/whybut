package com.dancing_koala.whathaveyoubeenupto.entry;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.dancing_koala.whathaveyoubeenupto.R;
import com.dancing_koala.whathaveyoubeenupto.application.WhybutApp;
import com.dancing_koala.whathaveyoubeenupto.data.repository.EntryRepository;
import com.dancing_koala.whathaveyoubeenupto.entry.mvp.EntryAddPresenter;
import com.dancing_koala.whathaveyoubeenupto.entry.mvp.IEntryAddView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EntryAddActivity extends AppCompatActivity implements IEntryAddView {

    public static final int RESULT_ENTRY_ADDED = 111;

    @BindView(R.id.entry_content_input)
    public EditText mContentInput;

    private EntryAddPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_add);

        ButterKnife.bind(this);

        mPresenter = new EntryAddPresenter(new EntryRepository(((WhybutApp) getApplication()).getDaoSession()));
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.detachView();
    }

    @OnClick(R.id.positive_button)
    public void onPositiveButtonClick() {
        final String content = mContentInput.getText().toString();
        if (mPresenter.validateContent(content)) {
            mPresenter.createEntry(content);
        }
    }

    @OnClick(R.id.negative_button)
    public void onNegativeButtonClick() {
        mPresenter.onCancelCreation();
    }

    @Override
    public void close() {
        finish();
    }

    @Override
    public void displayCreationSuccessMessage() {
        Toast.makeText(this, "Great success!", Toast.LENGTH_SHORT).show();
        setResult(RESULT_ENTRY_ADDED);
        close();
    }

    @Override
    public void displayCreationFailureMessage() {
        Toast.makeText(this, "Failed to create entry!", Toast.LENGTH_SHORT).show();
    }
}
