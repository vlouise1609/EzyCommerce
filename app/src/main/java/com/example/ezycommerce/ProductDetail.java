package com.example.ezycommerce;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductDetail extends Fragment {

    TextView productName, productPrice, productDesc;
    ImageView productImage, starImg;
    Button btnBuy;
    long id;
    DBManager dbManager;
    ArrayList<Product> listProduct;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    public ProductDetail() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_product_detail, container, false);
        productImage = view.findViewById(R.id.productDetailImage);
        productName = view.findViewById(R.id.productDetailName);
        productPrice = view.findViewById(R.id.productDetailPrice);
        productDesc = view.findViewById(R.id.productDetailDescription);
        starImg = view.findViewById(R.id.starImg);

        if(getArguments()!=null) {
            id = getArguments().getLong("productId");

            Toast.makeText(view.getContext(), String.valueOf(id), Toast.LENGTH_LONG).show();

            Retrofit retrofit = ApiClient.getRetrofit();
            AmazonService service = retrofit.create(AmazonService.class);
            Call<ProductResponse> call = service.getProductDetail(id, "2201741385", "VanessaLouise");

            call.enqueue(new Callback<ProductResponse>() {
                @Override
                public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                    listProduct = response.body().products;
                    productName.setText(listProduct.get(0).getName());
                    productPrice.setText(String.valueOf(listProduct.get(0).getPrice()));
                    productDesc.setText(listProduct.get(0).getDescription());
                    Picasso.with(view.getContext()).load(listProduct.get(0).getImg()).into(productImage);

                    for (int i = 0; i < 3; i++) {
                        starImg.setImageResource(R.drawable.ic_baseline_star_24);
                    }


                }

                @Override
                public void onFailure(Call<ProductResponse> call, Throwable t) {
                    Toast.makeText(view.getContext(), "response failed", Toast.LENGTH_LONG).show();
                    call.cancel();
                    t.getCause();
                    Toast.makeText(view.getContext(),  String.valueOf(t.getCause()), Toast.LENGTH_LONG).show();
                }
            });


        }

        btnBuy = view.findViewById(R.id.buyBtn);
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listProduct.get(0).setQuantity(1);
                dbManager = new DBManager();
                dbManager.addCartRecords(listProduct.get(0));

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                ShoppingCart shoppingCart = new ShoppingCart();

                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, shoppingCart)
                        .addToBackStack(null)
                        .commit();

            }
        });

        return view;
    }
}