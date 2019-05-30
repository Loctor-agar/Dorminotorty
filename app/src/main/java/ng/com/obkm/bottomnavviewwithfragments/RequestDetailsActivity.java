package ng.com.obkm.bottomnavviewwithfragments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import ng.com.obkm.bottomnavviewwithfragments.requests.RequestModel;
import ng.com.obkm.bottomnavviewwithfragments.services.ApiClient;
import ng.com.obkm.bottomnavviewwithfragments.services.RequestsService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static ng.com.obkm.bottomnavviewwithfragments.announcements.AnnouncementsFragment.EXTRA_ID;

public class RequestDetailsActivity extends AppCompatActivity {
    int id;

    private TextView title;
    private TextView status;
    private TextView description;
    private TextView date;
    private TextView room;
    private TextView author;
    private TextView category;


    private Toolbar toolbarRq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_details);

        Toolbar toolbar =(Toolbar)findViewById(R.id.toolbar_detail_rq);
        toolbar.setTitle("Request Details");
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        id = intent.getIntExtra(EXTRA_ID, 0);

        fetchAnnouncements();
        System.out.print(intent.getIntExtra(EXTRA_ID, 0));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_edit) {
            startActivity(new Intent(RequestDetailsActivity.this, RequestEditActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void fetchAnnouncements() {
        RequestsService requestsService = ApiClient.getClientInstance().create(RequestsService.class);
        Call<RequestModel> call = requestsService.getRequestById(Integer.toString(id));
        call.enqueue(new Callback<RequestModel>() {
            @Override
            public void onResponse(Call<RequestModel> call, Response<RequestModel> response) {
//                id = response.body().getId();

                title = (TextView) findViewById(R.id.title_txt_request);
                title.setText(response.body().getTitle());
                System.out.print("\n\n\n TITLE: " + title.getText());

                status = (TextView) findViewById(R.id.status_txt_request);
                status.setText(response.body().getStatus());

                description = (TextView) findViewById(R.id.description_txt_request);
                description.setText(response.body().getDescription());

                SimpleDateFormat simpledate = new SimpleDateFormat();
                date = (TextView) findViewById(R.id.date_txt_request);
                date.setText(simpledate.format(response.body().getDate()));

                room = (TextView)findViewById(R.id.room_txt_request);
                room.setText(response.body().getRoom());

                author =(TextView)findViewById(R.id.author_txt_request);
                author.setText(response.body().getAuthor());

                category =(TextView)findViewById(R.id.category_txt_request);
                category.setText(response.body().getCategory());

            }

            @Override
            public void onFailure(Call<RequestModel> call, Throwable t) {

            }
        });

    }
}
