package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//Реализация метода OnClick
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
EditText etText;
Button btnSave,btnLoad;
SharedPreferences sPref;
final String SAVED_TEXT = "saved_text";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etText = (EditText)findViewById(R.id.etText);
        btnSave = (Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        btnLoad = (Button)findViewById(R.id.btnLoad);
        btnLoad.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSave:
                saveText();
                //при нажатии вызываем saveText
                break;
            case R.id.btnLoad:
                //при нажатии вызываем LoadText
                loadText();
                break;
            default:
                break;

        }
    }



    private void saveText() {
        //констант MODE_PRIVATE используется для настройки доступа и  после сохранения данные будут видны только этому приложению
        //sPref позволяет работать с данными считывать и записовать

        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        //что бы редактировать данные не обходим обьект Editor
        ed.putString(SAVED_TEXT,etText.getText().toString());
        // что бы данные сохранились необходма выполнить метод commit
        ed.commit();
        Toast.makeText(MainActivity.this,"Text saved",Toast.LENGTH_SHORT).show();
        // Toast.makeText(MainActivity.this,"Text saved",Toast.LENGTH_SHORT).show(); - сообщения что данные сохранены
    }

    private void loadText() {
        //здесь будет реализованна загрузка данных
        sPref = getPreferences(MODE_PRIVATE);
        String savedText =  sPref.getString(SAVED_TEXT,"");
        // здесь Editor мы не используем так как нас интересует только чтения
        //читаем с помощью метода getString
        etText.setText(savedText);
        //значения по умолчанию пустая строка
        Toast.makeText(MainActivity.this,"Text Loaded",Toast.LENGTH_SHORT).show();
        // Toast.makeText(MainActivity.this,"Text Loaded",Toast.LENGTH_SHORT).show(); - сообщения что данные сохранены
    }
}