package com.example.cookidoo.ui.explore.destaque;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.cookidoo.R;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public interface MockData {

    final class Receipt {

        String name;
        String dificulty;
        String prepTime;
        String totalTime;
        String portions;
        int rating;
        int ratingCount;
        int image;

        public Receipt (String name, String dificulty, String prepTime, String totalTime, String portions, int image, int rating, int ratingCount) {
            this.name = name;
            this.dificulty = dificulty;
            this.prepTime = prepTime;
            this.totalTime = totalTime;
            this.portions = portions;
            this.image = image;
            this.rating = rating;
            this.ratingCount = ratingCount;
        }

        public String getName() { return name; }

        public String getDificulty() { return dificulty; }

        public String getPrepTime() { return prepTime; }

        public String getTotalTime() { return totalTime; }

        public String getPortions() { return portions; }

        public int getImage() { return image; }

        public int getRating() { return rating; }

        @NonNull
        public String getLabeledRating() { return String.format("%s,0", rating); }

        @NonNull
        public String getLabeledRatingCount() { return String.format("(%s avaliações)", ratingCount); }

        public static String readFromJson(FragmentActivity activity) {
            String json = null;
            try {
                InputStream is = activity.getAssets().open("data.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                json = new String(buffer, "UTF-8");
            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }
            return json;
        }
    }


    public static ArrayList<Receipt> getReceipts() {
        ArrayList<Receipt> receipts = new ArrayList<Receipt>();
        receipts.add(new Receipt("Muesli de cereais", "fácil", "20m", "6h 20m", "2 doses", R.drawable.receipt_1,5, 31 ));
        receipts.add(new Receipt("Leitelho", "fácil", "5m", "10m", "2 doses", R.drawable.receipt_2, 3, 13));
        receipts.add(new Receipt("Morango e Baunilha", "fácil", "10m", "25m", "4 frascos", R.drawable.receipt_3, 0, 2));
        receipts.add(new Receipt("Geleia de maçã", "fácil", "25m", "4h", "4 frascos", R.drawable.receipt_4, 1, 320));
        receipts.add(new Receipt("Tagliatelle", "fácil", "10m", "25m", "4 doses", R.drawable.receipt_5, 3, 560));
        receipts.add(new Receipt("Entrecosto agridoce", "fácil", "10m", "40m", "4 doses", R.drawable.receipt_6, 2, 94));
        receipts.add(new Receipt("Panquecas", "médio", "35m", "40m", "12 unidades", R.drawable.receipt_7, 4, 1139));
        return  receipts;
    }
}