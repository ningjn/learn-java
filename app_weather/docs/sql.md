```
BEGIN;
--
-- Create model WeatherAdCode
--
CREATE TABLE "weather_ad_code" ("cn_name" varchar(255) NOT NULL, "ad_code" varchar(255) NOT NULL PRIMARY KEY, "city_code" varchar(255) NOT NULL);
--
-- Create model WeatherLive
--
CREATE TABLE "weather_live" ("id" serial NOT NULL PRIMARY KEY, "province" varchar(255) NOT NULL, "city" varchar(255) NOT NULL, "ad_code" varchar(255) NOT NULL, "weather" varchar(255) NOT NULL, "temperature" varchar(255) NOT NULL, "wind_direction" varchar(255) NOT NULL, "wind_power" varchar(255) NOT NULL, "humidity" varchar(255) NOT NULL, "report_time" timestamp with time zone NOT NULL);
--
-- Create model WeatherCast
--
CREATE TABLE "weather_forecast" ("id" serial NOT NULL PRIMARY KEY, "province" varchar(255) NOT NULL, "city" varchar(255) NOT NULL, "ad_code" varchar(255) NOT NULL, "report_time" timestamp with time zone NOT NULL, "date" varchar(255) NOT NULL, "week" varchar(255) NOT NULL, "day_weather" varchar(255) NOT NULL, "night_weather" varchar(255) NOT NULL, "day_temp" varchar(255) NOT NULL, "night_temp" varchar(255) NOT NULL, "day_wind" varchar(255) NOT NULL, "night_wind" varchar(255) NOT NULL, "day_power" varchar(255) NOT NULL, "night_power" varchar(255) NOT NULL);
CREATE INDEX "weather_ad_code_ad_code_f33e79e2_like" ON "weather_ad_code" ("ad_code" varchar_pattern_ops);
ALTER TABLE "weather_live" ADD CONSTRAINT "weather_live_ad_code_report_time_c8cba4c1_uniq" UNIQUE ("ad_code", "report_time");
CREATE INDEX "weather_live_report_time_ac8b467e_idx" ON "weather_live" ("report_time");
ALTER TABLE "weather_forecast" ADD CONSTRAINT "weather_forecast_ad_code_report_time_date_419077f3_uniq" UNIQUE ("ad_code", "report_time", "date");
CREATE INDEX "weather_forecast_report_time_9cbd8570_idx" ON "weather_forecast" ("report_time");
COMMIT;
```

```
DATABASES = {
    'default': {
        'ENGINE': 'django.db.backends.postgresql',
        'NAME': 'app_db',
        'USER': 'appuser',
        'PASSWORD': '5WaPyUkLB5KctMv',
        'HOST': '49.234.50.69',
        'PORT': '5432',
    }
}
```
