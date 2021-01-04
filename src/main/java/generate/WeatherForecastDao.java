package generate;

import generate.WeatherForecast;

public interface WeatherForecastDao {
	int deleteByPrimaryKey(Integer id);

	int insert(WeatherForecast record);

	int insertSelective(WeatherForecast record);

	WeatherForecast selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(WeatherForecast record);

	int updateByPrimaryKey(WeatherForecast record);
}
