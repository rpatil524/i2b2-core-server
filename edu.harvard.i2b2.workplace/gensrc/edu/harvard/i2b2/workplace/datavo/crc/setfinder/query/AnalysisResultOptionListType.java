//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.04.17 at 02:58:01 PM EDT 
//


package edu.harvard.i2b2.workplace.datavo.crc.setfinder.query;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for analysis_result_optionListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="analysis_result_optionListType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result_output" type="{http://www.i2b2.org/xsd/cell/crc/psm/analysisdefinition/1.1/}analysis_result_optionType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "analysis_result_optionListType", namespace = "http://www.i2b2.org/xsd/cell/crc/psm/analysisdefinition/1.1/", propOrder = {
    "resultOutput"
})
public class AnalysisResultOptionListType {

    @XmlElement(name = "result_output")
    protected List<AnalysisResultOptionType> resultOutput;

    /**
     * Gets the value of the resultOutput property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resultOutput property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResultOutput().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AnalysisResultOptionType }
     * 
     * 
     */
    public List<AnalysisResultOptionType> getResultOutput() {
        if (resultOutput == null) {
            resultOutput = new ArrayList<AnalysisResultOptionType>();
        }
        return this.resultOutput;
    }

}
