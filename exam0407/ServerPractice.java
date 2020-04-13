package exam0407;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerPractice extends Thread {
	ServerSocket	server	= null;
	Socket			socket  = null;
	ObjectInputStream	ois	= null;
	ObjectOutputStream	oos = null;
	boolean			isOk	= false;
	@Override
	public void run() {
		try {
			server = new ServerSocket(3000);
			System.out.println("대기중");
		} catch (Exception e) {
			// TODO: handle exception
		}
			while(true) {
				try {
					socket = server.accept();
					System.out.println(socket.getInetAddress());
					oos = new ObjectOutputStream(socket.getOutputStream());
					ois = new ObjectInputStream(socket.getInputStream());
					oos.writeObject("안녕 난 서버야");
					System.out.println(ois.readObject());
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	
	public static void main(String[] args) {
		ServerPractice sp = new ServerPractice();
		sp.start();
	}

}
