import amap.ForecastsResponse;
import amap.LivesResponse;
import amap.MyWeather;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
		if (livesResponse != null && "10000".equals(livesResponse.getInfocode())) {
			System.out.println("实时天气入库处理");
			System.out.println(livesResponse.getLives());
		}

		// 获取3天预报
		ForecastsResponse forecastsResponse = myWeather.getForecastsWeather(
			String.format(forecastFormat, key, cityCode)
		);
		if (
			forecastsResponse != null &&
			"10000".equals(forecastsResponse.getInfocode())
		) {
			System.out.println("预报天气入库处理");
			System.out.println(forecastsResponse.getForecasts());
		}

		Logger logger = LoggerFactory.getLogger("root");
		logger.info("Hello World");

		logger.trace("trace");
		logger.debug("debug");
		logger.warn("warn");
		logger.info("info");
		logger.error("error");
	}
}
