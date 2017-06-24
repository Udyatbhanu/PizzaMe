
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
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Category"
})
public class Categories implements Parcelable
{

    @JsonProperty("Category")
    private List<Category> category;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Parcelable.Creator<Categories> CREATOR = new Creator<Categories>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Categories createFromParcel(Parcel in) {
            Categories instance = new Categories();
            instance.category = ((List<Category>) in.readValue((List.class.getClassLoader())));
            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public Categories[] newArray(int size) {
            return (new Categories[size]);
        }

    }
    ;

    @JsonProperty("Category")
    public List<Category> getCategory() {
        return category;
    }

    @JsonProperty("Category")
    public void setCategory(List<Category> category) {
        this.category = category;
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
        dest.writeValue(category);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

}
