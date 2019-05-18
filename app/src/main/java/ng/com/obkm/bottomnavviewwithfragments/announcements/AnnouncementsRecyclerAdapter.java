package ng.com.obkm.bottomnavviewwithfragments.announcements;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import ng.com.obkm.bottomnavviewwithfragments.R;

public class AnnouncementsRecyclerAdapter extends RecyclerView.Adapter<ng.com.obkm.bottomnavviewwithfragments.announcements.AnnouncementsRecyclerAdapter.ViewHolder> {
    public List<AnnouncementModel> announcementList;
    private Context mContext;
    private OnItemClickListener mListener;

    public AnnouncementsRecyclerAdapter(List<AnnouncementModel> list) {
        this.announcementList = list;
    }

    public AnnouncementsRecyclerAdapter(Context mContext, List<AnnouncementModel> list) {
        this.mContext = mContext;
        this.announcementList = list;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public ng.com.obkm.bottomnavviewwithfragments.announcements.AnnouncementsRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.announcement_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ng.com.obkm.bottomnavviewwithfragments.announcements.AnnouncementsRecyclerAdapter.ViewHolder holder, int position) {
        int id = announcementList.get(position).getId();
        String desc = announcementList.get(position).getDesc();
        String title = announcementList.get(position).getTitle();
        Date date = announcementList.get(position).getDate();

        holder.desc.setText(desc);
        holder.title.setText(title);
        holder.date.setText(DateFormat.getDateInstance().format(date));
    }

    @Override
    public int getItemCount() {
        return announcementList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private View mView;
        private TextView title;
        private TextView desc;
        private TextView date;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            title = mView.findViewById(R.id.txt_title);
            desc = mView.findViewById(R.id.txt_description);
            date = mView.findViewById(R.id.item_date);

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
