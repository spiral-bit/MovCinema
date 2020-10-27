package spiral.bit.dev.movcinema.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.Objects;

import spiral.bit.dev.movcinema.R;
import spiral.bit.dev.movcinema.models.NowPlayingResult;
import spiral.bit.dev.movcinema.models.PopularResult;
import spiral.bit.dev.movcinema.models.TopResult;

public class MovieDetailActivity extends AppCompatActivity {

    private RoundedImageView currImageView;
    private String urlPath;
    private TextView titleTv, voteTv, overviewTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        currImageView = findViewById(R.id.image_of_curr_movie);
        titleTv = findViewById(R.id.title_curr_movie_tv);
        voteTv = findViewById(R.id.vote_curr_movie_tv);
        overviewTv = findViewById(R.id.overview_curr_movie_tv);
        Intent intent = getIntent();
        urlPath = "https://image.tmdb.org/t/p/w500/";
        if (intent != null && intent.hasExtra("movObject") && intent.hasExtra("typeMovie")) {
            String typeOfMovie = intent.getStringExtra("typeMovie");
            if (typeOfMovie != null) {
                switch (typeOfMovie) {
                    case "popular":
                        PopularResult popularResult = intent.getParcelableExtra("movObject");
                        urlPath += Objects.requireNonNull(popularResult).getPosterPath();
                        Glide.with(currImageView).load(urlPath).placeholder(R.drawable.placeholder).into(currImageView);
                        titleTv.setText(popularResult.getOriginalTitle());
                        voteTv.setText(String.valueOf(popularResult.getVoteCount()));
                        overviewTv.setText(popularResult.getOverview());
                        return;
                    case "topRating":
                        TopResult topResult = intent.getParcelableExtra("movObject");
                        urlPath += Objects.requireNonNull(topResult).getPosterPath();
                        Glide.with(currImageView).load(urlPath).placeholder(R.drawable.placeholder).into(currImageView);
                        titleTv.setText(topResult.getOriginalTitle());
                        voteTv.setText(String.valueOf(topResult.getVoteCount()));
                        overviewTv.setText(topResult.getOverview());
                        return;
                    case "nowPlaying":
                        NowPlayingResult nowPlaying = intent.getParcelableExtra("movObject");
                        urlPath += Objects.requireNonNull(nowPlaying).getPosterPath();
                        Glide.with(currImageView).load(urlPath).placeholder(R.drawable.placeholder).into(currImageView);
                        titleTv.setText(nowPlaying.getOriginalTitle());
                        voteTv.setText(String.valueOf(nowPlaying.getVoteCount()));
                        overviewTv.setText(nowPlaying.getOverview());
                }
            }
        } else onBackPressed();
    }
}