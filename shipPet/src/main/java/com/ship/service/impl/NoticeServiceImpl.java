package com.ship.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ship.common.PhotoEnum;
import com.ship.dto.NoticeDto;
import com.ship.entity.Notice;
import com.ship.mapper.NoticeMapper;
import com.ship.service.INoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
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
    private final String basePath, noticePath, chatImg;

    public NoticeServiceImpl(@Value("${pet-ship.images-path}") String basePath,
                             @Value("${pet-ship.notice-path}") String noticePath,
                             @Value("${pet-ship.chatImg-path}") String chatImg) {
        this.basePath = basePath;
        this.noticePath = noticePath;
        this.chatImg = chatImg;
    }


    @Override
    public boolean delImg() {
        // 数据库中存储的头像照片名字+默认的照片名
        List<String> avatarList = baseMapper.selectImgAvatar();
        avatarList.addAll(myConst());
        // 去重
        Set<String> set = new HashSet<>(avatarList);
        // 文件夹下的文件名
        String[] list01 = new File(basePath).list();
        String[] list02 = new File(noticePath).list();
        String[] list03 = new File(chatImg).list();
        // 图片文件夹去掉数据库中的，就是冗余的图片
        var moreImg = new ArrayList<>(Arrays.asList(Objects.requireNonNull(list01)));
        moreImg.removeAll(set);
        // 公告文件夹下的冗余文件
        List<String> noticeFile = baseMapper.selectNoticeFile();
        var moreNotice = new ArrayList<>(Arrays.asList(Objects.requireNonNull(list02)));
        moreNotice.removeAll(noticeFile);
        // 聊天文件夹下的冗余图片
        List<String> imgChat = baseMapper.selectImgChat();
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
        return this.lambdaUpdate()
                .eq(Notice::getNoticeId, noticeId)
                .set(Notice::getIsDel, delDate)
                .update();
    }

    @Override
    public boolean deleteByIds(List<Long> idGroup) {
        String delDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return this.lambdaUpdate()
                .in(Notice::getNoticeId, idGroup)
                .set(Notice::getIsDel, delDate)
                .update();
    }

    @Override
    public IPage<NoticeDto> selectByPage(String noticeName, int numPage, int pageSize) {
        IPage<NoticeDto> noticePage = baseMapper.selectNoticePage(new Page<>(numPage, pageSize), noticeName);
        noticePage.getRecords().forEach(noticeDto -> noticeDto.setTextNotice(getText(noticeDto.getNoticeFile())));
        return noticePage;
    }

    @Override
    public List<NoticeDto> selectFour() {
        List<NoticeDto> noticeList = baseMapper.selectFour();
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
        return baseMapper.addNotice(creatId, noticeTitle, path);
    }

    @Override
    public boolean updateNotice(NoticeDto noticeDto) {
        String text = noticeDto.getTextNotice();
        String path = noticeDto.getNoticeFile();
        writeText(text, path);
        String noticeTitle = noticeDto.getNoticeTitle();
        Long updateId = noticeDto.getUpdateId();
        long noticeId = noticeDto.getNoticeId();
        return baseMapper.updateNotice(updateId, noticeTitle, noticeId);
    }

    @Override
    public boolean disableNotice(long noticeId, boolean isDis) {
        return baseMapper.disableNotice(noticeId, !isDis);
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
            return null;
        }
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
     * 通过枚举批量获取默认照片名称
     *
     * @return 设置的默认照片名称
     */
    private List<String> myConst() {
        List<String> defList = new ArrayList<>();
        PhotoEnum[] photoEnums = PhotoEnum.values();
        for (PhotoEnum photoEnum : photoEnums) {
            defList.add(photoEnum.getPhotoName());
        }
        return defList;
    }
}
