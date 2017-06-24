
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
    "publiclyCallable",
    "cache",
    "url",
    "query",
    "javascript",
    "user-time",
    "service-time",
    "build-version"
})
public class Diagnostics implements Parcelable
{

    @JsonProperty("publiclyCallable")
    private String publiclyCallable;
    @JsonProperty("cache")
    private List<Cache> cache = null;
    @JsonProperty("url")
    private List<Url> url;
    @JsonProperty("query")
    private List<Query_> query;
    @JsonProperty("javascript")
    private List<Javascript> javascript;
    @JsonProperty("user-time")
    private String userTime;
    @JsonProperty("service-time")
    private String serviceTime;
    @JsonProperty("build-version")
    private String buildVersion;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Parcelable.Creator<Diagnostics> CREATOR = new Creator<Diagnostics>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Diagnostics createFromParcel(Parcel in) {
            Diagnostics instance = new Diagnostics();
            instance.publiclyCallable = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.cache, (com.home.ubbs.pizzame.model.Cache.class.getClassLoader()));
            instance.url = ((List<Url>) in.readValue((List.class.getClassLoader())));
            instance.query = ((List<Query_>) in.readValue((List.class.getClassLoader())));
            instance.javascript = ((List<Javascript>) in.readValue((List.class.getClassLoader())));
            instance.userTime = ((String) in.readValue((String.class.getClassLoader())));
            instance.serviceTime = ((String) in.readValue((String.class.getClassLoader())));
            instance.buildVersion = ((String) in.readValue((String.class.getClassLoader())));
            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public Diagnostics[] newArray(int size) {
            return (new Diagnostics[size]);
        }

    }
    ;

    @JsonProperty("publiclyCallable")
    public String getPubliclyCallable() {
        return publiclyCallable;
    }

    @JsonProperty("publiclyCallable")
    public void setPubliclyCallable(String publiclyCallable) {
        this.publiclyCallable = publiclyCallable;
    }

    @JsonProperty("cache")
    public List<Cache> getCache() {
        return cache;
    }

    @JsonProperty("cache")
    public void setCache(List<Cache> cache) {
        this.cache = cache;
    }

    @JsonProperty("url")
    public List<Url> getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(List<Url> url) {
        this.url = url;
    }

    @JsonProperty("query")
    public List<Query_>  getQuery() {
        return query;
    }

    @JsonProperty("query")
    public void setQuery(List<Query_>  query) {
        this.query = query;
    }

    @JsonProperty("javascript")
    public List<Javascript> getJavascript() {
        return javascript;
    }

    @JsonProperty("javascript")
    public void setJavascript(List<Javascript> javascript) {
        this.javascript = javascript;
    }

    @JsonProperty("user-time")
    public String getUserTime() {
        return userTime;
    }

    @JsonProperty("user-time")
    public void setUserTime(String userTime) {
        this.userTime = userTime;
    }

    @JsonProperty("service-time")
    public String getServiceTime() {
        return serviceTime;
    }

    @JsonProperty("service-time")
    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    @JsonProperty("build-version")
    public String getBuildVersion() {
        return buildVersion;
    }

    @JsonProperty("build-version")
    public void setBuildVersion(String buildVersion) {
        this.buildVersion = buildVersion;
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
        dest.writeValue(publiclyCallable);
        dest.writeList(cache);
        dest.writeValue(url);
        dest.writeValue(query);
        dest.writeValue(javascript);
        dest.writeValue(userTime);
        dest.writeValue(serviceTime);
        dest.writeValue(buildVersion);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

}
