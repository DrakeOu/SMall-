package com.xjtuse.mall.datahandler;

import com.xjtuse.mall.result.BaseResultVo;
import com.xjtuse.mall.result.ListResultVo;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 失了秩
 * @date 2020/3/15 10:16
 * @description
 */
public class String2StringArray extends BaseTypeHandler<String[]> {


    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, String[] strings, JdbcType jdbcType) throws SQLException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (String s1 : strings) {
            stringBuilder.append(s1);
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        stringBuilder.append("]");
        preparedStatement.setString(i, stringBuilder.toString());
    }

    @Override
    public String[] getNullableResult(ResultSet resultSet, String columnLabel) throws SQLException {
        String result = resultSet.getString(columnLabel);
        String substring = result.substring(1, result.length() - 1);
        String replace = substring.replace("\"", "");
        if (result != null && substring.length()!=0) {
            String[] strings = replace.split(",");
            return strings;
        }
        return new String[0];
    }

    @Override
    public String[] getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return new String[0];
    }

    @Override
    public String[] getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return new String[0];
    }
}