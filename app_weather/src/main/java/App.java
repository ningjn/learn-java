import amap.ForecastsResponse;
import amap.LivesResponse;
import amap.MyWeather;
import com.fasterxml.jackson.core.JsonProcessingException;

public class App {

	public static void main(String[] args) throws JsonProcessingException {
		MyWeather myWeather = new MyWeather();

		String key = "7f76ae97dfa7bfbb32a22cf8e0b9c052";
		String cityCode = "110101";
		String forecastFormat =
			"https://restapi.amap.com/v3/weather/weatherInfo?key=%s&extensions=all&city=%s";
		String livesFormat =
			"https://restapi.amap.com/v3/weather/weatherInfo?key=%s&extensions=base&city=%s";

		// 获取实时天气
		LivesResponse livesResponse = myWeather.getLivesWeather(
			String.format(livesFormat, key, cityCode)
		);
		if (livesResponse != null) {
			System.out.println(livesResponse.getLives());
		}

		// 获取3天预报
		ForecastsResponse forecastsResponse = myWeather.getForecastsWeather(
			String.format(forecastFormat, key, cityCode)
		);
		if (forecastsResponse != null) {
			System.out.println(forecastsResponse.getForecasts());
		}
	}
}
