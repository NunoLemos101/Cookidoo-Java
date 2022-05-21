package com.example.cookidoo.ui.myrecipes.created;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cookidoo.databinding.FragmentMyRecipesCreatedBinding;
import com.example.cookidoo.ui.myrecipes.all.AllFragment;

public class CreatedFragment extends Fragment {

    private FragmentMyRecipesCreatedBinding binding;
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static CreatedFragment newInstance(int index) {
        CreatedFragment fragment = new CreatedFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMyRecipesCreatedBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}