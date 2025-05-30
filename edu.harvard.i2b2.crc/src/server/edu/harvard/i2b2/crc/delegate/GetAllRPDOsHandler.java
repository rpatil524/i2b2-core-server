/*******************************************************************************
 * Copyright (c) 2006-2018 Massachusetts General Hospital 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. I2b2 is also distributed under
 * the terms of the Healthcare Disclaimer.
 ******************************************************************************/
/*

 * 
 * Contributors:
 * 		Mike Mendis
 */
package edu.harvard.i2b2.crc.delegate;

import edu.harvard.i2b2.common.exception.I2B2DAOException;
import edu.harvard.i2b2.common.exception.I2B2Exception;
import edu.harvard.i2b2.common.util.jaxb.JAXBUtilException;
import edu.harvard.i2b2.crc.axis2.MessageFactory;
import edu.harvard.i2b2.crc.axis2.GetAllRPDOsDataMessage;
import edu.harvard.i2b2.crc.dao.RPDODao;
import edu.harvard.i2b2.crc.datavo.i2b2message.MessageHeaderType;
import edu.harvard.i2b2.crc.datavo.i2b2message.ResponseMessageType;
import edu.harvard.i2b2.crc.datavo.pdo.query.RpdoType;
import edu.harvard.i2b2.crc.datavo.pdo.query.RpdosType;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GetAllRPDOsHandler extends RPDOReqHandler {
	private static Log log = LogFactory.getLog(GetAllRPDOsHandler.class);
	private GetAllRPDOsDataMessage RPDOsDataMsg = null;
	private MessageHeaderType msgHdrType = null;
	private RPDODao RPDODao = null;
	private boolean isAdmin = false;

	
	
	public GetAllRPDOsHandler(GetAllRPDOsDataMessage requestMsg) throws I2B2Exception{
		try {
			RPDOsDataMsg = requestMsg;
			msgHdrType = RPDOsDataMsg.getMessageHeaderType();
			//isAdmin = isAdmin(msgHdrType);	
			RPDODao = new RPDODao(msgHdrType);
		} catch (JAXBUtilException e) {
			log.error("error setting up GetAllRPDOsHandler");
			throw new I2B2Exception("GetAllRPDOsHandler not configured");
		}
	}
	
	@Override
	public String execute() throws I2B2Exception {
		//log.info(" execute()");
		MessageHeaderType msgHdr = MessageFactory.createResponseMessageHeader(msgHdrType);          
		ResponseMessageType responseMessageType = null;
		RpdosType dblus = new RpdosType();
		List<RpdoType> response = null;
		try {
			response = RPDODao.findRPDOs();
		} catch (I2B2DAOException e1) {
			String dbError = "Database error";
			responseMessageType = MessageFactory.doBuildErrorResponse(msgHdr, dbError);
			log.error(dbError);
		}
		if (responseMessageType == null) { // no db error
			if (null == response) { // but response is empty
				String emptyResult = "query results are empty";
				log.warn(emptyResult);
				responseMessageType = MessageFactory.doBuildErrorResponse(msgHdr, emptyResult);
			} else { 
				Iterator<RpdoType> it = response.iterator();
				if (it.hasNext()) {
					do {
						RpdoType dblu = it.next();
						dblus.getRpdo().add(dblu);
					} while (it.hasNext());
					responseMessageType = MessageFactory.createBuildResponse(msgHdr, dblus);
				} else {
					String msg = "No RPDO row was found!";
					log.info(msg);
					responseMessageType = MessageFactory.createNonStandardResponse(msgHdr, msg);					
				}
			}
		}
        String responseVdo = null;
		responseVdo = MessageFactory.convertToXMLString(responseMessageType);
		return responseVdo;
	}    	
}
