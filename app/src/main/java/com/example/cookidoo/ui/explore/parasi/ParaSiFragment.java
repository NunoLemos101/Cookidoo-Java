package com.example.cookidoo.ui.explore.parasi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cookidoo.databinding.FragmentParasiBinding;

public class ParaSiFragment extends Fragment {

    private FragmentParasiBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ParaSiViewModel paraSiViewModel = new ViewModelProvider(this).get(ParaSiViewModel.class);

        binding = FragmentParasiBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textParasi;
        paraSiViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
