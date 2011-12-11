/*
 * ChatPanel.java
 *
 * Created on __DATE__, __TIME__
 */

/**
 *
 * @author  __USER__
 */
public class ChatPanel extends java.awt.Panel {

	private Client client;
	private long Fid;
	/** Creates new form ChatPanel */
	public ChatPanel(Client _client, long _id) {
		client = _client;
		Fid =_id;
		initComponents();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		textField1 = new java.awt.TextField();
		textField2 = new java.awt.TextField();
		button1 = new java.awt.Button();

		setBackground(java.awt.SystemColor.activeCaption);

		textField1.setEnabled(false);
		textField1.setText("textField1");

		textField2.setText("textField1");

		button1.setLabel("\u53d1\u9001");
		button1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				button1MouseClicked(evt);
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
												.addComponent(
														textField1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														269,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														textField2,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														269,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap(174, Short.MAX_VALUE)
								.addComponent(button1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										74,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(41, 41, 41)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(textField1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										184,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(textField2,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										46,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(button1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));
	}// </editor-fold>
	//GEN-END:initComponents

	private void button1MouseClicked(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:
		textField1.setText(textField1.getText()+"\r\n"+client.getId()+"\r\n"+textField2.getText());
		client.chat(textField2.getText(), String.valueOf(Fid));
		textField2.setText("");
	}
	
	public void messageShow(String content, String senderID){
		textField1.setText(textField1.getText()+"\r\n"+senderID+"\r\n"+content);
	}
	
	public long getFid(){
		return Fid;
	}
	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private java.awt.Button button1;
	private java.awt.TextField textField1;
	private java.awt.TextField textField2;
	// End of variables declaration//GEN-END:variables

}