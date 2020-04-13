package exam0407;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UdpServer {
	public void connect_process() {
		try {
			DatagramSocket socket = new DatagramSocket(7000);
			DatagramPacket inDP, outDP = null;
			byte[] inMsg = new byte[10];
			byte[] outMsg = null;
			while(true) {
				inDP = new DatagramPacket(inMsg, inMsg.length);
				socket.receive(inDP);
				//수신한 패킷에서 사용자의 ip와 port을 얻을 수 있다.
				InetAddress iaddr = inDP.getAddress();//클라이언트 네트워크정보
				int port = inDP.getPort();//클라이언트의 포트정보
				//서버의 현재 시간정보
				SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
				String time = sdf.format(new Date());
				outMsg = time.getBytes();//시간정보를 byte배열로 변환
				//패킷생성 client전송
				outDP = new DatagramPacket(outMsg,outMsg.length,iaddr,port);
				socket.send(outDP);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		UdpServer us = new UdpServer();
		us.connect_process();
}
}