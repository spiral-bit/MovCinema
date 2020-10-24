package spiral.bit.dev.movcinema.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import spiral.bit.dev.movcinema.R;
import spiral.bit.dev.movcinema.models.TopResult;
import spiral.bit.dev.movcinema.activities.MovieDetailActivity;

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.TopResultViewHolder> {

    private Context context;
    private ArrayList<TopResult> topArrayList;

    public TopAdapter(Context context, ArrayList<TopResult> topArrayList) {
        this.context = context;
        this.topArrayList = topArrayList;
    }

    @NonNull
    @Override
    public TopResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_item, parent, false);
        return new TopResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopResultViewHolder holder, int position) {
        holder.tvName.setText(topArrayList.get(position).getOriginalTitle());
        holder.tvPopular.setText(Double.toString(topArrayList.get(position).getPopularity()));
        String urlPath = "https://image.tmdb.org/t/p/w500/" + topArrayList.get(position).getPosterPath();
        Picasso.get()
                .load(urlPath)
                .placeholder(R.drawable.placeholder)
                .into(holder.imgPreview);
        holder.itemView.setOnClickListener(v -> {
            TopResult topResult = topArrayList.get(position);
            Intent intent = new Intent(context, MovieDetailActivity.class);
            intent.putExtra("movObject", topResult);
            intent.putExtra("typeMovie", "topRating");
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return topArrayList.size();
    }

    class TopResultViewHolder extends RecyclerView.ViewHolder {

        RoundedImageView imgPreview;
        TextView tvName, tvPopular;

        public TopResultViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.popular_name_tv);
            tvPopular = itemView.findViewById(R.id.popular_tv);
            imgPreview = itemView.findViewById(R.id.popular_iv);
        }
    }
}
