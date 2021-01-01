import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ningth
 */
@NoArgsConstructor
@Data
public class LivesResponse {

	@JsonProperty("lives")
	private List<LivesItem> lives;

	@JsonProperty("count")
	private String count;

	@JsonProperty("infocode")
	private String infocode;

	@JsonProperty("status")
	private String status;

	@JsonProperty("info")
	private String info;

	@Override
	public String toString() {
		return (
			"LivesResponse{" +
			"lives = '" +
			lives +
			'\'' +
			",count = '" +
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
			"}"
		);
	}
}
