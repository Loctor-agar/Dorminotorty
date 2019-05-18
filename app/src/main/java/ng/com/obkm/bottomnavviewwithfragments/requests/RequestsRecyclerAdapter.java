package ng.com.obkm.bottomnavviewwithfragments.requests;

import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import ng.com.obkm.bottomnavviewwithfragments.R;
import ng.com.obkm.bottomnavviewwithfragments.RequestDetailsActivity;
import ng.com.obkm.bottomnavviewwithfragments.requests.RequestsRecyclerAdapter;

public class RequestsRecyclerAdapter extends RecyclerView.Adapter<RequestsRecyclerAdapter.ViewHolder> {
    public List<RequestModel> home_list;
    private OnItemClickListener mListener;

    public RequestsRecyclerAdapter(List<RequestModel> list) {
        this.home_list = list;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public RequestsRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_item, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestsRecyclerAdapter.ViewHolder holder, int position) {
        int id = home_list.get(position).getId();
        String title = home_list.get(position).getTitle();
        String desc = home_list.get(position).getDescription();
        Date date = home_list.get(position).getDate();
        String status = home_list.get(position).getStatus();

        holder.title.setText(title);
        holder.desc.setText(desc);
        holder.date.setText(DateFormat.getDateInstance().format(date));
        holder.status.setText(status);


    }

    @Override
    public int getItemCount() {
        return home_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private View mView;
        private TextView title;
        private TextView desc;
        private TextView date;
        private TextView status;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            title = mView.findViewById(R.id.item_title);
            desc = mView.findViewById(R.id.item_desc);
            date = mView.findViewById(R.id.item_date);
            status = mView.findViewById(R.id.item_status);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
