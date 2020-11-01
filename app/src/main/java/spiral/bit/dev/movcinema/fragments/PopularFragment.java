package spiral.bit.dev.movcinema.fragments;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
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
import spiral.bit.dev.movcinema.adapters.PopularAdapter;
import spiral.bit.dev.movcinema.models.PopularResult;
import spiral.bit.dev.movcinema.services.MovieService;
import spiral.bit.dev.movcinema.services.RetroInst;

public class PopularFragment extends Fragment {

    private ArrayList<PopularResult> popularArrayList;
    private RecyclerView recycler;
    private SwipeRefreshLayout swipe;
    private Animation animRecycler;
    private SharedPreferences prefAnim;
    private SharedPreferences.Editor editorAnim;
    private CompositeDisposable compositeDisposable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular, container, false);
        compositeDisposable = new CompositeDisposable();
        requestPopularMovies();
        prefAnim = Objects.requireNonNull(getContext()).getSharedPreferences("anim_pref", 0);
        editorAnim = prefAnim.edit();
        if (!prefAnim.getBoolean("isAnimated", false)) {
            animRecycler = AnimationUtils.loadAnimation(getContext(), R.anim.anim_recycler);
            Animation animLabel = AnimationUtils.loadAnimation(getContext(), R.anim.anim_label);
            TextView label = view.findViewById(R.id.label_tv);
            label.startAnimation(animLabel);
            editorAnim.putBoolean("isAnimated", true);
            editorAnim.apply();
        }
        swipe = view.findViewById(R.id.swipe_layout);
        swipe.setColorSchemeColors(Color.GREEN);
        swipe.setOnRefreshListener(this::requestPopularMovies);
        return view;
    }

    @EverythingIsNonNull
    private void requestPopularMovies() {
        MovieService service = RetroInst.getService();
        compositeDisposable.add(service.getAllPopularMovies(getString(R.string.api_key))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(popular -> {
                    if (popular != null && popular.getResults() != null) {
                        popularArrayList = (ArrayList<PopularResult>) popular.getResults();
                        initRecycler();
                        swipe.setRefreshing(false);
                        if (!prefAnim.getBoolean("isAnimated", false)) {
                            recycler.startAnimation(animRecycler);
                            editorAnim.putBoolean("isAnimated", true);
                            editorAnim.apply();
                        }
                    } else
                        Toast.makeText(getContext(), getString(R.string.error_response_toast), Toast.LENGTH_SHORT).show();
                }));
    }

    private void initRecycler() {
        recycler = Objects.requireNonNull(getView()).findViewById(R.id.popular_recycler);
        recycler.setItemAnimator(new DefaultItemAnimator());
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recycler.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        } else
            recycler.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        PopularAdapter adapter = new PopularAdapter(getContext(), popularArrayList);
        recycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}