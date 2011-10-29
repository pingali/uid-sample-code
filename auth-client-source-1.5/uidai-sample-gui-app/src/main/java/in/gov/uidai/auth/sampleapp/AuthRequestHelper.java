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
package in.gov.uidai.auth.sampleapp;

import in.gov.uidai.authentication.uid_auth_request._1.Uses;
import in.gov.uidai.authentication.uid_auth_request_data._1.Bio;
import in.gov.uidai.authentication.uid_auth_request_data._1.BioMetricType;
import in.gov.uidai.authentication.uid_auth_request_data._1.BiometricPosition;
import in.gov.uidai.authentication.uid_auth_request_data._1.Bios;
import in.gov.uidai.authentication.uid_auth_request_data._1.Demo;
import in.gov.uidai.authentication.uid_auth_request_data._1.Gender;
import in.gov.uidai.authentication.uid_auth_request_data._1.Meta;
import in.gov.uidai.authentication.uid_auth_request_data._1.Pa;
import in.gov.uidai.authentication.uid_auth_request_data._1.Pfa;
import in.gov.uidai.authentication.uid_auth_request_data._1.Pi;
import in.gov.uidai.authentication.uid_auth_request_data._1.Pid;
import in.gov.uidai.authentication.uid_auth_request_data._1.Pv;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

/**
 * <code>AuthRequestHelper</code> provides utility method to create Pid and Uses element.
 * 
 * 
 * @author UIDAI
 *
 */
public class AuthRequestHelper {

	public static Pid createPid(Meta metaData, AuthenticationRequestData data) {
		Map<String, String> languageToCodeMap = new HashMap<String, String>();
		languageToCodeMap.put("English", "23");
		languageToCodeMap.put("Hindi", "06");
		languageToCodeMap.put("Kannada", "07");
		languageToCodeMap.put("Malayalam", "11");

		Pid pid = new Pid();

		
		pid.setMeta(metaData);

		if (data != null) {
			Demo demo = new Demo();

			demo.setLang(languageToCodeMap.get(data.getLanguage()));

			if (data.getName() != null && !data.getName().isEmpty()
					|| data.getLname() != null && !data.getLname().isEmpty()
					|| data.getDob() != null && !data.getDob().isEmpty()
					|| data.getDobType() != null
					&& !data.getDobType().isEmpty() || data.getEmail() != null
					&& !data.getEmail().isEmpty() || data.getGender() != null
					|| data.getAge() != null && !data.getAge().isEmpty()
					|| data.getPhoneNo() != null
					&& !data.getPhoneNo().isEmpty()) {
				Pi pi = new Pi();
				pi.setMs(data.getNameMatchStrategy());
				pi.setMv(data.getNameMatchValue());
				pi.setName(data.getName());

				if (data.getLname() != null && !data.getLname().isEmpty()) {
					pi.setLmv(data.getLocalNameMatchValue());
					pi.setLname(data.getLname());
				}

				pi.setDob(data.getDob());
				pi.setDobt(data.getDobType());

				if (StringUtils.isNumeric(data.getAge())) {
					pi.setAge(new Integer(data.getAge()));
				} else {
					if (StringUtils.isNotBlank(data.getAge())) {
						throw new RuntimeException("Age should be numeric");
					}
				}

				pi.setEmail(data.getEmail());
				if (data.getGender().equalsIgnoreCase("Male")) {
					pi.setGender(Gender.M);
				} else if (data.getGender().equalsIgnoreCase("Female")) {
					pi.setGender(Gender.F);
				} else if (data.getGender().equalsIgnoreCase("Transgender")) {
					pi.setGender(Gender.T);
				} else {
					pi.setGender(null);
				}
				pi.setPhone(data.getPhoneNo());
				demo.setPi(pi);
			}

			if (!data.getFullAddress().isEmpty()
					|| (data.getLocalFullAddress() != null && !data
							.getLocalFullAddress().isEmpty())) {
				Pfa pfa = new Pfa();
				pfa.setMs(data.getFullAddressMatchStrategy());
				pfa.setMv(data.getFullAddressMatchValue());
				pfa.setAv(data.getFullAddress());

				if (!data.getLocalFullAddress().isEmpty()) {
					pfa.setLav(data.getLocalFullAddress());
					pfa.setLmv(data.getLocalFullAddressMatchValue());
				}

				demo.setPfa(pfa);
			}
			// Add Pa only if one of the constituent attributes have a value
			// specified
			if (data.getCareOf() != null && !data.getCareOf().isEmpty()
					|| data.getDistrict() != null
					&& !data.getDistrict().isEmpty()
					|| data.getBuilding() != null
					&& !data.getBuilding().isEmpty()
					|| data.getLandmark() != null
					&& !data.getLandmark().isEmpty()
					|| data.getLocality() != null
					&& !data.getLocality().isEmpty()
					|| data.getPinCode() != null
					&& !data.getPinCode().isEmpty() || data.getPoName() != null
					&& !data.getPoName().isEmpty()
					|| data.getSubdistrict() != null
					&& !data.getSubdistrict().isEmpty()
					|| data.getState() != null && !data.getState().isEmpty()
					|| data.getStreet() != null && !data.getStreet().isEmpty()
					|| data.getVillage() != null
					&& !data.getVillage().isEmpty()) {
				Pa pa = new Pa();
				pa.setMs(data.getAddressMatchStrategy());
				pa.setCo(data.getCareOf());
				pa.setDist(data.getDistrict());
				pa.setHouse(data.getBuilding());
				pa.setLm(data.getLandmark());
				pa.setLoc(data.getLocality());
				pa.setPc(data.getPinCode());
				pa.setPo(data.getPoName());
				pa.setSubdist(data.getSubdistrict());
				pa.setState(data.getState());
				pa.setStreet(data.getStreet());
				pa.setVtc(data.getVillage());
				demo.setPa(pa);
			}

			pid.setDemo(demo);

			pid.setPv(new Pv());
			pid.getPv().setPin(data.getStaticPin());
			pid.getPv().setOtp(data.getDynamicPin());

			Calendar calendar = GregorianCalendar.getInstance();
			pid.setTs(XMLGregorianCalendarImpl.createDateTime(calendar
					.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
					calendar.get(Calendar.DAY_OF_MONTH), calendar
							.get(Calendar.HOUR_OF_DAY), calendar
							.get(Calendar.MINUTE), calendar
							.get(Calendar.SECOND)));

			if (data.getBiometricTemplate() != null
					&& data.getBiometricTemplate().length > 0) {
				Bio bio = new Bio();
				bio.setType(BioMetricType.valueOf(data.getBiometricType()
						.toUpperCase()));
				bio.setValue(data.getBiometricTemplate());
				bio.setPos(BiometricPosition.valueOf(data
						.getBiometricPosition()));

				Bios bios = new Bios();
				pid.setBios(bios);
				pid.getBios().getBio().add(bio);
			}
		}

		return pid;
	}

	public static Uses createUses(AuthenticationFactors authfactors) {
		Uses uses = new Uses();
		uses.setPi(authfactors.isUsesPi() ? "y" : "n");
		uses.setPa(authfactors.isUsesPa() ? "y" : "n");
		uses.setPin(authfactors.isUsesPin() ? "y" : "n");
		uses.setOtp(authfactors.isUsesOtp() ? "y" : "n");
		uses.setBio(authfactors.isUsesBio() ? "y" : "n");
		uses.setPfa(authfactors.isUsesPfa() ? "y" : "n");

		uses.setBt(StringUtils.join(authfactors.getBiometricTypes(), ","));

		return uses;
	}
}
