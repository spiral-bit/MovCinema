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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        RoundedImageView currImageView = findViewById(R.id.image_of_curr_movie);
        TextView titleTv = findViewById(R.id.title_curr_movie_tv);
        TextView popularityTv = findViewById(R.id.popular_movie_tv);
        TextView overviewTv = findViewById(R.id.overview_curr_movie_tv);
        TextView dateReleaseTv = findViewById(R.id.release_date_tv);
        TextView adultTv = findViewById(R.id.adult_tv);
        TextView langTv = findViewById(R.id.lang_tv);
        Intent intent = getIntent();
        String urlPath = "https://image.tmdb.org/t/p/w500/";
        if (intent != null && intent.hasExtra("movObject") && intent.hasExtra("typeMovie")) {
            String typeOfMovie = intent.getStringExtra("typeMovie");
            if (typeOfMovie != null) {
                switch (typeOfMovie) {
                    case "popular":
                        PopularResult popularResult = intent.getParcelableExtra("movObject");
                        urlPath += Objects.requireNonNull(popularResult).getPosterPath();
                        Glide.with(currImageView).load(urlPath).placeholder(R.drawable.placeholder).into(currImageView);
                        dateReleaseTv.setText(popularResult.getReleaseDate());
                        titleTv.setText(popularResult.getOriginalTitle());
                        popularityTv.setText(String.valueOf(popularResult.getPopularity()));
                        dateReleaseTv.setText(popularResult.getReleaseDate());
                        overviewTv.setText(popularResult.getOverview());
                        adultTv.setText(popularResult.getAdult() ? "Да" : "Нет");
                        langTv.setText(popularResult.getOriginalLanguage());
                        return;
                    case "topRating":
                        TopResult topResult = intent.getParcelableExtra("movObject");
                        urlPath += Objects.requireNonNull(topResult).getPosterPath();
                        Glide.with(currImageView).load(urlPath).placeholder(R.drawable.placeholder).into(currImageView);
                        titleTv.setText(topResult.getOriginalTitle());
                        popularityTv.setText(String.valueOf(topResult.getPopularity()));
                        overviewTv.setText(topResult.getOverview());
                        dateReleaseTv.setText(topResult.getReleaseDate());
                        adultTv.setText(topResult.getAdult() ? "Да" : "Нет");
                        langTv.setText(topResult.getOriginalLanguage());
                        return;
                    case "nowPlaying":
                        NowPlayingResult nowPlaying = intent.getParcelableExtra("movObject");
                        urlPath += Objects.requireNonNull(nowPlaying).getPosterPath();
                        Glide.with(currImageView).load(urlPath).placeholder(R.drawable.placeholder).into(currImageView);
                        titleTv.setText(nowPlaying.getOriginalTitle());
                        popularityTv.setText(String.valueOf(nowPlaying.getPopularity()));
                        overviewTv.setText(nowPlaying.getOverview());
                        dateReleaseTv.setText(nowPlaying.getReleaseDate());
                        adultTv.setText(nowPlaying.getAdult() ? "Да" : "Нет");
                        langTv.setText(nowPlaying.getOriginalLanguage());
                    default:
                        onBackPressed();
                }
            }
        } else onBackPressed();
    }
}