package spiral.bit.dev.movcinema.services;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import spiral.bit.dev.movcinema.models.NowPlaying;
import spiral.bit.dev.movcinema.models.Popular;
import spiral.bit.dev.movcinema.models.Top;

public interface MovieService {

    @GET("movie/popular")
    Observable<Popular> getAllPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Observable<Top> getAllTopRatingMovies(@Query("api_key") String apiKey);

    @GET("movie/now_playing")
    Observable<NowPlaying> getAllNowPlayingMovies(@Query("api_key") String apiKey);
}
