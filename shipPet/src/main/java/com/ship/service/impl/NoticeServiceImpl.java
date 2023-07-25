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
        // 第一个参数为被删除的图片数，二是被删除的公告数
        final int[] delNum = {0, 0};
        // 一：删除冗余头像
        List<String> avatarList = baseMapper.selectImgAvatar(); // 数据库中头像图片
        avatarList.addAll(myConst()); // 加上默认的照片名
        Set<String> avatarSet = new HashSet<>(avatarList); // 去重
        String[] list01 = new File(basePath).list(); // 文件夹下的头像文件
        if (list01 != null) {
            Arrays.stream(list01)
                    .filter(i -> !avatarSet.contains(i))
                    .forEach(fileName -> {
                        System.out.println("一"+fileName);
                        if (new File(basePath + fileName).delete()) delNum[0]++;
                    });
        }
        // 二：公告文件夹下的冗余文件
        String[] list02 = new File(noticePath).list(); // 文件夹下文件名
        List<String> dbNotice = baseMapper.selectNoticeFile(); // 数据库文件名
        if (list02 != null) {
            Arrays.stream(list02)
                    .filter(i -> !dbNotice.contains(i))
                    .forEach(fileName -> {
                        if (new File(noticePath + fileName).delete()) delNum[1]++;
                    });
        }
        // 三：删除冗余聊天图片
        String[] list03 = new File(chatImg).list(); // 文件夹下文件名
        List<String> dbChat = baseMapper.selectImgChat(); // 数据库文件名
        if (list03 != null) {
            Arrays.stream(list03)
                    .filter(i -> !dbChat.contains(i))
                    .forEach(fileName -> {
                        if (new File(chatImg + fileName).delete()) delNum[0]++;
                    });
        }
        log.info("删除冗余图片{}张，冗余公告{}条", delNum[0], delNum[1]);
        return delNum[0] != 0 || delNum[1] != 0;
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
        String path = UUID.randomUUID() + ".txt";
        writeText(noticeDto.getTextNotice(), path);
        return baseMapper.addNotice(noticeDto.getCreatId(), noticeDto.getNoticeTitle(), path);
    }

    @Override
    public boolean updateNotice(NoticeDto noticeDto) {
        writeText(noticeDto.getTextNotice(), noticeDto.getNoticeFile());
        return baseMapper.updateNotice(noticeDto);
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
        return Arrays.stream(PhotoEnum.values())
                .map(PhotoEnum::getPhotoName)
                .toList();
    }
}
