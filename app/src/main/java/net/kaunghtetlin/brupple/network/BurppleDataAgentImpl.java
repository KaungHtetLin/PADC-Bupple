package net.kaunghtetlin.brupple.network;

import android.content.Context;

import net.kaunghtetlin.brupple.events.RestApiEvents;
import net.kaunghtetlin.brupple.network.responses.GetFeaturedResponse;
import net.kaunghtetlin.brupple.network.responses.GetGuidesResponse;
import net.kaunghtetlin.brupple.network.responses.GetPromotionsResponse;
import net.kaunghtetlin.brupple.utils.AppConstants;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kaung Htet Lin on 1/18/2018.
 */

public class BurppleDataAgentImpl implements BurppleDataAgent {


    private static BurppleDataAgentImpl objInstance;

    private BurppleApi theAPI;

    private BurppleDataAgentImpl() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.APP_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        theAPI = retrofit.create(BurppleApi.class);
    }

    public static BurppleDataAgentImpl getObjInstance() {
        if (objInstance == null) {
            objInstance = new BurppleDataAgentImpl();
        }
        return objInstance;
    }


    @Override
    public void loadFeatured(String accessToken, int page, final Context context) {
        Call<GetFeaturedResponse> loadFeaturedCall = theAPI.loadFeatured(accessToken, page);
        loadFeaturedCall.enqueue(
                new BurppleCallBack<GetFeaturedResponse>() {
                    @Override
                    public void onResponse(Call<GetFeaturedResponse> call, Response<GetFeaturedResponse> response) {
                        super.onResponse(call, response);
                        GetFeaturedResponse getFeaturedResponse = response.body();
                        if (getFeaturedResponse != null && getFeaturedResponse.getFeatured()!=null
                                && getFeaturedResponse.getFeatured().size() > 0) {
                            RestApiEvents.FeaturedDataLoadedEvent featuredDataLoadedEvent = new RestApiEvents.FeaturedDataLoadedEvent
                                    (getFeaturedResponse.getPage(), getFeaturedResponse.getFeatured(), context);
                            EventBus.getDefault().post(featuredDataLoadedEvent);
                        }
                    }
                }
        );
    }

    @Override
    public void loadGuides(String accessToken, int page, final Context context) {
        Call<GetGuidesResponse> loadGuideCall = theAPI.loadGuides(accessToken, page);
        loadGuideCall.enqueue(
                new BurppleCallBack<GetGuidesResponse>() {
                    @Override
                    public void onResponse(Call<GetGuidesResponse> call, Response<GetGuidesResponse> response) {
                        super.onResponse(call, response);
                        GetGuidesResponse getGuidesResponse = response.body();
                        if (getGuidesResponse != null && getGuidesResponse.getGuides()!=null
                                && getGuidesResponse.getGuides().size() > 0) {
                            RestApiEvents.GuidesDataLoadedEvent guidesDataLoadedEvent = new RestApiEvents.GuidesDataLoadedEvent
                                    (getGuidesResponse.getPage(), getGuidesResponse.getGuides(), context);
                            EventBus.getDefault().post(guidesDataLoadedEvent);
                        }
                    }
                }
        );
    }

    @Override
    public void loadPromotions(String accessToken, int page, final Context context) {
        Call<GetPromotionsResponse> loadPromotionsCall = theAPI.loadPromotions(accessToken, page);
        loadPromotionsCall.enqueue(
                new BurppleCallBack<GetPromotionsResponse>() {
                    @Override
                    public void onResponse(Call<GetPromotionsResponse> call, Response<GetPromotionsResponse> response) {
                        super.onResponse(call, response);
                        GetPromotionsResponse getMoviesResponse = response.body();
                        if (getMoviesResponse != null && getMoviesResponse.getPromotions()!=null
                                && getMoviesResponse.getPromotions().size() > 0) {
                            RestApiEvents.PromotionsDataLoadedEvent promotionsDataLoadedEvent = new RestApiEvents.PromotionsDataLoadedEvent
                                    (getMoviesResponse.getPage(), getMoviesResponse.getPromotions(), context);
                            EventBus.getDefault().post(promotionsDataLoadedEvent);
                        }
                    }
                }
        );
    }
}
