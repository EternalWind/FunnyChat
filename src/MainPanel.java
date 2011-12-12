import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * MainPanel.java
 *
 * Created on __DATE__, __TIME__
 */

/**
 *
 * @author  __USER__
 */
public class MainPanel extends javax.swing.JPanel {

	/** Creates new form MainPanel */
	public MainPanel(Client _client) {
		client = _client;
		name = client.friendsName();
		state = client.friendsState();
		id = client.friendsID();
		initComponents();
		this.refreshFriends();
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		jSeparator1 = new javax.swing.JSeparator();
		jScrollPane1 = new javax.swing.JScrollPane();
		jList1 = new javax.swing.JList();
		jLabel2 = new javax.swing.JLabel();
		jSeparator2 = new javax.swing.JSeparator();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jButton4 = new javax.swing.JButton();
		jButton5 = new javax.swing.JButton();
		jButton6 = new javax.swing.JButton();

		jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 25));
		jLabel1.setText("Status :");

		jList1.setModel(new javax.swing.AbstractListModel() {
			String[] strings = { "You" };

			public int getSize() {
				return strings.length;
			}

			public Object getElementAt(int i) {
				return strings[i];
			}
		});
		jList1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				listboxMouseClicked(evt);
			}
		});
		jScrollPane1.setViewportView(jList1);

		jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 25));
		jLabel2.setText("Friends :");

		jButton1.setText("Chat");
		jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				chatMouseClicked(evt);
			}
		});

		jButton2.setText("Add");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		jButton3.setText("Delete");
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});

		jButton4.setText("Sign Out");

		jButton5.setText("Info");

		jButton6.setText("\u221a");
		jButton6.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton6ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jLabel2,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		238,
																		Short.MAX_VALUE)
																.addGap(157,
																		157,
																		157))
												.addComponent(
														jSeparator1,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														395, Short.MAX_VALUE)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jLabel1,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		146,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jTextField1,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		190,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jButton6,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		47,
																		Short.MAX_VALUE))
												.addGroup(
														javax.swing.GroupLayout.Alignment.TRAILING,
														layout.createSequentialGroup()
																.addComponent(
																		jButton1,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		70,
																		Short.MAX_VALUE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jButton2,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		67,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jButton3,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		69,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jButton5,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		67,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(18, 18,
																		18)
																.addComponent(
																		jButton4))
												.addComponent(
														jScrollPane1,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														395, Short.MAX_VALUE)
												.addComponent(
														jSeparator2,
														javax.swing.GroupLayout.Alignment.TRAILING,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														395, Short.MAX_VALUE))
								.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
																.addComponent(
																		jTextField1,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		32,
																		Short.MAX_VALUE)
																.addComponent(
																		jButton6,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		31,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
												.addComponent(
														jLabel1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														32,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jSeparator1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jLabel2,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jScrollPane1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										332,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jSeparator2,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														jButton4,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														34,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jButton1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														34,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jButton2,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														34,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jButton3,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														34,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jButton5,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														34,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap()));
	}// </editor-fold>
	//GEN-END:initComponents

	private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
		client.setUserState(jTextField1.getText());
	}

	private void listboxMouseClicked(java.awt.event.MouseEvent evt) {
		if (evt.getClickCount() == 2) {
			onChatClicked();
		}
	}

	private void chatMouseClicked(java.awt.event.MouseEvent evt) {
		onChatClicked();
	}

	private void onChatClicked() {
		JFrame frame = new JFrame("Chatting with "
				+ name[jList1.getSelectedIndex()] + "("
				+ id[jList1.getSelectedIndex()] + ")");
		Chat chatPanel = new Chat(client, Long.parseLong(id[jList1
				.getSelectedIndex()]));
		client.chatPanels.put(Long.parseLong(id[jList1.getSelectedIndex()]),
				chatPanel);
		frame.add(chatPanel);
		frame.setSize(607, 547);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}

	private void refreshFriends() {
		jList1.removeAll();
		String[] friendItem = new String[name.length];

		for (int i = 0; i < name.length; i++) {
			friendItem[i] = name[i] + "(" + id[i] + ")" + "         "
					+ state[i];
		}

		jList1.setListData(friendItem);
	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		String fid = JOptionPane
				.showInputDialog("Please input the ID of the friend you want to add :");
		client.addFriend(Long.parseLong(client.getUid()), Long.parseLong(fid));
	}

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
		int _k = jList1.getSelectedIndex();
		client.deleteFriend(Long.parseLong(client.getUid()),
				Long.parseLong(id[jList1.getSelectedIndex()]));
	}

	private Client client;
	private String[] name;
	private String[] state;
	private String[] id;
	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JButton jButton5;
	private javax.swing.JButton jButton6;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JList jList1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JSeparator jSeparator2;
	private javax.swing.JTextField jTextField1;
	// End of variables declaration//GEN-END:variables

}