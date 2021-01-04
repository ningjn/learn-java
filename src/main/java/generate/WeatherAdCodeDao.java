package generate;

import generate.WeatherAdCode;

public interface WeatherAdCodeDao {
	int deleteByPrimaryKey(String adCode);

	int insert(WeatherAdCode record);

	int insertSelective(WeatherAdCode record);

	WeatherAdCode selectByPrimaryKey(String adCode);

	int updateByPrimaryKeySelective(WeatherAdCode record);

	int updateByPrimaryKey(WeatherAdCode record);
}
