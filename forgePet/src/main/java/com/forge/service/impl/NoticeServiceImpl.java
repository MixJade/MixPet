package com.forge.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forge.common.PhotoConst;
import com.forge.util.PageUtil;
import com.forge.dto.NoticeDto;
import com.forge.vo.Page;
import com.forge.entity.Notice;
import com.forge.mapper.NoticeMapper;
import com.forge.service.INoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 公告表 服务实现类
 * </p>
 *
 * @author MixJade
 * @since 2023-01-02
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {
    private static final Logger log = LoggerFactory.getLogger(NoticeServiceImpl.class);
    private final NoticeMapper noticeMapper;
    private final String basePath, noticePath, chatImg;

    public NoticeServiceImpl(@Autowired NoticeMapper noticeMapper,
                             @Value("${pet-forge.images-path}") String basePath,
                             @Value("${pet-forge.notice-path}") String noticePath,
                             @Value("${pet-forge.chatImg-path}") String chatImg) {
        this.noticeMapper = noticeMapper;
        this.basePath = basePath;
        this.noticePath = noticePath;
        this.chatImg = chatImg;
    }


    @Override
    public boolean delImg() {
        // 数据库中存储的头像照片名字
        List<String> clientImg = noticeMapper.selectImgClient();
        clientImg.addAll(noticeMapper.selectImgDoctor());
        clientImg.addAll(noticeMapper.selectImgPet());
        clientImg.addAll(noticeMapper.selectImgEmployee());
        // 默认的照片名
        clientImg.addAll(myConst());
        // 去重
        Set<String> set = new HashSet<>(clientImg);
        // 文件夹下的文件名
        String[] list01 = new File(basePath).list();
        String[] list02 = new File(noticePath).list();
        String[] list03 = new File(chatImg).list();
        // 图片文件夹去掉数据库中的，就是冗余的图片
        var moreImg = new ArrayList<>(Arrays.asList(Objects.requireNonNull(list01)));
        moreImg.removeAll(set);
        // 公告文件夹下的冗余文件
        List<String> noticeFile = noticeMapper.selectNoticeFile();
        var moreNotice = new ArrayList<>(Arrays.asList(Objects.requireNonNull(list02)));
        moreNotice.removeAll(noticeFile);
        // 聊天文件夹下的冗余图片
        List<String> imgChat = noticeMapper.selectImgChat();
        var moreImgChat = new ArrayList<>(Arrays.asList(Objects.requireNonNull(list03)));
        moreImgChat.removeAll(imgChat);
        // 删除的前置，校验是否已无冗余，并设置删除参数
        if (moreImg.isEmpty() && moreNotice.isEmpty() && moreImgChat.isEmpty()) return false;
        final int[] delNum = {0, 0}; // 第一个参数为被删除的图片数，二是被删除的公告数
        // 开删:头像图片
        moreImg.forEach(fileName -> {
            if (new File(basePath + fileName).delete()) delNum[0]++;
        });
        // 开删:冗余公告
        moreNotice.forEach(fileName -> {
            if (new File(noticePath + fileName).delete()) delNum[1]++;
        });
        // 开删:聊天图片
        moreImgChat.forEach(fileName -> {
            if (new File(chatImg + fileName).delete()) delNum[0]++;
        });
        log.info("删除冗余图片{}张，冗余公告{}条", delNum[0], delNum[1]);
        return true;
    }

    @Override
    public boolean deleteById(long noticeId) {
        String delDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return noticeMapper.deleteId(delDate, noticeId);
    }

    @Override
    public boolean deleteByIds(long[] idGroup) {
        String delDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return noticeMapper.deleteIdGroup(delDate, idGroup);
    }

    @Override
    public Page<NoticeDto> selectByPage(String noticeName, int numPage, int pageSize) {
        int maxCount = noticeMapper.selectNoticeCount(noticeName);
        PageUtil pu = PageUtil.pu(numPage, pageSize, maxCount);
        List<NoticeDto> noticeList = noticeMapper.selectNoticePage(noticeName, pu);
        noticeList.forEach(noticeDto -> noticeDto.setTextNotice(getText(noticeDto.getNoticeFile())));
        return new Page<>(noticeList, maxCount);
    }

    @Override
    public List<NoticeDto> selectFour() {
        List<NoticeDto> noticeList = noticeMapper.selectFour();
        noticeList.forEach(noticeDto -> noticeDto.setTextNotice(getText(noticeDto.getNoticeFile())));
        return noticeList;
    }

    @Override
    public boolean addNotice(NoticeDto noticeDto) {
        String text = noticeDto.getTextNotice();
        String path = UUID.randomUUID() + ".txt";
        writeText(text, path);
        Long creatId = noticeDto.getCreatId();
        String noticeTitle = noticeDto.getNoticeTitle();
        return noticeMapper.addNotice(creatId, noticeTitle, path);
    }

    @Override
    public boolean updateNotice(NoticeDto noticeDto) {
        String text = noticeDto.getTextNotice();
        String path = noticeDto.getNoticeFile();
        writeText(text, path);
        String noticeTitle = noticeDto.getNoticeTitle();
        Long updateId = noticeDto.getUpdateId();
        long noticeId = noticeDto.getNoticeId();
        return noticeMapper.updateNotice(updateId, noticeTitle, noticeId);
    }

    @Override
    public boolean disableNotice(long noticeId, boolean isDis) {
        return noticeMapper.disableNotice(noticeId, !isDis);
    }

    /**
     * 获取公告文本
     *
     * @param noticeFile 公告文件名
     * @return 公告文本
     */
    private String getText(String noticeFile) {
        try {
            return Files.readString(Path.of(noticePath + noticeFile));
        } catch (IOException e) {
            log.warn("公告文本获取失败" + e);
        }
        return null;
    }

    /**
     * 将公告内容写入文本
     *
     * @param text       公告内容
     * @param noticeFile 公告文件名
     */
    private void writeText(String text, String noticeFile) {
        try (var sw = new OutputStreamWriter(new FileOutputStream(noticePath + noticeFile), StandardCharsets.UTF_8)) {
            sw.write(text);
        } catch (IOException e) {
            log.warn("公告文本写入失败" + e);
        }
    }

    /**
     * 通过反射获取接口常量值
     *
     * @return 设置的默认照片名称
     */
    private List<String> myConst() {
        List<String> defList = new ArrayList<>();
        Field[] fields = (PhotoConst.class).getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true); // 忽略权限修饰符的安全检查
                defList.add((String) field.get(String.class));
            }
        } catch (IllegalAccessException e) {
            log.warn("不安全的反射");
            throw new RuntimeException(e);
        }
        return defList;
    }
}
