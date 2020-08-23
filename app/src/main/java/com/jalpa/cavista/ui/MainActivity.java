package com.jalpa.cavista.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.jalpa.cavista.R;
import com.jalpa.cavista.model.ApiResponse;
import com.jalpa.cavista.network.Debouncer;
import com.jalpa.cavista.network.NetworkManager;
import com.jalpa.cavista.viewmodel.ImageViewModel;

public class MainActivity extends AppCompatActivity implements Observer<ApiResponse>{
    private RecyclerView recyclerView;
    private ImageViewModel viewModelMovieOutputs;
    private ImageGridAdapter imageGridAdapter;
    private SearchView searchView;
    private int searchNumber;
    private ProgressBar progress;
    private boolean isLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        progress = findViewById(R.id.progressBarLoading);

        recyclerView = findViewById(R.id.grid_view);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        searchView = findViewById(R.id.searchVbar);
        searchView.setQueryHint(getString(R.string.hint_text));
        searchView.setIconifiedByDefault(false);
        searchView.requestFocus();
        searchView.setRevealOnFocusHint(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Debouncer debounce = new Debouncer();
                debounce.debounce(1, new Runnable() {
                    @Override
                    public void run() {
                        if (NetworkManager.isNetworkAvailable(MainActivity.this)) {
                            searchNumber = 1;
                            viewModelMovieOutputs = new ViewModelProvider((ViewModelStoreOwner) MainActivity.this).get(ImageViewModel.class);
                            viewModelMovieOutputs.getFetchImages(searchNumber, s).observe((LifecycleOwner) MainActivity.this, MainActivity.this);
                        }
                        else{
                            showNetMessage();
                        }

                    }
                });
                return false;
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                if (layoutManager != null && layoutManager.findLastCompletelyVisibleItemPosition() == recyclerView.getAdapter().getItemCount() - 1) {
                    if (NetworkManager.isNetworkAvailable(MainActivity.this)) {
                        progress.setVisibility(View.VISIBLE);
                        loadMore();
                    }else{
                        showNetMessage();
                    }
                }
            }
        });
    }

    private void showNetMessage() {
        Snackbar mySnackbar = Snackbar.make(findViewById(R.id.constraint_layout),
                R.string.poor_conn, Snackbar.LENGTH_LONG);
        mySnackbar.show();
    }

    @Override
    public void onChanged(ApiResponse imageOutputs) {
       if(imageOutputs.getError()== null) {
           if (imageOutputs.getPosts().size() > 0) {
               findViewById(R.id.empty_msg).setVisibility(View.GONE);
           } else {
               findViewById(R.id.empty_msg).setVisibility(View.VISIBLE);
           }

           if (searchNumber == 1) {
               imageGridAdapter = new ImageGridAdapter(imageOutputs.getPosts());
               recyclerView.setAdapter(imageGridAdapter);
           } else {
               Log.e("response","added"+imageOutputs.getPosts().size() +" old size"+ recyclerView.getAdapter().getItemCount());
               progress.setVisibility(View.GONE);
               ((ImageGridAdapter) recyclerView.getAdapter()).addItems(imageOutputs.getPosts());
           }
           isLoading = false;
       }else{
           Toast.makeText(MainActivity.this,"Error fetching response",Toast.LENGTH_LONG).show();
       }
    }

    private void loadMore() {
        if (!isLoading) {
            viewModelMovieOutputs = new ViewModelProvider((ViewModelStoreOwner) MainActivity.this).get(ImageViewModel.class);
            viewModelMovieOutputs.getFetchImages(++searchNumber, searchView.getQuery().toString()).observe((LifecycleOwner) MainActivity.this, MainActivity.this);
            isLoading = true;
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ((GridLayoutManager) recyclerView.getLayoutManager()).setSpanCount(4);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            ((GridLayoutManager) recyclerView.getLayoutManager()).setSpanCount(2);
        }
        super.onConfigurationChanged(newConfig);
    }
}
