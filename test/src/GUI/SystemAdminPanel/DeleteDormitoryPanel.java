package GUI.SystemAdminPanel;

//import java.awt.BorderLayout;
//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

import Dormitory.SystemAdmin;
import Dormitory.Admin;

public class DeleteDormitoryPanel extends JFrame {

        private JPanel contentPane;
        private JTextField building;
        private JTextField room;
        private SystemAdmin systemAdmin = new SystemAdmin();
        private Admin admin = new Admin();

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
        public DeleteDormitoryPanel(DormitoryInfoPanel dip) {
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setBounds(80, 80, 450, 300);
                setLocationRelativeTo(null);
                setTitle("删除宿舍-系统管理员");
                contentPane = new JPanel();
                contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
                setContentPane(contentPane);

                building = new JTextField();
                building.setColumns(10);

                room = new JTextField();
                room.setColumns(10);

                JLabel label = new JLabel("楼栋号");

                JLabel label_2 = new JLabel("宿舍号");

                JButton delete = new JButton("删除");
                delete.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                if (building.getText().equals("") || room.getText().equals("")) {
                                        JOptionPane.showMessageDialog(null, "请继续输入", "消息提示",
                                                        JOptionPane.WARNING_MESSAGE);
                                } else if (systemAdmin.searchDormitory(building.getText(), room.getText()) == null) {
                                        JOptionPane.showMessageDialog(null, "该宿舍不存在,请重新输入", "消息提示",
                                                        JOptionPane.WARNING_MESSAGE);
                                        building.setText("");
                                        room.setText("");
                                } else if (systemAdmin.searchDormitoryWithStudent(building.getText(),
                                                room.getText()) != null) {
                                        JOptionPane.showMessageDialog(null, "该宿舍有学生居住,请重新输入", "消息提示",
                                                        JOptionPane.WARNING_MESSAGE);
                                        building.setText("");
                                        room.setText("");
                                } else {
                                        systemAdmin.deleteDormitory(building.getText(), room.getText());
                                        JOptionPane.showMessageDialog(null, "删除成功", "消息提示",
                                                        JOptionPane.WARNING_MESSAGE);
                                        setVisible(false);
                                        dip.showTable();
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
                                                                                .addComponent(room)
                                                                                .addComponent(building,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                154, Short.MAX_VALUE))))
                                                .addContainerGap(91, Short.MAX_VALUE)));
                gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                .addGroup(gl_contentPane.createSequentialGroup().addGap(40).addGroup(gl_contentPane
                                                .createParallelGroup(Alignment.TRAILING).addComponent(label)
                                                .addComponent(building, GroupLayout.PREFERRED_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGap(26)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                                                .addComponent(label_2).addComponent(room,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE))
                                                .addGap(44).addComponent(delete).addContainerGap(47, Short.MAX_VALUE)));
                contentPane.setLayout(gl_contentPane);
        }
}
