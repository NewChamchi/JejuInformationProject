package persistence.DAO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.DTO.JejuGridDTO;
import persistence.DTO.SpotInformationDTO;
import persistence.mapper.JejuGridMapper;
import persistence.mapper.SpotInformationMapper;

import java.util.List;

public class SearchSuggestionDAO {
    private SqlSessionFactory sqlSessionFactory = null;

    public SearchSuggestionDAO(SqlSessionFactory sqlSessionFactory) { this.sqlSessionFactory = sqlSessionFactory; }

    public List<SpotInformationDTO> s_select_by_area_randomly_with_annotation(String[] address, int data_amount) {
        SqlSession session = sqlSessionFactory.openSession();
        SpotInformationMapper mapper = session.getMapper(SpotInformationMapper.class);
        return mapper.get_spot_information_random_by_address(address, data_amount);
    }

    public List<JejuGridDTO> j_select_local_by_grid_with_annotation(Integer X, Integer Y) {
        SqlSession session = sqlSessionFactory.openSession();
        JejuGridMapper mapper = session.getMapper(JejuGridMapper.class);
        return mapper.get_local_jeju_grid(X, Y);
    }
}
