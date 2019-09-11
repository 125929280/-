package GUI.AdminPanel;

//import java.awt.BorderLayout;
//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Dormitory.Admin;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
//import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//import javax.swing.SwingConstants;
import javax.swing.JOptionPane;

public class SearchStudentPanel extends JFrame {

	private JPanel contentPane;
	private JTextField sno;
	private Admin admin = new Admin();
	// private SystemAdmin systemAdmin = new SystemAdmin();

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// SearchStudentPanel frame = new SearchStudentPanel();
	// frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the frame.
	 */
	public SearchStudentPanel(StudentInfoPanel sip) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(80, 80, 450, 300);
		setLocationRelativeTo(null);
		setTitle("查询学生-宿舍管理员");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		sno = new JTextField();
		sno.setColumns(10);

		JLabel label = new JLabel("学生学号");

		JButton search = new JButton("查询");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (sno.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "请继续输入", "消息提示", JOptionPane.WARNING_MESSAGE);
				} else if (admin.searchStudent(sno.getText()) == null) {
					JOptionPane.showMessageDialog(null, "该学生不存在,请重新输入", "消息提示", JOptionPane.WARNING_MESSAGE);
					sno.setText("");
				} else {
					//
					// setVisible(false);
					sip.showTable(admin.searchStudent(sno.getText()));
					sip.change();
					dispose();
				}
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(171).addComponent(search))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(83).addComponent(label).addGap(18)
								.addComponent(sno, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(91, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(81)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(label).addComponent(sno,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(56).addComponent(search).addContainerGap(41, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);
	}
}
