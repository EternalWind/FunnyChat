/*
 * registerPanel.java
 *
 * Created on __DATE__, __TIME__
 */

/**
 *
 * @author  __USER__
 */
public class registerPanel extends java.awt.Panel {

	private Client client;

	/** Creates new form registerPanel */
	public registerPanel(Client _client) {
		client = _client;
		initComponents();
	}

//GEN-BEGIN:initComponents
// <editor-fold defaultstate="collapsed" desc="Generated Code">
private void initComponents() {

label1 = new java.awt.Label();
label2 = new java.awt.Label();
label3 = new java.awt.Label();
label4 = new java.awt.Label();
label5 = new java.awt.Label();
textField1 = new java.awt.TextField();
textField2 = new java.awt.TextField();
textField3 = new java.awt.TextField();
textField4 = new java.awt.TextField();
textField5 = new java.awt.TextField();
button1 = new java.awt.Button();
label6 = new java.awt.Label();
label7 = new java.awt.Label();
button2 = new java.awt.Button();

setBackground(java.awt.SystemColor.activeCaption);

label1.setText("\u7528\u6237\u540d\uff1a");

label2.setText("\u5bc6\u7801\u8f93\u5165\uff1a");

label3.setText("\u5bc6\u7801\u786e\u8ba4\uff1a");

label4.setText("\u5bc6\u7801\u67e5\u8be2\u95ee\u9898\uff1a");

label5.setText("\u5bc6\u7801\u67e5\u8be2\u7b54\u6848\uff1a");

button1.setActionCommand("button1");
button1.setLabel("\u63d0\u4ea4");
button1.addMouseListener(new java.awt.event.MouseAdapter() {
public void mouseClicked(java.awt.event.MouseEvent evt) {
button1MouseClicked(evt);
}
});
button1.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
button1ActionPerformed(evt);
}
});

label6.setFont(new java.awt.Font("Dialog", 1, 12));
label6.setText("\u6ce8\u518c\u4e2d\u5fc3");

label7.setForeground(new java.awt.Color(255, 0, 0));

button2.setActionCommand("button1");
button2.setLabel("\u9000\u51fa");
button2.addMouseListener(new java.awt.event.MouseAdapter() {
public void mouseClicked(java.awt.event.MouseEvent evt) {
button2MouseClicked(evt);
}
});
button2.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
button2ActionPerformed(evt);
}
});

javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
this.setLayout(layout);
layout.setHorizontalGroup(
layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(layout.createSequentialGroup()
.addGap(30, 30, 30)
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
.addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
.addGroup(layout.createSequentialGroup()
.addGap(7, 7, 7)
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
.addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(layout.createSequentialGroup()
.addGap(10, 10, 10)
.addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addComponent(textField5, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
.addComponent(textField4, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
.addComponent(textField3, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
.addComponent(textField2, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
.addComponent(textField1, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)))
.addGap(45, 45, 45))
.addGroup(layout.createSequentialGroup()
.addGap(206, 206, 206)
.addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
.addContainerGap(69, Short.MAX_VALUE))
);
layout.setVerticalGroup(
layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
.addContainerGap()
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
.addGroup(layout.createSequentialGroup()
.addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
.addGap(21, 21, 21)
.addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
.addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
.addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
.addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(textField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
.addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(textField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(textField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
.addComponent(label7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
.addComponent(button2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
.addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
.addContainerGap(26, Short.MAX_VALUE))
);

button1.getAccessibleContext().setAccessibleName("\u63d0\u4ea4");
}// </editor-fold>
//GEN-END:initComponents
private void button2ActionPerformed(java.awt.event.ActionEvent evt) {
// TODO add your handling code here:
}



private void button2MouseClicked(java.awt.event.MouseEvent evt) {
// TODO add your handling code here:
	client.gotoLoginPanel();
}


	private void button1MouseClicked(java.awt.event.MouseEvent evt) {
		if (textField1.getText().equals("") || textField2.getText().equals("")
				|| textField3.getText().equals("")
				|| textField4.getText().equals("")
				|| textField5.getText().equals("")) {
			label7.setText("信息填写不完全！");
		} else {
			if (textField2.getText().equals(textField3.getText())) {
				label7.setText("");
				//client.register(textField1.getText(), textField2.getText(), textField4.getText(), textField5.getText());
			} else {
				label7.setText("密码不一致!");
			}
		}
		// TODO add your handling code here:
	}

	private void button1ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

//GEN-BEGIN:variables
// Variables declaration - do not modify
private java.awt.Button button1;
private java.awt.Button button2;
private java.awt.Label label1;
private java.awt.Label label2;
private java.awt.Label label3;
private java.awt.Label label4;
private java.awt.Label label5;
private java.awt.Label label6;
private java.awt.Label label7;
private java.awt.TextField textField1;
private java.awt.TextField textField2;
private java.awt.TextField textField3;
private java.awt.TextField textField4;
private java.awt.TextField textField5;
// End of variables declaration//GEN-END:variables

}