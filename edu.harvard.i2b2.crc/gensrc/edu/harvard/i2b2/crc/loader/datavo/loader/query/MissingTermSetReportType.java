//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.08.22 at 10:48:46 AM EDT 
//


package edu.harvard.i2b2.crc.loader.datavo.loader.query;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for missing_term_set_reportType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="missing_term_set_reportType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="mapped" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="unmapped" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "missing_term_set_reportType")
public class MissingTermSetReportType {

    @XmlAttribute(name = "mapped")
    protected Integer mapped;
    @XmlAttribute(name = "unmapped")
    protected Integer unmapped;

    /**
     * Gets the value of the mapped property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMapped() {
        return mapped;
    }

    /**
     * Sets the value of the mapped property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMapped(Integer value) {
        this.mapped = value;
    }

    /**
     * Gets the value of the unmapped property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getUnmapped() {
        return unmapped;
    }

    /**
     * Sets the value of the unmapped property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setUnmapped(Integer value) {
        this.unmapped = value;
    }

}
