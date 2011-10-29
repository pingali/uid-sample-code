//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.10.11 at 08:41:06 PM IST 
//


package in.gov.uidai.authentication.uid_auth_request_data._1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for matchingStrategy.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="matchingStrategy">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="E"/>
 *     &lt;enumeration value="P"/>
 *     &lt;enumeration value="F"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "matchingStrategy")
@XmlEnum
public enum MatchingStrategy {


    /**
     * Exact Match
     *                          
     * 
     */
    E,

    /**
     * Partial Match
     *                          
     * 
     */
    P,

    /**
     * Fuzzy Match
     *                          
     * 
     */
    F;

    public String value() {
        return name();
    }

    public static MatchingStrategy fromValue(String v) {
        return valueOf(v);
    }

}
