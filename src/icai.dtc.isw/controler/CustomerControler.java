package icai.dtc.isw.controler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import neuraHealthUI.dominio.MonthPanel;
import neuraHealthUI.ui.JVentana;

import icai.dtc.isw.dao.CustomerDAO;
import icai.dtc.isw.domain.Customer;

public class CustomerControler {

	public void getCustomer(ArrayList<Customer> lista) {
		CustomerDAO.getClientes(lista);
	}

	public int autenticarAlUsuario(String id, String nombre) {
		return CustomerDAO.autenticar(id, nombre);
	}

	public void insertarEmociones(String id, HashMap<String,String> hm){CustomerDAO.rellenarAnimo(id,hm);}

	public HashSet<MonthPanel> recuperacionDeAnimos(String id, JVentana ventana){return CustomerDAO.recuperarAnimos(id,ventana);}
}

