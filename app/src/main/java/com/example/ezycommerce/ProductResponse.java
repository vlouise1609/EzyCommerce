package com.example.ezycommerce;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductResponse {

    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("nim")
    @Expose
    private String nim;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("productId")
    @Expose
    private Object productId;
    @SerializedName("credits")
    @Expose
    private String credits;
    @SerializedName("products")
    @Expose
    public ArrayList<Product> products = null;

    /**
     * No args constructor for use in serialization
     */
    public ProductResponse() {
    }

    /**
     * @param statusCode
     * @param nim
     * @param nama
     * @param productId
     * @param credits
     * @param products
     */
    public ProductResponse(Integer statusCode, String nim, String nama, Object productId, String credits, ArrayList<Product> products) {
        super();
        this.statusCode = statusCode;
        this.nim = nim;
        this.nama = nama;
        this.productId = productId;
        this.credits = credits;
        this.products = products;
    }
}