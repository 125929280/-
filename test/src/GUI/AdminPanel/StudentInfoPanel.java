package GUI.AdminPanel;

//import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import Dormitory.SystemAdmin;

import javax.swing.JButton;
//import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.ResultSet;

public class StudentInfoPanel extends JFrame {

	private static JPanel contentPane = new JPanel();
	private static JTable table = new JTable();
	private static JButton searchStudent = null;
	private static JButton back = null;
	private static GroupLayout gl_contentPane = new GroupLayout(contentPane);
	private static JScrollPane scrollPane = new JScrollPane();
	private static SystemAdmin systemAdmin = new SystemAdmin();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentInfoPanel frame = new StudentInfoPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StudentInfoPanel getSelf() {
		return this;
	}

	/**
	 * Create the frame.
	 */
	public StudentInfoPanel() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(80, 80, 800, 600);
		setLocationRelativeTo(null);
		setTitle("学生信息-宿舍管理员");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton changeDormitory = new JButton("学生换寝");
		changeDormitory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ChangeDormitoryPanel(getSelf()).setVisible(true);
			}
		});

		searchStudent = new JButton("查询学生");
		searchStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SearchStudentPanel(getSelf()).setVisible(true);
			}
		});

		showTable();

		gl_contentPane
				.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
						gl_contentPane.createSequentialGroup().addContainerGap()
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE).addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(changeDormitory, GroupLayout.PREFERRED_SIZE, 199,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(searchStudent, GroupLayout.PREFERRED_SIZE, 199,
												GroupLayout.PREFERRED_SIZE))
								.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup().addGap(99)
										.addComponent(changeDormitory, GroupLayout.PREFERRED_SIZE, 80,
												GroupLayout.PREFERRED_SIZE)
										.addGap(128).addComponent(searchStudent, GroupLayout.PREFERRED_SIZE, 80,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
										.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)))
						.addContainerGap()));

		contentPane.setLayout(gl_contentPane);
	}

	public void showTable() {
		DefaultTableModel model = new DefaultTableModel(new Object[][] {},
				new String[] { "\u5B66\u53F7", "\u59D3\u540D", "\u6027\u522B", "\u697C\u680B\u53F7",
						"\u5BBF\u820D\u53F7", "\u5E8A\u53F7", "\u4E13\u4E1A", "\u5165\u4F4F\u65E5\u671F" });

		ResultSet rs = systemAdmin.searchAllStudent();
		try {
			while (rs.next()) {
				String[] data = new String[8];
				for (int i = 1; i <= 8; i++) {
					data[i - 1] = rs.getString(i);
				}
				model.addRow(data);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		table.setModel(model);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.getColumnModel().getColumn(4).setPreferredWidth(60);
		table.getColumnModel().getColumn(5).setPreferredWidth(50);
		table.getColumnModel().getColumn(5).setMinWidth(10);
		scrollPane.setViewportView(table);
	}

	public void showTable(ResultSet rs) {
		table = new JTable();
		DefaultTableModel dormitoryModel = new DefaultTableModel(new Object[][] {}, new String[] { "\u697C\u680B\u53F7",
				"\u5BBF\u820D\u53F7", "\u536B\u751F\u5F97\u5206", "\u62A5\u4FEE\u60C5\u51B5" });
		scrollPane.setViewportView(table);

		// ResultSet rs1 = student.searchDormitory(sno);
		try {
			while (rs.next()) {
				String[] data = new String[4];
				for (int i = 1; i <= 4; i++) {
					data[i - 1] = rs.getString(i);
				}
				dormitoryModel.addRow(data);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		table.setModel(dormitoryModel);
	}

	public void change() {
		searchStudent.setVisible(false);
		back = new JButton("返回");

		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// new SearchDormitoryPanel(getSelf()).setVisible(true);
				showTable();
				recover();
			}
		});
		back.setVisible(true);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap(554, Short.MAX_VALUE)
						.addComponent(back, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(307)
						.addComponent(back, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
	}

	public void recover() {
		back.setVisible(false);
		searchStudent.setVisible(true);
	}
}
