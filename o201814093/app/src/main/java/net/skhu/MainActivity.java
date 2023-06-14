package net.skhu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<String> keyList = new ArrayList<>();
    DatabaseReference item03;

    ChildEventListener firebaseListener = new ChildEventListener() {
        private int findIndex(String key) {
            return Collections.binarySearch(keyList, key);
        }

        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
            String key = dataSnapshot.getKey();
            String memo = dataSnapshot.getValue(String.class);
            arrayList.add(memo);
            keyList.add(key);
            recyclerViewAdapter.notifyItemInserted(arrayList.size() - 1);
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            String key = dataSnapshot.getKey();
            int index = findIndex(key);
            arrayList.remove(index);
            keyList.remove(index);
            recyclerViewAdapter.notifyItemRemoved(index);
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
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerViewAdapter);
        Button b = findViewById(R.id.btnAdd);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                EditText e = findViewById(R.id.editText);
                String s = e.getText().toString();
                e.setText("");
                String key = item03.push().getKey();
                item03.child(key).setValue(s);
            }
        });

        this.item03 = FirebaseDatabase.getInstance().getReference("item03");
        this.item03.addChildEventListener(firebaseListener);
    }

    public void onMemoClicked(int index) {
        removeMemo(index);
    }

    private void removeMemo(int memoIndex) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.confirm);
        builder.setMessage(R.string.doYouWantToDelete);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int index) {
                item03.child(keyList.get(memoIndex)).removeValue();
            }
        });
        builder.setNegativeButton(R.string.no, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
