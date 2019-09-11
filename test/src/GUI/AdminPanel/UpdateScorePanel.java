package GUI.AdminPanel;

//import java.awt.BorderLayout;
//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Dormitory.SystemAdmin;
import Dormitory.Admin;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//import javax.swing.SwingConstants;
import javax.swing.JOptionPane;

public class UpdateScorePanel extends JFrame {

        private JPanel contentPane;
        private JTextField building;
        private JTextField room;
        private JTextField score;
        private Admin admin = new Admin();
        private SystemAdmin systemAdmin = new SystemAdmin();

        /**
         * Launch the application.
         */
        // public static void main(String[] args) {
        // EventQueue.invokeLater(new Runnable() {
        // public void run() {
        // try {
        // UpdateScorePanel frame = new UpdateScorePanel();
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
        public UpdateScorePanel(DormitoryInfoPanel dip) {
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setBounds(80, 80, 450, 300);
                setLocationRelativeTo(null);
                setTitle("更新得分-宿舍管理员");
                contentPane = new JPanel();
                contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
                setContentPane(contentPane);

                building = new JTextField();
                building.setColumns(10);

                room = new JTextField();
                room.setColumns(10);

                JLabel label = new JLabel("楼栋号");

                JLabel label_2 = new JLabel("宿舍号");

                JLabel label_1 = new JLabel("新得分");

                score = new JTextField();
                score.setColumns(10);

                JButton update = new JButton("更新");
                update.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                if (building.getText().equals("") || room.getText().equals("")
                                                || score.getText().equals("")) {
                                        JOptionPane.showMessageDialog(null, "请继续输入", "消息提示",
                                                        JOptionPane.WARNING_MESSAGE);
                                } else if (systemAdmin.searchDormitory(building.getText(), room.getText()) == null) {
                                        JOptionPane.showMessageDialog(null, "该宿舍不存在,请重新输入", "消息提示",
                                                        JOptionPane.WARNING_MESSAGE);
                                        building.setText("");
                                        room.setText("");
                                } else {
                                        if (admin.updateScore(building.getText(), room.getText(), score.getText()))
                                                JOptionPane.showMessageDialog(null, "更新成功", "消息提示",
                                                                JOptionPane.WARNING_MESSAGE);
                                        else
                                                JOptionPane.showMessageDialog(null, "请输入一个值为0~10的整数", "更新失败",
                                                                JOptionPane.WARNING_MESSAGE);
                                        setVisible(false);
                                        dip.showTable();
                                }
                        }
                });

                GroupLayout gl_contentPane = new GroupLayout(contentPane);
                gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                .addGroup(gl_contentPane.createSequentialGroup().addGap(94)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                                                .addComponent(label_2).addComponent(label_1)
                                                                .addComponent(label))
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
                                                                .addComponent(score).addComponent(room)
                                                                .addComponent(building, GroupLayout.DEFAULT_SIZE, 146,
                                                                                Short.MAX_VALUE))
                                                .addGap(109))
                                .addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup().addGap(172)
                                                .addComponent(update).addContainerGap(177, Short.MAX_VALUE)));
                gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                .addGroup(gl_contentPane.createSequentialGroup().addGap(28).addGroup(gl_contentPane
                                                .createParallelGroup(Alignment.TRAILING)
                                                .addComponent(building, GroupLayout.PREFERRED_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(label)).addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                                                .addComponent(room, GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(label_2))
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                                                .addComponent(score, GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(label_1))
                                                .addGap(30).addComponent(update).addContainerGap(48, Short.MAX_VALUE)));
                contentPane.setLayout(gl_contentPane);
        }
}
