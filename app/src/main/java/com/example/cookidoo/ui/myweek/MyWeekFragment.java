package com.example.cookidoo.ui.myweek;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cookidoo.databinding.FragmentMyWeekBinding;

public class MyWeekFragment extends Fragment {

    private FragmentMyWeekBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MyWeekViewModel myWeekViewModel =
                new ViewModelProvider(this).get(MyWeekViewModel.class);

        binding = FragmentMyWeekBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textMyWeek;
        myWeekViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}