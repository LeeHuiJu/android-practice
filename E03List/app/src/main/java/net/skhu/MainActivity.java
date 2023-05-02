package net.skhu;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //ActivityResultLauncher<Intent> 멤버 변수 선언
    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ActivityResultLauncher<Intent> 객체 생성
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent intent = result.getData();
                    Memo3 memo = (Memo3) intent.getSerializableExtra("MEMO");
                    String s = String.format("<h1>%s</h1><p>%s</p><p style='color: blue'>%s</p>", memo.getTitle(), memo.getDateFormatted(), memo.getContent());
                    TextView textView = findViewById(R.id.textView);
                    textView.setText(Html.fromHtml(s));
                }
            }
        });
    }

    public void btnListView_clicked(View view) {
        Intent intent = new Intent(this, ListViewActivity.class);
        startActivity(intent);
    }

    public void btnRecyclerView1_clicked(View view) {
        Intent intent = new Intent(this, RecyclerView1Activity.class);
        startActivity(intent);
    }

    public void btnRecyclerView2_clicked(View view) {
        Intent intent = new Intent(this, RecyclerView2Activity.class);
        startActivity(intent);
    }

    public void btnMemo3_clicked(View view) {
        Intent intent = new Intent(this, Memo3Activity.class);
        activityResultLauncher.launch(intent);
    }
}