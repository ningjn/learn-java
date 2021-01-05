package generate;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * weather_forecast
 * @author
 */
@Data
public class WeatherForecast implements Serializable {

	private Integer id;

	private String province;

	private String city;

	private String adCode;

	private Date reportTime;

	private String date;

	private String week;

	private String dayWeather;

	private String nightWeather;

	private String dayTemp;

	private String nightTemp;

	private String dayWind;

	private String nightWind;

	private String dayPower;

	private String nightPower;

	private static final long serialVersionUID = 1L;
}
