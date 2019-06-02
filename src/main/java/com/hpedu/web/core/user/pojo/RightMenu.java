package com.hpedu.web.core.user.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

//vip权限菜单
@TableName("rightmenu")
public class RightMenu  implements Serializable {
    @TableId
    private long id;
    private long pId;//父节点id
    private String name;//节点名称
    private boolean open;//是否打开
    private boolean nocheck;//是否显示checkbox
    private String realVal;//实际的值

    @TableField(exist = false)
    private boolean checked;//是否选中

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getpId() {
        return pId;
    }

    public void setpId(long pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isNocheck() {
        return nocheck;
    }

    public void setNocheck(boolean nocheck) {
        this.nocheck = nocheck;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getRealVal() {
        return realVal;
    }

    public void setRealVal(String realVal) {
        this.realVal = realVal;
    }


}
