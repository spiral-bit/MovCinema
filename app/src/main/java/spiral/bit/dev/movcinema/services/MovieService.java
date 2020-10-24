package spiral.bit.dev.movcinema.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import spiral.bit.dev.movcinema.models.NowPlaying;
import spiral.bit.dev.movcinema.models.Popular;
import spiral.bit.dev.movcinema.models.Top;

public interface MovieService {

    @GET("movie/popular")
    Call<Popular> getAllPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Call<Top> getAllTopRatingMovies(@Query("api_key") String apiKey);

    @GET("movie/now_playing")
    Call<NowPlaying> getAllNowPlayingMovies(@Query("api_key") String apiKey);
}
