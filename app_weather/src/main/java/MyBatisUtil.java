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
	/*@Override
	public List<LivesItem> selectLivesItem() {
		SqlSessionFactory factory = MyBatisUtil.obtionSqlSessionFactory();
		try (SqlSession sqlSession = factory.openSession()) {
			LivesItemMapper livesItemMapper = sqlSession.getMapper(
				LivesItemMapper.class
			);
			List<LivesItem> livesItems = livesItemMapper.selectLivesItem();
			if (livesItems != null) {
				System.out.println("ok");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}*/

	/*public static void main(String[] args) {
		System.out.println("hello");
		MyBatisUtil myBatisUtil = new MyBatisUtil();
	}*/
}
