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

		// Ýki tane radio buttonun ayný anda seçilmemesi için ButtonGroup oluþturuldu
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnEnterManually);
		group.add(rdbtnGenerateRandomly);

		// 'Add Integer' Butonuna basýldýðýnda
		JButton btnAddInteger = new JButton("Add Integer");
		btnAddInteger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (getArraySize.getText().isEmpty())
					JOptionPane.showMessageDialog(null,
							"Please enter the array size!");
				else {

					arraySize = getArraySize.getText(); // Array'in boyutunu textbox'tan alýyor
					
					try {
						if (Integer.parseInt(arraySize) == 0)
							JOptionPane.showMessageDialog(null,
									"Array size cannot be zero!");
						else if (Integer.parseInt(arraySize) < 0)
							JOptionPane.showMessageDialog(null,
									"Array size can not be negative number!");
						else {
							if (rdbtnEnterManually.isSelected()) { 	// Enter New Manually radio butonu seçilirse
								if (getInt.getText().isEmpty()) 	// Eðer textboxa deðer girilmemiþse girilmesi için mesaj verecek
									JOptionPane.showMessageDialog(null,	"Please enter a value to add!");
								else if (arrList.size() == Integer.parseInt(arraySize)) // Array'in boyutunun aþýlmamasý için if-else kontrolü
									JOptionPane.showMessageDialog(null,	"Array is full!"); // Array dolduðunda uyarý verecek ve yeni sayý eklenemeyecek
								else {
									if (Integer.parseInt(arraySize) < arrList.size()) { // Eðer kullanýcýnýn girdiði array size deðeri 
																						//önceki arrayList size'dan küçükse
										arrList.clear(); // Yeni array oluþturulmak üzere arrayList ve model temizlenecek
										model.clear();
									}
									// Eðer kullanýcýnýn girdiði array size deðeri önceki array sizedan büyükse
									// array temizlenmeyecek sadece o sayý kadar geniþletilecek
									arrList.add(Integer.parseInt(getInt.getText())); // ArrayList'e textbox'tan kullanýcýnýn girdiði sayýlarý ekleyecek
									model.addElement((arrList.get(arrList.size() - 1).toString())); // ArrayList'deki elemanlar JList'te gösterilmek için eklendi

								}
							}

							else if (rdbtnGenerateRandomly.isSelected()) { // GenerateRandomly radio butonu seçilirse
								int num;
								arrList.clear();
								model.clear();

								for (int l = 0; l < Integer.parseInt(arraySize); l++) {
									num = rnd.nextInt(100) + 1; // 1-100 arasýnda random sayý üretilecek
									arrList.add(num);
									model.addElement((arrList.get(l).toString()));
								}

							}

							else
								// Kullanýcý hiçbir radio butonu seçmeden iþlem yapmaya çalýþýrsa hata mesajý verecek
								JOptionPane.showMessageDialog(null,
										"Please select a radio button");
						}
					} catch (NumberFormatException nfe)				 // Kullanýcý textboxlara integer deðer girmezse hata mesajý verecek
					{
						JOptionPane.showMessageDialog(null,	"Please enter an integer value!");
					}
				}
			}
		});

		btnAddInteger.setBounds(28, 63, 115, 23);
		frame.getContentPane().add(btnAddInteger);

		// 'Delete Integer' butonuna basýldýðýnda
		JButton btnDeleteInteger = new JButton("Delete Integer");
		btnDeleteInteger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Kullanýcý silme iþlemini 2 þekilde yapabilir
				// Ya listeden seçebilir ya da textbox'a silinecek deðeri girebilir
				int index = -1;
				if (arrList.isEmpty()) // Array boþsa silinecek eleman yok hatasý verecek
					JOptionPane.showMessageDialog(null,	"There is no data to delete!");
				else if (selectedIndex != -1) { // Eðer listeden seçim yapýldýysa
					int frequency = Collections.frequency(arrList, selectedValue);  // Seçilen deðerin listede kaç tane olduðunu bulur
					for (int i = 0; i < frequency; i++) {    	// Sayý kaç tane varsa hepsini siler
						index = arrList.indexOf(selectedValue);	//Seçilen sayýnýn index deðeri alýndý
						arrList.remove(index);					//O index deðerindeki sayý arrayList'ten silindi
					}
					model.clear();
					for (int j = 0; j < arrList.size(); j++)
						model.addElement((arrList.get(j).toString()));
				} 
				else {
					if (getInt.getText().isEmpty()) 			// Kullanýcý silinecek deðeri textboxa girmediyse hata mesajý verecek
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

		// 'Sort Array' butonuna basýldýðýnda
		JButton btnSortArray = new JButton("Sort Array");
		btnSortArray.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (arrList.isEmpty())				//Array list boþsa hata mesajý verecek
					JOptionPane.showMessageDialog(null, "Array is empty!");
				else {
					Collections.sort(arrList);		//ArrayListin elemanlarýný küçükten büyüðe sýralar
					model.clear();
					for (int j = 0; j < arrList.size(); j++) {
						model.addElement((arrList.get(j).toString()));
					}
				}
			}
		});
		btnSortArray.setBounds(336, 141, 146, 23);
		frame.getContentPane().add(btnSortArray);

		// 'Search In Array' butonuna basýldýðýnda
		JButton btnSearchInArray = new JButton("Search In Array");
		btnSearchInArray.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
										
					if (!getSearchVal.getText().isEmpty()) {								//Eðer kullanýcý textboxa bir deðer girdiyse
						if(arrList.isEmpty())
							JOptionPane.showMessageDialog(null,	"Array is empty!");
						else if (arrList.contains(Integer.parseInt(getSearchVal.getText())))		//Girilen deðer bulunduysa
							JOptionPane.showMessageDialog(null, getSearchVal.getText()+ " is in the array in "
											+ Collections.frequency(arrList, Integer.parseInt(getSearchVal.getText()))+ " times");
						else		//Girilen deðer arrayList'te yoksa 
							JOptionPane.showMessageDialog(null,	getSearchVal.getText() + " is NOT in the array!");
					} 		
					else		//Kullanýcý textboxa bir deðer girmediyse hata mesajý verilecek
						JOptionPane.showMessageDialog(null,	"Please enter a value to search!");
				} 
				catch (NumberFormatException nfd) // Kullanýcý integer olmayan bir deðer girerse hata mesajý verecek
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
				selectedValue = Integer.parseInt(list.getSelectedValue().toString()); // Listeden seçim yapýldýðýnda seçilen deðeri alýr
			}
		});
		scrollPane.setViewportView(list);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				selectedIndex = list.getSelectedIndex(); // Listeden seçim yapýldýðýnda seçilen index deðerini alýr
			}
		});
		list.setVisibleRowCount(5);

	}
}
