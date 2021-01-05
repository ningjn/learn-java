import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * @author ningth
 */
public class MyBatisUtil {

	private static volatile SqlSessionFactory sqlSessionFactory;

	public static SqlSessionFactory obtionSqlSessionFactory() {
		if (sqlSessionFactory == null) {
			synchronized (MyBatisUtil.class) {
				if (sqlSessionFactory == null) {
					sqlSessionFactory = getSqlSessionFactory();
				}
			}
		}
		return sqlSessionFactory;
	}

	private static SqlSessionFactory getSqlSessionFactory() {
		String resource = "mybatis-config.xml";
		SqlSessionFactory sqlSessionFactory = null;
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSessionFactory;
	}
}
