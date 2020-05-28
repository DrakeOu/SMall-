package com.xjtuse.mall.result;

/**
 * @author 失了秩
 * @date 2020/3/13 15:51
 * @description
 */
public abstract class ResultVo {
    String errmsg;
    Integer errno;

    @Override
    public String toString() {
        return "ResultVo{" +
                "errmsg='" + errmsg + '\'' +
                ", errno=" + errno +
                '}';
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Integer getErrno() {
        return errno;
    }

    public void setErrno(Integer errno) {
        this.errno = errno;
    }
}
