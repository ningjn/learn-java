import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;

public class MyWeather {

	/**
	 * 创建一个自定义的HTTP客户端对象
	 */
	private final HttpClient httpClient = HttpClient
		.newBuilder()
		.connectTimeout(Duration.ofSeconds(10))
		.build();

	public MyWeather() {}

	public LivesResponse getLivesWeather(String url) {
		try {
			// 创建一个自定义的HTTP请求对象
			HttpRequest request = HttpRequest
				.newBuilder()
				.GET()
				.header("Accept", "application/json")
				.uri(URI.create(url))
				.build();

			return this.httpClient.send(
					request,
					new JsonBodyHandler<>(LivesResponse.class)
				)
				.body()
				.get();
		} catch (Exception e) {
			System.out.println("error---> " + e.getMessage());
		}

		return null;
	}

	public ForecastsResponse getForecastsWeather(String url) {
		try {
			// 创建一个自定义的HTTP请求对象
			HttpRequest request = HttpRequest
				.newBuilder()
				.GET()
				.header("Accept", "application/json")
				.uri(URI.create(url))
				.build();

			return this.httpClient.send(
					request,
					new JsonBodyHandler<>(ForecastsResponse.class)
				)
				.body()
				.get();
		} catch (Exception e) {
			System.out.println("error---> " + e.getMessage());
		}

		return null;
	}
}
