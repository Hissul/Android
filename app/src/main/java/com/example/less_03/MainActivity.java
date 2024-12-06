package com.example.less_03;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static ArrayList<String> COUNTRIES = new ArrayList<String>();
    private String textStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // получение из хранилища
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        // получение из хранилища
        String resultstr = sharedPreferences.getString("savedArray", "");
        String[] strings = resultstr.split(" ");



        if(resultstr.isEmpty()){
            COUNTRIES.add("Belgium");
            COUNTRIES.add("France");
            COUNTRIES.add("Italy");
            COUNTRIES.add("Germany");
            COUNTRIES.add("Spain");
            COUNTRIES.add("Belorussian");
            Log.d("KUKU", "onCreate:  IFFFFF" );
        }else{
            COUNTRIES.clear();
            COUNTRIES.addAll(Arrays.asList(strings));
        }
        // получение из хранилища
        textStr = sharedPreferences.getString("savedSTR", "");

        TextView textView = findViewById(R.id.text_view);
        textView.setText(textStr);


        // Dropdown
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, COUNTRIES);

        AutoCompleteTextView search = (AutoCompleteTextView)
                findViewById(R.id.autoCompleteTextView);
        search.setAdapter(adapter);


        // Текущее время
        Date currentDate = new Date();
        // Форматирование времени как "день.месяц.год"
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);


        // Save button
        Button b = findViewById(R.id.button);
        b.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(!COUNTRIES.contains(String.valueOf(search.getText()))){
                    COUNTRIES.add(String.valueOf(search.getText()));
                }

                String result = "";
                textStr += dateText + "  " + search.getText() + "\n";

                textView.setText(textStr);

                saveAllText();

                search.setText("");

                // Установить обновленный адаптер для AutoCompleteTextView
                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,  // Используйте контекст вашей активности
                        android.R.layout.simple_dropdown_item_1line, COUNTRIES);
                search.setAdapter(adapter);

            }
        });

        DatePicker dpick = findViewById(R.id.date_picker);
        Button but = findViewById(R.id.button2);

        but.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                textView.setVisibility(View.GONE);
                dpick.setVisibility(View.VISIBLE);

                dpick.setOnDateChangedListener(new DatePicker.OnDateChangedListener(){
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        textView.setText("");

                        String formattedString = selectedDate(year, monthOfYear, dayOfMonth);

                        textView.setVisibility(View.VISIBLE);
                        dpick.setVisibility(View.GONE);

                        String[] notes = textStr.split("\n");

                        for (String note : notes) {

                            String[] tmp = note.trim().split("  ");

                            if(formattedString.equals(tmp[0])){

                                textView.append(note + "\n");
                            }
                        }
                    }

                });
            }


        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveAllText();
    }

    private void saveAllText(){
        // Сохраняем текст перед тем, как приложение будет закрыто
        StringBuilder res = new StringBuilder();
        for (String str : COUNTRIES) {
            res.append(str).append(" ");
        }
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("savedArray", res.toString());
        editor.putString("savedSTR", textStr);
        editor.apply();  // Применяем изменения
    }

    private String selectedDate(int year, int monthOfYear, int dayOfMonth){
        LocalDate localDate = LocalDate.of(year, monthOfYear + 1, dayOfMonth);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return localDate.format(formatter);
    }

}