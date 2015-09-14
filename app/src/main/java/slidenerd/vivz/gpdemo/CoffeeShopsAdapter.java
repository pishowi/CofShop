package slidenerd.vivz.gpdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import slidenerd.vivz.gpdemo.log.L;
import slidenerd.vivz.gpdemo.model.Results;

class CoffeeShopsAdapter extends RecyclerView.Adapter<CoffeeShopsAdapter.YourRecyclerViewHolder> {
    private ArrayList<Results> list = new ArrayList<>();
    private LayoutInflater inflater;

    public CoffeeShopsAdapter(Context context) {
        inflater = LayoutInflater.from(context);

    }

    public void setDataSource(ArrayList<Results> listCoffeeShops) {
        this.list = listCoffeeShops;
        notifyItemRangeChanged(0, list.size());
    }

    @Override
    public YourRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View root = inflater.inflate(R.layout.custom_row, parent, false);
        YourRecyclerViewHolder holder = new YourRecyclerViewHolder(root);
        return holder;
    }

    @Override
    public void onBindViewHolder(YourRecyclerViewHolder yourRecyclerViewHolder, int i) {
        Results current = list.get(i);
        yourRecyclerViewHolder.setName(current.getName());
        yourRecyclerViewHolder.setVicinity(current.getVicinity());
        String ratingString = current.getRating();
        float rating = 0;

        String message = "because rating is not available at " + i;
        if (ratingString != null && !ratingString.trim().isEmpty()) {
            try {
                rating = Float.parseFloat(ratingString);
            } catch (NumberFormatException e) {
                L.m(e + message);
            }
        } else {
            L.m(message);
        }
        yourRecyclerViewHolder.setRating(rating);
    }

    public int getPosition(String title) {
        int position = -1;
        for (int i = 0; i < list.size(); i++) {
            Results current = list.get(i);
            String currentTitle = current.getName();
            if (currentTitle.equalsIgnoreCase(title)) {
                position = i;
            }
        }
        return position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class YourRecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView textName;
        private TextView textVicinity;
        private RatingBar ratingBar;

        public YourRecyclerViewHolder(View itemView) {
            super(itemView);
            textName = (TextView) itemView.findViewById(R.id.text_name);
            textVicinity = (TextView) itemView.findViewById(R.id.text_vicinity);
            ratingBar = (RatingBar) itemView.findViewById(R.id.rating);
        }

        public void setName(String name) {
            textName.setText(name);
        }

        public void setVicinity(String vicinity) {
            textVicinity.setText(vicinity);
        }

        public void setRating(float rating) {
            ratingBar.setRating(rating);
        }
    }
}