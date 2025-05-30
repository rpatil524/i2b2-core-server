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
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="query_status_type" type="{http://i2b2.mgh.harvard.edu/querytool}query_status_typeType"/>
 *         &lt;element name="query_result_type" type="{http://i2b2.mgh.harvard.edu/querytool}query_result_typeType"/>
 *         &lt;element name="query_master" type="{http://i2b2.mgh.harvard.edu/querytool}query_masterType"/>
 *         &lt;element name="query_instance" type="{http://i2b2.mgh.harvard.edu/querytool}query_instanceType"/>
 *         &lt;element name="query_result_instance" type="{http://i2b2.mgh.harvard.edu/querytool}query_result_instanceType"/>
 *         &lt;element name="patient_enc_collection" type="{http://i2b2.mgh.harvard.edu/querytool}patient_enc_collectionType"/>
 *         &lt;element name="patient_set_collection" type="{http://i2b2.mgh.harvard.edu/querytool}patient_set_collectionType"/>
 *         &lt;element name="xml_result" type="{http://i2b2.mgh.harvard.edu/querytool}xml_resultType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "queryStatusType",
    "queryResultType",
    "queryMaster",
    "queryInstance",
    "queryResultInstance",
    "patientEncCollection",
    "patientSetCollection",
    "xmlResult"
})
@XmlRootElement(name = "datatypes_example")
public class DatatypesExample {

    @XmlElement(name = "query_status_type", required = true)
    protected QueryStatusTypeType queryStatusType;
    @XmlElement(name = "query_result_type", required = true)
    protected QueryResultTypeType queryResultType;
    @XmlElement(name = "query_master", required = true)
    protected QueryMasterType queryMaster;
    @XmlElement(name = "query_instance", required = true)
    protected QueryInstanceType queryInstance;
    @XmlElement(name = "query_result_instance", required = true)
    protected QueryResultInstanceType queryResultInstance;
    @XmlElement(name = "patient_enc_collection", required = true)
    protected PatientEncCollectionType patientEncCollection;
    @XmlElement(name = "patient_set_collection", required = true)
    protected PatientSetCollectionType patientSetCollection;
    @XmlElement(name = "xml_result", required = true)
    protected XmlResultType xmlResult;

    /**
     * Gets the value of the queryStatusType property.
     * 
     * @return
     *     possible object is
     *     {@link QueryStatusTypeType }
     *     
     */
    public QueryStatusTypeType getQueryStatusType() {
        return queryStatusType;
    }

    /**
     * Sets the value of the queryStatusType property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryStatusTypeType }
     *     
     */
    public void setQueryStatusType(QueryStatusTypeType value) {
        this.queryStatusType = value;
    }

    /**
     * Gets the value of the queryResultType property.
     * 
     * @return
     *     possible object is
     *     {@link QueryResultTypeType }
     *     
     */
    public QueryResultTypeType getQueryResultType() {
        return queryResultType;
    }

    /**
     * Sets the value of the queryResultType property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryResultTypeType }
     *     
     */
    public void setQueryResultType(QueryResultTypeType value) {
        this.queryResultType = value;
    }

    /**
     * Gets the value of the queryMaster property.
     * 
     * @return
     *     possible object is
     *     {@link QueryMasterType }
     *     
     */
    public QueryMasterType getQueryMaster() {
        return queryMaster;
    }

    /**
     * Sets the value of the queryMaster property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryMasterType }
     *     
     */
    public void setQueryMaster(QueryMasterType value) {
        this.queryMaster = value;
    }

    /**
     * Gets the value of the queryInstance property.
     * 
     * @return
     *     possible object is
     *     {@link QueryInstanceType }
     *     
     */
    public QueryInstanceType getQueryInstance() {
        return queryInstance;
    }

    /**
     * Sets the value of the queryInstance property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryInstanceType }
     *     
     */
    public void setQueryInstance(QueryInstanceType value) {
        this.queryInstance = value;
    }

    /**
     * Gets the value of the queryResultInstance property.
     * 
     * @return
     *     possible object is
     *     {@link QueryResultInstanceType }
     *     
     */
    public QueryResultInstanceType getQueryResultInstance() {
        return queryResultInstance;
    }

    /**
     * Sets the value of the queryResultInstance property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryResultInstanceType }
     *     
     */
    public void setQueryResultInstance(QueryResultInstanceType value) {
        this.queryResultInstance = value;
    }

    /**
     * Gets the value of the patientEncCollection property.
     * 
     * @return
     *     possible object is
     *     {@link PatientEncCollectionType }
     *     
     */
    public PatientEncCollectionType getPatientEncCollection() {
        return patientEncCollection;
    }

    /**
     * Sets the value of the patientEncCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link PatientEncCollectionType }
     *     
     */
    public void setPatientEncCollection(PatientEncCollectionType value) {
        this.patientEncCollection = value;
    }

    /**
     * Gets the value of the patientSetCollection property.
     * 
     * @return
     *     possible object is
     *     {@link PatientSetCollectionType }
     *     
     */
    public PatientSetCollectionType getPatientSetCollection() {
        return patientSetCollection;
    }

    /**
     * Sets the value of the patientSetCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link PatientSetCollectionType }
     *     
     */
    public void setPatientSetCollection(PatientSetCollectionType value) {
        this.patientSetCollection = value;
    }

    /**
     * Gets the value of the xmlResult property.
     * 
     * @return
     *     possible object is
     *     {@link XmlResultType }
     *     
     */
    public XmlResultType getXmlResult() {
        return xmlResult;
    }

    /**
     * Sets the value of the xmlResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlResultType }
     *     
     */
    public void setXmlResult(XmlResultType value) {
        this.xmlResult = value;
    }

}
