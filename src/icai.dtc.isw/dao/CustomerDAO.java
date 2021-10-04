package icai.dtc.isw.dao;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import icai.dtc.isw.domain.Customer;
import neuraHealthUI.ui.JVentana;
import neuraHealthUI.dominio.MonthPanel;
import neuraHealthUI.dominio.DayPanel;

public class CustomerDAO {



	public static void getClientes(ArrayList<Customer> lista) {
		Connection con=ConnectionDAO.getInstance().getConnection();
		try (PreparedStatement pst = con.prepareStatement("SELECT * FROM usuarios");
			 ResultSet rs = pst.executeQuery()) {

			while (rs.next()) {
				lista.add(new Customer(rs.getString(1),rs.getString(2)));
			}

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		}

	}
	public static int autenticar(String id,String nombre)
	{
		int encontrado =0 ;
		int respuesta=0;
		Connection con=ConnectionDAO.getInstance().getConnection();
		try (PreparedStatement pst = con.prepareStatement("SELECT * FROM usuarios");
			 ResultSet rs = pst.executeQuery()) {

			while (rs.next()) {

				if(nombre.equals(rs.getString(1)) && (id.equals(rs.getString(2))))
				{
					System.out.println("Encontrado bien nombre e id en la bbdd");
					encontrado=1;
					respuesta=1;
				}
				else
				{
					encontrado=0;
				}


			}
			if(encontrado==0)
			{
				System.out.println("NO encontrado en la bbdd");
			}



		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		}
		return respuesta;
	}

	//Añadido el 2 oct-------------------------------------

	public static void rellenarAnimo(String idConectado, HashMap<String,String> fechaYemocion)
	{

		Connection con = ConnectionDAO.getInstance().getConnection();

		for (Map.Entry<String, String> entry : fechaYemocion.entrySet())
		{

			String fecha = entry.getKey();
			String emocion = entry.getValue();

			try (PreparedStatement pst = con.prepareStatement("INSERT INTO usuarioanimos (id,fecha,emocion) VALUES (\'" + idConectado + "\',\'" + fecha + "\',\'" + emocion + "\')");
				 ResultSet rs = pst.executeQuery()) {



			} catch (SQLException ex) {

				System.out.println(ex.getMessage());
			}
		}

	}

	public static HashSet<MonthPanel> recuperarAnimos(String idConectado, JVentana ventana)
	{
		//ArrayList<MonthPanel> mesesArray = new ArrayList<MonthPanel>();
		HashSet<MonthPanel> mesesHSet = new HashSet<MonthPanel>();
		Connection con = ConnectionDAO.getInstance().getConnection();
		HashMap<String, Color> colorEmocion = new HashMap<String, Color>();
		colorEmocion.put("Feliz",new Color(255,153,0));
		colorEmocion.put("Triste",new Color(51,153,255));
		colorEmocion.put("Estresad@",new Color(255,51,51));
		colorEmocion.put("Cansad@",new Color(102,20,153));
		colorEmocion.put("Productiv@",new Color(0,204,0));
		try (PreparedStatement pst = con.prepareStatement("SELECT * FROM usuarioanimos");
			 ResultSet rs = pst.executeQuery()) {
			HashMap<String, String> hmFechaEmocion = new HashMap<String, String>();
			while (rs.next()) {

				if (idConectado.equals(rs.getString(1))) {
					System.out.println("Encuentro el usuario que ha entrado");
					String fecha = rs.getString(2);
					String emocion = rs.getString(3);
					hmFechaEmocion.put(fecha, emocion);
				} else {
					System.out.println("este usuario no tiene emociones guardadas");
				}
			}
			//mesesArray = new ArrayList<MonthPanel>();
			mesesHSet = new HashSet<MonthPanel>();
			HashSet<String> mesAnioSet = new HashSet<String>();
			for (Map.Entry<String, String> entry : hmFechaEmocion.entrySet())
			{
				String fecha = entry.getKey();
				String mesYanio = fecha.substring(2);
				mesAnioSet.add(mesYanio);
			}

			for (String mesAnio : mesAnioSet)
			{
				String nombreMes = mesAnio.substring(0,mesAnio.length()-4);
				String anio = mesAnio.substring(mesAnio.length()-4);
				mesesHSet.add(new MonthPanel(nombreMes,Integer.parseInt(anio),ventana));
			}

			for (MonthPanel mes : mesesHSet)
			{
				for (DayPanel day : mes.getDayArray())
				{
					String diaDosDigitos = day.getDiaDosDigitos();
					for (Map.Entry<String, String> entry : hmFechaEmocion.entrySet())
					{
						String emocion = entry.getValue();
						if (entry.getKey().substring(2).equals(day.getMesYAnio()) && entry.getKey().substring(0,2).equals(diaDosDigitos))
						{
							day.setEmocion(emocion);
							day.setBtnColor(colorEmocion.get(emocion));
						}
					}
				}
			}

			/*HashSet<String> mesAnioSet = new HashSet<String>();
			for (Map.Entry<String, String> entry : hmFechaEmocion.entrySet())
			{
				String fecha = entry.getKey();
				String mesyAnio = fecha.substring(2);
				mesAnioSet.add(mesyAnio);
			}

			for (String mesAnio : mesAnioSet)
			{
				String nombreMes = mesAnio.substring(2,mesAnio.length()-4);
				String anio = mesAnio.substring(mesAnio.length()-4);
				MonthPanel mesNuevo = new MonthPanel(nombreMes,Integer.parseInt(anio),ventana);
				mesesHSet.add(mesNuevo);
			}

			for (MonthPanel mes : mesesHSet)
			{
				String mesYanio = mes.getMesYAnio();
				for (Map.Entry<String, String> entry : hmFechaEmocion.entrySet())
				{
					String fecha = entry.getKey();
					String mesAnioHm = fecha.substring(2);
					if (mesAnioHm.equals(mesYanio))
					{
						String emocion = entry.getValue();
						String dia = fecha.substring(0,2);
						for (DayPanel day : mes.getDayArray())
						{
							if (dia.equals(day.getDiaDosDigitos()))
							{
								day.setEmocion(emocion);
								day.setBtnColor(colorEmocion.get(emocion));
							}
						}
					}
				}
			}*/

			/*for (Map.Entry<String, String> entry : hmFechaEmocion.entrySet())
			{
				String fecha = entry.getKey();
				String emocion = entry.getValue();
				String dia = fecha.substring(0,2);
				for (MonthPanel mes : mesesArray)
				{
					if (mes.getMesYAnio().equals(fecha.substring(2)))
					{
						for (DayPanel day : mes.getDayArray())
						{
							if (dia.equals(day.getDiaDosDigitos()))
							{
								day.setEmocion(emocion);
								day.setBtnColor(colorEmocion.get(emocion));
							}
						}
					}
				}
			}*/
			//ult linea del try
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		//return mesesArray;
		return mesesHSet;
	}


	//-------------------------------------

	public static void main(String[] args) {


		ArrayList<Customer> lista=new ArrayList<Customer>();
		CustomerDAO.getClientes(lista);


		for (Customer customer : lista) {
			System.out.println("He leído el id: "+customer.getId()+" con nombre: "+customer.getName());
		}

	}

}