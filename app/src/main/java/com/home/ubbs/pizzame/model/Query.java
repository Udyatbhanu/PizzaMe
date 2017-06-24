
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
    "count",
    "created",
    "lang",
    "diagnostics",
    "results"
})
public class Query implements Parcelable
{

    @JsonProperty("count")
    private int count;
    @JsonProperty("created")
    private String created;
    @JsonProperty("lang")
    private String lang;
    @JsonProperty("diagnostics")
    private Diagnostics diagnostics;
    @JsonProperty("results")
    private Results results;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Parcelable.Creator<Query> CREATOR = new Creator<Query>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Query createFromParcel(Parcel in) {
            Query instance = new Query();
            instance.count = ((int) in.readValue((int.class.getClassLoader())));
            instance.created = ((String) in.readValue((String.class.getClassLoader())));
            instance.lang = ((String) in.readValue((String.class.getClassLoader())));
            instance.diagnostics = ((Diagnostics) in.readValue((Diagnostics.class.getClassLoader())));
            instance.results = ((Results) in.readValue((Results.class.getClassLoader())));
            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public Query[] newArray(int size) {
            return (new Query[size]);
        }

    }
    ;

    @JsonProperty("count")
    public int getCount() {
        return count;
    }

    @JsonProperty("count")
    public void setCount(int count) {
        this.count = count;
    }

    @JsonProperty("created")
    public String getCreated() {
        return created;
    }

    @JsonProperty("created")
    public void setCreated(String created) {
        this.created = created;
    }

    @JsonProperty("lang")
    public String getLang() {
        return lang;
    }

    @JsonProperty("lang")
    public void setLang(String lang) {
        this.lang = lang;
    }

    @JsonProperty("diagnostics")
    public Diagnostics getDiagnostics() {
        return diagnostics;
    }

    @JsonProperty("diagnostics")
    public void setDiagnostics(Diagnostics diagnostics) {
        this.diagnostics = diagnostics;
    }

    @JsonProperty("results")
    public Results getResults() {
        return results;
    }

    @JsonProperty("results")
    public void setResults(Results results) {
        this.results = results;
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
        dest.writeValue(count);
        dest.writeValue(created);
        dest.writeValue(lang);
        dest.writeValue(diagnostics);
        dest.writeValue(results);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

}
