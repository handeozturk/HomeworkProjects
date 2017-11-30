package Question_3;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SwingApp {

	private JFrame frame;
	private JTextField getArraySize;
	private JTextField getInt;
	private JTextField getSearchVal;

	String arraySize;
	ArrayList<Integer> arrList = new ArrayList<Integer>();;
	DefaultListModel<String> model = new DefaultListModel<String>();
	Random rnd = new Random();
	int selectedIndex = -1;
	int selectedValue;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingApp window = new SwingApp();
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
	public SwingApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 530, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblTheSizeOf = new JLabel("The Size of Array");
		lblTheSizeOf.setBounds(28, 11, 115, 21);
		frame.getContentPane().add(lblTheSizeOf);

		JLabel lblEnterNewInteger = new JLabel("Enter New Integer");
		lblEnterNewInteger.setBounds(164, 14, 107, 14);
		frame.getContentPane().add(lblEnterNewInteger);

		getArraySize = new JTextField();
		getArraySize.setBounds(28, 32, 115, 20);
		frame.getContentPane().add(getArraySize);
		getArraySize.setColumns(10);

		getInt = new JTextField();
		getInt.setBounds(164, 32, 130, 20);
		frame.getContentPane().add(getInt);
		getInt.setColumns(10);

		final JRadioButton rdbtnEnterManually = new JRadioButton("Enter New Manually");
		rdbtnEnterManually.setBounds(336, 31, 164, 23);
		frame.getContentPane().add(rdbtnEnterManually);

		final JRadioButton rdbtnGenerateRandomly = new JRadioButton("Generate Randomly");

		rdbtnGenerateRandomly.setBounds(336, 63, 164, 23);
		frame.getContentPane().add(rdbtnGenerateRandomly);

		// �ki tane radio buttonun ayn� anda se�ilmemesi i�in ButtonGroup olu�turuldu
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnEnterManually);
		group.add(rdbtnGenerateRandomly);

		// 'Add Integer' Butonuna bas�ld���nda
		JButton btnAddInteger = new JButton("Add Integer");
		btnAddInteger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (getArraySize.getText().isEmpty())
					JOptionPane.showMessageDialog(null,
							"Please enter the array size!");
				else {

					arraySize = getArraySize.getText(); // Array'in boyutunu textbox'tan al�yor
					
					try {
						if (Integer.parseInt(arraySize) == 0)
							JOptionPane.showMessageDialog(null,
									"Array size cannot be zero!");
						else if (Integer.parseInt(arraySize) < 0)
							JOptionPane.showMessageDialog(null,
									"Array size can not be negative number!");
						else {
							if (rdbtnEnterManually.isSelected()) { 	// Enter New Manually radio butonu se�ilirse
								if (getInt.getText().isEmpty()) 	// E�er textboxa de�er girilmemi�se girilmesi i�in mesaj verecek
									JOptionPane.showMessageDialog(null,	"Please enter a value to add!");
								else if (arrList.size() == Integer.parseInt(arraySize)) // Array'in boyutunun a��lmamas� i�in if-else kontrol�
									JOptionPane.showMessageDialog(null,	"Array is full!"); // Array doldu�unda uyar� verecek ve yeni say� eklenemeyecek
								else {
									if (Integer.parseInt(arraySize) < arrList.size()) { // E�er kullan�c�n�n girdi�i array size de�eri 
																						//�nceki arrayList size'dan k���kse
										arrList.clear(); // Yeni array olu�turulmak �zere arrayList ve model temizlenecek
										model.clear();
									}
									// E�er kullan�c�n�n girdi�i array size de�eri �nceki array sizedan b�y�kse
									// array temizlenmeyecek sadece o say� kadar geni�letilecek
									arrList.add(Integer.parseInt(getInt.getText())); // ArrayList'e textbox'tan kullan�c�n�n girdi�i say�lar� ekleyecek
									model.addElement((arrList.get(arrList.size() - 1).toString())); // ArrayList'deki elemanlar JList'te g�sterilmek i�in eklendi

								}
							}

							else if (rdbtnGenerateRandomly.isSelected()) { // GenerateRandomly radio butonu se�ilirse
								int num;
								arrList.clear();
								model.clear();

								for (int l = 0; l < Integer.parseInt(arraySize); l++) {
									num = rnd.nextInt(100) + 1; // 1-100 aras�nda random say� �retilecek
									arrList.add(num);
									model.addElement((arrList.get(l).toString()));
								}

							}

							else
								// Kullan�c� hi�bir radio butonu se�meden i�lem yapmaya �al���rsa hata mesaj� verecek
								JOptionPane.showMessageDialog(null,
										"Please select a radio button");
						}
					} catch (NumberFormatException nfe)				 // Kullan�c� textboxlara integer de�er girmezse hata mesaj� verecek
					{
						JOptionPane.showMessageDialog(null,	"Please enter an integer value!");
					}
				}
			}
		});

		btnAddInteger.setBounds(28, 63, 115, 23);
		frame.getContentPane().add(btnAddInteger);

		// 'Delete Integer' butonuna bas�ld���nda
		JButton btnDeleteInteger = new JButton("Delete Integer");
		btnDeleteInteger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Kullan�c� silme i�lemini 2 �ekilde yapabilir
				// Ya listeden se�ebilir ya da textbox'a silinecek de�eri girebilir
				int index = -1;
				if (arrList.isEmpty()) // Array bo�sa silinecek eleman yok hatas� verecek
					JOptionPane.showMessageDialog(null,	"There is no data to delete!");
				else if (selectedIndex != -1) { // E�er listeden se�im yap�ld�ysa
					int frequency = Collections.frequency(arrList, selectedValue);  // Se�ilen de�erin listede ka� tane oldu�unu bulur
					for (int i = 0; i < frequency; i++) {    	// Say� ka� tane varsa hepsini siler
						index = arrList.indexOf(selectedValue);	//Se�ilen say�n�n index de�eri al�nd�
						arrList.remove(index);					//O index de�erindeki say� arrayList'ten silindi
					}
					model.clear();
					for (int j = 0; j < arrList.size(); j++)
						model.addElement((arrList.get(j).toString()));
				} 
				else {
					if (getInt.getText().isEmpty()) 			// Kullan�c� silinecek de�eri textboxa girmediyse hata mesaj� verecek
						JOptionPane.showMessageDialog(null, "Please enter a value to delete or select on the list!");
					else if (!arrList.contains(Integer.parseInt(getInt.getText())))
						JOptionPane.showMessageDialog(null,	+Integer.parseInt(getInt.getText())	+ " is NOT in the array!");
					else {
						int frequency = Collections.frequency(arrList, Integer.parseInt(getInt.getText()));
						for (int i = 0; i < frequency; i++) {
							index = arrList.indexOf(Integer.parseInt(getInt.getText()));
							arrList.remove(index);
						}
						model.clear();
						for (int j = 0; j < arrList.size(); j++) {
							model.addElement((arrList.get(j).toString()));
						}
					}
				}

			}
		});
		btnDeleteInteger.setBounds(164, 63, 130, 23);
		frame.getContentPane().add(btnDeleteInteger);

		// 'Sort Array' butonuna bas�ld���nda
		JButton btnSortArray = new JButton("Sort Array");
		btnSortArray.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (arrList.isEmpty())				//Array list bo�sa hata mesaj� verecek
					JOptionPane.showMessageDialog(null, "Array is empty!");
				else {
					Collections.sort(arrList);		//ArrayListin elemanlar�n� k���kten b�y��e s�ralar
					model.clear();
					for (int j = 0; j < arrList.size(); j++) {
						model.addElement((arrList.get(j).toString()));
					}
				}
			}
		});
		btnSortArray.setBounds(336, 141, 146, 23);
		frame.getContentPane().add(btnSortArray);

		// 'Search In Array' butonuna bas�ld���nda
		JButton btnSearchInArray = new JButton("Search In Array");
		btnSearchInArray.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
										
					if (!getSearchVal.getText().isEmpty()) {								//E�er kullan�c� textboxa bir de�er girdiyse
						if(arrList.isEmpty())
							JOptionPane.showMessageDialog(null,	"Array is empty!");
						else if (arrList.contains(Integer.parseInt(getSearchVal.getText())))		//Girilen de�er bulunduysa
							JOptionPane.showMessageDialog(null, getSearchVal.getText()+ " is in the array in "
											+ Collections.frequency(arrList, Integer.parseInt(getSearchVal.getText()))+ " times");
						else		//Girilen de�er arrayList'te yoksa 
							JOptionPane.showMessageDialog(null,	getSearchVal.getText() + " is NOT in the array!");
					} 		
					else		//Kullan�c� textboxa bir de�er girmediyse hata mesaj� verilecek
						JOptionPane.showMessageDialog(null,	"Please enter a value to search!");
				} 
				catch (NumberFormatException nfd) // Kullan�c� integer olmayan bir de�er girerse hata mesaj� verecek
				{
					JOptionPane.showMessageDialog(null,	"Please enter a integer value to search!");
				}
			}
		});
		btnSearchInArray.setBounds(336, 107, 146, 23);
		frame.getContentPane().add(btnSearchInArray);

		JLabel lblValueToBe = new JLabel("Value To Be Search");
		lblValueToBe.setBounds(28, 108, 115, 21);
		frame.getContentPane().add(lblValueToBe);

		getSearchVal = new JTextField();
		getSearchVal.setBounds(164, 108, 130, 20);
		frame.getContentPane().add(getSearchVal);
		getSearchVal.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 136, 266, 103);
		frame.getContentPane().add(scrollPane);

		final JList<String> list = new JList<String>((model));
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selectedValue = Integer.parseInt(list.getSelectedValue().toString()); // Listeden se�im yap�ld���nda se�ilen de�eri al�r
			}
		});
		scrollPane.setViewportView(list);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				selectedIndex = list.getSelectedIndex(); // Listeden se�im yap�ld���nda se�ilen index de�erini al�r
			}
		});
		list.setVisibleRowCount(5);

	}
}
