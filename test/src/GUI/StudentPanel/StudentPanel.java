package GUI.StudentPanel;

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
import javax.swing.JButton;
//import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//import javax.swing.JScrollBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JOptionPane;

import java.sql.ResultSet;
import Dormitory.Student;
import GUI.LoginPanel;

public class StudentPanel extends JFrame {

    private JPanel contentPane;
    private static JTable studentTable;
    private static JTable dormitoryTable;
    private JTextField repair;
    private static JScrollPane scrollPane = new JScrollPane();
    private static JScrollPane scrollPane_1 = new JScrollPane();
    private static Student student = new Student();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StudentPanel frame = new StudentPanel("2");
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
    public StudentPanel(String sno) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setLocationRelativeTo(null);
        setTitle("宿舍管理系统-学生");
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu account = new JMenu("账户");
        menuBar.add(account);

        JMenuItem updatePassword = new JMenuItem("修改密码");
        updatePassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new UpdatePasswordPanel(sno).setVisible(true);
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

        JMenuItem logout = new JMenuItem("退出登陆");
        logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        account.add(logout);

        JMenu menu = new JMenu("更多");
        menuBar.add(menu);

        JMenuItem aboutUs = new JMenuItem("关于我们");
        aboutUs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                JOptionPane.showMessageDialog(null,
                        "宿舍信息管理系统\nProd.By 赵正 陈寅瑞 寸恩超 白子名 张宇\n大连理工大学开发区校区 地址：大连经济技术开发区图强街321号 邮编：116600", "关于我们",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        menu.add(aboutUs);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JButton updateRepair = new JButton("提交报修信息");
        updateRepair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (student.updateRepair(sno, repair.getText()))
                    JOptionPane.showMessageDialog(null, "报修成功", "报修", JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, "请输入'门','床','窗','桌子','椅子','厕所','灯'或'其他'", "消息提示",
                            JOptionPane.INFORMATION_MESSAGE);
                repair.setText("");
                showTable(sno);
            }
        });

        repair = new JTextField();
        repair.setFont(new Font("宋体", Font.PLAIN, 26));
        repair.setColumns(10);

        showTable(sno);
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
                .createSequentialGroup().addContainerGap()
                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE).addGap(18)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(updateRepair, GroupLayout.PREFERRED_SIZE, 199,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(repair, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)))
                        .addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 528, GroupLayout.PREFERRED_SIZE))
                .addContainerGap()));
        gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
                .createSequentialGroup().addContainerGap(27, Short.MAX_VALUE)
                .addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE).addGap(35)
                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addComponent(repair, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addGap(34)
                                .addComponent(updateRepair, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE))
                .addGap(27)));

        contentPane.setLayout(gl_contentPane);
    }

    public static void showTable(String sno) {
        dormitoryTable = new JTable();
        DefaultTableModel dormitoryModel = new DefaultTableModel(new Object[][] {}, new String[] { "\u697C\u680B\u53F7",
                "\u5BBF\u820D\u53F7", "\u536B\u751F\u5F97\u5206", "\u62A5\u4FEE\u60C5\u51B5" });
        scrollPane_1.setViewportView(dormitoryTable);

        ResultSet rs1 = student.searchDormitory(sno);
        try {
            while (rs1.next()) {
                String[] data = new String[4];
                for (int i = 1; i <= 4; i++) {
                    data[i - 1] = rs1.getString(i);
                }
                dormitoryModel.addRow(data);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        dormitoryTable.setModel(dormitoryModel);

        studentTable = new JTable();
        DefaultTableModel studentModel = new DefaultTableModel(new Object[][] {}, new String[] { "\u5B66\u53F7",
                "\u59D3\u540D", "\u5E8A\u53F7", "\u4E13\u4E1A", "\u5165\u4F4F\u65E5\u671F" });

        scrollPane.setViewportView(studentTable);
        ResultSet rs2 = student.searchRoommate(sno);
        try {
            while (rs2.next()) {
                String[] data = new String[5];
                for (int i = 1; i <= 5; i++) {
                    data[i - 1] = rs2.getString(i);
                }
                studentModel.addRow(data);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        studentTable.setModel(studentModel);
    }
}
