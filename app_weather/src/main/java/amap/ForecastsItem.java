package amap;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import lombok.Data;

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
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date reporttime;

	@Override
	public String toString() {
		return (
			"ForecastsItem{" +
			"province='" +
			province +
			'\'' +
			", casts=" +
			casts +
			", city='" +
			city +
			'\'' +
			", adcode='" +
			adcode +
			'\'' +
			", reporttime='" +
			reporttime +
			'\'' +
			'}'
		);
	}
}
