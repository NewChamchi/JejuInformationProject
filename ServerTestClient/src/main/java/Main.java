import persistence.DTO.JejuGridDTO;
import service.JProtocol;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        InetAddress addr = InetAddress.getLocalHost();
        String localname = addr.getHostName();
        /*/
        String ip = "192.168.0.4"; //*/
        //*/
        String ip = "192.168.235.86"; //*/
        System.out.println(localname + ip);
        Socket socket = new Socket(ip, 3000); // 아이피 확인은 실행 -> cmd -> ipconfig 후 IPv4를 복사하면 됨
        System.out.println("connect");
        boolean program_stop = false;
        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);
        ObjectInputStream ois = new ObjectInputStream(is);
        Scanner sc = new Scanner(System.in);

        do {
            PrintMenu();
            int menu_result = sc.nextInt();
            JProtocol.ClientSpotDataRequestPacket clientSpotDataRequestPacket = new JProtocol.ClientSpotDataRequestPacket();
            JProtocol.ClientGridRequestPacket clientGridRequestPacket = new JProtocol.ClientGridRequestPacket();
            JProtocol.ClientRecommendedSpotRequestPacket clientRecommendedSpotRequestPacket = new JProtocol.ClientRecommendedSpotRequestPacket();
            switch (menu_result) {
                case 1:
                    clientSpotDataRequestPacket.setPacket(JProtocol.PT_EXHIBITION, JProtocol.PT_CLIENT_REQ, "", "");
                    oos.writeObject(clientSpotDataRequestPacket);
                    oos.flush();
                    break;
                case 2:
                    clientSpotDataRequestPacket.setPacket(JProtocol.PT_GOOD_RESTAURANT, JProtocol.PT_CLIENT_REQ, "", "");
                    oos.writeObject(clientSpotDataRequestPacket);
                    oos.flush();
                    break;
                case 3:
                    clientSpotDataRequestPacket.setPacket(JProtocol.PT_HORSERIDING_BACK, JProtocol.PT_CLIENT_REQ, "", "");
                    oos.writeObject(clientSpotDataRequestPacket);
                    oos.flush();
                    break;
                case 4:
                    clientSpotDataRequestPacket.setPacket(JProtocol.PT_NATURE_SIGHT, JProtocol.PT_CLIENT_REQ, "", "");
                    oos.writeObject(clientSpotDataRequestPacket);
                    oos.flush();
                    break;
                case 5:
                    clientSpotDataRequestPacket.setPacket(JProtocol.PT_OLLEH_INFORMATION, JProtocol.PT_CLIENT_REQ, "", "");
                    oos.writeObject(clientSpotDataRequestPacket);
                    oos.flush();
                    break;
                case 6:
                    clientSpotDataRequestPacket.setPacket(JProtocol.PT_SPOT_INFORMATION, JProtocol.PT_CLIENT_REQ, "", "농원");
                    oos.writeObject(clientSpotDataRequestPacket);
                    oos.flush();
                    break;
                case 7:
                    clientSpotDataRequestPacket.setPacket(JProtocol.PT_SPOT_INFORMATION, JProtocol.PT_CLIENT_REQ, "", "오름");
                    oos.writeObject(clientSpotDataRequestPacket);
                    oos.flush();
                    break;
                case 8:
                    clientSpotDataRequestPacket.setPacket(JProtocol.PT_SPOT_INFORMATION, JProtocol.PT_CLIENT_REQ, "", "무장애 여행지");
                    oos.writeObject(clientSpotDataRequestPacket);
                    oos.flush();
                    break;
                case 9:
                    clientSpotDataRequestPacket.setPacket(JProtocol.PT_AREA_INFORMATION, JProtocol.PT_CLIENT_REQ, "지역", "");
                    oos.writeObject(clientSpotDataRequestPacket);
                    oos.flush();
                    break;
                case 10:
                    clientGridRequestPacket.setPacket(JProtocol.PT_JEJU_GRID, JProtocol.PT_CLIENT_REQ);
                    oos.writeObject(clientGridRequestPacket);
                    oos.flush();
                    break;
                case 11:
                    // 임의로 넣은 값임
                    ArrayList xdata = new ArrayList();
                    xdata.add(55);
                    xdata.add(54);
                    xdata.add(53);
                    ArrayList ydata = new ArrayList();
                    ydata.add(32);
                    ydata.add(33);
                    ydata.add(37);
                    ArrayList weightdata = new ArrayList();
                    weightdata.add(50.3);
                    weightdata.add(44.7);
                    weightdata.add(37.2);
                    clientRecommendedSpotRequestPacket.setPacket(JProtocol.PT_RECOMMEND_SPOT, JProtocol.PT_CLIENT_REQ, xdata, ydata, weightdata);
                    oos.writeObject(clientRecommendedSpotRequestPacket);
                    oos.flush();
                    break;
                case 0:
                    program_stop = true;
                    continue;
                default :
                    System.out.println("long number");
                    continue;
            }

            JProtocol.Packet serverMessagePacket = (JProtocol.Packet) ois.readObject();

            switch (serverMessagePacket.getProtocolType()) {
                case JProtocol.PT_UNDEFINED:
                    switch (serverMessagePacket.getProtocolCode()) {
                        case JProtocol.PT_UNDEFINED:
                            System.out.println("패킷이 제대로 전송되지 않음.");
                            break;
                    }
                    break;
                case JProtocol.PT_EXHIBITION:
                    switch (serverMessagePacket.getProtocolCode()) {
                        case JProtocol.PT_SERVER_RES:
                            System.out.println("((JProtocol.SendExhibitionPacket) serverMessagePacket).getProtocolData() = " +
                                    ((JProtocol.SendExhibitionPacket) serverMessagePacket).getProtocolBasicsData());
                            System.out.println("((JProtocol.SendExhibitionPacket) serverMessagePacket).getProtocolDetailsData() = " +
                                    ((JProtocol.SendExhibitionPacket) serverMessagePacket).getProtocolDetailsData());
                            break;
                    }
                    break;
                case JProtocol.PT_GOOD_RESTAURANT:
                    switch (serverMessagePacket.getProtocolCode()) {
                        case JProtocol.PT_SERVER_RES:
                            System.out.println("((JProtocol.SendGoodRestaurantPacket)serverMessagePacket).getProtocolData() = " +
                                    ((JProtocol.SendGoodRestaurantPacket)serverMessagePacket).getProtocolData());
                            break;
                    }
                    break;
                case JProtocol.PT_HORSERIDING_BACK:
                    switch (serverMessagePacket.getProtocolCode()) {
                        case JProtocol.PT_SERVER_RES:
                            System.out.println("((JProtocol.SendHorseridingBackPacket) serverMessagePacket).getProtocolBasicsData() = " +
                                    ((JProtocol.SendHorseridingBackPacket) serverMessagePacket).getProtocolBasicsData());
                            System.out.println("((JProtocol.SendHorseridingBackPacket) serverMessagePacket).getProtocolDetailsData() = " +
                                    ((JProtocol.SendHorseridingBackPacket) serverMessagePacket).getProtocolDetailsData());
                            break;
                    }
                    break;
                case JProtocol.PT_NATURE_SIGHT:
                    switch (serverMessagePacket.getProtocolCode()) {
                        case JProtocol.PT_SERVER_RES:
                            System.out.println("((JProtocol.SendNatureSightBackPacket) serverMessagePacket).getProtocolBasicsData() = " +
                                    ((JProtocol.SendNatureSightBackPacket) serverMessagePacket).getProtocolBasicsData());
                            System.out.println("((JProtocol.SendNatureSightBackPacket) serverMessagePacket).getProtocolDetailsData() = " +
                                    ((JProtocol.SendNatureSightBackPacket) serverMessagePacket).getProtocolDetailsData());
                            break;
                    }
                    break;
                case JProtocol.PT_OLLEH_INFORMATION:
                    switch (serverMessagePacket.getProtocolCode()) {
                        case JProtocol.PT_SERVER_RES:
                            System.out.println("((JProtocol.SendOllehInformationPacket)serverMessagePacket).getProtocolBasicsData() = " +
                                    ((JProtocol.SendOllehInformationPacket)serverMessagePacket).getProtocolBasicsData());
                            System.out.println("((JProtocol.SendOllehInformationPacket)serverMessagePacket).getProtocolDetailsData() = " +
                                    ((JProtocol.SendOllehInformationPacket)serverMessagePacket).getProtocolDetailsData());
                            break;
                    }
                    break;
                case JProtocol.PT_SPOT_INFORMATION: // 사실상 PT_SPOT_INFORMATION과 PT_AREA_INFROMATION이 받는 데이터 형식이 같음
                case JProtocol.PT_AREA_INFORMATION:
                    switch (serverMessagePacket.getProtocolCode()) {
                        case JProtocol.PT_SERVER_RES:
                            System.out.println("((JProtocol.SendSpotInformationPacket)serverMessagePacket).getProtocolData() = " +
                                    ((JProtocol.SendSpotInformationPacket)serverMessagePacket).getProtocolData());
                            break;
                    }
                    break;
                case JProtocol.PT_JEJU_GRID:
                    switch (serverMessagePacket.getProtocolCode()) {
                        case JProtocol.PT_SERVER_RES:
                            System.out.println("((JProtocol.SendJejuGridListPacket)serverMessagePacket).getProtocolGridData() = " +
                                    ((JProtocol.SendJejuGridListPacket)serverMessagePacket).getProtocolGridData());
                            // 제주도 x좌표, y좌표 값 얻는 법(예시일 뿐! 참고해서 바꿀 것)
                            List<JejuGridDTO> jejuGridDTOS =((JProtocol.SendJejuGridListPacket)serverMessagePacket).getProtocolGridData();
                            List<Integer> x = new ArrayList<>();
                            for(JejuGridDTO obj : jejuGridDTOS) {
                                x.add(obj.getJejugridx());
                            }
                            List<Integer> y = new ArrayList<>();
                            for(JejuGridDTO obj : jejuGridDTOS) {
                                y.add(obj.getJejugridy());
                            }
                            break;
                    }
                    break;
                case JProtocol.PT_RECOMMEND_SPOT:
                    switch (serverMessagePacket.getProtocolCode()) {
                        case JProtocol.PT_SERVER_RES:
                            System.out.println("((JProtocol.SendSpotInformationListPacket)serverMessagePacket).getProtocolData() = " +
                                    ((JProtocol.SendSpotInformationListPacket)serverMessagePacket).getProtocolData());
                            break;
                    }
                    break;

            }

        } while (!program_stop);

        is.close();
        ois.close();
        os.close();
        oos.close();
        socket.close();
        sc.close();


    }

    public static void PrintMenu() {
        System.out.println("0. 프로그램 종료");
        System.out.println("1. 전시관 조회");
        System.out.println("2. 모범음식점 조회");
        System.out.println("3. 승마장 조회");
        System.out.println("4. 자연 명소 조회");
        System.out.println("5. 올레길 조회");
        System.out.println("6. 농원 조회");
        System.out.println("7. 오름 조회");
        System.out.println("8. 무장애 여행지 조회");
        System.out.println("9. 제주도 지역별 관광지 조회");
        System.out.println("10. 제주도 격자 요청");
        System.out.println("11. 날씨에 따른 추천 관광지 요청");
        System.out.print("메뉴 선택 : ");
    }
}
