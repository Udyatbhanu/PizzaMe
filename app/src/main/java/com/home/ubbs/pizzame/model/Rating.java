
package com.home.ubbs.pizzame.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "AverageRating",
    "TotalRatings",
    "TotalReviews",
    "LastReviewDate",
    "LastReviewIntro"
})
public class Rating implements Parcelable
{

    @JsonProperty("AverageRating")
    private String averageRating;
    @JsonProperty("TotalRatings")
    private String totalRatings;
    @JsonProperty("TotalReviews")
    private String totalReviews;
    @JsonProperty("LastReviewDate")
    private Object lastReviewDate;
    @JsonProperty("LastReviewIntro")
    private Object lastReviewIntro;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Parcelable.Creator<Rating> CREATOR = new Creator<Rating>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Rating createFromParcel(Parcel in) {
            Rating instance = new Rating();
            instance.averageRating = ((String) in.readValue((String.class.getClassLoader())));
            instance.totalRatings = ((String) in.readValue((String.class.getClassLoader())));
            instance.totalReviews = ((String) in.readValue((String.class.getClassLoader())));
            instance.lastReviewDate = in.readValue((Object.class.getClassLoader()));
            instance.lastReviewIntro = in.readValue((Object.class.getClassLoader()));
            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public Rating[] newArray(int size) {
            return (new Rating[size]);
        }

    }
    ;

    @JsonProperty("AverageRating")
    public String getAverageRating() {
        return averageRating;
    }

    @JsonProperty("AverageRating")
    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

    @JsonProperty("TotalRatings")
    public String getTotalRatings() {
        return totalRatings;
    }

    @JsonProperty("TotalRatings")
    public void setTotalRatings(String totalRatings) {
        this.totalRatings = totalRatings;
    }

    @JsonProperty("TotalReviews")
    public String getTotalReviews() {
        return totalReviews;
    }

    @JsonProperty("TotalReviews")
    public void setTotalReviews(String totalReviews) {
        this.totalReviews = totalReviews;
    }

    @JsonProperty("LastReviewDate")
    public Object getLastReviewDate() {
        return lastReviewDate;
    }

    @JsonProperty("LastReviewDate")
    public void setLastReviewDate(Object lastReviewDate) {
        this.lastReviewDate = lastReviewDate;
    }

    @JsonProperty("LastReviewIntro")
    public Object getLastReviewIntro() {
        return lastReviewIntro;
    }

    @JsonProperty("LastReviewIntro")
    public void setLastReviewIntro(Object lastReviewIntro) {
        this.lastReviewIntro = lastReviewIntro;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(averageRating);
        dest.writeValue(totalRatings);
        dest.writeValue(totalReviews);
        dest.writeValue(lastReviewDate);
        dest.writeValue(lastReviewIntro);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

}
