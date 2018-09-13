/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajpham.appchatclient.gui.clientgui;

import com.ajpham.appchatclient.bus.UserBUS;
import com.ajpham.appchatclient.dto.UserDTO;
import com.ajpham.appchatclient.nettyclient.NettyClient;
import com.ajpham.appchatclient.service.ServiceUser;
import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author taipham
 */
public class LoginFormGUI extends javax.swing.JFrame {

    /**
     * Creates new form LoginGUi
     */
    private UserBUS _userBUS;
    private String _userName = "";
    private String _password = "";
    private NettyClient _nettyClient;
    private static ServiceUser _serviceUser;
    final static private String HOST = "localhost";
    final static private int PORT = 9999;

    public LoginFormGUI() throws IOException {
        initComponents();
        this.setLocationRelativeTo(null);
        this.init();

    }

    public LoginFormGUI(NettyClient nettyClient) {
        initComponents();
        this.setLocationRelativeTo(null);
        this._nettyClient = nettyClient;
        this._userBUS = new UserBUS();
        this.lbNotify.setVisible(false);
        _nettyClient.getHandler().setLoginFormGUI(this);
        _serviceUser = new ServiceUser(_nettyClient.getHandler());
        _nettyClient.getHandler().setServiceUser(_serviceUser);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbUsername = new javax.swing.JLabel();
        lbPassword = new javax.swing.JLabel();
        tfUsername = new javax.swing.JTextField();
        tfPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        lbCreateAccount = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbNotify = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lbUsername.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lbUsername.setForeground(new java.awt.Color(51, 51, 51));
        lbUsername.setText("Username:");

        lbPassword.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lbPassword.setForeground(new java.awt.Color(0, 0, 0));
        lbPassword.setText("Password:");

        tfUsername.setBackground(new java.awt.Color(255, 255, 255));
        tfUsername.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        tfUsername.setForeground(new java.awt.Color(0, 0, 0));
        tfUsername.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfUsernameKeyPressed(evt);
            }
        });

        tfPassword.setBackground(new java.awt.Color(255, 255, 255));
        tfPassword.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        tfPassword.setForeground(new java.awt.Color(0, 0, 0));
        tfPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tfPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfPasswordKeyPressed(evt);
            }
        });

        btnLogin.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnCancel.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        lbCreateAccount.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lbCreateAccount.setForeground(java.awt.Color.gray);
        lbCreateAccount.setText("Create an account ");
        lbCreateAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbCreateAccountMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbCreateAccountMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbCreateAccountMouseEntered(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(51, 204, 255));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Log in");
        jLabel1.setToolTipText("");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        lbNotify.setBackground(new java.awt.Color(255, 255, 255));
        lbNotify.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lbNotify.setForeground(new java.awt.Color(255, 51, 0));
        lbNotify.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNotify.setText("Login Failed");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbUsername)
                    .addComponent(lbPassword))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lbCreateAccount, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(btnCancel)
                            .addGap(57, 57, 57)
                            .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(tfPassword)
                        .addComponent(tfUsername, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(lbNotify))
                .addGap(100, 100, 100))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbUsername)
                    .addComponent(tfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPassword)
                    .addComponent(tfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnLogin))
                .addGap(18, 18, 18)
                .addComponent(lbCreateAccount)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbNotify)
                .addGap(11, 11, 11))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void init() throws IOException {
        _nettyClient = new NettyClient(HOST, PORT);
        this._userBUS = new UserBUS();
        this.lbNotify.setVisible(false);
        _nettyClient.start();
        _nettyClient.getHandler().setLoginFormGUI(this);
        _serviceUser = new ServiceUser(_nettyClient.getHandler());
        _nettyClient.getHandler().setServiceUser(_serviceUser);
    }

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        if (JOptionPane.showConfirmDialog(this,
                "Are you sure to close Login form?", "Really Closing?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            this._nettyClient.Shutdown();
            System.exit(0);
        }
    }//GEN-LAST:event_btnCancelActionPerformed

    private void lbCreateAccountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCreateAccountMouseClicked
        RegisterFormGUI signupGUI = new RegisterFormGUI(this._nettyClient);
        signupGUI.setVisible(true);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_lbCreateAccountMouseClicked

    private void lbCreateAccountMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCreateAccountMouseEntered
        lbCreateAccount.setForeground(Color.RED);
    }//GEN-LAST:event_lbCreateAccountMouseEntered

    private void lbCreateAccountMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCreateAccountMouseExited
        lbCreateAccount.setForeground(Color.GRAY);
    }//GEN-LAST:event_lbCreateAccountMouseExited

    public void resetLogin(boolean flag) {
        this.lbCreateAccount.setEnabled(flag);
        this.lbNotify.setVisible(!flag);
        if (!flag) {
            this.tfUsername.setText("");
            this.tfPassword.setText("");
        }
    }

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        this._userName = this.tfUsername.getText().trim();
        this._password = String.valueOf(this.tfPassword.getPassword()).trim();

        if (!this._userBUS.checkUserLogin(this._userName, this._password)) {
            this.lbNotify.setVisible(true);
        } else {
            //gui ve server;
            _serviceUser.sendMessageLogin(_userName, _password);
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void tfUsernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfUsernameKeyPressed
        if (this.tfUsername.getText().equals("")) {
            this.lbNotify.setVisible(false);
        }
    }//GEN-LAST:event_tfUsernameKeyPressed

    private void tfPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPasswordKeyPressed
        if (this.tfPassword.getPassword().length == 0) {
            this.lbNotify.setVisible(false);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this._userName = this.tfUsername.getText().trim();
            this._password = String.valueOf(this.tfPassword.getPassword()).trim();

            if (!this._userBUS.checkUserLogin(this._userName, this._password)) {
                this.lbNotify.setVisible(true);
            } else {
                //gui ve server;
                _serviceUser.sendMessageLogin(_userName, _password);
            }
        }
    }//GEN-LAST:event_tfPasswordKeyPressed

    public void setLbNotify(String notification) {
        this.lbNotify.setText(notification);
        this.lbNotify.setVisible(true);
    }

    public void initAppChat(UserDTO user, String token) {
        _nettyClient.setUserName(user.getUserName());
        AppChatGUI appChatGUI = new AppChatGUI(_nettyClient, user, token);
        appChatGUI.setVisible(true);
        this.setVisible(false);
        this.dispose();
    }

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(LoginFormGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginFormGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginFormGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginFormGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new LoginFormGUI().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(LoginFormGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbCreateAccount;
    private javax.swing.JLabel lbNotify;
    private javax.swing.JLabel lbPassword;
    private javax.swing.JLabel lbUsername;
    private javax.swing.JPasswordField tfPassword;
    private javax.swing.JTextField tfUsername;
    // End of variables declaration//GEN-END:variables
}