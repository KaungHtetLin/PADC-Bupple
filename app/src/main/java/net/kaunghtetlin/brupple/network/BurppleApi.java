package net.kaunghtetlin.brupple.network;

import net.kaunghtetlin.brupple.network.responses.GetFeaturedResponse;
import net.kaunghtetlin.brupple.network.responses.GetGuidesResponse;
import net.kaunghtetlin.brupple.network.responses.GetPromotionsResponse;
import net.kaunghtetlin.brupple.utils.AppConstants;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Kaung Htet Lin on 1/18/2018.
 */

public interface BurppleApi {

  /*  @FormUrlEncoded
    @POST("v1/getPopularMovies.php")
    Call<GetFeaturedResponse> loadFeatured(
            @Field("access_token") String accessToken,
            @Field("page") int pageIndex);
*/

    @FormUrlEncoded
    @POST(AppConstants.API_GET_FEATURED)
    Call<GetFeaturedResponse> loadFeatured(
            @Field(AppConstants.PARAM_ACCESS_TOKEN) String accessToken,
            @Field("page") int pageIndex);

    @FormUrlEncoded
    @POST(AppConstants.API_GET_GUIDES)
    Call<GetGuidesResponse> loadGuides(
            @Field(AppConstants.PARAM_ACCESS_TOKEN) String accessToken,
            @Field("page") int pageIndex);

    @FormUrlEncoded
    @POST(AppConstants.API_GET_PROMOTIONS)
    Call<GetPromotionsResponse> loadPromotions(
            @Field(AppConstants.PARAM_ACCESS_TOKEN) String accessToken,
            @Field("page") int pageIndex);

}
