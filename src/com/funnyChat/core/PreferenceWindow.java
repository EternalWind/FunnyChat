/*
 * PreferenceWindow.java
 *
 * Created on __DATE__, __TIME__
 */

package com.funnyChat.core;

import java.awt.Dimension;
import java.util.Collection;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.funnyChat.network.NetworkManager;
import com.funnyChat.plugin.Plugin;
import com.funnyChat.plugin.PluginManager;
import com.funnyChat.utils.ConfigurationInfo;

/**
 * 
 * @author __USER__
 */
public class PreferenceWindow extends javax.swing.JDialog {

	/** Creates new form PreferenceWindow */
	public PreferenceWindow(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		mWarning = false;
		initComponents();
		initializePM();
		initializeGN();
		this.setLocation(200, 200);
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jTabbedPane1 = new javax.swing.JTabbedPane();
		jPanel2 = new javax.swing.JPanel();
		jTextField1 = new javax.swing.JTextField();
		jLabel1 = new javax.swing.JLabel();
		jCheckBox1 = new javax.swing.JCheckBox();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jPanel1 = new javax.swing.JPanel();
		JBT_move_enable = new javax.swing.JButton();
		JBT_move_disable = new javax.swing.JButton();
		JLB_disable = new javax.swing.JLabel();
		JLB_enable = new javax.swing.JLabel();
		JBT_ok = new javax.swing.JButton();
		JBT_cancel = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		JL_disable = new javax.swing.JList();
		jScrollPane2 = new javax.swing.JScrollPane();
		JL_enable = new javax.swing.JList();
		jButton3 = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		jTabbedPane1.setToolTipText("");

		jTextField1.setPreferredSize(new java.awt.Dimension(41, 23));

		jLabel1.setText("Listening port :");

		jCheckBox1.setText("Random");
		jCheckBox1.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				PreferenceWindow.this.stateChanged(evt);
			}
		});

		jButton1.setText("OK");
		jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				OK2mouseClicked(evt);
			}
		});

		jButton2.setText("Cancel");
		jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				cancel2MouseClicked(evt);
			}
		});

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
				jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout
				.setHorizontalGroup(jPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel2Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				jLabel1)
																		.addGap(18,
																				18,
																				18)
																		.addComponent(
																				jTextField1,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				51,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				jCheckBox1))
														.addGroup(
																jPanel2Layout
																		.createSequentialGroup()
																		.addGap(196,
																				196,
																				196)
																		.addComponent(
																				jButton1,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				92,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				39,
																				Short.MAX_VALUE)
																		.addComponent(
																				jButton2,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				92,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addContainerGap()));
		jPanel2Layout
				.setVerticalGroup(jPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel1)
														.addComponent(
																jCheckBox1)
														.addComponent(
																jTextField1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												307, Short.MAX_VALUE)
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jButton1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																54,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jButton2,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																54,
																Short.MAX_VALUE))
										.addGap(22, 22, 22)));

		jTabbedPane1.addTab("General", jPanel2);

		JBT_move_enable.setText(">>");
		JBT_move_enable.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				JBT_move_enableMouseClicked(evt);
			}
		});

		JBT_move_disable.setText("<<");
		JBT_move_disable.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				JBT_move_disableMouseClicked(evt);
			}
		});

		JLB_disable.setText("Disable");

		JLB_enable.setText("Enable");

		JBT_ok.setText("OK");
		JBT_ok.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				JBT_okMouseClicked(evt);
			}
		});

		JBT_cancel.setText("CANCEL");
		JBT_cancel.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				JBT_cancelMouseClicked(evt);
			}
		});

		JL_disable.setModel(new javax.swing.AbstractListModel() {
			String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4",
					"Item 5" };

			public int getSize() {
				return strings.length;
			}

			public Object getElementAt(int i) {
				return strings[i];
			}
		});
		jScrollPane1.setViewportView(JL_disable);

		JL_enable.setModel(new javax.swing.AbstractListModel() {
			String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4",
					"Item 5" };

			public int getSize() {
				return strings.length;
			}

			public Object getElementAt(int i) {
				return strings[i];
			}
		});
		jScrollPane2.setViewportView(JL_enable);

		jButton3.setText("Cfg");
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				jScrollPane1,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				148,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jButton3,
																								0,
																								0,
																								Short.MAX_VALUE)
																						.addGroup(
																								jPanel1Layout
																										.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING,
																												false)
																										.addComponent(
																												JBT_move_disable,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												Short.MAX_VALUE)
																										.addComponent(
																												JBT_move_enable,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												69,
																												Short.MAX_VALUE))))
														.addComponent(
																JLB_disable))
										.addGap(26, 26, 26)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																JLB_enable)
														.addGroup(
																jPanel1Layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING,
																				false)
																		.addGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				jPanel1Layout
																						.createSequentialGroup()
																						.addComponent(
																								JBT_ok,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								72,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addPreferredGap(
																								javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								JBT_cancel))
																		.addComponent(
																				jScrollPane2,
																				javax.swing.GroupLayout.Alignment.LEADING,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				156,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addGap(68, 68, 68)));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				JLB_enable)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jScrollPane2,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				318,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								JBT_ok,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								42,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								JBT_cancel,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								40,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addContainerGap())
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING)
																						.addGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								jPanel1Layout
																										.createSequentialGroup()
																										.addGap(61,
																												61,
																												61)
																										.addComponent(
																												JBT_move_enable,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												43,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												JBT_move_disable,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												43,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addGap(70,
																												70,
																												70)
																										.addComponent(
																												jButton3,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												48,
																												javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								jPanel1Layout
																										.createSequentialGroup()
																										.addComponent(
																												JLB_disable)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												jScrollPane1,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												326,
																												javax.swing.GroupLayout.PREFERRED_SIZE)))
																		.addGap(56,
																				56,
																				56)))));

		jTabbedPane1.addTab("Plugin Management", jPanel1);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 436,
				javax.swing.GroupLayout.PREFERRED_SIZE));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addComponent(jTabbedPane1,
								javax.swing.GroupLayout.PREFERRED_SIZE, 452,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
		int _index;
		JDialog _con_pan = null;
		
		if((_index = JL_enable.getSelectedIndex()) != -1) {
			_con_pan = PluginManager.getInstance().get(mEnableList.
					get(_index)).getConfigPanel();
		}
		else if((_index = JL_disable.getSelectedIndex()) != -1){
			_con_pan = PluginManager.getInstance().get(mDisableList.
					get(_index)).getConfigPanel();
		}
		
		if(_con_pan != null) {
			_con_pan.setVisible(true);
		}
	}

	private void cancel2MouseClicked(java.awt.event.MouseEvent evt) {
		JBT_cancelMouseClicked(evt);
	}

	private void OK2mouseClicked(java.awt.event.MouseEvent evt) {
		JBT_okMouseClicked(evt);
	}

	private void detectPortFieldEnable() {
		if (jCheckBox1.isSelected()) {
			jTextField1.setEnabled(false);
		} else {
			jTextField1.setEnabled(true);
		}
	}

	private void stateChanged(javax.swing.event.ChangeEvent evt) {
		detectPortFieldEnable();
	}

	private void JBT_cancelMouseClicked(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:
		this.dispose();
	}

	private void JBT_okMouseClicked(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:
		updatePluginState();
		updatePort();

		if (mWarning) {
			JOptionPane.showMessageDialog(JBT_ok,
					"You need to restart FunnyChat to apply the change.");
		}

		this.dispose();
	}

	private void JBT_move_disableMouseClicked(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:
		if (JL_enable.getSelectedIndex() != -1) {
			Vector<String> _enable_list = new Vector<String>();
			Vector<String> _disable_list = new Vector<String>();

			for (int i = 0; i != JL_disable.getModel().getSize(); ++i) {
				_disable_list.add(String.valueOf(JL_disable.getModel()
						.getElementAt(i)));
			}
			for (int i = 0; i != JL_enable.getModel().getSize(); ++i) {
				if (JL_enable.isSelectedIndex(i)) {
					_disable_list.add(String.valueOf(JL_enable.getModel()
							.getElementAt(i)));
				} else {
					_enable_list.add(String.valueOf(JL_enable.getModel()
							.getElementAt(i)));
				}
			}
			JL_enable.setListData(_enable_list);
			JL_disable.setListData(_disable_list);
			mEnableList = _enable_list;
			mDisableList = _disable_list;
		}
	}

	private void JBT_move_enableMouseClicked(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:
		if (JL_disable.getSelectedIndex() != -1) {
			Vector<String> _enable_list = new Vector<String>();
			Vector<String> _disable_list = new Vector<String>();

			for (int i = 0; i != JL_enable.getModel().getSize(); ++i) {
				_enable_list.add(String.valueOf(JL_enable.getModel()
						.getElementAt(i)));
			}
			for (int i = 0; i != JL_disable.getModel().getSize(); ++i) {
				if (JL_disable.isSelectedIndex(i)) {
					_enable_list.add(String.valueOf(JL_disable.getModel()
							.getElementAt(i)));
				} else {
					_disable_list.add(String.valueOf(JL_disable.getModel()
							.getElementAt(i)));
				}
			}
			JL_enable.setListData(_enable_list);
			JL_disable.setListData(_disable_list);
			mEnableList = _enable_list;
			mDisableList = _disable_list;
		}
	}

	private void initializePM() {
		Collection<Plugin> _plugins = PluginManager.getInstance().getPlugins();

		mEnableList = new Vector<String>();
		mDisableList = new Vector<String>();

		for (Plugin _plugin : _plugins) {
			if (_plugin.isEnabled())
				mEnableList.add(_plugin.getPluginName());
			else
				mDisableList.add(_plugin.getPluginName());
		}
		JL_enable.setListData(mEnableList);
		JL_disable.setListData(mDisableList);
	}

	private void initializeGN() {
		mPort = NetworkManager.getInstance().getPort();
		jTextField1.setText(mPort.toString());
		ConfigurationInfo _config = Core.getInstance().getMainWindow()
				.getConfigInfo();

		if (_config.getPort() == _config.RANDOMPORT) {
			jCheckBox1.setSelected(true);
		}

		detectPortFieldEnable();
	}

	private void updatePort() {
		mPort = Integer.parseInt(jTextField1.getText());
		ConfigurationInfo _config = Core.getInstance().getMainWindow()
				.getConfigInfo();

		if (mPort > 65535 || mPort < 0) {
			JOptionPane.showMessageDialog(JBT_ok, "Invalid port!");
		} else {
			if (NetworkManager.getInstance().getPort() != mPort
					|| (_config.getPort() != _config.RANDOMPORT && jCheckBox1
							.isSelected())
					|| (_config.getPort() == _config.RANDOMPORT && !jCheckBox1
							.isSelected())) {
				if (_config.getPort() != _config.RANDOMPORT
						&& jCheckBox1.isSelected()) {
					_config.setPort(_config.RANDOMPORT);
				} else {
					_config.setPort(mPort);
				}
				mWarning = true;
			}
		}
	}

	private void updatePluginState() {
		/*Collection<Plugin> _plugins = PluginManager.getInstance().getPlugins();
		for (Plugin _plugin : _plugins) {
			if (mEnableList.contains(_plugin.getPluginName())) {
				_plugin.enable();
				if (_plugin.getPanel() != null)
					Core.getInstance().getMainWindow().add(_plugin.getPanel());
				_plugin.getPanel().setVisible(true);
			} else {
				if (_plugin.getPanel() != null) {
					Core.getInstance().getMainWindow()
							.remove(_plugin.getPanel());
					Core.getInstance().getMainWindow().repaint();
					Core.getInstance().getMainWindow().validate();
					_plugin.getPanel().repaint();
					_plugin.getPanel().validate();
				}
				_plugin.disable();
			}
		}*/
		PluginManager _pm = PluginManager.getInstance();

		for (String _name : mEnableList) {
			_pm.enable(_name);
		}

		for (String _name : mDisableList) {
			Plugin _plugin = _pm.disable(_name);
			Dimension _dim = Core.getInstance().getMainWindow().getSize();

			Core.getInstance().getMainWindow().remove(_plugin.getPanel());
			_dim.height -= 1;
			Core.getInstance().getMainWindow().setSize(_dim);
			_dim.height += 1;
			Core.getInstance().getMainWindow().setSize(_dim);
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				PreferenceWindow dialog = new PreferenceWindow(
						new javax.swing.JFrame(), true);
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					public void windowClosing(java.awt.event.WindowEvent e) {
						e.getWindow().dispose();
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}

	// private javax.swing.JCheckBox[] mPluginCBs;
	private Vector<String> mEnableList;
	private Vector<String> mDisableList;
	private Integer mPort;
	private boolean mWarning;

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton JBT_cancel;
	private javax.swing.JButton JBT_move_disable;
	private javax.swing.JButton JBT_move_enable;
	private javax.swing.JButton JBT_ok;
	private javax.swing.JLabel JLB_disable;
	private javax.swing.JLabel JLB_enable;
	private javax.swing.JList JL_disable;
	private javax.swing.JList JL_enable;
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JCheckBox jCheckBox1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTabbedPane jTabbedPane1;
	private javax.swing.JTextField jTextField1;
	// End of variables declaration//GEN-END:variables

}