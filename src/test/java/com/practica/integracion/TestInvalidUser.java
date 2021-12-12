package com.practica.integracion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.naming.OperationNotSupportedException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.junit.jupiter.MockitoExtension;

import com.practica.integracion.DAO.*;
import com.practica.integracion.manager.SystemManager;
import com.practica.integracion.manager.SystemManagerException;


@ExtendWith(MockitoExtension.class)
public class TestInvalidUser {
	
	Object ob = new Object();
	 
	GenericDAO mockGenericDao = mock(GenericDAO.class);
	AuthDAO mockAuthDao = mock(AuthDAO.class);

	@Test
	@DisplayName("Start: InvalidUser + ValidId")
	public void testStartRemoteSystemWithInValidUserAndValidSystem() throws Exception {
	  User invalidUser = new User("1","Lucas","Pérez","Madrid", new ArrayList<Object>(Arrays.asList(1, 2)));
	  when(mockAuthDao.getAuthData(invalidUser.getId())).thenReturn(null); //no autenticado
	  
	  OperationNotSupportedException e = new OperationNotSupportedException();
	  String validId = "12345"; 
	
	  when(mockGenericDao.getSomeData(invalidUser, "where id=" + validId)).thenThrow(e); //se lanza excepcion porque no esta autenticado
	 
	  InOrder ordered = inOrder(mockAuthDao, mockGenericDao);
	  
	  SystemManager manager = new SystemManager(mockAuthDao, mockGenericDao);
	 	  	  
	  ordered.verify(mockAuthDao).getAuthData(invalidUser.getId());
	  ordered.verify(mockGenericDao).getSomeData(invalidUser, "where id=" + validId);
	}
	
	
	@Test
	@DisplayName("Start: InvalidUser + InvalidId")
	public void testStartRemoteSystemWithInValidUserAndInValidSystem() throws Exception {
	  User invalidUser = new User("1","Lucas","Pérez","Madrid", new ArrayList<Object>(Arrays.asList(1, 2)));
	  when(mockAuthDao.getAuthData(invalidUser.getId())).thenReturn(null);

	  OperationNotSupportedException e = new OperationNotSupportedException();
	  String invalidId = "98765"; 
	  
	  when(mockGenericDao.getSomeData(invalidUser, "where id=" + invalidId)).thenThrow(e);
	 
	  InOrder ordered = inOrder(mockAuthDao, mockGenericDao);
	  
	  SystemManager manager = new SystemManager(mockAuthDao, mockGenericDao);
	 	  
	  ordered.verify(mockAuthDao).getAuthData(invalidUser.getId());
	  ordered.verify(mockGenericDao).getSomeData(invalidUser, "where id=" + invalidId);
	}
	
	//HEMOS CONSIDERADO QUE NO SE VA A REALIZAR EN ESTA CLASE EL CASO DE PRUEBA DONDE FILTER ES NULL YA QUE 
	//PRIMERO DEBERÁ AUTENTICARSE PARA PODER DEVOLVER ALGUN TIPO DE DATO. EN ESTE CASO COMO EL USUARIO ES INVALIDO NO SE AUTENTICA 
	//-------------------------------------------------------------------------
	
	@Test
	@DisplayName("Stop: InvalidUser + ValidId")
	public void testStopRemoteSystemWithInValidUserAndValidSystem() throws Exception {
	  User invalidUser = new User("1","Lucas","Pérez","Madrid", new ArrayList<Object>(Arrays.asList(1, 2)));
	  when(mockAuthDao.getAuthData(invalidUser.getId())).thenReturn(null); //no autenticado
	  
	  OperationNotSupportedException e = new OperationNotSupportedException();
	  String validId = "12345"; 
	  
	  when(mockGenericDao.getSomeData(invalidUser, "where id=" + validId)).thenThrow(e); //se lanza excepcion porque no esta autenticado
	 
	  InOrder ordered = inOrder(mockAuthDao, mockGenericDao);
	  
	  SystemManager manager = new SystemManager(mockAuthDao, mockGenericDao);
	  	  
	  ordered.verify(mockAuthDao).getAuthData(invalidUser.getId());
	  ordered.verify(mockGenericDao).getSomeData(invalidUser, "where id=" + validId);
	}
	
	
	@Test
	@DisplayName("Stop: InvalidUser + InvalidId")
	public void testStopRemoteSystemWithInValidUserAndInValidSystem() throws Exception {
	  User invalidUser = new User("1","Lucas","Pérez","Madrid", new ArrayList<Object>(Arrays.asList(1, 2)));
	  when(mockAuthDao.getAuthData(invalidUser.getId())).thenReturn(null);

	  OperationNotSupportedException e = new OperationNotSupportedException();
	  String invalidId = "98765"; 
	  
	  when(mockGenericDao.getSomeData(invalidUser, "where id=" + invalidId)).thenThrow(e);
	 
	  InOrder ordered = inOrder(mockAuthDao, mockGenericDao);
	  
	  SystemManager manager = new SystemManager(mockAuthDao, mockGenericDao);
	 	  
	  ordered.verify(mockAuthDao).getAuthData(invalidUser.getId());
	  ordered.verify(mockGenericDao).getSomeData(invalidUser, "where id=" + invalidId);
	}
	
	//----------------------------------------------------------------------------
		

	@Test
	@DisplayName("AddRemote: InvalidUser + validId")
	public void testAddRemoteSystemWithInValiduserAndValidSystem() throws Exception {
	  User invalidUser = new User("1","Lucas","Pérez","Madrid", new ArrayList<Object>(Arrays.asList(1, 2)));
	  when(mockAuthDao.getAuthData(invalidUser.getId())).thenReturn(null);

	 
	  OperationNotSupportedException e1 = new OperationNotSupportedException();
	  String invalidId = "12345"; 
	  
	  when(mockGenericDao.updateSomeData(invalidUser, ob)).thenThrow(e1);
	 
	  InOrder ordered = inOrder(mockAuthDao, mockGenericDao);
	 
	  SystemManager manager = new SystemManager(mockAuthDao, mockGenericDao);
	  	  
	  ordered.verify(mockAuthDao).getAuthData(invalidUser.getId());
	  ordered.verify(mockGenericDao).updateSomeData(invalidUser, ob);
	}
	

	@Test
	@DisplayName("AddRemote: InvalidUser + InvalidId")
	public void testAddRemoteSystemWithInValiduserAndInValidSystem() throws Exception {
	  User invalidUser = new User("1","Lucas","Pérez","Madrid", new ArrayList<Object>(Arrays.asList(1, 2)));
	  when(mockAuthDao.getAuthData(invalidUser.getId())).thenReturn(null);

	  OperationNotSupportedException e1 = new OperationNotSupportedException();
	  String invalidId = "98765"; 
	  
	  when(mockGenericDao.updateSomeData(invalidUser, ob)).thenThrow(e1);
	 
	  InOrder ordered = inOrder(mockAuthDao, mockGenericDao);
	 
	  SystemManager manager = new SystemManager(mockAuthDao, mockGenericDao);
	  	  
	  ordered.verify(mockAuthDao).getAuthData(invalidUser.getId());
	  ordered.verify(mockGenericDao).updateSomeData(invalidUser, ob);
	}
	

	//-----------------------------------------------------
	
	@Test
	@DisplayName("DeleteRemote: InvalidUser + validId")
	public void testDeleteRemoteSystemWithInValiduserAndValidSystem() throws Exception {
	String idABorrar="56789";
	  User invalidUser = new User("1","Lucas","Pérez","Madrid", new ArrayList<Object>(Arrays.asList(1, 2)));
	  when(mockAuthDao.getAuthData(invalidUser.getId())).thenReturn(null);

	  OperationNotSupportedException e1 = new OperationNotSupportedException();
	  String invalidId = "12345"; 
	  
	  when(mockGenericDao.updateSomeData(invalidUser, ob)).thenThrow(e1);
	 
	  InOrder ordered = inOrder(mockAuthDao, mockGenericDao);
	 
	  SystemManager manager = new SystemManager(mockAuthDao, mockGenericDao);
	  	  
	  ordered.verify(mockAuthDao).getAuthData(invalidUser.getId());
	  ordered.verify(mockGenericDao).deleteSomeData(invalidUser, idABorrar);
	}
	

	@Test
	@DisplayName("DeleteRemote: InvalidUser + InvalidId")
	public void testDeleteRemoteSystemWithInValiduserAndInValidSystem() throws Exception {
		String idABorrar="56789";
	  User invalidUser = new User("1","Lucas","Pérez","Madrid", new ArrayList<Object>(Arrays.asList(1, 2)));
	  when(mockAuthDao.getAuthData(invalidUser.getId())).thenReturn(null);

	  OperationNotSupportedException e1 = new OperationNotSupportedException();
	  String invalidId = "98765"; 
	  
	  when(mockGenericDao.updateSomeData(invalidUser, ob)).thenThrow(e1);
	 
	  InOrder ordered = inOrder(mockAuthDao, mockGenericDao);
	 
	  SystemManager manager = new SystemManager(mockAuthDao, mockGenericDao);
	  	  
	  ordered.verify(mockAuthDao).getAuthData(invalidUser.getId());
	  ordered.verify(mockGenericDao).deleteSomeData(invalidUser, idABorrar);
	}
	
}
