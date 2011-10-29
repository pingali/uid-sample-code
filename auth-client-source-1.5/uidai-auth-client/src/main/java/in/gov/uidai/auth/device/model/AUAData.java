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
package in.gov.uidai.auth.device.model;

/**
 * <code>AUAData</code> class represents the information that typically needs to be 
 * transferred by an authentication device to AUA server. 
 * 
 * @author UIDAI
 *
 */
public class AUAData {
	
	String uid;
	String terminalId;
	byte[] encryptedPid;
	byte[] encrytpedHmac;
	byte[] encryptedSkey;
	byte[] hashedDemoXML;
	String certificateIdentifier;

	/**
	 * Constructor
	 * @param uid - Aadhaar number.
	 * @param terminalId - Id of the authentication device.
	 * @param encryptedPid - Encrypted Pid XML, that has been encrypted using session key.
	 * @param encrytpedHmac - Encrypted HMAC of Pid XML string, that has been encrypted using session key.
	 * @param encryptedSkey - Session key that has been encrypted using UIDAI public certificate.
	 * @param certificateIdentifier - Certificate identifier, expiry date of UIDAI public certificate in YYYYMMDD format.
	 */
	public AUAData(String uid, String terminalId, byte[] encryptedPid, byte[] encrytpedHmac,
			byte[] encryptedSkey, byte[] hashedDemoXML, String certificateIdentifier) {
		this.uid = uid;
		this.terminalId = terminalId;
		this.encryptedPid = encryptedPid;
		this.encrytpedHmac = encrytpedHmac;
		this.encryptedSkey = encryptedSkey;
		this.hashedDemoXML = hashedDemoXML;
		this.certificateIdentifier = certificateIdentifier;
	}
	
	public String getUid() {
		return uid;
	};

	public String getTerminalId() {
		return terminalId;
	}
	
	public byte[] getEncryptedPid() {
		return encryptedPid;
	}

	public byte[] getEncrytpedHmac() {
		return encrytpedHmac;
	}

	public byte[] getEncryptedSkey() {
		return encryptedSkey;
	}

	public byte[] getHashedDemoXML() {
		return hashedDemoXML;
	}
	
	public String getCertificateIdentifier() {
		return certificateIdentifier;
	}
	
}
