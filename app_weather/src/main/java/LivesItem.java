import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ningth
 */
@NoArgsConstructor
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
	private String reporttime;

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
