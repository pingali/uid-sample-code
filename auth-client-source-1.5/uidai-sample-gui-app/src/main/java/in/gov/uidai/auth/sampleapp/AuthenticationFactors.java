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

import java.util.List;

/**
 * <code>AuthenticationFactors</code> class represents the factors that are expected to be present
 * in the current authentication request.  It is used for constructing {@link Uses} element of Auth XML.
 * 
 * @author UIDAI
 *
 */
class AuthenticationFactors {
    private boolean usesPi;
    private boolean usesPa;
    private boolean usesPfa;
    private boolean usesPin;
    private boolean usesOtp;
    private boolean usesBio;

    private List<String> biometricTypes;

    public AuthenticationFactors() {
    }

    /**
     * Biometric types expected in auth request. Represents value of "bt" attribute of Uses element 
     * @return
     */
    public List<String> getBiometricTypes() {
        return biometricTypes;
    }

    /**
     * Sets the value of biometric types
     * @param biometricTypes List of biometric types. Allowed values in the list are "FMR", "FIR", and "IIR".
     */
    public void setBiometricTypes(List<String> biometricTypes) {
        this.biometricTypes = biometricTypes;
    }

    /**
     * Flag indicating whether bio element should be present in Pid
     * @return true if yes, false if no
     */
    public boolean isUsesBio() {
        return usesBio;
    }

    public void setUsesBio(boolean usesBio) {
        this.usesBio = usesBio;
    }

    /**
     * Flag indicating whether otp attribute should be used in Pv element of Pid
     * @return true if yes, false if no
     */
    public boolean isUsesOtp() {
        return usesOtp;
    }

    public void setUsesOtp(boolean usesOtp) {
        this.usesOtp = usesOtp;
    }

    /**
     * Flag indicating whether Pa element should be present in Pid
     * @return true if yes, false if no
     */
    public boolean isUsesPa() {
        return usesPa;
    }

    public void setUsesPa(boolean usesPa) {
        this.usesPa = usesPa;
    }

    /**
     * Flag indicating whether Pi element should be present in Pid
     * @return true if yes, false if no
     */
    public boolean isUsesPi() {
        return usesPi;
    }

    public void setUsesPi(boolean usesPi) {
        this.usesPi = usesPi;
    }

    /**
     * Flag indicating whether pin attribute should be present in Pv element should in Pid
     * @return true if yes, false if no
     */

    public boolean isUsesPin() {
        return usesPin;
    }

    public void setUsesPin(boolean usesPin) {
        this.usesPin = usesPin;
    }

    /**
     * Flag indicating whether Pfa element should be present in Pid
     * @return true if yes, false if no
     */
    public boolean isUsesPfa() {
        return usesPfa;
    }

    public void setUsesPfa(boolean usesPfa) {
        this.usesPfa = usesPfa;
    }

}
