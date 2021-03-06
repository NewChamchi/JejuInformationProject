package persistence.DAO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.DTO.SpotInformationDTO;
import persistence.mapper.SpotInformationMapper;

import java.util.List;

public class SearchSuggestionDAO {
    private SqlSessionFactory sqlSessionFactory = null;

    public SearchSuggestionDAO(SqlSessionFactory sqlSessionFactory) { this.sqlSessionFactory = sqlSessionFactory; }

    public List<SpotInformationDTO> s_select_by_area_randomly_with_annotation(String address, int data_amount) {
        SqlSession session = sqlSessionFactory.openSession();
        SpotInformationMapper mapper = session.getMapper(SpotInformationMapper.class);
        return mapper.get_spot_information_random_by_address(address, data_amount);
    }
}
