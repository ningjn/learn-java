package amap;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.Data;

@Data
public class LivesItem {

	@JsonProperty("province")
	private String province;

	@JsonProperty("city")
	private String city;

	@JsonProperty("adcode")
	private String adcode;

	@JsonProperty("windpower")
	private String windpower;

	@JsonProperty("weather")
	private String weather;

	@JsonProperty("temperature")
	private String temperature;

	@JsonProperty("humidity")
	private String humidity;

	@JsonProperty("reporttime")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date reporttime;

	@JsonProperty("winddirection")
	private String winddirection;

	@Override
	public String toString() {
		return (
			"LivesItem{" +
			"province = '" +
			province +
			'\'' +
			",city = '" +
			city +
			'\'' +
			",adcode = '" +
			adcode +
			'\'' +
			",windpower = '" +
			windpower +
			'\'' +
			",weather = '" +
			weather +
			'\'' +
			",temperature = '" +
			temperature +
			'\'' +
			",humidity = '" +
			humidity +
			'\'' +
			",reporttime = '" +
			reporttime +
			'\'' +
			",winddirection = '" +
			winddirection +
			'\'' +
			"}"
		);
	}
}
