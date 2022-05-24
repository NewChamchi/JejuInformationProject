package persistence.DAO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.DTO.JejuGridDTO;
import persistence.mapper.JejuGridMapper;

import java.util.List;

public class SearchJejuGridDAO {
    private SqlSessionFactory sqlSessionFactory = null;

    public SearchJejuGridDAO(SqlSessionFactory sqlSessionFactory) { this.sqlSessionFactory = sqlSessionFactory; }

    public List<JejuGridDTO> jeju_grid_select_all_with_annotation() {
        SqlSession session = sqlSessionFactory.openSession();
        JejuGridMapper mapper = session.getMapper(JejuGridMapper.class);
        return mapper.get_jeju_grid_all();
    }
}
