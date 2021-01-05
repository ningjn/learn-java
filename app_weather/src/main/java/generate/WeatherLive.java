package generate;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * weather_live
 * @author
 */
@Data
public class WeatherLive implements Serializable {

	private Integer id;

	private String province;

	private String city;

	private String adCode;

	private String weather;

	private String temperature;

	private String windDirection;

	private String windPower;

	private String humidity;

	private Date reportTime;

	private static final long serialVersionUID = 1L;
}
