package com.example.ezycommerce;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShoppingCart#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShoppingCart extends Fragment {

    TextView subtotal, total, taxes, shipping;
    RecyclerView rvCart;
    CartListAdapter Adapter;
    DBManager dbManager;
    ArrayList<Product> productList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ShoppingCart() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShoppingCart.
     */
    // TODO: Rename and change types and number of parameters
    public static ShoppingCart newInstance(String param1, String param2) {
        ShoppingCart fragment = new ShoppingCart();
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
        final View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        rvCart = view.findViewById(R.id.rvCart);
        rvCart.setNestedScrollingEnabled(false);
        rvCart.setLayoutManager(new LinearLayoutManager(view.getContext()));

        subtotal = view.findViewById(R.id.subTotal);
        shipping = view.findViewById(R.id.shipping);
        total = view.findViewById(R.id.total);
        taxes = view.findViewById(R.id.taxes);


        productList = new ArrayList<>();
        productList = dbManager.getCartRecords();

        Adapter = new CartListAdapter(view.getContext(), productList);
        rvCart.setAdapter(Adapter);

        int temp_total = 0;
        if(productList!=null){
            for (int i=0;i<productList.size();i++){
                temp_total += productList.get(i).getPrice() * productList.get(i).getQuantity();
            }
        }else {
            temp_total = 0;
        }
        subtotal.setText(String.valueOf(temp_total));

        double total_taxes = 0f;
        if(temp_total>0){
            total_taxes = temp_total - (temp_total*0.5);
        }
        taxes.setText(String.valueOf(total_taxes));

        int shipp = 10;
        shipping.setText(String.valueOf(shipp));

        double total1 = temp_total + total_taxes + shipp;
        total.setText(String.valueOf(total1));

        return view;
    }
}