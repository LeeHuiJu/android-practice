package net.skhu.e05photo;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class Photo1Activity extends AppCompatActivity {
    ImageView imageView;
    ActivityResultLauncher<Intent> galleryLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo1);
        imageView = findViewById(R.id.imageView);
        galleryLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent intent = result.getData();
                    Uri imageUri = intent.getData();
                    Log.d("내태그", imageUri.toString());
                    imageView.setImageURI(imageUri);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_photo1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_pick_from_gallery) {
            startActivity_pickFromGallery();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void startActivity_pickFromGallery() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        galleryLauncher.launch(intent);
    }
}