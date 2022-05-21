package com.example.cookidoo.ui.explore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.cookidoo.R;
import com.example.cookidoo.ui.explore.destaque.DestaqueFragment;
import com.example.cookidoo.ui.explore.destaque.MockData;
import com.example.cookidoo.ui.explore.parasi.ParaSiFragment;
import com.example.cookidoo.ui.explore.temas.TemasFragment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;

public class ExploreFragment extends Fragment {

    private final int NAVIGATION_DESTAQUE_ID = R.id.navigation_destaque;
    private final int NAVIGATION_PARASI_ID = R.id.navigation_parasi;
    private final int NAVIGATION_TEMAS_ID = R.id.navigation_temas;
    private float INITIAL_NAV_BAR_Y;
    private float PROFILE_HEADER_HEIGHT;
    BottomNavigationView navegation;
    BottomNavigationView navegation2;
    View decorView;
    View smallSearchBar;
    ScrollView sv;
    View view;

    public Object[] loadJSONFromAsset() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        Object[] mystuff = mapper.readValue( MockData.Receipt.readFromJson(getActivity()), Object[].class );
        return mystuff;
    }

    private void setVisibleHeader() {
        navegation2.setVisibility(View.VISIBLE);
        smallSearchBar.setVisibility(View.VISIBLE);
        view.findViewById(R.id.profileHeaderCL).setBackgroundColor(getResources().getColor(R.color.white));
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    private void setInvisibleHeader() {
        navegation.setVisibility(View.VISIBLE);
        navegation2.setVisibility(View.GONE);
        smallSearchBar.setVisibility(View.GONE);
        view.findViewById(R.id.profileHeaderCL).setBackgroundColor(getResources().getColor(android.R.color.transparent));
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    private final ViewTreeObserver.OnScrollChangedListener overrideFunc = new ViewTreeObserver.OnScrollChangedListener() {
        @Override
        public void onScrollChanged() {
            if (sv.getScrollY() > INITIAL_NAV_BAR_Y - PROFILE_HEADER_HEIGHT) {
                setVisibleHeader();
            } else {
                setInvisibleHeader();
            }
        }
    };

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = item -> {
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        switch (item.getItemId()) {
            case NAVIGATION_DESTAQUE_ID:
                navegation.getMenu().getItem(0).setChecked(true);
                navegation2.getMenu().getItem(0).setChecked(true);
                ft.replace(R.id.nav_host_fragment_explore, new DestaqueFragment()).commit();
                return true;
            case NAVIGATION_PARASI_ID:
                navegation.getMenu().getItem(1).setChecked(true);
                navegation2.getMenu().getItem(1).setChecked(true);
                ft.replace(R.id.nav_host_fragment_explore, new ParaSiFragment()).commit();
                return true;
            case NAVIGATION_TEMAS_ID:
                navegation.getMenu().getItem(2).setChecked(true);
                navegation2.getMenu().getItem(2).setChecked(true);
                ft.replace(R.id.nav_host_fragment_explore, new TemasFragment()).commit();
                return true;
        }
        return false;
    };

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {
            System.out.println(loadJSONFromAsset()[0].toString());
            Object data = loadJSONFromAsset()[0];
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(data);
            Map mystuff = mapper.readValue( json, Map.class );

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        decorView = getActivity().getWindow().getDecorView();
        view = inflater.inflate(R.layout.fragment_explore, container, false);
        sv = view.findViewById(R.id.fragment_explore_scrollview);
        navegation = (BottomNavigationView) view.findViewById(R.id.explore_nav_view);
        navegation2 = (BottomNavigationView) view.findViewById(R.id.explore_nav_view2);
        smallSearchBar = view.findViewById(R.id.constraintLayout32);
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        smallSearchBar.setVisibility(View.GONE);
        navegation2.setVisibility(View.GONE);

        navegation.setOnItemSelectedListener(mOnNavigationItemSelectedListener);
        navegation2.setOnItemSelectedListener(mOnNavigationItemSelectedListener);

        view.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        // Layout has happened here.
                        PROFILE_HEADER_HEIGHT = view.findViewById(R.id.profileHeaderCL).getHeight();
                        INITIAL_NAV_BAR_Y = navegation.getY();
                        // Don't forget to remove your listener when you are done with it.
                        view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
        // sets initial Fragment

        if (savedInstanceState == null) {
            getChildFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_explore, new DestaqueFragment()).commit();
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (sv.getScrollY() > INITIAL_NAV_BAR_Y - PROFILE_HEADER_HEIGHT) {
            setVisibleHeader();
        } else {
            setInvisibleHeader();
        }
        sv.getViewTreeObserver().addOnScrollChangedListener(overrideFunc);
    }

    @Override
    public void onPause() {
        super.onPause();
        sv.getViewTreeObserver().removeOnScrollChangedListener(overrideFunc);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}