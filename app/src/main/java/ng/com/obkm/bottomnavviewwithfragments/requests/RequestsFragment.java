package ng.com.obkm.bottomnavviewwithfragments.requests;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


import ng.com.obkm.bottomnavviewwithfragments.AddRequest;
import ng.com.obkm.bottomnavviewwithfragments.R;
import ng.com.obkm.bottomnavviewwithfragments.RequestDetailsActivity;
import ng.com.obkm.bottomnavviewwithfragments.requests.RequestsRecyclerAdapter;
import ng.com.obkm.bottomnavviewwithfragments.services.ApiClient;
import ng.com.obkm.bottomnavviewwithfragments.services.RequestsService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RequestsFragment extends Fragment implements RequestsRecyclerAdapter.OnItemClickListener{

    private static final String TAG = "RequestsFragment";
    private static final String EXTRA_ID = "id";
    private FloatingActionButton fabAdd;

    private List<RequestModel> requestList;
    private RecyclerView recyclerView;
    RequestsRecyclerAdapter recyclerAdapter;

    public RequestsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_requests, container, false);
        requestList = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.home_rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        fetchRequests();

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        fabAdd = (FloatingActionButton)view.findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddRequest.class);
                startActivity(intent);

            }
        });

        return view;

    }

    void fetchRequests() {
        RequestsService requestsService = ApiClient.getClientInstance().create(RequestsService.class);
        Call<List<RequestModel>> call = requestsService.getRequests();
        call.enqueue(new Callback<List<RequestModel>>() {
            @Override
            public void onResponse(Call<List<RequestModel>> call, Response<List<RequestModel>> response) {
                requestList = response.body();
                recyclerAdapter = new RequestsRecyclerAdapter(requestList);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.setOnItemClickListener(RequestsFragment.this);
            }

            @Override
            public void onFailure(Call<List<RequestModel>> call, Throwable t) {
                Log.d(TAG, "-----FAIL----- : " + t.getMessage());
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(getActivity(), RequestDetailsActivity.class);
        RequestModel request = requestList.get(position);

        detailIntent.putExtra(EXTRA_ID, request.getId());
        startActivity(detailIntent);
    }
}
