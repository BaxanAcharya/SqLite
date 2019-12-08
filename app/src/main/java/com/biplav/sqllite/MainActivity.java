package com.biplav.sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.biplav.sqllite.helper.MyHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etword,etMeaning;
    Button btnaddWord;
    TextView tv_others;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etword=findViewById(R.id.etWord);
        etMeaning=findViewById(R.id.etMeaning);
        btnaddWord=findViewById(R.id.btnAdd);
        tv_others=findViewById(R.id.tv_others);
        tv_others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), OtherActivity.class);
                startActivity(intent);
            }
        });

        btnaddWord.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MyHelper myHelper=new MyHelper(this);
        SQLiteDatabase sqLiteDatabase=myHelper.getWritableDatabase();

        boolean insert=myHelper.InsertData(etword.getText().toString(),etMeaning.getText().toString(),sqLiteDatabase);

        if(insert)
        {
//            Toast.makeText(this, "Inserted", Toast.LENGTH_SHORT).show();

            etword.setText("");
            etMeaning.setText("");
            Intent intent=new Intent(this, DisplayActivity.class);
            startActivity(intent);

        }else {
            Toast.makeText(this, "Some error", Toast.LENGTH_SHORT).show();
        }

    }
}
