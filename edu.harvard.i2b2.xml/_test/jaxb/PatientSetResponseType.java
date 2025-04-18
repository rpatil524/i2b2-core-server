/*******************************************************************************
 * Copyright (c) 2006-2018 Massachusetts General Hospital 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. I2b2 is also distributed under
 * the terms of the Healthcare Disclaimer.
 ******************************************************************************/
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0.3-b01-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.10.27 at 11:21:39 AM EDT 
//


package jaxb;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for patient_set_responseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="patient_set_responseType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://i2b2.mgh.harvard.edu/querytool}responseType">
 *       &lt;sequence>
 *         &lt;element name="patient_set" type="{http://i2b2.mgh.harvard.edu/querytool}patient_setType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "patient_set_responseType", propOrder = {
    "patientSet"
})
public class PatientSetResponseType
    extends ResponseType
{

    @XmlElement(name = "patient_set", required = true)
    protected PatientSetType patientSet;

    /**
     * Gets the value of the patientSet property.
     * 
     * @return
     *     possible object is
     *     {@link PatientSetType }
     *     
     */
    public PatientSetType getPatientSet() {
        return patientSet;
    }

    /**
     * Sets the value of the patientSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link PatientSetType }
     *     
     */
    public void setPatientSet(PatientSetType value) {
        this.patientSet = value;
    }

}
