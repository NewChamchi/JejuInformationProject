package service;

import persistence.DAO.*;
import persistence.MybatisConnectionFactory;

public class Service {
    // 네트워크 관련 필드 (미정)

    // DAO 관련 클래스
    SearchTypeDAO searchTypeDAO = new SearchTypeDAO(MybatisConnectionFactory.getSqlSessionFactory());
    SearchAreaDAO searchAreaDAO = new SearchAreaDAO(MybatisConnectionFactory.getSqlSessionFactory());
    // 데이터 베이스 관련 서비스
    public JProtocol.SendExhibitionPacket e_work_read_all(String address) {
        JProtocol.SendExhibitionPacket sendExhibitionPacket = new JProtocol.SendExhibitionPacket(
                JProtocol.PT_EXHIBITION,
                JProtocol.PT_SERVER_RES,
                searchTypeDAO.e_select_basics_all_with_annotation(address),
                searchTypeDAO.e_select_details_all_with_annotation(address));
        return sendExhibitionPacket;
    }

    public JProtocol.SendGoodRestaurantPacket g_work_read_all(String address) {
        JProtocol.SendGoodRestaurantPacket sendGoodRestaurantPacket = new JProtocol.SendGoodRestaurantPacket(
                JProtocol.PT_GOOD_RESTAURANT,
                JProtocol.PT_SERVER_RES,
                searchTypeDAO.g_select_all_with_annotation(address));
        return sendGoodRestaurantPacket;
    }

    public JProtocol.SendHorseridingBackPacket h_work_read_all(String address) {
        JProtocol.SendHorseridingBackPacket sendHorseridingBackPacket = new JProtocol.SendHorseridingBackPacket(
                JProtocol.PT_HORSERIDING_BACK,
                JProtocol.PT_SERVER_RES,
                searchTypeDAO.h_select_basics_all_with_annotation(address),
                searchTypeDAO.h_select_details_all_with_annotation(address));
        return sendHorseridingBackPacket;
    }

    public JProtocol.SendNatureSightBackPacket n_work_read_all(String address) {
        JProtocol.SendNatureSightBackPacket sendNatureSightBackPacket = new JProtocol.SendNatureSightBackPacket(
                JProtocol.PT_NATURE_SIGHT,
                JProtocol.PT_SERVER_RES,
                searchTypeDAO.n_select_basics_all_with_annotation(address),
                searchTypeDAO.n_select_details_all_with_annotation(address));
        return sendNatureSightBackPacket;
    }

    public JProtocol.SendOllehInformationPacket o_work_read_all(String address) {
        JProtocol.SendOllehInformationPacket sendOllehInformationPacket = new JProtocol.SendOllehInformationPacket(
                JProtocol.PT_OLLEH_INFORMATION,
                JProtocol.PT_SERVER_RES,
                searchTypeDAO.o_select_basics_all_with_annotation(address),
                searchTypeDAO.o_select_details_all_with_annotation(address));
        return sendOllehInformationPacket;
    }

    public JProtocol.SendSpotInformationPacket s_work_read_by_type(String address, String type) {
        JProtocol.SendSpotInformationPacket sendSpotInformationPacket = new JProtocol.SendSpotInformationPacket(
                JProtocol.PT_SPOT_INFORMATION,
                JProtocol.PT_SERVER_RES,
                searchTypeDAO.s_select_by_type_with_annotation(address, type));
        return sendSpotInformationPacket;
    }

    public JProtocol.SendSpotInformationPacket s_work_read_by_address(String address) {
        JProtocol.SendSpotInformationPacket sendSpotInformationPacket = new JProtocol.SendSpotInformationPacket(
                JProtocol.PT_AREA_INFORMATION,
                JProtocol.PT_SERVER_RES,
                searchAreaDAO.s_select_by_area_with_annotation(address));
        return sendSpotInformationPacket;
    }

}
