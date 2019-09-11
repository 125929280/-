package GUI.StudentPanel;

//import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
//import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//import javax.swing.SwingConstants;
import javax.swing.JOptionPane;

import Dormitory.Student;

public class UpdatePasswordPanel extends JFrame {

        private JPanel contentPane = null;
        private JPasswordField oldPassword = null;
        private JPasswordField newPassword = null;
        private JPasswordField repeatPassword = null;
        private Student student = new Student();

        /**
         * Launch the application.
         */
        public static void main(String[] args) {
                EventQueue.invokeLater(new Runnable() {
                        public void run() {
                                try {
                                        UpdatePasswordPanel frame = new UpdatePasswordPanel("1");
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
        public UpdatePasswordPanel(String sno) {
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setBounds(80, 80, 450, 300);
                setLocationRelativeTo(null);
                setTitle("修改密码-学生");
                contentPane = new JPanel();
                contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
                setContentPane(contentPane);

                oldPassword = new JPasswordField();
                oldPassword.setColumns(10);

                JLabel label = new JLabel("原密码");

                JLabel label_1 = new JLabel("新密码");

                JLabel label_2 = new JLabel("重复密码");

                newPassword = new JPasswordField();
                newPassword.setColumns(10);

                repeatPassword = new JPasswordField();
                repeatPassword.setColumns(10);

                JButton confirmUpdate = new JButton("确认修改");
                confirmUpdate.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                if (String.valueOf(oldPassword.getPassword()).equals("")
                                                || String.valueOf(newPassword.getPassword()).equals("")
                                                || String.valueOf(repeatPassword.getPassword()).equals("")) {
                                        JOptionPane.showMessageDialog(null, "请继续输入", "消息提示",
                                                        JOptionPane.WARNING_MESSAGE);
                                } else if (String.valueOf(oldPassword.getPassword())
                                                .equals(student.getPassword(sno)) == false) {
                                        JOptionPane.showMessageDialog(null, "原密码错误", "消息提示",
                                                        JOptionPane.WARNING_MESSAGE);
                                        oldPassword.setText("");
                                        newPassword.setText("");
                                        repeatPassword.setText("");
                                } else if (String.valueOf(newPassword.getPassword())
                                                .equals(String.valueOf(repeatPassword.getPassword())) == false) {
                                        JOptionPane.showMessageDialog(null, "两次密码不一致,请重新输入", "消息提示",
                                                        JOptionPane.WARNING_MESSAGE);
                                        oldPassword.setText("");
                                        newPassword.setText("");
                                        repeatPassword.setText("");
                                } else {
                                        student.updatePassword(sno, String.valueOf(newPassword.getPassword()));
                                        JOptionPane.showMessageDialog(null, "修改成功", "消息提示",
                                                        JOptionPane.WARNING_MESSAGE);
                                        setVisible(false);
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
                                                                                .addComponent(oldPassword)
                                                                                .addComponent(newPassword)
                                                                                .addComponent(repeatPassword,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                161, Short.MAX_VALUE)))
                                                .addGroup(gl_contentPane.createSequentialGroup().addGap(171)
                                                                .addComponent(confirmUpdate)))
                                                .addContainerGap(141, Short.MAX_VALUE)));
                gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(
                                gl_contentPane.createSequentialGroup().addContainerGap().addGroup(gl_contentPane
                                                .createParallelGroup(Alignment.TRAILING)
                                                .addComponent(oldPassword, GroupLayout.PREFERRED_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(label)).addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                                                .addComponent(newPassword, GroupLayout.PREFERRED_SIZE,
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
                                                .addGap(44).addComponent(confirmUpdate)
                                                .addContainerGap(47, Short.MAX_VALUE)));
                contentPane.setLayout(gl_contentPane);
        }
}
