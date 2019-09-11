package GUI.SystemAdminPanel;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI.LoginPanel;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

public class SystemAdminPanel extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SystemAdminPanel frame = new SystemAdminPanel();
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
    public SystemAdminPanel() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(80, 80, 800, 600);
        setLocationRelativeTo(null);
        setTitle("宿舍管理系统-系统管理员");
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu account = new JMenu("账户");
        menuBar.add(account);

        JMenuItem updatePassword = new JMenuItem("修改密码");
        updatePassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new UpdatePasswordPanel().setVisible(true);
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

        JMenuItem logout = new JMenuItem("退出");
        logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        account.add(logout);

        JMenu info = new JMenu("信息");
        menuBar.add(info);

        JMenuItem adminInfo = new JMenuItem("宿管信息");
        adminInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AdminInfoPanel().setVisible(true);
            }
        });
        info.add(adminInfo);

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
        menuBar.add(moreInfo);

        JMenuItem aboutUs = new JMenuItem("关于我们");
        aboutUs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "宿舍信息管理系统\nProd.By 赵正 陈寅瑞 寸恩超 白子名 张宇\n大连理工大学开发区校区 地址：大连经济技术开发区图强街321号 邮编：116600", "关于我们",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        moreInfo.add(aboutUs);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
    }

}
