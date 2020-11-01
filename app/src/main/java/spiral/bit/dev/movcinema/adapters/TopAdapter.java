package spiral.bit.dev.movcinema.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.Locale;

import spiral.bit.dev.movcinema.R;
import spiral.bit.dev.movcinema.activities.MovieDetailActivity;
import spiral.bit.dev.movcinema.models.TopResult;

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.TopResultViewHolder> {

    private final Context context;
    private final ArrayList<TopResult> topArrayList;

    public TopAdapter(Context context, ArrayList<TopResult> topArrayList) {
        this.context = context;
        this.topArrayList = topArrayList;
    }

    @NonNull
    @Override
    public TopResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new TopResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopResultViewHolder holder, int position) {
        holder.tvName.setText(topArrayList.get(position).getOriginalTitle());
        String popularity = String.format(Locale.getDefault(), "%1$,.2f ",
                topArrayList.get(position).getPopularity());
        holder.tvPopular.setText(popularity);
        String urlPath = "https://image.tmdb.org/t/p/w500/" + topArrayList.get(position).getPosterPath();
        Glide.with(context).load(urlPath).placeholder(R.drawable.placeholder).into(holder.imgPreview);
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

    static class TopResultViewHolder extends RecyclerView.ViewHolder {

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
