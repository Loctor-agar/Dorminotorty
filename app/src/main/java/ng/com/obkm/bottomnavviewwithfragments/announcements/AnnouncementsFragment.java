package ng.com.obkm.bottomnavviewwithfragments.announcements;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ng.com.obkm.bottomnavviewwithfragments.AddAnnouncement;
import ng.com.obkm.bottomnavviewwithfragments.AddRequest;
import ng.com.obkm.bottomnavviewwithfragments.AnnouncementDetailsActivity;
import ng.com.obkm.bottomnavviewwithfragments.R;
import ng.com.obkm.bottomnavviewwithfragments.services.AnnouncementService;
import ng.com.obkm.bottomnavviewwithfragments.services.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnnouncementsFragment extends Fragment implements AnnouncementsRecyclerAdapter.OnItemClickListener {
    public static final String EXTRA_ID = "id";
    private static final String TAG = "AnnouncementFragment";
    private FloatingActionButton addFab;

    private List<AnnouncementModel> announcementList;
    private RecyclerView recyclerView;
    AnnouncementsRecyclerAdapter recyclerAdapter;

    public AnnouncementsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_announcements, container, false);
        announcementList = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.home_rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        fetchAnnouncements();
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        addFab = (FloatingActionButton)view.findViewById(R.id.add_fab);
        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddAnnouncement.class);
                startActivity(intent);
            }
        });

        return view;
    }


    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(getActivity(), AnnouncementDetailsActivity.class);
        AnnouncementModel announcement = announcementList.get(position);

        detailIntent.putExtra(EXTRA_ID, announcement.getId());
        startActivity(detailIntent);
    }

    private void fetchAnnouncements() {
        AnnouncementService announcementsService = ApiClient.getClientInstance().create(AnnouncementService.class);
        Call<List<AnnouncementModel>> call = announcementsService.getAnnouncements();
        call.enqueue(new Callback<List<AnnouncementModel>>() {
            @Override
            public void onResponse(Call<List<AnnouncementModel>> call, Response<List<AnnouncementModel>> response) {
                announcementList = response.body();
                recyclerAdapter = new AnnouncementsRecyclerAdapter(announcementList);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.setOnItemClickListener(AnnouncementsFragment.this);
            }

            @Override
            public void onFailure(Call<List<AnnouncementModel>> call, Throwable t) {
                Log.d(TAG, "-----FAIL----- : " + t.getMessage());
            }
        });
    }
}
