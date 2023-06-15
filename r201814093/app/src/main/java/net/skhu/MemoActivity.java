package net.skhu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class MemoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);
        EditText editText1 = findViewById(R.id.editText1);
        EditText editText2 = findViewById(R.id.editText2);
        Student student = (Student) getIntent().getSerializableExtra("MEMO");
        Integer index = (Integer) getIntent().getSerializableExtra("index");
        if (student != null) {
            editText1.setText(student.getStudentId());
            editText2.setText(student.getStudentName());
        }
        Button button = findViewById(R.id.btnSave);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String studentId = editText1.getText().toString();
                if (TextUtils.isEmpty(studentId)) {
                    editText1.setError("학번을 입력하세요");
                    return;
                }
                String studentName = editText2.getText().toString();
                if (TextUtils.isEmpty(studentName)) {
                    editText2.setError("이름을 입력하세요");
                    return;
                }
                Student student = new Student(studentId, studentName);
                Intent intent = new Intent();
                intent.putExtra("MEMO", student);
                intent.putExtra("index", index);
                setResult(RESULT_OK, intent);
                finish();
            }
        };
        button.setOnClickListener(listener);
    }
}