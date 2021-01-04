package generate;

import java.io.Serializable;
import lombok.Data;

/**
 * weather_ad_code
 * @author
 */
@Data
public class WeatherAdCode implements Serializable {

	private String adCode;

	private String cnName;

	private String cityCode;

	private static final long serialVersionUID = 1L;
}
