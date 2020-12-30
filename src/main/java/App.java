import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

class DataObj {

	public String str;
}

class LivesObj extends DataObj {}

class ForecastObj extends DataObj {}

class MyWeather {

	// 创建一个自定义的HTTP客户端对象
	private final HttpClient httpClient = HttpClient
		.newBuilder()
		.connectTimeout(Duration.ofSeconds(10))
		.build();

	public MyWeather() {}

	public boolean GetWeather(String url, DataObj result) {
		try {
			// 创建一个自定义的HTTP请求对象
			HttpRequest request = HttpRequest
				.newBuilder()
				.GET()
				.uri(URI.create(url))
				.build();

			HttpResponse<String> response =
				this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() == 200) {
				result.str = response.body();
				return true;
			}
		} catch (Exception e) {
			System.out.println("error---> " + e.getMessage());
		}

		return false;
	}
}

public class App {

	public static void main(String[] args) {
		MyWeather myWeather = new MyWeather();
		LivesObj livesObj = new LivesObj();
		ForecastObj forecastObj = new ForecastObj();

		String key = "7f76ae97dfa7bfbb32a22cf8e0b9c052";
		String cityCode = "110101";
		String forecastFormat =
			"https://restapi.amap.com/v3/weather/weatherInfo?key=%s&extensions=all&city=%s";
		String livesFormat =
			"https://restapi.amap.com/v3/weather/weatherInfo?key=%s&extensions=base&city=%s";

		// 获取实时天气
		if (
			myWeather.GetWeather(String.format(livesFormat, key, cityCode), livesObj)
		) {
			System.out.println(livesObj.str);
		}

		// 获取3天预报
		if (
			myWeather.GetWeather(
				String.format(forecastFormat, key, cityCode),
				forecastObj
			)
		) {
			System.out.println(forecastObj.str);
		}
		// 1、函数参数有引用吗， 类似与c#中的ref，如： int i; func(ref i)
	}
}
