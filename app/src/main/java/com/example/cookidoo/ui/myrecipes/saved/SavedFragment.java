package com.example.cookidoo.ui.myrecipes.saved;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cookidoo.databinding.FragmentMyRecipesSavedBinding;
import com.example.cookidoo.ui.myrecipes.created.CreatedFragment;

public class SavedFragment extends Fragment {

    private FragmentMyRecipesSavedBinding binding;
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static SavedFragment newInstance(int index) {
        SavedFragment fragment = new SavedFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMyRecipesSavedBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}