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
import spiral.bit.dev.movcinema.models.NowPlayingResult;
import spiral.bit.dev.movcinema.activities.MovieDetailActivity;

public class NowPlayingAdapter extends RecyclerView.Adapter<NowPlayingAdapter.NowPlayingViewHolder> {

    private Context context;
    private ArrayList<NowPlayingResult> nowPlayingArrayList;

    public NowPlayingAdapter(Context context, ArrayList<NowPlayingResult> nowPlayingArrayList) {
        this.context = context;
        this.nowPlayingArrayList = nowPlayingArrayList;
    }

    @NonNull
    @Override
    public NowPlayingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.now_playing_item, parent, false);
        return new NowPlayingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NowPlayingViewHolder holder, int position) {
        holder.tvName.setText(nowPlayingArrayList.get(position).getOriginalTitle());
        holder.tvPopular.setText(Double.toString(nowPlayingArrayList.get(position).getPopularity()));
        String urlPath = "https://image.tmdb.org/t/p/w500/" + nowPlayingArrayList.get(position).getPosterPath();
        Picasso.get()
                .load(urlPath)
                .placeholder(R.drawable.placeholder)
                .into(holder.imgPreview);
        holder.itemView.setOnClickListener(v -> {
            NowPlayingResult nowPlayingResult = nowPlayingArrayList.get(position);
            Intent intent = new Intent(context, MovieDetailActivity.class);
            intent.putExtra("movObject", nowPlayingResult);
            intent.putExtra("typeMovie", "nowPlaying");
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return nowPlayingArrayList.size();
    }

    class NowPlayingViewHolder extends RecyclerView.ViewHolder {

        RoundedImageView imgPreview;
        TextView tvName, tvPopular;

        public NowPlayingViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.popular_name_tv);
            tvPopular = itemView.findViewById(R.id.popular_tv);
            imgPreview = itemView.findViewById(R.id.popular_iv);
        }
    }
}
