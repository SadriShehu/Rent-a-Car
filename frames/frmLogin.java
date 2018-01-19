package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.SwingConstants;

import security.SaltedMD5;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.Base64;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

public class frmLogin {
	
	int numroTentimet = 1;
	
	private JFrame frmProgramiPerRezervimin;
	private JTextField txtPerdoruesi;
	private JPasswordField txtFjalekalimi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmLogin window = new frmLogin();
					window.frmProgramiPerRezervimin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public frmLogin() {
		initialize();
		Gjuha.caktoGjuhen("albanian");
		Database.conn = sqlDBConnector.connectCarDb();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {		
		
		frmProgramiPerRezervimin = new JFrame();
		
		frmProgramiPerRezervimin.setTitle("Programi per rezervimin e veturave me qera");
		frmProgramiPerRezervimin.setIconImage(Toolkit.getDefaultToolkit().getImage(frmLogin.class.getResource("/images/login.png")));
		frmProgramiPerRezervimin.setBounds(100, 100, 640, 480);
		frmProgramiPerRezervimin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmProgramiPerRezervimin.getContentPane().setLayout(null);
		
		JLabel lblMiresevini = new JLabel("Mir\u00EBsevini!");
		lblMiresevini.setHorizontalAlignment(SwingConstants.CENTER);
		lblMiresevini.setFont(new Font("Courier New", Font.BOLD, 25));
		lblMiresevini.setBounds(288, 87, 285, 29);
		frmProgramiPerRezervimin.getContentPane().add(lblMiresevini);
		
		JLabel lblPerdoruesi = new JLabel("P\u00EBrdoruesi:");
		lblPerdoruesi.setFont(new Font("Courier New", Font.PLAIN, 17));
		lblPerdoruesi.setBounds(288, 169, 126, 20);
		frmProgramiPerRezervimin.getContentPane().add(lblPerdoruesi);
		
		txtPerdoruesi = new JTextField();
		txtPerdoruesi.setColumns(10);
		txtPerdoruesi.setBounds(418, 169, 155, 20);
		frmProgramiPerRezervimin.getContentPane().add(txtPerdoruesi);
		
		JLabel lblFjalekalimi = new JLabel("Fjal\u00EBkalimi:");
		lblFjalekalimi.setFont(new Font("Courier New", Font.PLAIN, 17));
		lblFjalekalimi.setBounds(288, 207, 126, 20);
		frmProgramiPerRezervimin.getContentPane().add(lblFjalekalimi);
		
		txtFjalekalimi = new JPasswordField();
		txtFjalekalimi.setBounds(418, 207, 155, 20);
		frmProgramiPerRezervimin.getContentPane().add(txtFjalekalimi);
		
		JLabel lblFotografia = new JLabel("");
		lblFotografia.setIcon(new ImageIcon(frmLogin.class.getResource("/images/security.png")));
		lblFotografia.setBounds(10, 87, 256, 256);
		frmProgramiPerRezervimin.getContentPane().add(lblFotografia);
		
		JLabel lblMesazhi = new JLabel("");
		lblMesazhi.setHorizontalAlignment(SwingConstants.CENTER);
		lblMesazhi.setForeground(Color.RED);
		lblMesazhi.setBounds(285, 236, 288, 20);
		frmProgramiPerRezervimin.getContentPane().add(lblMesazhi);
		
		JButton btnKyqu = new JButton("Kyqu");

		btnKyqu.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				String perdoruesi = txtPerdoruesi.getText();
				Database.searchDB(perdoruesi);
				
				String eshteAktiv = Database.eshteAktiv; 
				
				if ( !txtPerdoruesi.getText().equals("") && !txtFjalekalimi.getText().equals("") ) {
					if ( eshteAktiv.equals("po") ) {
						if ( numroTentimet < 3) {
							try {
								String sql ="select * from llogarite where perdoruesi='"+ txtPerdoruesi.getText() + "' and fjalekalimi='"+ SaltedMD5.getSecurePassword(txtFjalekalimi.getText(), Base64.getDecoder().decode(Database.salt)) +"'";
								Database.executeQueryWithResult(sql);
								//dy rreshtat perdoren per te paraqitur numrin e rezultateve pas ekzekutimit te query-t
								Database.res.last();
								int count = Database.res.getRow();
								Database.pst.close();
								if (count>0) {
									frmProgramiPerRezervimin.dispose();
									frmMain objMain = new frmMain();
									objMain.setVisible(true);
								} else {
									numroTentimet++;
									
									if (Gjuha.gjuha == "albanian") {
										lblMesazhi.setText("Perdoruesi ose fjal�kalimi jane gabim!");
									} else {
										lblMesazhi.setText("Username or password is invalid!");
									}
								}
							} catch (NullPointerException e1) {
								JOptionPane.showMessageDialog(null, "Keni shenuar te dhenat gabim!");
							} catch (Exception e2) {
								// TODO: handle exception
								JOptionPane.showMessageDialog(null, e2);
							}
							
						} else if ( numroTentimet == 3 ) {
							try {
								String sql ="UPDATE llogarite SET eshteAktiv='jo' WHERE id='" + Database.idDB + "';";
								Database.executeQueryDB(sql);
								
								if (Gjuha.gjuha == "albanian") {
									lblMesazhi.setText("Llogaria juaj tani eshte e bllokuar!");
								} else {
									lblMesazhi.setText("Now account has been blocked!");
								}
								numroTentimet++;
							} catch (NullPointerException e1) {
								JOptionPane.showMessageDialog(null, "Keni shenuar te dhenat gabim!");
							} catch (Exception e2) {
								// TODO: handle exception
								JOptionPane.showMessageDialog(null, e2);
							}
						}				
					} else if ( numroTentimet > 3 ) {
						System.exit(0);
					} else {
						if (Gjuha.gjuha == "albanian") {
							lblMesazhi.setText("Llogaria juaj eshte bllokuar!");
						} else {
							lblMesazhi.setText("Your account has been blocked!");
						}
					}
				} else {
					if (Gjuha.gjuha == "albanian") {
						lblMesazhi.setText("Ju lutem plotesoni fushat e zbrazeta!");
					} else {
						lblMesazhi.setText("Please fill the empty fields!");
					}
					
						
				}
				
			}
		});
		
		btnKyqu.setFont(new Font("Courier New", Font.PLAIN, 17));
		btnKyqu.setBounds(380, 265, 99, 36);
		frmProgramiPerRezervimin.getContentPane().add(btnKyqu);
		
		JPanel panelGjuha = new JPanel();
		panelGjuha.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Zgjedh Gjuhen", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelGjuha.setBounds(418, 352, 182, 78);
		frmProgramiPerRezervimin.getContentPane().add(panelGjuha);
		panelGjuha.setLayout(null);
		
		JButton btnEnglish = new JButton("");
		btnEnglish.setBounds(27, 21, 60, 46);
		panelGjuha.add(btnEnglish);
		btnEnglish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Gjuha.gjuha = "english";
				lblMiresevini.setText("Welcome!");
				lblPerdoruesi.setText("Username:");
				lblFjalekalimi.setText("Password:");
				btnKyqu.setText("Login");
				frmProgramiPerRezervimin.setTitle("Car Rental Program");
				panelGjuha.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Choose Language", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			}
		});
		btnEnglish.setIcon(new ImageIcon(frmLogin.class.getResource("/images/England_icon.png")));
		
		JButton btnAlbanian = new JButton("");
		btnAlbanian.setBounds(97, 21, 60, 46);
		panelGjuha.add(btnAlbanian);
		btnAlbanian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gjuha.gjuha = "albanian";
				lblMiresevini.setText("Mir\u00EBsevini!");
				lblPerdoruesi.setText("P\u00EBrdoruesi:");
				lblFjalekalimi.setText("Fjal\u00EBkalimi:");
				btnKyqu.setText("Kyqu");
				frmProgramiPerRezervimin.setTitle("Programi per rezervimin e veturave me qera");
				panelGjuha.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Zgjedh Gjuhen", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			}
		});
		btnAlbanian.setIcon(new ImageIcon(frmLogin.class.getResource("/images/Albania_icon.png")));
		
		
		// Klikimi i buttonit me tastin enter
		txtFjalekalimi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if( e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnKyqu.doClick();
				}
			}
		});
		txtPerdoruesi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if( e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnKyqu.doClick();
				}
			}
		});
		
	}
}