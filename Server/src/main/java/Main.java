import service.JProtocol;
import view.*;

import java.io.*;
import java.net.*;


// 지금 HORSEBACK_RIDING으로 테이블이 저장되어 있는데 클래스들은 HORSERIDING_BACK으로 저장되어 있음
// 문제 생길 수도 있으니 참고
public class Main {
    public static void main(String[] args) {

        ServerSocket s = null;
        Socket conn = null;

        try {
            s = new ServerSocket();
            s.setReuseAddress(true);
            InetAddress Address = InetAddress.getLocalHost();
            System.out.println(Address.getHostAddress());
            SocketAddress addr = new InetSocketAddress(Address, 3000);
            s.bind(addr);
            System.out.println("waiting for client");

            while (true) {
                conn = s.accept();
                System.out.println("from ip:" + conn.getInetAddress().getHostAddress() + "port:" + conn.getPort());

                new client_handle(conn).start();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        try {
            s.close();
        } catch (IOException ioException) {
            System.err.println("unable to close");
        }
    }
}

class client_handle extends Thread {
    private Socket conn;
    boolean program_stop = false;

    View view = new View();

    client_handle(Socket conn) {
        this.conn = conn;
    }

    public void run() {
        InputStream is;
        ObjectInputStream ois;
        OutputStream os;
        ObjectOutputStream oos;

        boolean program_stop = false;
        try {
            is = conn.getInputStream();
            ois = new ObjectInputStream(is);
            os = conn.getOutputStream();
            oos = new ObjectOutputStream(os);

            do {
                JProtocol.Packet Packet =
                        (JProtocol.Packet) ois.readObject();

                switch (Packet.getProtocolType()) {
                    case JProtocol.PT_UNDEFINED:
                        switch (Packet.getProtocolCode()) {
                            case JProtocol.PT_UNDEFINED:
                                oos.writeObject(Packet);
                                oos.flush();
                                break;
                        }
                        break;
                    case JProtocol.PT_EXHIBITION:
                        switch (Packet.getProtocolCode()) {
                            case JProtocol.PT_CLIENT_REQ:
                                JProtocol.SendExhibitionPacket sendExhibitionPacket = view.read_exhibition_all(
                                        ((JProtocol.ClientSpotDataRequestPacket) Packet).getProtocolAddressData());
                                oos.writeObject(sendExhibitionPacket);
                                oos.flush();
                                break;
                        }
                        break;

                    case JProtocol.PT_GOOD_RESTAURANT:
                        switch (Packet.getProtocolCode()) {
                            case JProtocol.PT_CLIENT_REQ:
                                JProtocol.SendGoodRestaurantPacket sendGoodRestaurantPacket = view.read_good_restaurant_all(
                                        ((JProtocol.ClientSpotDataRequestPacket) Packet).getProtocolAddressData());
                                oos.writeObject(sendGoodRestaurantPacket);
                                oos.flush();
                                break;
                        }
                        break;

                    case JProtocol.PT_HORSERIDING_BACK:
                        switch (Packet.getProtocolCode()) {
                            case JProtocol.PT_CLIENT_REQ:
                                JProtocol.SendHorseridingBackPacket sendHorseridingBackPacket = view.read_horseriding_back_all(
                                        ((JProtocol.ClientSpotDataRequestPacket) Packet).getProtocolAddressData());
                                oos.writeObject(sendHorseridingBackPacket);
                                oos.flush();
                                break;
                        }
                        break;

                    case JProtocol.PT_NATURE_SIGHT:
                        switch (Packet.getProtocolCode()) {
                            case JProtocol.PT_CLIENT_REQ:
                                JProtocol.SendNatureSightBackPacket sendNatureSightBackPacket = view.read_nature_sight_all(
                                        ((JProtocol.ClientSpotDataRequestPacket) Packet).getProtocolAddressData());
                                oos.writeObject(sendNatureSightBackPacket);
                                oos.flush();
                                break;
                        }
                        break;

                    case JProtocol.PT_OLLEH_INFORMATION:
                        switch (Packet.getProtocolCode()) {
                            case JProtocol.PT_CLIENT_REQ:
                                JProtocol.SendOllehInformationPacket sendOllehInformationPacket = view.read_olleh_information_all(
                                        ((JProtocol.ClientSpotDataRequestPacket) Packet).getProtocolAddressData());
                                oos.writeObject(sendOllehInformationPacket);
                                oos.flush();
                                break;
                        }
                        break;

                    case JProtocol.PT_SPOT_INFORMATION:
                        switch (Packet.getProtocolCode()) {
                            case JProtocol.PT_CLIENT_REQ:
                                JProtocol.SendSpotInformationPacket sendSpotInformationPacket = view.read_spot_information_by_type(
                                        ((JProtocol.ClientSpotDataRequestPacket) Packet).getProtocolAddressData(),
                                        ((JProtocol.ClientSpotDataRequestPacket) Packet).getProtocolTypeData());
                                oos.writeObject(sendSpotInformationPacket);
                                oos.flush();
                                break;
                        }
                        break;

                    case JProtocol.PT_AREA_INFORMATION:
                        switch (Packet.getProtocolCode()) {
                            case JProtocol.PT_CLIENT_REQ:
                                JProtocol.SendSpotInformationPacket sendSpotInformationPacket = view.read_spot_information_by_address(
                                        ((JProtocol.ClientSpotDataRequestPacket) Packet).getProtocolAddressData());
                                oos.writeObject(sendSpotInformationPacket);
                                oos.flush();
                                break;
                        }
                        break;

                    case JProtocol.PT_RECOMMEND_SPOT:
                        switch (Packet.getProtocolCode()) {
                            case JProtocol.PT_CLIENT_REQ:
                                oos.writeObject("");
                                oos.flush();
                                break;
                        }
                        break;
                }

            } while (!program_stop);

            ois.close();
            os.close();
            oos.close();
            os.close();
            conn.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
