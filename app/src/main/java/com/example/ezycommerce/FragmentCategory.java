package com.example.ezycommerce;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
//        String category = getArguments().getString("category");
        rvProduct.setLayoutManager(new LinearLayoutManager(view.getContext()));


//        if (getActivity()!=null){
//            Adapter = new ProductListAdapter();
//        }

        Retrofit retrofit = ApiClient.getRetrofit();
        AmazonService service = retrofit.create(AmazonService.class);
//        AmazonService service = ApiClient.getRetrofit().create(AmazonService.class);
        Call<ProductResponse> call = service.getProduct("2201741385", "VanessaLouise");
        Toast.makeText(view.getContext(), String.valueOf(service.getProduct("2201741385", "VanessaLouise")), Toast.LENGTH_LONG).show();
        

        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                Toast.makeText(view.getContext(), "dalam onresponse", Toast.LENGTH_LONG).show();
                ArrayList<Product> listProduct = response.body().products;
                Adapter = new ProductListAdapter();
                Adapter.setListProduct(listProduct);
                rvProduct.setAdapter(Adapter);



                final int size = listProduct.size();
                for (int i = 0; i < size; i++)
                {
                    if(listProduct.get(i).getCategory() == "accessories"){
                        listAccesorries.add(listProduct.get(i));
                        String info = "list accesories" ;
                        Toast.makeText(getActivity(), info, Toast.LENGTH_LONG).show();
                    } else if(listProduct.get(i).getCategory() == "business"){
                        listBusiness.add(listProduct.get(i));
                    } else if(listProduct.get(i).getCategory() == "cookbooks"){
                        listCookBook.add(listProduct.get(i));
                    } else if(listProduct.get(i).getCategory() == "mystery"){
                        listMystery.add(listProduct.get(i));
                    } else if(listProduct.get(i).getCategory() == "scifi"){
                        listScifi.add(listProduct.get(i));
                    }

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

//        if(category == "accessories"){
//            Adapter.setListProduct(listAccesorries);
//            Adapter = new ProductListAdapter(view.getContext(), listAccesorries );
//        } else if(category == "business"){
//            Adapter.setListProduct(listBusiness);
//            Adapter = new ProductListAdapter(view.getContext(), listBusiness );
//        } else if(category == "cookbook"){
//            Adapter.setListProduct(listCookBook);
//            Adapter = new ProductListAdapter(view.getContext(), listCookBook );
//        } else if(category == "mystery"){
//            Adapter.setListProduct(listMystery);
//            Adapter = new ProductListAdapter(view.getContext(), listMystery );
//        } else if(category == "scifi"){
//            Adapter.setListProduct(listScifi);
//            Adapter = new ProductListAdapter(view.getContext(), listScifi );
//        }

//        Adapter = new ProductListAdapter(view.getContext(), //);
//        rvProduct.setAdapter(Adapter);



        return view;
    }

}