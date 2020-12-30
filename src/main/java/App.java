import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

class LivesObj {

	public String str;
}

class ForecastObj {

	public String str;
}

class MyWeather {

	// 创建一个自定义的HTTP客户端对象
	private final HttpClient httpClient = HttpClient
		.newBuilder()
		.connectTimeout(Duration.ofSeconds(10))
		.build();

	String key = "7f76ae97dfa7bfbb32a22cf8e0b9c052";
	String forecastFormat =
		"https://restapi.amap.com/v3/weather/weatherInfo?key=%s&extensions=all&city=%s";
	String livesFormat =
		"https://restapi.amap.com/v3/weather/weatherInfo?key=%s&extensions=base&city=%s";

	public MyWeather() {}

	public MyWeather(String _key) {
		key = _key;
	}

	public boolean getLivesWeather(String cityCode, LivesObj result) {
		try {
			// 创建一个自定义的HTTP请求对象
			HttpRequest request = HttpRequest
				.newBuilder()
				.GET()
				.uri(URI.create(String.format(livesFormat, this.key, cityCode)))
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

	public boolean getForecastWeather(String cityCode, ForecastObj result) {
		try {
			// 创建一个自定义的HTTP请求对象
			HttpRequest request = HttpRequest
				.newBuilder()
				.GET()
				.uri(URI.create(String.format(forecastFormat, this.key, cityCode)))
				.build();

			HttpResponse<String> response = httpClient.send(
				request,
				HttpResponse.BodyHandlers.ofString()
			);
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

		// 获取实时天气
		if (myWeather.getLivesWeather("110101", livesObj)) {
			System.out.println(livesObj.str);
		}

		// 获取3天预报
		if (myWeather.getForecastWeather("110101", forecastObj)) {
			System.out.println(forecastObj.str);
		}
	}
}
