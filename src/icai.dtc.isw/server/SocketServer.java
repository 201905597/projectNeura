package icai.dtc.isw.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import icai.dtc.isw.controler.CustomerControler;
import icai.dtc.isw.domain.Customer;
import icai.dtc.isw.message.Message;

public class SocketServer extends Thread {
	public static final int PORT_NUMBER = 8081;

	protected Socket socket;

	private SocketServer(Socket socket) {
		this.socket = socket;
		System.out.println("New client connected from " + socket.getInetAddress().getHostAddress());
		start();
	}

	////////EL CORRECTOOOOOOOOOOOO
	public void run() {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = socket.getInputStream();
			out = socket.getOutputStream();

			//first read the object that has been sent
			ObjectInputStream objectInputStream = new ObjectInputStream(in);
			Message mensajeIn= (Message)objectInputStream.readObject();
			//Object to return informations
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
			Message mensajeOut=new Message();
			switch (mensajeIn.getContext()) {
				case ("/peticionAcceso"):
					CustomerControler customerControler=new CustomerControler();
					ArrayList<Customer> lista=new ArrayList<Customer>();
					int var=customerControler.autenticarAlUsuario((String)mensajeIn.getSession().get("id"),(String)mensajeIn.getSession().get("nombre"));
					mensajeOut.setContext("/peticionAccesoResponse");
					HashMap<String,Object> session=new HashMap<String, Object>();
					session.put("RespuestaAcceso",var);
					mensajeOut.setSession(session);
					objectOutputStream.writeObject(mensajeOut);
					break;


				default:
					System.out.println("\nParámetro no encontrado");
					break;
			}

		} catch (IOException ex) {
			System.out.println("Unable to get streams from client");
		} catch (ClassNotFoundException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
				socket.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("SocketServer Example");
		ServerSocket server = null;
		try {
			server = new ServerSocket(PORT_NUMBER);
			while (true) {
				/**
				 * create a new {@link SocketServer} object for each connection
				 * this will allow multiple client connections
				 */
				new SocketServer(server.accept());
			}
		} catch (IOException ex) {
			System.out.println("Unable to start server.");
		} finally {
			try {
				if (server != null)
					server.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}