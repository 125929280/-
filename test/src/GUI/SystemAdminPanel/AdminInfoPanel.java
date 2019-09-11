package GUI.SystemAdminPanel;

//import java.awt.BorderLayout;
import java.awt.EventQueue;
//import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
//import javax.management.timer.*;

import Dormitory.SystemAdmin;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.ResultSet;

public class AdminInfoPanel extends JFrame {

    private JPanel contentPane;
    private static JTable table;
    private static JScrollPane scrollPane = new JScrollPane();
    private static SystemAdmin systemAdmin = new SystemAdmin();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AdminInfoPanel frame = new AdminInfoPanel();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private AdminInfoPanel getSelf() {
        return this;
    }

    /**
     * Create the frame.
     */
    public AdminInfoPanel() {
        // Timer timer = new Timer(5000, new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent e) {
        // }
        // });
        // timer.start();// 计时器，每5秒刷新一次图片
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(80, 80, 800, 600);
        setLocationRelativeTo(null);
        setTitle("宿管信息-系统管理员");
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JButton addAdmin = new JButton("宿管注册");
        addAdmin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // AddAdminPanel sonView = new AddAdminPanel();
                new AddAdminPanel(getSelf()).setVisible(true);
            }
        });

        JButton deleteAdmin = new JButton("宿管注销");
        deleteAdmin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DeleteAdminPanel(getSelf()).setVisible(true);
                // showTable();
            }
        });

        showTable();

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 356, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                .addComponent(addAdmin, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
                                .addComponent(deleteAdmin, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE))
                        .addGap(97)));
        gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
                .createSequentialGroup()
                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup().addContainerGap().addComponent(scrollPane,
                                GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE))
                        .addGroup(gl_contentPane.createSequentialGroup().addGap(99)
                                .addComponent(addAdmin, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                .addGap(128)
                                .addComponent(deleteAdmin, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap()));

        contentPane.setLayout(gl_contentPane);
    }

    public static void showTable() {
        table = new JTable();
        DefaultTableModel model = new DefaultTableModel(new Object[][] {}, new String[] { "用户名", "密码" });

        ResultSet rs = systemAdmin.searchAllAdmin();
        try {
            while (rs.next()) {
                String[] data = new String[2];
                for (int i = 1; i <= 2; i++) {
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

    // public void run() {

    // }
}
