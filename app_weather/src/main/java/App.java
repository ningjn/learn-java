import amap.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import generate.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Administrator
 */
public class App {

	public static final Logger logger = LoggerFactory.getLogger("root");

	public String key = "7f76ae97dfa7bfbb32a22cf8e0b9c052";
	public String livesFormat =
		"https://restapi.amap.com/v3/weather/weatherInfo?key=%s&extensions=base&city=%s";
	public String forecastFormat =
		"https://restapi.amap.com/v3/weather/weatherInfo?key=%s&extensions=all&city=%s";
	List<WeatherAdCode> weatherAdCodeList = new ArrayList<>();

	AmapWeather myWeather = new AmapWeather();

	private void oneCityLiveToDb(
		SqlSession sqlSession,
		WeatherLiveDao weatherLiveDao,
		String cityCode
	) {
		logger.debug(String.format("获取%s实时天气", cityCode));
		// 获取实时天气
		LivesResponse livesResponse = myWeather.getLivesWeather(
			String.format(livesFormat, key, cityCode)
		);

		// 信息入库
		if (livesResponse != null && "10000".equals(livesResponse.getInfocode())) {
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
	}

	private void oneCityForecastToDb(
		SqlSession sqlSession,
		WeatherForecastDao weatherForecastDao,
		String cityCode
	) {
		logger.debug(String.format("获取%s天气预报", cityCode));
		// 获取天气预报
		ForecastsResponse forecastsResponse = myWeather.getForecastsWeather(
			String.format(forecastFormat, key, cityCode)
		);

		// 信息入库
		if (
			forecastsResponse != null &&
			"10000".equals(forecastsResponse.getInfocode())
		) {
			System.out.println("预报天气入库处理");
			for (ForecastsItem item : forecastsResponse.getForecasts()) {
				for (CastsItem castsItem : item.getCasts()) {
					WeatherForecast weatherForecast = new WeatherForecast();
					weatherForecast.setProvince(item.getProvince());
					weatherForecast.setCity(item.getCity());
					weatherForecast.setAdCode(item.getAdcode());
					weatherForecast.setReportTime(item.getReporttime());

					weatherForecast.setDate(castsItem.getDate());
					weatherForecast.setWeek(castsItem.getWeek());
					weatherForecast.setDayWeather(castsItem.getDayweather());
					weatherForecast.setNightWeather(castsItem.getNightweather());
					weatherForecast.setDayTemp(castsItem.getDaytemp());
					weatherForecast.setNightTemp(castsItem.getNighttemp());
					weatherForecast.setDayWind(castsItem.getDaywind());
					weatherForecast.setNightWind(castsItem.getNightwind());
					weatherForecast.setDayPower(castsItem.getDaypower());
					weatherForecast.setNightPower(castsItem.getNightpower());

					logger.debug(String.valueOf(weatherForecast));
					weatherForecastDao.insert(weatherForecast);
				}
			}

			sqlSession.commit();
		}
	}

	/**
	 * 获取地市编码
	 */
	public void getCityCode() {
		SqlSessionFactory factory = MyBatisUtil.obtionSqlSessionFactory();
		try (SqlSession sqlSession = factory.openSession()) {
			WeatherAdCodeDao weatherAdCodeDao = sqlSession.getMapper(
				WeatherAdCodeDao.class
			);

			weatherAdCodeList = weatherAdCodeDao.selectList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 实时天气获取与入库处理
	 */
	public void livesToDb() {
		logger.debug("实时天气入库处理");

		SqlSessionFactory factory = MyBatisUtil.obtionSqlSessionFactory();
		try (SqlSession sqlSession = factory.openSession()) {
			WeatherLiveDao weatherLiveDao = sqlSession.getMapper(
				WeatherLiveDao.class
			);

			for (WeatherAdCode weatherAdCode : weatherAdCodeList) {
				oneCityLiveToDb(sqlSession, weatherLiveDao, weatherAdCode.getAdCode());
			}

			// 一次提交
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 预报天气获取与入库处理
	 */
	public void forecastsToDb() {
		logger.debug("预报天气入库处理");

		SqlSessionFactory factory = MyBatisUtil.obtionSqlSessionFactory();
		try (SqlSession sqlSession = factory.openSession()) {
			WeatherForecastDao weatherForecastDao = sqlSession.getMapper(
				WeatherForecastDao.class
			);

			for (WeatherAdCode weatherAdCode : weatherAdCodeList) {
				oneCityForecastToDb(
					sqlSession,
					weatherForecastDao,
					weatherAdCode.getAdCode()
				);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws JsonProcessingException {
		App app = new App();

		// 获取城市列表
		app.getCityCode();

		// 获取实时天气
		// app.livesToDb();

		// 获取天气预报
		app.forecastsToDb();
	}
}
