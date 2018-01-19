package frames;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import security.SaltedMD5;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


public class frmInternalKrijoLlogari extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textPerdoruesiRi;
	private JTextField txtEmriRi;
	private JTextField txtMbiemriRi;
	private JTextField txtNrTelRi;
	private JTextField txtEmailiRi;
	private JTextField txtAdresaRe;
	private JPasswordField txtFjalekalimiLlogarise;
	private JPasswordField txtFjalekalimi;
	private JPasswordField txtKonfirmoFjalekalimin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmInternalKrijoLlogari frame = new frmInternalKrijoLlogari();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frmInternalKrijoLlogari() {
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				
			}
		});
		setClosable(true);
		setFrameIcon(new ImageIcon(frmInternalKrijoLlogari.class.getResource("/images/user-icon.png")));
		
		if (Gjuha.gjuha == "albanian") {
			setTitle("Shto Nj\u00EB Llogari T\u00EB Re");
		} else {
			setTitle("Add a new Account");
		}
		setBounds(100, 100, 666, 480);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Te Dhenat Autentifikuese", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(20, 27, 262, 125);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblPerdoruesi = new JLabel();
		if (Gjuha.gjuha == "albanian") {
			lblPerdoruesi.setText("Perdoruesi");
		} else {
			lblPerdoruesi.setText("User");
		}
		lblPerdoruesi.setBounds(10, 24, 122, 20);
		panel.add(lblPerdoruesi);
		lblPerdoruesi.setFont(new Font("Courier New", Font.PLAIN, 13));
		
		textPerdoruesiRi = new JTextField();
		textPerdoruesiRi.setBounds(142, 24, 105, 20);
		panel.add(textPerdoruesiRi);
		textPerdoruesiRi.setColumns(10);
		
		JLabel lblFjalekalimi = new JLabel();
		if (Gjuha.gjuha == "albanian") {
			lblFjalekalimi.setText("Fjalekalimi");
		} else {
			lblFjalekalimi.setText("Password");
		}
		lblFjalekalimi.setBounds(10, 55, 122, 20);
		panel.add(lblFjalekalimi);
		lblFjalekalimi.setFont(new Font("Courier New", Font.PLAIN, 13));
		
		txtFjalekalimi = new JPasswordField();
		txtFjalekalimi.setBounds(142, 55, 105, 20);
		panel.add(txtFjalekalimi);
		
		JLabel lblKonfirmoFjalk = new JLabel();
			if (Gjuha.gjuha == "albanian") {
			lblKonfirmoFjalk.setText("<html><p>Konfirmo</p><p>Fjalekalimin:</p></html> ");
		} else {
			lblKonfirmoFjalk.setText("<html><p>Confirm</p><p>Password:</p></html> ");
		}
		lblKonfirmoFjalk.setVerticalAlignment(SwingConstants.TOP);
		lblKonfirmoFjalk.setHorizontalAlignment(SwingConstants.LEFT);
		lblKonfirmoFjalk.setFont(new Font("Courier New", Font.PLAIN, 12));
		lblKonfirmoFjalk.setBounds(10, 78, 105, 28);
		panel.add(lblKonfirmoFjalk);
		
		txtKonfirmoFjalekalimin = new JPasswordField();
		txtKonfirmoFjalekalimin.setBounds(142, 86, 105, 20);
		panel.add(txtKonfirmoFjalekalimin);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Te Dhenat Personale", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(322, 27, 292, 189);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblEmri = new JLabel();
		if (Gjuha.gjuha == "albanian") {
			lblEmri.setText("Emri*:");
		} else {
			lblEmri.setText("Name");
		}
		lblEmri.setBounds(20, 23, 122, 20);
		panel_1.add(lblEmri);
		lblEmri.setFont(new Font("Courier New", Font.PLAIN, 12));
		
		txtEmriRi = new JTextField();
		txtEmriRi.setBounds(164, 23, 105, 20);
		panel_1.add(txtEmriRi);
		txtEmriRi.setColumns(10);
		
		JLabel lblMbiemri = new JLabel("Mbiemri*:");
		if (Gjuha.gjuha == "albanian") {
			lblMbiemri.setText("Mbiemri*:");
		} else {
			lblMbiemri.setText("Surname");
		}
		lblMbiemri.setBounds(20, 54, 122, 20);
		panel_1.add(lblMbiemri);
		lblMbiemri.setFont(new Font("Courier New", Font.PLAIN, 12));
		
		txtMbiemriRi = new JTextField();
		txtMbiemriRi.setBounds(164, 54, 105, 20);
		panel_1.add(txtMbiemriRi);
		txtMbiemriRi.setColumns(10);
		
		JLabel lblNumriTelefonit = new JLabel();
		if (Gjuha.gjuha == "albanian") {
			lblNumriTelefonit.setText("Numri Telefonit*:");
		} else {
			lblNumriTelefonit.setText("Phone number");
		}
		lblNumriTelefonit.setBounds(20, 85, 134, 20);
		panel_1.add(lblNumriTelefonit);
		lblNumriTelefonit.setFont(new Font("Courier New", Font.PLAIN, 12));
		
		txtNrTelRi = new JTextField();
		txtNrTelRi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vchar = e.getKeyChar();
				
				if (!(Character.isDigit(vchar))) {
					e.consume();
				}
			}
		});
		txtNrTelRi.setBounds(164, 85, 105, 20);
		panel_1.add(txtNrTelRi);
		txtNrTelRi.setColumns(10);
		
		JLabel lblEmaili = new JLabel();
		if (Gjuha.gjuha == "albanian") {
			lblEmaili.setText("Emaili");
		} else {
			lblEmaili.setText("Email");
		}
		lblEmaili.setBounds(20, 116, 122, 20);
		panel_1.add(lblEmaili);
		lblEmaili.setFont(new Font("Courier New", Font.PLAIN, 12));
		
		txtEmailiRi = new JTextField();
		txtEmailiRi.setBounds(164, 116, 105, 20);
		panel_1.add(txtEmailiRi);
		txtEmailiRi.setColumns(10);
		
		JLabel lblAdresa = new JLabel();
		if (Gjuha.gjuha == "albanian") {
			lblAdresa.setText("Adresa:");
		} else {
			lblAdresa.setText("Adress");
		}
		lblAdresa.setBounds(20, 147, 122, 20);
		panel_1.add(lblAdresa);
		lblAdresa.setFont(new Font("Courier New", Font.PLAIN, 12));
		
		txtAdresaRe = new JTextField();
		txtAdresaRe.setBounds(164, 147, 105, 20);
		panel_1.add(txtAdresaRe);
		txtAdresaRe.setColumns(10);
		
		JLabel lblInfo = new JLabel("");
		lblInfo.setForeground(Color.RED);
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setBounds(322, 334, 292, 14);
		getContentPane().add(lblInfo);
		
		JButton btnKrijoLlogarine = new JButton();
		if (Gjuha.gjuha == "albanian") {
			btnKrijoLlogarine.setText("KRIJO LLOGARIN\u00CB");
		} else {
			btnKrijoLlogarine.setText("Create an account");
		}
		btnKrijoLlogarine.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				boolean error = false;
				
				String perdoruesiRi = textPerdoruesiRi.getText(), fjalekalimiRi=txtFjalekalimi.getText(), konfirmoFjalekalimin = txtKonfirmoFjalekalimin.getText(),  emriRi = txtEmriRi.getText(), mbiemriRi = txtMbiemriRi.getText(), nrTelRi = txtNrTelRi.getText(), emailiRi = txtEmailiRi.getText(), adresaRe = txtAdresaRe.getText();
				
				String salti = SaltedMD5.generateSalt();
				String sql = "INSERT INTO llogarite (perdoruesi, fjalekalimi, salti, eshteAktiv, emri, mbiemri, numriTelefonit, emaili, adresa) "
						+ "VALUES ('" + perdoruesiRi + "', '"+ SaltedMD5.getSecurePassword(txtFjalekalimi.getText(), Base64.getDecoder().decode(salti)) +"', '"+ salti + "', 'po', '" + emriRi + "', '" + mbiemriRi + "', '" + nrTelRi +"', '" + emailiRi + "', '" + adresaRe + "')";
				
				if ( perdoruesiRi.isEmpty() ) {
					lblPerdoruesi.setForeground(Color.RED);
				} else {
					lblPerdoruesi.setForeground(Color.BLACK);
				}
				
				if ( emriRi.isEmpty() ) {
					lblEmri.setForeground(Color.RED);
				} else {
					lblEmri.setForeground(Color.BLACK);
				}
				
				if ( mbiemriRi.isEmpty() ) {
					lblMbiemri.setForeground(Color.RED);
				} else {
					lblMbiemri.setForeground(Color.BLACK);
				} 
				
				if ( nrTelRi.isEmpty() ) {
					lblNumriTelefonit.setForeground(Color.RED);
				} else {
					lblNumriTelefonit.setForeground(Color.BLACK);
				}
				
				if ( !fjalekalimiRi.equals(konfirmoFjalekalimin) ) {
					lblFjalekalimi.setForeground(Color.RED);
					lblKonfirmoFjalk.setForeground(Color.RED);
				} else {
					lblFjalekalimi.setForeground(Color.BLACK);
					lblKonfirmoFjalk.setForeground(Color.BLACK);
				} 
				
				if ( fjalekalimiRi.equals("") || konfirmoFjalekalimin.equals("") ) {
					lblFjalekalimi.setForeground(Color.RED);
					lblKonfirmoFjalk.setForeground(Color.RED);
				} else {
					lblFjalekalimi.setForeground(Color.BLACK);
					lblKonfirmoFjalk.setForeground(Color.BLACK);
				} 
				
				if ( !emailiRi.equals("") ) {
					String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
					Pattern pattern = Pattern.compile(emailPattern);
					Matcher regMatcher = pattern.matcher(txtEmailiRi.getText());
					
					error = regMatcher.matches();
					
					if (!error) {
						lblEmaili.setForeground(Color.RED);
					} else {
						lblEmaili.setForeground(Color.BLACK);
					}
				} else {
					error = true;
				}
				
				if (error) {
					if( SaltedMD5.getSecurePassword(txtFjalekalimiLlogarise.getText(), Base64.getDecoder().decode(Database.salt)).equals(Database.fjalekalimiDB) ) {
						try {
							Database.executeQueryDB(sql);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						lblInfo.setText("Llogaria �sht� shtuar me sukses!");
						textPerdoruesiRi.setText("");
						txtFjalekalimi.setText("");
						txtKonfirmoFjalekalimin.setText("");
						txtEmriRi.setText("");
						txtMbiemriRi.setText("");
						txtNrTelRi.setText("");
						txtEmailiRi.setText("");
						txtAdresaRe.setText("");
						txtFjalekalimiLlogarise.setText("");
						
					} else {
						lblInfo.setText("Fjalekalimi i llogarise eshte shenuar gabim!");
					}
				} else {
					lblInfo.setText("Ju lutem plotesoni te gjitha fushat me te kuqe!");
				}
			}
		});
		btnKrijoLlogarine.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnKrijoLlogarine.setBounds(376, 363, 184, 43);
		getContentPane().add(btnKrijoLlogarine);
		
		JLabel photoLogin = new JLabel("");
		photoLogin.setIcon(new ImageIcon(frmInternalKrijoLlogari.class.getResource("/images/Add-User-icon.png")));
		photoLogin.setBounds(20, 169, 281, 274);
		getContentPane().add(photoLogin);
		
		JLabel lblShkruaniFjalekaliminE = new JLabel();
		if (Gjuha.gjuha == "albanian") {
			lblShkruaniFjalekaliminE.setText("Shkruani fjalekalimin e llogaris\u00EB tuaj:");
		} else {
			lblShkruaniFjalekaliminE.setText("Enter your account password:");
		}
		lblShkruaniFjalekaliminE.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblShkruaniFjalekaliminE.setBounds(370, 265, 197, 27);
		getContentPane().add(lblShkruaniFjalekaliminE);
		
		txtFjalekalimiLlogarise = new JPasswordField();
		txtFjalekalimiLlogarise.setBounds(416, 303, 105, 20);
		getContentPane().add(txtFjalekalimiLlogarise);
	}
}
