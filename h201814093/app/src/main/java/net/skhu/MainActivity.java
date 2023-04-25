package net.skhu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void btnTest1_clicked(View view) {
        Intent intent = new Intent(this, Test1Activity.class);
        startActivity(intent);
    }

    public void btnTest2_clicked(View view) {
        Intent intent = new Intent(this, Test2Activity.class);
        startActivity(intent);
    }
}