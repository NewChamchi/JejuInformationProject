package view;

import service.JProtocol;
import service.Service;

import java.util.List;

public class View {

    Service service = new Service();

    // 전시회 데이터 전송
    public JProtocol.SendExhibitionPacket read_exhibition_all(String address) {
        // Service
        JProtocol.SendExhibitionPacket sendExhibitionPacket = service.e_work_read_all(address);

        System.out.println("sendExhibitionPacket = " + sendExhibitionPacket); // 이거 다 안 보이는 이유는 protected 때문인듯?
        System.out.println("sendExhibitionPacket.getProtocolBasicsData() = " + sendExhibitionPacket.getProtocolBasicsData());
        System.out.println("sendExhibitionPacket.getProtocolDetailsData() = " + sendExhibitionPacket.getProtocolDetailsData());
        return sendExhibitionPacket;
    }

    public JProtocol.SendGoodRestaurantPacket read_good_restaurant_all(String address) {
        // Service
        JProtocol.SendGoodRestaurantPacket sendGoodRestaurantPacket = service.g_work_read_all(address);

        System.out.println("sendGoodRestaurantPacket = " + sendGoodRestaurantPacket);
        System.out.println("sendGoodRestaurantPacket.getProtocolData() = " + sendGoodRestaurantPacket.getProtocolData());
        return sendGoodRestaurantPacket;
    }

    public JProtocol.SendHorseridingBackPacket read_horseriding_back_all(String address) {
        // Service
        JProtocol.SendHorseridingBackPacket sendHorseridingBackPacket = service.h_work_read_all(address);

        System.out.println("sendHorseridingBackPacket = " + sendHorseridingBackPacket);
        System.out.println("sendHorseridingBackPacket.getprotocolBasicsData() = " + sendHorseridingBackPacket.getProtocolBasicsData());
        System.out.println("sendHorseridingBackPacket.getprotocolDetailsData() = " + sendHorseridingBackPacket.getProtocolDetailsData());

        return sendHorseridingBackPacket;
    }

    public JProtocol.SendNatureSightBackPacket read_nature_sight_all(String address) {
        // Service
        JProtocol.SendNatureSightBackPacket sendNatureSightBackPacket = service.n_work_read_all(address);

        System.out.println("sendNatureSightBackPacket = " + sendNatureSightBackPacket);
        System.out.println("sendNatureSightBackPacket.getprotocolBasicsData() = " + sendNatureSightBackPacket.getProtocolBasicsData());
        System.out.println("sendNatureSightBackPacket.getprotocolDetailsData() = " + sendNatureSightBackPacket.getProtocolDetailsData());
        return sendNatureSightBackPacket;
    }

    public JProtocol.SendOllehInformationPacket read_olleh_information_all(String address) {
        //Service
        JProtocol.SendOllehInformationPacket sendOllehInformationPacket = service.o_work_read_all(address);

        System.out.println("sendOllehInformationPacket = " + sendOllehInformationPacket);
        System.out.println("sendOllehInformationPacket.getprotocolBasicsData() = " + sendOllehInformationPacket.getProtocolBasicsData());
        System.out.println("sendOllehInformationPacket.getprotocolDetailsData() = " + sendOllehInformationPacket.getProtocolDetailsData());
        return sendOllehInformationPacket;
    }

    public JProtocol.SendSpotInformationPacket read_spot_information_by_type(String address, String type) {
        // Serivce
        JProtocol.SendSpotInformationPacket sendSpotInformationPacket = service.s_work_read_by_type(address, type);

        System.out.println("sendSpotInformationPacket = " + sendSpotInformationPacket);
        System.out.println("sendSpotInformationPacket.getProtocolData() = " + sendSpotInformationPacket.getProtocolData());
        return sendSpotInformationPacket;
    }

    public JProtocol.SendSpotInformationPacket read_spot_information_by_address(String address) {
        JProtocol.SendSpotInformationPacket sendSpotInformationPacket = service.s_work_read_by_address(address);

        System.out.println("sendSpotInformationPacket = " + sendSpotInformationPacket);
        System.out.println("sendSpotInformationPacket.getProtocolData() = " + sendSpotInformationPacket.getProtocolData());
        return sendSpotInformationPacket;
    }

    public JProtocol.SendSpotInformationListPacket read_spot_information_list_by_address_randomly(List<Integer> X, List<Integer> Y) {
        JProtocol.SendSpotInformationListPacket sendSpotInformationListPacket = service.s_work_read_list_by_address_randomly(X, Y);

        System.out.println("sendSpotInformationListPacket = " + sendSpotInformationListPacket);
        System.out.println("sendSpotInformationListPacket.getProtocolData() = " + sendSpotInformationListPacket.getProtocolData());

        return sendSpotInformationListPacket;
    }


}
