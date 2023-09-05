package com.ship.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ship.common.Result;
import com.ship.model.dto.NoticeDto;
import com.ship.security.model.RoleConst;
import com.ship.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 公告表 前端控制器
 * </p>
 *
 * @author MixJade
 * @since 2023-01-02
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {
    private final INoticeService noticeService;

    @Autowired
    public NoticeController(INoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/page")
    public IPage<NoticeDto> getPage(int numPage, int pageSize, String noticeName) {
        return noticeService.selectByPage(noticeName, numPage, pageSize);
    }

    /**
     * 用户在主页查看公告
     *
     * @return 公告信息与公告文本
     */
    @GetMapping("/four")
    public List<NoticeDto> getFour() {
        return noticeService.selectFour();
    }

    @PostMapping
    @Secured(RoleConst.MANAGER)
    public Result save(@RequestBody NoticeDto noticeDto) {
        return Result.choice("添加", noticeService.addNotice(noticeDto));
    }

    /**
     * 禁用与启用
     */
    @DeleteMapping("/disable/{id}")
    @Secured(RoleConst.MANAGER)
    public Result disableSet(@PathVariable Integer id, boolean isDis) {
        return Result.choice(isDis ? "启用" : "禁用", noticeService.disableNotice(id, isDis));
    }

    /**
     * 删除冗余资源
     */
    @DeleteMapping("/delImg")
    public Result delName() {
        if (noticeService.delImg()) return Result.success("删除冗余资源成功");
        else return Result.error("已无冗余资源");
    }

    @DeleteMapping("/{id}")
    @Secured(RoleConst.MANAGER)
    public Result delete(@PathVariable Integer id) {
        return Result.choice("删除单个", noticeService.deleteById(id));
    }

    @DeleteMapping("/batch/{ids}")
    @Secured(RoleConst.MANAGER)
    public Result deleteGroup(@PathVariable List<Integer> ids) {
        return Result.choice("删除多个", noticeService.deleteByIds(ids));
    }

    @PutMapping
    @Secured(RoleConst.MANAGER)
    public Result update(@RequestBody NoticeDto noticeDto) {
        return Result.choice("修改", noticeService.updateNotice(noticeDto));
    }

}
