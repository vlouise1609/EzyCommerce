package com.example.ezycommerce;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AmazonService {
    @GET("book/")
    Call<ProductResponse> getProduct(
            @Query(value="nim") String nim,
            @Query(value="nama") String nama
            //https://u73olh7vwg.execute-api.ap-northeast2.amazonaws.com/staging/book
//            ?nim=<nimMhs>&nama=<namaMhs>
    );

//    https://u73olh7vwg.execute-api.ap-northeast2.amazonaws.com/staging/book/{bookId}/
//            ?nim=<nimMhs>&nama=<namaMhs>

    @GET("book/{id}/")
    Call<ProductResponse> getProductDetail(
            @Path("id") long bookId,
            @Query(value="nim") String nim,
            @Query(value="nama") String name
    );
}
