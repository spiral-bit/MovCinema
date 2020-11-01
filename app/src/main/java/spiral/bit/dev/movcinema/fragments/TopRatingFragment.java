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
import spiral.bit.dev.movcinema.adapters.TopAdapter;
import spiral.bit.dev.movcinema.models.TopResult;
import spiral.bit.dev.movcinema.services.MovieService;
import spiral.bit.dev.movcinema.services.RetroInst;

public class TopRatingFragment extends Fragment {

    private ArrayList<TopResult> topArrayList;
    private SwipeRefreshLayout swipe;
    private CompositeDisposable compositeDisposable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_rating, container, false);
        compositeDisposable = new CompositeDisposable();
        requestTopRatingMovies();
        swipe = view.findViewById(R.id.swipe_layout);
        swipe.setColorSchemeColors(Color.GREEN);
        swipe.setOnRefreshListener(this::requestTopRatingMovies);
        return view;
    }

    @EverythingIsNonNull
    private void requestTopRatingMovies() {
        MovieService service = RetroInst.getService();
        compositeDisposable.add(service.getAllTopRatingMovies(getString(R.string.api_key))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(top -> {
                    if (top != null && top.getResults() != null) {
                        topArrayList = (ArrayList<TopResult>) top.getResults();
                        initRecycler();
                        swipe.setRefreshing(false);
                    } else
                        Toast.makeText(getContext(), getString(R.string.error_response_toast), Toast.LENGTH_SHORT).show();
                }));
    }

    private void initRecycler() {
        RecyclerView recycler = Objects.requireNonNull(getView()).findViewById(R.id.top_recycler);
        recycler.setItemAnimator(new DefaultItemAnimator());
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recycler.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        } else
            recycler.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        TopAdapter adapter = new TopAdapter(getContext(), topArrayList);
        recycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}