package exam0407;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientPractice {
	Socket	socket = null;
	final String _IP = "192.168.0.218";
	final int _PORT = 3000;
	ObjectOutputStream	oos = null;
	ObjectInputStream	ois = null;
	
	public void client() {
		try {
			socket = new Socket(_IP,_PORT);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			System.out.println(ois.readObject());
			oos.writeObject("안녕 난 클라이언트야");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void main(String[] args) {
		ClientPractice cp = new ClientPractice();
		cp.client();
	}

}
