import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ForecastsResponse {

	@JsonProperty("count")
	private String count;

	@JsonProperty("infocode")
	private String infocode;

	@JsonProperty("status")
	private String status;

	@JsonProperty("info")
	private String info;

	@JsonProperty("forecasts")
	private List<ForecastsItem> forecasts;

	@Override
	public String toString() {
		return (
			"ForecastsResponse{" +
			"count = '" +
			count +
			'\'' +
			",infocode = '" +
			infocode +
			'\'' +
			",status = '" +
			status +
			'\'' +
			",info = '" +
			info +
			'\'' +
			",forecasts = '" +
			forecasts +
			'\'' +
			"}"
		);
	}
}
