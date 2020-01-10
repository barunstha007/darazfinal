package com.example.darazfinal.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.darazfinal.R;
import com.example.darazfinal.Url.Url;
import com.example.darazfinal.adapter.ItemAdapter;
import com.example.darazfinal.adapter.ViewPageAdapter;
import com.example.darazfinal.api.ProductApi;
import com.example.darazfinal.model.Item;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    ViewPager viewPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager = root.findViewById(R.id.viewPager);
        recyclerView=root.findViewById(R.id.recyclerView);
        ViewPageAdapter viewPagerAdapter = new ViewPageAdapter(getActivity());

        viewPager.setAdapter(viewPagerAdapter);
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 5000, 3000);

        getProduct();
        return root;
    }
    private void getProduct(){
        ProductApi productApi= Url.getInstance().create(ProductApi.class);
        Call<List<Item>> listCall=productApi.getProduct();
        listCall.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(getContext(), "Toast " + response.code(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    ItemAdapter itemAdapter = new ItemAdapter(getActivity(), response.body());
                    recyclerView.setAdapter(itemAdapter);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                    itemAdapter.notifyDataSetChanged();
                }
            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Toast.makeText(getActivity(), "Error" +t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}