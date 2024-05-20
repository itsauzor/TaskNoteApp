
package tasknoteapptest4;


import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.mindfusion.common.DateTime;
import com.mindfusion.scheduling.Calendar;
import com.mindfusion.scheduling.ThemeType;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;


public class TaskTab extends javax.swing.JFrame {
        
        
        
    private Calendar calendar;
    private Set<Set<DateTime>> assignedDateRanges = new HashSet<>();
    private Set<DateTime> clickedDates = new HashSet<>();
    private DateTime dragStartDate = null;
    private DateTime dragEndDate = null;
                
        
        public TaskTab() {
                initComponents();
                initializeCalendar();
                initializeListeners();
        }
        
        
         private void initializeListeners() {
        calendar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                DateTime clickedDate = calendar.getDateAt(e.getX(), e.getY());
                if (!isDateInAssignedRange(clickedDate)) {
                    dragStartDate = clickedDate;
                } else {
                    dragStartDate = null;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                dragEndDate = calendar.getDateAt(e.getX(), e.getY());
                if (dragStartDate != null && dragEndDate != null) {
                    updateAssignedDates();
                }
                dragStartDate = null;
                dragEndDate = null;
            }
        });

        calendar.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (dragStartDate != null) {
                    dragEndDate = calendar.getDateAt(e.getX(), e.getY());
                }
            }
        });
    }

    private boolean isDateInAssignedRange(DateTime date) {
        for (Set<DateTime> range : assignedDateRanges) {
            if (range.contains(date)) {
                return true;
            }
        }
        return false;
    }

    private void updateAssignedDates() {
        if (dragStartDate != null && dragEndDate != null) {
            Set<DateTime> dateRange = new HashSet<>();
            DateTime tempDate = dragStartDate;

            while (tempDate.compareTo(dragEndDate) <= 0) {
                if (!clickedDates.contains(tempDate)) {
                    dateRange.add(tempDate);
                    clickedDates.add(tempDate);
                }
                tempDate = tempDate.addDays(1);
            }

            if (!dateRange.isEmpty()) {
                assignedDateRanges.add(dateRange);
                numbersOfAssigned.setText(String.valueOf(assignedDateRanges.size()));
                numbersOfAssigned.repaint();  // Ensure the label is refreshed
            }
        }
    }

        
        private void initializeCalendar() {
        calendar = new Calendar();
        calendar.beginInit();
        calendar.setTheme(ThemeType.Light);
        calendar.setCurrentView(com.mindfusion.scheduling.CalendarView.SingleMonth);
        calendar.getMonthSettings().getDaySettings().setShowToday(true);
        calendar.setDate(new DateTime(DateTime.today().getYear(), DateTime.today().getMonth(), 1));
        calendar.setEndDate(new DateTime(DateTime.today().getYear(), DateTime.today().getMonth(), 28));
        calendar.setEnableDragCreate(true);
        calendar.endInit();
        
        

        calendarPanel.setLayout(new BorderLayout());
        calendarPanel.add(calendar, BorderLayout.CENTER);
        }

        public void switchToTab(int tabIndex) {
        tabs.setSelectedIndex(tabIndex);
        }
        
        
        
        public void addTaskToTable(String text) {
        DefaultTableModel model = (DefaultTableModel) taskTable.getModel();
        model.addRow(new Object[]{text});
        updateNumberOfTasks();
        }
        
        public void editTaskInTable(int rowIndex, String editedText) {
        DefaultTableModel model = (DefaultTableModel) taskTable.getModel();
        model.setValueAt(editedText, rowIndex, 0);
        
        }

        public String getTaskText(int rowIndex) {
        DefaultTableModel model = (DefaultTableModel) taskTable.getModel();
        return (String) model.getValueAt(rowIndex, 0);
        
        }

        public void updateNumberOfTasks() {
        DefaultTableModel model = (DefaultTableModel) taskTable.getModel();
        int numberOfTasks = model.getRowCount();
        numbersOfTask.setText(String.valueOf(numberOfTasks));
        
        }
        
        
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jPanel2 = new javax.swing.JPanel();
                projectBtn = new javax.swing.JButton();
                tabs = new javax.swing.JTabbedPane();
                projectpanel = new javax.swing.JPanel();
                jLabel5 = new javax.swing.JLabel();
                taskBtn = new javax.swing.JButton();
                numbersOfTask = new javax.swing.JLabel();
                numbersOfAssigned = new javax.swing.JLabel();
                calendarPanel = new javax.swing.JPanel();
                taskpanel = new javax.swing.JPanel();
                jLabel4 = new javax.swing.JLabel();
                addBtn = new javax.swing.JButton();
                taskTableScroll = new javax.swing.JScrollPane();
                taskTable = new javax.swing.JTable();
                deleteBtn = new javax.swing.JButton();
                editbtn = new javax.swing.JButton();
                jPanel1 = new javax.swing.JPanel();
                jLabel6 = new javax.swing.JLabel();
                taskTableScroll1 = new javax.swing.JScrollPane();
                taskTable1 = new javax.swing.JTable();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                jPanel2.setBackground(new java.awt.Color(255, 255, 255));
                jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                projectBtn.setText("Project");
                projectBtn.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                projectBtnActionPerformed(evt);
                        }
                });
                jPanel2.add(projectBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

                getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 630));

                tabs.setBackground(new java.awt.Color(102, 102, 102));

                projectpanel.setBackground(new java.awt.Color(255, 255, 255));
                projectpanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
                jLabel5.setForeground(new java.awt.Color(0, 0, 0));
                jLabel5.setText("Project");
                projectpanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, -1, -1));

                taskBtn.setText("task");
                taskBtn.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                taskBtnActionPerformed(evt);
                        }
                });
                projectpanel.add(taskBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 30, -1, -1));

                numbersOfTask.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                numbersOfTask.setForeground(new java.awt.Color(0, 0, 0));
                numbersOfTask.setText("0");
                projectpanel.add(numbersOfTask, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 40, -1));

                numbersOfAssigned.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                numbersOfAssigned.setForeground(new java.awt.Color(0, 0, 0));
                numbersOfAssigned.setText("0");
                projectpanel.add(numbersOfAssigned, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 130, 40, -1));

                calendarPanel.setBackground(new java.awt.Color(240, 240, 240));
                calendarPanel.addContainerListener(new java.awt.event.ContainerAdapter() {
                        public void componentAdded(java.awt.event.ContainerEvent evt) {
                                calendarPanelComponentAdded(evt);
                        }
                });

                javax.swing.GroupLayout calendarPanelLayout = new javax.swing.GroupLayout(calendarPanel);
                calendarPanel.setLayout(calendarPanelLayout);
                calendarPanelLayout.setHorizontalGroup(
                        calendarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 610, Short.MAX_VALUE)
                );
                calendarPanelLayout.setVerticalGroup(
                        calendarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 390, Short.MAX_VALUE)
                );

                projectpanel.add(calendarPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 610, 390));

                tabs.addTab("project tab", projectpanel);

                taskpanel.setBackground(new java.awt.Color(255, 255, 255));
                taskpanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
                jLabel4.setForeground(new java.awt.Color(0, 0, 0));
                jLabel4.setText("To do");
                taskpanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 18, -1, -1));

                addBtn.setText("Add");
                addBtn.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                addBtnActionPerformed(evt);
                        }
                });
                taskpanel.add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 150, -1, -1));

                taskTable.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
                taskTable.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                                ""
                        }
                ));
                taskTable.setRowHeight(40);
                taskTableScroll.setViewportView(taskTable);

                taskpanel.add(taskTableScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 510, 340));

                deleteBtn.setText("Delete");
                deleteBtn.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                deleteBtnActionPerformed(evt);
                        }
                });
                taskpanel.add(deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 150, -1, -1));

                editbtn.setText("Edit");
                editbtn.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                editbtnActionPerformed(evt);
                        }
                });
                taskpanel.add(editbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 150, -1, -1));

                tabs.addTab("to do tab", taskpanel);

                getContentPane().add(tabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, -40, 660, 670));

                jPanel1.setBackground(new java.awt.Color(255, 255, 255));

                jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
                jLabel6.setForeground(new java.awt.Color(0, 0, 0));
                jLabel6.setText("Reminder");

                taskTable1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
                taskTable1.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                                ""
                        }
                ));
                taskTable1.setRowHeight(40);
                taskTableScroll1.setViewportView(taskTable1);

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(86, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addGap(81, 81, 81))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addContainerGap(21, Short.MAX_VALUE)
                                        .addComponent(taskTableScroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(23, Short.MAX_VALUE)))
                );
                jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel6)
                                .addContainerGap(576, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addContainerGap(81, Short.MAX_VALUE)
                                        .addComponent(taskTableScroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(20, Short.MAX_VALUE)))
                );

                getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 0, 330, 630));

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        AddTask AddTaskFrame = new AddTask(this); 
        AddTaskFrame.setVisible(true);
        AddTaskFrame.pack();
        AddTaskFrame.setLocationRelativeTo(null);
        this.dispose();
        
        
        
        }//GEN-LAST:event_addBtnActionPerformed

        private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed

        DefaultTableModel model = (DefaultTableModel) taskTable.getModel();
        int selectedRow = taskTable.getSelectedRow();

        if (selectedRow != -1) {
        model.removeRow(selectedRow);
        updateNumberOfTasks();
          } else {
        JOptionPane.showMessageDialog(this, "Please select a task to delete.", "No Task Selected", JOptionPane.WARNING_MESSAGE);
         }
        }//GEN-LAST:event_deleteBtnActionPerformed


        private void editbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editbtnActionPerformed
        int selectedRow = taskTable.getSelectedRow();

        if (selectedRow != -1) {
        EditTask editTaskFrame = new EditTask(this);
        editTaskFrame.setVisible(true);
        editTaskFrame.pack();
        editTaskFrame.setLocationRelativeTo(null);
        this.dispose();
        } else {

        JOptionPane.showMessageDialog(this, "Please select a task to edit.", "No Task Selected", JOptionPane.WARNING_MESSAGE);
        
        
        }

        }//GEN-LAST:event_editbtnActionPerformed

        private void taskBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_taskBtnActionPerformed
        tabs.setSelectedIndex(1);
        
        
        }//GEN-LAST:event_taskBtnActionPerformed

        private void projectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_projectBtnActionPerformed
        tabs.setSelectedIndex(0);
        

        }//GEN-LAST:event_projectBtnActionPerformed

        private void calendarPanelComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_calendarPanelComponentAdded
        
        
        }//GEN-LAST:event_calendarPanelComponentAdded
        
        
        public static void main(String args[]) {
                /* Set the Nimbus look and feel */
                //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
                /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
                 */
                try {
                        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                                if ("Nimbus".equals(info.getName())) {
                                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                                        break;
                                }
                        }
                } catch (ClassNotFoundException ex) {
                        java.util.logging.Logger.getLogger(TaskTab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                        java.util.logging.Logger.getLogger(TaskTab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                        java.util.logging.Logger.getLogger(TaskTab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                        java.util.logging.Logger.getLogger(TaskTab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
                //</editor-fold>
                //</editor-fold>

                /* Create and display the form */
                java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                                new TaskTab().setVisible(true);
                        }
                });
                
        }



        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton addBtn;
        private javax.swing.JPanel calendarPanel;
        private javax.swing.JButton deleteBtn;
        private javax.swing.JButton editbtn;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JLabel numbersOfAssigned;
        private javax.swing.JLabel numbersOfTask;
        private javax.swing.JButton projectBtn;
        private javax.swing.JPanel projectpanel;
        private javax.swing.JTabbedPane tabs;
        private javax.swing.JButton taskBtn;
        public javax.swing.JTable taskTable;
        public javax.swing.JTable taskTable1;
        private javax.swing.JScrollPane taskTableScroll;
        private javax.swing.JScrollPane taskTableScroll1;
        private javax.swing.JPanel taskpanel;
        // End of variables declaration//GEN-END:variables
}
