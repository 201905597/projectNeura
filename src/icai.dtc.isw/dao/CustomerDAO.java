package icai.dtc.isw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

import icai.dtc.isw.domain.Customer;

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
					encontrado=1;
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
	
	public static void main(String[] args) {
		
		
		ArrayList<Customer> lista=new ArrayList<Customer>();
		CustomerDAO.getClientes(lista);
		
		
		 for (Customer customer : lista) {			
			System.out.println("He leído el id: "+customer.getId()+" con nombre: "+customer.getName());
		}
		
	
	}

}
