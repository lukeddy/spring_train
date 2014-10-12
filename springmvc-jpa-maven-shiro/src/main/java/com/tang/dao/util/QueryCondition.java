package com.tang.dao.util;

public class QueryCondition {
	
	/** 等于 */
	public static final String EQ = "=";

	/** 小于 */
	public static final String LT = "<";

	/** 大于 */
	public static final String GT = ">";

	/** 小于等于 */
	public static final String LE = "<=";

	/** 大于等于 */
	public static final String GE = ">=";

	/** 相似 */
	public static final String LK = "like";
	
	/** IN */
	public static final String IN = "in";

	// 可以再扩展
	// ......

	/** 自定义jpql语句 */
	public static final String CUSTOM = "custom";

	/** 属性名 */
	private String field;

	/** 操作符 */
	private String operator;

	/** 值 */
	private Object value;

	/** 自定义jpql语句 */
	private String customJPQL;

	/**
	 * 传入自定义语句
	 * 
	 * @param customJPQL
	 */
	public QueryCondition(String customJPQL) {
		this.customJPQL = customJPQL;
		this.operator = CUSTOM;
	}

	/**
	 * 
	 * @param field
	 *            属性名
	 * @param operator
	 *            操作符
	 * @param value
	 *            值 如果属性是日期类型,需将字符串格式为日期 如new SimpleDateFormat("yyyy-MM-dd
	 *            HH:mm:ss").parse("2012-03-23 10:22:22")
	 */
	public QueryCondition(String field, String operator, Object value) {
		this.field = field;
		this.operator = operator;
		this.value = value;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getCustomJPQL() {
		return customJPQL;
	}

	public void setCustomJPQL(String customJPQL) {
		this.customJPQL = customJPQL;
	}

}
