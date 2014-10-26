package util.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tree {
    //此节点下的子节点children，可以无限制 地向下扩展子节点
    private List<Tree> children = new ArrayList<Tree>();
   //属性集合
    private Map<String,Object> attributes =new HashMap<String,Object>();
    //标识
    private String id;
    //要显示的文字
    private String text;
    //指定要显示的
    private String target;
    //选择状态 checked
    private boolean checked;
    //节点图标，如果不给其赋值，有默认图标
    private String iconCls;
    //状态 open 还是close
    private String state;
    //异步树请求调用地址
    private String url;
    
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<Tree> getChildren() {
		return children;
	}
	public void setChildren(List<Tree> children) {
		this.children = children;
	}
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
