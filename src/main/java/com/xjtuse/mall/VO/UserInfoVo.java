package com.xjtuse.mall.VO;

import com.xjtuse.mall.bean.user.User;

public class UserInfoVo {
    String nickname;
    String avatarUrl;

    public UserInfoVo(User user){
        this.nickname = user.getNickname();
        this.avatarUrl = user.getAvatar();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
