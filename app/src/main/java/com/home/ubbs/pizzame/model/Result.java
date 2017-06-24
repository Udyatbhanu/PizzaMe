
package com.home.ubbs.pizzame.model;

import java.util.HashMap;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "xmlns",
    "Title",
    "Address",
    "City",
    "State",
    "Phone",
    "Latitude",
    "Longitude",
    "Rating",
    "Distance",
    "Url",
    "ClickUrl",
    "MapUrl",
    "BusinessUrl",
    "BusinessClickUrl",
    "Categories"
})
public class Result implements Parcelable
{

    @JsonProperty("id")
    private String id;
    @JsonProperty("xmlns")
    private String xmlns;
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Address")
    private String address;
    @JsonProperty("City")
    private String city;
    @JsonProperty("State")
    private String state;
    @JsonProperty("Phone")
    private String phone;
    @JsonProperty("Latitude")
    private String latitude;
    @JsonProperty("Longitude")
    private String longitude;
    @JsonProperty("Rating")
    private Rating rating;
    @JsonProperty("Distance")
    private String distance;
    @JsonProperty("Url")
    private String url;
    @JsonProperty("ClickUrl")
    private String clickUrl;
    @JsonProperty("MapUrl")
    private String mapUrl;
    @JsonProperty("BusinessUrl")
    private String businessUrl;
    @JsonProperty("BusinessClickUrl")
    private String businessClickUrl;
    @JsonProperty("Categories")
    private Categories categories;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Parcelable.Creator<Result> CREATOR = new Creator<Result>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Result createFromParcel(Parcel in) {
            Result instance = new Result();
            instance.id = ((String) in.readValue((String.class.getClassLoader())));
            instance.xmlns = ((String) in.readValue((String.class.getClassLoader())));
            instance.title = ((String) in.readValue((String.class.getClassLoader())));
            instance.address = ((String) in.readValue((String.class.getClassLoader())));
            instance.city = ((String) in.readValue((String.class.getClassLoader())));
            instance.state = ((String) in.readValue((String.class.getClassLoader())));
            instance.phone = ((String) in.readValue((String.class.getClassLoader())));
            instance.latitude = ((String) in.readValue((String.class.getClassLoader())));
            instance.longitude = ((String) in.readValue((String.class.getClassLoader())));
            instance.rating = ((Rating) in.readValue((Rating.class.getClassLoader())));
            instance.distance = ((String) in.readValue((String.class.getClassLoader())));
            instance.url = ((String) in.readValue((String.class.getClassLoader())));
            instance.clickUrl = ((String) in.readValue((String.class.getClassLoader())));
            instance.mapUrl = ((String) in.readValue((String.class.getClassLoader())));
            instance.businessUrl = ((String) in.readValue((String.class.getClassLoader())));
            instance.businessClickUrl = ((String) in.readValue((String.class.getClassLoader())));
            instance.categories = ((Categories) in.readValue((Categories.class.getClassLoader())));
            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public Result[] newArray(int size) {
            return (new Result[size]);
        }

    }
    ;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("xmlns")
    public String getXmlns() {
        return xmlns;
    }

    @JsonProperty("xmlns")
    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }

    @JsonProperty("Title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("Title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("Address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("Address")
    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("City")
    public String getCity() {
        return city;
    }

    @JsonProperty("City")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("State")
    public String getState() {
        return state;
    }

    @JsonProperty("State")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("Phone")
    public String getPhone() {
        return phone;
    }

    @JsonProperty("Phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonProperty("Latitude")
    public String getLatitude() {
        return latitude;
    }

    @JsonProperty("Latitude")
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @JsonProperty("Longitude")
    public String getLongitude() {
        return longitude;
    }

    @JsonProperty("Longitude")
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @JsonProperty("Rating")
    public Rating getRating() {
        return rating;
    }

    @JsonProperty("Rating")
    public void setRating(Rating rating) {
        this.rating = rating;
    }

    @JsonProperty("Distance")
    public String getDistance() {
        return distance;
    }

    @JsonProperty("Distance")
    public void setDistance(String distance) {
        this.distance = distance;
    }

    @JsonProperty("Url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("Url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("ClickUrl")
    public String getClickUrl() {
        return clickUrl;
    }

    @JsonProperty("ClickUrl")
    public void setClickUrl(String clickUrl) {
        this.clickUrl = clickUrl;
    }

    @JsonProperty("MapUrl")
    public String getMapUrl() {
        return mapUrl;
    }

    @JsonProperty("MapUrl")
    public void setMapUrl(String mapUrl) {
        this.mapUrl = mapUrl;
    }

    @JsonProperty("BusinessUrl")
    public String getBusinessUrl() {
        return businessUrl;
    }

    @JsonProperty("BusinessUrl")
    public void setBusinessUrl(String businessUrl) {
        this.businessUrl = businessUrl;
    }

    @JsonProperty("BusinessClickUrl")
    public String getBusinessClickUrl() {
        return businessClickUrl;
    }

    @JsonProperty("BusinessClickUrl")
    public void setBusinessClickUrl(String businessClickUrl) {
        this.businessClickUrl = businessClickUrl;
    }

    @JsonProperty("Categories")
    public Categories getCategories() {
        return categories;
    }

    @JsonProperty("Categories")
    public void setCategories(Categories categories) {
        this.categories = categories;
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
        dest.writeValue(id);
        dest.writeValue(xmlns);
        dest.writeValue(title);
        dest.writeValue(address);
        dest.writeValue(city);
        dest.writeValue(state);
        dest.writeValue(phone);
        dest.writeValue(latitude);
        dest.writeValue(longitude);
        dest.writeValue(rating);
        dest.writeValue(distance);
        dest.writeValue(url);
        dest.writeValue(clickUrl);
        dest.writeValue(mapUrl);
        dest.writeValue(businessUrl);
        dest.writeValue(businessClickUrl);
        dest.writeValue(categories);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

}
