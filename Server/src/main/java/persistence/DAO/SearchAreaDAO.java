package persistence.DAO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.DTO.SpotInformationDTO;
import persistence.mapper.SpotInformationMapper;

import java.util.List;

public class SearchAreaDAO {
    private SqlSessionFactory sqlSessionFactory = null;

    public SearchAreaDAO(SqlSessionFactory sqlSessionFactory) { this.sqlSessionFactory = sqlSessionFactory; }

    public List<SpotInformationDTO> s_select_by_area_with_annotation(String address) {
        SqlSession session = sqlSessionFactory.openSession();
        SpotInformationMapper mapper = session.getMapper(SpotInformationMapper.class);
        return mapper.get_spot_information_by_address(address);
    }
}
