package service;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import persistence.DTO.*;

import java.io.Serializable;
import java.util.List;


public class JProtocol {

    public static final int PT_UNDEFINED = -1;
    public static final int PT_EXIT = 0;

    // TYPE 정보
    public static final int PT_EXHIBITION = 1;
    public static final int PT_GOOD_RESTAURANT = 2;
    public static final int PT_HORSERIDING_BACK = 3;
    public static final int PT_NATURE_SIGHT = 4;
    public static final int PT_OLLEH_INFORMATION = 5;
    public static final int PT_SPOT_INFORMATION = 6;
    public static final int PT_AREA_INFORMATION = 7;
    public static final int PT_JEJU_GRID = 8;
    public static final int PT_RECOMMEND_SPOT = 9;
    // CODE 정보
    public static final int PT_CLIENT_REQ = 1;
    public static final int PT_SERVER_RES = 2;

    @Getter
    @Setter
    @ToString
    public static class Packet implements Serializable {
        protected int protocolType;
        protected int protocolCode;

        public Packet() {
            this.protocolType = PT_UNDEFINED;
            this.protocolCode = PT_UNDEFINED;
        }

        public Packet(int protocolType, int protocolCode) {
            this.protocolType = protocolType;
            this.protocolCode = protocolCode;
        }

        public void setPacket (int protocolType, int protocolCode) {
            this.protocolType = protocolType;
            this.protocolCode = protocolCode;
        }

        public int getProtocolType() {
            return protocolType;
        }

        public int getProtocolCode() {
            return protocolCode;
        }
    }

    // 클라이언트에서 서버로 요청하는 메시지 패킷
    public static class ClientSpotDataRequestPacket extends Packet {
        protected String protocolAddressData;
        protected String protocolTypeData;
        // TypeData는 농원, 무장애여행지 같이 별도의 테이블 없이 관광지 정보로만 존재하는 데이터를 요청할 때 쓰이는 데이터입니다.
        public ClientSpotDataRequestPacket() {
            super();
        }

        public ClientSpotDataRequestPacket(int protocolType, int protocolCode, String protocolAddressData, String protocolTypeData) {
            super(protocolType, protocolCode);
            this.protocolAddressData = protocolAddressData;
            this.protocolTypeData = protocolTypeData;
        }

        public void setPacket (int protocolType, int protocolCode, String protocolAddressData, String protocolTypeData) {
            super.setPacket(protocolType, protocolCode);
            this.protocolAddressData = protocolAddressData;
            this.protocolTypeData = protocolTypeData;
        }

        public String getProtocolAddressData() {
            return protocolAddressData;
        }

        public String getProtocolTypeData() {
            return protocolTypeData;
        }
    }

    public static class ClientGridRequestPacket extends Packet { }

    public static class ClientRecommendedSpotRequestPacket extends Packet {
        protected List<Integer> protocolX;
        protected List<Integer> protocolY;
        protected List<Float> protocolWeight;

        public ClientRecommendedSpotRequestPacket() { super(); }

        public ClientRecommendedSpotRequestPacket(int protocolType, int protocolCode, List<Integer> protocolX,
                                                  List<Integer> protocolY, List<Float> protocolWeight) {
            super(protocolType, protocolCode);
            this.protocolX = protocolX;
            this.protocolY = protocolY;
            this.protocolWeight = protocolWeight;
        }

        public void setPacket(int protocolType, int protocolCode, List<Integer> protocolX,
                              List<Integer> protocolY, List<Float> protocolWeight) {
            super.setPacket(protocolType, protocolCode);
            this.protocolX = protocolX;
            this.protocolY = protocolY;
            this.protocolWeight = protocolWeight;
        }


        public List<Integer> getProtocolX() {
            return protocolX;
        }

        public List<Integer> getProtocolY() {
            return protocolY;
        }

        public List<Float> getProtocolWeight() {
            return protocolWeight;
        }
    }

    // 서버가 클라이언트에게 응답 전송하는 메시지 패킷
    public static class SendExhibitionPacket extends Packet {
        protected List<SpotInformationDTO> protocolBasicsData;
        protected List<ExhibitionDTO> protocolDetailsData;

        public SendExhibitionPacket(int protocolType, int protocolCode, List<SpotInformationDTO> protocolBasicData, List<ExhibitionDTO> protocolDetailsData) {
            super(protocolType, protocolCode);
            this.protocolBasicsData = protocolBasicData;
            this.protocolDetailsData = protocolDetailsData;
        }

        public void setPacket (int protocolType, int protocolCode, List<SpotInformationDTO> protocolBasicData, List<ExhibitionDTO> protocolDetailsData) {
            super.setPacket(protocolType, protocolCode);
            this.protocolBasicsData = protocolBasicData;
            this.protocolDetailsData = protocolDetailsData;
        }

        public List<SpotInformationDTO> getProtocolBasicsData() {
            return protocolBasicsData;
        }
        public List<ExhibitionDTO> getProtocolDetailsData() {
            return protocolDetailsData;
        }
    }

    public static class SendGoodRestaurantPacket extends Packet {
        protected List<GoodRestaurantDTO> protocolData;

        public SendGoodRestaurantPacket(int protocolType, int protocolCode, List<GoodRestaurantDTO> protocolData) {
            super(protocolType, protocolCode);
            this.protocolData = protocolData;
        }

        public void setPacket (int protocolType, int protocolCode, List<GoodRestaurantDTO> protocolData) {
            super.setPacket(protocolType, protocolCode);
            this.protocolData = protocolData;
        }

        public List<GoodRestaurantDTO> getProtocolData() {
            return protocolData;
        }
    }

    public static class SendHorseridingBackPacket extends Packet {
        protected List<SpotInformationDTO> protocolBasicsData;
        protected List<HorseridingBackDTO> protocolDetailsData;

        public SendHorseridingBackPacket(int protocolType, int protocolCode, List<SpotInformationDTO> protocolBasicsData, List<HorseridingBackDTO> protocolDetailsData) {
            super(protocolType, protocolCode);
            this.protocolBasicsData = protocolBasicsData;
            this.protocolDetailsData = protocolDetailsData;
        }

        public void setPacket (int protocolType, int protocolCode,
                               List<SpotInformationDTO> protocolBasicsData, List<HorseridingBackDTO> protocolDetailsData) {
            super.setPacket(protocolType, protocolCode);
            this.protocolBasicsData = protocolBasicsData;
            this.protocolDetailsData = protocolDetailsData;
        }

        public List<SpotInformationDTO> getProtocolBasicsData() {
            return protocolBasicsData;
        }

        public List<HorseridingBackDTO> getProtocolDetailsData() {
            return protocolDetailsData;
        }
    }

    public static class SendNatureSightBackPacket extends Packet {
        protected List<SpotInformationDTO> protocolBasicsData;
        protected List<NatureSightDTO> protocolDetailsData;

        public SendNatureSightBackPacket(int protocolType, int protocolCode, List<SpotInformationDTO> protocolBasicsData, List<NatureSightDTO> protocolDetailsData) {
            super(protocolType, protocolCode);
            this.protocolBasicsData = protocolBasicsData;
            this.protocolDetailsData = protocolDetailsData;
        }

        public void setPacket (int protocolType, int protocolCode,
                               List<SpotInformationDTO> protocolBasicsData, List<NatureSightDTO> protocolDetailsData) {
            super.setPacket(protocolType, protocolCode);
            this.protocolBasicsData = protocolBasicsData;
            this.protocolDetailsData = protocolDetailsData;
        }

        public List<SpotInformationDTO> getProtocolBasicsData() {
            return protocolBasicsData;
        }

        public List<NatureSightDTO> getProtocolDetailsData() {
            return protocolDetailsData;
        }
    }

    public static class SendOllehInformationPacket extends Packet {
        protected List<SpotInformationDTO> protocolBasicsData;
        protected List<OllehInformationDTO> protocolDetailsData;

        public SendOllehInformationPacket(int protocolType, int protocolCode, List<SpotInformationDTO> protocolBasicsData, List<OllehInformationDTO> protocolDetailsData) {
            super(protocolType, protocolCode);
            this.protocolBasicsData = protocolBasicsData;
            this.protocolDetailsData = protocolDetailsData;
        }

        public void setPacket (int protocolType, int protocolCode,
                               List<SpotInformationDTO> protocolBasicsData, List<OllehInformationDTO> protocolDetailsData) {
            super.setPacket(protocolType, protocolCode);
            this.protocolBasicsData = protocolBasicsData;
            this.protocolDetailsData = protocolDetailsData;
        }

        public List<SpotInformationDTO> getProtocolBasicsData() {
            return protocolBasicsData;
        }

        public List<OllehInformationDTO> getProtocolDetailsData() {
            return protocolDetailsData;
        }
    }

    public static class SendSpotInformationPacket extends Packet {
        protected List<SpotInformationDTO> protocolData;

        public SendSpotInformationPacket(int protocolType, int protocolCode, List<SpotInformationDTO> protocolData) {
            super(protocolType, protocolCode);
            this.protocolData = protocolData;
        }

        public void setPacket (int protocolType, int protocolCode, List<SpotInformationDTO> protocolData) {
            super.setPacket(protocolType, protocolCode);
            this.protocolData = protocolData;
        }

        public List<SpotInformationDTO> getProtocolData() {
            return protocolData;
        }
    }

    public static class SendSpotInformationListPacket extends Packet {
        protected List<List<SpotInformationDTO>> protocolData;

        public SendSpotInformationListPacket(int protocolType, int protocolCode, List<List<SpotInformationDTO>> protocolData) {
            super(protocolType, protocolCode);
            this.protocolData = protocolData;
        }

        public void setPacket (int protocolType, int protocolCode, List<List<SpotInformationDTO>> protocolData) {
            super.setPacket(protocolType, protocolCode);
            this.protocolData = protocolData;
        }

        public List<List<SpotInformationDTO>> getProtocolData() {
            return protocolData;
        }
    }

    public static class SendJejuGridListPacket extends Packet {
        protected List<JejuGridDTO> protocolGridData;

        public SendJejuGridListPacket(int protocolType, int protocolCode, List<JejuGridDTO> protocolGridData) {
            super(protocolType, protocolCode);
            this.protocolGridData = protocolGridData;
        }

        public void setPacket(int protocolType, int protocolCode, List<JejuGridDTO> protocolGridData) {
            super.setPacket(protocolType, protocolCode);
            this.protocolGridData = protocolGridData;
        }

        public List<JejuGridDTO> getProtocolGridData() {
            return protocolGridData;
        }
    }
}
