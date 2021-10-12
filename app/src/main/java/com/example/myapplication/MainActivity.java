package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView txtScreen;
    private LinearLayout layMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add24ButtonsForMePlease();

        initInterface();
    }

    private void initInterface() {
        txtScreen = findViewById(R.id.editTextNumber);
        layMain = findViewById(R.id.linearLayoutName);
    }

    private void add24ButtonsForMePlease() {
        LinearLayout[] rows = create6RowsPlease();
        add6RowToMainLayout(rows);
        String[] labels = new String[]
                {
                        "%", "CE", "C", "BackSp",
                        "1/x", "x^2", "sqrt", "/",
                        "7", "8", "9", "*",
                        "4", "5", "6", "-",
                        "1", "2", "3", "+",
                        "+/-", "0", ".", "="

                };
        Button btn;
        int rowIndex;
        for (int i = 0; i < 24; ++i)
        {
            btn = createButtonPlease(labels[i],65000+i);
            rowIndex = i/4;
            addButtonToRow(btn, rows[rowIndex]);
        }
    }

    private void add6RowToMainLayout(LinearLayout[] rows) {
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linearLayoutName);
        for (int i = 0; i < rows.length; ++i)
        {
            linearLayout.addView(rows[i]);

        }
    }

    private void addButtonToRow(Button btn, LinearLayout row) {
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params1.weight = 0.3f;
        btn.setLayoutParams(params1);

        row.addView(btn);

    }

    private Button createButtonPlease(String label, int id) {
        Button button = new Button(this);
        button.setText(label);
        button.setId(id);

        button.setOnClickListener(this);
        return button;


    }

    private LinearLayout[] create6RowsPlease() {
        LinearLayout[] rows = new LinearLayout[6];
        for (int i = 0; i < 6; ++i)
            rows[i] = createRow(65500+i);
        return rows;
    }

    private LinearLayout createRow(int id) {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setId(id);
        return linearLayout;
    }
    boolean first = true;
    int Result;
    int ResultBefore;
    int num;
    char sign;
    public void onClick(View v) {
        Button btn = (Button)v;
        switch (btn.getText().toString())
        {
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9": {
                if (first == true) {
                    txtScreen.setText("");
                    first = false;
                    ResultBefore = 0;
                }
                txtScreen.setText(txtScreen.getText() + btn.getText().toString());
                num =Integer.parseInt(btn.getText().toString());

                break;
            }
            case "C":{
                first = true;
                txtScreen.setText("");
                break;
            }
            case "+":{
                if (first == false){
                    txtScreen.setText(txtScreen.getText() + "+");
                    sign = '+';
                }
                break;
            }
            case "-":{
                if (first == false){
                    txtScreen.setText(txtScreen.getText() + "-");
                }
                sign = '-';
                break;
            }
            case "*":{
                if (first == false){
                    txtScreen.setText(txtScreen.getText() + "*");
                }
                sign = '*';
                break;
            }
            case "/":{
                if (first == false){
                    txtScreen.setText(txtScreen.getText() + "/");
                }
                sign = '/';
                break;
            }
            case "=":{
                if (first == false){
                    switch (sign){
                        case '+':{
                            Result = ResultBefore + num;
                            break;
                        }
                        case '-':{
                            Result = ResultBefore - num;
                            break;
                        }
                        case '*':{
                            Result = ResultBefore * num;
                            break;
                        }
                        case '/':{
                            Result = ResultBefore / num;
                            break;
                        }
                    }
                    txtScreen.setText(Result);
                }
                break;
            }
            default:
                btn.setText("clicked");
        }
    }

}