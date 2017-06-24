
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
    "execution-start-time",
    "execution-stop-time",
    "execution-time",
    "instructions-used",
    "table-name"
})
public class Javascript implements Parcelable
{

    @JsonProperty("execution-start-time")
    private String executionStartTime;
    @JsonProperty("execution-stop-time")
    private String executionStopTime;
    @JsonProperty("execution-time")
    private String executionTime;
    @JsonProperty("instructions-used")
    private String instructionsUsed;
    @JsonProperty("table-name")
    private String tableName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Parcelable.Creator<Javascript> CREATOR = new Creator<Javascript>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Javascript createFromParcel(Parcel in) {
            Javascript instance = new Javascript();
            instance.executionStartTime = ((String) in.readValue((String.class.getClassLoader())));
            instance.executionStopTime = ((String) in.readValue((String.class.getClassLoader())));
            instance.executionTime = ((String) in.readValue((String.class.getClassLoader())));
            instance.instructionsUsed = ((String) in.readValue((String.class.getClassLoader())));
            instance.tableName = ((String) in.readValue((String.class.getClassLoader())));
            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public Javascript[] newArray(int size) {
            return (new Javascript[size]);
        }

    }
    ;

    @JsonProperty("execution-start-time")
    public String getExecutionStartTime() {
        return executionStartTime;
    }

    @JsonProperty("execution-start-time")
    public void setExecutionStartTime(String executionStartTime) {
        this.executionStartTime = executionStartTime;
    }

    @JsonProperty("execution-stop-time")
    public String getExecutionStopTime() {
        return executionStopTime;
    }

    @JsonProperty("execution-stop-time")
    public void setExecutionStopTime(String executionStopTime) {
        this.executionStopTime = executionStopTime;
    }

    @JsonProperty("execution-time")
    public String getExecutionTime() {
        return executionTime;
    }

    @JsonProperty("execution-time")
    public void setExecutionTime(String executionTime) {
        this.executionTime = executionTime;
    }

    @JsonProperty("instructions-used")
    public String getInstructionsUsed() {
        return instructionsUsed;
    }

    @JsonProperty("instructions-used")
    public void setInstructionsUsed(String instructionsUsed) {
        this.instructionsUsed = instructionsUsed;
    }

    @JsonProperty("table-name")
    public String getTableName() {
        return tableName;
    }

    @JsonProperty("table-name")
    public void setTableName(String tableName) {
        this.tableName = tableName;
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
        dest.writeValue(executionStartTime);
        dest.writeValue(executionStopTime);
        dest.writeValue(executionTime);
        dest.writeValue(instructionsUsed);
        dest.writeValue(tableName);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

}
