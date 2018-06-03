package top.cyixlq.cy.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by 24215 on 2018/6/2.
 */

public enum EcIcons implements Icon {

    icon_scan('\ue6dd'),
    icon_alipay('\ue60f');

    private char iconChar;

    EcIcons(char iconChar){
        this.iconChar = iconChar;
    }

    @Override
    public String key() {
        return name().replace('_','-');
    }

    @Override
    public char character() {
        return iconChar;
    }
}
