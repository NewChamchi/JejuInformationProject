package persistence.DAO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.DTO.*;
import persistence.mapper.*;

import java.util.List;

public class SearchTypeDAO {
    private SqlSessionFactory sqlSessionFactory = null;

    public SearchTypeDAO(SqlSessionFactory sqlSessionFactory) { this.sqlSessionFactory = sqlSessionFactory; }

    /* SearchType SELECT */

    public List<ExhibitionDTO> e_select_details_all_with_annotation(String address) {
        SqlSession session = sqlSessionFactory.openSession();
        ExhibitionMapper mapper = session.getMapper(ExhibitionMapper.class);
        return mapper.get_all_exhibition_details(address);
    }

    public List<SpotInformationDTO> e_select_basics_all_with_annotation(String address) {
        SqlSession session = sqlSessionFactory.openSession();
        ExhibitionMapper mapper = session.getMapper(ExhibitionMapper.class);
        return mapper.get_all_exhibition_basics(address);
    }

    public List<GoodRestaurantDTO> g_select_all_with_annotation(String address) {
        SqlSession session = sqlSessionFactory.openSession();
        GoodRestaurantMapper mapper = session.getMapper(GoodRestaurantMapper.class);
        return mapper.get_all_good_restaurant(address);
    }

    public List<HorseridingBackDTO> h_select_details_all_with_annotation(String address) {
        SqlSession session = sqlSessionFactory.openSession();
        HorseridingBackMapper mapper = session.getMapper(HorseridingBackMapper.class);
        return mapper.get_all_horseback_riding_details(address);
    }

    public List<SpotInformationDTO> h_select_basics_all_with_annotation(String address) {
        SqlSession session = sqlSessionFactory.openSession();
        HorseridingBackMapper mapper = session.getMapper(HorseridingBackMapper.class);
        return mapper.get_all_horseback_riding_basics(address);
    }

    public List<NatureSightDTO> n_select_details_all_with_annotation(String address) {
        SqlSession session = sqlSessionFactory.openSession();
        NatureSightMapper mapper = session.getMapper(NatureSightMapper.class);
        return mapper.get_all_nature_sight_details(address);
    }

    public List<SpotInformationDTO> n_select_basics_all_with_annotation(String address) {
        SqlSession session = sqlSessionFactory.openSession();
        NatureSightMapper mapper = session.getMapper(NatureSightMapper.class);
        return mapper.get_all_nature_sight_basics(address);
    }

    public List<OllehInformationDTO> o_select_details_all_with_annotation(String address) {
        SqlSession session = sqlSessionFactory.openSession();
        OllehInformationMapper mapper = session.getMapper(OllehInformationMapper.class);
        return mapper.get_all_olleh_information_details(address);
    }

    public List<SpotInformationDTO> o_select_basics_all_with_annotation(String address) {
        SqlSession session = sqlSessionFactory.openSession();
        OllehInformationMapper mapper = session.getMapper(OllehInformationMapper.class);
        return mapper.get_all_olleh_information_basics(address);
    }

    public List<SpotInformationDTO> s_select_by_type_with_annotation(String address, String type) {
        SqlSession session = sqlSessionFactory.openSession();
        SpotInformationMapper mapper = session.getMapper(SpotInformationMapper.class);
        return mapper.get_spot_information_by_type(address, type);
    }


}
