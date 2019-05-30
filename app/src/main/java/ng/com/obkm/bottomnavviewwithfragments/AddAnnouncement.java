package ng.com.obkm.bottomnavviewwithfragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ng.com.obkm.bottomnavviewwithfragments.announcements.AnnouncementModel;
import ng.com.obkm.bottomnavviewwithfragments.services.AnnouncementService;
import ng.com.obkm.bottomnavviewwithfragments.services.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAnnouncement extends AppCompatActivity {
    private static final String TAG = "AddAnnouncement";


    private Button add_image_btn;
    private ImageView imageViewAnAdd;
    private EditText title;
    private Button addButton;
    private int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_announcement);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_announcement);
        toolbar.setTitle("Create Announcement");



        addButton = (Button) findViewById(R.id.add_btn);
        title = (EditText) findViewById(R.id.editText3);




        addButton.setOnClickListener(view -> {

            String annTitle = title.getText().toString();
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date currentDate = new Date(System.currentTimeMillis());
            AnnouncementModel announcement = new AnnouncementModel(23,annTitle, "privet details",  currentDate);
            Log.d(TAG, "Announcement: " + announcement);
            sendAnnouncement(announcement);

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK && data !=null && data.getData()!=null){

            Uri uri =data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                imageViewAnAdd.setImageBitmap(bitmap);
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }

    private void sendAnnouncement(AnnouncementModel announcementModel) {
        AnnouncementService announcementsService = ApiClient.getClientInstance().create(AnnouncementService.class);
        Call<AnnouncementModel> call = announcementsService.createAnnouncement(announcementModel);

        call.enqueue(new Callback<AnnouncementModel>() {
            @Override
            public void onResponse(Call<AnnouncementModel> call, Response<AnnouncementModel> response) {
                Log.d(TAG, "onResponse: " + response.message());
                Toast.makeText(AddAnnouncement.this, "Announcement added", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<AnnouncementModel> call, Throwable t) {
                Log.d(TAG, "something went wrong: " + t.getMessage().toString());
            }
        });
    }

}
