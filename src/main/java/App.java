import com.fasterxml.jackson.annotation.JsonProperty;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
class LivesObj {

	/**
	 * status : 1
	 * count : 1
	 * info : OK
	 * infocode : 10000
	 * lives : [{"province":"北京","city":"东城区","adcode":"110101","weather":"晴","temperature":"0","winddirection":"南","windpower":"≤3","humidity":"15","reporttime":"2020-12-31 17:00:23"}]
	 */

	@JsonProperty("status")
	private String status;

	@JsonProperty("count")
	private String count;

	@JsonProperty("info")
	private String info;

	@JsonProperty("infocode")
	private String infocode;

	@JsonProperty("lives")
	private List<LivesDTO> lives;

	@NoArgsConstructor
	@Data
	public static class LivesDTO {

		/**
		 * province : 北京
		 * city : 东城区
		 * adcode : 110101
		 * weather : 晴
		 * temperature : 0
		 * winddirection : 南
		 * windpower : ≤3
		 * humidity : 15
		 * reporttime : 2020-12-31 17:00:23
		 */

		@JsonProperty("province")
		private String province;

		@JsonProperty("city")
		private String city;

		@JsonProperty("adcode")
		private String adcode;

		@JsonProperty("weather")
		private String weather;

		@JsonProperty("temperature")
		private String temperature;

		@JsonProperty("winddirection")
		private String winddirection;

		@JsonProperty("windpower")
		private String windpower;

		@JsonProperty("humidity")
		private String humidity;

		@JsonProperty("reporttime")
		private String reporttime;
	}

	@Override
	public String toString() {
		return (
			"LivesObj{" +
			"status='" +
			status +
			'\'' +
			", count='" +
			count +
			'\'' +
			", info='" +
			info +
			'\'' +
			", infocode='" +
			infocode +
			'\'' +
			", lives=" +
			lives +
			'}'
		);
	}
}

@NoArgsConstructor
@Data
class ForecastObj {

	/**
	 * status : 1
	 * count : 1
	 * info : OK
	 * infocode : 10000
	 * forecasts : [{"city":"东城区","adcode":"110101","province":"北京","reporttime":"2020-12-31 17:00:23","casts":[{"date":"2020-12-31","week":"4","dayweather":"晴","nightweather":"晴","daytemp":"0","nighttemp":"-11","daywind":"西南","nightwind":"西南","daypower":"≤3","nightpower":"≤3"},{"date":"2021-01-01","week":"5","dayweather":"晴","nightweather":"晴","daytemp":"0","nighttemp":"-11","daywind":"西南","nightwind":"西南","daypower":"≤3","nightpower":"≤3"},{"date":"2021-01-02","week":"6","dayweather":"晴","nightweather":"多云","daytemp":"1","nighttemp":"-10","daywind":"西南","nightwind":"西南","daypower":"≤3","nightpower":"≤3"},{"date":"2021-01-03","week":"7","dayweather":"多云","nightweather":"多云","daytemp":"1","nighttemp":"-8","daywind":"东北","nightwind":"东北","daypower":"≤3","nightpower":"≤3"}]}]
	 */

	@JsonProperty("status")
	private String status;

	@JsonProperty("count")
	private String count;

	@JsonProperty("info")
	private String info;

	@JsonProperty("infocode")
	private String infocode;

	@JsonProperty("forecasts")
	private List<ForecastsDTO> forecasts;

	@NoArgsConstructor
	@Data
	public static class ForecastsDTO {

		/**
		 * city : 东城区
		 * adcode : 110101
		 * province : 北京
		 * reporttime : 2020-12-31 17:00:23
		 * casts : [{"date":"2020-12-31","week":"4","dayweather":"晴","nightweather":"晴","daytemp":"0","nighttemp":"-11","daywind":"西南","nightwind":"西南","daypower":"≤3","nightpower":"≤3"},{"date":"2021-01-01","week":"5","dayweather":"晴","nightweather":"晴","daytemp":"0","nighttemp":"-11","daywind":"西南","nightwind":"西南","daypower":"≤3","nightpower":"≤3"},{"date":"2021-01-02","week":"6","dayweather":"晴","nightweather":"多云","daytemp":"1","nighttemp":"-10","daywind":"西南","nightwind":"西南","daypower":"≤3","nightpower":"≤3"},{"date":"2021-01-03","week":"7","dayweather":"多云","nightweather":"多云","daytemp":"1","nighttemp":"-8","daywind":"东北","nightwind":"东北","daypower":"≤3","nightpower":"≤3"}]
		 */

		@JsonProperty("city")
		private String city;

		@JsonProperty("adcode")
		private String adcode;

		@JsonProperty("province")
		private String province;

		@JsonProperty("reporttime")
		private String reporttime;

		@JsonProperty("casts")
		private List<CastsDTO> casts;

		@NoArgsConstructor
		@Data
		public static class CastsDTO {

			/**
			 * date : 2020-12-31
			 * week : 4
			 * dayweather : 晴
			 * nightweather : 晴
			 * daytemp : 0
			 * nighttemp : -11
			 * daywind : 西南
			 * nightwind : 西南
			 * daypower : ≤3
			 * nightpower : ≤3
			 */

			@JsonProperty("date")
			private String date;

			@JsonProperty("week")
			private String week;

			@JsonProperty("dayweather")
			private String dayweather;

			@JsonProperty("nightweather")
			private String nightweather;

			@JsonProperty("daytemp")
			private String daytemp;

			@JsonProperty("nighttemp")
			private String nighttemp;

			@JsonProperty("daywind")
			private String daywind;

			@JsonProperty("nightwind")
			private String nightwind;

			@JsonProperty("daypower")
			private String daypower;

			@JsonProperty("nightpower")
			private String nightpower;
		}
	}
}

class JsonToObj {}

class MyWeather {

	/**
	 * 创建一个自定义的HTTP客户端对象
	 */
	private final HttpClient httpClient = HttpClient
		.newBuilder()
		.connectTimeout(Duration.ofSeconds(10))
		.build();

	public MyWeather() {}

	public HttpResponse<String> getWeather(String url) {
		try {
			// 创建一个自定义的HTTP请求对象
			HttpRequest request = HttpRequest
				.newBuilder()
				.GET()
				.uri(URI.create(url))
				.build();

			return this.httpClient.send(
					request,
					HttpResponse.BodyHandlers.ofString()
				);
		} catch (Exception e) {
			System.out.println("error---> " + e.getMessage());
		}

		return null;
	}
}

/**
 * @author Administrator
 */
public class App {

	public static void main(String[] args) {
		MyWeather myWeather = new MyWeather();

		String key = "7f76ae97dfa7bfbb32a22cf8e0b9c052";
		String cityCode = "110101";
		String forecastFormat =
			"https://restapi.amap.com/v3/weather/weatherInfo?key=%s&extensions=all&city=%s";
		String livesFormat =
			"https://restapi.amap.com/v3/weather/weatherInfo?key=%s&extensions=base&city=%s";

		// 获取实时天气
		HttpResponse<String> response = myWeather.getWeather(
			String.format(livesFormat, key, cityCode)
		);
		if (response != null && response.statusCode() == 200) {
			System.out.println(response.body());
		}

		// 获取3天预报
		response =
			myWeather.getWeather(String.format(forecastFormat, key, cityCode));
		if (response != null && response.statusCode() == 200) {
			System.out.println(response.body());
		}

		LivesObj livesObj = new LivesObj();
		System.out.println(livesObj.toString());
	}
}
