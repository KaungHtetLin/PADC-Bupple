package net.kaunghtetlin.brupple.network.responses;

import com.google.gson.annotations.SerializedName;

import net.kaunghtetlin.brupple.data.vos.GuidesVO;
import net.kaunghtetlin.brupple.network.BurppleResponse;

import java.util.List;

/**
 * Created by Kaung Htet Lin on 1/18/2018.
 */

public class GetGuidesResponse extends BurppleResponse {


    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("apiVersion")
    private String apiVersion;

    @SerializedName("page")
    private int page;

    @SerializedName("featured")
    private List<GuidesVO> guides;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public int getPage() {
        return page;
    }

    public List<GuidesVO> getGuides() {
        return guides;
    }
}
