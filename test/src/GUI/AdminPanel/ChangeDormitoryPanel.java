package GUI.AdminPanel;

//import java.awt.BorderLayout;
//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
//import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import java.awt.event.WindowEvent;

import Dormitory.Admin;
import Dormitory.SystemAdmin;

public class ChangeDormitoryPanel extends JFrame {

	private JPanel contentPane = null;
	private JTextField sno;
	private JTextField building;
	private JTextField room;
	private JTextField bed;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private Admin admin = new Admin();
	private SystemAdmin systemAdmin = new SystemAdmin();

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// ChangeDormitory frame = new ChangeDormitory();
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

	public ChangeDormitoryPanel getSelf() {
		return this;
	}

	public ChangeDormitoryPanel(StudentInfoPanel sip) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(80, 80, 450, 300);
		setLocationRelativeTo(null);
		setTitle("学生换寝-宿舍管理员");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		sno = new JTextField();
		sno.setColumns(10);

		building = new JTextField();
		building.setColumns(10);

		room = new JTextField();
		room.setColumns(10);

		bed = new JTextField();
		bed.setColumns(10);

		JButton change = new JButton("更换");
		change.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (sno.getText().equals("") || building.getText().equals("") || room.getText().equals("")
						|| bed.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "请继续输入", "消息提示", JOptionPane.WARNING_MESSAGE);
				} else if (sno.getText().equals("") || building.getText().equals("") || room.getText().equals("")
						|| bed.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "请继续输入", "消息提示", JOptionPane.WARNING_MESSAGE);
				} else if (systemAdmin.searchDormitory(building.getText(), room.getText()) == null) {
					JOptionPane.showMessageDialog(null, "该寝室不存在,请重新输入", "消息提示", JOptionPane.WARNING_MESSAGE);
					building.setText("");
					room.setText("");
				} else if (bed.getText().equals("1") == false && bed.getText().equals("2") == false
						&& bed.getText().equals("3") == false && bed.getText().equals("4") == false) {
					JOptionPane.showMessageDialog(null, "床号输入错误,请重新输入", "消息提示", JOptionPane.WARNING_MESSAGE);
					bed.setText("");
				} else if (admin.changeDormitory(sno.getText(), building.getText(), room.getText(), bed.getText())) {
					JOptionPane.showMessageDialog(null, "更换成功", "消息提示", JOptionPane.WARNING_MESSAGE);
					setVisible(false);
					// getSelf().dispose();
					sip.showTable();
				} else {
					JOptionPane.showMessageDialog(null, "更换失败,请重新输入", "消息提示", JOptionPane.WARNING_MESSAGE);
					sno.setText("");
					building.setText("");
					room.setText("");
					bed.setText("");
				}
				// getSelf().dispose();
			}
		});

		JLabel lblNewLabel = new JLabel("学号");

		lblNewLabel_1 = new JLabel("楼栋号");

		lblNewLabel_2 = new JLabel("宿舍号");

		lblNewLabel_3 = new JLabel("床号");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup().addGap(99)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel).addComponent(lblNewLabel_2).addComponent(lblNewLabel_3))
						.addGap(15)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false).addComponent(bed)
								.addComponent(room).addComponent(building)
								.addComponent(sno, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
						.addContainerGap(94, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup().addGap(176)
						.addComponent(change, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(173)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(sno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(building, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(room, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(bed, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_3))
						.addGap(59).addComponent(change).addContainerGap(29, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);
	}
}
