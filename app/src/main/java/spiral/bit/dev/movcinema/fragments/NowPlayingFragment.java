package spiral.bit.dev.movcinema.fragments;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import spiral.bit.dev.movcinema.R;
import spiral.bit.dev.movcinema.adapters.NowPlayingAdapter;
import spiral.bit.dev.movcinema.models.NowPlaying;
import spiral.bit.dev.movcinema.models.NowPlayingResult;
import spiral.bit.dev.movcinema.services.MovieService;
import spiral.bit.dev.movcinema.services.RetroInst;

public class NowPlayingFragment extends Fragment {

    private RecyclerView recycler;
    private NowPlayingAdapter adapter;
    private ArrayList<NowPlayingResult> nowPlayingArrayList;
    private SwipeRefreshLayout swipe;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);
        requestNowPlayingMovies();
        swipe = view.findViewById(R.id.swipe_layout);
        swipe.setColorSchemeColors(Color.GREEN);
        swipe.setOnRefreshListener(() -> requestNowPlayingMovies());
        return view;
    }

    private void requestNowPlayingMovies() {
        MovieService service = RetroInst.getService();
        Call<NowPlaying> nowPlayingCall = service.getAllNowPlayingMovies(getString(R.string.api_key));
        nowPlayingCall.enqueue(new Callback<NowPlaying>() {
            @Override
            public void onResponse(Call<NowPlaying> call, Response<NowPlaying> response) {
                NowPlaying nowPlaying = response.body();
                if (nowPlaying != null && nowPlaying.getResults() != null) {
                    nowPlayingArrayList = (ArrayList<NowPlayingResult>) nowPlaying.getResults();
                    initRecycler();
                    swipe.setRefreshing(false);
                } else {
                    Toast.makeText(getContext(), "null", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NowPlaying> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initRecycler() {
        recycler = getView().findViewById(R.id.now_playing_recycler);
        recycler.setItemAnimator(new DefaultItemAnimator());
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recycler.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        } else recycler.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        adapter = new NowPlayingAdapter(getContext(), nowPlayingArrayList);
        recycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}