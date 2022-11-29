package cn.jasonone.controller;

import cn.dev33.satoken.annotation.SaCheckDisable;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.hash.Hash;
import cn.jasonone.constants.RoleConstant;
import cn.jasonone.model.bean.Account;
import cn.jasonone.model.vo.AccountVO;
import cn.jasonone.model.vo.HttpResult;
import cn.jasonone.service.AccountService;
import com.github.pagehelper.PageInfo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xujie
 * @version 1.0.0
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    @Resource
    private AccountService accountService;
    // account:


    @GetMapping
    @SaCheckDisable(value = "account:list")
    @SaCheckPermission(value = "account:list",orRole = RoleConstant.ADMINISTRATOR)
    public HttpResult search(AccountVO accountVO,
                            @RequestParam(defaultValue = "1") int page,
                             @RequestParam(defaultValue = "0") int pageSize){
        PageInfo<Account> pageInfo = accountService.findAll(accountVO, page, pageSize);
        Map<String,Object> pageInfoMap=new HashMap<>();
        pageInfoMap.put("total",pageInfo.getTotal());
        pageInfoMap.put("page",page);
        pageInfoMap.put("pageSize",pageSize);
        List<Account> list = pageInfo.getList();
        list.forEach(ac->ac.setPassword(null));
        return HttpResult.ok().put("list",list).put("pageInfo",pageInfoMap);
    }

    /**
     * 修改用户信息
     * @param accountVO 用户信息
     * @return
     */
    @PostMapping("/change")
    public HttpResult updateStatus(@Validated(AccountVO.StatusGroup.class) @RequestBody AccountVO accountVO){
        accountService.updateById(accountVO);
        if (accountVO.getStatus()==1) {
            StpUtil.disable(accountVO.getId(),"account:list", -1);
        }else{
            StpUtil.untieDisable(accountVO.getId(),"account:list");
        }
        return HttpResult.ok();
    }
    /**
     * 修改用户信息
     * @param accountVO 用户信息
     * @return
     */
    @PostMapping
    public HttpResult update(@Validated(AccountVO.UpdateGroup.class) @RequestBody AccountVO accountVO){
        accountService.updateById(accountVO);
        return HttpResult.ok();
    }

    /**
     * 新增账号
     * @param avo 账号信息
     * @return
     */
    @PutMapping
    public HttpResult insert(@RequestBody @Validated(AccountVO.InsertGroup.class) AccountVO avo){
        accountService.insert(avo);
        return HttpResult.ok().put("id",avo.getId());
    }

    /**
     * 批量删除账号
     * @param ids 账号id数组
     * @return
     */
    @DeleteMapping
    public HttpResult deleteByIds(int[] ids){
        accountService.deleteByIds(ids);
        return HttpResult.ok();
    }
}
