package GUI.AdminPanel;

//import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
//import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
//import javax.swing.LayoutStyle.ComponentPlacement;
//import java.awt.Color;
//import java.awt.Font;
//import javax.swing.KeyStroke;
//import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//import javax.swing.JButton;
import javax.swing.JOptionPane;
import GUI.LoginPanel;

public class AdminPanel extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPanel frame = new AdminPanel("123");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminPanel(String username) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(80, 80, 800, 600);
		setLocationRelativeTo(null);
		setTitle("宿舍管理系统-宿舍管理员");

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu account = new JMenu("账户");
		menuBar.add(account);

		JMenuItem updatePassword = new JMenuItem("修改密码");
		updatePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UpdatePasswordPanel(username).setVisible(true);
			}
		});
		account.add(updatePassword);

		JMenuItem change_user = new JMenuItem("切换用户");
		change_user.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginPanel().Login();
				dispose();
			}
		});
		account.add(change_user);

		JMenuItem exit = new JMenuItem("退出登陆");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		account.add(exit);

		JMenu info = new JMenu("信息");
		menuBar.add(info);

		JMenuItem dormitoryInfo = new JMenuItem("宿舍信息");
		dormitoryInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DormitoryInfoPanel().setVisible(true);
			}
		});
		info.add(dormitoryInfo);

		JMenuItem studentInfo = new JMenuItem("学生信息");
		studentInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new StudentInfoPanel().setVisible(true);
			}
		});
		info.add(studentInfo);

		JMenu moreInfo = new JMenu("更多");
		// moreInfo.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent e) {
		// // new help().setVisible(true);
		// }
		// });
		menuBar.add(moreInfo);

		JMenuItem aboutUs = new JMenuItem("关于我们");
		aboutUs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"宿舍信息管理系统\nProd.By 赵正 陈寅瑞 寸恩超 白子名 张宇\n大连理工大学开发区校区 地址：大连经济技术开发区图强街321号 邮编：116600", "关于我们",
						JOptionPane.INFORMATION_MESSAGE);
				// new help().setVisible(true);
			}
		});
		moreInfo.add(aboutUs);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING).addGap(0, 775, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING))
						.addContainerGap(54, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);
	}
}
