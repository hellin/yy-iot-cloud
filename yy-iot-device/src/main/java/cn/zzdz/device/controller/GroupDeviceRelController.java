package cn.zzdz.device.controller;

import cn.zzdz.common.dto.device.GroupDeviceRelDto;
import cn.zzdz.common.entity.device.DeviceInfo;
import cn.zzdz.common.entity.device.GroupDeviceRelKey;
import cn.zzdz.common.entity.result.Result;
import cn.zzdz.common.entity.result.ResultCode;
import cn.zzdz.common.validated.CreatMethod;
import cn.zzdz.device.service.DeviceInfoService;
import cn.zzdz.device.service.GroupDeviceRelService;
import cn.zzdz.device.service.GroupInfoService;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

import static cn.zzdz.common.constData.ConstData.ID_SIZE;

@Api(tags="设备分组管理")
@Slf4j
@Validated
@RequestMapping("/group/rel")
@RestController
public class GroupDeviceRelController {
    @Autowired
    private GroupDeviceRelService groupDeviceRelService;

    @Autowired
    private DeviceInfoService deviceInfoService;

    @ApiOperation(value="添加设备分组")
    @PostMapping("/")
    public Result save(@Validated({CreatMethod.class}) @RequestBody GroupDeviceRelDto groupDeviceRelDto) {
        log.info("添加设备分组关系：{}", JSON.toJSONString(groupDeviceRelDto));
        GroupDeviceRelKey groupDeviceRel = new GroupDeviceRelKey();
        BeanUtils.copyProperties(groupDeviceRelDto, groupDeviceRel);
        int count = groupDeviceRelService.save(groupDeviceRel);
        return getResult(count);
    }

    @ApiOperation(value="删除分组下的设备")
    @DeleteMapping("/deleteByGroupId")
    public Result deleteByGroupId(@Size(min = ID_SIZE) @RequestParam("groupId") String groupId) {
        int count = groupDeviceRelService.deleteByGroupId(groupId);
        return getResult(count);
    }

    @ApiOperation(value="获取分组下的设备")
    @GetMapping("/findByGroupId")
    public Result findByGroupId(@Size(min = ID_SIZE) @RequestParam("groupId") String groupId,
                                @RequestParam("pageNum") int pageNum,
                                @RequestParam("pageSize") int pageSize) {
        List<String> deviceIds = new ArrayList<>();
        List<GroupDeviceRelKey> groupDeviceRels = groupDeviceRelService.findByGroupId(groupId);
        for (GroupDeviceRelKey groupDeviceRel : groupDeviceRels) {
            deviceIds.add(groupDeviceRel.getDeviceId());
        }
        PageInfo<DeviceInfo> pageInfo = deviceInfoService.findByIds(deviceIds, pageNum, pageSize);
        return new Result(ResultCode.SUCCESS, pageInfo);
        /*
        PageHelper.startPage(pageNum, pageSize);
        List<GroupDeviceRelKey> groupDeviceRels = groupDeviceRelService.findByGroupId(groupId);
        PageInfo<GroupDeviceRelKey> pageInfo = new PageInfo<>(groupDeviceRels);
        return new Result(ResultCode.SUCCESS, pageInfo);
        */
    }

    @ApiOperation(value="根据传入的分组id列表获取对应的item,可传入单个或多个分组id")
    @PostMapping("/findByGroupIds")
    public Result findByGroupIds(@Size(min = 1, max = 1000) @RequestBody List<String> groupIds,
                                                 @RequestParam("pageNum") int pageNum,
                                                 @RequestParam("pageSize") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<GroupDeviceRelKey> groupDeviceRels = groupDeviceRelService.findByGroupIds(groupIds);
        PageInfo<GroupDeviceRelKey> pageInfo = new PageInfo<>(groupDeviceRels);
        return new Result(ResultCode.SUCCESS, pageInfo);
    }

    /**
     * 根据影响行数返回结果
     * @param count
     * @return
     */
    private Result getResult(int count) {
        if (count > 0 ) {
            return new Result(ResultCode.SUCCESS,count);
        }
        return new Result(ResultCode.FAIL,count);
    }
}
