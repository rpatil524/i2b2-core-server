/*******************************************************************************
 * Copyright (c) 2006-2018 Massachusetts General Hospital 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. I2b2 is also distributed under
 * the terms of the Healthcare Disclaimer.
 ******************************************************************************/
package edu.harvard.i2b2.workpalce.ws;

import static org.junit.Assert.*;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import jakarta.xml.bind.JAXBElement;

import junit.framework.JUnit4TestAdapter;

import org.apache.axiom.om.OMElement;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.harvard.i2b2.common.util.jaxb.JAXBUnWrapHelper;
import edu.harvard.i2b2.workplace.datavo.i2b2message.ResponseMessageType;
import edu.harvard.i2b2.workplace.datavo.i2b2message.StatusType;
import edu.harvard.i2b2.workplace.datavo.wdo.FolderType;
import edu.harvard.i2b2.workplace.datavo.wdo.FoldersType;
import edu.harvard.i2b2.workplace.util.WorkplaceJAXBUtil;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WorkplaceServiceRESTTest extends WorkplaceAxisAbstract{
	private static String testFileDir = "";

	private static String workplaceTargetEPR = null;	

	private static String getFoldersByProject = "getFoldersByProject";
	private static String getFoldersByUserId = "getFoldersByUserId";
	private static String addChild = "addChild";
	private static String getNameInfo = "getNameInfo";
	private static String setProtectedAccess = "setProtectedAccess";

	//swc20160722 added following 4 DBlookup related
	private static String getAllDBlookups = null;
	private static String setDBlookup = null;
	private static String getDBlookup = null;
	private static String deleteDBlookup = null;

	//	"http://127.0.0.1:8080/i2b2/services/PMService/getServices";	
	private static String demoIndex = "";
	private static String i2b2Index = "";
	private static String sharedIndex = "";
	private static String findDemographicsIndex = "";


	public static junit.framework.Test suite() { 
		return new JUnit4TestAdapter(WorkplaceServiceRESTTest.class);
	}


	@BeforeClass
	public static void setUp() throws Exception {
		String host = (System.getProperty("testhost") == null ? "http://127.0.0.1:9090/i2b2/services" : System.getProperty("testhost") ) ;
		workplaceTargetEPR = 
				host + "/WorkplaceService/";	
		
		
		getAllDBlookups = workplaceTargetEPR + "getAllDblookups";
		setDBlookup = workplaceTargetEPR + "setDblookup";
		getDBlookup = workplaceTargetEPR + "getDblookup";
		deleteDBlookup = workplaceTargetEPR + "deleteDblookup";
		
		
		testFileDir = "test"; //System.getProperty("testfiledir");

		if (!((testFileDir != null) && (testFileDir.trim().length() > 0))) {
			throw new Exception(
					"please provide test file directory info -Dtestfiledir");
		}

	}

	@Test
	public void A10_GetFoldersByProject() throws Exception {
		String filename = testFileDir + "/folders_by_project.xml";
		String masterInstanceResult = null;
		try { 
			String requestString = getQueryString(filename);
			OMElement requestElement = convertStringToOMElement(requestString); 
			OMElement responseElement = getServiceClient(workplaceTargetEPR + getFoldersByProject).sendReceive(requestElement);
			JAXBElement responseJaxb = WorkplaceJAXBUtil.getJAXBUtil().unMashallFromString(responseElement.toString());
			ResponseMessageType r = (ResponseMessageType)responseJaxb.getValue();
			JAXBUnWrapHelper helper = new  JAXBUnWrapHelper();

			FoldersType folders = (FoldersType)helper.getObjectByClass(r.getMessageBody().getAny(),FoldersType.class);
			for(FolderType folder: folders.getFolder())
			{
				if (folder.getName().equals("demo"))
				{
					assertEquals(folder.getName(),"demo");
					demoIndex = (folder.getIndex()).replaceAll("\\\\", "\\\\\\\\");
				}
				if (folder.getName().equals("i2b2"))
				{
					assertEquals(folder.getName(),"i2b2");
					i2b2Index = (folder.getIndex()).replaceAll("\\\\", "\\\\\\\\");
				}

				if (folder.getName().equals("SHARED"))
				{
					assertEquals(folder.getName(),"SHARED");
					sharedIndex = (folder.getIndex()).replaceAll("\\\\", "\\\\\\\\");
				}
			}
			assertNotNull(folders);
			assertTrue(folders.getFolder().size() > 1);



		} catch (Exception e) { 
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void A20_GetFoldersByUserId() throws Exception {
		String filename = testFileDir + "/folders_by_userid.xml";
		String masterInstanceResult = null;
		try { 
			String requestString = getQueryString(filename);
			OMElement requestElement = convertStringToOMElement(requestString); 
			OMElement responseElement = getServiceClient(workplaceTargetEPR + getFoldersByUserId).sendReceive(requestElement);
			JAXBElement responseJaxb = WorkplaceJAXBUtil.getJAXBUtil().unMashallFromString(responseElement.toString());
			ResponseMessageType r = (ResponseMessageType)responseJaxb.getValue();
			JAXBUnWrapHelper helper = new  JAXBUnWrapHelper();

			FoldersType folders = (FoldersType)helper.getObjectByClass(r.getMessageBody().getAny(),FoldersType.class);
			for(FolderType folder: folders.getFolder())
			{
				if (folder.getName().equals("demo"))
				{
					assertEquals(folder.getName(),"demo");
				}
				if (folder.getName().equals("SHARED"))
				{
					assertEquals(folder.getName(),"SHARED");
				}
			}
			assertNotNull(folders);
			assertTrue(folders.getFolder().size() > 1);



		} catch (Exception e) { 
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void A30_AddChildOntologyFolder() throws Exception {

		String filename1 = testFileDir + "/add_child_ontology_folder.xml";
		String filename2 = testFileDir + "/add_child_ontology_folder_2.xml";
		String filename3 = testFileDir + "/add_child_ontology_folder_3.xml";
		String filename4 = testFileDir + "/add_child_ontology_folder_4.xml";
		try { 
			ResponseMessageType r = serviceRequest(addChild, filename1);
			checkResponseStatus(r, "DONE");

			r = serviceRequest(addChild, filename2);
			checkResponseStatus(r, "DONE");

			r = serviceRequest(addChild, filename3);
			checkResponseStatus(r, "DONE");

			r = serviceRequest(addChild, filename4);
			checkResponseStatus(r, "DONE");
		} catch (Exception e) { 
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void A40_AddChildOntologyConcept() throws Exception {

		ArrayList <String> files = new ArrayList <String>();
		files.add(testFileDir + "/add_child_ontology_concept.xml");
		files.add(testFileDir + "/add_child_ontology_concept_2.xml");
		files.add(testFileDir + "/add_child_ontology_concept_3.xml");
		files.add(testFileDir + "/add_child_ontology_concept_4.xml");
		files.add(testFileDir + "/add_child_ontology_concept_5.xml");

		ResponseMessageType respMsgType = null;

		for(String fileName : files){
			try { 
				respMsgType = serviceRequest(addChild, fileName);
				checkResponseStatus(respMsgType, "DONE");
				respMsgType = null; // resetting ResponseMessageType
			} catch (Exception e) { 
				e.printStackTrace();
				assertTrue(false);
			}
		}
	}


	
	/*
	 *  <ns4:get_name_info category="demo" max="200"  hiddens="false" blob="true" type="core">
	 *      <match_str strategy="contains">11 years old</match_str>
	 *  </ns4:get_name_info>
	@Test 
	public void FindDemographics() throws Exception {
		String filename = testFileDir + "/find_demographics.xml";
		String conceptName = "11 years old demo";
		try { 
			ResponseMessageType r = serviceRequest(getNameInfo, filename);
			checkResponseStatus(r, "DONE");
			assertTrue(verifyFolder(r, conceptName, true, false, null));
		} catch (Exception e) { 
			e.printStackTrace();
			assertTrue(false);
		}
	}
	*/
	@Test
	public void A50_FindWithCategoryMissing() throws Exception {
		String filename1 = testFileDir + "/find_category_validation_1.xml"; // category="" in request xml
		String filename2 = testFileDir + "/find_category_validation_2.xml"; // category element missing from request xml
		String errorstring = "select a valid category";
		try { 
			serviceExecWithErrResp(filename1, errorstring, getNameInfo);
			serviceExecWithErrResp(filename2, errorstring, getNameInfo);
		} catch (Exception e) { 
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void A60_FindWithMatchStrMissing() throws Exception {
		String filename1 = testFileDir + "/find_match_str_validation_1.xml"; // match_str = "" in request xml
		String filename2 = testFileDir + "/find_match_str_validation_2.xml"; // match_str element missing from request xml
		String errString1 = "Please enter a valid search string" ;
		String errString2 = "'match_str' element is missing.";
		try { 
			serviceExecWithErrResp(filename1, errString1, getNameInfo);
			serviceExecWithErrResp(filename2, errString2, getNameInfo);
		} catch (Exception e) { 
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void A70_FindWithMaxValidation() throws Exception {
		String filename1 = testFileDir + "/find_max_validation_1.xml"; // max="-1" in request xml
		String filename2 = testFileDir + "/find_max_validation_2.xml"; // max = "0" in request xml
		String errorstring = "Please enter a valid 'max' value.";
		try { 
			serviceExecWithErrResp(filename1, errorstring, getNameInfo);
			serviceExecWithErrResp(filename2, errorstring, getNameInfo);
		} catch (Exception e) { 
			e.printStackTrace();
			assertTrue(false);
		}
	}


	@Test
	public void A80_FindWithStartegyMissing() throws Exception {
		String filename1 = testFileDir + "/find_strategy_validation_1.xml"; // strategy="" in request xml
		String filename2 = testFileDir + "/find_strategy_validation_2.xml"; // no element strategy provided in request xml
		String errorstring = "Strategy is missing.";
		String filename3 = testFileDir + "/find_strategy_validation_3.xml"; // strategy="abcd" in request xml
		String errorStr2 = "Incorrect strategy provided.";
		try { 
			// Test that response returns error with the given error message
			serviceExecWithErrResp(filename1, errorstring, getNameInfo);
			serviceExecWithErrResp(filename2, errorstring, getNameInfo);
			serviceExecWithErrResp(filename3, errorStr2, getNameInfo);
		} catch (Exception e) { 
			e.printStackTrace();
			assertTrue(false);
		}
	}



	@Test
	public void A90_setProtectedAccDemographics() throws Exception{
		String filename1 = testFileDir + "/setProtectedAcc_demo_1.xml";
		String filename2 = testFileDir + "/setProtectedAcc_demo_2.xml";
		String conceptName = "11 years old demo";
		String nameInfoFilename = testFileDir + "/find_demographics.xml";
		try { 
//MM OFF
			/*
			// set protected access to true
			ResponseMessageType r = serviceRequest(setProtectedAccess, filename1);
			checkResponseStatus(r, "DONE");

			// request getNameInfo service and then check if the concept has been 
			// set protectedAccess = true
			ResponseMessageType rspMsgType = serviceRequest(getNameInfo, nameInfoFilename);
			checkResponseStatus(rspMsgType, "DONE");
			assertTrue(verifyFolder(rspMsgType, conceptName, false, false, true));

			// set protected access to false
			ResponseMessageType r2 = serviceRequest(setProtectedAccess, filename2);
			checkResponseStatus(r2, "DONE");

			// request getNameInfo service and then check if the concept has been 
			// set protectedAccess = false
			ResponseMessageType rspMsgType2 = serviceRequest(getNameInfo, nameInfoFilename);
			checkResponseStatus(rspMsgType2, "DONE");
			assertTrue(verifyFolder(rspMsgType2, conceptName, false, false, false));
*/
		} catch (Exception e) { 
			e.printStackTrace();
			assertTrue(false);
		}
	}
	

	@Test
	public void A100_setProtectedAccIndexValidation() throws Exception{
		String filename1 = testFileDir + "/setProtectedAcc_indexValidation_1.xml";
		String filename2 = testFileDir + "/setProtectedAcc_indexValidation_2.xml";
		String errStr = "folder index is missing";

		try { 

			// <index><index> - empty index element
			serviceExecWithErrResp(filename1, errStr, setProtectedAccess);

			// <index> element not specified in the request
			serviceExecWithErrResp(filename2, errStr, setProtectedAccess);

		} catch (Exception e) { 
			e.printStackTrace();
			assertTrue(false);
		}
	}


	@Test
	public void A110_setProtectedAccValidation() throws Exception{
		String filename1 = testFileDir + "/setProtectedAcc_validation_1.xml";
		String filename2 = testFileDir + "/setProtectedAcc_validation_2.xml";
		String filename3 = testFileDir + "/setProtectedAcc_validation_3.xml";
		String errStr = "Please specify protected access value";

		try { 

			// <protected_access><protected_access> - empty element
			serviceExecWithErrResp(filename1, errStr, setProtectedAccess);

			// <protected_access> element not specified in the request
			serviceExecWithErrResp(filename2, errStr, setProtectedAccess);

			// <protected_access>gfgfgf<protected_access> element with incorrect value
			serviceExecWithErrResp(filename3, errStr, setProtectedAccess);

		} catch (Exception e) { 
			e.printStackTrace();
			assertTrue(false);
		}
	}


	/**
	 * 
	 * Please Note this method assumes users demo and i2b2 have role of 'DATA_PROT
	 * 
	 * @throws Exception
	 */
	
	/*
	@Test
	public void setProtectedAccUserValidation() throws Exception{

		try { 

			String filename1 = testFileDir + "/setProtectedAcc_userValidation_1.xml";
			String conceptName1 = "11 years old demo";
			String nameInfoFilename1 = testFileDir + "/find_demographics.xml";

			// manager setting protected access on another user's content within same project 
			ResponseMessageType r = serviceRequest(setProtectedAccess, filename1);
			checkResponseStatus(r, "DONE");

			// request getNameInfo service and then check if the concept has been 
			// set protectedAccess = true
			ResponseMessageType rspMsgType = serviceRequest(getNameInfo, nameInfoFilename1);
			checkResponseStatus(rspMsgType, "DONE");
			assertTrue(verifyFolder(rspMsgType, conceptName1, false, false, true));


			String filename2 = testFileDir + "/setProtectedAcc_userValidation_2.xml";
			String conceptName2 = "Rheumatism, excluding the back demo";
			String nameInfoFilename2 = testFileDir + "/find_hidden_validation_1.xml";

			// user setting his/her own content to protected access
			ResponseMessageType r2 = serviceRequest(setProtectedAccess, filename2);
			checkResponseStatus(r2, "DONE");

			// request getNameInfo service and then check if the concept has been 
			// set protectedAccess = true
			ResponseMessageType rspMsgType2 = serviceRequest(getNameInfo, nameInfoFilename2);
			checkResponseStatus(rspMsgType2, "DONE");
			assertTrue(verifyFolder(rspMsgType2, conceptName2, false, false, true));


			String filename3 = testFileDir + "/setProtectedAcc_userValidation_3.xml";
			String conceptName3 = "Rheumatism, excluding the back shared";
			String nameInfoFilename3 = testFileDir + "/find_user_role_validation_3.xml";

			// user setting shared content to protected access=false which is allowed
			ResponseMessageType r3 = serviceRequest(setProtectedAccess, filename3);
			checkResponseStatus(r3, "DONE");

			// request getNameInfo service and then check if the concept has been 
			// set protectedAccess = false
			ResponseMessageType rspMsgType3 = serviceRequest(getNameInfo, nameInfoFilename3);
			checkResponseStatus(rspMsgType3, "DONE");
			assertTrue(verifyFolder(rspMsgType3, conceptName3, false, false, false));

			String filename4 = testFileDir + "/setProtectedAcc_userValidation_4.xml";
			String errStr = "User does not have correct privileges";

			// user setting protected access to manager's file, which is not allowed
			serviceExecWithErrResp(filename4, errStr, setProtectedAccess);

		} catch (Exception e) { 
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	// This method assumes the root directory 'demo' has index of WxWMM6p2jbSPmtszJaJu
	@Test	
	public void setProtectedAccOnFolder() throws Exception{
		String filename1 = testFileDir + "/setProtectedAcc_onFolder.xml";
		//String filename2 = testFileDir + "/setProtectedAcc_demo_2.xml";
		String conceptName = "11 years old demo";
		String nameInfoFilename = testFileDir + "/find_demographics.xml";

		String conceptName2 = "Rheumatism, excluding the back demo";
		String nameInfoFilename2 = testFileDir + "/find_hidden_validation_1.xml";

		String conceptName3a = "Test Folder 1";
		String conceptName3b = "Test Folder 2";
		String conceptName3c = "Test Folder 3";
		String conceptName3d = "Test Folder 4";
		String nameInfoFilename3 = testFileDir + "/find_folder.xml";

		try { 

			// set protected access to false on a folder which has concepts as well as
			// other folders as child
			ResponseMessageType r = serviceRequest(setProtectedAccess, filename1);
			checkResponseStatus(r, "DONE");

			// check to confirm that all the child folders and concept has been 
			// set to protected_access =false
			// checking child concept
			ResponseMessageType rspMsgType = serviceRequest(getNameInfo, nameInfoFilename);
			checkResponseStatus(rspMsgType, "DONE");
			assertTrue(verifyFolder(rspMsgType, conceptName, false, false, false));

			// checking second child concept
			ResponseMessageType rspMsgType2 = serviceRequest(getNameInfo, nameInfoFilename2);
			checkResponseStatus(rspMsgType2, "DONE");
			assertTrue(verifyFolder(rspMsgType2, conceptName2, false, false, false));

			// checking all the sub-folders
			ResponseMessageType rspMsgType3 = serviceRequest(getNameInfo, nameInfoFilename3);
			checkResponseStatus(rspMsgType3, "DONE");
			assertTrue(verifyFolder(rspMsgType3, conceptName3a, false, false, false));
			assertTrue(verifyFolder(rspMsgType3, conceptName3b, false, false, false));
			assertTrue(verifyFolder(rspMsgType3, conceptName3c, false, false, false));
			assertTrue(verifyFolder(rspMsgType3, conceptName3d, false, false, false));

		} catch (Exception e) { 
			e.printStackTrace();
			assertTrue(false);
		}
	}
	*/
	
	// This method assumes the root directory 'demo' has index of WxWMM6p2jbSPmtszJaJu
	/* fix
	@Test
	public void setProtectedAccOnRoot() throws Exception{
		String filename1 = testFileDir + "/setProtectedAcc_onRoot.xml";
		//String filename2 = testFileDir + "/setProtectedAcc_demo_2.xml";
		String conceptName = "11 years old demo";
		String nameInfoFilename = testFileDir + "/find_demographics.xml";

		String conceptName2 = "Rheumatism, excluding the back demo";
		String nameInfoFilename2 = testFileDir + "/find_hidden_validation_1.xml";

		String conceptName3a = "Test Folder 1";
		String conceptName3b = "Test Folder 2";
		String conceptName3c = "Test Folder 3";
		String conceptName3d = "Test Folder 4";
		String nameInfoFilename3 = testFileDir + "/find_folder.xml";

		try { 

			// set protected access to false on a folder which has concepts as well as
			// other folders as child
			ResponseMessageType r = serviceRequest(setProtectedAccess, filename1);
			checkResponseStatus(r, "DONE");

			// check to confirm that all the child folders and concept has been 
			// set to protected_access =false
			// checking child concept
			ResponseMessageType rspMsgType = serviceRequest(getNameInfo, nameInfoFilename);
			checkResponseStatus(rspMsgType, "DONE");
			assertTrue(verifyFolder(rspMsgType, conceptName, false, false, true));

			// checking second child concept
			ResponseMessageType rspMsgType2 = serviceRequest(getNameInfo, nameInfoFilename2);
			checkResponseStatus(rspMsgType2, "DONE");
			assertTrue(verifyFolder(rspMsgType2, conceptName2, false, false, true));

			// checking all the sub-folders
			ResponseMessageType rspMsgType3 = serviceRequest(getNameInfo, nameInfoFilename3);
			checkResponseStatus(rspMsgType3, "DONE");
			assertTrue(verifyFolder(rspMsgType3, conceptName3a, false, false, true));
			assertTrue(verifyFolder(rspMsgType3, conceptName3b, false, false, true));
			assertTrue(verifyFolder(rspMsgType3, conceptName3c, false, false, true));
			assertTrue(verifyFolder(rspMsgType3, conceptName3d, false, false, true));

		} catch (Exception e) { 
			e.printStackTrace();
			assertTrue(false);
		}
	}
	*/
	
	
/*
	@Test
	/*
	 *   <ns4:get_name_info category="dEmo">
	 *               <match_str strategy="right">old demo</match_str>
	 *   </ns4:get_name_info>
	 
	public void FindWithParamsMissing() throws Exception {
		String xmlFileName = testFileDir + "/find_missing_params.xml"; // only required params given in the request
		String conceptName = "11 years old demo";
		try { 
			ResponseMessageType r = serviceRequest(getNameInfo, xmlFileName);
			checkResponseStatus(r, "DONE");
			JAXBUnWrapHelper helper = new  JAXBUnWrapHelper();
			FoldersType folders = (FoldersType)helper.getObjectByClass(r.getMessageBody().getAny(),FoldersType.class);
			assertNotNull(folders);
			boolean foundConcept= false;
			for(FolderType folder: folders.getFolder())
			{

				if (folder.getName().toLowerCase().equals(conceptName.toLowerCase())){
					foundConcept = true;

					// verify workxml is part of the response
					// which wil test that blob = true is default
					assertTrue(folder.getWorkXml()!=null);	

					// verify visualattributes is not hidden 
					// which will test that hidden=false is default
					assertFalse(folder.getVisualAttributes().contains("H"));

					// verify Entry date is not part of response
					// which will test that type="CORE" is default
					assertTrue(folder.getEntryDate()==null);

					break;
				}
			}

			assertTrue(folders.getFolder().size() >= 1);

		} catch (Exception e) { 
			e.printStackTrace();
			assertTrue(false);
		}
	}
	


	@Test
	public void FindWithStrategyValidation() throws Exception {
		HashMap<String, String> fileStrings = new HashMap<String, String>();

		fileStrings.put(testFileDir + "/find_strategy_contains.xml", "Rheumatism, excluding the back demo");
		fileStrings.put(testFileDir + "/find_strategy_left.xml", "11 years old demo");
		fileStrings.put(testFileDir + "/find_strategy_match.xml", "11 years old i2b2");
		fileStrings.put(testFileDir + "/find_strategy_right.xml", "Rheumatism, excluding the back shared");

		try { 
			// Test each of the request returned the resulting concept/folder
			Iterator<String> it = fileStrings.keySet().iterator();
			String xmlFileName= "";
			String conceptName = "";
			ResponseMessageType r = null;
			while (it.hasNext()){
				xmlFileName = it.next();
				conceptName = fileStrings.get(xmlFileName);
				r = serviceRequest(getNameInfo, xmlFileName);
				checkResponseStatus(r, "DONE");
				assertTrue(verifyFolder(r, conceptName, true, false, null));
			}
		} catch (Exception e) { 
			e.printStackTrace();
			assertTrue(false);
		}
	}
	 */
	
	/**
	 * This test is designed to ensure correct response is provided
	 * depending on user's accessibility to the workplace/folder
	 * 
	 * @throws Exception
	 */
/*
	@Test
	public void FindUserValidation() throws Exception { 
		String filename1 = testFileDir + "/find_user_role_validation_1.xml"; // user accessing manager's file
		String errorstring = "User does not have correct privileges";

		HashMap<String, String> fileStrings = new HashMap<String, String>();
		fileStrings.put(testFileDir + "/find_user_role_validation_2.xml", "11 years old demo"); // user accessing his/her own directory
		fileStrings.put(testFileDir + "/find_user_role_validation_3.xml", "Rheumatism, excluding the back shared"); // user accessing shared directory
		fileStrings.put(testFileDir + "/find_user_role_validation_4.xml", "11 years old i2b2"); // manager accessing his/her own directory
		fileStrings.put(testFileDir + "/find_user_role_validation_5.xml", "11 years old demo"); // manager accessing user's directory
		fileStrings.put(testFileDir + "/find_user_role_validation_6.xml", "Rheumatism, excluding the back shared");  // manager accessing shared directory

		try { 
			// Test that response returns error with the given error message
			// request has userid = demo and category = i2b2
			serviceExecWithErrResp(filename1, errorstring, getNameInfo);
		} catch (Exception e) { 
			e.printStackTrace();
			assertTrue(false);
		}

		try { 
			// Test each of the request returned the resulting concept/folder
			Iterator<String> it = fileStrings.keySet().iterator();
			String xmlFileName= "";
			String conceptName = "";
			ResponseMessageType respMsgType = null;	
			while (it.hasNext()){
				xmlFileName = it.next();
				conceptName = fileStrings.get(xmlFileName);
				respMsgType = serviceRequest(getNameInfo, xmlFileName);
				checkResponseStatus(respMsgType, "DONE");
				assertTrue(verifyFolder(respMsgType, conceptName, true, false, null));
			}
		} catch (Exception e) { 
			e.printStackTrace();
			assertTrue(false);
		}
	}


	@Test
	public void FindHiddenValidation() throws Exception {
		String filename1 = testFileDir + "/find_hidden_validation_1.xml"; // hidden=true in request
		String conceptName = "Rheumatism, excluding the back demo";
		String filename2 = testFileDir + "/find_hidden_validation_2.xml"; // hidden=false in request

		try { 
			// hidden=true in request
			// Test that folder/Concept was returned as it had visualattr = ZH
			ResponseMessageType r = serviceRequest(getNameInfo, filename1);
			checkResponseStatus(r, "DONE");
			assertTrue(verifyFolder(r, conceptName, true, false, null));

			r = null; //re-setting responseMessageType to null

			// hidden=false in request
			// Test that folder/Concept was not returned as it had visualattr = ZH
			r = serviceRequest(getNameInfo, filename2);
			checkResponseStatus(r, "DONE");
			JAXBUnWrapHelper helper = new  JAXBUnWrapHelper();
			FoldersType folders = (FoldersType)helper.getObjectByClass(r.getMessageBody().getAny(),FoldersType.class);
			assertTrue(folders==null || folders.getFolder().size() <= 0);

		} catch (Exception e) { 
			e.printStackTrace();
			assertTrue(false);
		}
	}


	@Test
	public void FindBlobValidation() throws Exception {
		String filename1 = testFileDir + "/find_blob_validation_1.xml"; // blob=true in request
		String conceptName = "Rheumatism, excluding the back demo";
		String filename2 = testFileDir + "/find_blob_validation_2.xml"; // blob=false in request

		try { 
			// blob=true in request
			// Test folders were returned along with workxml in response
			ResponseMessageType r = serviceRequest(getNameInfo, filename1);
			checkResponseStatus(r, "DONE");
			verifyFolder(r, conceptName, true, false, null);

			r = null; //re-setting responseMessageType to null

			// blob=false in request
			// Test folders were returned in response but workxml was not returned
			r = serviceRequest(getNameInfo, filename2);
			checkResponseStatus(r, "DONE");
			JAXBUnWrapHelper helper = new  JAXBUnWrapHelper();
			FoldersType folders2 = (FoldersType)helper.getObjectByClass(r.getMessageBody().getAny(),FoldersType.class);
			assertNotNull(folders2);
			for(FolderType folder: folders2.getFolder())
			{
				if (folder.getName().toLowerCase().equals(conceptName.toLowerCase())){
					assertTrue(folder.getWorkXml()==null);					
					break;
				}
			}

			assertTrue(folders2.getFolder().size() >= 1);

		} catch (Exception e) { 
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void FindTypeValidation() throws Exception {
		String filename1 = testFileDir + "/find_type_validation_1.xml"; // type=core in request
		String conceptName = "Rheumatism, excluding the back i2b2";
		String filename2 = testFileDir + "/find_type_validation_2.xml"; // type=all in request

		try { 
			// type=core in request
			// Test all elements of folders were returned except the entrydate
			ResponseMessageType r = serviceRequest(getNameInfo, filename1);
			
			
			checkResponseStatus(r, "DONE");
			verifyFolder(r, conceptName, true, false, null);

		
			r = null; //re-setting responseMessageType to null

			// type=all in request
			// Test all elements of folders were returned in the response including entrydate
			r = serviceRequest(getNameInfo, filename2);
			checkResponseStatus(r, "DONE");
			verifyFolder(r, conceptName, true, true, null);

		} catch (Exception e) { 
			e.printStackTrace();
			assertTrue(false);
		}
	}
*/
	

	@Test
	public void A120_GetAllDBlookups_admin() throws Exception { //swc20160722
		String filename = testFileDir + "/getAllDBlookups_admin.xml";
		try { 
			String requestString = getQueryString(filename);
			OMElement requestElement = convertStringToOMElement(requestString); 
			OMElement responseElement = getServiceClient(getAllDBlookups).sendReceive(requestElement);
			JAXBElement responseJaxb = WorkplaceJAXBUtil.getJAXBUtil().unMashallFromString(responseElement.toString());
			ResponseMessageType r = (ResponseMessageType)responseJaxb.getValue();
			String msg = r.getResponseHeader().getResultStatus().getStatus().getType();
			assertEquals("DONE", msg);
		} catch (Exception e) { 
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void A130_GetAllDBlookups_non_admin() throws Exception { //swc20160722
		String filename = testFileDir + "/getAllDBlookups_non_admin.xml";
		try { 
			String requestString = getQueryString(filename);
			OMElement requestElement = convertStringToOMElement(requestString); 
			OMElement responseElement = getServiceClient(getAllDBlookups).sendReceive(requestElement);
			JAXBElement responseJaxb = WorkplaceJAXBUtil.getJAXBUtil().unMashallFromString(responseElement.toString());
			ResponseMessageType r = (ResponseMessageType)responseJaxb.getValue();
			String msg = r.getResponseHeader().getResultStatus().getStatus().getType();
			assertEquals("ERROR", msg);
		} catch (Exception e) { 
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void A140_SetDBlookup_admin() throws Exception { //swc20160722
		String filename = testFileDir + "/setDBlookup_admin.xml";
		try { 
			String requestString = getQueryString(filename);
			OMElement requestElement = convertStringToOMElement(requestString); 
			OMElement responseElement = getServiceClient(setDBlookup).sendReceive(requestElement);
			JAXBElement responseJaxb = WorkplaceJAXBUtil.getJAXBUtil().unMashallFromString(responseElement.toString());
			ResponseMessageType r = (ResponseMessageType)responseJaxb.getValue();
			String msg = r.getResponseHeader().getResultStatus().getStatus().getType();
			assertEquals("DONE", msg);			
			A190_DeleteDBlookup_admin(); //clean it up (in case this gets run after the DeleteDBlookup_admin()
		} catch (Exception e) { 
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void A150_SetDBlookup_non_admin() throws Exception { //swc20160722
		String filename = testFileDir + "/setDBlookup_non_admin.xml";
		try { 
			String requestString = getQueryString(filename);
			OMElement requestElement = convertStringToOMElement(requestString); 
			OMElement responseElement = getServiceClient(setDBlookup).sendReceive(requestElement);
			JAXBElement responseJaxb = WorkplaceJAXBUtil.getJAXBUtil().unMashallFromString(responseElement.toString());
			ResponseMessageType r = (ResponseMessageType)responseJaxb.getValue();
			String msg = r.getResponseHeader().getResultStatus().getStatus().getType();
			assertEquals("ERROR", msg);
		} catch (Exception e) { 
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void A160_GetDBlookup_admin() throws Exception { //swc20160722
		String filename = testFileDir + "/getDBlookup_admin.xml";
		try { 
			String requestString = getQueryString(filename);
			OMElement requestElement = convertStringToOMElement(requestString); 
			OMElement responseElement = getServiceClient(getDBlookup).sendReceive(requestElement);
			JAXBElement responseJaxb = WorkplaceJAXBUtil.getJAXBUtil().unMashallFromString(responseElement.toString());
			ResponseMessageType r = (ResponseMessageType)responseJaxb.getValue();
			String msg = r.getResponseHeader().getResultStatus().getStatus().getType();
			assertEquals("DONE", msg);
		} catch (Exception e) { 
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void A170_GetDBlookup_schema_admin() throws Exception { //swc20160722
		String filename = testFileDir + "/getDBlookup_schema_admin.xml";
		try { 
			String requestString = getQueryString(filename);
			OMElement requestElement = convertStringToOMElement(requestString); 
			OMElement responseElement = getServiceClient(getDBlookup).sendReceive(requestElement);
			JAXBElement responseJaxb = WorkplaceJAXBUtil.getJAXBUtil().unMashallFromString(responseElement.toString());
			ResponseMessageType r = (ResponseMessageType)responseJaxb.getValue();
			String msg = r.getResponseHeader().getResultStatus().getStatus().getType();
			assertEquals("DONE", msg);
		} catch (Exception e) { 
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void A180_GetDBlookup_non_admin() throws Exception { //swc20160722
		String filename = testFileDir + "/getDBlookup_non_admin.xml";
		try { 
			String requestString = getQueryString(filename);
			OMElement requestElement = convertStringToOMElement(requestString); 
			OMElement responseElement = getServiceClient(getDBlookup).sendReceive(requestElement);
			JAXBElement responseJaxb = WorkplaceJAXBUtil.getJAXBUtil().unMashallFromString(responseElement.toString());
			ResponseMessageType r = (ResponseMessageType)responseJaxb.getValue();
			String msg = r.getResponseHeader().getResultStatus().getStatus().getType();
			assertEquals("ERROR", msg);
		} catch (Exception e) { 
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void A190_DeleteDBlookup_admin() throws Exception { //swc20160722
		String filename = testFileDir + "/deleteDBlookup_admin.xml";
		try { 
			String requestString = getQueryString(filename);
			OMElement requestElement = convertStringToOMElement(requestString); 
			OMElement responseElement = getServiceClient(deleteDBlookup).sendReceive(requestElement);
			JAXBElement responseJaxb = WorkplaceJAXBUtil.getJAXBUtil().unMashallFromString(responseElement.toString());
			ResponseMessageType r = (ResponseMessageType)responseJaxb.getValue();
			String msg = r.getResponseHeader().getResultStatus().getStatus().getType();
			assertEquals("DONE", msg);
		} catch (Exception e) { 
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void A200_DeleteDBlookup_non_admin() throws Exception { //swc20160722
		String filename = testFileDir + "/deleteDBlookup_non_admin.xml";
		try { 
			String requestString = getQueryString(filename);
			OMElement requestElement = convertStringToOMElement(requestString); 
			OMElement responseElement = getServiceClient(deleteDBlookup).sendReceive(requestElement);
			JAXBElement responseJaxb = WorkplaceJAXBUtil.getJAXBUtil().unMashallFromString(responseElement.toString());
			ResponseMessageType r = (ResponseMessageType)responseJaxb.getValue();
			String msg = r.getResponseHeader().getResultStatus().getStatus().getType();
			assertEquals("ERROR", msg);
		} catch (Exception e) { 
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void A210_DeleteDBlookup_nonexist_admin() throws Exception { //swc20160722
		String filename = testFileDir + "/deleteDBlookup_nonexist_admin.xml";
		try { 
			String requestString = getQueryString(filename);
			OMElement requestElement = convertStringToOMElement(requestString); 
			OMElement responseElement = getServiceClient(deleteDBlookup).sendReceive(requestElement);
			JAXBElement responseJaxb = WorkplaceJAXBUtil.getJAXBUtil().unMashallFromString(responseElement.toString());
			ResponseMessageType r = (ResponseMessageType)responseJaxb.getValue();
			StatusType st = r.getResponseHeader().getResultStatus().getStatus();
			assertEquals("DONE", st.getType());
			assertEquals("no dblookup row was deleted (could be due to no target row found)! - Workplace processing completed", st.getValue());
		} catch (Exception e) { 
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void A220_DeleteDBlookup_missingAttrib_admin() throws Exception { //swc20160722
		String filename = testFileDir + "/deleteDBlookup_missing_attrib_admin.xml";
		try { 
			String requestString = getQueryString(filename);
			OMElement requestElement = convertStringToOMElement(requestString); 
			OMElement responseElement = getServiceClient(deleteDBlookup).sendReceive(requestElement);
			JAXBElement responseJaxb = WorkplaceJAXBUtil.getJAXBUtil().unMashallFromString(responseElement.toString());
			ResponseMessageType r = (ResponseMessageType)responseJaxb.getValue();
			String msg = r.getResponseHeader().getResultStatus().getStatus().getType();
			assertEquals("ERROR", msg);
		} catch (Exception e) { 
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	
	/************** PRIVATE METHODS **********************/


	/**
	 * This method executes the error requests 
	 * and verifies the response status is ERROR 
	 * and compares the ERROR string is same as given error string
	 * This is to ensure that specific error messages are returned in
	 * response instead of generic Database Error
	 * 
	 * @param xmlFileName - file containing xml request 
	 * @param compareErrStr - error string in the response
	 * @param serviceName - service to be requested
	 * @throws Exception
	 */
	private void serviceExecWithErrResp(String xmlFileName, String compareErrStr, String serviceName) throws Exception{

		ResponseMessageType r = serviceRequest(serviceName, xmlFileName);
		checkResponseStatus(r, "ERROR");
		String resultErrString = r.getResponseHeader().getResultStatus().getStatus().getValue();
		assertTrue(resultErrString.contains(compareErrStr));	
		//String resultStatusType = r.getResponseHeader().getResultStatus().getStatus().getType();
	}


	/**
	 * This method executes the service given as a parameter with the request xml file. 
	 * It returns the object ResponseMessageType containing the response element
	 * 
	 * @param serviceName - service to be requested
	 * @param filename - xml request file name
	 * @return
	 * @throws Exception
	 * 
	 */
	private ResponseMessageType serviceRequest(String serviceName, String filename) throws Exception {

		String requestString = getQueryString(filename);
		OMElement requestElement = convertStringToOMElement(requestString); 
		OMElement responseElement = getServiceClient(workplaceTargetEPR + serviceName).sendReceive(requestElement);
		JAXBElement responseJaxb = WorkplaceJAXBUtil.getJAXBUtil().unMashallFromString(responseElement.toString());
		ResponseMessageType rspMsgType = (ResponseMessageType)responseJaxb.getValue();

		return rspMsgType;
	}

	/**
	 * This method gets the status from response header 
	 * and compares with the param status
	 * 
	 * @param respMsgType - ResponseMessageType
	 * @param statusStr - status to compare
	 * @throws Exception
	 */
	private void checkResponseStatus(ResponseMessageType respMsgType, String statusStr) throws Exception{
		String statusType = respMsgType.getResponseHeader().getResultStatus().getStatus().getType();
		assertEquals("Checking for: " + statusType  ,statusStr.toUpperCase(), statusType.toUpperCase());
	}

	/**
	 * This methods verifies if the folder was returned in response
	 * Also it checks that number of folders returned in response are more than 0
	 * 
	 * @param respMsgType - ResponseMessageType
	 * @param conceptName - foldername to be searched within response
	 * @param protectedAccess - Its Boolean object type so that we can pass null if not using it
	 * @return
	 * @throws Exception
	 */
	private boolean verifyFolder(ResponseMessageType respMsgType, String conceptName, boolean includesBlob, boolean typeAll, Boolean protectedAccess) throws Exception {

		JAXBUnWrapHelper helper = new  JAXBUnWrapHelper();
		FoldersType folders = (FoldersType)helper.getObjectByClass(respMsgType.getMessageBody().getAny(),FoldersType.class);

		// verify folders obj is not null
		assertNotNull(folders);

		boolean foundConcept= false;
		for(FolderType folder: folders.getFolder())
		{
			// verify all the elements only if search file has been found
			if (folder.getName().toLowerCase().equals(conceptName.toLowerCase())){
				assertTrue(folder.getName()!=null);
				assertTrue(folder.getGroupId()!=null);
				assertTrue(folder.getIndex()!=null);
				assertTrue(folder.getParentIndex()!=null);
				assertTrue(folder.getShareId()!=null);
				assertTrue(folder.getUserId()!=null);
				assertTrue(folder.getVisualAttributes()!=null);
				assertTrue(folder.getWorkXmlI2B2Type()!=null);

				if(includesBlob)
					assertTrue(folder.getWorkXml()!=null);		

				if(typeAll)
					assertTrue(folder.getEntryDate()!=null);

				if(protectedAccess != null){
					String protectedAccessVal = "N";
					if(protectedAccess.booleanValue()== true)
						protectedAccessVal = "Y";
					else
						protectedAccessVal = "N";

					assertTrue(folder.getProtectedAccess().equalsIgnoreCase(protectedAccessVal));
				}

				foundConcept = true;

				if (conceptName.equals("11 years old demo"))
					findDemographicsIndex = folder.getIndex().replaceAll("\\\\", "\\\\\\\\");
				break;
			}
		}

		// verify number of resulting folders are more than/equal to 1 
		assertTrue(folders.getFolder().size() >= 1);		

		return foundConcept;
	}





	public static String getQueryString(String filename) throws Exception {


		//StringBuffer queryStr = new StringBuffer();
		String queryStr = "";
		DataInputStream dataStream = new DataInputStream(new FileInputStream(
				filename));
		while (dataStream.available() > 0) {
			queryStr += (dataStream.readLine() + "\n");
		}
		queryStr = queryStr.replaceAll("~~~demoIndex~~~", demoIndex);
		queryStr = queryStr.replaceAll("~~~sharedIndex~~~", sharedIndex);
		queryStr = queryStr.replaceAll("~~~i2b2Index~~~", i2b2Index);
		queryStr = queryStr.replaceAll("~~~findDemographicsIndex~~~", findDemographicsIndex);
		return queryStr;
	}	

}





