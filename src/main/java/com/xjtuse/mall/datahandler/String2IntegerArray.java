package com.xjtuse.mall.datahandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 失了秩
 * @date 2020/3/15 10:16
 * @description
 */
public class String2IntegerArray extends BaseTypeHandler<Integer[]> {


    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Integer[] parameter, JdbcType jdbcType) throws SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (parameter.length != 0){
            for (Integer integer : parameter) {
                sb.append(integer.toString());
                sb.append(",");
            }
            sb.substring(0, sb.length()-1);
        }
        sb.append("]");
        preparedStatement.setString(i, sb.toString());
}

    @Override
    public Integer[] getNullableResult(ResultSet resultSet, String columnLabel) throws SQLException {
        String result = resultSet.getString(columnLabel);
        String substring = result.substring(1, result.length() - 1);
        Integer[] integers = null;
        if (result != null && substring.length()!=0) {
            String[] split = substring.split(",");
            integers = new Integer[split.length];
            for (int i =0; i < split.length; i++) {
                integers[i] = Integer.parseInt(split[i]);
            }
        }
        return integers;
    }

    @Override
    public Integer[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return new Integer[0];
    }

    @Override
    public Integer[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return new Integer[0];
    }

}