package generate;

import generate.WeatherLive;

public interface WeatherLiveDao {
	int deleteByPrimaryKey(Integer id);

	int insert(WeatherLive record);

	int insertSelective(WeatherLive record);

	WeatherLive selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(WeatherLive record);

	int updateByPrimaryKey(WeatherLive record);
}
