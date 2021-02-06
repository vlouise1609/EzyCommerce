package com.example.ezycommerce;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCategory#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCategory extends Fragment {

    RecyclerView rvProduct;
    ProductListAdapter Adapter;
    ArrayList<Product> listAccesorries;
    ArrayList<Product> listBusiness;
    ArrayList<Product> listCookBook;
    ArrayList<Product> listMystery;
    ArrayList<Product> listScifi;
    Button Acc, Buss, Coo, Mys, Sci;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String param1;
    public String category;

    public FragmentCategory() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment FragmentCategory.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentCategory newInstance(String param1) {
        FragmentCategory fragment = new FragmentCategory();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getString(ARG_PARAM1);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_category, container, false);
        rvProduct = view.findViewById(R.id.rvProduct);
        rvProduct.setNestedScrollingEnabled(false);
        rvProduct.setLayoutManager(new LinearLayoutManager(view.getContext()));

        listAccesorries = new ArrayList<>();
        listBusiness= new ArrayList<>();
        listCookBook = new ArrayList<>();
        listMystery = new ArrayList<>();
        listMystery = new ArrayList<>();

        Retrofit retrofit = ApiClient.getRetrofit();
        AmazonService service = retrofit.create(AmazonService.class);
        Call<ProductResponse> call = service.getProduct("2201741385", "VanessaLouise");

        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                Toast.makeText(view.getContext(), "dalam onresponse", Toast.LENGTH_LONG).show();
                ArrayList<Product> listProduct = response.body().products;

                final int size = listProduct.size();
                for (int i = 0; i < size; i++)
                {
                    if(listProduct.get(i).getCategory().equals("accessories")){
                        listAccesorries.add(listProduct.get(i));
                    } else if(listProduct.get(i).getCategory().equals("business")){
                        listBusiness.add(listProduct.get(i));
                    } else if(listProduct.get(i).getCategory().equals("cookbooks")){
                        listCookBook.add(listProduct.get(i));
                    } else if(listProduct.get(i).getCategory().equals("mystery")){
                        listMystery.add(listProduct.get(i));
                    } else if(listProduct.get(i).getCategory().equals("scifi")){
                        listScifi.add(listProduct.get(i));
                    }

                }

                Adapter = new ProductListAdapter(view.getContext(), listProduct);
                rvProduct.setAdapter(Adapter);

            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Toast.makeText(view.getContext(), "response failed", Toast.LENGTH_LONG).show();
                call.cancel();
                t.getCause();
                Toast.makeText(view.getContext(),  String.valueOf(t.getCause()), Toast.LENGTH_LONG).show();
            }
        });

        Acc = view.findViewById(R.id.accessories);
        Acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Adapter.setListProduct(listAccesorries);
            }
        });

        Buss = view.findViewById(R.id.business);
        Buss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Adapter.setListProduct(listBusiness);
            }
        });

        Coo = view.findViewById(R.id.cookbook);
        Coo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Adapter.setListProduct(listCookBook);
            }
        });

        Mys = view.findViewById(R.id.mystery);
        Mys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Adapter.setListProduct(listMystery);
            }
        });

        Sci = view.findViewById(R.id.scifi);
        Sci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Adapter.setListProduct(listScifi);
            }
        });


        return view;
    }

}