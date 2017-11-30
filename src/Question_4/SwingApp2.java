package Question_4;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.SpinnerModel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import java.awt.Color;

import javax.swing.JPasswordField;
import javax.swing.SpinnerDateModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;

public class SwingApp2 {

	private JFrame frame;
	private JTextField txtName;
	private JTextField txtSurname;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField txtUsername;
	private JTextField txtEmail;
	
	boolean btnState;
	String notifications;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingApp2 window = new SwingApp2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SwingApp2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(153, 204, 255));
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setBounds(100, 100, 450, 620);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
				
		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Female", "Male"}));
		comboBox.setBounds(25, 129, 121, 20);
		frame.getContentPane().add(comboBox);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(25, 111, 161, 20);
		frame.getContentPane().add(lblGender);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(25, 11, 34, 20);
		frame.getContentPane().add(lblName);
		
		txtName = new JTextField();
		txtName.setForeground(SystemColor.desktop);
		txtName.setBounds(25, 34, 121, 20);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtSurname = new JTextField();
		txtSurname.setForeground(SystemColor.desktop);
		txtSurname.setToolTipText("");
		txtSurname.setBounds(171, 34, 121, 20);
		frame.getContentPane().add(txtSurname);
		txtSurname.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(25, 272, 267, 20);
		frame.getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(25, 317, 267, 20);
		frame.getContentPane().add(passwordField_1);
		
		JLabel lblCreatePassword = new JLabel("Create Password");
		lblCreatePassword.setBounds(25, 253, 240, 23);
		frame.getContentPane().add(lblCreatePassword);
		
		JLabel lblReenterPassword = new JLabel("Reenter Password");
		lblReenterPassword.setBounds(25, 297, 240, 23);
		frame.getContentPane().add(lblReenterPassword);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(25, 229, 267, 20);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(25, 203, 240, 28);
		frame.getContentPane().add(lblUsername);
		
		final JCheckBox chckbxNewCheckBox = new JCheckBox("I have read and agree to the Terms of Use and Privacy Policy");
		chckbxNewCheckBox.setBounds(25, 446, 378, 23);
		frame.getContentPane().add(chckbxNewCheckBox);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(25, 181, 267, 20);
		frame.getContentPane().add(txtEmail);
		
		JLabel lblEmail = new JLabel("E-Mail");
		lblEmail.setBounds(25, 160, 240, 20);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setBounds(173, 11, 62, 20);
		frame.getContentPane().add(lblSurname);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setBounds(25, 60, 161, 20);
		frame.getContentPane().add(lblDateOfBirth);
		
		 SpinnerModel model = new SpinnerDateModel();
		 final JSpinner spinner = new JSpinner(model);
		 JComponent editor = new JSpinner.DateEditor(spinner, "dd-MMMMMMM-yyyy");
		 spinner.setEditor(editor);
		 spinner.setBounds(25, 80, 121, 20);
		 frame.getContentPane().add(spinner);
		 
		 JButton btnCreateAccount = new JButton("Create Account");
		 btnCreateAccount.addActionListener(new ActionListener() {
		 	@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				if (txtName.getText().isEmpty() || txtSurname.getText().isEmpty() || txtEmail.getText().isEmpty() || txtUsername.getText().isEmpty() || passwordField.getText().isEmpty() || passwordField_1.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "No fields can be left blank");
				else {
					if (chckbxNewCheckBox.isSelected()) {
						User usr = new User();
						usr.setName(txtName.getText());
						usr.setSurname(txtSurname.getText());
						usr.setBirthday(spinner.getValue().toString());
						usr.setEmail(txtEmail.getText());
						usr.setGender(comboBox.getSelectedItem().toString());
						String ps1 = new String(passwordField.getPassword());
						String ps2 = new String(passwordField_1.getPassword());				
					
						
						if (!ps1.equals(ps2))		//Eðer girilen iki parola ayný deðilse
							JOptionPane.showMessageDialog(null, "Reentered password is different");
						else {
							usr.setUsername(txtUsername.getText());
							usr.setPassword(ps1);
							usr.setNotifications(btnState);
							if(btnState)
								notifications = "ON";
							else
								notifications = "OFF";
							//Kullanýcý bilgileri Message Dialog'ta yazýlacak
							JOptionPane.showMessageDialog(null,	"Account created successfully.\n\nUser Info :" 
							+ "\nName : " + usr.getName() + "\nSurname : " + usr.getSurname()+ "\nBirthday : " 
							+ usr.getBirthday() + "\nGender : " +usr.getGender()+ "\nEmail : " + usr.getEmail()
							+ "\nUsername : " + usr.getUsername() + "\nPassword : " + usr.getPassword()
							+ "\nNotifications are " + notifications);
						}
					} else		//Kullanýcý checkBox'u seçmemiþse hata mesajý verecek
						JOptionPane.showMessageDialog(null,"You have to agree to the Terms of Use and Privacy Policy to create an account");
				}
		 	}
		 });
		 btnCreateAccount.setBounds(139, 494, 151, 28);
		 frame.getContentPane().add(btnCreateAccount);
		 
		 JLabel lblIWantTo = new JLabel("I want to receive e-mail notifications");
		 lblIWantTo.setBounds(25, 383, 267, 23);
		 frame.getContentPane().add(lblIWantTo);
		 
		 final JToggleButton tglbtnOff = new JToggleButton("OFF");
		 tglbtnOff.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mouseClicked(MouseEvent arg0) {
		 		if(tglbtnOff.isSelected()){
		 			tglbtnOff.setText("ON");
		 			btnState = true;
		 		}
		 		else{
		 			tglbtnOff.setText("OFF");
		 			btnState = false;
		 		}
		 	}
		 });
		 tglbtnOff.setBounds(296, 383, 62, 23);
		 frame.getContentPane().add(tglbtnOff);

	}
}

