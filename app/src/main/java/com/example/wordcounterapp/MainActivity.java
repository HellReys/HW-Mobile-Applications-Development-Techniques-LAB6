package com.example.wordcounterapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.graphics.Insets;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText editText = findViewById(R.id.editTextInput);
        Spinner spinner = findViewById(R.id.spinnerMetrics);
        Button btnCount = findViewById(R.id.btnCount);
        TextView resultView = findViewById(R.id.textViewResult);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString();

                if (text.isEmpty()) {
                    Toast.makeText(MainActivity.this, R.string.empty_warning, Toast.LENGTH_SHORT).show();
                    return;
                }

                String selected = spinner.getSelectedItem().toString();
                int count = 0;

                switch (selected) {
                    case "Words":
                        count = TextMetrics.countWords(text);
                        break;
                    case "Sentences":
                        count = TextMetrics.countSentences(text);
                        break;
                    case "Characters":
                        count = TextMetrics.countChars(text);
                        break;
                    case "Numbers":
                        count = TextMetrics.countNumbers(text);
                        break;
                }

                resultView.setText(getString(R.string.result_label) + count);
            }
        });
    }
}