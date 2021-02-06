package com.example.ezycommerce;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AmazonService {
    @GET("book/")
    Call<ProductResponse> getProduct(
            @Query(value="nim") String nim,
            @Query(value="nama") String nama
            //https://u73olh7vwg.execute-api.ap-northeast2.amazonaws.com/staging/book
//            ?nim=<nimMhs>&nama=<namaMhs>
    );


//    @GET("book/{name}")
//    Call<List<Commit>> getCommitsByName(@Path("name") String name);
}
