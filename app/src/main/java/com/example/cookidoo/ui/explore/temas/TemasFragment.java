package com.example.cookidoo.ui.explore.temas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cookidoo.databinding.FragmentTemasBinding;

public class TemasFragment extends Fragment {

    private FragmentTemasBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TemasViewModel temasViewModel =
                new ViewModelProvider(this).get(TemasViewModel.class);

        binding = FragmentTemasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textTemas;
        temasViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}