import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Administrator
 */
@NoArgsConstructor
@Data
public class CastsItem {

	@JsonProperty("date")
	private String date;

	@JsonProperty("dayweather")
	private String dayweather;

	@JsonProperty("daywind")
	private String daywind;

	@JsonProperty("week")
	private String week;

	@JsonProperty("daypower")
	private String daypower;

	@JsonProperty("daytemp")
	private String daytemp;

	@JsonProperty("nightwind")
	private String nightwind;

	@JsonProperty("nighttemp")
	private String nighttemp;

	@JsonProperty("nightweather")
	private String nightweather;

	@JsonProperty("nightpower")
	private String nightpower;

	@Override
	public String toString() {
		return (
			"CastsItem{" +
			"date = '" +
			date +
			'\'' +
			",dayweather = '" +
			dayweather +
			'\'' +
			",daywind = '" +
			daywind +
			'\'' +
			",week = '" +
			week +
			'\'' +
			",daypower = '" +
			daypower +
			'\'' +
			",daytemp = '" +
			daytemp +
			'\'' +
			",nightwind = '" +
			nightwind +
			'\'' +
			",nighttemp = '" +
			nighttemp +
			'\'' +
			",nightweather = '" +
			nightweather +
			'\'' +
			",nightpower = '" +
			nightpower +
			'\'' +
			"}"
		);
	}
}
