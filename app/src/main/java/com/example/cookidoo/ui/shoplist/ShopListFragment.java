package com.example.cookidoo.ui.shoplist;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cookidoo.R;
import com.example.cookidoo.databinding.FragmentShopListBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class ShopListFragment extends Fragment {

    private FragmentShopListBinding binding;

    private final View.OnClickListener threeDotsIconClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext(), R.style.BottomModalStyleTheme);
            View bottomSheetView = LayoutInflater.from(getActivity().getApplicationContext()).inflate(
                    R.layout.shoplist_threedots_modal,
                    (LinearLayout)getActivity().findViewById(R.id.shoplistThreeDotsModal)
            );

            bottomSheetView.findViewById(R.id.shoplistModalCancelButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bottomSheetDialog.dismiss();
                }
            });

            bottomSheetDialog.setContentView(bottomSheetView);
            bottomSheetDialog.show();
        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        binding = FragmentShopListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Animation lAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.alpha);
        binding.threeDotsIcon.setOnClickListener(threeDotsIconClickListener);
        binding.shopListMainLayout.startAnimation(lAnimation);

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}