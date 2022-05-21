package com.example.cookidoo.ui.explore.destaque;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookidoo.R;
import com.example.cookidoo.ReceiptDetailActivity;
import com.example.cookidoo.databinding.FragmentDestaqueBinding;
import com.example.cookidoo.ui.explore.destaque.DestaqueViewModel;

import java.util.ArrayList;

public class DestaqueFragment extends Fragment {

    private FragmentDestaqueBinding binding;
    private Context context;

    RecyclerView rv;
    RecyclerView rv2;

    ArrayList<MockData.Receipt> dataSource = MockData.getReceipts();
    ArrayList<MockData.Receipt> dataSource2 = MockData.getReceipts();

    LinearLayoutManager linearLayoutManager;
    LinearLayoutManager linearLayoutManager2;

    MyRvAdapter myRvAdapter;
    MyRvAdapter myRvAdapter2;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DestaqueViewModel destaqueViewModel = new ViewModelProvider(this).get(DestaqueViewModel.class);

        binding = FragmentDestaqueBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.context = getContext();

        rv = view.findViewById(R.id.horizontalRV);
        rv2 = view.findViewById(R.id.horizontalRV2);

        myRvAdapter = new MyRvAdapter(dataSource);
        myRvAdapter2 = new MyRvAdapter(dataSource2);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManager2 = new LinearLayoutManager(getActivity());
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);

        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(myRvAdapter);
        rv2.setLayoutManager(linearLayoutManager2);
        rv2.setAdapter(myRvAdapter2);

        View v = view.findViewById(R.id.view2);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("XX");

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    class MyRvAdapter extends RecyclerView.Adapter<MyRvAdapter.MyHolder> {

        ArrayList<MockData.Receipt> data;

        private void setMargins (View v, int l, int t, int r, int b) {
            if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
                p.setMargins(l, t, r, b);
                v.requestLayout();
            }
        }

        public MyRvAdapter(ArrayList<MockData.Receipt> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.rv_item, parent, false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            holder.tvTitle.setText(data.get(position).name);
            holder.image.setImageResource(data.get(position).image);
            holder.setItemOnClickListener(position);

            // special margins for first and last elements
            if (position == 0) {
                setMargins(holder.card, 40, 0, 0, 0);
            } else if (position == data.size() - 1){
                setMargins(holder.card, 20, 0, 40, 0);
            } else {
                setMargins(holder.card, 20, 0, 0, 0);
            }

        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class MyHolder extends RecyclerView.ViewHolder {
            TextView tvTitle;
            CardView card;
            ImageView image;
            View iv;

            public MyHolder(@NonNull View itemView) {
                super(itemView);
                iv = itemView;
                tvTitle = itemView.findViewById(R.id.tvTitle);
                card = itemView.findViewById(R.id.cardView);
                image = itemView.findViewById(R.id.cardReceiptImage);
            }

            public void setItemOnClickListener(int position) {
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), ReceiptDetailActivity.class);
                        System.out.println("JSUSJCMSJM");
                        System.out.println(position);
                        intent.putExtra("receiptPosition", position);
                        startActivity(intent);
                    }
                });
            }
        }
    }

}
