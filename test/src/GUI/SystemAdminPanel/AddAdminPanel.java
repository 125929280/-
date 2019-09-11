package GUI.SystemAdminPanel;

//import java.awt.BorderLayout;
//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
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

import Dormitory.SystemAdmin;

public class AddAdminPanel extends JFrame {

        private JPanel contentPane = null;
        private JTextField adminUsername = null;
        private JPasswordField adminPassword = null;
        private JPasswordField repeatPassword = null;
        private SystemAdmin systemAdmin = new SystemAdmin();

        /**
         * Launch the application.
         */
        // public static void main(String[] args) {
        // EventQueue.invokeLater(new Runnable() {
        // public void run() {
        // try {
        // AddAdminPanel frame = new AddAdminPanel();
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
        public AddAdminPanel(AdminInfoPanel aip) {
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setBounds(80, 80, 450, 300);
                setLocationRelativeTo(null);
                setTitle("宿管注册-系统管理员");
                contentPane = new JPanel();
                contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
                setContentPane(contentPane);

                adminUsername = new JTextField();
                adminUsername.setColumns(10);

                JLabel label = new JLabel("宿管账号");

                JLabel label_1 = new JLabel("宿管密码");

                JLabel label_2 = new JLabel("重复密码");

                adminPassword = new JPasswordField();
                adminPassword.setColumns(10);

                repeatPassword = new JPasswordField();
                repeatPassword.setColumns(10);

                JButton register = new JButton("注册");
                register.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                if (adminUsername.getText().equals("")
                                                || String.valueOf(adminPassword.getPassword()).equals("")
                                                || String.valueOf(repeatPassword.getPassword()).equals("")) {
                                        JOptionPane.showMessageDialog(null, "请继续输入", "消息提示",
                                                        JOptionPane.WARNING_MESSAGE);
                                } else if (adminUsername.getText().equals("admin")) {
                                        JOptionPane.showMessageDialog(null, "该用户名不可使用,请重新输入", "消息提示",
                                                        JOptionPane.WARNING_MESSAGE);
                                } else if (systemAdmin.searchAdmin(adminUsername.getText()) != null) {
                                        JOptionPane.showMessageDialog(null, "该用户名已存在,请重新输入", "消息提示",
                                                        JOptionPane.WARNING_MESSAGE);
                                        adminUsername.setText("");
                                        adminPassword.setText("");
                                        repeatPassword.setText("");
                                } else if (String.valueOf(adminPassword.getPassword())
                                                .equals(String.valueOf(repeatPassword.getPassword())) == false) {
                                        JOptionPane.showMessageDialog(null, "两次密码不一致,请重新输入", "消息提示",
                                                        JOptionPane.WARNING_MESSAGE);
                                        adminUsername.setText("");
                                        adminPassword.setText("");
                                        repeatPassword.setText("");
                                } else {
                                        systemAdmin.addAdmin(adminUsername.getText(),
                                                        String.valueOf(adminPassword.getPassword()));
                                        JOptionPane.showMessageDialog(null, "注册成功", "消息提示",
                                                        JOptionPane.WARNING_MESSAGE);
                                        setVisible(false);
                                        aip.showTable();
                                        // new AdminInfoPanel().setVisible(true);

                                }
                        }
                });
                GroupLayout gl_contentPane = new GroupLayout(contentPane);
                gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                .addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
                                                .createParallelGroup(Alignment.LEADING)
                                                .addGroup(gl_contentPane.createSequentialGroup().addGap(83)
                                                                .addGroup(gl_contentPane
                                                                                .createParallelGroup(Alignment.TRAILING)
                                                                                .addComponent(label_1)
                                                                                .addComponent(label)
                                                                                .addComponent(label_2))
                                                                .addGap(18)
                                                                .addGroup(gl_contentPane
                                                                                .createParallelGroup(Alignment.LEADING,
                                                                                                false)
                                                                                .addComponent(adminUsername)
                                                                                .addComponent(adminPassword)
                                                                                .addComponent(repeatPassword,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                161, Short.MAX_VALUE)))
                                                .addGroup(gl_contentPane.createSequentialGroup().addGap(171)
                                                                .addComponent(register)))
                                                .addContainerGap(141, Short.MAX_VALUE)));
                gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(
                                gl_contentPane.createSequentialGroup().addContainerGap().addGroup(gl_contentPane
                                                .createParallelGroup(Alignment.TRAILING)
                                                .addComponent(adminUsername, GroupLayout.PREFERRED_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(label)).addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                                                .addComponent(adminPassword, GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(label_1))
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                                                .addComponent(repeatPassword,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(label_2))
                                                .addGap(44).addComponent(register)
                                                .addContainerGap(47, Short.MAX_VALUE)));
                contentPane.setLayout(gl_contentPane);
        }

}
