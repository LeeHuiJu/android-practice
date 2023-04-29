package net.skhu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = findViewById(R.id.button);
        EditText e1 = findViewById(R.id.editText1);
        EditText e2 = findViewById(R.id.editText2);

        View.OnClickListener listenerObj = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence s1 = e1.getText();
                CharSequence s2 = e2.getText();
                e1.setText(s2);
                e2.setText(s1);
                Toast.makeText(MainActivity.this, "201814093 이희주", Toast.LENGTH_SHORT).show();
            }
        };
        b.setOnClickListener(listenerObj);
    }
}