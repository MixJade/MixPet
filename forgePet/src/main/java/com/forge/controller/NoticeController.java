package com.forge.controller;

import com.forge.common.Result;
import com.forge.dto.NoticeDto;
import com.forge.shiro.RoleConst;
import com.forge.vo.Page;
import com.forge.entity.Employee;
import com.forge.entity.Notice;
import com.forge.service.INoticeService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public List<Notice> getAll() {
        return noticeService.list();
    }

    @GetMapping("/page")
    public Page<List<NoticeDto>> getPage(int numPage, int pageSize, String noticeName) {
        return noticeService.selectByPage(noticeName, numPage, pageSize);
    }

    @GetMapping("/four")
    public List<NoticeDto> getFour() {
        return noticeService.selectFour();
    }

    @PostMapping
    @RequiresRoles(RoleConst.MANAGER)
    public Result save(@RequestBody NoticeDto noticeDto) {
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
        noticeDto.setCreatId(employee.getEmployeeId());
        return Result.choice("添加", noticeService.addNotice(noticeDto));
    }

    /**
     * 禁用与启用
     */
    @DeleteMapping("/disable/{id}")
    @RequiresRoles(RoleConst.MANAGER)
    public Result disableSet(@PathVariable Long id, boolean isDis) {
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
    @RequiresRoles(RoleConst.MANAGER)
    public Result delete(@PathVariable Long id) {
        return Result.choice("删除单个", noticeService.deleteById(id));
    }

    @DeleteMapping("/batch/{ids}")
    @RequiresRoles(RoleConst.MANAGER)
    public Result deleteGroup(@PathVariable long[] ids) {
        return Result.choice("删除多个", noticeService.deleteByIds(ids));
    }

    @PutMapping
    @RequiresRoles(RoleConst.MANAGER)
    public Result update(@RequestBody NoticeDto noticeDto) {
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
        noticeDto.setUpdateId(employee.getEmployeeId());
        return Result.choice("修改", noticeService.updateNotice(noticeDto));
    }

}
