package GUI.SystemAdminPanel;

//import java.awt.BorderLayout;
//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//import Dormitory.Admin;
import Dormitory.SystemAdmin;

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

public class DeleteAdminPanel extends JFrame {

        private JPanel contentPane;
        private JTextField adminUsername;
        private JTextField repeatAdminUsername;
        private SystemAdmin systemAdmin = new SystemAdmin();

        /**
         * Launch the application.
         */
        // public static void main(String[] args) {
        // EventQueue.invokeLater(new Runnable() {
        // public void run() {
        // try {
        // updatePasswordPanel frame = new updatePasswordPanel();
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
        public DeleteAdminPanel(AdminInfoPanel aip) {
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setBounds(80, 80, 450, 300);
                setLocationRelativeTo(null);
                setTitle("宿管注销-系统管理员");
                contentPane = new JPanel();
                contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
                setContentPane(contentPane);

                adminUsername = new JTextField();
                adminUsername.setColumns(10);

                repeatAdminUsername = new JTextField();
                repeatAdminUsername.setColumns(10);

                JLabel label = new JLabel("宿管账号");

                JLabel label_2 = new JLabel("再次确认");

                JButton delete = new JButton("注销");
                delete.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                if (adminUsername.getText().equals("") || repeatAdminUsername.getText().equals("")) {
                                        JOptionPane.showMessageDialog(null, "请继续输入", "消息提示",
                                                        JOptionPane.WARNING_MESSAGE);
                                } else if (systemAdmin.searchAdmin(adminUsername.getText()) == null) {
                                        JOptionPane.showMessageDialog(null, "该用户不存在,请重新输入", "消息提示",
                                                        JOptionPane.WARNING_MESSAGE);
                                        adminUsername.setText("");
                                        repeatAdminUsername.setText("");
                                } else if (adminUsername.getText().equals(repeatAdminUsername.getText()) == false) {
                                        JOptionPane.showMessageDialog(null, "两次用户名不一致,请重新输入", "消息提示",
                                                        JOptionPane.WARNING_MESSAGE);
                                        adminUsername.setText("");
                                        repeatAdminUsername.setText("");
                                } else {
                                        systemAdmin.deleteAdmin(adminUsername.getText());
                                        JOptionPane.showMessageDialog(null, "注销成功", "消息提示",
                                                        JOptionPane.WARNING_MESSAGE);
                                        setVisible(false);
                                        aip.showTable();
                                }
                        }
                });

                GroupLayout gl_contentPane = new GroupLayout(contentPane);
                gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                .addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
                                                .createParallelGroup(Alignment.LEADING)
                                                .addGroup(gl_contentPane.createSequentialGroup().addGap(171)
                                                                .addComponent(delete))
                                                .addGroup(gl_contentPane.createSequentialGroup().addGap(83)
                                                                .addGroup(gl_contentPane
                                                                                .createParallelGroup(Alignment.TRAILING)
                                                                                .addComponent(label)
                                                                                .addComponent(label_2))
                                                                .addGap(18)
                                                                .addGroup(gl_contentPane
                                                                                .createParallelGroup(Alignment.LEADING,
                                                                                                false)
                                                                                .addComponent(repeatAdminUsername)
                                                                                .addComponent(adminUsername,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                154, Short.MAX_VALUE))))
                                                .addContainerGap(91, Short.MAX_VALUE)));
                gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                .addGroup(gl_contentPane.createSequentialGroup().addGap(40).addGroup(gl_contentPane
                                                .createParallelGroup(Alignment.TRAILING).addComponent(label)
                                                .addComponent(adminUsername, GroupLayout.PREFERRED_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGap(26)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                                                .addComponent(label_2).addComponent(repeatAdminUsername,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE))
                                                .addGap(44).addComponent(delete).addContainerGap(47, Short.MAX_VALUE)));
                contentPane.setLayout(gl_contentPane);
        }
}
