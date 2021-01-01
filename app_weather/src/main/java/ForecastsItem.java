import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Administrator
 */
@NoArgsConstructor
@Data
public class ForecastsItem {

	@JsonProperty("province")
	private String province;

	@JsonProperty("casts")
	private List<CastsItem> casts;

	@JsonProperty("city")
	private String city;

	@JsonProperty("adcode")
	private String adcode;

	@JsonProperty("reporttime")
	private String reporttime;

	@Override
	public String toString() {
		return (
			"ForecastsItem{" +
			"province = '" +
			province +
			'\'' +
			",casts = '" +
			casts +
			'\'' +
			",city = '" +
			city +
			'\'' +
			",adcode = '" +
			adcode +
			'\'' +
			",reporttime = '" +
			reporttime +
			'\'' +
			"}"
		);
	}
}
