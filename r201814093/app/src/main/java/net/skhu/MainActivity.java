package net.skhu;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<Student> arrayList = new ArrayList<>();
    ArrayList<String> keyList = new ArrayList<>();
    ActivityResultLauncher<Intent> activityResultLauncher;
    DatabaseReference item01;
    ChildEventListener firebaseListener = new ChildEventListener() {
        private int findIndex(String key) {
            return Collections.binarySearch(keyList, key);
        }

        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
            String key = dataSnapshot.getKey();
            Student student = dataSnapshot.getValue(Student.class);
            arrayList.add(student);
            keyList.add(key);
            recyclerViewAdapter.notifyItemInserted(arrayList.size() - 1);
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
            String key = dataSnapshot.getKey();
            Student student = dataSnapshot.getValue(Student.class);
            int index = findIndex(key);
            arrayList.set(index, student);
            recyclerViewAdapter.notifyItemChanged(index);
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewAdapter = new RecyclerViewAdapter(this, arrayList);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerViewAdapter);
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent intent = result.getData();
                    Student student = (Student) intent.getSerializableExtra("MEMO");
                    Integer index = (Integer) intent.getSerializableExtra("index");
                    if (index == null) {
                        String key = item01.push().getKey();
                        item01.child(key).setValue(student);
                    } else {
                        String key = keyList.get(index);
                        item01.child(key).setValue(student);
                    }
                    recyclerViewAdapter.notifyDataSetChanged();
                }
            }
        });
        this.item01 = FirebaseDatabase.getInstance().getReference("item01");
        this.item01.addChildEventListener(firebaseListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_create) {
            Intent intent = new Intent(this, MemoActivity.class);
            activityResultLauncher.launch(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void onMemoClicked(int index) {
        Intent intent = new Intent(this, MemoActivity.class);
        Student student = arrayList.get(index);
        intent.putExtra("MEMO", student);
        intent.putExtra("index", index);
        activityResultLauncher.launch(intent);
    }
}