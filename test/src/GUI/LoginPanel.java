package GUI;

import Dormitory.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import GUI.*;
import GUI.AdminPanel.AdminPanel;
import GUI.SystemAdminPanel.*;
import GUI.StudentPanel.*;

public class LoginPanel extends JFrame implements ActionListener {// 继承界面+事件监听器
	// MyJPanel mp;
	String username, password, login, quit;
	private JLabel title;
	private JPanel jContentPane = null;
	private JButton button1 = null;// 登录按键
	private JButton button2 = null;// 退出按键
	private JTextField jTextField = null;// 用户名文本区
	private JTextField jPasswordField = null;// 密码文本区
	static int storeUserId;// 登录用户名
	public static String storeUserName = null;// 登录用户名
	public static String storeUserPassword = null;// 登录密码
	static boolean RELOAD = true;// 重新登陆标记
	private JLabel jLabel_userName = null;
	private JLabel jLabel_password = null;
	private JLabel jLabel1 = null;// 用户名
	private JLabel jLabel2 = null;// 密码

	private static Admin admin = new Admin();
	private static Student student = new Student();
	private static SystemAdmin systemAdmin = new SystemAdmin();

	// int index;
	// ImageIcon[] imgs = { // 把要显示的图片放这
	// new ImageIcon("src/images/1.jpg"), new ImageIcon("src/images/2.jpg"), };

	public void Login() {// 登录界面
		// 可以循环显示图片的小东西
		// mp = new MyJPanel();
		// mp.setBounds(0, 0, 1280, 400);// 图片大小
		// this.add(mp);// 把当前元素加入到窗口中
		// Timer timer = new Timer(5000, new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// mp.repaint();
		// }
		// });
		// timer.start();// 计时器，每5秒刷新一次图片

		this.setResizable(false);// 不可改变窗口大小
		this.setSize(800, 600);
		this.setTitle("宿舍信息管理系统");
		Toolkit tool = this.getToolkit();
		Image myimage = tool.getImage("src/images/logo.jpg");
		this.setIconImage(myimage);
		this.setLocationRelativeTo(null);
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");// 设置UI外观
		} catch (Exception e) {
			e.printStackTrace();
		}

		title = new JLabel("欢迎登陆宿舍信息管理系统!");
		Font font = new Font("楷体", Font.PLAIN, 30);
		title.setFont(font);
		title.setBounds(200, 60, 650, 50);
		this.add(title);

		button1 = new JButton();
		button1.setBounds(new Rectangle(290, 350, 75, 25));
		button1.setText("登录");
		/*
		 * ButtonUI bui1 =new ButtonUI() {
		 * 
		 * @Override public void paint(Graphics g, JComponent c) { super.paint(g, c); }
		 * }; button1.setUI(bui1);
		 */
		// getRootPane().setDefaultButton(button1);//默认按键

		button2 = new JButton();
		/*
		 * ButtonUI bui2 =new ButtonUI() {
		 * 
		 * @Override public void paint(Graphics g, JComponent c) { super.paint(g, c); }
		 * }; button1.setUI(bui1);
		 */
		button2.setBounds(new Rectangle(430, 350, 75, 25));
		button2.setText("退出");

		jTextField = new JTextField(20);
		jTextField.setBounds(new Rectangle(350, 250, 160, 25));

		jPasswordField = new JPasswordField();
		jPasswordField.setBounds(new Rectangle(350, 300, 160, 25));

		Font font2 = new Font("楷体", Font.BOLD, 18);
		jLabel_userName = new JLabel();
		jLabel_userName.setBounds(new Rectangle(270, 250, 80, 30));
		jLabel_userName.setFont(font2);
		jLabel_userName.setText("用户名：");
		jLabel_password = new JLabel();
		jLabel_password.setFont(font2);
		jLabel_password.setBounds(new Rectangle(280, 300, 80, 30));
		jLabel_password.setText("密 码：");

		// jLabel1 = new JLabel();
		// Font font1 = new Font("楷体", Font.BOLD, 15);
		// jLabel1.setFont(font1);
		// jLabel1.setBounds(new Rectangle(10, 500, 650, 40));
		// jLabel1.setText("大连理工大学开发区校区 地址：大连经济技术开发区图强街321号 邮编：116600");
		// jLabel2 = new JLabel();
		// jLabel2.setFont(font1);
		// jLabel2.setBounds(new Rectangle(10, 520, 500, 40));
		// jLabel2.setText("Prod.By 赵正 陈寅瑞 寸恩超 白子名 张宇");

		// 新建jPanel面板
		jContentPane = new JPanel();
		jContentPane.setLayout(null);
		jContentPane.add(jLabel_userName, null);
		jContentPane.add(jLabel_password, null);
		jContentPane.add(button1, null);
		jContentPane.add(button2, null);
		jContentPane.add(jTextField, null);
		jContentPane.add(jPasswordField, null);
		// jContentPane.add(jLabel1, null);
		// jContentPane.add(jLabel2, null);
		this.add(jContentPane);

		jTextField.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				// 没用
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// 没用
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// 转到判断密码环节
				if (e.getKeyChar() == KeyEvent.VK_ENTER)
					judge();
			}
		});

		jPasswordField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// 没用
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// 没用
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER)
					judge();
			}
		});
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);// 关闭按钮
		this.setVisible(true);// 窗口可见
		jTextField.addActionListener(this);
		jPasswordField.addActionListener(this);
		button1.addActionListener(this);
		button2.addActionListener(this);
	}

	// class MyJPanel extends JPanel {
	// @Override
	// public void paint(Graphics g) {
	// g.drawImage(imgs[index % imgs.length].getImage(), 0, 0, this);
	// index++;
	// }
	// }

	@Override
	public void actionPerformed(ActionEvent e) {
		username = jTextField.getText();
		password = jPasswordField.getText();
		if (e.getSource() == button1) {
			judge();
		} else if (e.getSource() == button2) {// 按了退出
			System.exit(0);
		}
	}

	public void judge() {
		if (jTextField.getText().equals(""))
			JOptionPane.showMessageDialog(null, "你还没有输入用户名", "消息提示", JOptionPane.WARNING_MESSAGE);
		else {
			if (jPasswordField.getText().equals(""))
				JOptionPane.showMessageDialog(null, "你还没有输入密码", "消息提示", JOptionPane.WARNING_MESSAGE);
			else {
				username = jTextField.getText();
				password = jPasswordField.getText();
				System.out.println(username);
				System.out.println(password);
				if (systemAdmin.login(username, password)) {
					this.setVisible(false);
					new SystemAdminPanel().setVisible(true);
				} else if (admin.login(username, password)) {
					this.setVisible(false);
					new AdminPanel(username).setVisible(true);
				} else if (student.login(username, password)) {
					this.setVisible(false);
					new StudentPanel(username).setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "用户名或密码错误", "消息提示", JOptionPane.WARNING_MESSAGE);
				} /*
					 * else { setVisible(false); //Menu MM = new Menu();//TODO:调用下一级菜单 }
					 */
			}
		}
	}

	// public static void main(String[] args) {
	// new test().Login();// 调用登录菜单
	// }
}