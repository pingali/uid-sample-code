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
package in.gov.uidai.auth.aua.uidaiclient;

import in.gov.uidai.auth.aua.helper.DigitalSigner;
import in.gov.uidai.authentication.uid_auth_request._1.Auth;
import in.gov.uidai.authentication.uid_auth_response._1.AuthRes;

import java.io.StringWriter;
import java.net.InetAddress;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.urlconnection.HTTPSProperties;

/**
 * <code>AuthClient</code> class can be used for submitting an Authentication request to
 * UIDAI Auth Server, and to get the response back.  Given an <code>Auth</code> object, this
 * class (@see {@link AuthClient#authenticate}) will convert it to XML string, then, 
 * digitally sign it, and submit it to UIDAI Auth Server using HTTP POST message.  After, 
 * receiving the resonse, this class converts the response XML into authentication response
 * @see AuthRes object  
 * 
 * 
 * @author UIDAI
 *
 */
public class AuthClient {
	private URI serverURI = null;
	private DigitalSigner digitalSignator;

	/**
	 * Constructor
	 * @param serverUri - URI of the authentication server
	 */
	public AuthClient(URI serverUri) {
		this.serverURI = serverUri;
	}
	
	/**
	 * Method to perform authentication
	 * @param auth Authentication request
	 * @return Authentication response
	 */
	public AuthRes authenticate(Auth auth) {
		try {
			String signedXML = generateSignedAuthXML(auth);

			URI authServiceURI = new URI(serverURI.toString() + (serverURI.toString().endsWith("/") ? "" : "/")
					+ auth.getAc() + "/" + auth.getUid().charAt(0) + "/" + auth.getUid().charAt(1));

			WebResource webResource = Client.create(getClientConfig(serverURI.getScheme())).resource(authServiceURI);

			return webResource.header("REMOTE_ADDR", InetAddress.getLocalHost().getHostAddress()).post(AuthRes.class,
					signedXML);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Exception during authentication " + e.getMessage(), e);
		}

	}

	private String generateSignedAuthXML(Auth auth) throws JAXBException, Exception {
		StringWriter authXML = new StringWriter();

		JAXBElement authElement = new JAXBElement(new QName(
				"http://www.uidai.gov.in/authentication/uid-auth-request/1.0", "Auth"), Auth.class, auth);

		JAXBContext.newInstance(Auth.class).createMarshaller().marshal(authElement, authXML);
		boolean includeKeyInfo = true;

		String signedXML = this.digitalSignator.signXML(authXML.toString(), includeKeyInfo);
		return signedXML;
	}

	private ClientConfig getClientConfig(String uriScheme) {
		ClientConfig config = new DefaultClientConfig();

		if (uriScheme.equalsIgnoreCase("https")) {
			X509TrustManager xtm = new X509TrustManager() {

				@Override
				public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
					return;
				}

				@Override
				public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
					return;
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};
			TrustManager mytm[] = { xtm };

			HostnameVerifier hv = new HostnameVerifier() {

				public boolean verify(String hostname, SSLSession sslSession) {
					return true;
				}
			};

			SSLContext ctx = null;

			try {
				ctx = SSLContext.getInstance("SSL");
				ctx.init(null, mytm, null);
				config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(hv, ctx));
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (KeyManagementException e) {
				e.printStackTrace();
			}
		}

		return config;
	}
	
	/**
	 * Method to inject an instance of <code>DigitalSigner</code> class.
	 * @param digitalSignator
	 */
	public void setDigitalSignator(DigitalSigner digitalSignator) {
		this.digitalSignator = digitalSignator;
	}
}
