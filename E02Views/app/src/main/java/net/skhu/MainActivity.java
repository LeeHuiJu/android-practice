package net.skhu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //메뉴가 달려있는 액티비티는 이 메소드가 필수이다
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //메뉴항목이 클릭되면 자동으로 호출되는 메소드
        int id = item.getItemId();
        if (id == R.id.action_signUp) {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_memo) {
            Intent intent = new Intent(this, MemoActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_buttons) {
            Intent intent = new Intent(this, ButtonsActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_checkboxes) {
            Intent intent = new Intent(this, CheckboxesActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_spinners) {
            Intent intent = new Intent(this, SpinnersActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}