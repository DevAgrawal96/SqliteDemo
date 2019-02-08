 package com.example.sqlitedemo1;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

 public class MainActivity extends AppCompatActivity {

    private Sqlitehelper mSqlite;
    private EditText mEdtId,mEdtName,mEdtSurname;
    private TextView mTxtId,mTxtName,mTxtSurname;
    private Button mSubmitBtn,mViewBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSqlite = new Sqlitehelper(this);
        init();
        addData();
        viewAllData();



    }


    void addData(){
        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean result = mSqlite.insert(mEdtId.getText().toString(),
                        mEdtName.getText().toString(),
                        mEdtSurname.getText().toString());
                if (result = true) {
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    void viewAllData(){
        mViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cursor = mSqlite.getAllData();

                if(cursor.getCount() == 0 ){
                    showMessage("Error","NO Data Found");
                        return;

                    }
                StringBuffer buffer = new StringBuffer();
                while (cursor.moveToNext()){
                    buffer.append("Id :- "+cursor.getString(0)+"\n");
                    buffer.append("Name :- "+cursor.getString(1)+"\n");
                    buffer.append("SurName :- "+cursor.getString(2)+"\n\n");


                }
                showMessage("Data",buffer.toString());


            }
        });
    }

    void showMessage(String title ,String message){
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setCancelable(true);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.show();
    }

    void init(){
        mEdtId = findViewById(R.id.idShow);
        mEdtName = findViewById(R.id.nameShow);
        mEdtSurname  = findViewById(R.id.surnameShow);
        mTxtId  = findViewById(R.id.idView);
        mTxtName = findViewById(R.id.nameView);
        mTxtSurname = findViewById(R.id.surnameView);
        mSubmitBtn = findViewById(R.id.submitBtn);
        mViewBtn = findViewById(R.id.viewBtn);
    }
}
