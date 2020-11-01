package spiral.bit.dev.movcinema.fragments;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.internal.EverythingIsNonNull;
import spiral.bit.dev.movcinema.R;
import spiral.bit.dev.movcinema.adapters.NowPlayingAdapter;
import spiral.bit.dev.movcinema.models.NowPlayingResult;
import spiral.bit.dev.movcinema.services.MovieService;
import spiral.bit.dev.movcinema.services.RetroInst;

public class NowPlayingFragment extends Fragment {

    private ArrayList<NowPlayingResult> nowPlayingArrayList;
    private SwipeRefreshLayout swipe;
    private CompositeDisposable compositeDisposable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);
        compositeDisposable = new CompositeDisposable();
        requestNowPlayingMovies();
        swipe = view.findViewById(R.id.swipe_layout);
        swipe.setColorSchemeColors(Color.GREEN);
        swipe.setOnRefreshListener(this::requestNowPlayingMovies);
        return view;
    }

    @EverythingIsNonNull
    private void requestNowPlayingMovies() {
        MovieService service = RetroInst.getService();
        compositeDisposable.add(service.getAllNowPlayingMovies(getString(R.string.api_key))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(nowPlaying -> {
                    if (nowPlaying != null && nowPlaying.getResults() != null) {
                        nowPlayingArrayList = (ArrayList<NowPlayingResult>) nowPlaying.getResults();
                        initRecycler();
                        swipe.setRefreshing(false);
                    } else
                        Toast.makeText(getContext(), getString(R.string.error_response_toast), Toast.LENGTH_SHORT).show();
                }));
    }

    private void initRecycler() {
        RecyclerView recycler = Objects.requireNonNull(getView()).findViewById(R.id.now_playing_recycler);
        recycler.setItemAnimator(new DefaultItemAnimator());
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recycler.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        } else
            recycler.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        NowPlayingAdapter adapter = new NowPlayingAdapter(getContext(), nowPlayingArrayList);
        recycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}