package ng.com.obkm.bottomnavviewwithfragments;

import android.content.Intent;
import android.graphics.Bitmap;
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
import java.text.SimpleDateFormat;
import java.util.Date;

import ng.com.obkm.bottomnavviewwithfragments.announcements.AnnouncementModel;


import ng.com.obkm.bottomnavviewwithfragments.requests.RequestModel;
import ng.com.obkm.bottomnavviewwithfragments.services.AnnouncementService;
import ng.com.obkm.bottomnavviewwithfragments.services.ApiClient;
import ng.com.obkm.bottomnavviewwithfragments.services.RequestsService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddRequest extends AppCompatActivity {

    private static final String TAG = "AddRequest";

    private Button image_btn;
    private ImageView image_btn_add_rq;
    private EditText title;
    private EditText description;
    private Button addButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_request);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_add_request);
        toolbar.setTitle("Create Request");
        setSupportActionBar(toolbar);

        addButton =(Button)findViewById(R.id.save_btn);
        title = (EditText)findViewById(R.id.title_rq);
        description = (EditText)findViewById(R.id.desc_rq);
        image_btn = (Button)findViewById(R.id.image_btn);
        image_btn_add_rq = (ImageView)findViewById(R.id.imageView_add_rq);

        image_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);
            }
        });


        addButton.setOnClickListener(view -> {
            String rqTitle = title.getText().toString();
            String requestDesc = description.getText().toString();
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date currenttDate = new Date(System.currentTimeMillis());
            RequestModel request = new RequestModel(24,rqTitle,requestDesc,currenttDate, "done");
            Log.d(TAG, "Announcement: " + request);
            sendRequest(request);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        image_btn_add_rq.setImageBitmap(bitmap);
    }

    private void sendRequest(RequestModel request) {
        RequestsService requestsService = ApiClient.getClientInstance().create(RequestsService.class);
        Call<RequestModel> call = requestsService.createRequest(request);

        call.enqueue(new Callback<RequestModel>() {
            @Override
            public void onResponse(Call<RequestModel> call, Response<RequestModel> response) {
                Log.d(TAG, "onResponse: " + response.message());
                Toast.makeText(AddRequest.this, "Request added", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<RequestModel> call, Throwable t) {
                Log.d(TAG, "something went wrong: " + t.getMessage().toString());
            }
        });
    }
}


