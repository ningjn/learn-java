import amap.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import generate.WeatherLive;
import generate.WeatherLiveDao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Administrator
 */
public class App {

	public static final Logger logger = LoggerFactory.getLogger("root");

	/**
	 * 实时天气获取与入库处理
	 * @param key API密钥
	 */
	public void livesToDb(String key) {
		logger.debug("实时天气入库处理");

		// 高德地图
		AmapWeather myWeather = new AmapWeather();
		String cityCode;
		String livesFormat =
			"https://restapi.amap.com/v3/weather/weatherInfo?key=%s&extensions=base&city=%s";

		SqlSessionFactory factory = MyBatisUtil.obtionSqlSessionFactory();

		// 获取cityCode
		cityCode = "110101";

		try (SqlSession sqlSession = factory.openSession()) {
			WeatherLiveDao weatherLiveDao = sqlSession.getMapper(
				WeatherLiveDao.class
			);

			// 获取实时天气
			LivesResponse livesResponse = myWeather.getLivesWeather(
				String.format(livesFormat, key, cityCode)
			);

			// 信息入库
			if (
				livesResponse != null && "10000".equals(livesResponse.getInfocode())
			) {
				System.out.println("实时天气入库处理");
				for (LivesItem item : livesResponse.getLives()) {
					WeatherLive weatherLive = new WeatherLive();
					weatherLive.setProvince(item.getProvince());
					weatherLive.setCity(item.getCity());
					weatherLive.setAdCode(item.getAdcode());
					weatherLive.setWeather(item.getWeather());
					weatherLive.setTemperature(item.getTemperature());
					weatherLive.setWindDirection(item.getWinddirection());
					weatherLive.setWindPower(item.getWindpower());
					weatherLive.setHumidity(item.getHumidity());
					weatherLive.setReportTime(item.getReporttime());

					logger.debug(String.valueOf(weatherLive));
					weatherLiveDao.insert(weatherLive);
				}
			}

			sqlSession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 预报天气获取与入库处理
	 * @param key API密钥
	 */
	public void forecastsToDb(String key) {
		logger.debug("预报天气入库处理");

		// 高德地图
		AmapWeather myWeather = new AmapWeather();
		String cityCode;
		String forecastFormat =
			"https://restapi.amap.com/v3/weather/weatherInfo?key=%s&extensions=all&city=%s";

		// 获取cityCode
		cityCode = "110101";

		// 获取实时天气
		ForecastsResponse forecastsResponse = myWeather.getForecastsWeather(
			String.format(forecastFormat, key, cityCode)
		);
		if (
			forecastsResponse != null &&
			"10000".equals(forecastsResponse.getInfocode())
		) {
			System.out.println("预报天气入库处理");
			for (ForecastsItem item : forecastsResponse.getForecasts()) {
				System.out.println(item.getProvince());
				System.out.println(item.getCity());
				System.out.println(item.getAdcode());
				System.out.println(item.getReporttime());
				for (CastsItem cast : item.getCasts()) {
					System.out.println(cast);
				}
			}
		}
	}

	public static void main(String[] args) throws JsonProcessingException {
		String key = "7f76ae97dfa7bfbb32a22cf8e0b9c052";
		App app = new App();

		// 获取实时天气
		app.livesToDb(key);

		// 获取天气预报
		app.forecastsToDb(key);
	}
}
