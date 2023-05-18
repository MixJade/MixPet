package com.forge;

import net.coobird.thumbnailator.Thumbnails;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 重新设置图片尺寸，通过拖拽输入图片
 */
public class ResizeImg extends JFrame implements ActionListener {
    public static void main(String[] args) throws IOException {
        new ResizeImg();
    }

    private final JTextArea area = new JTextArea();
    private final JTextField weightText = new JTextField(5), heightText = new JTextField(5);

    public ResizeImg() {
        area.setEditable(false);
        area.setLineWrap(true);
        weightText.setText("375");
        heightText.setText("100");
        JScrollPane jScrollPane = new JScrollPane(area);
        jScrollPane.setSize(200, 200);
        JFrame jf = new JFrame("重新设置图片尺寸");
        jf.add(jScrollPane);
        JPanel panel3 = new JPanel();
        // 创建一个按钮，点击后获取文本框中的文本
        panel3.add(new JLabel("宽"));
        panel3.add(weightText);
        panel3.add(new JLabel("高"));
        panel3.add(heightText);
        JButton btn = new JButton("转化");
        panel3.add(btn);
        jf.add(panel3);
        new DropTarget(area, DnDConstants.ACTION_COPY_OR_MOVE, dropTargetAdapter());
        jf.setSize(500, 350);
        jf.setLocationRelativeTo(null);
        jf.setLayout(new GridLayout(4, 10));
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setVisible(true);
        //监听按钮
        btn.addActionListener(this);
    }

    private DropTargetAdapter dropTargetAdapter() {
        return new DropTargetAdapter() {
            @Override
            public void drop(DropTargetDropEvent tde) {
                try {
                    tde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);// 接收拖拽来的数据
                    java.util.List<File> list = new ArrayList<>();
                    Object tranData = tde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                    if (tranData instanceof List<?> tranList) {
                        tranList.forEach(o -> {
                            if (o instanceof File f) list.add(f);
                        });
                    }
                    area.setText(list.get(0).getAbsolutePath());
                    tde.dropComplete(true);// 指示拖拽操作已完成
                } catch (IOException | UnsupportedFlavorException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String path = area.getText(),
                weightS = weightText.getText(),
                heightS = heightText.getText();
        if ("转化成功".equals(path) || "文件转存失败".equals(path) || path.isEmpty()) return;
        if (weightS.isBlank() || heightS.isBlank()) return;
        int weight = Integer.parseInt(weightS),
                height = Integer.parseInt(heightS);
        try {
            Thumbnails.of(path).forceSize(weight, height).toFile(path);//修改图片尺寸
        } catch (IOException ex) {
            area.setText("文件转存失败");
        }
        area.setText("转化成功");
    }
}