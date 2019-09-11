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

import Dormitory.Admin;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.ResultSet;

public class DormitoryInfoPanel extends JFrame {

	private static JPanel contentPane = new JPanel();
	private static JTable table = new JTable();
	private static JScrollPane scrollPane = new JScrollPane();
	private static JButton searchDormitory = null;
	private static JButton back = null;
	private static GroupLayout gl_contentPane = new GroupLayout(contentPane);

	private static Admin admin = new Admin();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DormitoryInfoPanel frame = new DormitoryInfoPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DormitoryInfoPanel getSelf() {
		return this;
	}

	/**
	 * Create the frame.
	 */
	public DormitoryInfoPanel() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(80, 80, 800, 600);
		setLocationRelativeTo(null);
		setTitle("宿舍信息-宿舍管理员");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton finishRepair = new JButton("处理报修");
		finishRepair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new FinishRepairPanel(getSelf()).setVisible(true);
			}
		});

		JButton updateScore = new JButton("更新得分");
		updateScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UpdateScorePanel(getSelf()).setVisible(true);
			}
		});

		searchDormitory = new JButton("查找宿舍成员");
		searchDormitory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SearchDormitoryPanel(getSelf()).setVisible(true);
			}
		});

		showTable();

		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 356, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(updateScore, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
						.addComponent(finishRepair, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
						.addComponent(searchDormitory, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE))
				.addGap(97)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addContainerGap().addComponent(scrollPane,
								GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(75)
								.addComponent(finishRepair, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
								.addGap(61)
								.addComponent(updateScore, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
								.addGap(60).addComponent(searchDormitory, GroupLayout.PREFERRED_SIZE, 80,
										GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));

		contentPane.setLayout(gl_contentPane);
	}

	public static void showTable() {
		DefaultTableModel model = new DefaultTableModel(new Object[][] {}, new String[] { "\u697C\u680B\u53F7",
				"\u5BBF\u820D\u53F7", "\u536B\u751F\u5F97\u5206", "\u62A5\u4FEE\u60C5\u51B5" });

		ResultSet rs = admin.searchAllDormitory();
		try {
			while (rs.next()) {
				String[] data = new String[4];
				for (int i = 1; i <= 4; i++) {
					data[i - 1] = rs.getString(i);
				}
				model.addRow(data);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		table.setModel(model);
		scrollPane.setViewportView(table);
	}

	public static void showTable(ResultSet rs) {
		table = new JTable();
		DefaultTableModel studentModel = new DefaultTableModel(new Object[][] {}, new String[] { "\u5B66\u53F7",
				"\u59D3\u540D", "\u5E8A\u53F7", "\u4E13\u4E1A", "\u5165\u4F4F\u65E5\u671F" });

		scrollPane.setViewportView(table);
		// rs = student.searchRoommate(sno);
		try {
			while (rs.next()) {
				String[] data = new String[5];
				for (int i = 1; i <= 5; i++) {
					data[i - 1] = rs.getString(i);
				}
				studentModel.addRow(data);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		table.setModel(studentModel);
	}

	public void change() {
		// finishRepair.setVisible(false);
		// updateScore.setVisible(false);
		// searchDormitory.setVisible(false);
		// JButton back = new JButton("返回");
		// back.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent x) {
		// showTable();
		// finishRepair.setVisible(true);
		// updateScore.setVisible(true);
		// searchDormitory.setVisible(true);
		// back.setVisible(false);
		// }
		// });
		searchDormitory.setVisible(false);
		back = new JButton("返回");

		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// new SearchDormitoryPanel(getSelf()).setVisible(true);
				showTable();
				recover();
			}
		});
		back.setVisible(true);
		// GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(
				Alignment.TRAILING, gl_contentPane.createSequentialGroup().addContainerGap(101, Short.MAX_VALUE)
						.addComponent(back, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE).addGap(97)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(356)
						.addComponent(back, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE).addGap(238)));
	}

	public void recover() {
		back.setVisible(false);
		searchDormitory.setVisible(true);
	}

}
