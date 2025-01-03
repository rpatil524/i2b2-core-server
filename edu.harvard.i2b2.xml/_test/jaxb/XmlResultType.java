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
 * <p>Java class for xml_resultType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="xml_resultType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="xml_result_id" type="{http://i2b2.mgh.harvard.edu/querytool}xml_result_idType"/>
 *         &lt;element name="result_instance_id" type="{http://i2b2.mgh.harvard.edu/querytool}result_instance_idType"/>
 *         &lt;element name="xml_value" type="{http://i2b2.mgh.harvard.edu/querytool}xml_valueType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "xml_resultType", propOrder = {
    "xmlResultId",
    "resultInstanceId",
    "xmlValue"
})
public class XmlResultType {

    @XmlElement(name = "xml_result_id")
    protected int xmlResultId;
    @XmlElement(name = "result_instance_id")
    protected int resultInstanceId;
    @XmlElement(name = "xml_value", required = true)
    protected XmlValueType xmlValue;

    /**
     * Gets the value of the xmlResultId property.
     * 
     */
    public int getXmlResultId() {
        return xmlResultId;
    }

    /**
     * Sets the value of the xmlResultId property.
     * 
     */
    public void setXmlResultId(int value) {
        this.xmlResultId = value;
    }

    /**
     * Gets the value of the resultInstanceId property.
     * 
     */
    public int getResultInstanceId() {
        return resultInstanceId;
    }

    /**
     * Sets the value of the resultInstanceId property.
     * 
     */
    public void setResultInstanceId(int value) {
        this.resultInstanceId = value;
    }

    /**
     * Gets the value of the xmlValue property.
     * 
     * @return
     *     possible object is
     *     {@link XmlValueType }
     *     
     */
    public XmlValueType getXmlValue() {
        return xmlValue;
    }

    /**
     * Sets the value of the xmlValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlValueType }
     *     
     */
    public void setXmlValue(XmlValueType value) {
        this.xmlValue = value;
    }

}
