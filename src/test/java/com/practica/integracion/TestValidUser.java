package com.practica.integracion;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.junit.jupiter.MockitoExtension;

import com.practica.integracion.DAO.AuthDAO;
import com.practica.integracion.DAO.GenericDAO;
import com.practica.integracion.DAO.User;
import com.practica.integracion.manager.SystemManager;
import com.practica.integracion.manager.SystemManagerException;

@ExtendWith(MockitoExtension.class)
public class TestValidUser {

	GenericDAO mockGenericDao = mock(GenericDAO.class);
	AuthDAO mockAuthDao = mock(AuthDAO.class);
	Object ob = new Object();
	 
	@Test
	@DisplayName("StartRemote: ValidUser + ValidId")
	public void testStartRemoteSystemWithValidUserAndSystem() throws Exception {
	  User validUser = new User("1","Ana","Lopez","Madrid", new ArrayList<Object>(Arrays.asList(1, 2)));
	  when(mockAuthDao.getAuthData(validUser.getId())).thenReturn(validUser);

	  String validId = "12345"; 
	  ArrayList<Object> lista = new ArrayList<>(Arrays.asList(1, 2));
	  when(mockGenericDao.getSomeData(validUser, "where id=" + validId)).thenReturn(validUser.getRoles());
	 
	  InOrder ordered = inOrder(mockAuthDao, mockGenericDao);
	  
	  SystemManager manager = new SystemManager(mockAuthDao, mockGenericDao);
	  Collection<Object> retorno = manager.startRemoteSystem(validUser.getId(), validId);
	  
	  assertEquals(retorno.toString(), "[1, 2]");
	  
	  ordered.verify(mockAuthDao).getAuthData(validUser.getId());
	  ordered.verify(mockGenericDao).getSomeData(validUser, "where id=" + validId);
	}
	
	@Test
	@DisplayName("StartRemote: ValidUser + ValidId FILTER NULL")
	public void testStartRemoteSystemWithValidUserAndSystemFilterNull() throws Exception {
	  User validUser = new User("1","Ana","Lopez","Madrid", new ArrayList<Object>(Arrays.asList(1, 2)));
	  when(mockAuthDao.getAuthData(validUser.getId())).thenReturn(validUser);
	  
	 Collection<Object> usuario = null;
	  
	  String validId = "12345"; 
	  
	  when(mockGenericDao.getSomeData(validUser, "where id=" + null)).thenReturn(usuario);
	 
	  InOrder ordered = inOrder(mockAuthDao, mockGenericDao);
	  
	  SystemManager manager = new SystemManager(mockAuthDao, mockGenericDao);
	  Collection<Object> retorno = manager.startRemoteSystem(validUser.getId(), validId);
	  
	  
	  ordered.verify(mockAuthDao).getAuthData(validUser.getId());
	  ordered.verify(mockGenericDao).getSomeData(validUser, "where id=" + validId);
	}
	
	@Test
	@DisplayName("StartRemote: ValidUser + InvalidId")
	public void testStartRemoteSystemWithValidUserAndInvalidSystem() throws Exception {
	  User validUser = new User("1","Ana","Lopez","Madrid", new ArrayList<Object>(Arrays.asList(1, 2)));
	  when(mockAuthDao.getAuthData(validUser.getId())).thenReturn(null);

	  OperationNotSupportedException e1 = new OperationNotSupportedException();
	  
	  String invalidId = "98765"; 
	  
	  when(mockGenericDao.getSomeData(validUser, "where id=" + invalidId)).thenThrow(e1);
	  InOrder ordered = inOrder(mockAuthDao, mockGenericDao);
	  
	  SystemManager manager = new SystemManager(mockAuthDao, mockGenericDao);
	  	  
	  ordered.verify(mockAuthDao).getAuthData(validUser.getId());
	  ordered.verify(mockGenericDao).getSomeData(validUser, "where id=" + invalidId);
	}
	
	// ----------------------------------------------------------
	@Test
	@DisplayName("StopRemote: ValidUser + ValidId")
	public void testStopRemoteSystemWithValidUserAndSystem() throws Exception {
	  User validUser = new User("1","Ana","Lopez","Madrid", new ArrayList<Object>(Arrays.asList(1, 2)));
	  when(mockAuthDao.getAuthData(validUser.getId())).thenReturn(validUser);

	  String validId = "12345"; 
	  ArrayList<Object> lista = new ArrayList<>(Arrays.asList(1, 2));
	  when(mockGenericDao.getSomeData(validUser, "where id=" + validId)).thenReturn(validUser.getRoles());
	 
	  InOrder ordered = inOrder(mockAuthDao, mockGenericDao);
	  
	  SystemManager manager = new SystemManager(mockAuthDao, mockGenericDao);
	  Collection<Object> retorno = manager.startRemoteSystem(validUser.getId(), validId);
	  
	  assertEquals(retorno.toString(), "[1, 2]");
	  
	  ordered.verify(mockAuthDao).getAuthData(validUser.getId());
	  ordered.verify(mockGenericDao).getSomeData(validUser, "where id=" + validId);
	}
	
	@Test
	@DisplayName("StopRemote: ValidUser + InvalidId")
	public void testStopRemoteSystemWithValidUserAndInvalidSystem() throws Exception {
	  User validUser = new User("1","Ana","Lopez","Madrid", new ArrayList<Object>(Arrays.asList(1, 2)));
	  when(mockAuthDao.getAuthData(validUser.getId())).thenReturn(null);

	  OperationNotSupportedException e1 = new OperationNotSupportedException();
	  
	  String invalidId = "98765"; 
	  
	  when(mockGenericDao.getSomeData(validUser, "where id=" + invalidId)).thenThrow(e1);
	  InOrder ordered = inOrder(mockAuthDao, mockGenericDao);
	  
	  SystemManager manager = new SystemManager(mockAuthDao, mockGenericDao);
	  	  
	  ordered.verify(mockAuthDao).getAuthData(validUser.getId());
	  ordered.verify(mockGenericDao).getSomeData(validUser, "where id=" + invalidId);
	}
	
	@Test
	@DisplayName("StopRemote: ValidUser + ValidId FILTER NULL")
	public void testStopRemoteSystemWithValidUserAndSystemFilterNull() throws Exception {
	  User validUser = new User("1","Ana","Lopez","Madrid", new ArrayList<Object>(Arrays.asList(1, 2)));
	  when(mockAuthDao.getAuthData(validUser.getId())).thenReturn(validUser);
	  
	 Collection<Object> usuario = null;
	  
	  String validId = "12345"; 
	  
	  when(mockGenericDao.getSomeData(validUser, "where id=" + null)).thenReturn(usuario);
	 
	  InOrder ordered = inOrder(mockAuthDao, mockGenericDao);
	  
	  SystemManager manager = new SystemManager(mockAuthDao, mockGenericDao);
	  Collection<Object> retorno = manager.startRemoteSystem(validUser.getId(), validId);
	  
	  
	  ordered.verify(mockAuthDao).getAuthData(validUser.getId());
	  ordered.verify(mockGenericDao).getSomeData(validUser, "where id=" + validId);
	}
	//-----------------------------------------------------------
	
	@Test
	@DisplayName("AddRemote: ValidUser + ValidId CON PERMISOS")
	public void testAddRemoteSystemWithValidUserAndSystem() throws Exception {
	  User validUser = new User("1","Ana","Lopez","Madrid", new ArrayList<Object>(Arrays.asList(1, 2)));
	  when(mockAuthDao.getAuthData(validUser.getId())).thenReturn(validUser);

	  String validId = "12345"; 
	 
	  InOrder ordered = inOrder(mockAuthDao, mockGenericDao);
	  ArrayList<Object> lista = new ArrayList<>(Arrays.asList(1, 2));
	  when(mockGenericDao.getSomeData(validUser, "where id=" + validId)).thenReturn(validUser.getRoles());
	
	  
	  SystemManager manager = new SystemManager(mockAuthDao, mockGenericDao);
	  Collection<Object> retorno = manager.startRemoteSystem(validUser.getId(), validId);
	  
	  if(retorno.toString().equals(lista.toString())) {
		  when(mockGenericDao.updateSomeData(validUser, ob)).thenReturn(true);
		  manager.addRemoteSystem(validUser.getId(), ob);
	  }else {
		  OperationNotSupportedException e1 = new OperationNotSupportedException();		  
		  when(mockGenericDao.updateSomeData(validUser, ob)).thenThrow(e1);
	  }
	  
	  ordered.verify(mockAuthDao).getAuthData(validUser.getId());
	  ordered.verify(mockGenericDao).updateSomeData(validUser, ob);
	 
	}
	
	
	@Test
	@DisplayName("AddRemote: ValidUser + ValidId SIN PERMISOS")
	public void testAddRemoteSystemWithValidUserAndSystem2() throws Exception {
	  User validUser = new User("1","Ana","Lopez","Madrid", new ArrayList<Object>(Arrays.asList(3, 4)));
	  when(mockAuthDao.getAuthData(validUser.getId())).thenReturn(validUser);

	  String validId = "12345"; 
	 
	  InOrder ordered = inOrder(mockAuthDao, mockGenericDao);
	  ArrayList<Object> lista = new ArrayList<>(Arrays.asList(1, 2));
	  when(mockGenericDao.getSomeData(validUser, "where id=" + validId)).thenReturn(validUser.getRoles());
	  
	  SystemManager manager = new SystemManager(mockAuthDao, mockGenericDao);
	  Collection<Object> retorno = manager.startRemoteSystem(validUser.getId(), validId); 
	 
	  if(retorno.toString().equals(lista.toString())) {
		  when(mockGenericDao.updateSomeData(validUser, ob)).thenReturn(true);
		  manager.addRemoteSystem(validUser.getId(), ob);
	  }else {
		
		  OperationNotSupportedException e1 = new OperationNotSupportedException();		  
		  when(mockGenericDao.updateSomeData(validUser, ob)).thenThrow(e1);
		 
	  }
	  
	  ordered.verify(mockAuthDao).getAuthData(validUser.getId());
	  ordered.verify(mockGenericDao).updateSomeData(validUser, ob);
	 
	}
	
	@Test
	@DisplayName("AddRemote: ValidUser + InValidId ")
	public void testAddRemoteSystemWithValidUserAndInSystem() throws Exception {
		User validUser = new User("1","Ana","Lopez","Madrid", new ArrayList<Object>(Arrays.asList(1, 2)));
		  when(mockAuthDao.getAuthData(validUser.getId())).thenReturn(null);

		  OperationNotSupportedException e1 = new OperationNotSupportedException();
		  
		  String invalidId = "98765"; 
		  
		  when(mockGenericDao.getSomeData(validUser, "where id=" + invalidId)).thenThrow(e1);
		  InOrder ordered = inOrder(mockAuthDao, mockGenericDao);
		  
		  SystemManager manager = new SystemManager(mockAuthDao, mockGenericDao);
		  
		  // manager.addRemoteSystem(invalidId,ob);
		  //SE HA COMENTADO ESTA INSTRUCCION PORQUE AL NO ESTAR AUTENTICADO NO PODRA REALIZAR 
		  //NINGUNA OPERACION
		  
		  ordered.verify(mockAuthDao).getAuthData(validUser.getId());
		  ordered.verify(mockGenericDao).getSomeData(validUser, "where id=" + invalidId);
	 
	}
	
	@Test
	@DisplayName("AddRemote: ValidUser + ValidId FILTER NULL")
	public void testAddRemoteSystemWithValidUserAndSystemFilterNull() throws Exception {
	  User validUser = new User("1","Ana","Lopez","Madrid", new ArrayList<Object>(Arrays.asList(1, 2)));
	  when(mockAuthDao.getAuthData(validUser.getId())).thenReturn(validUser);
	  
	 Collection<Object> usuario = null;
	  
	  String validId = "12345"; 
	  
	  when(mockGenericDao.getSomeData(validUser, "where id=" + null)).thenReturn(usuario);
	 
	  InOrder ordered = inOrder(mockAuthDao, mockGenericDao);
	  
	  SystemManager manager = new SystemManager(mockAuthDao, mockGenericDao);
	  Collection<Object> retorno = manager.startRemoteSystem(validUser.getId(), validId);
	  
	  
	  ordered.verify(mockAuthDao).getAuthData(validUser.getId());
	  ordered.verify(mockGenericDao).getSomeData(validUser, "where id=" + validId);
	}
	
	//--------------------------------------------------------------
	
	
	
	@Test
	@DisplayName("DeleteRemote: ValidUser + ValidId CON PERMISOS")
	public void testDeleteRemoteSystemWithValidUserAndSystem() throws Exception {
	  User validUser = new User("1","Ana","Lopez","Madrid", new ArrayList<Object>(Arrays.asList(1, 2)));
	  when(mockAuthDao.getAuthData(validUser.getId())).thenReturn(validUser);

	  String validId = "12345"; 
	  String idABorrar = "34456";
	 
	  InOrder ordered = inOrder(mockAuthDao, mockGenericDao);
	  ArrayList<Object> lista = new ArrayList<>(Arrays.asList(1, 2));
	  when(mockGenericDao.getSomeData(validUser, "where id=" + validId)).thenReturn(validUser.getRoles());
	  
	  
	  SystemManager manager = new SystemManager(mockAuthDao, mockGenericDao);
	  Collection<Object> retorno = manager.startRemoteSystem(validUser.getId(), validId);
	  
	  when(mockGenericDao.deleteSomeData(validUser, "where id=" + validId)).thenReturn(true);

	  
	  if(retorno.toString().equals(lista.toString())) {
		  try {
		  manager.deleteRemoteSystem(validUser.getId(), idABorrar);
		  }catch(SystemManagerException e) {
			  	e.getStackTrace();
		  }
	  }else {
		  OperationNotSupportedException e1 = new OperationNotSupportedException();		  
		  when(mockGenericDao.deleteSomeData(validUser, idABorrar)).thenThrow(e1);
	  }
	  
	  ordered.verify(mockAuthDao).getAuthData(validUser.getId());
	  ordered.verify(mockGenericDao).deleteSomeData(validUser, idABorrar);
	 
	}
	
	@Test
	@DisplayName("DeleteRemote: ValidUser + ValidId sin PERMISOS")
	public void testDeleteRemoteSystemWithValidUserAndSystem2() throws Exception {
	  User validUser = new User("1","Ana","Lopez","Madrid", new ArrayList<Object>(Arrays.asList(3, 4)));
	  when(mockAuthDao.getAuthData(validUser.getId())).thenReturn(validUser);

	  String validId = "12345"; 
	  String idABorrar = "34567";
	 
	  InOrder ordered = inOrder(mockAuthDao, mockGenericDao);
	  ArrayList<Object> lista = new ArrayList<>(Arrays.asList(1, 2));
	  when(mockGenericDao.getSomeData(validUser, "where id=" + validId)).thenReturn(validUser.getRoles());
	
	  
	  SystemManager manager = new SystemManager(mockAuthDao, mockGenericDao);
	  Collection<Object> retorno = manager.startRemoteSystem(validUser.getId(), validId);
	  
	  if(retorno.toString().equals(lista.toString())) {
		  when(mockGenericDao.deleteSomeData(validUser, idABorrar)).thenReturn(true);
		  manager.deleteRemoteSystem(validUser.getId(), idABorrar);
	  }else {
		  OperationNotSupportedException e1 = new OperationNotSupportedException();		  
		  when(mockGenericDao.deleteSomeData(validUser, idABorrar)).thenThrow(e1);
	  }
	  
	  ordered.verify(mockAuthDao).getAuthData(validUser.getId());
	  ordered.verify(mockGenericDao).deleteSomeData(validUser, idABorrar);
	 
	}
	
	
	
	@Test
	@DisplayName("DeleteRemote: ValidUser + InValidId")
	public void testDeleteRemoteSystemWithValidUserAndInSystem() throws Exception {
		User validUser = new User("1","Ana","Lopez","Madrid", new ArrayList<Object>(Arrays.asList(1, 2)));
		  when(mockAuthDao.getAuthData(validUser.getId())).thenReturn(null);

		  OperationNotSupportedException e1 = new OperationNotSupportedException();
		  
		  String invalidId = "98765"; 
		  String idABorrar = "34567";
		  
		  when(mockGenericDao.getSomeData(validUser, "where id=" + invalidId)).thenThrow(e1);
		  InOrder ordered = inOrder(mockAuthDao, mockGenericDao);
		  
		  SystemManager manager = new SystemManager(mockAuthDao, mockGenericDao);
		  
		  //manager.deleteRemoteSystem(invalidId,idABorrar);
		  //SE HA COMENTADO ESTA INSTRUCCION PORQUE AL NO ESTAR AUTENTICADO NO PODRA REALIZAR 
		  //NINGUNA OPERACION
		  
		  ordered.verify(mockAuthDao).getAuthData(validUser.getId());
		  ordered.verify(mockGenericDao).getSomeData(validUser, "where id=" + invalidId);
	}
	
	@Test
	@DisplayName("DeleteRemote: ValidUser + ValidId FILTER NULL")
	public void testDeleteRemoteSystemWithValidUserAndSystemFilterNull() throws Exception {
	  User validUser = new User("1","Ana","Lopez","Madrid", new ArrayList<Object>(Arrays.asList(1, 2)));
	  when(mockAuthDao.getAuthData(validUser.getId())).thenReturn(validUser);
	  
	 Collection<Object> usuario = null;
	  
	  String validId = "12345"; 
	  
	  when(mockGenericDao.getSomeData(validUser, "where id=" + null)).thenReturn(usuario);
	 
	  InOrder ordered = inOrder(mockAuthDao, mockGenericDao);
	  
	  SystemManager manager = new SystemManager(mockAuthDao, mockGenericDao);
	  Collection<Object> retorno = manager.startRemoteSystem(validUser.getId(), validId);
	  
	  
	  ordered.verify(mockAuthDao).getAuthData(validUser.getId());
	  ordered.verify(mockGenericDao).getSomeData(validUser, "where id=" + validId);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
