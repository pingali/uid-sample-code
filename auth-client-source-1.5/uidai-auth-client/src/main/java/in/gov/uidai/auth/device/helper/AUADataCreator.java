/*******************************************************************************
 * DISCLAIMER: The sample code or utility or tool described herein
 *    is provided on an "as is" basis, without warranty of any kind.
 *    UIDAI does not warrant or guarantee the individual success
 *    developers may have in implementing the sample code on their
 *    environment. 
 *    
 *    UIDAI does not warrant, guarantee or make any representations
 *    of any kind with respect to the sample code and does not make
 *    any representations or warranties regarding the use, results
 *    of use, accuracy, timeliness or completeness of any data or
 *    information relating to the sample code. UIDAI disclaims all
 *    warranties, express or implied, and in particular, disclaims
 *    all warranties of merchantability, fitness for a particular
 *    purpose, and warranties related to the code, or any service
 *    or software related thereto. 
 *    
 *    UIDAI is not responsible for and shall not be liable directly
 *    or indirectly for any direct, indirect damages or costs of any
 *    type arising out of use or any action taken by you or others
 *    related to the sample code.
 *    
 *    THIS IS NOT A SUPPORTED SOFTWARE.
 ******************************************************************************/
package in.gov.uidai.auth.device.helper;

import in.gov.uidai.auth.device.model.AUAData;
import in.gov.uidai.authentication.uid_auth_request_data._1.Pid;

import java.io.StringWriter;
import java.text.SimpleDateFormat;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.commons.lang.StringUtils;

/**
 * <code>AUADataCreator</code> class provides method to package data that is typically 
 * sent to AUA server.
 * 
 * @author UIDAI
 *
 */
public class AUADataCreator {
	private Encrypter encrypter;
	private HashGenerator hashGenerator;

	/**
	 * Constructor
	 * @param encrypter - Encrypter instance for encrypting data
	 * @see Encrypter
	 */
	
	public AUADataCreator(Encrypter encrypter) {
		this.hashGenerator = new HashGenerator();
		this.encrypter = encrypter;
	}

	/**
	 * Prepares data for sending to AUA server.
	 * @param uid - Aadhaar number as collected by authentication device.
	 * @param terminalId - Terminal ID identifying the authentication device
	 * @param pid - Instance of <code>Pid</Pid> class that contains data to be used for authentication purposes.
	 * @return Instance of {@link AUAData} class
	 */
	public AUAData prepareAUAData(String uid, String terminalId, Pid pid) {

		try {
			String pidXML = createPidXML(pid);

			byte[] pidXmlBytes = pidXML.getBytes();
			byte[] sessionKey = this.encrypter.generateSessionKey();
			byte[] encXMLPIDData = this.encrypter.encryptUsingSessionKey(sessionKey, pidXmlBytes);

			byte[] hmac = this.hashGenerator.generateSha256Hash(pidXmlBytes);
			byte[] encryptedHmacBytes = this.encrypter.encryptUsingSessionKey(sessionKey, hmac);

			byte[] encryptedSessionKey = this.encrypter.encryptUsingPublicKey(sessionKey);

			SimpleDateFormat df2 = new SimpleDateFormat("yyyyMMdd");
			String certificateIdentifier = df2.format(this.encrypter.getCertExpiryDate());
			
			String demoXML = getDemoXML(pid);
			byte[] hashedDemoXML = StringUtils.leftPad("0", 64, '0').getBytes(); 
			if (StringUtils.isNotBlank(demoXML)) {
				hashedDemoXML = hashGenerator.generateSha256Hash(demoXML.getBytes());
			}

			return new AUAData(uid, terminalId, encXMLPIDData, encryptedHmacBytes, encryptedSessionKey, hashedDemoXML, certificateIdentifier);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * This method returns Demo XML string for a given Pid.  
	 * This method can be used for verifying Demo XML hash received in auth responses.
	 * @param pid Pid instance
	 * @return String containing XML representation of Demo element
	 */
	private String getDemoXML(Pid pid) {
		StringWriter sw = new StringWriter();

		try {
			JAXBContext.newInstance(Pid.class).createMarshaller().marshal(pid, sw);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		String pidXML = sw.toString();

		try {
			int start = pidXML.indexOf("<Demo>");
			int end = pidXML.indexOf("</Demo>") + ("<Demo>".length() + 1);

			return pidXML.substring(start, end);
		} catch (Exception e) {
			//In case of exception return blank string
			e.printStackTrace();
			return "";
		}

	}

	private String createPidXML(Pid pid) {
		StringWriter pidXML = new StringWriter();

		try {
			JAXBContext.newInstance(Pid.class).createMarshaller().marshal(pid, pidXML);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return pidXML.toString();
	}

}
