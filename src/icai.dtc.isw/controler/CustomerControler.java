package icai.dtc.isw.controler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import main.java.neuraHealthUI.dominio.Usuario;
import neuraHealthUI.dominio.MonthPanel;
import neuraHealthUI.ui.JVentana;

import icai.dtc.isw.dao.CustomerDAO;
import icai.dtc.isw.domain.Customer;
import main.java.neuraHealthUI.dominio.Psicologo;

public class CustomerControler {

	public void getCustomer(ArrayList<Customer> lista) {
		CustomerDAO.getClientes(lista);
	}

	public int autenticarAlUsuario(String id, String nombre) {
		return CustomerDAO.autenticarAlUsuario(id, nombre);
	}

	public int autenticarAlPsicologo(String id, String centro)
	{
		return CustomerDAO.autenticarAlPsicologo(id,centro);
	}

	public void insertarEmociones(String id, HashMap<String,String> hm){CustomerDAO.rellenarAnimo(id,hm);}

	public HashSet<MonthPanel> recuperacionDeAnimos(String id, JVentana ventana){return CustomerDAO.recuperarAnimos(id,ventana);}

	public void insertarHabitos(String id, HashMap<String,String> hm) {CustomerDAO.rellenarHabitos(id,hm);}

	public HashSet<MonthPanel> recuperacionDeHabitos(String id,String habito, JVentana ventana){return CustomerDAO.recuperarHabitos(id,habito,ventana);}

	public HashSet<String> recuperacionNombreHabitos(String id){return CustomerDAO.recuperarNombreHabitos(id);}

	public HashMap<String,String> recuperacionPacientes(String id){return CustomerDAO.recuperarPacientes(id);}
}

