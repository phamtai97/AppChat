/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajpham.appchatclient.gui.clientgui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

/**
 *
 * @author taipham
 */
public class HandleAvatar {

    private JFileChooser _fileChooser;
    private String _pathfileAvatar;

    public HandleAvatar() {
        _fileChooser = new JFileChooser();
        _fileChooser.setDialogTitle("Please select a photo");
        _fileChooser.setMultiSelectionEnabled(false);
        _fileChooser.setFileFilter(new FileTypeFilter(".jpg", "JPG"));
        _fileChooser.setFileFilter(new FileTypeFilter(".gif", "GIF"));
        _fileChooser.setFileFilter(new FileTypeFilter(".png", "PNG"));
        _fileChooser.setFileFilter(new FileTypeFilter(".JPG", "JPG"));
        _fileChooser.setFileFilter(new FileTypeFilter(".GIF", "GIF"));
        _fileChooser.setFileFilter(new FileTypeFilter(".PNG", "PNG"));
    }

    public String chooseAvatar() {
        int result = _fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            _pathfileAvatar = _fileChooser.getSelectedFile().getAbsolutePath();
        } else {
            _pathfileAvatar = "";
        }
        return _pathfileAvatar;
    }

    //đổi file ảnh thành byte
    public byte[] convertFile(String path) throws IOException {
        try {
            if (path.length() == 0) {
                return null;
            }
            File file = new File(path);
            FileInputStream fileInput = new FileInputStream(file);
            byte[] bFile = new byte[(int) file.length()];
            fileInput.read(bFile);
            fileInput.close();
            return bFile;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
