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

import in.gov.uidai.auth.aua.helper.DigitalSigner;
import in.gov.uidai.auth.aua.uidaiclient.AuthClient;
import in.gov.uidai.auth.client.biometrics.BiometricIntegrationAPI;
import in.gov.uidai.auth.client.biometrics.CaptureDetails;
import in.gov.uidai.auth.client.biometrics.CaptureHandler;
import in.gov.uidai.auth.device.helper.AUADataCreator;
import in.gov.uidai.auth.device.helper.AuthRequestCreator;
import in.gov.uidai.auth.device.helper.Encrypter;
import in.gov.uidai.auth.device.model.AUAData;
import in.gov.uidai.authentication.common.types._1.AuthResult;
import in.gov.uidai.authentication.uid_auth_request._1.Auth;
import in.gov.uidai.authentication.uid_auth_request._1.Tkn;
import in.gov.uidai.authentication.uid_auth_request_data._1.Locn;
import in.gov.uidai.authentication.uid_auth_request_data._1.MatchingStrategy;
import in.gov.uidai.authentication.uid_auth_request_data._1.Meta;
import in.gov.uidai.authentication.uid_auth_response._1.AuthRes;

import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

public class SampleClientMainFrame extends javax.swing.JFrame {

	private static final long serialVersionUID = -1588439781263939263L;
	private static Map<String, Font> languageToFontMap = new HashMap<String, Font>();
    
    static {
        languageToFontMap.put("English", Font.decode("tahoma-plain-10"));
        languageToFontMap.put("Hindi", Font.decode("mangal-plain-10"));
        languageToFontMap.put("Kannada", Font.decode("tunga-plain-10"));
        languageToFontMap.put("Malayalam", Font.decode("kartika-plain-10"));
    }

    private static Map<String, String> tokenLabelToTokenTypeMap = new HashMap<String, String>();
    static {
        tokenLabelToTokenTypeMap.put("Mobile", "001");
    }

    private byte[] isoFingerPrintFeatureSet;
    private String biometricType;
    
    /**
     * Name of the class that provides biometric integration API implementation.
     */
    private String biometricAPIImplementationClass = "in.gov.uidai.auth.sampleapp.DigitalPersonaImpl";

    /** Creates new form Test */
    public SampleClientMainFrame() throws URISyntaxException {
        initComponents();
        loadPreferences();
        
        if (StringUtils.isBlank(System.getenv("qa")) && StringUtils.isBlank(System.getProperty("qa"))) {
            this.jButton1.setVisible(false);
        } else {
        	this.jButton1.setVisible(true);
        }
        
        this.jLabelAuthRefCode.setVisible(false);
        this.jLabelAuthRefCodeValue.setVisible(false);

        this.jLabelAuthStatusText.setVisible(false);
        this.jLabelUidMandatory.setVisible(false);

        jSpinnerNameMatchValue.setEnabled(false);
        jSpinnerNameMatchValue.setValue(100);
        jSpinnerNameMatchValueLocal.setValue(90);

        jSpinnerPfaMatchValue.setEnabled(false);
        jSpinnerPfaMatchValue.setValue(100);
        jSpinnerPfaMatchValueLocal.setValue(90);
        
        jFormattedTextFieldAADHAAR1.setFocusLostBehavior(JFormattedTextField.COMMIT);

        jTextFieldPincode.setFocusLostBehavior(JFormattedTextField.COMMIT);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonGroupNameMatchStrategy = new javax.swing.ButtonGroup();
        jButtonGroupAddressMatchStrategy = new javax.swing.ButtonGroup();
        jDialogPreferences = new javax.swing.JDialog();
        jPanelPreferences = new javax.swing.JPanel();
        jLabelAUA = new javax.swing.JLabel();
        jTextFieldAua = new javax.swing.JTextField();
        jLabelTerminalID = new javax.swing.JLabel();
        jTextFieldTerminalID = new javax.swing.JTextField();
        jPanelUsesPreferences = new javax.swing.JPanel();
        jCheckBoxPi = new javax.swing.JCheckBox();
        jCheckBoxPfa = new javax.swing.JCheckBox();
        jCheckBoxPin = new javax.swing.JCheckBox();
        jCheckBoxBio = new javax.swing.JCheckBox();
        jCheckBoxPa = new javax.swing.JCheckBox();
        jCheckBoxOtp = new javax.swing.JCheckBox();
        jLabelBt = new javax.swing.JLabel();
        jCheckBoxFMR = new javax.swing.JCheckBox();
        jCheckBoxFIR = new javax.swing.JCheckBox();
        jCheckBoxIIR = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldAuthServerURL = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldPublicKeyFile = new javax.swing.JTextField();
        jButtonPickPublicKeyFile = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jTextFieldLicenseKey = new javax.swing.JTextField();
        jLabelAUA1 = new javax.swing.JLabel();
        jTextFieldServiceAgency = new javax.swing.JTextField();
        jLabelTerminalID1 = new javax.swing.JLabel();
        jTextFieldSignatureFile = new javax.swing.JTextField();
        jButtonPickPublicKeyFile1 = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldSignatureAlias = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jPasswordSignature = new javax.swing.JPasswordField();
        jPanelDeviceDetails = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jTextFieldAPC = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTextFieldFDC = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jTextFieldIDC = new javax.swing.JTextField();
        jPanelLocationDetails = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jTextFieldLongitude = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jTextFieldLattitude = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jTextFieldLocVTCCode = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jTextFieldLocSubdistCode = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jTextFieldLocDistrictCode = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jTextFieldLocStateCode = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jTextFieldLocPinCode = new javax.swing.JTextField();
        jbuttonGroupPfaMatchStrategy = new javax.swing.ButtonGroup();
        jDialogResponseValidationResult = new javax.swing.JDialog();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaResponseValidationResult = new javax.swing.JTextArea();
        jButtonResultValidationCopyToClipboard = new javax.swing.JButton();
        jButtonResultValidationDone = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabelLogo = new javax.swing.JLabel();
        jPanelKYR = new javax.swing.JPanel();
        jLabelAadhaarNumber = new javax.swing.JLabel();
        jPanelIdentificationDetails = new javax.swing.JPanel();
        jFrameIdentificationDetails = new javax.swing.JPanel();
        jLabelName = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jLabelGener = new javax.swing.JLabel();
        jComboGender = new javax.swing.JComboBox();
        jLabelDob = new javax.swing.JLabel();
        jLabelPhone = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jLabelEmail = new javax.swing.JLabel();
        jTextFieldDobYear = new javax.swing.JFormattedTextField();
        jTextFieldDobMonth = new javax.swing.JFormattedTextField();
        jTextFieldDobDay = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldPhone = new javax.swing.JFormattedTextField();
        jLabelAge = new javax.swing.JLabel();
        jTextFieldAge = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jSpinnerNameMatchValue = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        jRadioButtonNameMatchExact = new javax.swing.JRadioButton();
        jRadioButtonNameMatchPartial = new javax.swing.JRadioButton();
        jRadioFuzzyName = new javax.swing.JRadioButton();
        jLabelName1 = new javax.swing.JLabel();
        jTextFieldNameLocal = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jSpinnerNameMatchValueLocal = new javax.swing.JSpinner();
        jLabel21 = new javax.swing.JLabel();
        jComboBoxDOBType = new javax.swing.JComboBox();
        jPanelAddress = new javax.swing.JPanel();
        jFrameAddressDetails = new javax.swing.JPanel();
        jLabelCareof = new javax.swing.JLabel();
        jTextFieldCareOf = new javax.swing.JTextField();
        jLabelBuilding = new javax.swing.JLabel();
        jTextFieldBuilding = new javax.swing.JTextField();
        jLabelLandmark = new javax.swing.JLabel();
        jTextFieldLandmark = new javax.swing.JTextField();
        jLabelStreet = new javax.swing.JLabel();
        jTextFieldStreet = new javax.swing.JTextField();
        jLabelLocality = new javax.swing.JLabel();
        jTextFieldLocality = new javax.swing.JTextField();
        jTextFieldDistrict = new javax.swing.JTextField();
        jLabeDistrict = new javax.swing.JLabel();
        jTextFieldState = new javax.swing.JTextField();
        jLabelState = new javax.swing.JLabel();
        jLabelPincode = new javax.swing.JLabel();
        jTextFieldVtc = new javax.swing.JTextField();
        jLabelLocality1 = new javax.swing.JLabel();
        jTextFieldPincode = new javax.swing.JFormattedTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextFieldPOName = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jTextFieldSubdist = new javax.swing.JTextField();
        jRadioButtonAddressPartialMatch = new javax.swing.JRadioButton();
        jRadioButtonAddressExactMatch = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jSpinnerPaMatchValue = new javax.swing.JSpinner();
        jPanelAuthParameters = new javax.swing.JPanel();
        jLabelPIN = new javax.swing.JLabel();
        jLabelPIN1 = new javax.swing.JLabel();
        jPasswordFieldPIN = new javax.swing.JPasswordField();
        jPasswordFieldOTP = new javax.swing.JPasswordField();
        jLabelProgressIndicator = new javax.swing.JLabel();
        jFormattedTextFieldAADHAAR1 = new javax.swing.JFormattedTextField();
        jPanelAuthStatus = new javax.swing.JPanel();
        jLabelAuthStatus = new javax.swing.JLabel();
        jLabelAuthStatusText = new javax.swing.JLabel();
        jButtonValidateResponse = new javax.swing.JButton();
        jPanelBiometricsOuter = new javax.swing.JPanel();
        jPanelBiometric = new javax.swing.JPanel();
        jLabelBiometric = new javax.swing.JLabel();
        jButtonScan = new javax.swing.JButton();
        jComboBiometricPosition = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jPanelPfa = new javax.swing.JPanel();
        jLabelPfa = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaFullAddressValue = new javax.swing.JTextArea();
        jRadioButtonPfaExactMatch = new javax.swing.JRadioButton();
        jRadioButtonPfaPartialMatch = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSpinnerPfaMatchValue = new javax.swing.JSpinner();
        jRadioAddressFuzzy = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaFullAddressValueLocal = new javax.swing.JTextArea();
        jLabelPfa1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSpinnerPfaMatchValueLocal = new javax.swing.JSpinner();
        jLabelUidMandatory = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLanguageCombo = new javax.swing.JComboBox();
        jLabelBiometricFile = new javax.swing.JLabel();
        jLabelToken = new javax.swing.JLabel();
        jTextFieldToken = new javax.swing.JTextField();
        jComboBoxTokenType = new javax.swing.JComboBox();
        jLabelTokenType = new javax.swing.JLabel();
        jButtonAuthenticate = new javax.swing.JButton();
        jButtonClear = new javax.swing.JButton();
        jLabelAuthRefCodeValue = new javax.swing.JLabel();
        jLabelAuthRefCode = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuOptions = new javax.swing.JMenu();
        jMenuItemPreferences = new javax.swing.JMenuItem();

        jDialogPreferences.setTitle("Preferences");
        jDialogPreferences.setBackground(java.awt.Color.white);
        jDialogPreferences.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        jDialogPreferences.setName("preferencesDialog"); // NOI18N
        jDialogPreferences.setResizable(false);

        jPanelPreferences.setBackground(new java.awt.Color(255, 255, 255));

        jLabelAUA.setText("AUA");

        jTextFieldAua.setText("public");

        jLabelTerminalID.setText("Terminal ID");

        jTextFieldTerminalID.setText("public");

        jPanelUsesPreferences.setBackground(new java.awt.Color(255, 255, 255));
        jPanelUsesPreferences.setBorder(javax.swing.BorderFactory.createTitledBorder("Authentication factors ('Uses' element)"));

        jCheckBoxPi.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxPi.setText("pi  (Personal Identity)");

        jCheckBoxPfa.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxPfa.setText("pfa (Personal Full Address)");

        jCheckBoxPin.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxPin.setText("pin (Resident PIN)");

        jCheckBoxBio.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxBio.setText("bio (Biometrics)");

        jCheckBoxPa.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxPa.setText("pa (Personal Address)");

        jCheckBoxOtp.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxOtp.setText("otp (One time PIN)");

        jLabelBt.setBackground(new java.awt.Color(255, 255, 255));
        jLabelBt.setText("bt (Biometric types)");

        jCheckBoxFMR.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxFMR.setText("FMR (Finger Minutiae)");

        jCheckBoxFIR.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxFIR.setText("Finger Image Record");

        jCheckBoxIIR.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxIIR.setText("IIR (Iris Image Record)");

        javax.swing.GroupLayout jPanelUsesPreferencesLayout = new javax.swing.GroupLayout(jPanelUsesPreferences);
        jPanelUsesPreferences.setLayout(jPanelUsesPreferencesLayout);
        jPanelUsesPreferencesLayout.setHorizontalGroup(
            jPanelUsesPreferencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUsesPreferencesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelUsesPreferencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jCheckBoxOtp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCheckBoxPin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCheckBoxPi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCheckBoxPa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCheckBoxPfa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))
                .addGroup(jPanelUsesPreferencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelUsesPreferencesLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelBt))
                    .addGroup(jPanelUsesPreferencesLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanelUsesPreferencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBoxFIR)
                            .addComponent(jCheckBoxFMR)
                            .addComponent(jCheckBoxIIR)))
                    .addGroup(jPanelUsesPreferencesLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxBio, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanelUsesPreferencesLayout.setVerticalGroup(
            jPanelUsesPreferencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUsesPreferencesLayout.createSequentialGroup()
                .addGroup(jPanelUsesPreferencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelUsesPreferencesLayout.createSequentialGroup()
                        .addGroup(jPanelUsesPreferencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBoxPi)
                            .addComponent(jCheckBoxBio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxPa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBoxPfa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxPin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBoxOtp))
                    .addGroup(jPanelUsesPreferencesLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabelBt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxFMR)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBoxFIR)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxIIR)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel9.setText("Auth Server URL");

        jTextFieldAuthServerURL.setText("http://developer.uidai.gov.in/auth/");

        jLabel13.setText("Public Key");

        jTextFieldPublicKeyFile.setText("pub");

        jButtonPickPublicKeyFile.setText("Browse");
        jButtonPickPublicKeyFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPickPublicKeyFileActionPerformed(evt);
            }
        });

        jLabel18.setText("AUA License Key");

        jLabelAUA1.setText("Sub AUA");

        jLabelTerminalID1.setText("Signature file");

        jTextFieldSignatureFile.setText("public");

        jButtonPickPublicKeyFile1.setText("Browse");
        jButtonPickPublicKeyFile1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPickPublicKeyFile1ActionPerformed(evt);
            }
        });

        jButtonSave.setText("Save");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jLabel5.setText("Signature Key Alias");

        jLabel15.setText("Signature Password");

        jPanelDeviceDetails.setBackground(new java.awt.Color(255, 255, 255));
        jPanelDeviceDetails.setBorder(javax.swing.BorderFactory.createTitledBorder("Device Details"));

        jLabel22.setText("APC");

        jLabel23.setText("FDC");

        jLabel24.setText("IDC");

        javax.swing.GroupLayout jPanelDeviceDetailsLayout = new javax.swing.GroupLayout(jPanelDeviceDetails);
        jPanelDeviceDetails.setLayout(jPanelDeviceDetailsLayout);
        jPanelDeviceDetailsLayout.setHorizontalGroup(
            jPanelDeviceDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDeviceDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldAPC, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldFDC, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldIDC, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(98, Short.MAX_VALUE))
        );
        jPanelDeviceDetailsLayout.setVerticalGroup(
            jPanelDeviceDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDeviceDetailsLayout.createSequentialGroup()
                .addGroup(jPanelDeviceDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldAPC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(jTextFieldFDC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(jTextFieldIDC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelLocationDetails.setBackground(new java.awt.Color(255, 255, 255));
        jPanelLocationDetails.setBorder(javax.swing.BorderFactory.createTitledBorder("Location Details"));

        jLabel25.setText("Longitude");

        jLabel26.setText("Lattitude");

        jLabel27.setText("VTC Code");

        jLabel28.setText("Subdist Code");

        jLabel29.setText("District Code");

        jLabel30.setText("State Code");

        jTextFieldLocStateCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldLocStateCodeActionPerformed(evt);
            }
        });

        jLabel31.setText("Pin Code");

        javax.swing.GroupLayout jPanelLocationDetailsLayout = new javax.swing.GroupLayout(jPanelLocationDetails);
        jPanelLocationDetails.setLayout(jPanelLocationDetailsLayout);
        jPanelLocationDetailsLayout.setHorizontalGroup(
            jPanelLocationDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLocationDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelLocationDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel30)
                    .addComponent(jLabel27)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLocationDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLocationDetailsLayout.createSequentialGroup()
                        .addGroup(jPanelLocationDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextFieldLocStateCode, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldLocVTCCode, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addGroup(jPanelLocationDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel28)
                            .addComponent(jLabel31)))
                    .addComponent(jTextFieldLongitude, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelLocationDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel26)
                    .addGroup(jPanelLocationDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextFieldLocPinCode, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextFieldLocSubdistCode, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelLocationDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelLocationDetailsLayout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldLocDistrictCode, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextFieldLattitude, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelLocationDetailsLayout.setVerticalGroup(
            jPanelLocationDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLocationDetailsLayout.createSequentialGroup()
                .addGroup(jPanelLocationDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jTextFieldLongitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldLattitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addGap(18, 18, 18)
                .addGroup(jPanelLocationDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jTextFieldLocVTCCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldLocSubdistCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28)
                    .addComponent(jTextFieldLocDistrictCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelLocationDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jTextFieldLocStateCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31)
                    .addComponent(jTextFieldLocPinCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelPreferencesLayout = new javax.swing.GroupLayout(jPanelPreferences);
        jPanelPreferences.setLayout(jPanelPreferencesLayout);
        jPanelPreferencesLayout.setHorizontalGroup(
            jPanelPreferencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPreferencesLayout.createSequentialGroup()
                .addGroup(jPanelPreferencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPreferencesLayout.createSequentialGroup()
                        .addGroup(jPanelPreferencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelPreferencesLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel5))
                            .addGroup(jPanelPreferencesLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel9))
                            .addGroup(jPanelPreferencesLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanelPreferencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelAUA)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabelTerminalID)
                                    .addComponent(jLabelTerminalID1))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelPreferencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelPreferencesLayout.createSequentialGroup()
                                .addGroup(jPanelPreferencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextFieldAuthServerURL, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelPreferencesLayout.createSequentialGroup()
                                        .addComponent(jTextFieldAua, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabelAUA1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldServiceAgency))
                                    .addComponent(jTextFieldLicenseKey, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                                    .addComponent(jTextFieldPublicKeyFile, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonPickPublicKeyFile))
                            .addGroup(jPanelPreferencesLayout.createSequentialGroup()
                                .addGroup(jPanelPreferencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPreferencesLayout.createSequentialGroup()
                                        .addComponent(jTextFieldSignatureAlias, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPasswordSignature, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTextFieldTerminalID, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldSignatureFile, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelPreferencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonSave)
                                    .addComponent(jButtonPickPublicKeyFile1))))
                        .addGap(130, 130, 130))
                    .addGroup(jPanelPreferencesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelPreferencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanelUsesPreferences, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelLocationDetails, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelDeviceDetails, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanelPreferencesLayout.setVerticalGroup(
            jPanelPreferencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPreferencesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPreferencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldAuthServerURL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPreferencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldAua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldServiceAgency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAUA)
                    .addComponent(jLabelAUA1))
                .addGap(7, 7, 7)
                .addGroup(jPanelPreferencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldLicenseKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(8, 8, 8)
                .addGroup(jPanelPreferencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPublicKeyFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPickPublicKeyFile)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPreferencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldTerminalID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTerminalID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPreferencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldSignatureFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPickPublicKeyFile1)
                    .addComponent(jLabelTerminalID1))
                .addGap(9, 9, 9)
                .addGroup(jPanelPreferencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jPasswordSignature, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jTextFieldSignatureAlias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelUsesPreferences, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanelDeviceDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelLocationDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonSave)
                .addContainerGap())
        );

        javax.swing.GroupLayout jDialogPreferencesLayout = new javax.swing.GroupLayout(jDialogPreferences.getContentPane());
        jDialogPreferences.getContentPane().setLayout(jDialogPreferencesLayout);
        jDialogPreferencesLayout.setHorizontalGroup(
            jDialogPreferencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPreferences, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
        );
        jDialogPreferencesLayout.setVerticalGroup(
            jDialogPreferencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPreferences, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTextAreaResponseValidationResult.setColumns(20);
        jTextAreaResponseValidationResult.setRows(5);
        jTextAreaResponseValidationResult.setText("Results of Response Validation:\n===============================\n\nAadhaar Hash Validation   : PASS\nDemo XML Hash Validation  : PASS\nUIDAI Encoded Value:\n");
        jScrollPane3.setViewportView(jTextAreaResponseValidationResult);

        jButtonResultValidationCopyToClipboard.setText("Copy to clipoard");
        jButtonResultValidationCopyToClipboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResultValidationCopyToClipboardActionPerformed(evt);
            }
        });

        jButtonResultValidationDone.setText("Done");
        jButtonResultValidationDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResultValidationDoneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialogResponseValidationResultLayout = new javax.swing.GroupLayout(jDialogResponseValidationResult.getContentPane());
        jDialogResponseValidationResult.getContentPane().setLayout(jDialogResponseValidationResultLayout);
        jDialogResponseValidationResultLayout.setHorizontalGroup(
            jDialogResponseValidationResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogResponseValidationResultLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialogResponseValidationResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogResponseValidationResultLayout.createSequentialGroup()
                        .addComponent(jButtonResultValidationCopyToClipboard)
                        .addGap(28, 28, 28)
                        .addComponent(jButtonResultValidationDone)))
                .addContainerGap())
        );
        jDialogResponseValidationResultLayout.setVerticalGroup(
            jDialogResponseValidationResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogResponseValidationResultLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDialogResponseValidationResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonResultValidationDone)
                    .addComponent(jButtonResultValidationCopyToClipboard))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 204));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(180, 233, 251));

        jLabelLogo.setFont(new java.awt.Font("Monospaced", 1, 36));
        jLabelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aadhar-logo.png"))); // NOI18N

        jPanelKYR.setBackground(new java.awt.Color(255, 255, 255));
        jPanelKYR.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "KYR Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(124, 186, 247))); // NOI18N
        jPanelKYR.setToolTipText("");
        jPanelKYR.setName("piFrame"); // NOI18N

        jLabelAadhaarNumber.setText("AADHAAR Number");

        jPanelIdentificationDetails.setBackground(new java.awt.Color(255, 255, 255));
        jPanelIdentificationDetails.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Personal Identity (Pi)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanelIdentificationDetails.setToolTipText("");

        jFrameIdentificationDetails.setBackground(new java.awt.Color(255, 255, 255));
        jFrameIdentificationDetails.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 204, 102), 1, true));

        jLabelName.setText("Name");

        jLabelGener.setText("Gender");

        jComboGender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select gender", "Male", "Female", "Transgender" }));

        jLabelDob.setText("Date of birth");

        jLabelPhone.setText("Phone");

        jLabelEmail.setText("Email");

        try {
            jTextFieldDobYear.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            jTextFieldDobMonth.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            jTextFieldDobDay.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel3.setText("-");

        jLabel4.setText("-");

        jTextFieldPhone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        jLabelAge.setText("Age");

        jTextFieldAge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldAgeActionPerformed(evt);
            }
        });

        jLabel10.setText("Match Value");

        jSpinnerNameMatchValue.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));

        jLabel6.setText("Match Strategy");

        jRadioButtonNameMatchExact.setBackground(new java.awt.Color(255, 255, 255));
        jButtonGroupNameMatchStrategy.add(jRadioButtonNameMatchExact);
        jRadioButtonNameMatchExact.setSelected(true);
        jRadioButtonNameMatchExact.setText("Exact match");
        jRadioButtonNameMatchExact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonNameMatchExactActionPerformed(evt);
            }
        });

        jRadioButtonNameMatchPartial.setBackground(new java.awt.Color(255, 255, 255));
        jButtonGroupNameMatchStrategy.add(jRadioButtonNameMatchPartial);
        jRadioButtonNameMatchPartial.setText("Partial match");
        jRadioButtonNameMatchPartial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonNameMatchPartialActionPerformed(evt);
            }
        });

        jRadioFuzzyName.setBackground(new java.awt.Color(255, 255, 255));
        jButtonGroupNameMatchStrategy.add(jRadioFuzzyName);
        jRadioFuzzyName.setText("Fuzzy match");
        jRadioFuzzyName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioFuzzyNameActionPerformed(evt);
            }
        });

        jLabelName1.setText("Local Name");

        jLabel17.setText("Local Match Value");

        jSpinnerNameMatchValueLocal.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setLabelFor(jComboBoxDOBType);
        jLabel21.setText("DOB type");
        jLabel21.setToolTipText("Date of birth type");

        jComboBoxDOBType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "A", "V", "D" }));
        jComboBoxDOBType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxDOBTypeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jFrameIdentificationDetailsLayout = new javax.swing.GroupLayout(jFrameIdentificationDetails);
        jFrameIdentificationDetails.setLayout(jFrameIdentificationDetailsLayout);
        jFrameIdentificationDetailsLayout.setHorizontalGroup(
            jFrameIdentificationDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrameIdentificationDetailsLayout.createSequentialGroup()
                .addGroup(jFrameIdentificationDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jFrameIdentificationDetailsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jFrameIdentificationDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelName)
                            .addGroup(jFrameIdentificationDetailsLayout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jTextFieldName, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                .addGap(8, 8, 8)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSpinnerNameMatchValue, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jFrameIdentificationDetailsLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButtonNameMatchExact)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonNameMatchPartial)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioFuzzyName))))
                    .addGroup(jFrameIdentificationDetailsLayout.createSequentialGroup()
                        .addGroup(jFrameIdentificationDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jFrameIdentificationDetailsLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabelEmail))
                            .addGroup(jFrameIdentificationDetailsLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabelPhone)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jFrameIdentificationDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jFrameIdentificationDetailsLayout.createSequentialGroup()
                                .addGroup(jFrameIdentificationDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jFrameIdentificationDetailsLayout.createSequentialGroup()
                                        .addGap(108, 108, 108)
                                        .addComponent(jLabelAge)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldAge, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
                                    .addGroup(jFrameIdentificationDetailsLayout.createSequentialGroup()
                                        .addComponent(jTextFieldPhone, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabelDob)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                                .addGroup(jFrameIdentificationDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jFrameIdentificationDetailsLayout.createSequentialGroup()
                                        .addComponent(jTextFieldDobYear, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldDobMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldDobDay, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jFrameIdentificationDetailsLayout.createSequentialGroup()
                                        .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBoxDOBType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jFrameIdentificationDetailsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelGener)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jFrameIdentificationDetailsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelName1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldNameLocal, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerNameMatchValueLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jFrameIdentificationDetailsLayout.setVerticalGroup(
            jFrameIdentificationDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrameIdentificationDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jFrameIdentificationDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelName)
                    .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinnerNameMatchValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jFrameIdentificationDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jRadioButtonNameMatchExact)
                    .addComponent(jRadioButtonNameMatchPartial)
                    .addComponent(jRadioFuzzyName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addGroup(jFrameIdentificationDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelName1)
                    .addComponent(jSpinnerNameMatchValueLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(jTextFieldNameLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jFrameIdentificationDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelGener)
                    .addComponent(jComboGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAge)
                    .addComponent(jTextFieldAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxDOBType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jFrameIdentificationDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPhone)
                    .addComponent(jTextFieldDobYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldDobMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldDobDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabelDob))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jFrameIdentificationDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelEmail))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanelIdentificationDetailsLayout = new javax.swing.GroupLayout(jPanelIdentificationDetails);
        jPanelIdentificationDetails.setLayout(jPanelIdentificationDetailsLayout);
        jPanelIdentificationDetailsLayout.setHorizontalGroup(
            jPanelIdentificationDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelIdentificationDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jFrameIdentificationDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelIdentificationDetailsLayout.setVerticalGroup(
            jPanelIdentificationDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelIdentificationDetailsLayout.createSequentialGroup()
                .addComponent(jFrameIdentificationDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelAddress.setBackground(new java.awt.Color(255, 255, 255));
        jPanelAddress.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Personal Address (Pa)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanelAddress.setToolTipText("");

        jFrameAddressDetails.setBackground(new java.awt.Color(255, 255, 255));
        jFrameAddressDetails.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 204, 102), 1, true));

        jLabelCareof.setText("Care Of");

        jLabelBuilding.setText("Building");

        jLabelLandmark.setText("Landmark");

        jLabelStreet.setText("Street");

        jLabelLocality.setText("Locality");

        jTextFieldLocality.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldLocalityActionPerformed(evt);
            }
        });

        jLabeDistrict.setText("District");

        jLabelState.setText("State");

        jLabelPincode.setText("Pincode");

        jLabelLocality1.setText("Village/Town/City");

        try {
            jTextFieldPincode.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("######")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel19.setText("PO Name");

        jLabel20.setText("Subdist");

        javax.swing.GroupLayout jFrameAddressDetailsLayout = new javax.swing.GroupLayout(jFrameAddressDetails);
        jFrameAddressDetails.setLayout(jFrameAddressDetailsLayout);
        jFrameAddressDetailsLayout.setHorizontalGroup(
            jFrameAddressDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrameAddressDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jFrameAddressDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabeDistrict)
                    .addComponent(jLabelCareof)
                    .addComponent(jLabelLocality1)
                    .addComponent(jLabelLandmark)
                    .addComponent(jLabelLocality))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jFrameAddressDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldDistrict, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                    .addComponent(jTextFieldCareOf, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                    .addComponent(jTextFieldLandmark, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                    .addComponent(jTextFieldLocality, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                    .addComponent(jTextFieldVtc, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
                .addGroup(jFrameAddressDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jFrameAddressDetailsLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jFrameAddressDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelState, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jFrameAddressDetailsLayout.createSequentialGroup()
                                .addGroup(jFrameAddressDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelStreet)
                                    .addComponent(jLabelBuilding)
                                    .addComponent(jLabel19))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jFrameAddressDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldStreet, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jFrameAddressDetailsLayout.createSequentialGroup()
                                .addComponent(jTextFieldState, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelPincode)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldPincode, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextFieldBuilding, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                            .addComponent(jTextFieldPOName, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)))
                    .addGroup(jFrameAddressDetailsLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldSubdist, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jFrameAddressDetailsLayout.setVerticalGroup(
            jFrameAddressDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrameAddressDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jFrameAddressDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCareOf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCareof)
                    .addComponent(jTextFieldBuilding, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelBuilding))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jFrameAddressDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelLandmark)
                    .addComponent(jTextFieldLandmark, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldStreet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelStreet))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jFrameAddressDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelLocality)
                    .addComponent(jTextFieldLocality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(jTextFieldPOName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jFrameAddressDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelLocality1)
                    .addComponent(jTextFieldVtc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(jTextFieldSubdist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jFrameAddressDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPincode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPincode)
                    .addComponent(jTextFieldState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelState)
                    .addComponent(jLabeDistrict)
                    .addComponent(jTextFieldDistrict, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jRadioButtonAddressPartialMatch.setBackground(new java.awt.Color(255, 255, 255));
        jButtonGroupAddressMatchStrategy.add(jRadioButtonAddressPartialMatch);
        jRadioButtonAddressPartialMatch.setText("Partial match (not supported for Pa)");
        jRadioButtonAddressPartialMatch.setEnabled(false);

        jRadioButtonAddressExactMatch.setBackground(new java.awt.Color(255, 255, 255));
        jButtonGroupAddressMatchStrategy.add(jRadioButtonAddressExactMatch);
        jRadioButtonAddressExactMatch.setSelected(true);
        jRadioButtonAddressExactMatch.setText("Exact match");

        jLabel7.setText("Match Strategy");

        jLabel11.setText("Match Value");

        jLabel12.setText("(not supported for Pa)");
        jLabel12.setEnabled(false);

        jSpinnerPaMatchValue.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));
        jSpinnerPaMatchValue.setEnabled(false);

        javax.swing.GroupLayout jPanelAddressLayout = new javax.swing.GroupLayout(jPanelAddress);
        jPanelAddress.setLayout(jPanelAddressLayout);
        jPanelAddressLayout.setHorizontalGroup(
            jPanelAddressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAddressLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAddressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jFrameAddressDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelAddressLayout.createSequentialGroup()
                        .addGroup(jPanelAddressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel11))
                        .addGap(34, 34, 34)
                        .addGroup(jPanelAddressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelAddressLayout.createSequentialGroup()
                                .addComponent(jRadioButtonAddressExactMatch)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButtonAddressPartialMatch))
                            .addGroup(jPanelAddressLayout.createSequentialGroup()
                                .addComponent(jSpinnerPaMatchValue, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel12)))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanelAddressLayout.setVerticalGroup(
            jPanelAddressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAddressLayout.createSequentialGroup()
                .addComponent(jFrameAddressDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelAddressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonAddressPartialMatch)
                    .addComponent(jRadioButtonAddressExactMatch)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanelAddressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jSpinnerPaMatchValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanelAuthParameters.setBackground(new java.awt.Color(255, 255, 255));
        jPanelAuthParameters.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PIN values (Pv)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabelPIN.setText("PIN");

        jLabelPIN1.setText("OTP");

        javax.swing.GroupLayout jPanelAuthParametersLayout = new javax.swing.GroupLayout(jPanelAuthParameters);
        jPanelAuthParameters.setLayout(jPanelAuthParametersLayout);
        jPanelAuthParametersLayout.setHorizontalGroup(
            jPanelAuthParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAuthParametersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelPIN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPasswordFieldPIN, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jLabelPIN1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPasswordFieldOTP, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelAuthParametersLayout.setVerticalGroup(
            jPanelAuthParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAuthParametersLayout.createSequentialGroup()
                .addGroup(jPanelAuthParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordFieldPIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordFieldOTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPIN1)
                    .addComponent(jLabelPIN))
                .addContainerGap())
        );

        try {
            jFormattedTextFieldAADHAAR1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("############")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldAADHAAR1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldAADHAAR1ActionPerformed(evt);
            }
        });

        jPanelAuthStatus.setBackground(new java.awt.Color(255, 255, 255));
        jPanelAuthStatus.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Authentication Status", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanelAuthStatus.setPreferredSize(new java.awt.Dimension(100, 100));

        jLabelAuthStatus.setBackground(new java.awt.Color(255, 153, 51));
        jLabelAuthStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAuthStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unknown.png"))); // NOI18N

        jLabelAuthStatusText.setText("Auth Status Here....");
        jLabelAuthStatusText.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jButtonValidateResponse.setText("Validate Response");
        jButtonValidateResponse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonValidateResponseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelAuthStatusLayout = new javax.swing.GroupLayout(jPanelAuthStatus);
        jPanelAuthStatus.setLayout(jPanelAuthStatusLayout);
        jPanelAuthStatusLayout.setHorizontalGroup(
            jPanelAuthStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAuthStatusLayout.createSequentialGroup()
                .addGroup(jPanelAuthStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAuthStatusLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jLabelAuthStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelAuthStatusLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelAuthStatusText, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAuthStatusLayout.createSequentialGroup()
                .addContainerGap(165, Short.MAX_VALUE)
                .addComponent(jButtonValidateResponse, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelAuthStatusLayout.setVerticalGroup(
            jPanelAuthStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAuthStatusLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabelAuthStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelAuthStatusText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jButtonValidateResponse))
        );

        jPanelBiometricsOuter.setBackground(new java.awt.Color(255, 255, 255));
        jPanelBiometricsOuter.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Biometrics (Bios)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanelBiometricsOuter.setToolTipText("Scan to capture finger minutiae");

        jPanelBiometric.setBackground(new java.awt.Color(255, 255, 204));
        jPanelBiometric.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 102)));

        javax.swing.GroupLayout jPanelBiometricLayout = new javax.swing.GroupLayout(jPanelBiometric);
        jPanelBiometric.setLayout(jPanelBiometricLayout);
        jPanelBiometricLayout.setHorizontalGroup(
            jPanelBiometricLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelBiometric, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanelBiometricLayout.setVerticalGroup(
            jPanelBiometricLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelBiometric, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jButtonScan.setText("Scan");
        jButtonScan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonScanActionPerformed(evt);
            }
        });

        jComboBiometricPosition.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "UNKNOWN", "LEFT_IRIS", "RIGHT_IRIS", "BOTH_IRIS", "LEFT_INDEX", "LEFT_LITTLE", "LEFT_MIDDLE", "LEFT_RING", "LEFT_THUMB", "RIGHT_INDEX", "RIGHT_LITTLE", "RIGHT_MIDDLE", "RIGHT_RING", "RIGHT_THUMB", "BOTH_THUMBS", "LEFT_SLAP", "RIGHT_SLAP" }));

        jButton1.setText("Browse");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBiometricsOuterLayout = new javax.swing.GroupLayout(jPanelBiometricsOuter);
        jPanelBiometricsOuter.setLayout(jPanelBiometricsOuterLayout);
        jPanelBiometricsOuterLayout.setHorizontalGroup(
            jPanelBiometricsOuterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBiometricsOuterLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelBiometricsOuterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jComboBiometricPosition, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelBiometric, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelBiometricsOuterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButtonScan))
                .addContainerGap())
        );
        jPanelBiometricsOuterLayout.setVerticalGroup(
            jPanelBiometricsOuterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBiometricsOuterLayout.createSequentialGroup()
                .addComponent(jComboBiometricPosition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelBiometricsOuterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBiometricsOuterLayout.createSequentialGroup()
                        .addComponent(jButtonScan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addComponent(jPanelBiometric, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        jPanelPfa.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPfa.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Personal Full Address (Pfa)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabelPfa.setText("Address value (av)");

        jTextAreaFullAddressValue.setColumns(20);
        jTextAreaFullAddressValue.setFont(new java.awt.Font("Tahoma", 0, 11));
        jTextAreaFullAddressValue.setLineWrap(true);
        jTextAreaFullAddressValue.setRows(5);
        jTextAreaFullAddressValue.setWrapStyleWord(true);
        jTextAreaFullAddressValue.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane1.setViewportView(jTextAreaFullAddressValue);

        jRadioButtonPfaExactMatch.setBackground(new java.awt.Color(255, 255, 255));
        jbuttonGroupPfaMatchStrategy.add(jRadioButtonPfaExactMatch);
        jRadioButtonPfaExactMatch.setSelected(true);
        jRadioButtonPfaExactMatch.setText("Exact Match");
        jRadioButtonPfaExactMatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonPfaExactMatchActionPerformed(evt);
            }
        });

        jRadioButtonPfaPartialMatch.setBackground(new java.awt.Color(255, 255, 255));
        jbuttonGroupPfaMatchStrategy.add(jRadioButtonPfaPartialMatch);
        jRadioButtonPfaPartialMatch.setText("Partial Match");
        jRadioButtonPfaPartialMatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonPfaPartialMatchActionPerformed(evt);
            }
        });

        jLabel8.setText("Match Strategy (ms)");

        jLabel1.setText("Match Value (mv)");

        jSpinnerPfaMatchValue.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));

        jRadioAddressFuzzy.setBackground(new java.awt.Color(255, 255, 255));
        jbuttonGroupPfaMatchStrategy.add(jRadioAddressFuzzy);
        jRadioAddressFuzzy.setText("Fuzzy match");
        jRadioAddressFuzzy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioAddressFuzzyActionPerformed(evt);
            }
        });

        jTextAreaFullAddressValueLocal.setColumns(20);
        jTextAreaFullAddressValueLocal.setFont(new java.awt.Font("Tahoma", 0, 11));
        jTextAreaFullAddressValueLocal.setLineWrap(true);
        jTextAreaFullAddressValueLocal.setRows(5);
        jTextAreaFullAddressValueLocal.setWrapStyleWord(true);
        jTextAreaFullAddressValueLocal.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane2.setViewportView(jTextAreaFullAddressValueLocal);

        jLabelPfa1.setText("Local Address value (lav)");

        jLabel14.setText("Local Match Value (lmv)");

        jSpinnerPfaMatchValueLocal.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));

        javax.swing.GroupLayout jPanelPfaLayout = new javax.swing.GroupLayout(jPanelPfa);
        jPanelPfa.setLayout(jPanelPfaLayout);
        jPanelPfaLayout.setHorizontalGroup(
            jPanelPfaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPfaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPfaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                    .addGroup(jPanelPfaLayout.createSequentialGroup()
                        .addComponent(jRadioButtonPfaExactMatch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButtonPfaPartialMatch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioAddressFuzzy))
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelPfaLayout.createSequentialGroup()
                        .addComponent(jLabelPfa)
                        .addGap(52, 52, 52)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerPfaMatchValue, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelPfaLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerPfaMatchValueLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelPfa1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelPfaLayout.setVerticalGroup(
            jPanelPfaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPfaLayout.createSequentialGroup()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPfaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonPfaExactMatch)
                    .addComponent(jRadioAddressFuzzy)
                    .addComponent(jRadioButtonPfaPartialMatch))
                .addGap(3, 3, 3)
                .addGroup(jPanelPfaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelPfaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jSpinnerPfaMatchValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelPfa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPfaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jSpinnerPfaMatchValueLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelPfa1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabelUidMandatory.setForeground(new java.awt.Color(255, 0, 0));
        jLabelUidMandatory.setText("This field is required");

        jLabel16.setText("Language");

        jLanguageCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "Hindi", "Kannada", "Malayalam" }));
        jLanguageCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLanguageComboActionPerformed(evt);
            }
        });

        jLabelBiometricFile.setText(".");

        jLabelToken.setText("Token");

        jComboBoxTokenType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mobile" }));

        jLabelTokenType.setText("Token Type");

        javax.swing.GroupLayout jPanelKYRLayout = new javax.swing.GroupLayout(jPanelKYR);
        jPanelKYR.setLayout(jPanelKYRLayout);
        jPanelKYRLayout.setHorizontalGroup(
            jPanelKYRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelKYRLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelKYRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelKYRLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabelBiometricFile, javax.swing.GroupLayout.PREFERRED_SIZE, 719, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelKYRLayout.createSequentialGroup()
                        .addGroup(jPanelKYRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelKYRLayout.createSequentialGroup()
                                .addComponent(jPanelAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanelKYRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelKYRLayout.createSequentialGroup()
                                        .addComponent(jLabelProgressIndicator, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(25, 25, 25))
                                    .addComponent(jPanelAuthStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelKYRLayout.createSequentialGroup()
                                .addGroup(jPanelKYRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelKYRLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabelAadhaarNumber)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jFormattedTextFieldAADHAAR1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabelUidMandatory))
                                    .addComponent(jPanelIdentificationDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanelKYRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanelBiometricsOuter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanelAuthParameters, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelKYRLayout.createSequentialGroup()
                                        .addComponent(jLabelToken)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextFieldToken)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabelTokenType)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanelKYRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanelKYRLayout.createSequentialGroup()
                                        .addComponent(jComboBoxTokenType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLanguageCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanelPfa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(11, 11, 11)))
                .addContainerGap())
        );
        jPanelKYRLayout.setVerticalGroup(
            jPanelKYRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelKYRLayout.createSequentialGroup()
                .addGroup(jPanelKYRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAadhaarNumber)
                    .addComponent(jFormattedTextFieldAADHAAR1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelUidMandatory)
                    .addComponent(jLanguageCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jComboBoxTokenType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTokenType)
                    .addComponent(jTextFieldToken, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelToken))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelKYRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelPfa, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelKYRLayout.createSequentialGroup()
                        .addComponent(jPanelAuthParameters, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelBiometricsOuter, 0, 148, Short.MAX_VALUE))
                    .addComponent(jPanelIdentificationDetails, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelKYRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelAuthStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                    .addComponent(jPanelAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelBiometricFile)
                .addGap(341, 341, 341)
                .addComponent(jLabelProgressIndicator))
        );

        jButtonAuthenticate.setText("Authenticate");
        jButtonAuthenticate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAuthenticateActionPerformed(evt);
            }
        });

        jButtonClear.setText("Reset");
        jButtonClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearActionPerformed(evt);
            }
        });

        jLabelAuthRefCodeValue.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabelAuthRefCodeValue.setText("auth ref code value");

        jLabelAuthRefCode.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabelAuthRefCode.setText("Authentication Reference Code");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("UID Authentication Demo Client  (For API 1.5)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabelLogo)
                .addGap(399, 399, 399)
                .addComponent(jLabel2))
            .addComponent(jPanelKYR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonClear)
                .addGap(27, 27, 27)
                .addComponent(jLabelAuthRefCode)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelAuthRefCodeValue, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                .addComponent(jButtonAuthenticate)
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelLogo)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelKYR, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonClear)
                    .addComponent(jLabelAuthRefCode)
                    .addComponent(jLabelAuthRefCodeValue)
                    .addComponent(jButtonAuthenticate, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenuFile.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem1.setText("Exit");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exit(evt);
            }
        });
        jMenuFile.add(jMenuItem1);

        jMenuBar.add(jMenuFile);

        jMenuOptions.setText("Edit");
        jMenuOptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuOptionsActionPerformed(evt);
            }
        });

        jMenuItemPreferences.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK));
        jMenuItemPreferences.setText("Preferences");
        jMenuItemPreferences.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editPreferences(evt);
            }
        });
        jMenuOptions.add(jMenuItemPreferences);

        jMenuBar.add(jMenuOptions);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAuthenticateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAuthenticateActionPerformed
        if (jFormattedTextFieldAADHAAR1.getText().trim().isEmpty()) {
            jLabelUidMandatory.setVisible(true);
        } else {
            jLabelUidMandatory.setVisible(false);
            this.authenticateRequest(constructAuthRequest());
        }
    }//GEN-LAST:event_jButtonAuthenticateActionPerformed

    private void jButtonScanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonScanActionPerformed
    	
    	BiometricIntegrationAPI biometricIntegrationAPI;
		try {
			biometricIntegrationAPI = (BiometricIntegrationAPI) Class.forName(biometricAPIImplementationClass).newInstance();
	    	biometricIntegrationAPI.captureBiometrics(new CaptureHandlerImpl(this));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Biometric capture implementation not found.\n\n" +
					"Please ensure that an implementation of in.gov.uidai.auth.biometric.BiometricIntegrationAPI is \n" +
					"present in classpath, and biometricAPIImplementationClass field of this application is initialized\n" +
					"with name of that class.", "UID Authentication Demo Client", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
    }//GEN-LAST:event_jButtonScanActionPerformed

    private void jButtonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearActionPerformed
        jLabelAuthStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unknown.png")));

        jLabelBiometricFile.setText("..");

        this.jLabelAuthRefCode.setVisible(false);
        this.jLabelAuthRefCodeValue.setVisible(false);

        this.jLabelUidMandatory.setVisible(false);

        jFormattedTextFieldAADHAAR1.setText(null);

        jTextFieldNameLocal.setText(null);
        jTextAreaFullAddressValueLocal.setText(null);
        jTextAreaFullAddressValue.setText(null);

        jPasswordFieldOTP.setText(null);
        jPasswordFieldPIN.setText(null);
        jTextFieldBuilding.setText(null);
        jTextFieldCareOf.setText(null);
        jTextFieldDistrict.setText(null);
        jTextFieldDobDay.setText(null);
        jTextFieldDobMonth.setText(null);
        jTextFieldDobYear.setText(null);
        jTextFieldEmail.setText(null);
        jTextFieldLandmark.setText(null);
        jTextFieldLocality.setText(null);
        jTextFieldName.setText(null);
        jTextFieldPhone.setText(null);
        jTextFieldAge.setText(null);
        jTextFieldPOName.setText(null);
        jTextFieldSubdist.setText(null);
        jTextFieldPincode.setText(null);
        jTextFieldState.setText(null);
        jTextFieldStreet.setText(null);
        jTextFieldVtc.setText(null);
        jTextFieldToken.setText(null);
        jComboGender.setSelectedIndex(0);
        resetFingerprintISOFeatureSet();
        jLabelBiometricFile.setText(".");
        
        jTextAreaResponseValidationResult.setText("No auth response to validate");
        
    }//GEN-LAST:event_jButtonClearActionPerformed

    private void resetFingerprintISOFeatureSet() {
    	this.isoFingerPrintFeatureSet = null;
    	jLabelBiometric.setIcon(null);
    }

    private void loadPreferences() {
        FileInputStream is = null;
        try {
            File preferencesFile = new File("authclient.properties");
            if (preferencesFile.exists()) {
                is = new FileInputStream(preferencesFile);
                Properties p = new Properties();
                p.load(is);

                if (p.get("authServerUrl") != null) {
                    jTextFieldAuthServerURL.setText(p.get("authServerUrl").toString());
                }
                if (p.get("auaCode") != null) {
                    jTextFieldAua.setText(p.get("auaCode").toString());
                }
                if (p.get("signKeyStore") != null) {
                	jTextFieldSignatureFile.setText(p.get("signKeyStore").toString());
                }
                
                if (p.get("sa") != null) {
                    jTextFieldServiceAgency.setText(p.get("sa").toString());
                }

                if (p.get("licenseKey") != null) {
                    jTextFieldLicenseKey.setText(p.get("licenseKey").toString());
                }
                
                if (p.get("terminalId") != null) {
                    jTextFieldTerminalID.setText(p.get("terminalId").toString());
                }

                if (p.get("publicKeyFile") != null) {
                    jTextFieldPublicKeyFile.setText(p.get("publicKeyFile").toString());
                }

                if (p.get("usesPi") != null) {
                    jCheckBoxPi.setSelected(Boolean.valueOf(p.get("usesPi").toString()));
                }

                if (p.get("usesPa") != null) {
                    jCheckBoxPa.setSelected(Boolean.valueOf(p.get("usesPa").toString()));
                }

                if (p.get("usesPfa") != null) {
                    jCheckBoxPfa.setSelected(Boolean.valueOf(p.get("usesPfa").toString()));
                }

                if (p.get("usesPin") != null) {
                    jCheckBoxPin.setSelected(Boolean.valueOf(p.get("usesPin").toString()));
                }

                if (p.get("usesOtp") != null) {
                    jCheckBoxOtp.setSelected(Boolean.valueOf(p.get("usesOtp").toString()));
                }

                if (p.get("usesBio") != null) {
                    jCheckBoxBio.setSelected(Boolean.valueOf(p.get("usesBio").toString()));
                }

                if (p.get("usesBioFMR") != null) {
                    jCheckBoxFMR.setSelected(Boolean.valueOf(p.get("usesBioFMR").toString()));
                }

                if (p.get("usesBioFIR") != null) {
                    jCheckBoxFIR.setSelected(Boolean.valueOf(p.get("usesBioFIR").toString()));
                }

                if (p.get("usesBioIIR") != null) {
                    jCheckBoxIIR.setSelected(Boolean.valueOf(p.get("usesBioIIR").toString()));
                }

                if (p.get("signatureAlias") != null) {
                    jTextFieldSignatureAlias.setText(p.get("signatureAlias").toString());
                }

                if (p.get("signaturePassword") != null) {
                    jPasswordSignature.setText(p.get("signaturePassword").toString());
                }


                if (p.get("apc") != null) {
                    jTextFieldAPC.setText(p.get("apc").toString());
                }

                if (p.get("fdc") != null) {
                    jTextFieldFDC.setText(p.get("fdc").toString());
                }

                if (p.get("idc") != null) {
                    jTextFieldIDC.setText(p.get("idc").toString());
                }

                if (p.get("longitude") != null) {
                    jTextFieldLongitude.setText(p.get("longitude").toString());
                }

                if (p.get("lattitude") != null) {
                    jTextFieldLattitude.setText(p.get("lattitude").toString());
                }

                if (p.get("vtc") != null) {
                    jTextFieldLocVTCCode.setText(p.get("vtc").toString());
                }

                if (p.get("sudist") != null) {
                    jTextFieldLocSubdistCode.setText(p.get("sudist").toString());
                }

                if (p.get("dist") != null) {
                    jTextFieldLocDistrictCode.setText(p.get("dist").toString());
                }

                if (p.get("state") != null) {
                    jTextFieldLocStateCode.setText(p.get("state").toString());
                }

                if (p.get("pincode") != null) {
                    jTextFieldLocPinCode.setText(p.get("pincode").toString());
                }
                
            } 
            
        
        } catch (IOException ex) {
            Logger.getLogger(SampleClientMainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(SampleClientMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     
    private void editPreferences(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editPreferences

        loadPreferences();
        
        jDialogPreferences.setBounds(100, 100, 582, 635);
        jDialogPreferences.setResizable(true);
        jDialogPreferences.setVisible(true);
    }//GEN-LAST:event_editPreferences

    private void exit(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exit
        System.exit(0);
    }//GEN-LAST:event_exit

    private void storePreferences() {
        FileOutputStream of = null;
        FileInputStream is = null;
        try {
            jDialogPreferences.setVisible(false);
            
            Properties p = new Properties();

            File preferencesFile = new File("authclient.properties");
            if (preferencesFile.exists()) {
                is = new FileInputStream(preferencesFile);
                p.load(is);
            }
            is.close();
            is = null;
            
            p.put("authServerUrl", jTextFieldAuthServerURL.getText());
            p.put("auaCode", jTextFieldAua.getText());
           
            p.put("sa", jTextFieldServiceAgency.getText());
            p.put("licenseKey", jTextFieldLicenseKey.getText());
            p.put("terminalId", jTextFieldTerminalID.getText());
            p.put("publicKeyFile", jTextFieldPublicKeyFile.getText());

            p.put("usesPi", String.valueOf(jCheckBoxPi.isSelected()));
            p.put("usesPa", String.valueOf(jCheckBoxPa.isSelected()));
            p.put("usesPfa", String.valueOf(jCheckBoxPfa.isSelected()));
            p.put("usesPin", String.valueOf(jCheckBoxPin.isSelected()));
            p.put("usesOtp", String.valueOf(jCheckBoxOtp.isSelected()));
            p.put("usesBio", String.valueOf(jCheckBoxBio.isSelected()));
            p.put("usesBioFMR", String.valueOf(jCheckBoxFMR.isSelected()));
            p.put("usesBioFIR", String.valueOf(jCheckBoxFIR.isSelected()));
            p.put("usesBioIIR", String.valueOf(jCheckBoxIIR.isSelected()));

            
            boolean signatureAttributeChanged = false;
            if (StringUtils.isNotBlank(jTextFieldSignatureFile.getText()) && !jTextFieldSignatureFile.getText().equals(p.get("signKeyStore").toString())) {
            	signatureAttributeChanged = true;
            }
            p.put("signKeyStore", jTextFieldSignatureFile.getText());
            
            if (StringUtils.isNotBlank(jTextFieldSignatureAlias.getText()) && !jTextFieldSignatureAlias.getText().equals(p.get("signatureAlias").toString())) {
            	signatureAttributeChanged = true;
            }
            p.put("signatureAlias", jTextFieldSignatureAlias.getText());
            
            if (StringUtils.isNotBlank(jPasswordSignature.getText()) && !jPasswordSignature.getText().equals(p.get("signaturePassword").toString())) {
            	signatureAttributeChanged = true;
            }
            p.put("signaturePassword", new String(jPasswordSignature.getPassword()));

            p.put("apc", jTextFieldAPC.getText());
            p.put("fdc", jTextFieldFDC.getText());
            p.put("idc", jTextFieldIDC.getText());

            p.put("longitude", jTextFieldLongitude.getText());
            p.put("lattitude", jTextFieldLattitude.getText());
            p.put("vtc", jTextFieldLocVTCCode.getText());
            p.put("sudist", jTextFieldLocSubdistCode.getText());
            p.put("dist", jTextFieldLocDistrictCode.getText());
            p.put("state", jTextFieldLocStateCode.getText());
            p.put("pincode", jTextFieldLocPinCode.getText());

            File f = new File("authclient.properties");
            of = new FileOutputStream(f);
            p.store(of, "Auth client preferences");
            
            if (signatureAttributeChanged) {
            	JOptionPane.showMessageDialog(this, "Signature related attributes changed. \nPlease RESTART the auth client for it to take effect.", "UID Authentication Demo Client", JOptionPane.ERROR_MESSAGE);
            }

        } catch (IOException ex) {
            Logger.getLogger(SampleClientMainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            	
                if (of != null) {
                    of.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(SampleClientMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        storePreferences();
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonPickPublicKeyFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPickPublicKeyFileActionPerformed
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(this.jDialogPreferences);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            this.jTextFieldPublicKeyFile.setText(file.getAbsolutePath());
        } 

    }//GEN-LAST:event_jButtonPickPublicKeyFileActionPerformed

    private void jRadioButtonNameMatchExactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonNameMatchExactActionPerformed
        jSpinnerNameMatchValue.setEnabled(false);
        jSpinnerNameMatchValue.setValue(100);
    }//GEN-LAST:event_jRadioButtonNameMatchExactActionPerformed

    private void jRadioButtonNameMatchPartialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonNameMatchPartialActionPerformed
        jSpinnerNameMatchValue.setEnabled(true);
        jSpinnerNameMatchValue.setValue(1);
    }//GEN-LAST:event_jRadioButtonNameMatchPartialActionPerformed

    private void jRadioButtonPfaExactMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonPfaExactMatchActionPerformed
        jSpinnerPfaMatchValue.setEnabled(false);
        jSpinnerPfaMatchValue.setValue(100);
    }//GEN-LAST:event_jRadioButtonPfaExactMatchActionPerformed

    private void jRadioButtonPfaPartialMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonPfaPartialMatchActionPerformed
        jSpinnerPfaMatchValue.setEnabled(true);
        jSpinnerPfaMatchValue.setValue(75);
    }//GEN-LAST:event_jRadioButtonPfaPartialMatchActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        File preferencesFile = new File("authclient.properties");
        if (!preferencesFile.exists()) {
            JOptionPane.showMessageDialog(this, "Default preferences are being used.\nEdit your preferences and and save it by using menu option Edit->Preferences", "UID Authentication Demo Client", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_formWindowOpened

    private void jTextFieldAgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldAgeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldAgeActionPerformed

    private void jRadioFuzzyNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioFuzzyNameActionPerformed
        jSpinnerNameMatchValue.setEnabled(true);
    }//GEN-LAST:event_jRadioFuzzyNameActionPerformed

    private void jRadioAddressFuzzyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioAddressFuzzyActionPerformed
        jSpinnerPfaMatchValue.setEnabled(true);
    }//GEN-LAST:event_jRadioAddressFuzzyActionPerformed

    private void jLanguageComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLanguageComboActionPerformed
        String language = "English";
        if (this.jLanguageCombo.getSelectedIndex() > 0) {
            language = this.jLanguageCombo.getSelectedItem().toString();
        }
        Font f = languageToFontMap.get(language);
        this.jTextAreaFullAddressValueLocal.setFont(f);
        this.jTextFieldNameLocal.setFont(f);
    }//GEN-LAST:event_jLanguageComboActionPerformed

    private void jTextFieldLocalityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldLocalityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldLocalityActionPerformed

    private void jButtonPickPublicKeyFile1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPickPublicKeyFile1ActionPerformed
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(this.jDialogPreferences);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            this.jTextFieldSignatureFile.setText(file.getAbsolutePath());
        }
    }//GEN-LAST:event_jButtonPickPublicKeyFile1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser fc = new JFileChooser();

        FileFilter filter = new FileNameExtensionFilter("Biometric Image Record (.iir, .fir)", "iir", "fir");
        fc.addChoosableFileFilter(filter);

        int returnVal = fc.showOpenDialog(this.jPanel1);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            System.out.println(file.getAbsolutePath());
            try {
                String fileName = file.getAbsolutePath();
                File f = new File(fileName);
                String fileExtension = fileName.substring(fileName.length() - 3, fileName.length());
                this.biometricType = fileExtension.toUpperCase();
                this.isoFingerPrintFeatureSet = FileUtils.readFileToByteArray(f);
                jLabelBiometricFile.setText("This auth is using " + this.biometricType + " biometrics from file: " + file.getAbsolutePath());
            } catch (IOException ex) {
                Logger.getLogger(SampleClientMainFrame.class.getName()).log(Level.SEVERE, null, ex);
                jLabelBiometricFile.setText("ERROR: unable to read the specified file!!");
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jFormattedTextFieldAADHAAR1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldAADHAAR1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldAADHAAR1ActionPerformed

    private void jComboBoxDOBTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxDOBTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxDOBTypeActionPerformed

    private void jButtonValidateResponseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonValidateResponseActionPerformed
        // TODO add your handling code here:

        jDialogResponseValidationResult.setBounds(100, 100, 859, 477);
        jDialogResponseValidationResult.setVisible(true);
        jTextAreaResponseValidationResult.scrollRectToVisible(new Rectangle(0, 0, 0, 0));
        
    }//GEN-LAST:event_jButtonValidateResponseActionPerformed

    private void jButtonResultValidationCopyToClipboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResultValidationCopyToClipboardActionPerformed
        jTextAreaResponseValidationResult.selectAll();
        jTextAreaResponseValidationResult.copy();
    }//GEN-LAST:event_jButtonResultValidationCopyToClipboardActionPerformed

    private void jButtonResultValidationDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResultValidationDoneActionPerformed
       jDialogResponseValidationResult.setVisible(false);
    }//GEN-LAST:event_jButtonResultValidationDoneActionPerformed

    private void jTextFieldLocStateCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldLocStateCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldLocStateCodeActionPerformed

    private void jMenuOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuOptionsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuOptionsActionPerformed

    private AuthenticationRequestData constructAuthRequest() {
        AuthenticationRequestData request = new AuthenticationRequestData();

        String uid = jFormattedTextFieldAADHAAR1.getText();
        request.setUid(uid);

        request.setLanguage(jLanguageCombo.getSelectedItem().toString());

        String name = jTextFieldName.getText().trim();
        if ((name != null) && (name.length() > 0)) {
            request.setName(name);
        }
        String lname = jTextFieldNameLocal.getText().trim();
        if ((lname != null) && (lname.length() > 0)) {
            request.setLname(lname);
        }

        String pinCode = jTextFieldPincode.getText().trim();
        if ((pinCode != null) && (pinCode.length() > 0)) {
            request.setPinCode(pinCode);
        }
        String careOf = jTextFieldCareOf.getText().trim();
        if ((careOf != null) && (careOf.length() > 0)) {
            request.setCareOf(careOf);
        }
        String building = jTextFieldBuilding.getText().trim();
        if ((building != null) && (building.length() > 0)) {
            request.setBuilding(building);
        }
        String street = jTextFieldStreet.getText().trim();
        if ((street != null) && (street.length() > 0)) {
            request.setStreet(street);
        }
        String landmark = jTextFieldLandmark.getText().trim();
        if ((landmark != null) && (landmark.length() > 0)) {
            request.setLandmark(landmark);
        }
        String locality = jTextFieldLocality.getText().trim();
        if ((locality != null) && (locality.length() > 0)) {
            request.setLocality(locality);
        }
        String village = jTextFieldVtc.getText().trim(); //******
        if ((village != null) && (village.length() > 0)) {
            request.setVillage(village);
        }

        String poName = jTextFieldPOName.getText().trim();
        if ((poName != null) && (poName.length() > 0)) {
            request.setPoName(poName);
        }

        String subdistrict = jTextFieldSubdist.getText().trim();
        if ((subdistrict != null) && (subdistrict.length() > 0)) {
            request.setSubdistrict(subdistrict);
        }

        String district = jTextFieldDistrict.getText().trim();
        if ((district != null) && (district.length() > 0)) {
            request.setDistrict(district);
        }
        String state = (String) jTextFieldState.getText().trim();
        if ((state != null) && (state.trim().length() > 0)) {
            request.setState(state);
        }
        String phoneNo = jTextFieldPhone.getText().trim();
        if ((phoneNo != null) && (phoneNo.length() > 0)) {
            request.setPhoneNo(phoneNo);
        }
        String email = jTextFieldEmail.getText().trim();
        if ((email != null) && (email.length() > 0)) {
            request.setEmail(email);
        }
        String staticPin = (new String(jPasswordFieldPIN.getPassword())).trim();
        if ((staticPin != null) && (staticPin.length() > 0)) {
            request.setStaticPin(staticPin);
        }
        String dynamicPin = (new String(jPasswordFieldOTP.getPassword())).trim();
        if ((dynamicPin != null) && (dynamicPin.length() > 0)) {
            request.setDynamicPin(dynamicPin);
        }

        // Assemble gender
        request.setGender((String) jComboGender.getSelectedItem());

        // Assemble DOB
        String day = jTextFieldDobDay.getText().trim();
        String month = jTextFieldDobMonth.getText().trim();
        String year = jTextFieldDobYear.getText().trim();
        String dob = null;
        if ((year != null) && (year.length() > 0) && (month != null)
                && (month.length() > 0) && (day != null) && (day.length() > 0)) {
            dob = year + "-" + month + "-" + day;
        } else if ((year != null) && (year.length() > 0) && (month != null)
                && (month.length() > 0)) {
            dob = year + "-" + month + "-" + "";
        } else if ((year != null) && (year.length() > 0) && (day != null) && (day.length() > 0)) {
            dob = year + "-" + "" + "-" + day;
        } else if ((month != null) && (month.length() > 0) && (day != null) && (day.length() > 0)) {
            dob = "" + "-" + month + "-" + day;
        } else if ((month != null) && (month.length() > 0)) {
            dob = "" + "-" + month + "-" + "";
        } else if ((day != null) && (day.length() > 0)) {
            dob = "" + "-" + "" + "-" + day;
        } else if ((year != null) && (year.length() > 0)) {
            dob = year;
        }

        request.setDob(dob);
        
        if (!"Select".equalsIgnoreCase(jComboBoxDOBType.getSelectedItem().toString())) {
            request.setDobType(jComboBoxDOBType.getSelectedItem().toString());
        }

        if (StringUtils.isNotBlank(this.jTextFieldAge.getText())) {
            request.setAge(jTextFieldAge.getText());
        }
        request.setNameMatchValue((Integer)jSpinnerNameMatchValue.getValue());
        request.setLocalNameMatchValue((Integer)jSpinnerNameMatchValueLocal.getValue());
        
        if (this.isoFingerPrintFeatureSet != null && this.isoFingerPrintFeatureSet.length > 0) {
            request.setBiometricTemplate(this.isoFingerPrintFeatureSet);
            request.setBiometricType(this.biometricType);
            request.setBiometricPosition((String)jComboBiometricPosition.getSelectedItem());
        }

        request.setFullAddress(this.jTextAreaFullAddressValue.getText());
        request.setLocalFullAddress(this.jTextAreaFullAddressValueLocal.getText());
        request.setFullAddressMatchValue((Integer)jSpinnerPfaMatchValue.getValue());
        request.setLocalFullAddressMatchValue((Integer)jSpinnerPfaMatchValueLocal.getValue());

        //Name match strategy
        if (jRadioButtonNameMatchExact.isSelected()) {
        	request.setNameMatchStrategy(MatchingStrategy.E);
        } else {
        	if (jRadioButtonNameMatchPartial.isSelected()) {
        		request.setNameMatchStrategy(MatchingStrategy.P);
        	} else {
        		request.setNameMatchStrategy(MatchingStrategy.F);
        	}
        }

        //Pa match strategy
        request.setAddressMatchStrategy(jRadioButtonAddressExactMatch.isSelected() ? MatchingStrategy.E : MatchingStrategy.P);
        
        //Pfa match strategy
        if (jRadioButtonPfaExactMatch.isSelected()) {
        	request.setFullAddressMatchStrategy(MatchingStrategy.E);
        } else {
        	if (jRadioButtonPfaPartialMatch.isSelected()) {
        		request.setFullAddressMatchStrategy(MatchingStrategy.P);
        	} else {
        		request.setFullAddressMatchStrategy(MatchingStrategy.F);
        	}
        }

        return request;

    }

    private boolean authenticateRequest(AuthenticationRequestData authData) {
    	
    	boolean authSuccessful = false;
    	
        try {

            jLabelAuthStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/unknown.png")));
            this.repaint();

            try {
                new URL(this.jTextFieldAuthServerURL.getText()).openConnection().connect();
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Server not reachable.\nVerify the URL in Edit -> Preferences", "UID Authentication Demo Client", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            if (!(new File(this.jTextFieldPublicKeyFile.getText())).exists()) {
                JOptionPane.showMessageDialog(this, "Public key file not found.\nVerify the file path in Edit -> Preferences", "UID Authentication Demo Client", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (!(new File(this.jTextFieldSignatureFile.getText())).exists()) {
                JOptionPane.showMessageDialog(this, "Signature file not found.\nVerify the file path in Edit -> Preferences", "UID Authentication Demo Client", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            AuthenticationFactors authfactors = new AuthenticationFactors();
            authfactors.setUsesPi(jCheckBoxPi.isSelected());
            authfactors.setUsesPa(jCheckBoxPa.isSelected());
            authfactors.setUsesPin(jCheckBoxPin.isSelected());
            authfactors.setUsesOtp(jCheckBoxOtp.isSelected());
            authfactors.setUsesBio(jCheckBoxBio.isSelected());
            authfactors.setUsesPfa(jCheckBoxPfa.isSelected());

            List<String> bioTypes = new ArrayList<String>();
            if (jCheckBoxFMR.isSelected()) {
                bioTypes.add("FMR");
            }
            if (jCheckBoxFIR.isSelected()) {
                bioTypes.add("FIR");
            }
            if (jCheckBoxIIR.isSelected()) {
                bioTypes.add("IIR");
            }
            authfactors.setBiometricTypes(bioTypes);

            Meta metaData = createMetaData();

            AUADataCreator auaDataCreator = new AUADataCreator(new Encrypter(this.jTextFieldPublicKeyFile.getText()));
            AUAData auaData = auaDataCreator.prepareAUAData(authData.getUid(), this.jTextFieldTerminalID.getText(), AuthRequestHelper.createPid(metaData, authData));
            
            Tkn token = null;
            if (StringUtils.isNotBlank(this.jTextFieldToken.toString())) {
                token = new Tkn();
                token.setNum(this.jTextFieldToken.getText());
                token.setType(tokenLabelToTokenTypeMap.get((String)this.jComboBoxTokenType.getSelectedItem()));
            }
            
            AuthRequestCreator authRequestCreator = new AuthRequestCreator();
            Auth auth = authRequestCreator.createAuthRequest(
            		this.jTextFieldAua.getText(),
            		this.jTextFieldServiceAgency.getText(),
            		this.jTextFieldLicenseKey.getText(), 
            		AuthRequestHelper.createUses(authfactors),
            		token,
            		auaData);
           
            AuthClient client = new AuthClient(new URL(this.jTextFieldAuthServerURL.getText()).toURI());
            client.setDigitalSignator(new DigitalSigner(this.jTextFieldSignatureFile.getText(), this.jPasswordSignature.getPassword(), this.jTextFieldSignatureAlias.getText()));
            
            AuthRes authResult = client.authenticate(auth);
            
            if (authResult != null) {
                if (authResult.getRet().equals(AuthResult.Y)) {
                    this.jLabelAuthStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/success.png")));
                    this.jLabelAuthRefCodeValue.setText(authResult.getCode());

                    this.jLabelAuthRefCode.setVisible(true);
                    this.jLabelAuthRefCodeValue.setVisible(true);
                    this.jLabelAuthStatusText.setVisible(false);

                    
                    this.jLabelAuthStatusText.setText("");
                    authSuccessful = true;
                } else {
                    this.jLabelAuthStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/failure.png")));

                    this.jLabelAuthStatusText.setText("Error code: " + authResult.getErr() + " (" + ErrorCode.getDescription(authResult.getErr()) + ")");
                    this.jLabelAuthStatusText.setVisible(true);

                    this.jLabelAuthRefCodeValue.setText(authResult.getCode());
                    this.jLabelAuthRefCode.setVisible(true);
                    this.jLabelAuthRefCodeValue.setVisible(true);
                }
            }

            fillAuthResponseValidationText(auth, authResult, auaData.getHashedDemoXML());
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "UID Authentication Demo Client", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return authSuccessful;

    }

    private void fillAuthResponseValidationText(Auth auth, AuthRes authResult, byte[] hashedDemoXML) {
       this.jTextAreaResponseValidationResult.setText(AuthResponseValidator.validateAuthResponse(auth, authResult, hashedDemoXML).toString());
    }

    public void drawFingerprintImage(Image image) {
            jLabelBiometric.setIcon(new ImageIcon(image.getScaledInstance(jLabelBiometric
				.getWidth(), jLabelBiometric.getHeight(), Image.SCALE_DEFAULT)));
    }

    public void setFingerprintISOFeatureSet(byte[] isoFeatureSet) {
        this.isoFingerPrintFeatureSet = isoFeatureSet;
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            // Set System L&F
            UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName());

        } catch (Exception e) {
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    new SampleClientMainFrame().setVisible(true);

                } catch (URISyntaxException ex) {
                    Logger.getLogger(SampleClientMainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAuthenticate;
    private javax.swing.JButton jButtonClear;
    private javax.swing.ButtonGroup jButtonGroupAddressMatchStrategy;
    private javax.swing.ButtonGroup jButtonGroupNameMatchStrategy;
    private javax.swing.JButton jButtonPickPublicKeyFile;
    private javax.swing.JButton jButtonPickPublicKeyFile1;
    private javax.swing.JButton jButtonResultValidationCopyToClipboard;
    private javax.swing.JButton jButtonResultValidationDone;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonScan;
    private javax.swing.JButton jButtonValidateResponse;
    private javax.swing.JCheckBox jCheckBoxBio;
    private javax.swing.JCheckBox jCheckBoxFIR;
    private javax.swing.JCheckBox jCheckBoxFMR;
    private javax.swing.JCheckBox jCheckBoxIIR;
    private javax.swing.JCheckBox jCheckBoxOtp;
    private javax.swing.JCheckBox jCheckBoxPa;
    private javax.swing.JCheckBox jCheckBoxPfa;
    private javax.swing.JCheckBox jCheckBoxPi;
    private javax.swing.JCheckBox jCheckBoxPin;
    private javax.swing.JComboBox jComboBiometricPosition;
    private javax.swing.JComboBox jComboBoxDOBType;
    private javax.swing.JComboBox jComboBoxTokenType;
    private javax.swing.JComboBox jComboGender;
    private javax.swing.JDialog jDialogPreferences;
    private javax.swing.JDialog jDialogResponseValidationResult;
    private javax.swing.JFormattedTextField jFormattedTextFieldAADHAAR1;
    private javax.swing.JPanel jFrameAddressDetails;
    private javax.swing.JPanel jFrameIdentificationDetails;
    private javax.swing.JLabel jLabeDistrict;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAUA;
    private javax.swing.JLabel jLabelAUA1;
    private javax.swing.JLabel jLabelAadhaarNumber;
    private javax.swing.JLabel jLabelAge;
    private javax.swing.JLabel jLabelAuthRefCode;
    private javax.swing.JLabel jLabelAuthRefCodeValue;
    private javax.swing.JLabel jLabelAuthStatus;
    private javax.swing.JLabel jLabelAuthStatusText;
    private javax.swing.JLabel jLabelBiometric;
    private javax.swing.JLabel jLabelBiometricFile;
    private javax.swing.JLabel jLabelBt;
    private javax.swing.JLabel jLabelBuilding;
    private javax.swing.JLabel jLabelCareof;
    private javax.swing.JLabel jLabelDob;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelGener;
    private javax.swing.JLabel jLabelLandmark;
    private javax.swing.JLabel jLabelLocality;
    private javax.swing.JLabel jLabelLocality1;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelName1;
    private javax.swing.JLabel jLabelPIN;
    private javax.swing.JLabel jLabelPIN1;
    private javax.swing.JLabel jLabelPfa;
    private javax.swing.JLabel jLabelPfa1;
    private javax.swing.JLabel jLabelPhone;
    private javax.swing.JLabel jLabelPincode;
    private javax.swing.JLabel jLabelProgressIndicator;
    private javax.swing.JLabel jLabelState;
    private javax.swing.JLabel jLabelStreet;
    private javax.swing.JLabel jLabelTerminalID;
    private javax.swing.JLabel jLabelTerminalID1;
    private javax.swing.JLabel jLabelToken;
    private javax.swing.JLabel jLabelTokenType;
    private javax.swing.JLabel jLabelUidMandatory;
    private javax.swing.JComboBox jLanguageCombo;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItemPreferences;
    private javax.swing.JMenu jMenuOptions;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelAddress;
    private javax.swing.JPanel jPanelAuthParameters;
    private javax.swing.JPanel jPanelAuthStatus;
    private javax.swing.JPanel jPanelBiometric;
    private javax.swing.JPanel jPanelBiometricsOuter;
    private javax.swing.JPanel jPanelDeviceDetails;
    private javax.swing.JPanel jPanelIdentificationDetails;
    private javax.swing.JPanel jPanelKYR;
    private javax.swing.JPanel jPanelLocationDetails;
    private javax.swing.JPanel jPanelPfa;
    private javax.swing.JPanel jPanelPreferences;
    private javax.swing.JPanel jPanelUsesPreferences;
    private javax.swing.JPasswordField jPasswordFieldOTP;
    private javax.swing.JPasswordField jPasswordFieldPIN;
    private javax.swing.JPasswordField jPasswordSignature;
    private javax.swing.JRadioButton jRadioAddressFuzzy;
    private javax.swing.JRadioButton jRadioButtonAddressExactMatch;
    private javax.swing.JRadioButton jRadioButtonAddressPartialMatch;
    private javax.swing.JRadioButton jRadioButtonNameMatchExact;
    private javax.swing.JRadioButton jRadioButtonNameMatchPartial;
    private javax.swing.JRadioButton jRadioButtonPfaExactMatch;
    private javax.swing.JRadioButton jRadioButtonPfaPartialMatch;
    private javax.swing.JRadioButton jRadioFuzzyName;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSpinner jSpinnerNameMatchValue;
    private javax.swing.JSpinner jSpinnerNameMatchValueLocal;
    private javax.swing.JSpinner jSpinnerPaMatchValue;
    private javax.swing.JSpinner jSpinnerPfaMatchValue;
    private javax.swing.JSpinner jSpinnerPfaMatchValueLocal;
    private javax.swing.JTextArea jTextAreaFullAddressValue;
    private javax.swing.JTextArea jTextAreaFullAddressValueLocal;
    private javax.swing.JTextArea jTextAreaResponseValidationResult;
    private javax.swing.JTextField jTextFieldAPC;
    private javax.swing.JTextField jTextFieldAge;
    private javax.swing.JTextField jTextFieldAua;
    private javax.swing.JTextField jTextFieldAuthServerURL;
    private javax.swing.JTextField jTextFieldBuilding;
    private javax.swing.JTextField jTextFieldCareOf;
    private javax.swing.JTextField jTextFieldDistrict;
    private javax.swing.JFormattedTextField jTextFieldDobDay;
    private javax.swing.JFormattedTextField jTextFieldDobMonth;
    private javax.swing.JFormattedTextField jTextFieldDobYear;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldFDC;
    private javax.swing.JTextField jTextFieldIDC;
    private javax.swing.JTextField jTextFieldLandmark;
    private javax.swing.JTextField jTextFieldLattitude;
    private javax.swing.JTextField jTextFieldLicenseKey;
    private javax.swing.JTextField jTextFieldLocDistrictCode;
    private javax.swing.JTextField jTextFieldLocPinCode;
    private javax.swing.JTextField jTextFieldLocStateCode;
    private javax.swing.JTextField jTextFieldLocSubdistCode;
    private javax.swing.JTextField jTextFieldLocVTCCode;
    private javax.swing.JTextField jTextFieldLocality;
    private javax.swing.JTextField jTextFieldLongitude;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldNameLocal;
    private javax.swing.JTextField jTextFieldPOName;
    private javax.swing.JFormattedTextField jTextFieldPhone;
    private javax.swing.JFormattedTextField jTextFieldPincode;
    private javax.swing.JTextField jTextFieldPublicKeyFile;
    private javax.swing.JTextField jTextFieldServiceAgency;
    private javax.swing.JTextField jTextFieldSignatureAlias;
    private javax.swing.JTextField jTextFieldSignatureFile;
    private javax.swing.JTextField jTextFieldState;
    private javax.swing.JTextField jTextFieldStreet;
    private javax.swing.JTextField jTextFieldSubdist;
    private javax.swing.JTextField jTextFieldTerminalID;
    private javax.swing.JTextField jTextFieldToken;
    private javax.swing.JTextField jTextFieldVtc;
    private javax.swing.ButtonGroup jbuttonGroupPfaMatchStrategy;
    // End of variables declaration//GEN-END:variables

    private Meta createMetaData() {
        Meta m = new Meta();
        if (StringUtils.isNotBlank(this.jTextFieldAPC.getText())) {
            m.setApc(this.jTextFieldAPC.getText());
        }
        if (StringUtils.isNotBlank(this.jTextFieldFDC.getText())) {
            m.setFdc(this.jTextFieldFDC.getText());
        }
        if (StringUtils.isNotBlank(this.jTextFieldIDC.getText())) {
            m.setIdc(this.jTextFieldIDC.getText());
        }
        
        Locn l = new Locn();
        if (StringUtils.isNotBlank(this.jTextFieldLongitude.getText())) {
            l.setLng(this.jTextFieldLongitude.getText());
        }
        if (StringUtils.isNotBlank(this.jTextFieldLattitude.getText())) {
            l.setLat(this.jTextFieldLattitude.getText());
        }
        if (StringUtils.isNotBlank(this.jTextFieldLocVTCCode.getText())) {
            l.setVtc(this.jTextFieldLocVTCCode.getText());
        }
        if (StringUtils.isNotBlank(this.jTextFieldLocSubdistCode.getText())) {
            l.setSubdist(this.jTextFieldLocSubdistCode.getText());
        }
        if (StringUtils.isNotBlank(this.jTextFieldLocDistrictCode.getText())) {
            l.setDist(this.jTextFieldLocDistrictCode.getText());
        }
        if (StringUtils.isNotBlank(this.jTextFieldLocStateCode.getText())) {
            l.setState(this.jTextFieldLocStateCode.getText());
        }
        if (StringUtils.isNotBlank(this.jTextFieldLocPinCode.getText())) {
            l.setPc(this.jTextFieldLocPinCode.getText());
        }
        
        m.setLocn(l);
        return m;
    }
    // End of variables declaration

    public static class PinVerifier extends InputVerifier {

        public static final int PIN_MAX_LENGTH = 6;

        @Override
        public boolean verify(JComponent input) {
            JPasswordField password = (JPasswordField) input;
            return StringUtils.isNumeric(new String(password.getPassword())) && password.getPassword().length == PIN_MAX_LENGTH;
        }
    }
    
    public static class CaptureHandlerImpl implements CaptureHandler {
    	
    	private SampleClientMainFrame mainFrame;

		public CaptureHandlerImpl(SampleClientMainFrame mainFrame) {
    		this.mainFrame = mainFrame;
		}
    	
    	@Override
    	public void onCapture(CaptureDetails details) {
    		this.mainFrame.setFingerprintISOFeatureSet(details.getIsoFeatureSet());
    		this.mainFrame.drawFingerprintImage(details.getImage());
    		this.mainFrame.biometricType = "FMR";
    	}
    }

}
