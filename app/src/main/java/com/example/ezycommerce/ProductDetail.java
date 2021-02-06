package com.example.ezycommerce;

import android.os.Bundle;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductDetail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductDetail extends Fragment {

    TextView productName, productPrice, productDesc;
    ImageView productImage, starImg;
    Button btnBuy;
    long id;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProductDetail() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductDetail.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductDetail newInstance(String param1, String param2) {
        ProductDetail fragment = new ProductDetail();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
                    ArrayList<Product> listProduct = response.body().products;
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


//        btnBuy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        return view;
    }
}