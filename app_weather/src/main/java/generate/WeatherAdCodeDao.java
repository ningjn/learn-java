package generate;

import generate.WeatherAdCode;
import java.util.List;

public interface WeatherAdCodeDao {
	int deleteByPrimaryKey(String adCode);

	int insert(WeatherAdCode record);

	int insertSelective(WeatherAdCode record);

	WeatherAdCode selectByPrimaryKey(String adCode);

	List<WeatherAdCode> selectList();

	int updateByPrimaryKeySelective(WeatherAdCode record);

	int updateByPrimaryKey(WeatherAdCode record);
}
