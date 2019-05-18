package ng.com.obkm.bottomnavviewwithfragments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ng.com.obkm.bottomnavviewwithfragments.announcements.AnnouncementModel;
import ng.com.obkm.bottomnavviewwithfragments.services.AnnouncementService;
import ng.com.obkm.bottomnavviewwithfragments.services.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static ng.com.obkm.bottomnavviewwithfragments.announcements.AnnouncementsFragment.EXTRA_ID;
import android.support.v7.widget.Toolbar;

public class AnnouncementDetailsActivity extends AppCompatActivity {

    int id;

    private Toolbar toolbarAn;
    private TextView title;
    private TextView description;
    private TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_details);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_details_an);
        toolbar.setTitle("Announcement Details");
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
            startActivity(new Intent(AnnouncementDetailsActivity.this, AnnouncementEditActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void fetchAnnouncements() {
        AnnouncementService announcementsService = ApiClient.getClientInstance().create(AnnouncementService.class);
        Call<AnnouncementModel> call = announcementsService.getAnnouncementById(Integer.toString(id));
        call.enqueue(new Callback<AnnouncementModel>() {
            @Override
            public void onResponse(Call<AnnouncementModel> call, Response<AnnouncementModel> response) {
//                id = response.body().getId();

                title = (TextView) findViewById(R.id.title_txt);
                title.setText(response.body().getTitle());
                System.out.print("\n\n\n TITLE: " + title.getText());

                description = (TextView) findViewById(R.id.description_txt);
                description.setText(response.body().getDesc());

                SimpleDateFormat simpledate = new SimpleDateFormat();
                date = (TextView) findViewById(R.id.date_txt);
                date.setText(simpledate.format(response.body().getDate()));
            }

            @Override
            public void onFailure(Call<AnnouncementModel> call, Throwable t) {

            }
        });
    }
}
