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
import spiral.bit.dev.movcinema.models.PopularResult;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularResultViewHolder> {

    private final Context context;
    private final ArrayList<PopularResult> results;

    public PopularAdapter(Context context, ArrayList<PopularResult> results) {
        this.context = context;
        this.results = results;
    }

    @NonNull
    @Override
    public PopularResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new PopularResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularResultViewHolder holder, int position) {
        holder.tvName.setText(results.get(position).getOriginalTitle());
        String popularity = String.format(Locale.getDefault(), "%1$,.2f ",
                        results.get(position).getPopularity());
        holder.tvPopular.setText(popularity);
        String urlPath = "https://image.tmdb.org/t/p/w500/" + results.get(position).getPosterPath();
        Glide.with(context).load(urlPath).placeholder(R.drawable.placeholder).into(holder.imgPreview);
        holder.itemView.setOnClickListener(v -> {
            PopularResult popularResult = results.get(position);
            Intent intent = new Intent(context, MovieDetailActivity.class);
            intent.putExtra("movObject", popularResult);
            intent.putExtra("typeMovie", "popular");
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    static class PopularResultViewHolder extends RecyclerView.ViewHolder {

        RoundedImageView imgPreview;
        TextView tvName, tvPopular;

        public PopularResultViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.popular_name_tv);
            tvPopular = itemView.findViewById(R.id.popular_tv);
            imgPreview = itemView.findViewById(R.id.popular_iv);
        }
    }
}
